// aj.js for andmejalgija testrakendus

"use strict";

/* Global conf set in html, commented out from here */

/*
var queryURL = "proxy"; // url to query from: this here is a relative url
//var queryURL = "http://localhost:8080/dumonitor-query-1.0-SNAPSHOT/proxy"; // use either relative or full path   
var pageLength = 50; // maximal number of rows shown on a page
var ajaxTimeout = 5000; // milliseconds until the data query timeouts
var debugging=true; // set to true for some console logging
*/

/* specific conf not set in html */

var maxListStr=20; // max nr of chars in a string to be listed
var maxRecordStr=60; // max nr of chars in a string shown in record

/* query template */

var queryTemplate="<soapenv:Envelope "+
"  xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "+
"  xmlns:xro=\"http://x-road.eu/xsd/xroad.xsd\" " +
"  xmlns:iden=\"http://x-road.eu/xsd/identifiers\" "+
"  xmlns:prod=\"http://dumonitor.x-road.eu/producer\"> " +
" {header} "+
"  <soapenv:Body> "+
"     <prod:findUsage> "+
"        <offset>{offset}</offset>"+
"        <limit>{limit}</limit>"+
"     </prod:findUsage>"+
"  </soapenv:Body>"+
"</soapenv:Envelope>";

/* global vars used and changed during processing */

var data=[]; // stores data read by ajax
var firstRowNr=0; // currently shown first row

/* keys to show in the table */

var listKeys=["logtime","action","receiver"];
  
/* keys to hide in a list if a screen is small */

var wideListKeys=[]; 

/* keys to show for one record */

var recordKeys=["logtime","action","receiver"];
  
/* long Estonian names for the record view  */

var transTable={
  "id":"kirje tehniline id",
  "personcode":"isikukood",
  "logtime":"Aeg",
  "action":"Tegevus",
  "sender":"Saatja",
  "receiver":"Saaja"};
  
/* initial actions when the page is loaded */
  
$(document).ready(function(){ 
  //askData();
});

/* querying and handling data */

function askData() {  
  var soap=makeSOAP();   
  // call proxy with soap
  $.ajax({
    url: queryURL, 
    type: "POST",   
    contentType: "text/xml",
    // Tell jQuery we're expecting text: we will parse it ourselves    
    dataType: "text",
    data: soap,
    // Time in millis until fails with timeout
    timeout: ajaxTimeout,
    // Work with the response
    success: function(response) { handleAjaxSuccess(response); },    
    error: function(response) { handleAjaxError(response); }
  });
}

function makeSOAP() {
  var userid=$("#flt_personcode_value").val();      
  var orgname=$("#flt_organization option:selected").val();
  var uuid=makeUUID();  
  var header=headers[orgname];
  header=header.replace("{userId}",userid).replace("{id}",uuid);
  var envelope=queryTemplate;
  envelope=envelope.replace("{header}",header);
  envelope=envelope.replace("{offset}",firstRowNr);
  envelope=envelope.replace("{limit}",pageLength);         
  debug(envelope);   
  return envelope;
}

// solution from https://jsfiddle.net/xg7tek9j/7/

function makeUUID(){
  var d = new Date().getTime();
  if(window.performance && typeof window.performance.now === "function"){
      d += performance.now(); //use high-precision timer if available
  }
  var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = (d + Math.random()*16)%16 | 0;
    d = Math.floor(d/16);
    return (c=='x' ? r : (r&0x3|0x8)).toString(16);
  });
  return uuid;
}
 

function handleAjaxSuccess(response) {
  var html;
  debug("response: "+response);    
  $("#maintable tr:gt(0)").remove();
  data=parseSOAP(response);  // store to global var
  if (data && ! $.isArray(data)) {
    handleDataError(data);
    return;
  }  
  html=makeTable(data);
  $("#maintable > tbody:last-child").append(html);
  $("#firstrownr").html(getFirstRowNr()+1);
  $("#lastrownr").html(getLastRowNr()+1);  
} 

function handleAjaxError(response) {
  var errmsg="Viga ühenduses serveriga: ";
  if (response && has(response,"status")) {
    errmsg+=" staatus "+response["status"]+", ";
  }
  if (response && has(response,"statusText")) {
     errmsg+=" teade "+response["statusText"];
  }
  errmsg+="<p>Serveri url: "+queryURL;
  $("#errorcontents").html(errmsg);
  $("#errorModal").modal('show');
  debug(errmsg); 
}  

