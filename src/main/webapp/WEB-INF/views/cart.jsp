<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Responsive Bootstrap4 Shop Template, Created by Imran Hossain from https://imransdesign.com/">

    <!-- title -->
    <title>Cart</title>

    <!-- favicon -->
    <link rel="shortcut icon" type="image/png" href="<%= request.getContextPath() %>/assets/img/favicon.png">
    <!-- google font -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">
    <!-- fontawesome -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/all.min.css">
    <!-- bootstrap -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/bootstrap/css/bootstrap.min.css">
    <!-- owl carousel -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/owl.carousel.css">
    <!-- magnific popup -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/magnific-popup.css">
    <!-- animate css -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/animate.css">
    <!-- mean menu css -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/meanmenu.min.css">
    <!-- main style -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/main.css">
    <!-- responsive -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/responsive.css">

</head>
<body>

<!-- PreLoader -->
<div class="loader">
    <div class="loader-inner">
        <div class="circle"></div>
    </div>
</div>
<!-- PreLoader Ends -->

<!-- header -->
<div class="top-header-area" id="sticker">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-sm-12 text-center">
                <div class="main-menu-wrap">
                    <!-- logo -->
                    <div class="site-logo">
                        <a href="index.jsp">
                            <img src="<%= request.getContextPath() %>/assets/img/logo.png" alt="">
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
                    <!-- menu end -->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end header -->

<!-- cart -->
<div class="cart-section mt-150 mb-150">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-12">
                <div class="cart-table-wrap">
                    <table class="cart-table">
                        <thead class="cart-table-head">
                            <tr class="table-head-row">
                                <th class="product-remove"></th>
                                <th class="product-image">Product Image</th>
                                <th class="product-name">Name</th>
                                <th class="product-price">Price</th>
                                <th class="product-quantity">Quantity</th>
                                <th class="product-total">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cart.items}">
                                <tr class="table-body-row">
                                    <td class="product-remove">
                                        <a href="${ctx}/removeFromCart?productId=${item.id}">
                                            <i class="far fa-window-close"></i>
                                        </a>
                                    </td>
                                    <td class="product-image">
                                        <img src="${item.imageUrl}" alt="${item.name}">
                                    </td>
                                    <td class="product-name">${item.name}</td>
                                    <td class="product-price">${item.price}</td>
                                    <td class="product-quantity">
                                        <form action="${ctx}/updateCart" method="POST">
                                            <input type="number" name="quantity" value="${item.quantity}" min="1" max="99" />
                                            <input type="hidden" name="productId" value="${item.id}" />
                                            <button type="submit" class="btn btn-sm btn-primary">Update</button>
                                        </form>
                                    </td>
                                    <td class="product-total">${item.totalPrice}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-lg-4">
                <div class="total-section">
                    <table class="total-table">
                        <thead class="total-table-head">
                            <tr class="table-total-row">
                                <th>Total</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="total-data">
                                <td><strong>Subtotal: </strong></td>
                                <td>${cart.subtotal}</td>
                            </tr>
                            <tr class="total-data">
                                <td><strong>Shipping: </strong></td>
                                <td>${cart.shipping}</td>
                            </tr>
                            <tr class="total-data">
                                <td><strong>Total: </strong></td>
                                <td>${cart.total}</td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="cart-buttons">
                        <a href="${ctx}/Cart" class="boxed-btn">Update Cart</a>
                        <a href="${ctx}/Checkout" class="boxed-btn black">Check Out</a>
                    </div>
                </div>

                <div class="coupon-section">
                    <h3>Apply Coupon</h3>
                    <div class="coupon-form-wrap">
                        <form action="${ctx}/applyCoupon" method="POST">
                            <p><input type="text" name="couponCode" placeholder="Coupon"></p>
                            <p><input type="submit" value="Apply"></p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end cart -->

	<!-- logo carousel -->
	<div class="logo-carousel-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="logo-carousel-inner">
                                            <div class="single-logo-item">
                                                    <img src="${ctx}/assets/img/company-logos/1.png" alt="">
                                            </div>
                                            <div class="single-logo-item">
                                                    <img src="${ctx}/assets/img/company-logos/2.png" alt="">
                                            </div>
                                            <div class="single-logo-item">
                                                    <img src="${ctx}/assets/img/company-logos/3.png" alt="">
                                            </div>
                                            <div class="single-logo-item">
                                                    <img src="${ctx}/assets/img/company-logos/4.jpg" alt="">
                                            </div>
                                             <div class="single-logo-item">
                                                    <img src="${ctx}/assets/img/company-logos/5.jpg" alt="">
                                            </div>
                                            <div class="single-logo-item">
                                                    <img src="${ctx}/assets/img/company-logos/6.jpg" alt="">
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
							<li><a href="${ctx}/Shop">Shop</a></li>
							<li><a href="news.html">News</a></li>
							<li><a href="contact.html">Contact</a></li>
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
	<script src="<%= request.getContextPath() %>/assets/js/jquery-1.11.3.min.js"></script>
	<!-- bootstrap -->
	<script src="<%= request.getContextPath() %>/assets/bootstrap/js/bootstrap.min.js"></script>
        <!-- count down -->
	<script src="${ctx}/assets/js/jquery.countdown.js"></script>
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
