<%-- 
    Document   : index
    Created on : Mar 31, 2023, 1:06:09 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="login">
        <form action="<c:url value="/account/login.do"/>">   
            <div class="login-header">
                <h1 style="font-size: 50px"> Chào mừng bạn </h1>
                <a href="<c:url value="/account/register.do" />" style="color: gray; text-decoration: none; font-size:25px; margin: auto;margin-top: 15px;">Đăng Ký <i class="bi bi-arrow-right"></i> </a> 
            </div>
            <div class="text-field">
                <input type="text" class="form-control" id="email" name="email" value="" placeholder="Email">
            </div>
            <div class="text-field">
                <input type="password" class="form-control" id="password" name="password" value="" placeholder="Mật khẩu" >
                <i class="bi bi-eye-slash" id="eyeClose" onclick="eyeClose()"></i>

            </div>
            <i style="color:red;">${message}</i>
            <div class="btn-login" style="text-align: center;">
                <button type="submit" name="op" value="login">Đăng nhập</button>
            </div> 
        </form>  
    </div>
    <div class="image d-none d-lg-block ">
        <img src="<c:url value="/images/bg-doctor.png" />"style="width: 100%;position: relative;"/>     
    </div>
</div>

 <script>
     const input=document.getElementById("password");
     function eyeClose(){
         if(input.type==="password"){
             input.type = "text";            
             document.getElementById("eyeClose").className = "bi bi-eye";
             
         }else{
             input.type = "password";       
             document.getElementById("eyeClose").className ="bi bi-eye-slash";             
         }
     }
</script>
