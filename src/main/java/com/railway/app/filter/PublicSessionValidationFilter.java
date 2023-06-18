package com.railway.app.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/public")
public class PublicSessionValidationFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    boolean flag = SessionUtility.handleSession((HttpServletRequest) request, (HttpServletResponse) response, "/public");
    if (flag) {
      chain.doFilter(request, response);
    }
  }


}
