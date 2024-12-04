<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <style>
        /* Global Styles */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            color: #333;
        }
        .star {
            color: #f0ad4e !important; /* Màu vàng cho sao đầy */
            font-size: 1.5rem;
        }

        .empty-star {
            color: #ddd !important; /* Màu xám cho sao rỗng */
            font-size: 1.5rem;
        }


        .container {
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .product-layout {
            display: grid;
            grid-template-columns: 1fr 2fr;
            gap: 20px;
            align-items: start;
        }

        .product-image {
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .product-image img {
            width: 100%;
            height: auto;
            display: block;
        }

        .product-details {
            padding: 10px 20px;
        }

        .product-details h1 {
            font-size: 2rem;
            color: #444;
            margin-bottom: 15px;
        }

        .product-details p {
            font-size: 1rem;
            line-height: 1.5;
            margin-bottom: 10px;
        }

        .price {
            font-size: 1.5rem;
            color: #d9534f;
            font-weight: bold;
            margin: 20px 0;
        }

        .reviews-section {
            margin-top: 30px;
            padding: 10px 0;
            border-top: 2px solid #eee;
        }

        .reviews-section h2 {
            margin-bottom: 15px;
            font-size: 1.5rem;
            color: #555;
        }

        .review {
            margin-bottom: 15px;
            padding: 15px;
            background: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .review p {
            margin: 5px 0;
        }

        .form-section {
            margin-top: 30px;
            padding: 20px;
            background: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
        }

        .form-section label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1rem;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        .info-message {
            text-align: center;
            color: #777;
            font-style: italic;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .product-layout {
                grid-template-columns: 1fr;
            }

            .product-details {
                padding: 10px 0;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="product-layout">
            <!-- Cột trái: Ảnh sản phẩm -->
            <div class="product-image">
                <img src="${product.imageURL}" alt="${product.name}">
            </div>

            <!-- Cột phải: Thông tin sản phẩm -->
            <div class="product-details">
                <h1>${product.name}</h1>
                <p>${product.description}</p>
                <p class="price">Price: ${product.price} $</p>

                <div class="reviews-section">
                    <h2>Customer Reviews</h2>
                    <c:forEach var="review" items="${reviews}">
                        <div class="review">
                            <p><strong>Rating:</strong>
                                <!-- Sao đầy (vàng) -->
                                <c:forEach var="i" begin="1" end="${review.rating}">
                                    <span class="star">&#9733;</span> <!-- Biểu tượng sao đầy (★) -->
                                </c:forEach>
                                <!-- Sao rỗng (xám) -->
                                <c:forEach var="i" begin="${review.rating + 1}" end="5">
                                    <span class="empty-star">&#9734;</span> <!-- Biểu tượng sao rỗng (☆) -->
                                </c:forEach>
                            </p>
                            <p><strong>Comment:</strong> ${review.comment}</p>
                            <p><strong>Reviewed by:</strong> ${review.customer.name}</p>
                            <p><strong>Date:</strong> ${review.reviewDate}</p>
                        </div>
                    </c:forEach>


                <c:if test="${hasPurchased}">
                    <div class="form-section">
                        <h3>Add a Review</h3>
                        <form action="addReview" method="post">
                            <input type="hidden" name="productId" value="${product.id}">
                            <label for="rating">Rating:</label>
                            <input type="number" name="rating" min="1" max="5" required>
                            <label for="comment">Comment:</label>
                            <textarea name="comment" rows="4" required></textarea>
                            <button type="submit">Submit Review</button>
                        </form>
                    </div>
                </c:if>

                <c:if test="${!hasPurchased}">
                    <p class="info-message">You need to purchase this product before you can leave a review.</p>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html