function handleDataError(response) {
  var errmsg="",tmp="";
  if (response && has(response,"errmessage")) {
    tmp=response["errmessage"];
    errmsg=errmsg+encodeHtml(tmp);
  } else {
    errmsg=errmsg+encodeHtml(" "+response);
  }    
  $("#dataerrorcontents").html(errmsg);
  $("#dataerrorModal").modal('show');
  debug(errmsg); 
} 

/* parse soap body data to a json list of objects */

function parseSOAP(soap) {
  var logtime,action,receiver;
  var data=[];
  try {
    var xmlDoc = $.parseXML(soap);
  } catch (err) {
    return {"errmessage":err+""};     
  } 
  $(xmlDoc).find("usage").each(function(){     
    logtime=$(this).find('logtime').text();
    action=$(this).find('action').text();
    receiver=$(this).find('receiver').text();
    data.push({"logtime":logtime,"action":action,"receiver":receiver});    
  });
  return data;
}

/* prepare data list table contents from data */

function makeTable(data) {
  var len=pageLength;
  var str=""; //"<table>";
  if (data.length>pageLength) len=pageLength;
  else len=data.length;
  for (var i=0; i<len; i++) {
    var row=data[i];
    var rowstr="<tr class='datarow'>";
    for (var j=0; j<listKeys.length; j++) {
      var key=listKeys[j];
      var colstr;
      if ($.inArray(key,wideListKeys)>=0)  
        colstr="<td onclick='showRow("+i+")' class='widelistcol'>";
      else 
        colstr="<td onclick='showRow("+i+")'>";
      var value = row[key];
      if (key === "logtime") value = convertDate(value);
      if (has(row,key)) colstr+=encodeHtml(shortenStr(value,maxListStr));
      colstr+="</td>";
      rowstr+=colstr;
    }
    rowstr+="</tr>";
    str+=rowstr;
  }
  //str+="</table>";
  return str;
}

/* convert ISO datetime to human readable datetime */
function convertDate(value) {
  var dateRegexp = /^([0-9]+)-([0-9]+)-([0-9]+)T([0-9:]+)/g;
  var match = dateRegexp.exec(value);
  if (match != null) return match[3] + "." + match[2] + "." + match[1] + " " + match[4];
  return value;
}

/* show one record */
function showRow(nr) {
  var obj=data[nr];
  if (!obj) return;
  var str="<table><tbody>";
  for (var j=0; j<recordKeys.length; j++) {
    var key=recordKeys[j];
    var rowstr="<tr>";    
    rowstr+="<td class='recordkey'>"+trans(key)+"</td><td class='recordvalue'>";
    var value = obj[key];
    if (key === "logtime") value = convertDate(value);
    if (has(obj,key)) rowstr+=encodeHtml(shortenStr(value,maxRecordStr));
    rowstr+="</td></tr>";
    str+=rowstr;
  }
  str+="</tbody></table>";
  $("#recordcontents").html(str);
  $("#recordModal").modal('show');
}

function trans(key) {
  if (has(transTable,key)) return transTable[key];
  else return key;  
}

/* pagination and sorting */

function previousPage() {
  if (firstRowNr<=0) return;
  firstRowNr=firstRowNr-pageLength;
  if (firstRowNr<0) firstRowNr=0;
  askData();
}

function nextPage() {
  debug("nextPage "+firstRowNr+" "+pageLength+" "+getLastRowNr());
  if (getLastRowNr()<firstRowNr+pageLength-1) return;
  firstRowNr+=pageLength;
  debug("firstRowNr "+firstRowNr);
  askData();
}

function getFirstRowNr() {
  return firstRowNr;
}

function getLastRowNr() {
  if (!data) return firstRowNr;
  else if (data && data.length>(firstRowNr+pageLength)) return firstRowNr+pageLength-1;
  else if ((firstRowNr+data.length-1)<0) return 0;
  else return firstRowNr+data.length-1;  
}

function sortcol(key) {
  return false; // no sorting implemented
}

/* helpers */

  
function has(object, key) {return object ? hasOwnProperty.call(object, key) : false;}

function shortenStr(str,maxlen) {
  if (str && str.length>maxlen) {
    return str.substr(0,maxlen)+"...";
  } else {
    return str;
  }
}

function encodeHtml(txt) {
  return txt.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;");
}

function debug(str) {if (debugging) console.log(str); }  

$(document).ready(function(){
  // Fill in select-box:
  $('#flt_organization').html('');
  for (var key in headers) {
    if (key === 'length' || !headers.hasOwnProperty(key)) continue;
    $('#flt_organization').append('<option value="'+encodeHtml(key).replace(/'/g, "&#39;")+'">'+encodeHtml(key)+'</option>');
  }
});
