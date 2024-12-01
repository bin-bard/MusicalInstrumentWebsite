<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="menu-admin.jsp" />
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="page-wrapper">
  <div class="content">
    <div class="page-header">
      <div class="page-title">
        <h4>Chỉnh sửa sản phẩm</h4>
        <h6>Cập nhật thông tin sản phẩm của bạn</h6>
      </div>
    </div>

    <div class="card">
      <div class="card-body">
        <form action="${pageContext.request.contextPath}/admin-updateproduct" method="post" enctype="multipart/form-data">
          <input type="hidden" name="id" value="${product.id}">
          <div class="row">
            <!-- Tên sản phẩm -->
            <div class="col-lg-6 col-sm-12">
              <div class="form-group">
                <label>Tên sản phẩm</label>
                <input type="text" name="name" class="form-control" value="${product.name}" required>
              </div>
            </div>
            <!-- Danh mục -->
            <div class="col-lg-6 col-sm-12">
              <div class="form-group">
                <label>Danh mục</label>
                <select name="categoryId" class="form-control" required>
                  <option value="">Chọn danh mục</option>
                  <c:forEach var="category" items="${categories}">
                    <option value="${category.id}" ${category.id == product.category.id ? 'selected' : ''}>${category.name}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <!-- Giá -->
            <div class="col-lg-6 col-sm-12">
              <div class="form-group">
                <label>Giá</label>
                <input type="number" name="price" class="form-control" step="0.01" value="${product.price}" required>
              </div>
            </div>
            <!-- Hình ảnh -->
            <div class="col-lg-6 col-sm-12">
              <div class="form-group">
                <label>Hình ảnh sản phẩm</label>
                <input type="file" name="image" class="form-control-file">
                <br/>
                <c:if test="${not empty product.imageURL}">
                  <img src="${pageContext.request.contextPath}/${product.imageURL}" alt="Hình ảnh sản phẩm" style="max-width: 150px;">
                </c:if>
              </div>
            </div>
            <!-- Mô tả -->
            <div class="col-lg-12">
              <div class="form-group">
                <label>Mô tả</label>
                <textarea name="description" class="form-control" rows="4">${product.description}</textarea>
              </div>
            </div>
            <!-- Nút cập nhật -->
            <div class="col-lg-12">
              <button type="submit" class="btn btn-primary">Cập nhật sản phẩm</button>
              <a href="${pageContext.request.contextPath}/admin-product" class="btn btn-cancel">Hủy</a>
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