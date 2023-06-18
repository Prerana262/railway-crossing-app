<%
String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .submit-button {
            background-color: green;
            color: white;
        }

        .center-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .login-form {
            max-width: 400px;
            padding: 20px;
            margin: 20px;
        }
    </style>
</head>
<body>
    <div class="center-container">
        <div class="login-form">
            <h1 class="text-center">Railway Crossing - Login</h1>
            <form action="<%= baseUrl %>/authenticate" method="POST" class="mt-4">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>

                <button type="submit" class="btn btn-success submit-button">Login</button>
            </form>

            <p class="mt-3 text-center">Don't have an account? <a href="registration.html">Register here</a></p>
        </div>
    </div>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
