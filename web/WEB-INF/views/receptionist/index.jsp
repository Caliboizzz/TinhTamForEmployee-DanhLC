<%-- 
    Document   : index
    Created on : Mar 31, 2023, 1:06:09 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<div class="row" style="margin-top: 30px;">

    <div class="col">
        <form action="<c:url value="/receptionist/index.do" />"> 
            <div class="input-group mb-3">
                <input type="text" maxlength="10" class="form-control" id="searchKey" name="searchKey" value="${searchKey}" placeholder="Nhập số điện thoại để tìm kiếm...">

                <button class="btn btn-outline-light" type="submit"  name="op" value="search" style="border-color: gray; color: gray;">
                    <i class="bi bi-search"></i>
                </button>
                <button class="btn btn-outline-danger" type="submit"  name="op" value="clear">
                    <i class="bi bi-x-lg"></i>
                </button>

            </div>
        </form>
        <table class="table table-striped">
            <tr>
                <th style="text-align: right;">STT</th>

                <th style="text-align: right;">Mã</th>
                <th>Họ và tên lót </th>
                <th>Tên </th>
                <th>Giới tính</th>
                <th>Số điện thoại</th>
                <th>Ngày sinh</th>
                <th></th>

            </tr>
            <c:forEach var="patient" items="${list}" varStatus="loop">
                <tr>
                    <td style="text-align: right;">${loop.count}</td>
                    <td style="text-align: center;">${patient.id}</td>
                    <td>${patient.lastName}</td>
                    <td>${patient.firstName}</td>
                    <td>${patient.sex}</td>
                    <td>${patient.phone}</td>
                    <td>
                        <fmt:formatDate value="${patient.birthday}" pattern="MM-dd-yyyy" />
                    </td>
                    <td>

                        <a href="<c:url value="/receptionist/details.do?patientId=${patient.id}" />" title="Xem chi tiết" class="btn btn-secondary" style="width: 50px;" >
                            <i class="bi bi-info-circle-fill"></i>
                        </a>  
                        <a href="<c:url value="/receptionist/update.do?patientId=${patient.id}" />" title="Chỉnh sửa hồ sơ" class="btn btn-secondary" style="width: 50px;" >
                            <i class="bi bi-pencil-fill"></i>
                        </a>
                        <a href="<c:url value="/receptionist/transfer.do?patientId=${patient.id}" />" title="Tạo phiếu khám" class="btn btn-secondary" style="width: 50px;" >
                            <i class="bi bi-clipboard2-pulse-fill"></i>
                        </a>  

                    </td>


                </tr>
            </c:forEach>
        </table>
        <jsp:include page="navigation.jsp" />
    </div>
    <div class="col">
        <h2>Đăng ký bệnh nhân mới</h2>
        <form action="<c:url value="/receptionist/registerHandler.do" />" class="needs-validation" novalidate>   
            <div class="mb-3 mt-3">
                <label for="firstName" class="form-label">Tên:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="" required placeholder="Nhập tên của bệnh nhân">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập tên của của bệnh nhân</div>
            </div>
            <div class="mb-3 mt-3">
                <label for="lastName" class="form-label">Họ và tên lót:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required value="" placeholder="Nhập họ và tên lót của bệnh nhân">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập họ và tên lót của bệnh nhân</div>
            </div>
            <div class="mb-3 mt-3">
                <label for="birthday" class="form-label">Ngày sinh:</label>
                <input type="date" class="form-control" id="birthday" name="birthday" required value="" placeholder="Nhập ngày sinh của bệnh nhân">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập ngày sinh của bệnh nhân</div>
            </div>
            <div class="mb-3 mt-3">
                <label for="address" class="form-label">Địa chỉ:</label>
                <input type="text" class="form-control" id="address" name="address" required value="" placeholder="Nhập địa chỉ của bệnh nhân">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập địa chỉ của bệnh nhân</div>
            </div>
            <div class="mb-3 mt-3">
                <label for="phone" class="form-label">Số điện thoại:</label>
                <input type="text" class="form-control" id="phone" name="phone" value="" required pattern="^(03|05|07|08|09)+([0-9]{8})$"  placeholder="Nhập số điện thoại của bệnh nhân" maxlength="10">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập số điện thoại hợp lệ của bệnh nhân</div>
            </div>

            <div class="mb-3 mt-3">
                <label for="healthInsurance" class="form-label">Số bảo hiểm y tế:</label>
                <input type="text" class="form-control" id="healthInsurance" name="healthInsurance" value="" pattern="^(03|05|07|08|09)+([0-9]{8})$" placeholder="Nhập số bảo hiểm y tế của bệnh nhân" maxlength="10">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập số bảo hiểm y tế hợp lệ của bệnh nhân</div>
            </div>
            <div class="mb-3 mt-3">
                <label for="sex" class="form-label">Giới tính:</label>
                <select id="sex" name="sex" class="form-control">
                    <option value="M">Nam</option>
                    <option value="F">Nữ</option>
                </select>
            </div>
            <div class="btn-register" style="text-align: center;">
                <button type="submit"  name="op" value="register">Tạo phiếu khám</button>
            </div>
        </form>
    </div>
</div>
<script>
// Hàm xử lý khi submit form
    function validateForm() {
        var form = document.getElementsByClassName('needs-validation')[0];



        if (form.checkValidity() === false) {
            event.preventDefault(); // Ngăn không cho form submit
            event.stopPropagation();
        }
        form.classList.add('was-validated');
    }

// Lắng nghe sự kiện khi submit form
    var form = document.getElementsByClassName('needs-validation')[0];
    form.addEventListener('submit', validateForm, false);
</script>
