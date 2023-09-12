<%-- 
    Document   : index
    Created on : Mar 31, 2023, 1:06:09 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="row" style="margin-top: 30px;">
    <div class="col-sm-5">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th style="text-align: right;">STT</th>
                    <th style="text-align: center;">Mã phiếu</th>
                    <th>Tên bệnh nhân </th>
                    <th style="text-align: center;">Ngày khám</th>
                    <th style="text-align: center;">Mã bệnh nhân</th>   
                    <th></th>
                </tr> 
            </thead>


            <tr id="divResult"></tr>
            <tfoot>

            <td colspan="6" style="text-align: center;">
                <img src="<c:url value="/images/loading.gif" />" style="width: 10%" id="loader-tbl" class="hidden"/>
            </td>

            </tfoot>
        </table>
    </div>
    <div class="col-sm-7">

        <div  type="hidden" id="idSelected"></div>
        <div class="row">
            <div class="col">
                <label for="patientName" class="form-label">Tên:</label>
                <input style="border-radius: 5px;" type="text" class="form-control" id="patientName" name="patientName" readonly value="" placeholder="Tên bệnh nhân" required>
            </div >
            <div class="col">
                <label for="reCheckdate" class="form-label">Ngày tái khám:</label>
                <input style="border-radius: 5px;" type="date" class="form-control" id="reCheckdate" name="reCheckdate" required value="" placeholder="Nhập tên của bạn">
            </div>
        </div>
        <div style="    padding-left: 30px; padding-right: 30px; padding-top: 10px;">
            <label for="diagnose" class="form-label">Chuẩn đoán:</label>
            <textarea style="border-radius: 5px;" id="diagnose" class="form-control" rows="8" placeholder="Nhập chuẩn đoán"></textarea>
        </div>
        <div class="row" style="padding-top: 30px;">
            <div class="col-sm-3" >
                <label for="medicineName" class="form-label">Tên thuốc:</label>

            </div>
            <div class="col-sm-1">
                <label for="quantitie" class="form-label">Sl:</label>

            </div>
            <div class="col">
                <label for="morning" class="form-label">Sáng:</label>

            </div>
            <div class="col">
                <label for="afternoon" class="form-label">Trưa:</label>

            </div>
            <div class="col">
                <label for="evening" class="form-label">chiều:</label>

            </div>
            <input type="hidden" id="prices" name='price' value=""/>
            <div class="col">
                <label for="night" class="form-label">Tối:</label>

            </div>
            <div class="col-sm-3">
                <label for="note" class="form-label">Ghi chú:</label>

            </div>
            <div class="col-sm-1" style="padding-top: 30px; text-align: end;">

            </div>
        </div>
        <div class="row" id="rowContainer" style="padding-left: 10px;padding-right: 0px; padding-top: 10px;">
            <!-- Dòng HTML ban đầu -->
            <div class="col-sm-11">
                <!-- Các trường input và label khác trong dòng này -->
            </div>
            <!-- Nút thêm hàng -->

        </div>
        <div class="btn-register" style="text-align: end;">
            <button type="button" id="btn-add" onclick="addRow()">Thêm thuốc</button>
        </div>

        <div class="btn-register" style="text-align: center;">
            <button type="submit" id="btn-send" value="send">Hoàn tất</button>
        </div>

        <div style="text-align: center; margin-bottom: 30px; margin-top: -30px;">
            <img src="<c:url value="/images/loading.gif" />" style="width: 10%" id="loader" class="hidden"/>
        </div>
    </div>





</div>

