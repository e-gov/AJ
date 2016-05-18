/*
 * MIT License
 * Copyright (c) 2016 Estonian Information System Authority (RIA)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ee.ria.dumonitor.storage;


/**
 * String templates for xroad: version 4 has some differences from older
 *
 */
public final class Strs {

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
    + "    <m:findUsageResponse xmlns:m=\"{producerns}\">\n"
    + "      {request}\n"
    + "      <response>\n"
    + "        <faultCode>{faultCode}</faultCode>\n"
    + "        <faultString>{faultString}</faultString>\n"
    + "      </response>\n"
    + "    </m:findUsageResponse>\n"
    + "  </SOAP-ENV:Body>\n"
    + "</SOAP-ENV:Envelope>";

  public static String xroadMessage =
      "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
    + "  {header}"
    + "  <SOAP-ENV:Body>\n"
    + "    <m:findUsageResponse xmlns:m=\"{producerns}\">\n"
    + "      {request}\n"
    + "      {response}\n"
    + "    </m:findUsageResponse>\n"
    + "  </SOAP-ENV:Body>\n"
    + "</SOAP-ENV:Envelope>";

  // next new xroad version

  public static String xroad40Err =
        "<SOAP-ENV:Envelope \n"
    + "    xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"\n"
    + "    xmlns:xrd=\"http://x-road.eu/xsd/xroad.xsd\"\n"
    + "    xmlns:id=\"http://x-road.eu/xsd/identifiers\"\n"
    + "    xmlns:prod=\"{producerns}\">\n"
    + "  {header}"
    + "  <SOAP-ENV:Body>\n"
    + "    <m:findUsageResponse xmlns:m=\"{producerns}\">\n"
    + "      <faultCode>{faultCode}</faultCode>\n"
    + "      <faultString>{faultString}</faultString>\n"
    + "    </m:findUsageResponse>\n"
    + "  </SOAP-ENV:Body>\n"
    + "</SOAP-ENV:Envelope>";

  public static String xroad40Message =
      "<SOAP-ENV:Envelope \n"
    + "    xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"\n"
    + "    xmlns:xrd=\"http://x-road.eu/xsd/xroad.xsd\"\n"
    + "    xmlns:id=\"http://x-road.eu/xsd/identifiers\"\n"
    + "    xmlns:prod=\"{producerns}\">\n"
    + "  {header}"
    + "  <SOAP-ENV:Body>\n"
    + "     {response}\n"
    + "  </SOAP-ENV:Body>\n"
    + "</SOAP-ENV:Envelope>";

  /**
   * Class with only static methods - no instantiation is allowed.
   */
  private Strs() {
    //not called
  }
}
