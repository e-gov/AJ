package ee.degeetia.dumonitor.storage;

public class Strs {
   
  // first old xroad version
  
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
    
  public static String xroadMessage = 
      "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
    + "  {header}"
    + "  <SOAP-ENV:Body>\n"
    + "    <m:paringResponse xmlns:m=\"{producerns}\">\n"
    + "      {request}\n"
    + "      {response}\n"
    + "    </m:paringResponse>\n"
    + "  </SOAP-ENV:Body>\n"
    + "</SOAP-ENV:Envelope>";  
    
  // next new xroad version  
    
  public static String xroad40Header =    
      "<SOAP-ENV:Header \n"
    + "    xmlns:xrd=\"http://x-road.ee/xsd/x-road.xsd\"\n"
    + "    xmlns:id=\"http://x-road.eu/xsd/identifiers\">\n" 
    + "  <xrd:client id:objectType=\"MEMBER\">\n"
    + "    <id:xRoadInstance>EE</id:xRoadInstance>\n"
    + "    <id:memberClass>GOV</id:memberClass>\n"
    + "    <id:memberCode>MEMBER1</id:memberCode>\n"
    + "    <id:subsystemCode>SUBSYSTEM1</id:subsystemCode>\n"
    + "  </xrd:client>\n"
    + "  <xrd:service id:objectType=\"SERVICE\">\n"
    + "    <id:xRoadInstance>EE</id:xRoadInstance>\n"
    + "    <id:memberClass>GOV</id:memberClass>\n"
    + "    <id:memberCode>MEMBER2</id:memberCode>\n"
    + "    <id:subsystemCode>SUBSYSTEM2</id:subsystemCode>\n"
    + "    <id:serviceCode>getRandom</id:serviceCode>\n"
    + "    <id:serviceVersion>v1</id:serviceVersion>\n"
    + "  </xrd:service>\n"
    + "  <xrd:userId>EE12345678901</xrd:userId>\n"
    + "  <xrd:id>{id}</xrd:id>\n"
    + "  <xrd:protocolVersion>4.0</xrd:protocolVersion>\n"
    + "  </SOAP-ENV:Header>\n";
    
   public static String xroad40TechErr =  
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
  
  public static String xroad40Err = 
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
    
  public static String xroad40Message = 
      "<SOAP-ENV:Envelope \n"
    + "    xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"\n"
    + "    xmlns:xrd=\"http://x-road.eu/xsd/xroad.xsd\"\n" 
    + "    xmlns:id=\"http://x-road.eu/xsd/identifiers\"\n" 
    + "    xmlns:prod=\"http://dumonitor.x-road.eu/producer\">\n"
    + "  {header}"
    + "  <SOAP-ENV:Body>\n"
    + "    <m:paringResponse xmlns:m=\"{producerns}\">\n"
    + "      {request}\n"
    + "      {response}\n"
    + "    </m:paringResponse>\n"
    + "  </SOAP-ENV:Body>\n"
    + "</SOAP-ENV:Envelope>";    
    
}
