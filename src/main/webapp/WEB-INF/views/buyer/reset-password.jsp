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
    <title>Reset Password - HarmonyHub</title>

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
                        <h3>Reset Password</h3>
                        <h4>Please enter your new password</h4>
                    </div>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                                ${error}
                        </div>
                    </c:if>

                    <form action="${ctx}/reset-password" method="post" class="form-login">
                        <input type="hidden" name="token" value="${token}">

                        <div class="form-group">
                            <label>New Password</label>
                            <div class="pass-group">
                                <input type="password"
                                       name="password"
                                       class="form-control pass-input"
                                       placeholder="Enter your new password"
                                       required>
                                <span class="fas toggle-password fa-eye-slash"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Confirm Password</label>
                            <div class="pass-group">
                                <input type="password"
                                       name="confirmPassword"
                                       class="form-control pass-input"
                                       placeholder="Confirm your new password"
                                       required>
                                <span class="fas toggle-password fa-eye-slash"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-login w-100">Reset Password</button>
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