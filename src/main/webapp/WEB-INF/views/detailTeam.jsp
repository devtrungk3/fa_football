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
        <h2 class="text-uppercase mt-4">Trang của đội bóng</h2>
        <div style="border-bottom: solid 1px red;" class="mb-3"></div>
                        <div class="row mt-3 justify-content-center">
                            <div class="col-3">
                                <div class="card">
                                    <h5 class="card-header">Lịch sử thi đấu</h5>
                                    <div class="card-body">
                                        <table>
                                            <c:forEach var = "m" items="${matchs}">
                                                <tr>
                                                    <td class="text-danger fw-bolder">${m.firstTeam.teamName}</td>
                                                    <td></td>
                                                    <td>${m.firstTeamScore}</td>
                                                    <td>:</td>
                                                    <td>${m.secondTeamScore}</td>
                                                    <td></td>
                                                    <td class="text-danger fw-bolder">${m.secondTeam.teamName}</td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <div class="col-6">
                                <div class="card">
                                    <h5 class="card-header">
                                    Thông tin đội bóng
                                    </h5>
                                    <div class="card-body">
                                    <h4 class="card-title">${team.teamName}</h4>
                                    <p class="card-text">Huấn luyện viên: ${team.coachName}</p>
                                    <p class="card-text">Giải đang tham gia: <a href="${pageContext.request.contextPath}/league/${team.league.leagueId}">${team.league.leagueName}</a></p>
                                    </div>
                                </div>
                            </div>

                            <div class="col-3">
                                <div class="card">
                                    <h5 class="card-header">Danh sách cầu thủ</h5>
                                    <div class="card-body">
                                    <c:forEach var = "p" items="${players}">
                                        <h6 class="card-title">${p.playerName}</h6>
                                    </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>

    </div>

    <%@ include file="templates/footer.jsp" %>
</body>
</html>