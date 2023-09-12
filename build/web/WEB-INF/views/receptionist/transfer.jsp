<%-- 
    Document   : transfer
    Created on : Apr 4, 2023, 12:06:09 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
    <div class="col">
        <a href="<c:url value="/receptionist/index.do" />" style="margin-left: -30px; text-decoration:none;color: gray;font-size: 25px;"><i class="bi bi-arrow-left"></i>Trở về</a>

    </div>
    <div class="col" >
        <form action="<c:url value="/receptionist/transfer.do" />">
            <div class="row">
                <div class="col" style="text-align: end;padding-top: 5px;">
                    <label for="sex" class="form-label">Chọn khoa:</label>
                </div>
                <input type="hidden" name="patientId" value="${patientId}"/>
                <div class="col">

                    <select name="depId" class="form-control">
                        <option value="null" ${depId.equals("null") ? 'selected' : ''}>Tất cả</option>
                        <c:forEach var="Department" items="${listDepartment}">
                            <option value="${Department.id}" ${depId == Department.id ? 'selected' : ''}>${Department.name}</option>
                        </c:forEach>
                    </select>



                </div> 
                <div class="col">
                    <button class="btn btn-secondary" type="submit" value="search">Tìm kiếm</button>
                </div>
            </div>
        </form> 
    </div>

</div>

<form action="<c:url value="/receptionist/transferHandler.do"/>" style="margin-top: 30px">
    <input type="hidden" name="patientId" value="${patientId}"/>
    <table class="table table-striped">
        <tr>
            <th style="text-align: right;">STT</th>

            <th>Họ và tên lót </th>
            <th>Tên </th>
            <th style="text-align: center;">Chuyên khoa</th>
            <th style="text-align: center;">Số điện thoại</th>
            <th style="text-align: center;">Số bệnh nhân đang tiếp nhận</th>
            <th style="text-align: right;"></th>

        </tr>
        <c:forEach var="doctor" items="${list}" varStatus="loop">
            <tr>
                <td style="text-align: right;">${loop.count}</td>
                <td>${doctor.lastName}</td>
                <td>${doctor.firstName}</td>
                <td style="text-align: center;">${doctor.departmentName}</td>
                <td style="text-align: center;">${doctor.phone}</td>
                <td style="text-align: center;">${doctor.processingCount}</td>
                <td style="text-align: right;">
                    <button type="submit" name="doctorId" value="${doctor.id}" class="btn btn-secondary"><i class="bi bi-send"></i> Chuyển phiếu</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>