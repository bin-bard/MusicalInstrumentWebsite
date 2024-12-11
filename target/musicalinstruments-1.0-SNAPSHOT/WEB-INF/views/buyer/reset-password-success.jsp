<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
  <meta name="description" content="HarmonyHub - Musical Instruments Store">
  <meta name="keywords" content="admin, music, instruments, bootstrap, business, ecommerce">
  <meta name="author" content="HarmonyHub">
  <meta name="robots" content="noindex, nofollow">
  <title>Password Reset Success - HarmonyHub</title>

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
            <img src="${ctx}/assets/img/logo.png" alt="HarmonyHub Logo">
          </div>
          <div class="login-userheading text-center">
            <h3><i class="fas fa-check-circle text-success"></i> Success!</h3>
            <h4>Your password has been reset successfully</h4>
            <p class="mt-4">You can now login with your new password</p>

            <div class="mt-4">
              <a href="${ctx}/login" class="btn btn-submit">
                <i class="fas fa-sign-in-alt me-2"></i>Login Now
              </a>
            </div>
          </div>
        </div>
      </div>
      <div class="login-img">
        <img src="${ctx}/assets/img/login.jpg" alt="Login Background">
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