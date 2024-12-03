<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
  <meta name="description" content="POS - Bootstrap Admin Template">
  <meta name="keywords" content="admin, estimates, bootstrap, business, corporate, creative, invoice, html5, responsive, Projects">
  <meta name="author" content="Dreamguys - Bootstrap Admin Template">
  <meta name="robots" content="noindex, nofollow">
  <title>Login</title>

  <link rel="shortcut icon" type="image/x-icon" href="${ctx}/assets/img/favicon.jpg">
  <link rel="stylesheet" href="${ctx}/assets/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}/assets/plugins/fontawesome/css/fontawesome.min.css">
  <link rel="stylesheet" href="${ctx}/assets/plugins/fontawesome/css/all.min.css">
  <link rel="stylesheet" href="${ctx}/assets/css/style.css">
</head>
<body class="account-page">

<div class="main-wrapper">
  <div class="account-content">
    <div class="login-wrapper">
      <div class="login-content">
        <div class="login-userset">
          <div class="login-logo">
            <img src="${ctx}/assets/img/logo.png" alt="img">
          </div>
          <div class="login-userheading">
            <h3>Login</h3>
            <h4>Please enter your login details</h4>
          </div>

          <form method="post" action="${ctx}/login" id="loginForm">
            <div class="form-login">
              <label>Email or Username</label>
              <div class="form-addons">
                <input type="text" name="emailOrUsername" placeholder="Enter your email or username" required>
              </div>
            </div>
          
            <div class="form-login">
              <label>Password</label>
              <div class="pass-group">
                <input type="password" name="password" class="pass-input" placeholder="Enter your password" required>
                <span class="fas toggle-password fa-eye-slash"></span>
              </div>
            </div>
          
            <div class="form-login">
              <button type="submit" class="btn btn-login">Login</button>
            </div>
          </form>

          <script>
          document.getElementById('loginForm').addEventListener('submit', function(e) {
              e.preventDefault();
              const form = this;
              
              fetch(form.action, {
                  method: 'POST',
                  headers: {
                      'Content-Type': 'application/x-www-form-urlencoded',
                  },
                  body: new URLSearchParams(new FormData(form))
              })
              .then(response => response.json())
              .then(data => {
                  console.log('Response data:', data);
                  if (data.success) {
                      console.log('Redirecting to:', '${ctx}/');
                      window.location.href = '${ctx}/';
                  } else {
                      console.log('Login failed:', data.error);
                      document.querySelector('.text-danger').textContent = data.error || 'Login failed';
                  }
              })
              .catch(error => {
                  console.error('Error:', error);
                  document.querySelector('.text-danger').textContent = 'An error occurred during login';
              });
          });
          </script>

          <p class="text-danger mt-3">${error != null ? error : ""}</p>

          <div class="signinform text-center">
            <h4>Don't have an account? <a href="${ctx}/register" class="hover-a">Register</a></h4>
          </div>

          <div class="form-setlogin">
            <h4>Or login with</h4>
          </div>

          <div class="form-sociallink">
            <ul>
              <li>
                <a href="javascript:void(0);">
                  <img src="${ctx}/assets/img/icons/google.png" class="me-2" alt="google">
                  Login with Google
                </a>
              </li>
              <li>
                <a href="javascript:void(0);">
                  <img src="${ctx}/assets/img/icons/facebook.png" class="me-2" alt="facebook">
                  Login with Facebook
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <div class="login-img">
        <img src="${ctx}/assets/img/login.jpg" alt="img">
      </div>
    </div>
  </div>
</div>

<script src="${ctx}/assets/js/jquery-3.6.0.min.js"></script>
<script src="${ctx}/assets/js/feather.min.js"></script>
<script src="${ctx}/assets/js/bootstrap.bundle.min.js"></script>
<script src="${ctx}/assets/js/script.js"></script>
</body>
</html>