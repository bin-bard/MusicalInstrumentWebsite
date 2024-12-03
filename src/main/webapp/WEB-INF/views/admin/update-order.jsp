<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:include page="menu-admin.jsp" />

<%-- Initialize grandTotal --%>
<c:set var="grandTotal" value="0" />

<div class="page-wrapper">
  <div class="content">
    <div class="page-header">
      <div class="page-title">
        <h4>Order Details</h4>
        <h6>View order details</h6>
      </div>
    </div>
    <div class="card">
      <div class="card-body">
        <div class="card-sales-split">
          <h2>Sale Detail : SL0101</h2>
          <ul>
            <li>
              <a href="javascript:void(0);"><img src="assets/img/icons/edit.svg" alt="img"></a>
            </li>
            <li>
              <a href="javascript:void(0);"><img src="assets/img/icons/pdf.svg" alt="img"></a>
            </li>
            <li>
              <a href="javascript:void(0);"><img src="assets/img/icons/excel.svg" alt="img"></a>
            </li>
            <li>
              <a href="javascript:void(0);"><img src="assets/img/icons/printer.svg" alt="img"></a>
            </li>
          </ul>
        </div>

        <form action="${pageContext.request.contextPath}/admin-updateorder" method="post">
          <input type="hidden" name="orderId" value="${order.id}" />

          <div class="invoice-box table-height" style="max-width: 1600px;width:100%;overflow: auto;margin:15px auto;padding: 0;font-size: 14px;line-height: 24px;color: #555;">
            <table cellpadding="0" cellspacing="0" style="width: 100%;line-height: inherit;text-align: left;">
              <tbody>
              <tr class="top">
                <td colspan="6" style="padding: 5px;vertical-align: top;">
                  <table style="width: 100%;line-height: inherit;text-align: left;">
                    <tbody>
                    <tr>
                      <td style="padding:5px;vertical-align:top;text-align:left;padding-bottom:20px">
                        <font style="vertical-align: inherit;margin-bottom:25px;">
                          <font style="vertical-align: inherit;font-size:14px;color:#7367F0;font-weight:600;line-height: 35px;">Customer Info</font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#000;font-weight: 400;">
                            ${order.customer.firstName} ${order.customer.lastName}
                          </font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#000;font-weight: 400;">
                            <a href="mailto:${order.customer.email}" class="__cf_email__">${order.customer.email}</a>
                          </font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#000;font-weight: 400;">
                            <c:forEach var="address" items="${order.customer.addresses}">
                              ${address.street}, ${address.city}, ${address.state}, ${address.zipCode}, ${address.country}<br/>
                            </c:forEach>
                          </font>
                        </font><br>
                      </td>

                      <td style="padding:5px;vertical-align:top;text-align:left;padding-bottom:20px">
                        <font style="vertical-align: inherit;margin-bottom:25px;">
                          <font style="vertical-align: inherit;font-size:14px;color:#7367F0;font-weight:600;line-height: 35px;">Invoice Info</font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#000;font-weight: 400;">Reference</font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#000;font-weight: 400;">Payment Method</font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#000;font-weight: 400;">Status</font>
                        </font><br>
                      </td>

                      <td style="padding:5px;vertical-align:top;text-align:right;padding-bottom:20px">
                        <font style="vertical-align: inherit;margin-bottom:25px;">
                          <font style="vertical-align: inherit;font-size:14px;color:#7367F0;font-weight:600;line-height: 35px;">&nbsp;</font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#000;font-weight: 400;">${order.id}</font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#2E7D32;font-weight: 400;">${order.paymentMethod}</font>
                        </font><br>
                        <font style="vertical-align: inherit;">
                          <font style="vertical-align: inherit;font-size: 14px;color:#2E7D32;font-weight: 400;">${order.status}</font>
                        </font><br>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </td>
              </tr>

              <tr class="heading" style="background: #F3F2F7;">
                <td style="padding: 5px;vertical-align: middle;font-weight: 600;color: #5E5873;font-size: 14px;padding: 10px;">
                  Product Name
                </td>
                <td style="padding: 5px;vertical-align: middle;font-weight: 600;color: #5E5873;font-size: 14px;padding: 10px;">
                  QTY
                </td>
                <td style="padding: 5px;vertical-align: middle;font-weight: 600;color: #5E5873;font-size: 14px;padding: 10px;">
                  Price
                </td>
                <td style="padding: 5px;vertical-align: middle;font-weight: 600;color: #5E5873;font-size: 14px;padding: 10px;">
                  Subtotal
                </td>
                <td style="padding: 5px;vertical-align: middle;font-weight: 600;color: #5E5873;font-size: 14px;padding: 10px;">
                </td>
              </tr>

              <c:forEach var="item" items="${order.orderItems}">
                <tr class="details" style="border-bottom:1px solid #E9ECEF;">
                  <td>
                    <img src="${item.product.imageURL}" alt="img" class="me-2" style="width:40px;height:40px;">
                      ${item.product.name}
                    <input type="hidden" name="orderItemId" value="${item.id}" />
                  </td>
                  <td>
                    <input type="number" name="quantities" value="${item.quantity}" class="quantity-input" min="1">
                  </td>
                  <td>
                    $ ${item.unitPrice}
                  </td>
                  <td>
                    <c:set var="subtotal" value="${item.quantity * item.unitPrice}" />
                    <c:set var="grandTotal" value="${grandTotal + subtotal}" />
                    <span class="subtotal">$ ${subtotal}</span>
                  </td>
                  <td>
                    <a class="confirm-deleteproductinorder" href="${pageContext.request.contextPath}/admin-deleteproductinorder?orderId=${order.id}&productId=${item.product.id}">
                      <img src="assets/img/icons/delete.svg" alt="img">
                    </a>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
            </table>

            <div class="col-lg-3 col-sm-6 col-12">
              <div class="form-group">
                <label>Status</label>
                <select name="status" class="select" required>
                  <option value="SHIPPED" <c:if test="${order.status == 'SHIPPED'}">selected</c:if>>SHIPPED</option>
                  <option value="DELIVERED" <c:if test="${order.status == 'DELIVERED'}">selected</c:if>>DELIVERED</option>
                  <option value="CANCELED" <c:if test="${order.status == 'CANCELED'}">selected</c:if>>CANCELED</option>
                  <option value="PENDING" <c:if test="${order.status == 'PENDING'}">selected</c:if>>PENDING</option>
                  <option value="PROCESSING" <c:if test="${order.status == 'PROCESSING'}">selected</c:if>>PROCESSING</option>
                </select>
              </div>
            </div>

            <div class="row">
              <div class="col-lg-6 ">
                <div class="total-order w-100 max-widthauto m-auto mb-4">
                  <ul>
                    <li class="total">
                      <h4>Grand Total</h4>
                      <h5>$ ${grandTotal}</h5>
                    </li>
                  </ul>
                </div>
              </div>
            </div>

            <div class="col-lg-12">
              <button type="submit" class="btn btn-submit me-2">Update</button>
              <a href="${pageContext.request.contextPath}/admin-orderlist" class="btn btn-cancel">Cancel</a>
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

<script>
  document.addEventListener('DOMContentLoaded', function() {
    var elements = document.querySelectorAll('a.confirm-deleteproductinorder');
    elements.forEach(function(element) {
      element.addEventListener('click', function(event) {
        event.preventDefault(); // Prevent default action
        var href = this.getAttribute('href');
        Swal.fire({
          title: 'Bạn có chắc chắn muốn xóa sản phẩm này không?',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#3085d6',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Có, xóa nó!',
          cancelButtonText: 'Hủy'
        }).then((result) => {
          if (result.isConfirmed) {
            window.location.href = href;
          }
        });
      });
    });
  });
</script>

</div>