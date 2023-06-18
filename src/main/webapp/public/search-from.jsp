<!-- railwaycrossing-form.jsp -->
<!DOCTYPE html>
<html>
  <head>
    <title>Railway Crossing Form</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
    />
  </head>
  <body>
    <div class="container">
      <%@ include file="header.jsp" %>
      <div class="col-md-4">
        <h1>Railway Crossing Form</h1>
        <form action="<%= baseUrl %>/public" method="post">
          <input type="hidden" name="action" value="search" />

          <div class="form-group">
            <label for="name">Name:</label>
            <input
              type="text"
              class="form-control"
              id="name"
              name="name"
              required
            />
          </div>

          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </body>
</html>
