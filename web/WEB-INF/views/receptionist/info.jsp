<%-- 
    Document   : info.jsp
    Created on : Jun 18, 2023, 1:05:59 PM
    Author     : ad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<div class="row" >
    <div class="col" style="margin-left: -35px;">
        <div class="col-sm-8">
            <a href="<c:url value="/receptionist/details.do?patientId=${param.patientId}&page=${param.page}" />"<i class="bi bi-arrow-left"></i>Trở lại</a>
        </div>  
    </div>
</div>



<h1>Thông tin chi tiết</h1>


<div class="row" style="margin-top: 10px">
    <div class="col">
        <label class="form-label">Ngày tạo:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="<fmt:formatDate value="${mr.dateCreated}" pattern="dd-MM-yyyy" />">
    </div >
    <div class="col">
        <label class="form-label">Bác sĩ tiếp nhận:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="${mr.doctorName}">
    </div >
    <div class="col">
        <label class="form-label">Tên bệnh nhân:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="${mr.patientName}">
    </div >

</div>
<div class="row" style="margin-top: 10px">
    <div class="col">
        <label class="form-label">Tiếp tân tiếp nhận:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="${mr.receptionistName}">
    </div >

    <div class="col">
        <label class="form-label">Ngày tái khám:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="<fmt:formatDate value="${mr.reCheckDate}" pattern="dd-MM-yyyy" />">
    </div >
    <div class="col">
        <label class="form-label">Tổng tiền:</label>
        <input style="border-radius: 5px;" type="text" class="form-control" readonly value="<fmt:formatNumber value="${mr.totalPrice}" type="currency" currencyCode="VND" pattern="#,##0 ¤"/>">
    </div >
</div>
<div class="row" style="margin-top: 10px">
    <div class="col">
        <label class="form-label">Ghi chú:</label>
        <textarea style="border-radius: 5px;" type="text" class="form-control" rows="5" readonly >${mr.note}</textarea>
    </div > 
</div>   


<table class="table">
    <thead>
        <tr>
            <th style="text-align: center;">STT</th>
            <th style="text-align: left;">Thuốc</th>   
            <th style="text-align: left;">Ghi chú</th>  
            <th style="text-align: center;">Số lượng</th>
            <th style="text-align: left;">Giá</th>
        </tr> 
    </thead>
    <tbody>
        <c:forEach var="pf" items="${list}" varStatus="loop">
            <tr>
                <td style="text-align: center;">${loop.count}.</td>
                <td style="text-align: left;">
                    <div>${pf.medicine.name}</div><br/>
                    <div style="margin-top: -25px;">sáng: ${pf.dosageMorning} trưa: ${pf.dosageAfternoon} chiều: ${pf.dosageEvening} tối: ${pf.dosageNight}</div>
                </td>
                <td style="text-align: left;">${pf.note}</td>
                <td style="text-align: center;">${pf.quantity}</td>
                <td style="text-align: left;"><fmt:formatNumber value="${(pf.medicine.price)*(pf.quantity)}" type="currency" currencyCode="VND" pattern="#,##0 ¤"/></td>

            </tr>
        </c:forEach>
    </tbody>

</table>
