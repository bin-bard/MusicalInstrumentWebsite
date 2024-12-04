<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shop</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        /* Global Styles */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            color: #333;
        }

        h2 {
            text-align: center;
            margin-top: 20px;
            color: #333;
        }

        .filters {
            display: flex;
            justify-content: center;
            gap: 20px;
            padding: 20px;
            background-color: #fff;
            border-bottom: 1px solid #ddd;
        }

        .filters select, .filters input[type="text"] {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
            width: 200px;
        }

        .filters button {
            background-color: #27ae60;
            color: #fff;
            border: none;
            padding: 10px 15px;
            font-size: 1em;
            border-radius: 5px;
            cursor: pointer;
        }

        .filters button:hover {
            background-color: #219150;
        }

        .product-list {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
            gap: 20px;
            padding: 20px;
            margin: 0 auto;
            max-width: 1200px;
        }

        .product-item {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            height: 500px;
            padding: 15px;
        }

        .product-item img {
            width: 100%;
            height: 250px;
            object-fit: cover;
            border-radius: 8px;
        }

        .product-item h3 {
            font-size: 1.2em;
            color: #333;
            margin: 10px 0;
            height: 50px;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .product-item p {
            font-size: 1em;
            color: #555;
            padding: 0 15px;
            height: 50px;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .product-item .price {
            font-size: 1.4em;
            color: #27ae60;
            margin: 10px 0;
        }

        .product-item .btn-add-to-cart {
            background-color: #ff7f50;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 80%;
            margin: 10px auto;
        }

        .pagination {
            text-align: center;
            margin-top: 30px;
        }

        .pagination a {
            margin: 0 5px;
            padding: 8px 16px;
            text-decoration: none;
            color: #333;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #fff;
        }

        .pagination a:hover {
            background-color: #27ae60;
            color: white;
        }

        .footer {
            text-align: center;
            margin-top: 30px;
            padding: 20px;
            background-color: #333;
            color: #fff;
        }
    </style>
</head>
<body>

    <h2>Welcome to Our Store</h2>

    <!-- Filters and Search -->
    <div class="filters">
        <!-- Thêm b? l?c th??ng hi?u vào ph?n Filters -->
        <form action="shop" method="GET">
            <!-- Search Box -->
            <input type="text" name="search" placeholder="Search products..." value="${param.search}">
            <!-- Category Filter -->
            <select name="category">
                <option value="">All Categories</option>
                <c:forEach var="category" items="${categories}">
                    <option value="${category.id}" ${param.category == category.id ? 'selected' : ''}>
                        ${category.name}
                    </option>
                </c:forEach>
            </select>
            
            <!-- Price Range Filter -->
            <select name="priceRange">
                <option value="">All Prices</option>
                <option value="low" ${param.priceRange == 'low' ? 'selected' : ''}>$0 - $50</option>
                <option value="medium" ${param.priceRange == 'medium' ? 'selected' : ''}>$50 - $200</option>
                <option value="high" ${param.priceRange == 'high' ? 'selected' : ''}>$200+</option>
            </select>
            <!-- Search Button -->
            <button type="submit">Filter</button>
        </form>

    </div>

    <div class="product-list">
       <c:forEach var="product" items="${productList}">
            <div class="product-item">
                 <a href="product-detail?id=${product.id}">
                    <img src="${product.imageURL}" alt="${product.name}">
                </a>
                <h3>${product.name}</h3>
                <p>${product.description}</p>
                <p class="price">$${product.price}</p>

                <!-- Thêm form cho nút "Add to Cart" -->
                <form action="cart" method="POST">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="hidden" name="customerId" value="1"> <!-- Gi? s? customer.id ?ã có s?n trong session -->
                    <button type="submit" class="btn-add-to-cart">Add to Cart</button>
                </form>
            </div>
        </c:forEach>

    </div>

    <!-- Pagination -->
    <div class="pagination">
        <c:forEach var="page" begin="1" end="${totalPages}">
            <a href="shop?page=${page}">${page}</a>
        </c:forEach>
    </div>

    <div class="footer">
        <p>&copy; 2024 Our Store - All rights reserved</p>
    </div>

</body>
</html>
