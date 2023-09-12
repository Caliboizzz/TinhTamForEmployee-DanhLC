<%-- 
    Document   : navigation
    Created on : Apr 17, 2023, 10:02:56 PM
    Author     : ad
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<div class="row">
    <div style="text-align: right;">
        Page ${page}/${numOfPages}<br/>
        <form action="<c:url value="/receptionist/index.do" />">
            <!--First-->
            <a href="<c:url value="/receptionist/index.do?page=1" />" class="btn btn-outline-secondary" style="width: 40px;" title="First"><i class="bi bi-caret-left-square-fill"></i></a>
            <!--Prev-->
            <a href="<c:url value="/receptionist/index.do?page=${page-1}" />" class="btn btn-outline-secondary ${page==1?"disabled":""}" style="width: 40px;" title="Previous"><i class="bi bi-caret-left-fill"></i></a>
            <!--Next-->
            <a href="<c:url value="/receptionist/index.do?page=${page+1}" />" class="btn btn-outline-secondary ${page==numOfPages?"disabled":""}" style="width: 40px;" title="Next"><i class="bi bi-caret-right-fill"></i></a>
            <!--Last-->
            <a href="<c:url value="/receptionist/index.do?page=${numOfPages}" />" class="btn btn-outline-secondary" style="width: 40px;" title="Last"><i class="bi bi-caret-right-square-fill"></i></a>
            <input type="text" name="page" value="${page}" class="btn btn-outline-secondary" style="width: 40px;border-color: #01dcc8;color:#01dcc8;"/>
            <button type="submit" class="btn btn-outline-dark" style="width: 40px;color:#01dcc8;border-color:#01dcc8; " title="Go to" name="op" value="goto"><i class="bi bi-box-arrow-up-right"></i></button>
        </form>
    </div>
</div>
