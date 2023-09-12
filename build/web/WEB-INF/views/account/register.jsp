<%-- 
    Document   : register
    Created on : Mar 31, 2023, 1:06:09 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row">
    <div class="col-sm-12" style="font-size: 30px;">
        <a href="<c:url value="/home/index.do" />"> <i class="bi bi-arrow-left"></i> Đăng nhập</a>
        <h2>Tạo tài khoản</h2>
    </div>
</div>

<div class="col-sm-6" style="width: 100%">
    <form action="<c:url value="/account/registerHandler.do" />" class="needs-validation" novalidate>  
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="firstName" class="form-label">Tên:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" required value="" placeholder="Nhập tên của bạn">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập tên của bạn</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="lastName" class="form-label">Họ và tên lót:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required value="" placeholder="Nhập họ và tên lót của bạn">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập họ và tên lót của bạn</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="birthday" class="form-label">Ngày sinh:</label>
                <input type="date" class="form-control" id="birthday" name="birthday" required value="" placeholder="Nhập ngày sinh của bạn">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập ngày sinh của bạn</div>
            </div>
            <div class=" col mb-3 mt-3">
                <label for="address" class="form-label">Địa chỉ:</label>
                <input type="text" class="form-control" id="address" name="address" required value="" placeholder="Nhập địa chỉ của bạn">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập địa chỉ của bạn</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="phone" class="form-label">Số điện thoại:</label>
                <input type="text" class="form-control" id="phone" name="phone" value="" required pattern="^(03|05|07|08|09)+([0-9]{8})$" placeholder="Nhập số điện thoại của bạn" maxlength="10">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập số điện thoại hợp lệ</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value="" required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" placeholder="Nhập email của bạn">
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập email hợp lệ</div>
                <div style="color:red;">${message}</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="password" class="form-label">Mật khẩu:</label>
                <input type="password" class="form-control" id="password" name="password" value="" required placeholder="Nhập mật khẩu">
                <i class="bi bi-eye-slash-fill" id="eyeClose" onclick="eyeClose()"></i>
                <div class="valid-feedback">Hợp lệ</div>
                <div class="invalid-feedback">Vui lòng nhập mật khẩu hợp lệ</div>
            </div>
            <div class="col mb-3 mt-3">
                <label for="confirmPassword" class="form-label">Xác nhận mật khẩu:</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" value="" placeholder="Nhập lại mật khẩu">
                <i class="bi bi-eye-slash-fill"id="eyeClose1" onclick="eyeClose1()"></i>
                <div class="invalid-feedback">Vui lòng xác nhận lại mật khẩu</div>
            </div>
        </div>
        <div class="row">
            <div class="col mb-3 mt-3">
                <label for="sex" class="form-label">Giới tính:</label>
                <select id="sex" name="sex" class="form-control">
                    <option value="M">Nam</option>
                    <option value="F">Nữ</option>
                </select>
            </div>
            <div class="col mb-3 mt-3">
                <label for="department" class="form-label">Phòng ban:</label>
                <select name="department" class="form-control">
                    <c:forEach var="Department" items="${listDepartment}">
                        <option value="${Department.id}">${Department.name}</option>
                    </c:forEach>
                </select>

            </div>
        </div>

        <div class="btn-register" style="text-align: center;">
            <button type="submit" name="op" value="register">Đăng Ký</button>
        </div>
    </form>
</div>

<script>
// Hàm xử lý khi submit form
    function validateForm() {
        var form = document.getElementsByClassName('needs-validation')[0];
        var password = document.getElementById('password').value;
        var confirmPassword = document.getElementById('confirmPassword');

        if (form.checkValidity() === false || password !== confirmPassword.value) {
            if(password !== confirmPassword.value){
               alert("Xác nhận mật khẩu không chính xác!"); 
            }
            event.preventDefault(); // Ngăn không cho form submit
            event.stopPropagation();
        }
        form.classList.add('was-validated');
    }

// Lắng nghe sự kiện khi submit form
    var form = document.getElementsByClassName('needs-validation')[0];
    form.addEventListener('submit', validateForm, false);
    const input = document.getElementById("password");
    function eyeClose() {
        if (input.type === "password") {
            input.type = "text";
            document.getElementById("eyeClose").className = "bi bi-eye-fill";

        } else {
            input.type = "password";
            document.getElementById("eyeClose").className = "bi bi-eye-slash-fill";
        }
    }
    const input1 = document.getElementById("confirmPassword");
    function eyeClose1() {
        if (input1.type === "password") {
            input1.type = "text";
            document.getElementById("eyeClose1").className = "bi bi-eye-fill";

        } else {
            input1.type = "password";
            document.getElementById("eyeClose1").className = "bi bi-eye-slash-fill";
        }
    }
</script>