<script>
    var currentList = [];
    var listMedicine = [];
    setInterval(function () {
        $.ajax({
            url: "<c:url value="/doctor/ajaxController.do"/>", //Ten controller xu ly yeu cau
            method: "GET",
            async: true, //Xu ly yeu cau bat dong bo
            cache: false, //Khong cache du lieu
            dataType: "json", //Kieu du lieu server gui ve
            data: {//Du lieu gui len server 
                op: "getMedicalRecord"
            },
            beforeSend: function () {//Chay truoc khi gui yeu cau len server
                if (currentList.length === 0) {
                    $("#loader-tbl").toggleClass("hidden");
                }

            }
        }).done(function (result) {//Lay ket qua server gui ve  
            var list = result.listMR;
            listMedicine = result.listM;
            //console.log(list);
            //console.log(listMedicine);
            var newItems = list.filter(function (mr) {
                return !currentList.some(function (item) {
                    return item.id === mr.id; // Kiểm tra trùng mã phiếu khám bệnh
                });
            });

            s = ``
            //duyệt qua từng phần tử của list
            $.each(newItems, function (index, mr) {
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
                        <td style="text-align: center;">
                            \${formattedDate}
                        </td>
                        <td style="text-align: center;">\${mr.patientId}</td>
                        <td style="text-align: center;">
                            <button type="button" onclick="examine(\${mr.id},'\${mr.patientName}')" class="btn btn-secondary" >
                                <i class="bi bi-heart-pulse-fill"></i> Khám bệnh
                            </button>  
                        </td>
                      </tr>`

            });
            currentList = currentList.concat(newItems);
            $("#divResult").appendTo($("#divResult").closest('table')).after(s);
        }).fail(function (xhr, status) {//Xu ly loi
            $("#divResult").html("HTTP error: " + status + ": " + xhr.statusText);
        }).always(function () {//Luon duoc thuc hien sau khi xu ly xong yeu cau
        });
    }, 3000);
    function addRow() {
        if (listMedicine.length === 0) {
            alert("Xin đợi trong giây lát");
        } else {

            // Tạo một dòng mới
            var newRow = document.createElement("div");
            newRow.className = "row";

            // Thêm nội dung của dòng mới vào
            newRow.innerHTML = `
            <div class="col-sm-3" >
            
                <select id="medicineNames" name="medicineNames" required>
                </select>
            </div>
            <div class="col-sm-1">
              
                <input type="number" min="0" class="form-control" id="quantities" name="quantities" required value="1">
            </div>
            <div class="col">
              
                <input type="number" min="0" class="form-control" id="mornings" name="mornings" required value="1">
            </div>
            <div class="col">
                
                <input type="number" min="0" class="form-control" id="afternoons" name="afternoons" required value="1">
            </div>
            <div class="col">
             
                <input type="number" min="0" class="form-control" id="evenings" name="evenings" required value="1">
            </div>
            <input type="hidden" id="prices" name='prices' value=""/>
            <div class="col">
             
                <input type="number" min="0" class="form-control" id="nights" name="nights" required value="1">
            </div>
            <div class="col-sm-3">
                
                <input type="text" class="form-control" style="border-radius: 5px;" id="notes" name="notes" value="" placeholder="Nhập ghi chú">
            </div>
            <div class="col-sm-1" style="text-align: end;">
                <button type="button" class="btn btn-danger" onclick="removeRow(this)">Xóa</button>
            </div>`;

            // Thêm dòng mới vào container
            document.getElementById("rowContainer").appendChild(newRow);
            // Lấy thẻ select trong dòng mới được thêm vào
            var selectElement = newRow.querySelector("#medicineNames");
            //Thêm giá trị cho các thẻ option
            listMedicine.forEach(function (lst, index) {
                var optionElement = document.createElement("option");

                optionElement.value = lst.id; // Giá trị của thẻ option
                optionElement.text = lst.name; // Hiển thị nội dung của thẻ option
                selectElement.add(optionElement); // Thêm thẻ option vào thẻ select
            });



            var quantitiesMedicine = newRow.querySelector("#quantities");
            var pricesMedicine = newRow.querySelector("#prices");
            quantitiesMedicine.max = listMedicine[0].inStock;
            pricesMedicine.value = listMedicine[0].price;
            $("#medicineNames", newRow).selectize({
                onChange: function (value) {
                    var selectedMedicine = listMedicine.find(function (medicine) {
                        return medicine.id == value;
                    });
                    pricesMedicine.value = selectedMedicine.price;
                    quantitiesMedicine.max = selectedMedicine.inStock;
                    console.log(value);
                    console.log(pricesMedicine.value);
                }
            }
            );
        }
    }
    ;

    function removeRow(row) {
        // Xóa dòng
        row.parentNode.parentNode.remove();
    }
    ;



    function examine(id, patientName) {
        document.getElementById("patientName").value = patientName;
        document.getElementById("idSelected").value = id;
    }
    ;
    $("#btn-send").click(function () {
        if (document.getElementById("patientName").value === "") {
            alert("Vui lòng chọn bệnh nhân cần khám");
        } else {
            reCheckDate = $("#reCheckdate").val();
            diagnose = $("#diagnose").val();
            mrId = $("#idSelected").val();
            medIds = $.map($("select[name='medicineNames']"), function (el) {
                return el.value;
            });
            quantities = $.map($("input[name='quantities']"), function (el) {
                return el.value;
            });
            mornings = $.map($("input[name='mornings']"), function (el) {
                return el.value;
            });
            afternoons = $.map($("input[name='afternoons']"), function (el) {
                return el.value;
            });
            evenings = $.map($("input[name='evenings']"), function (el) {
                return el.value;
            });
            nights = $.map($("input[name='nights']"), function (el) {
                return el.value;
            });
            notes = $.map($("input[name='notes']"), function (el) {
                return el.value;
            });
            prices = $.map($("input[name='prices']"), function (el) {
                return el.value;
            });
            console.log(mrId)
            console.log(reCheckDate)
            console.log(diagnose)
            console.log(medIds)
            console.log(quantities)
            console.log(prices)
            $.ajax({
                url: "<c:url value="/doctor/ajaxController.do"/>", //Ten controller xu ly yeu cau
                method: "GET",
                async: true, //Xu ly yeu cau bat dong bo
                cache: false, //Khong cache du lieu
                dataType: "json", //Kieu du lieu server gui ve
                data: {//Du lieu gui len server
                    op: "sendMedicalRecord",
                    mrId: mrId,
                    diagnose: diagnose,
                    reCheckDate: reCheckDate,
                    medIds: JSON.stringify(medIds),
                    quantities: JSON.stringify(quantities),
                    mornings: JSON.stringify(mornings),
                    afternoons: JSON.stringify(afternoons),
                    evenings: JSON.stringify(evenings),
                    nights: JSON.stringify(nights),
                    notes: JSON.stringify(notes),
                    prices: JSON.stringify(prices)
                },
                beforeSend: function () {//Chay truoc khi gui yeu cau len server 
                    $("#loader").toggleClass("hidden");
                }
            }).done(function () {//Lay ket qua server gui ve  
                //location.reload();
            }).fail(function (xhr, status) {//Xu ly loi
                //$("#divResult").html("HTTP error: " + status + ": " + xhr.statusText);
            }).always(function () {//Luon duoc thuc hien sau khi xu ly xong yeu cau
                location.reload();
                $("#loader").toggleClass("hidden");


            });
        }

    });
</script>
