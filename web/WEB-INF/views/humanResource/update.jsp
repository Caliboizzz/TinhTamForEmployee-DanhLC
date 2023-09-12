<%-- 
    Document   : update
    Created on : Jun 9, 2023, 10:42:20 PM
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
            <input type="hidden" name="actionPre" id="actionPre" value="${param.actionPre}">
            <input type="hidden" name="empId" id="empId" value="${param.empId}">
            <input type="hidden" name="password" id="password" value="${employee.password}">
            <div class="col mb-3 mt-3">
                <label for="firstName" class="form-label">Tên:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" required value="${employee.firstName}" placeholder="Nhập tên của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập tên của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="lastName" class="form-label">Họ và tên lót:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required value="${employee.lastName}" placeholder="Nhập họ và tên lót của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập họ và tên lót của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="salary" class="form-label">Lương:</label>
                <input type="number" class="form-control" id="salary" name="salary" required value="${employee.salary}" placeholder="Nhập lương của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập lương của nhân viên</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="birthday" class="form-label">Ngày sinh:</label>
                <input type="date" class="form-control" id="birthday" name="birthday" required value="${employee.birthday}" placeholder="Nhập ngày sinh của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập ngày sinh của nhân viên</div>
            </div>
            <div class=" col mb-3 mt-3">
                <label for="address" class="form-label">Địa chỉ:</label>
                <input type="text" class="form-control" id="address" name="address" required value="${employee.address}" placeholder="Nhập địa chỉ của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập địa chỉ của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="phone" class="form-label">Số điện thoại:</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${employee.phone}" required pattern="^(03|05|07|08|09)+([0-9]{8})$" placeholder="Nhập số điện thoại của nhân viên" maxlength="10">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập số điện thoại hợp lệ</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="${employee.email}" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" placeholder="Nhập email của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập email hợp lệ</div>
                <div style="color:red;">${message}</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="sex" class="form-label">Giới tính:</label>
                <select id="sex" name="sex" class="form-control">
                    <option value="M" ${employee.sex.equals("M") ? 'selected' : ''}>Nam</option>
                    <option value="F" ${employee.sex.equals("F") ? 'selected' : ''}>Nữ</option>
                </select>
            </div>
            <div class="col mb-3 mt-3">
                <label for="department" class="form-label">Phòng ban:</label>
                <select id="department" name="department" class="form-control">
                    <c:forEach var="Department" items="${listDepartment}">
                        <option value="${Department.id}" ${Department.id == employee.departmentID ? 'selected' : ''}>${Department.name}</option>
                    </c:forEach>
                </select>

            </div>
        </div>

        <div class="btn-register" style="text-align: center;">
            <button type="submit" name="op" value="update">Cập nhật</button>
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
