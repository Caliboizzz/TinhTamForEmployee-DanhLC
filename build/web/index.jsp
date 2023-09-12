<%-- 
    Document   : index
    Created on : Oct 24, 2022, 6:16:34 PM
    Author     : PHT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${account != null}">
        <jsp:forward page="/${account.role}/index.do" />
    </c:when>
    <c:otherwise>
        <jsp:forward page="/home/index.do" /> 
    </c:otherwise>
</c:choose> 