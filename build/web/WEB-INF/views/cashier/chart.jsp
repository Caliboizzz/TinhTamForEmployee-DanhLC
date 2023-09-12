<%-- 
    Document   : chart
    Created on : May 28, 2023, 12:27:05 PM
    Author     : Danh
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
            <a class="nav-link" aria-current="page" href="<c:url value="/cashier/income.do" />" >Danh sách</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="<c:url value="/cashier/chart.do" />">Biểu đồ</a>
        </li>
    </ul>
    <form action="<c:url value="/cashier/chart.do"/>">
        <div class="cashier-income-search">
            <div class="row">


                <div class="col-sm-2" style="padding-top: 5px;">
                    <select class="form-control" name="year" id="yearSelect">
                        <option value="" >Chọn năm</option>
                    </select>
                </div>

                <div class="col-sm-2" style="padding-top: 5px;" >
                    <button class="btn btn-outline-secondary" type="submit" value="search">Tìm kiếm</button>

                </div>

                <div class="col-sm-8" style="text-align: end;">
                    <h1>Tổng tiền: <fmt:formatNumber value="${total}" type="currency" currencyCode="VND" pattern="#,##0 ¤"/></h1>
                </div>
            </div>
        </div>
    </form>
    <div style="text-align: center;margin-bottom: 100px;margin-top: 30px;">
        <canvas id="myChart" style="width:100%;max-width:1000px;margin: auto;"></canvas> 
    </div>

</div>
<script>
    const xValues = ["tháng 1", "tháng 2", "tháng 3", "tháng 4", "tháng 5", "tháng 6", "tháng 7", "tháng 8", "tháng 9", "tháng 10", "tháng 11", "tháng 12"];
    const yValues = ${dataChart};
    const maxVal = Math.max(...yValues);
    new Chart("myChart", {
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(0,0,255,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: yValues
                }]
        },
        options: {
            legend: {display: false},
            scales: {
                yAxes: [{
                        ticks: {
                            min: 0,
                            max: maxVal
                        }
                    }]
            }
        }
    });

    var currentYear = new Date().getFullYear();
    var selectElement = document.getElementById("yearSelect");

    for (var i = 2000; i <= currentYear; i++) {
        var option = document.createElement("option");
        option.text = i;
        option.value = i;
        if (i === ${year}) {
            option.selected = true;
        }
        selectElement.appendChild(option);
    }
</script>