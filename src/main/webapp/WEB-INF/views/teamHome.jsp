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
        <c:choose> 
            <c:when test="${invitation == null}"> 
                <h2> Vui lòng <a href="${pageContext.request.contextPath}/league/list">đăng ký giải đấu.</a></h2>
            </c:when> 
            <c:when test="${invitation.status == 'PENDING'}" > 
                <h2> Yêu cầu tham gia giải đấu ${invitation.league.leagueName} đang được duyệt.</h2>
            </c:when> 
            <c:when test="${invitation.status == 'REJECTED'}" > 
                <h2> Yêu cầu tham gia giải đấu ${invitation.league.leagueName} đã bị từ chối!</h2>
            </c:when> 
            <c:when test="${invitation.status == 'ACCEPTED'}" > 
                <c:choose>
                    <c:when test="${noteam == 0}">
                        <h2> Yêu cầu tham gia giải đấu ${invitation.league.leagueName} đã được duyệt.</h2>
                        <h2> Bạn chưa có đội bóng. <a href="${pageContext.request.contextPath}/team/create">Tạo đội bóng</a></h2>
                    </c:when>
                    <c:otherwise> 
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
                                    <a href="${pageContext.request.contextPath}/team/summary" class="btn btn-primary mt-2">Xem tổng kết</a>
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
                                    <p class="card-text">Giải đang tham gia: <a href="#">${team.league.leagueName}</a></p>
                                    <a href="${pageContext.request.contextPath}/team/edit" class="btn btn-primary">Chỉnh sửa thông tin</a>
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
                                    <a href="${pageContext.request.contextPath}/player/view" class="btn btn-primary">Xem tất cả</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise> 
                </c:choose>
            </c:when>
        </c:choose>

    </div>

    <%@ include file="templates/footer.jsp" %>
</body>
</html>