<%-- 
    Document   : index
    Created on : Jun 7, 2023, 5:36:33 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <ul class="nav nav-tabs justify-content-end" style="margin-bottom: 5px;">

        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/humanResource/doctorManagement.do"/>">Quản lý bác sĩ</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/humanResource/employeeManagement.do"/>">Quản lý nhân viên</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="<c:url value="/humanResource/index.do"/>" >Kích hoạt tài khoản</a>
        </li>
    </ul>
        <table class="table">
        <tr>
            <th style="text-align: center;">STT</th>
            <th style="text-align: center;">Mã tài khoản</th>
            <th>Email</th>
            <th>Địa chỉ</th>
            <th>Số điện thoại</th>
            <th>Quyền</th>
            <th>Họ và lót</th>
            <th>Tên</th>
            <th style="text-align: end;">
                <div class="row">
                    
                    <div class="col-sm-9" style="text-align: end;">
                      Tùy chọn  
                    </div>
                    <div class="col">
                        <a type="button" onclick="reload()" style="">
                            <i class="bi bi-arrow-clockwise"></i>
                        </a> 
                    </div>
                    
                </div>
                
            </th>
        </tr>
        <c:forEach var="acc" items="${list}" varStatus="loop">
            <tr>
                <td style="text-align: center;">${loop.count}</td>
                <td style="text-align: center;">${acc.id}</td>
                <td >${acc.email}</td>                    
                
                <td>${acc.address}</td>
                <td>
                    ${acc.phone}
                </td>
                <td>${acc.role}</td>
                <td>${acc.lastName}</td>
                <td>${acc.firstName}</td>
                <td style="text-align: end;">
                    <a class="btn btn-danger" style="width: 120px" 
                       href="#" onclick="confirmDelete(${acc.id},'${acc.lastName } ${acc.firstName}')">
                        <i class="bi bi-x-lg"></i> Xóa
                    </a> 
                    <a class="btn btn-secondary" style="width: 120px" 
                       href="#" onclick="confirmActivation(${acc.id},'${acc.lastName } ${acc.firstName}')">
                        <i class="bi bi-check-lg"></i> Kích hoạt
                    </a> 
                </td>

            </tr>
        </c:forEach>

    </table>
</div>


<script>
    function confirmActivation(accountId,accountName) {
        var result = confirm("Bạn có chắc chắn muốn kích hoạt tài khoản của " + accountName + "?");
        if (result) {
            window.location.href = "<c:url value='/humanResource/activate.do?accId=' />" + accountId;
        }
        return false;
    }
    function confirmDelete(accountId,accountName) {
        var result = confirm("Bạn có chắc chắn muốn xóa tài khoản của " + accountName + "?");
        if (result) {
            window.location.href = "<c:url value='/humanResource/delete.do?accId=' />" + accountId;
        }
        return false;
    }
    function reload(){
            location.reload();
        }
</script>