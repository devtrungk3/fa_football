<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%@ include file="templates/header.jsp" %>

    <div class="container">
        <h2 class="text-uppercase mt-3">Tổng kết</h2>
        <div style="border-bottom: solid 1px red;" class="mb-3"></div>
        <div class="row mt-3 justify-content-center">
            <c:forEach items="${standing }" var="s">
                <div class="card">
                    <h5 class="card-header"><a href="${pageContext.request.contextPath}/" style="text-decoration: none; color: black;">Giải ${s.league.leagueName}</a></h5>
                    <div class="card-body">
                      <p class="card-text">Số trận thắng: ${s.win}</p>
                      <p class="card-text">Số trận thua: ${s.loss}</p>
                      <p class="card-text">Tổng số bàn thắng: ${s.ga}</p>
                      <p class="card-text">Tổng số bàn thua: ${s.gf}</p>
                      <p class="card-text">Hiệu số: ${s.draw}</p>
                      <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Xem bảng xếp hạng</a>
                      <a href="${pageContext.request.contextPath}/team/goalHistory" class="btn btn-info">Xem lịch sử bàn thắng</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@ include file="templates/footer.jsp" %>
</body>
</html>