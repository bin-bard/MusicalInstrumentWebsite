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
    <title>Forgot Password - HarmonyHub</title>

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
                    <div class="login-userheading">
                        <h3>Forgot Password?</h3>
                        <h4>Don't worry! It happens. Please enter the email address<br>
                            associated with your account.</h4>
                    </div>
                    
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:if>
                    
                    <c:if test="${not empty success}">
                        <div class="alert alert-success" role="alert">
                            ${success}
                        </div>
                    </c:if>

                    <form action="${ctx}/forgot-password" method="post" class="form-login">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <div class="form-addons">
                                <input type="email" 
                                       id="email" 
                                       name="email" 
                                       class="form-control" 
                                       placeholder="Enter your email address" 
                                       required>
                                <img src="${ctx}/assets/img/icons/mail.svg" alt="email icon">
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <button type="submit" class="btn btn-login w-100">Submit</button>
                        </div>
                        
                        <div class="form-group text-center">
                            <a href="${ctx}/login" class="forgot-link">Back to Login</a>
                        </div>
                    </form>
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