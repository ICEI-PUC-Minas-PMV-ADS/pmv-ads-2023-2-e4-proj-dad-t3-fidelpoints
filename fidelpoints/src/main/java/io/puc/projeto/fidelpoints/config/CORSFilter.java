package io.puc.projeto.fidelpoints.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.LogRecord;

public class CORSFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.addHeader("Access-Control-Allow-Origin", "*");
      httpResponse.addHeader("Access-Control-Allow-Methods", "*");
      httpResponse.setHeader("Access-Control-Allow-Headers", "*");       chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }

}