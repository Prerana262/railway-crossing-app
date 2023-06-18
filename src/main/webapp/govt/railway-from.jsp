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
        <form action="<%= baseUrl %>/govt" method="post">
          <input type="hidden" name="action" value="create" />

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

          <div class="form-group">
            <label for="address">Address:</label>
            <input
              type="text"
              class="form-control"
              id="address"
              name="address"
              required
            />
          </div>

          <div class="form-group">
            <label for="landmark">Landmark:</label>
            <input
              type="text"
              class="form-control"
              id="landmark"
              name="landmark"
            />
          </div>

          <div class="form-group">
            <label for="trainSchedule">Train Schedule:</label>
            <input
              type="datetime-local"
              class="form-control"
              id="trainSchedule"
              name="trainSchedule"
              required
            />
          </div>

          <div class="form-group">
            <label for="platformInCharge">Platform In Charge:</label>
            <input
              type="text"
              class="form-control"
              id="platformInCharge"
              name="platformInCharge"
              required
            />
          </div>

          <div class="form-group">
            <label for="status">Status:</label>
            <br/>
            <input
              type="radio"

              id="status1"
              name="status"
              value="Open"
              required
            /> Open
            <br/>

             <input
                   type="radio"

                          id="status1"
                          name="status"
                          value="Close"
                          required
                        /> Close
          </div>


          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
      </div>
    </div>
  </body>
</html>
