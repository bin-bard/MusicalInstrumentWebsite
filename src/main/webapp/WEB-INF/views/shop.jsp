<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

    <!-- title -->
    <title>Shop</title>

    <!-- favicon -->
    <link rel="shortcut icon" type="image/png" href="assets/img/favicon.png">
    <!-- google font -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- fontawesome -->
    <link rel="stylesheet" href="assets/css/all.min.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <!-- owl carousel -->
    <link rel="stylesheet" href="assets/css/owl.carousel.css">
    <!-- magnific popup -->
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <!-- animate css -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- mean menu css -->
    <link rel="stylesheet" href="assets/css/meanmenu.min.css">
    <!-- main style -->
    <link rel="stylesheet" href="assets/css/main.css">
    <!-- responsive -->
    <link rel="stylesheet" href="assets/css/responsive.css">
</head>
<body>

    <!-- PreLoader -->
    <div class="loader">
        <div class="loader-inner">
            <div class="circle"></div>
        </div>
    </div>

    <!-- Header -->
    <div class="top-header-area" id="sticker">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-sm-12 text-center">
                    <div class="main-menu-wrap">
                        <!-- logo -->
                        <div class="site-logo">
                            <a href="index.jsp">
                                <img src="assets/img/logo.png" alt="">
                            </a>
                        </div>
                        <!-- logo -->

                        <!-- menu start -->
                        <nav class="main-menu">
                            <ul>
                                <li class="current-list-item"><a href="#">Home</a>
                                    <ul class="sub-menu">
                                        <li><a href="index.jsp">Static Home</a></li>
                                        <li><a href="index.jsp">Slider Home</a></li>
                                    </ul>
                                </li>
                                <li><a href="about.html">About</a></li>
                                <li><a href="#">Pages</a>
                                    <ul class="sub-menu">
                                        <li><a href="error404.jsp">404 page</a></li>
                                        <li><a href="about.html">About</a></li>
                                        <li><a href="${ctx}/Cart">Cart</a></li>
                                        <li><a href="${ctx}/Checkout">Check Out</a></li>
                                        <li><a href="contact.html">Contact</a></li>
                                        <li><a href="news.html">News</a></li>
                                        <li><a href="${ctx}/Shop">Shop</a></li>
                                    </ul>
                                </li>
                                <li><a href="news.html">News</a>
                                    <ul class="sub-menu">
                                        <li><a href="news.html">News</a></li>
                                        <li><a href="${ctx}/SingleNews">Single News</a></li>
                                    </ul>
                                </li>
                                <li><a href="contact.html">Contact</a></li>
                                <li><a href="${ctx}/Shop">Shop</a>
                                    <ul class="sub-menu">
                                        <li><a href="${ctx}/Shop">Shop</a></li>
                                        <li><a href="${ctx}/Checkout">Check Out</a></li>
                                        <li><a href="${ctx}/SingleProduct">Single Product</a></li>
                                        <li><a href="${ctx}/Cart">Cart</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <div class="header-icons">
                                        <a class="shopping-cart" href="${ctx}/Cart"><i class="fas fa-shopping-cart"></i></a>
                                        <a class="mobile-hide search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                                    </div>
                                </li>
                            </ul>
                        </nav>
                        <a class="mobile-show search-bar-icon" href="#"><i class="fas fa-search"></i></a>
                        <div class="mobile-menu"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Search Area -->
    <div class="search-area">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <span class="close-btn"><i class="fas fa-window-close"></i></span>
                    <div class="search-bar">
                        <div class="search-bar-tablecell">
                            <h3>Search For:</h3>
                            <input type="text" placeholder="Keywords">
                            <button type="submit">Search <i class="fas fa-search"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Breadcrumb Section -->
    <div class="breadcrumb-section breadcrumb-bg">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 offset-lg-2 text-center">
                    <div class="breadcrumb-text">
                        <h1>Shop</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Products Section -->
    <div class="product-section mt-150 mb-150">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-filters">
                        <ul>
                            <li class="active" data-filter="*">All</li>
                            <c:forEach var="category" items="${categories}">
                                <li data-filter=".${category.name}">${category.name}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>

            <!-- Products List -->
            <div class="row product-lists">
                <c:forEach var="product" items="${products}">
                    <div class="col-lg-4 col-md-6 text-center ${product.category.name}">
                        <div class="single-product-item">
                            <div class="product-image">
                                <a href="${ctx}/SingleProduct?id=${product.id}">
                                    <img src="${product.image}" alt="${product.name}">
                                </a>
                            </div>
                            <h3>${product.name}</h3>
                            <p class="product-price">${product.price}</p>
                            <a href="${ctx}/Cart" class="cart-btn">
                                <i class="fas fa-shopping-cart"></i> Add to Cart
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <!-- Footer Section -->
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
                            <li><a href="contact.html">Contact</a></li>
                            <li><a href="news.html">News</a></li>
                            <li><a href="${ctx}/Shop">Shop</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="footer-box newsletter">
                        <h2 class="widget-title">Newsletter</h2>
                        <p>Sign up for the newsletter and get the latest updates about products.</p>
                        <input type="email" placeholder="Your email">
                        <button class="subscribe-btn">Subscribe</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Scripts -->
    <script src="assets/js/jquery-3.5.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <script src="assets/js/magnific-popup.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>
