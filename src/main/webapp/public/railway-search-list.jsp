<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.railway.app.model.*,java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Railway Crossings</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <%@ include file="header.jsp" %>
        <h2>Search Results</h2>

         <% for (RailwayCrossing crossing : (List<RailwayCrossing>)request.getAttribute("crossings")) { %>

        <ul class="list-group">
            <li class="list-group-item">ID: <%= crossing.getId() %>
            </li>
            <li class="list-group-item">Name: <%= crossing.getName() %>
            </li>
            <li class="list-group-item">Address: <%= crossing.getAddress() %>
            </li>
            <li class="list-group-item">Landmark: <%= crossing.getLandmark() %>
            </li>
            <li class="list-group-item">Train Schedule: <%= crossing.getTrainSchedule() %>
            </li>
            <li class="list-group-item">Platform In Charge: <%= crossing.getPlatformInCharge() %>
            </li>
            <li class="list-group-item">Status: <%= crossing.getStatus() %>
            </li>
        </ul>
        <form action="<%= baseUrl %>/public" method="post">
            <!-- Assuming deleteRecord is the servlet handling the delete action -->
            <input type="hidden" name="action" value="favourite" />

            <input type="hidden" name="id" value="<%= crossing.getId() %>">
            <button type="submit" class="btn btn-danger">Favourite</button>
        </form>
        <hr />
          <% } %>
    </div>
</body>
</html>
