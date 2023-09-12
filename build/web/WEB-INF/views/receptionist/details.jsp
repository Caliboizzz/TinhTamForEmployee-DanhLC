<%-- 
    Document   : detail.jsp
    Created on : Jun 17, 2023, 10:52:17 PM
    Author     : ad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="row" >
    <div class="col" style="margin-left: -35px;">
        <div class="col-sm-8">

            <a href="<c:url value="/receptionist/index.do" />"<i class="bi bi-arrow-left"></i>Trở lại</a>
        </div>  
    </div>
</div>



<h1>Thông tin chi tiết bệnh nhân</h1>


<div class="row" style="margin-top: 10px">
    <div class="col">
        <label class="form-label">Họ và tên lót:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="${patient.lastName}">
    </div >
    <div class="col">
        <label class="form-label">Tên:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="${patient.firstName}">
    </div >
    <div class="col">
        <label class="form-label">Số điện thoại:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" rows="5" readonly value="${patient.phone}"/>
    </div >
</div>
<div class="row" style="margin-top: 10px">
    <div class="col">
        <label class="form-label">Số bảo hiểm:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="${patient.healthInsurance}">
    </div >

    <div class="col">
        <label class="form-label">Ngày sinh:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="<fmt:formatDate value="${patient.birthday}" pattern="dd-MM-yyyy" />">
    </div >
    <div class="col">
        <label class="form-label">Giới tính:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="${patient.sex.equals("M") ? 'Nam' : 'Nữ'}">
    </div >
</div>
  
<div class="row" style="margin-top: 10px">
    <div class="col">
        <label class="form-label">Địa chỉ:</label>
        <textarea style="border-radius: 5px;" type="text" class="form-control" rows="5" readonly >${patient.address}</textarea>
    </div > 
</div> 
            
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
                   href="<c:url value="/receptionist/info.do?patientId=${patient.id}&mrId=${mr.id}&page=${page}" />"
                   <i class="bi bi-cash-coin"></i> Xem chi tiết
                </a> 
            </td>

        </tr>
    </c:forEach>

</table>
    
    <div class="row">
    <div style="text-align: right;">
        Page ${page}/${numOfPages}<br/>
        <form action="<c:url value="/receptionist/details.do" />" style="margin-bottom: 20px;">
            <input type="hidden" name="patientId" id="patientId" value="${param.patientId}"/>
            <!--First-->
            <a href="<c:url value="/receptionist/details.do?patientId=${patient.id}&page=1" />" class="btn btn-outline-secondary" style="width: 40px;" title="First"><i class="bi bi-caret-left-square-fill"></i></a>
            <!--Prev-->
            <a href="<c:url value="/receptionist/details.do?patientId=${patient.id}&page=${page-1}" />" class="btn btn-outline-secondary ${page==1?"disabled":""}" style="width: 40px;" title="Previous"><i class="bi bi-caret-left-fill"></i></a>
            <!--Next-->
            <a href="<c:url value="/receptionist/details.do?patientId=${patient.id}&page=${page+1}" />" class="btn btn-outline-secondary ${page==numOfPages?"disabled":""}" style="width: 40px;" title="Next"><i class="bi bi-caret-right-fill"></i></a>
            <!--Last-->
            <a href="<c:url value="/receptionist/details.do?patientId=${patient.id}&page=${numOfPages}" />" class="btn btn-outline-secondary" style="width: 40px;" title="Last"><i class="bi bi-caret-right-square-fill"></i></a>
            <input type="text" name="page" value="${page}" class="btn btn-outline-secondary" style="width: 40px;border-color: #01dcc8;color:#01dcc8;"/>
            <button type="submit" class="btn btn-outline-dark" style="width: 40px;color:#01dcc8;border-color:#01dcc8; " title="Go to" name="op" value="goto"><i class="bi bi-box-arrow-up-right"></i></button>
        </form>
    </div>
</div>
