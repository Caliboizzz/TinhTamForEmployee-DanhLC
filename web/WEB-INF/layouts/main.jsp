<%-- 
    Document   : main
    Created on : Mar 31, 2023, 1:06:09 PM
    Author     : Danh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>TinhTamMedical</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="<c:url value="/js/jquery.min.js" />"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.13.3/css/selectize.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/selectize.js/0.13.3/js/standalone/selectize.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <link href="<c:url value="/css/site.css" />" rel="stylesheet" type="text/css"/>
    </head>        
    <body>
        <!--Header-->
        <div class="row header">
            <div class="col">

                <img src="<c:url value="/images/logo.png" />" alt="" height="80"/><br/>

            </div>

            <c:choose>
                <c:when test="${account != null}">

                    <div class="col" style="text-align: right;margin: auto">
                        <div class="no-print">
                            <div style="font-size: 20px">
                                Xin chào ${account.firstName}
                            </div>
                            <div class="col-sm-12">
                                <a href="<c:url value="/account/logout.do" />" style="font-size: 15px"><i class="bi bi-box-arrow-left"></i> Logout</a>  
                            </div>
                        </div> 
                    </div>

                </c:when>

            </c:choose>



        </div>

        <!--View-->
        <div class="row content">
            <div class="col">
                <jsp:include page="/WEB-INF/views/${controller}/${action}.jsp" />
            </div>
        </div>

        <!--Footer-->
        <div class="no-print">
            <div class="row footer">
                <div class="col"style="margin-top: 20px;">
                    <a href="/" class="img">
                        <img src="<c:url value="/images/logo.png" />"style="width: 15%; margin-bottom: 20px"/>     
                    </a> 
                    <div class="contact">
                        <h2>Công ty cổ phần Tịnh Tâm</h2>   
                        <p>
                            <i class="bi bi-envelope"></i>
                            TinhTamSite@gmail.com
                            </br> 
                            <i class="bi bi-geo-alt"></i>
                            123 Trần Hưng Đạo, Phường 3, Quận 5, Thành Phố Hồ Chí Minh,Việt Nam
                        </p>
                    </div>

                </div>
                <div class="col" style="margin-top: 20px">
                    <div class="contact">

                        <h2> Văn phòng đại diện tại Thành phố Hồ Chí Minh </h2>
                        <p>212 Lý Thường Kiệt, Phường 12, Quận 10, Thành phố Hồ Chí Minh, Việt Nam</p>

                        <h2>Hotline</h2>
                        <p style="color: #01dcc8">1800 2724</p>
                    </div>
                </div> 
                <p style="color: #01dcc8">
                    Copyrights &copy; by Công ty Cổ phần Tịnh Tâm. All rights reserved.
                </p>
            </div>

        </div>
    </body>
</html> 


