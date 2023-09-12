<%-- 
    Document   : index
    Created on : Mar 31, 2023, 1:06:09 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-sm-8">
    <a href="<c:url value="/cashier/income.do" />">Doanh Thu</a>
</div>

<table class="table table-striped">
    <thead>
        <tr>
            <th style="text-align: right;">STT</th>
            <th style="text-align: center;">Mã phiếu</th>
            <th>Tên bệnh nhân </th>
            <th>Tiếp tân xử lý </th>
            <th>Bác sĩ tiếp nhận </th>
            <th style="text-align: center;">Ngày khám</th>
            <th style="text-align: center;">Mã bệnh nhân</th>   
            <th style="text-align: left;">Tổng tiền</th>  
            <th></th>
        </tr> 
    </thead>


    <tr id="divResult"></tr>
    <tfoot>

    <td colspan="12" style="text-align: center;">
        <img src="<c:url value="/images/loading.gif" />" style="width: 10%" id="loader" class="hidden"/>
    </td>

</tfoot>
</table>
<script>
    var currentList = [];
    setInterval(function () {
        $.ajax({
            url: "<c:url value="/cashier/ajaxController.do"/>", //Ten controller xu ly yeu cau
            method: "GET",
            async: true, //Xu ly yeu cau bat dong bo
            cache: false, //Khong cache du lieu
            dataType: "json", //Kieu du lieu server gui ve
            data: {//Du lieu gui len server 
                op: "getMedicalRecord"
            },
            beforeSend: function () {//Chay truoc khi gui yeu cau len server
                if (currentList.length === 0) {
                    $("#loader").toggleClass("hidden");
                }

            }
        }).done(function (list) {//Lay ket qua server gui ve  
            console.log(currentList.length);
            var newItems = list.filter(function (mr) {
                return !currentList.some(function (item) {
                    return item.id === mr.id; // Kiểm tra trùng mã phiếu khám bệnh
                });
            });

            s = ``
            //duyệt qua từng phần tử của list
            $.each(newItems, function (index, mr) {
                //tiền tệ format
                const price = mr.totalPrice;
                const formattedPrice = price.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace('₫', ' VND');

                //date format
                var date = new Date(mr.dateCreated);
                // Lấy thông tin ngày, tháng, năm từ đối tượng Date
                var day = String(date.getDate()).padStart(2, '0');
                var month = String(date.getMonth() + 1).padStart(2, '0');
                var year = date.getFullYear();
                // Định dạng chuỗi đầu ra
                var formattedDate = `\${day}-\${month}-\${year}`;
                //Tạo một table row mới
                //console.log(formattedDate);
                s += `
                    <tr>
                    <td style="text-align: right;">\${index+1}</td>
                    <td style="text-align: center;">\${mr.id}</td>
                    <td>\${mr.patientName}</td>
                    <td>\${mr.receptionistName}</td>  
                    <td>\${mr.doctorName}</td>
                    <td style="text-align: center;">
                        \${formattedDate}
                    </td>
                    <td style="text-align: center;">\${mr.patientId}</td>
                    <td style="text-align: left;">\${formattedPrice}</td>
                    <td style="text-align: center;">
                        <a class="btn btn-secondary" style="width: 130px" 
                            href="<c:url value="/cashier/details.do?mrId=\${mr.id}" />"
                            <i class="bi bi-cash-coin"></i> Thanh Toán
                        </a> 
                    </td>
                  </tr>`

            });
            currentList = currentList.concat(newItems);
            $("#divResult").appendTo($("#divResult").closest('table')).after(s);
        }).fail(function (xhr, status) {//Xu ly loi
            $("#divResult").html("HTTP error: " + status + ": " + xhr.statusText);
        }).always(function () {//Luon duoc thuc hien sau khi xu ly xong yeu cầu
        });
    }, 3000);
</script>