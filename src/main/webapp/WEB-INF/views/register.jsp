<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Đăng ký tài khoản</title>

  <!-- favicon -->
  <link rel="shortcut icon" type="image/png" href="${ctx}/assets/img/favicon.png">
  <!-- google font -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
  <!-- fontawesome -->
  <link rel="stylesheet" href="${ctx}/assets/css/all.min.css">
  <!-- bootstrap -->
  <link rel="stylesheet" href="${ctx}/assets/bootstrap/css/bootstrap.min.css">
  <!-- main style -->
  <link rel="stylesheet" href="${ctx}/assets/css/main.css">
  <!-- responsive -->
  <link rel="stylesheet" href="${ctx}/assets/css/responsive.css">
</head>
<body>

<!--PreLoader-->
<div class="loader">
  <div class="loader-inner">
    <div class="circle"></div>
  </div>
</div>
<!--PreLoader Ends-->

<!-- header -->
<div class="top-header-area" id="sticker">
  <div class="container">
    <div class="row">
      <div class="col-lg-12 col-sm-12 text-center">
        <div class="main-menu-wrap">
          <div class="site-logo">
            <a href="index.jsp">
              <img src="${ctx}/assets/img/logo.png" alt="">
            </a>
          </div>
          <nav class="main-menu">
            <ul>
              <li class="current-list-item"><a href="#">Home</a></li>
              <li><a href="${ctx}/WEB-INF/views/about.html">About</a></li>
              <li><a href="${ctx}/WEB-INF/views/contact.html">Contact</a></li>
              <li><a href="${ctx}/WEB-INF/views/shop.html">Shop</a></li>
              <li>
                <div class="header-icons">
                  <a class="shopping-cart" href="${ctx}/WEB-INF/views/cart.html"><i class="fas fa-shopping-cart"></i></a>
                </div>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end header -->

<!-- breadcrumb section -->
<div class="breadcrumb-section">
  <div class="container">
    <div class="row">
      <div class="col-lg-8 offset-lg-2 text-center">
        <div class="breadcrumb-text">
          <p>Fresh and Organic</p>
          <h1>Đăng ký tài khoản</h1>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end breadcrumb section -->

<!-- registration form section -->
<div class="registration-form-section pt-80 pb-80">
  <div class="container">
    <div class="row">
      <div class="col-lg-8 offset-lg-2">
        <form method="post" action="${ctx}/register">
          <div class="form-group">
            <label for="firstName">Họ:</label>
            <input type="text" id="firstName" name="firstName" class="form-control" required>
          </div>
          <div class="form-group">
            <label for="lastName">Tên:</label>
            <input type="text" id="lastName" name="lastName" class="form-control" required>
          </div>
          <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" class="form-control" required>
          </div>
          <div class="form-group">
            <label for="phoneNumber">Số điện thoại:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" required>
          </div>
          <div class="form-group">
            <label for="username">Tên tài khoản:</label>
            <input type="text" id="username" name="username" class="form-control" required>
          </div>
          <div class="form-group">
            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" class="form-control" required>
          </div>
          <button type="submit" class="btn btn-primary">Đăng ký</button>
        </form>
        <p class="text-danger mt-3">${error != null ? error : ""}</p>
      </div>
    </div>
  </div>
</div>
<!-- end registration form section -->

<!-- footer -->
<div class="footer-area">
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-md-6">
        <div class="footer-box about-widget">
          <h2 class="widget-title">About us</h2>
          <p>Ut enim ad minim veniam perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae.</p>
        </div>
      </div>
      <div class="col-lg-3 col-md-6">
        <div class="footer-box get-in-touch">
          <h2 class="widget-title">Get in Touch</h2>
          <ul>
            <li>34/8, East Hukupara, Gifirtok, Sadan.</li>
            <li>support@fruitkha.com</li>
            <li>+00 111 222 3333</li>
          </ul>
        </div>
      </div>
      <div class="col-lg-3 col-md-6">
        <div class="footer-box pages">
          <h2 class="widget-title">Pages</h2>
          <ul>
            <li><a href="index.jsp">Home</a></li>
            <li><a href="${ctx}/WEB-INF/views/about.html">About</a></li>
            <li><a href="${ctx}/WEB-INF/views/shop.html">Shop</a></li>
            <li><a href="${ctx}/WEB-INF/views/news.html">News</a></li>
            <li><a href="${ctx}/WEB-INF/views/contact.html">Contact</a></li>
          </ul>
        </div>
      </div>
      <div class="col-lg-3 col-md-6">
        <div class="footer-box subscribe">
          <h2 class="widget-title">Subscribe</h2>
          <p>Subscribe to our mailing list to get the latest updates.</p>
          <form action="index.jsp">
            <input type="email" placeholder="Email" class="form-control">
            <button type="submit"><i class="fas fa-paper-plane"></i></button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end footer -->

<!-- copyright -->
<div class="copyright">
  <div class="container">
    <div class="row">
      <div class="col-lg-6 col-md-12">
        <p>Copyrights &copy; 2023 - <a href="https://imransdesign.com/">Imran Hossain</a>, All Rights Reserved.</p>
      </div>
      <div class="col-lg-6 text-right col-md-12">
        <div class="social-icons">
          <ul>
            <li><a href="#" target="_blank"><i class="fab fa-facebook-f"></i></a></li>
            <li><a href="#" target="_blank"><i class="fab fa-twitter"></i></a></li>
            <li><a href="#" target="_blank"><i class="fab fa-instagram"></i></a></li>
            <li><a href="#" target="_blank"><i class="fab fa-linkedin"></i></a></li>
            <li><a href="#" target="_blank"><i class="fab fa-dribbble"></i></a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end copyright -->

<!-- jquery -->
<script src="${ctx}/assets/js/jquery-1.11.3.min.js"></script>
<!-- bootstrap -->
<script src="${ctx}/assets/bootstrap/js/bootstrap.min.js"></script>
<!-- main js -->
<script src="${ctx}/assets/js/main.js"></script>

</body>
</html>