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
<title>Login - Pos admin template</title>

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
<h3>Sign In</h3>
<h4>Please login to your account</h4>
</div>
<div class="form-login">
<label>Email</label>
<div class="form-addons">
<input type="text" placeholder="Enter your email address">
<img src="${ctx}/assets/img/icons/mail.svg" alt="img">
</div>
</div>
<div class="form-login">
<label>Password</label>
<div class="pass-group">
<input type="password" class="pass-input" placeholder="Enter your password">
<span class="fas toggle-password fa-eye-slash"></span>
</div>
</div>
<div class="form-login">
<div class="alreadyuser">
<h4><a href="admin/forgetpassword.html" class="hover-a">Forgot Password?</a></h4>
</div>
</div>
<div class="form-login">
<a class="btn btn-login" href="index.html">Sign In</a>
</div>
<div class="signinform text-center">
<h4>Don’t have an account? <a href="admin/signup.html" class="hover-a">Sign Up</a></h4>
</div>
<div class="form-setlogin">
<h4>Or sign up with</h4>
</div>
<div class="form-sociallink">
<ul>
<li>
<a href="javascript:void(0);">
<img src="${ctx}/assets/img/icons/google.png" class="me-2" alt="google">
Sign Up using Google
</a>
</li>
<li>
<a href="javascript:void(0);">
<img src="${ctx}/assets/img/icons/facebook.png" class="me-2" alt="google">
Sign Up using Facebook
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