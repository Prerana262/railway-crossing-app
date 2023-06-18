<%
String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<h1>Public Home Page</h1>
<div class="row">
    <a class="btn btn-primary" href="<%= baseUrl %>/public">Home</a>
    <a class="btn btn-primary" href="<%= baseUrl %>/public?action=favorite">Favorite</a>
    <a class="btn btn-primary" href="<%= baseUrl %>/search">Search Crossing</a>
    <a class="btn btn-primary" href="<%= baseUrl %>/logout">Logout</a>

</div>