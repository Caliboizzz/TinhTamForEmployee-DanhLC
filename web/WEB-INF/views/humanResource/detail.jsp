<%-- 
    Document   : detail
    Created on : Jun 17, 2023, 8:54:57 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-sm-12" style="font-size: 30px;">
        <a href="<c:url value="/humanResource/${actionPre}.do"/>"> <i class="bi bi-arrow-left"></i> Trở về</a>
        <h2>Thông tin nhân viên</h2>
    </div>
</div>
<div class="col-sm-6" style="width: 100%">
    <form action="<c:url value="/humanResource/updateHandler.do" />" class="needs-validation" novalidate>  
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="firstName" class="form-label">Tên:</label>
                <input type="text" class="form-control" readonly id="firstName" name="firstName" required value="${employee.firstName}" placeholder="Nhập tên của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập tên của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="lastName" class="form-label">Họ và tên lót:</label>
                <input type="text" class="form-control" readonly id="lastName" name="lastName" required value="${employee.lastName}" placeholder="Nhập họ và tên lót của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập họ và tên lót của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="salary" class="form-label">Lương:</label>
                <input type="text" class="form-control" readonly id="salary" name="salary" required value="<fmt:formatNumber value="${employee.salary}" type="currency" currencyCode="VND" pattern="#,##0 ¤"/>" placeholder="Nhập lương của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập lương của nhân viên</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="birthday" class="form-label">Ngày sinh:</label>
                <input type="date" class="form-control" readonly id="birthday" name="birthday" required value="${employee.birthday}" placeholder="Nhập ngày sinh của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập ngày sinh của nhân viên</div>
            </div>
            <div class=" col mb-3 mt-3">
                <label for="address" class="form-label">Địa chỉ:</label>
                <input type="text" class="form-control" readonly id="address" name="address" required value="${employee.address}" placeholder="Nhập địa chỉ của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập địa chỉ của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="phone" class="form-label">Số điện thoại:</label>
                <input type="text" class="form-control" readonly id="phone" name="phone" value="${employee.phone}" required pattern="^(03|05|07|08|09)+([0-9]{8})$" placeholder="Nhập số điện thoại của nhân viên" maxlength="10">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập số điện thoại hợp lệ</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" readonly id="email" name="email" value="${employee.email}" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" placeholder="Nhập email của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập email hợp lệ</div>
                <div style="color:red;">${message}</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="sex" class="form-label">Giới tính:</label>
                <input id="sex" readonly name="sex" class="form-control" value="${employee.sex.equals("M") ? 'Nam' : 'Nữ'}">
            </div>
            <div class="col mb-3 mt-3">
                <label for="department" class="form-label">Phòng ban:</label>
                <select id="department" disabled name="department" class="form-control">
                    <c:forEach var="Department" items="${listDepartment}">
                        <option value="${Department.id}" ${Department.id == employee.departmentID ? 'selected' : ''}>${Department.name}</option>
                    </c:forEach>
                </select>

            </div>
        </div>

    </form>
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

</script>
