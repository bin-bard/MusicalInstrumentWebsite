<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page import="java.util.Map" %>
<%@ page import="com.instrumentwebsite.musicalinstruments.model.CartItem" %>
<%@ page import="com.instrumentwebsite.musicalinstruments.model.Product" %>

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

<!-- breadcrumb-section -->
<div class="breadcrumb-section breadcrumb-bg">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 text-center">
				<div class="breadcrumb-text">
					<h1>Cart</h1>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- end breadcrumb section -->

<!-- cart -->
<div class="cart-section mt-150 mb-150">
	<div class="container">
		<%@ page import="java.util.Map" %>
		<%@ page import="com.instrumentwebsite.musicalinstruments.model.CartItem" %>
		<%@ page import="com.instrumentwebsite.musicalinstruments.model.Product" %>
		<%@ page import="java.util.HashMap" %>
		<%
			Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
			if (cart == null) {
				cart = new HashMap<>();
			}
		%>
		<div class="row">
			<div class="col-lg-8 col-md-12">
				<div class="cart-table-wrap">
					<form action="${ctx}/UpdateCart" method="POST">
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

			<c:forEach var="cartItem" items="${cart.values()}">
				<c:set var="product" value="${cartItem.product}" />
				<tr class="table-body-row">
					<td class="product-remove">
						<a href="${ctx}/removeFromCart?productId=${product.id}">
							<i class="far fa-window-close"></i>
						</a>
					</td>

					<td class="product-image">
						<img src="${product.imageURL}" alt="${product.name}">
					</td>

					<td class="product-name">${product.name}</td>

					<td class="product-price">${product.price}</td>

					<td class="product-quantity">
						<input type="number" name="quantity_${product.id}" value="${cartItem.quantity}" min="1" max="99" />
					</td>

					<td class="product-total">
							${product.price.multiply(cartItem.quantity)}
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
						<div class="cart-buttons">
							<input type="submit" class="boxed-btn" value="Update Cart" />
						</div>
					</form>
				</div>
			</div>

			<div class="col-lg-4">
				<div class="cart__discount">
					<h6 style="cursor: pointer;" data-bs-toggle="modal" data-bs-target="#form2Modal">Discount codes</h6>
					<form onsubmit="return false;">
						<input type="text" id="couponCode" placeholder="Coupon code" required>
						<button type="button" class="btn btn-primary"
								onclick="sendCouponCode()">
							Áp Dụng
						</button>
					</form>
				</div>


				<div id="couponContent"></div>


				<div id="discountContent" style="display: block;">
					<div class="row text-info d-flex align-items-center">
						<div class="col-1">
							<i class="fa-solid fa-check"></i>
						</div>
						<div class="col-2 text-info">
							<input type="text" id="title" style="border: none; color:#17a2b8 ;" data-bs-toggle="modal"
								   data-bs-target="#form2Modal" readonly>
						</div>
					</div>

					<div class="row">
						<div class="col-1 text-info">
							<i class="fa-solid fa-arrow-right"></i>
						</div>
						<div class="col-5">
							<input type="text" id="descriptionCoupon"
								   style="border: none; background: transparent; pointer-events: none; font-weight: bold"
								   readonly>
						</div>
						<div class="col-1 text-info fw-bold">
							<input type="text" id="percentCoupon"
								   style="border: none; background: transparent; pointer-events: none; font-weight: bold; color: #17a2b8;"
								   readonly>
						</div>
					</div>
					<div class="row text-info d-flex align-items-center fw-bold" style="border: none; background: transparent; pointer-events: none; font-weight: bold">
						<div class = "col-8">
							<h6>• Áp dụng với đơn hàng trên: </h6>
						</div>
						<div class = "col-2 mb-1 ">
							<input type="text" id="minimumInvoiceAmount" style="border: none; color:#17a2b8 ;" readonly>
						</div>
						<div class = "col-2"></div>
					</div>
					<div class="row text-black d-flex align-items-center fw-bold" style="border: none; background: transparent; pointer-events: none; font-weight: bold">
						<div class="col-8">
							<h6>• Số tiền giảm tối đa: </h6>
						</div>
						<div class = "col-2 mb-1">
							<input type="text" id="maximumAmount" style="border: none; color:#17a2b8 ;" readonly>
						</div>
						<div class = "col-2"></div>
					</div>
					<div class="row text-info d-flex align-items-center mb-5">
						<div class="col-1">
							<i class="fas fa-ticket"></i>
						</div>
						<div class="col-2 text-info">
							<input type="text" id="title1" style="border: none; color:#17a2b8 ; cursor: pointer;"
								   data-bs-toggle="modal" data-bs-target="#form2Modal" readonly>
						</div>
					</div>
				</div>
				<div class="modal fade" id="form2Modal" tabindex="-1" aria-labelledby="form2ModalLabel"
					 aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content shadow">
							<div class="modal-header">
								<h4 class="modal-title" id="form2ModalLabel">Chọn mã giảm giá</h4>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
							</div>
							<div class="modal-body" style="max-height: 400px; overflow-y: auto;">
								<c:if test="${not empty discountList}">
									<c:forEach items="${discountList}" var="o">
										<div class="card mb-3">
											<div class="card-body">
												<div class="row ps-1">
													<h5 class="fw-bold">${searchDiscount.name}</h5>
												</div>
												<div class="row">
													<div class="col-2">
														<div class="fs-3 text-dark pb-2">
															<i class="fas fa-ticket"></i>
														</div>
													</div>
													<div class="col-10">
														<strong>${o.code}</strong><br>
														<p class="text-secondary-emphasis">Hạn sử dụng: ${o.endDate}</p>
													</div>
												</div>
												<div class="row">
													<h6>• Giảm ${o.discountPercentage}%</h6>
												</div>
												<div id="extraContent${o.code}" class="collapse"
													 data-minimum-invoice-amount="${o.minimumInvoiceAmount}"
													 data-maximum-amount="${o.maximumAmount}">
													<h6>• Áp dụng với đơn hàng trên: ${o.minimumInvoiceAmount} VND.</h6>
													<h6>• Số tiền giảm tối đa: ${o.maximumAmount} VND.</h6>
												</div>


												<div class="row">
													<div class="col-8">
														<!-- Sử dụng data-bs-target với id riêng biệt -->
														<button type="button" class="btn btn-link p-0 text-decoration-none"
																data-bs-toggle="collapse"
																data-bs-target="#extraContent${o.code}" aria-expanded="false"
																aria-controls="extraContent${o.code}"
																onclick="toggleButtonText(this)">Xem chi tiết ⬎</button>
													</div>
													<div class="col-4">
														<button type="button" class="btn btn-dark w-100"
																data-bs-dismiss="modal" onclick="applyCoupon(this)"
																data-code="${o.code}" data-description="${o.name}"
																data-minInvoiceAmount = "${o.minimumInvoiceAmount}"
																data-maxAmount = "${o.maximumAmount}"
																data-percentCoupon="${o.discountPercentage}">Áp dụng</button>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</div>
				</div>

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
							<td><strong>Total: </strong></td>
							<td>$ ${total}</td>
						</tr>
						</tbody>
					</table>


						<div class="cart-buttons">
						<form action="${ctx}/Checkout" method="GET">
							<input type="submit" class="boxed-btn black" value="Check Out" />
						</form>
					</div>

					<div class="cart-buttons" >
						<a href="${ctx}/shop" class="boxed-btn black">Continue Shopping</a>
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
e				<h2 class="widget-title">Subscribe</h2>
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
