package com.railway.app.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class SessionUtility {
  public static boolean handleSession(HttpServletRequest request, HttpServletResponse response, String protectedUrl) throws IOException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    // Get the current session, if it exists
    HttpSession session = httpRequest.getSession(false);

    // Check if the request is for a protected resource
    boolean isProtectedResource = isProtectedResource(httpRequest, protectedUrl);

    // If the request is for a protected resource and no session is present, redirect to the login page
    if (isProtectedResource && session == null ) {
      httpResponse.sendRedirect("login");
      return false;
    }

    Object email = session.getAttribute("email");
    if(email == null){
      httpResponse.sendRedirect("login");
      return false;
    }

    System.out.println( " " + email);
    // Continue the filter chain

    return true;

  }

  private static boolean isProtectedResource(HttpServletRequest request, String url) {
    // Implement your logic to determine if the request is for a protected resource
    // For example, you can check the URL or request attributes
    // Return true if the resource is protected, otherwise return false

    // Here's a simple example that checks if the URL contains "/protected/"
    String requestURI = request.getRequestURI();
    return requestURI.contains(url);
  }
}
