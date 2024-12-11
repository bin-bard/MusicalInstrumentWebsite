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
  <title>Email Verification Failed</title>

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
          <div class="login-userheading text-center">
            <h3><i class="fas fa-exclamation-circle text-danger"></i> Verification Failed</h3>
            <h4>Email verification was unsuccessful</h4>

            <div class="alert alert-danger mt-4">
              <p>${error}</p>
            </div>

            <div class="mt-4">
              <a href="${ctx}/register" class="btn btn-submit me-2">
                <i class="fas fa-user-plus me-2"></i>Register Again
              </a>
              <a href="${ctx}/" class="btn btn-cancel">
                <i class="fas fa-home me-2"></i>Back to Home
              </a>
            </div>
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