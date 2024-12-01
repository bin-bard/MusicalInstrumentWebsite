<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="menu-admin.jsp" />


<div class="page-wrapper">
    <div class="content">
        <div class="page-header">
            <div class="page-title">
                <h4>Product Add</h4>
                <h6>Create new product</h6>
            </div>
        </div>

        <div class="card">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/admin-addproduct" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <!-- Product Name -->
                        <div class="col-lg-6 col-sm-12">
                            <div class="form-group">
                                <label>Product Name</label>
                                <input type="text" name="name" class="form-control" required>
                            </div>
                        </div>
                        <!-- Category -->
                        <div class="col-lg-6 col-sm-12">
                            <div class="form-group">
                                <label>Category</label>
                                <select name="categoryId" class="form-control" required>
                                    <option value="">Choose Category</option>
                                    <%-- Populate categories dynamically --%>
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <!-- Price -->
                        <div class="col-lg-6 col-sm-12">
                            <div class="form-group">
                                <label>Price</label>
                                <input type="number" name="price" class="form-control" step="0.01" required>
                            </div>
                        </div>
                        <!-- Image -->
                        <div class="col-lg-6 col-sm-12">
                            <div class="form-group">
                                <label>Product Image</label>
                                <input type="file" name="image" class="form-control-file">
                            </div>
                        </div>
                        <!-- Description -->
                        <div class="col-lg-12">
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" class="form-control" rows="4"></textarea>
                            </div>
                        </div>
                        <!-- Submit Button -->
                        <div class="col-lg-12">
                            <button type="submit" class="btn btn-primary">Add Product</button>
                            <a href="${pageContext.request.contextPath}/admin" class="btn btn-cancel">Cancel</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="assets/js/jquery-3.6.0.min.js"></script>

<script src="assets/js/feather.min.js"></script>

<script src="assets/js/jquery.slimscroll.min.js"></script>

<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/dataTables.bootstrap4.min.js"></script>

<script src="assets/js/bootstrap.bundle.min.js"></script>

<script src="assets/plugins/select2/js/select2.min.js"></script>

<script src="assets/plugins/sweetalert/sweetalert2.all.min.js"></script>
<script src="assets/plugins/sweetalert/sweetalerts.min.js"></script>

<script src="assets/js/script.js"></script>
</body>
</html>