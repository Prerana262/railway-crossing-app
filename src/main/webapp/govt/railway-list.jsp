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
        <h2>List of Railway Crossings</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Landmark</th>
                    <th>Train Schedule</th>
                    <th>Platform In Charge</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% for (RailwayCrossing crossing : (List<RailwayCrossing>)request.getAttribute("crossings")) { %>
                <tr>
                    <td><%= crossing.getId() %></td>
                    <td><%= crossing.getName() %></td>
                    <td><%= crossing.getAddress() %></td>
                    <td><%= crossing.getLandmark() %></td>
                    <td><%= crossing.getTrainSchedule() %></td>
                    <td><%= crossing.getPlatformInCharge() %></td>
                    <td><%= crossing.getStatus() %></td>
                    <td>
                        <a href="<%= baseUrl %>/govt?action=edit&id=<%= crossing.getId() %>" class="btn btn-primary">Update</a>
                        <!-- <a href="railway-crossing?id=<%= crossing.getId() %>" class="btn btn-primary">Delete</a> -->
                   
                        <form action="<%= baseUrl %>/govt" method="post"> <!-- Assuming deleteRecord is the servlet handling the delete action -->
                            <input type="hidden" name="action" value="delete" />
                            
                            <input type="hidden" name="id" value="<%= crossing.getId() %>">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
