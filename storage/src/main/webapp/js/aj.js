// aj.js for andmejalgija 

"use strict";

/* Global conf set in html, commented out from here */

/*
var queryURL = "query"; // url to query from: this here is a relative url
//var queryURL = "http://localhost:8080/dumonitor-storage-1.0-SNAPSHOT/query"; // use either relative or full path   
var pageLength = 50; // maximal number of rows shown on a page
var ajaxTimeout = 5000; // milliseconds until the data query timeouts
var debugging=true; // set to true for some console logging
*/

/* specific conf not set in html */

var maxListStr=20; // max nr of chars in a string to be listed
var maxRecordStr=60; // max nr of chars in a string shown in record

/* global vars used and changed during processing */

var data=[]; // stores data read by ajax
var firstRowNr=0; // currently shown first row

/* keys to show in the table */

var listKeys=[
  "personcode","logtime","actioncode",
  "receivercode","sendercode","usercode",
  "restrictions"];
  
/* keys to hide in a list if a screen is small */

var wideListKeys=[
  "receivercode","sendercode","restrictions"];  

/* keys to show for one record */

var recordKeys=[
  "personcode","logtime","actioncode","action",
  "receivercode","receiver","sendercode","sender",
  "usercode", "xroadrequestid","xroadservice",
  "restrictions",
  ];  
  
/* keys in a filter */

var filterKeys=recordKeys.concat(["to_date","from_date"]);  
  
/* long Estonian names for the record view  */

var transTable={
  "id":"kirje tehniline id",
  "personcode":"isikukood",
  "action":"Tegevuse avalik nimi",
  "sender":"Saatja avalik nimi",
  "receiver":"Saaja avalik nimi",
  "restrictions":"Piirangud (P: piiratud)",
  "sendercode":"Saatja tehniline kood",
  "receivercode":"Saaja tehniline kood",
  "actioncode":"Tegevuse tehniline kood",
  "xroadrequestid":"X-tee päringu id",
  "xroadservice":"X-tee teenuse nimetus",
  "usercode":"Andmete töötleja isikukood",
  "logtime":"Kirje salvestamisaeg"};
  
/* initial actions when the page is loaded */
  
$(document).ready(function(){ 
  askData();
});

/* querying and handling data */

function askData() {
  var params=makeParams();
  debug("loading url: "+queryURL);
  debug("with params: ")
  debug(params);  
  // Using jsonp and cross-domain request
  $.ajax({
    url: queryURL, 
    // The name of the callback parameter, as specified by the server 
    jsonp: "callback",
    // Tell jQuery we're expecting JSONP
    dataType: "jsonp", 
    // Params as object to be encoded as cgi in get
    data: params,
    // Time in millis until fails with timeout
    timeout: ajaxTimeout,
    // Work with the response
    success: function(response) { handleAjaxSuccess(response); },    
    error: function(response) { handleAjaxError(response); }
  });
}

function makeParams() {
  var params=
    {"offset": firstRowNr,
     "limit": pageLength};  
  for (var i=0; i<filterKeys.length; i++) {
    var key=filterKeys[i];
    var val=$("#flt_"+key+"_value").val();
    if (val) params[key]=val;
  }
  return params;   
}

function handleAjaxSuccess(response) {
  var html;
  debug(response);  
  data=response; // store to global var
  $("#maintable tr:gt(0)").remove();
  if (response && ! $.isArray(response)) {
    handleDataError(response);
    return;
  }  
  html=makeTable(response);
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
    tmp=response["errmessage"].replace("database query error: ERROR:", 
                                       "päringu viga: ");
    errmsg=errmsg+encodeHtml(tmp);
  } else {
    errmsg=errmsg+encodeHtml(" "+response);
  }    
  $("#dataerrorcontents").html(errmsg);
  $("#dataerrorModal").modal('show');
  debug(errmsg); 
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
      if (has(row,key)) colstr+=encodeHtml(shortenStr(row[key],maxListStr));
      colstr+="</td>";
      rowstr+=colstr;
    }
    rowstr+="</tr>";
    str+=rowstr;
  }
  //str+="</table>";
  return str;
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
    if (has(obj,key)) rowstr+=encodeHtml(shortenStr(obj[key],maxRecordStr));
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

/* handling form */

function clearFilters() { 
  debug("clearing");
  
  for (var i=0; i<filterKeys.length; i++) {
    var key=filterKeys[i];
    $("#flt_"+key+"_value").val("");
    //input.replaceWith(input.val('').clone(true));
  }
  //$("#flt_personcode_value").attr("value","");
  $("#flt_personcode_value").val("");
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
  return txt.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
}

function debug(str) {if (debugging) console.log(str); }  

