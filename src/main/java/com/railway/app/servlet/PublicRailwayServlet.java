package com.railway.app.servlet;

import com.railway.app.dao.RailwayCrossingDAO;
import com.railway.app.dao.RailwayCrossingMappingDAO;
import com.railway.app.model.RailwayCrossing;
import com.railway.app.model.RailwayCrossingMapping;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/public")
public class PublicRailwayServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    String action = request.getParameter("action");
    if (action == null) {
      RailwayCrossingDAO crossingDAO = new RailwayCrossingDAO();
      List<RailwayCrossing> crossings = crossingDAO.getAllRailwayCrossings();
      // Set the list as an attribute in the request
      request.setAttribute("crossings", crossings);
      // Forward the request to the JSP for rendering
      request.getRequestDispatcher("public/railway-list.jsp").forward(request, response);
      return;
    }

    if(action.equals("favorite")){
      String email = request.getSession(false).getAttribute("email").toString();
      RailwayCrossingMappingDAO railwayCrossingMappingDAO = new RailwayCrossingMappingDAO();
      List<RailwayCrossingMapping> mappings = railwayCrossingMappingDAO.getMappingsByEmail(email);
      RailwayCrossingDAO crossingDAO = new RailwayCrossingDAO();
      List<RailwayCrossing> crossings = crossingDAO.getAllRailwayCrossings();

      List<RailwayCrossing> filteredCrossings = crossings.stream().filter(e->{
        return mappings.stream().filter(e1-> {
          return e1.getRailwayCrossingId() == e.getId();}).findAny().isPresent();
      }).collect(Collectors.toList());

      System.out.println(filteredCrossings);
      request.setAttribute("crossings", filteredCrossings);
      // Forward the request to the JSP for rendering
      request.getRequestDispatcher("public/railway-fav-list.jsp").forward(request, response);
    }

    if(action.equals("delete_favorite")){
      String email = request.getSession(false).getAttribute("email").toString();
      int id = Integer.parseInt(request.getParameter("id"));

      RailwayCrossingMappingDAO dao = new RailwayCrossingMappingDAO();
      dao.deleteMapping(email, id);
      response.sendRedirect("public?action=favorite");
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String action = request.getParameter("action");

    if(action.equals("search"))
    {
      String crossingName = request.getParameter("name");
      RailwayCrossingDAO dao = new RailwayCrossingDAO();
      List<RailwayCrossing> crossings = dao.searchByName(crossingName);
      request.setAttribute("crossings", crossings);
      // Forward the request to the JSP for rendering
      request.getRequestDispatcher("public/railway-search-list.jsp").forward(request, response);
      return;
    }

    String email = request.getSession(false).getAttribute("email").toString();
    int id = Integer.parseInt(request.getParameter("id"));

    RailwayCrossingMapping mapping = new RailwayCrossingMapping();
    mapping.setEmail(email);
    mapping.setRailwayCrossingId(id);

    RailwayCrossingMappingDAO dao = new RailwayCrossingMappingDAO();
    dao.saveMapping(mapping);

    response.sendRedirect("public?action=favorite");

  }
}
