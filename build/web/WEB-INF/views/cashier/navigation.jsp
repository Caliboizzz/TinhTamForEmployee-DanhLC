<%-- 
    Document   : navigation
    Created on : Apr 17, 2023, 10:03:04 PM
    Author     : ad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<div class="row">
    <div style="text-align: right; margin-bottom: 15px;">
        Page ${page}/${numOfPages}<br/>
        <form action="<c:url value="/cashier/income.do" />">
            <!--First-->
            <a href="<c:url value="/cashier/income.do?page=1&op=${op}&date=${date}" />" class="btn btn-outline-secondary" style="width: 40px;" title="First"><i class="bi bi-caret-left-square-fill"></i></a>
            <!--Prev-->
            <a href="<c:url value="/cashier/income.do?page=${page-1}&op=${op}&date=${date}" />" class="btn btn-outline-secondary ${page==1?"disabled":""}" style="width: 40px;" title="Previous"><i class="bi bi-caret-left-fill"></i></a>
            <!--Next-->
            <a href="<c:url value="/cashier/income.do?page=${page+1}&op=${op}&date=${date}" />" class="btn btn-outline-secondary ${page==numOfPages?"disabled":""}" style="width: 40px;" title="Next"><i class="bi bi-caret-right-fill"></i></a>
            <!--Last-->
            <a href="<c:url value="/cashier/income.do?page=${numOfPages}&op=${op}&date=${date}" />" class="btn btn-outline-secondary" style="width: 40px;" title="Last"><i class="bi bi-caret-right-square-fill"></i></a>
            <input type="text" name="page" value="${page}" class="btn btn-outline-secondary" style="width: 40px;color: #01dcc8;border-color: #01dcc8;"/>
            <input type="hidden" name="op" value="${op}" />
            <input type="hidden" name="date" value="${date}"/>
            <button type="submit" class="btn btn-outline-secondary" style="width: 40px;color:#01dcc8;border-color: #01dcc8;" title="Go to"><i class="bi bi-box-arrow-up-right"></i></button>
        </form>
    </div>
</div>

