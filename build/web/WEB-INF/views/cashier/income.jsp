<%-- 
    Document   : income
    Created on : Apr 2, 2023, 1:43:24 PM
    Author     : hai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-sm-8" style="margin-bottom: -35px;">
    <a href="<c:url value="/cashier/index.do" />"<i class="bi bi-arrow-left"></i>Trở lại</a>
</div>
<div>
    <ul class="nav nav-tabs justify-content-end" style="margin-bottom: 5px;">
        <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="<c:url value="/cashier/income.do"/>" >Danh sách</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/cashier/chart.do"/>">Biểu đồ</a>
        </li>
    </ul>
    <form action="<c:url value="/cashier/income.do"/>" ">
        <div class="cashier-income-search">
            <div class="row">
                <div class="col-sm-2" style="padding-top: 10px;font-size: 20px;">
                    <input type="radio" id="day" name="op" value="day">
                    <label style="margin-right: 15px;">Ngày </label>
                    <input type="radio" id="month" name="op" value="month">
                    <label style="margin-right: 15px;">Tháng </label>
                    <input type="radio" id="year" name="op" value="year">
                    <label>Năm </label>
                </div>

                <div class="col-sm-2" style="padding-top: 5px;">
                    <input class="form-control" type="date" id="date" name="date" value="${date}" style="padding-left: 55px;">
                </div>
                <div class="col-sm-2" style="padding-top: 5px;" >
                    <button class="btn btn-outline-secondary" type="submit" value="search">Tìm kiếm</button>
                    <button class="btn btn-outline-secondary" type="submit" value="search" name="op" value="">Tất cả</button>
                </div>

                <div class="col-sm-6" style="text-align: end;">
                    <h1>Tổng tiền: <fmt:formatNumber value="${total}" type="currency" currencyCode="VND" pattern="#,##0 ¤"/></h1>
                </div>
            </div>
        </div>
    </form>
    <table class="table">
        <tr>
            <th style="text-align: right;">STT</th>
            <th style="text-align: center;">Mã phiếu khám bệnh</th>
            <th>Tên bệnh nhân </th>
            <th>Bác sĩ xử lý </th>
            <th>Tiếp tân xử lý </th>
            <th>Thu ngân xử lý </th>
            <th style="text-align: center;"> Ngày khám</th>
            <th style="text-align: center;">Tình trạng thanh toán </th>
            <th style="text-align: center;">Thành tiền</th>
            <th></th>
        </tr>
        <c:forEach var="mr" items="${list}" varStatus="loop">
            <tr>
                <td style="text-align: right;">${loop.count}</td>
                <td style="text-align: center;">${mr.id}</td>
                <td >${mr.patientName}</td>                    
                <td>${mr.doctorName}</td>
                <td>${mr.receptionistName}</td>
                <td>${mr.cashierName}</td>
                <td style="text-align: center;">
                    <fmt:formatDate value="${mr.dateCreated}" pattern="MM-dd-yyyy" />
                </td>
                <td style="text-align: center;">${mr.status}</td>
                <td style="text-align: center;">
                    <fmt:formatNumber value="${mr.totalPrice}" type="currency" currencyCode="VND" pattern="#,##0 ¤"/>
                </td>
                <td style="text-align: center;">
                    <a class="btn btn-secondary" style="width: 130px" 
                       href="<c:url value="/cashier/info.do?mrId=${mr.id}&page=${page+1}&op=${op}&date=${date}" />"
                       <i class="bi bi-cash-coin"></i> Xem chi tiết
                    </a> 
                </td>

            </tr>
        </c:forEach>

    </table>
    <jsp:include page="navigation.jsp" />           
</div>





