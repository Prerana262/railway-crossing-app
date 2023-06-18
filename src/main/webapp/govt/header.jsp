<% String baseUrl=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
    request.getContextPath(); %>
    <h1>Admin Home Page</h1>
    <div class="row">
        <a class="btn btn-primary" href="<%= baseUrl %>/govt">Home</a>
        <a class="btn btn-primary" href="<%= baseUrl %>/railway-form">Add Railway Crossing</a>
        <a class="btn btn-primary" href="#">Search Crossing</a>

        <a class="btn btn-primary" href="<%= baseUrl %>/logout">Logout</a>
    </div>