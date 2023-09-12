<%-- 
    Document   : details
    Created on : Apr 25, 2023, 6:23:24 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="no-print">
    <div class="row" >
        <div class="col" style="margin-left: -35px;">
            <div class="col-sm-8">

                <a href="<c:url value="/cashier/index.do" />"<i class="bi bi-arrow-left"></i>Trở lại</a>
            </div>  
        </div>
        <div class="col" style="text-align: end">
            <button class="btn btn-outline-secondary" style="border-radius: 50px;" onclick="window.print()"><i class="bi bi-printer"></i> IN</button> 
        </div>
    </div>


</div>
<div class="print-only">
    <div style="text-align: end;margin-top: -65px;margin-bottom: 30px">
        Mã phiếu khám: ${mr.id}
    </div>
</div>
<div class="print-only">
    <h1 style="text-align: center">TOA THUỐC</h1>
</div>

<h1 class="no-print">Phiếu khám bệnh</h1>
<div class="print-only">
    <div>Tên bệnh nhân: ${mr.patientName}</div><br/>
    <div style="margin-top: -20px;">Bác sĩ tiếp nhận: ${mr.doctorName}</div><br/>
    <div style="margin-top: -20px;">Chuẩn đoán: <a style="font-weight: 900;">${mr.note}</a>  </div><br/>

</div>
<div class="no-print">
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
<div class="print-only">
    <div class="row"> 
        <div class="col">
            <div>Ngày tái khám: <fmt:formatDate value="${mr.reCheckDate}" pattern="dd-MM-yyyy" /></div>
        </div>
        <div class="col">
            <div style="font-weight: 900;text-align: end;">Tổng tiền: <fmt:formatNumber value="${mr.totalPrice}" type="currency" currencyCode="VND" pattern="#,##0 ¤"/> </div>  
        </div>
    </div>
    <div style="display: flex;justify-content: flex-end;margin-top: 50px;margin-bottom: 150px;">
        <div style="text-align: center;">
            <div>Ngày <fmt:formatDate value="${mr.dateCreated}" pattern="dd 'tháng' MM 'năm' yyyy" /></div>
            <div>
                <a style="font-weight: 900;">Dược sĩ phát thuốc</a><br/>
                <a>(Ký tên và ghi rõ họ tên)</a>
            </div>  
        </div>

    </div>
    <div style="font-style: italic">
        Khi tái khám vui lòng mang theo phiếu này
    </div><br/>
    <div style="margin-top: -25px">
        (Lưu ý chủ nhật Tịnh Tâm Medical chỉ làm việc buổi sáng nghỉ sau 12 giờ trưa)
    </div>

</div>





<div class="no-print" style="text-align: center;margin-bottom: 20px;">
    <a href="<c:url value="/cashier/checkout.do?mrId=${param.mrId}" />" class="btn btn-secondary" style="width: 230px;" >
        <i class="bi bi-cash"></i> Thanh toán
    </a>  
</div> 



<!--<button class="no-print" onclick="printContent()">In nội dung</button>

<script>
    function printContent() {
        var additionalContent = `

    <h1>heading</h1>
    <p>test</p>
  `;

        var printWindow = window.open('', 'TinhTamMMedical');
        printWindow.document.write('<html><head><title>Tịnh Tâm Medical</title></head><body>');
        printWindow.document.write(additionalContent);
        printWindow.document.write('<footer><p>Footer</p></footer></body></html>');
        printWindow.document.close();
        printWindow.print();
    }
</script>-->




