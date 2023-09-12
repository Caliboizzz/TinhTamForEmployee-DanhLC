<%-- 
    Document   : update
    Created on : Jun 17, 2023, 3:33:55 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col-sm-12" style="font-size: 30px;">
        <a href="<c:url value="/receptionist/index.do"/>"> <i class="bi bi-arrow-left"></i> Trở về</a>
        <h2>Thông tin bệnh nhân</h2>
    </div>
</div>
<div class="col-sm-6" style="width: 100%">
    <form action="<c:url value="/receptionist/updateHandler.do" />" class="needs-validation" novalidate>  
        <div class="row">

            <input type="hidden" name="patientId" id="patientId" value="${param.patientId}">
            <div class="col mb-3 mt-3">
                <label for="firstName" class="form-label">Tên:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" required value="${patient.firstName}" placeholder="Nhập tên của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập tên của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="lastName" class="form-label">Họ và tên lót:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required value="${patient.lastName}" placeholder="Nhập họ và tên lót của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập họ và tên lót của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="healthInsurance" class="form-label">Số bảo hiểm y tế:</label>
                <input type="text" class="form-control" id="healthInsurance" name="healthInsurance" value="${patient.healthInsurance}" pattern="^(03|05|07|08|09)+([0-9]{8})$" placeholder="Nhập số bảo hiểm y tế của bệnh nhân" maxlength="10">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập số bảo hiểm y tế hợp lệ của bệnh nhân</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="birthday" class="form-label">Ngày sinh:</label>
                <input type="date" class="form-control" id="birthday" name="birthday" required value="${patient.birthday}" placeholder="Nhập ngày sinh của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập ngày sinh của nhân viên</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="sex" class="form-label">Giới tính:</label>
                <select id="sex" name="sex" class="form-control">
                    <option value="M" ${patient.sex.equals("M") ? 'selected' : ''}>Nam</option>
                    <option value="F" ${patient.sex.equals("F") ? 'selected' : ''}>Nữ</option>
                </select>
            </div>
            <div class="col mb-3 mt-3">
                <label for="phone" class="form-label">Số điện thoại:</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${patient.phone}" required pattern="^(03|05|07|08|09)+([0-9]{8})$" placeholder="Nhập số điện thoại của nhân viên" maxlength="10">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập số điện thoại hợp lệ</div>
            </div>
        </div>
        <div class="row">
            
            <div class=" col mb-3 mt-3">
                <label for="address" class="form-label">Địa chỉ:</label>
                <input type="text" class="form-control" id="address" name="address" required value="${patient.address}" placeholder="Nhập địa chỉ của nhân viên">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập địa chỉ của nhân viên</div>
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

