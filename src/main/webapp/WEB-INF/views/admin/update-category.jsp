<jsp:include page="menu-admin.jsp" />
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="page-wrapper">
  <div class="content">
    <div class="page-header">
      <div class="page-title">
        <h4>Chỉnh sửa danh mục</h4>
        <h6>Cập nhật thông tin danh mục của bạn</h6>
      </div>
    </div>

    <div class="card">
      <div class="card-body">
        <form action="${pageContext.request.contextPath}/admin-updatecategory" method="post">
          <div class="row">
            <div class="col-lg-6 col-sm-6 col-12">
              <div class="form-group">
                <label>ID danh mục</label>
                <input type="text" name="id" class="form-control" value="${category.id}" readonly>
              </div>
            </div>
            <div class="col-lg-6 col-sm-6 col-12">
              <div class="form-group">
                <label>Tên danh mục</label>
                <input type="text" name="name" class="form-control" value="${category.name}" required>
              </div>
            </div>
            <div class="col-lg-12">
              <button type="submit" class="btn btn-submit me-2">Cập nhật</button>
              <a href="${pageContext.request.contextPath}/admin-categorylist" class="btn btn-cancel">Hủy</a>
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