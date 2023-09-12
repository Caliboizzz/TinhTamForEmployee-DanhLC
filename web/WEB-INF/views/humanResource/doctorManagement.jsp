<%-- 
    Document   : doctorManagement
    Created on : Jun 9, 2023, 9:30:03 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <ul class="nav nav-tabs justify-content-end" style="margin-bottom: 5px;">

        <li class="nav-item">
            <a class="nav-link active" href="<c:url value="/humanResource/doctorManagement.do"/>">Quản lý bác sĩ</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/humanResource/employeeManagement.do"/>">Quản lý nhân viên</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" aria-current="page" href="<c:url value="/humanResource/index.do"/>" >Kích hoạt tài khoản</a>
        </li>
    </ul>
    <form action="<c:url value="/humanResource/doctorManagement.do"/>">
        <div class="cashier-income-search">
            <div class="row">
                <div class="col-sm-2" style="padding-top: 5px;">
                    <select id="employeeId" name="employeeId" multiple placeholder="Nhập tên để tìm kiếm...">
                        <c:forEach var="employee" items="${listSearch}">
                            <option value="${employee.id}" >${employee.lastName} ${employee.firstName}</option>
                        </c:forEach>
                    </select>  
                </div>

                <div class="col-sm-2" style="padding-top: 5px;" >
                    <button class="btn btn-outline-secondary" type="submit" value="search" name="op">Tìm kiếm</button>
                    <button class="btn btn-outline-secondary" type="submit" value="all" name="op">Tất cả</button>
                </div>
            </div> 
        </div>


    </form>

    <table class="table">
        <tr>
            <th style="text-align: center;">STT</th>
            <th>Email</th>
            <th>Địa chỉ</th>
            <th>Số điện thoại</th>
            <th>Khoa</th>
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
        <c:forEach var="employee" items="${list}" varStatus="loop">
            <tr>
                <td style="text-align: center;">${loop.count}</td>
                <td >${employee.email}</td>                    

                <td>${employee.address}</td>
                <td>
                    ${employee.phone}
                </td>
                <td>${employee.department.name}</td>
                <td>${employee.lastName}</td>
                <td>${employee.firstName}</td>
                <td style="text-align: end;">
                    <a class="btn btn-secondary" style="width: 120px" 
                       href="<c:url value="/humanResource/detail.do?empId=${employee.id}&actionPre=doctorManagement"/>">
                        <i class="bi bi-info-circle-fill"></i> Chi tiết
                    </a>
                    <a class="btn btn-danger" style="width: 120px" 
                       href="#" onclick="confirmDeactivation(${employee.id},'${employee.lastName} ${employee.firstName}')">
                        <i class="bi bi-x-lg"></i> Vô hiệu
                    </a> 
                    <a class="btn btn-secondary" style="width: 120px" 
                       href="<c:url value="/humanResource/update.do?empId=${employee.id}&actionPre=doctorManagement"/>">
                        <i class="bi bi-check-lg"></i> Chỉnh sửa
                    </a>
                </td>

            </tr>
        </c:forEach>

    </table>
</div>

<script>
    function confirmDeactivation(accountId,accountName ) {
        var result = confirm("Bạn có chắc chắn muốn vô hiệu hóa tài khoản của " + accountName + "?");
        if (result) {
            window.location.href = "<c:url value='/humanResource/deactivate.do?accId=' />" + accountId + "&actionPre=doctorManagement";
        }
        return false;
    }
    function reload() {
        location.reload();
    }

    $("#employeeId").selectize({
        maxItems: 1,
        openOnFocus: false
    });
</script>