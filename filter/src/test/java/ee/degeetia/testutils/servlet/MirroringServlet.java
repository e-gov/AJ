package ee.degeetia.testutils.servlet;


import ee.degeetia.dumonitor.common.util.IOUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MirroringServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentType(request.getContentType());
    response.setContentLength(request.getContentLength());
    IOUtil.pipe(request.getInputStream(), response.getOutputStream());
  }

}
