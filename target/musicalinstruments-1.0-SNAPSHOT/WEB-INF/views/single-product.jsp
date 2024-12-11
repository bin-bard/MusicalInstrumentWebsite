<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

  <!-- title -->
  <title>HarmonyHub</title>

  <!-- favicon -->
  <link rel="shortcut icon" type="image/png" href="${ctx}/assets/img/favicon.png">
  <!-- google font -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
  <!-- fontawesome -->
  <link rel="stylesheet" href="${ctx}/assets/css/all.min.css">
  <!-- bootstrap -->
  <link rel="stylesheet" href="${ctx}/assets/bootstrap/css/bootstrap.min.css">
  <!-- owl carousel -->
  <link rel="stylesheet" href="${ctx}/assets/css/owl.carousel.css">
  <!-- magnific popup -->
  <link rel="stylesheet" href="${ctx}/assets/css/magnific-popup.css">
  <!-- animate css -->
  <link rel="stylesheet" href="${ctx}/assets/css/animate.css">
  <!-- mean menu css -->
  <link rel="stylesheet" href="${ctx}/assets/css/meanmenu.min.css">
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
          <!-- logo -->
          <div class="site-logo">
            <a href="${ctx}">
              <img src="${ctx}/assets/img/logo.png" alt="">
            </a>
          </div>
          <!-- logo -->

          <!-- menu start -->
          <nav class="main-menu">
            <ul>
              <li><a href="${ctx}/admin">Admin</a></li>
              <li><a href="${ctx}/about">About</a></li>
              <li><a href="${ctx}/WEB-INF/views/admin/contact.html">Contact</a></li>
              <li><a href="${ctx}/Cart</a></li>
							<li><a href="${ctx}/shop">Shop</a></li>
            </ul>
          </nav>
          <a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
          <div class="mobile-menu"></div>
          <!-- menu end -->
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end header -->

<!-- breadcrumb-section -->
<div class="breadcrumb-section breadcrumb-bg">
  <div class="container">
    <div class="row">
      <div class="col-lg-8 offset-lg-2 text-center">
        <div class="breadcrumb-text">
          <p>See more Details</p>
          <h1>Single Product</h1>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end breadcrumb section -->

<!-- single product -->
<div class="single-product mt-150 mb-150">
  <div class="container">
    <div class="row">
      <div class="col-md-5">
        <div class="single-product-img">
          <img src="${product.imageURL}" alt="${product.name}">
        </div>
      </div>
      <div class="col-md-7">
        <div class="single-product-content">
          <h3>${product.name}</h3>
          <p class="single-product-pricing"><span> </span> $${product.price}</p>
          <p>${product.description}</p>
          <div class="single-product-form">
            <form action="${ctx}/AddToCart" method="post">
              <input type="hidden" name="productId" value="${product.id}">
              <input type="number" name="quantity" value="1" min="1">
              <div class="cart-buttons">
              <button type="submit" class="boxed-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</button>
              </div>

            </form>
            <p><strong>Categories: </strong>${product.category.name}</p>
          </div>
          <h4>Share:</h4>
          <ul class="product-share">
            <li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
            <li><a href="#"><i class="fab fa-twitter"></i></a></li>
            <li><a href="#"><i class="fab fa-google-plus-g"></i></a></li>
            <li><a href="#"><i class="fab fa-linkedin"></i></a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end single product -->

<!-- more products -->
<div class="more-products mb-150">
  <div class="container">
    <div class="row">
      <div class="col-lg-8 offset-lg-2 text-center">
        <div class="section-title">
          <h3><span class="orange-text">Related</span> Products</h3>
          <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, fuga quas itaque eveniet beatae optio.</p>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-4 col-md-6 text-center">
        <div class="single-product-item">
          <div class="product-image">
            <a href="single-product.html"><img src="assets/img/products/product-img-1.jpg" alt=""></a>
          </div>
          <h3>Guitar</h3>
          <p class="product-price"><span>Per unit</span> 85$ </p>
          <a href="cart.jsp" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 text-center">
        <div class="single-product-item">
          <div class="product-image">
            <a href="single-product.html"><img src="assets/img/products/product-img-2.jpg" alt=""></a>
          </div>
          <h3>Brown Guitar</h3>
          <p class="product-price"><span>Per unit</span> 70$ </p>
          <a href="cart.jsp" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 offset-lg-0 offset-md-3 text-center">
        <div class="single-product-item">
          <div class="product-image">
            <a href="single-product.html"><img src="assets/img/products/product-img-3.jpg" alt=""></a>
          </div>
          <h3>Flute</h3>
          <p class="product-price"><span>Per unit</span> 35$ </p>
          <a href="cart.jsp" class="cart-btn"><i class="fas fa-shopping-cart"></i> Add to Cart</a>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end more products -->

<!-- logo carousel -->
<div class="logo-carousel-section">
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="logo-carousel-inner">
          <div class="single-logo-item">
            <img src="assets/img/company-logos/1.png" alt="">
          </div>
          <div class="single-logo-item">
            <img src="assets/img/company-logos/2.png" alt="">
          </div>
          <div class="single-logo-item">
            <img src="assets/img/company-logos/3.png" alt="">
          </div>
          <div class="single-logo-item">
            <img src="assets/img/company-logos/4.png" alt="">
          </div>
          <div class="single-logo-item">
            <img src="assets/img/company-logos/5.png" alt="">
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end logo carousel -->

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
            <li><a href="about.html">About</a></li>
            <li><a href="services.html">Shop</a></li>
            <li><a href="news.html">News</a></li>
            <li><a href="admin/contact.html">Contact</a></li>
          </ul>
        </div>
      </div>
      <div class="col-lg-3 col-md-6">
        <div class="footer-box subscribe">
          <h2 class="widget-title">Subscribe</h2>
          <p>Subscribe to our mailing list to get the latest updates.</p>
          <form action="index.jsp">
            <input type="email" placeholder="Email">
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
        <p>Copyrights &copy; 2019 - <a href="https://imransdesign.com/">Imran Hossain</a>,  All Rights Reserved.<br>
          Distributed By - <a href="https://themewagon.com/">Themewagon</a>
        </p>
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
<script src="assets/js/jquery-1.11.3.min.js"></script>
<!-- bootstrap -->
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<!-- count down -->
<script src="assets/js/jquery.countdown.js"></script>
<!-- isotope -->
<script src="assets/js/jquery.isotope-3.0.6.min.js"></script>
<!-- waypoints -->
<script src="assets/js/waypoints.js"></script>
<!-- owl carousel -->
<script src="assets/js/owl.carousel.min.js"></script>
<!-- magnific popup -->
<script src="assets/js/jquery.magnific-popup.min.js"></script>
<!-- mean menu -->
<script src="assets/js/jquery.meanmenu.min.js"></script>
<!-- sticker js -->
<script src="assets/js/sticker.js"></script>
<!-- main js -->
<script src="assets/js/main.js"></script>

</body>
</html>