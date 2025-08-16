<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <h2 class="text-uppercase mt-3">Lịch sử bàn thắng</h2>
        <div style="border-bottom: solid 1px red;" class="mb-3"></div>
        <div class="row mt-3 justify-content-center">
            <table class="table table-striped">
                <tr>
                    <th></th>
                    <th></th>
                    <th>Tên cầu thủ</th>
                    <th>Phút</th>
                    <th>Mô tả</th>
                </tr>
                <c:forEach items="${goals }" var="g">
                    <tr>
                        <td>${g.match.firstTeam.teamName}</td>
                        <td>${g.match.secondTeam.teamName}</td>
                        <td>${g.player.playerName}</td>
                        <td>Phút ${g.minute}</td>
                        <td>${g.description}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <%@ include file="templates/footer.jsp" %>
</body>
</html>