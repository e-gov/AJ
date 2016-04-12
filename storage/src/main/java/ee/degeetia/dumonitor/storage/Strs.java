package ee.degeetia.dumonitor.storage;

public class Strs {
     
  public static String xroadHeader =    
      "<SOAP-ENV:Header xmlns:xrd=\"http://x-road.ee/xsd/x-road.xsd\">\n"
    + "    <xrd:consumer>{consumer}</xrd:consumer>\n"
    + "    <xrd:producer>{producer}</xrd:producer>\n"
    + "    <xrd:userId>{userId}</xrd:userId>\n"
    + "    <xrd:id>{id}</xrd:id>\n"
    + "    <xrd:service>{service}</xrd:service>\n"
    + "    <xrd:issue/>\n"
    + "  </SOAP-ENV:Header>\n";
    
   public static String xroadTechErr =  
      "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" 
    + "  <SOAP-ENV:Body>\n"
    + "    <SOAP-ENV:Fault>\n"
    + "      <faultcode>{faultCode}</faultcode>\n"
    + "      <faultstring>{faultString}</faultstring>\n"
    + "      <faultactor>andmejalgija Xroad.class</faultactor>\n"
    + "      <detail>{faultString}</detail>\n"
    + "   </SOAP-ENV:Fault>\n"
    + "  </SOAP-ENV:Body>\n"
    + "</SOAP-ENV:Envelope>";
  
  public static String xroadErr = 
      "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
    + "  {header}"
    + "  <SOAP-ENV:Body>\n"
    + "    <m:paringResponse xmlns:m=\"{producerns}\">\n"
    + "      {request}\n"
    + "      <response>\n"
    + "        <faultCode>{faultCode}</faultCode>\n"
    + "        <faultString>{faultString}</faultString>\n"
    + "      </response>\n"
    + "    </m:paringResponse>\n"
    + "  </SOAP-ENV:Body>\n"
    + "</SOAP-ENV:Envelope>";
    
}