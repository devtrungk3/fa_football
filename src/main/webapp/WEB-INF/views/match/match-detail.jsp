<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết Trận đấu</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/detail-match-style.css"
	type="text/css">
</head>
<body>
	<%@ include file="../templates/header.jsp"%>

	<section class="match-section"
		style="background-image: url(${pageContext.request.contextPath }/resources/img/match/match-bg.jpg); padding-top: 60px;">
		<div class="container">
			<div class="row">
				<c:choose>
					<c:when test="${role == 'ROLE_LEAGUE'}">
						<div class="col-lg-8">
					</c:when>
					<c:otherwise>
						<div class="col-lg-12">
					</c:otherwise>
				</c:choose>
				<div class="overview-match bg-light p-3 rounded">
					<div class="text-center border-bottom pb-2">
						<h3 class="text-uppercase fw-500">${match.league.leagueName}</h3>
					</div>
					<p class="text-center text-dark d-block mt-3">${match.matchDatetime}</p>
					<div>
						<table class="table table-borderless mt-3 mb-0">
							<tbody>
								<tr class="text-center">
									<td class="text-right pt-3">
										<h4 class="fw-500 pt-1">${match.firstTeam.teamName}</h4>
									</td>
									<td>
										<h2 class="primary-red-color fw-600">${match.firstTeamScore}
											- ${match.secondTeamScore}</h2>
									</td>
									<td class="text-left pt-3">
										<h4 class="fw-500 pt-1">${match.secondTeam.teamName}</</h4>
									</td>
								</tr>
							</tbody>
						</table>
						<table class="table table-borderless m-0">
							<tbody>
								<tr class="text-center">
									<td class="text-left pt-3"><c:forEach
											items="${eventMatch1_setfalse}" var="event1">
											<div class="player-goal-list">
												<p>${event1.player.playerName}-${event1.minute}</p>
											</div>
										</c:forEach>
										<h3>${evenMatch1}</h3></td>
									<td class="text-righ pt-3"><c:forEach
											items="${eventMatch2_setfalse}" var="event2">
											<div class="player-goal-list">
												<p>${event2.player.playerName}${event2.minute})</p>
											</div>
										</c:forEach></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="footer-stadium text-center">${match.location}</div>
				</div>
				<div class="detail-match bg-light p-3 rounded mt-3">
					<div class="text-center border-bottom pb-2">
						<h3 class="text-uppercase fw-500">
						<c:choose>
							<c:when test="${role == 'ROLE_LEAGUE'}">
								<a
								href="${pageContext.request.contextPath}/match/edit/${match.matchId}"
								class="btn btn-danger btn-custom">Chi tiết trận đấu</a>
							</c:when>
							<c:otherwise>
								<h4 class="btn btn-danger btn-custom">Chi tiết trận đấu</h4>
							</c:otherwise>
						</c:choose>
							
						</h3>
					</div>
					<div id="body-detail-match" class="pt-2">
						<div id="content-detail-match">
							<c:forEach items="${eventMatchTime}" var="event">
								<c:if
									test="${event.player.team.teamId == match.firstTeam.teamId}">
									<article class="player-goal clearfix">
										<div class="left">
											<span class="player-name text-uppercase text-secondary">${event.player.playerName}
											</span>
											<c:if test="${event.event.eventId == 1}">
												<i class="fa fa-futbol-o mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 2}">
												<i class="fa fa-file text-danger mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 3}">
												<i class="fa fa-file text-warning mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 4}">
												<i class="fa fa-exclamation text-danger mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 5}">
												<i class="fa fa-arrow-right text-danger mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 6}">
												<i class="fa fa-arrow-left text-success mx-2"></i>
											</c:if>
										</div>
										<div class="minute">${event.minute}</div>
									</article>
								</c:if>
								<c:if
									test="${event.player.team.teamId == match.secondTeam.teamId}">
									<article class="player-goal clearfix">
										<div class="right">
											<span class="player-name text-uppercase text-secondary">${event.player.playerName}
											</span>
											<c:if test="${event.event.eventId == 1}">
												<i class="fa fa-futbol-o mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 2}">
												<i class="fa fa-file text-danger mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 3}">
												<i class="fa fa-file text-warning mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 4}">
												<i class="fa fa-exclamation text-danger mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 5}">
												<i class="fa fa-arrow-right text-danger mx-2"></i>
											</c:if>
											<c:if test="${event.event.eventId == 6}">
												<i class="fa fa-arrow-left text-success mx-2"></i>
											</c:if>
										</div>
										<div class="minute">${event.minute}</div>
									</article>
								</c:if>
							</c:forEach>
							<div class="line"></div>
						</div>
					</div>
				</div>
			</div>

			<c:if test="${role == 'ROLE_LEAGUE' }">
				<div class="col-lg-4">
					<div class="row mt-3">
						<div class="col-md-6 mb-4">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">${match.firstTeam.teamName}</h5>
									<form
										action="${pageContext.request.contextPath}/match/addEvent/${match.matchId}"
										method="post">
										<div class="form-group">
											<label for="players1">Chọn cầu thủ</label> <select
												class="form-control" name="playerId" id="players1" required>
												<c:forEach items="${players1}" var="p">
													<option value="${p.playerId}">${p.playerName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label for="eventId1">Sự kiện</label> <select
												class="form-control" name="eventId" id="eventId1" required>
												<c:forEach items="${events}" var="e">
													<option value="${e.eventId}">${e.eventName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label for="minute1">Vào lúc (phút)</label> <input
												type="number" class="form-control" name="minute"
												id="minute1" required>
										</div>
										<div class="form-group">
											<label for="description1">Mô tả</label> <input type="text"
												class="form-control" name="description" id="description1">
										</div>
										<button type="submit" class="btn btn-primary btn-block">Thêm
											sự kiện</button>
									</form>
								</div>
							</div>
						</div>
						<div class="col-md-6 mb-4">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title">${match.secondTeam.teamName}</h5>
									<form
										action="${pageContext.request.contextPath}/match/addEvent/${match.matchId}"
										method="post">
										<div class="form-group">
											<label for="players2">Chọn cầu thủ</label> <select
												class="form-control" name="playerId" id="players2">
												<c:forEach items="${players2}" var="p">
													<option value="${p.playerId}">${p.playerName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label for="eventId2">Sự kiện</label> <select
												class="form-control" name="eventId" id="eventId2">
												<c:forEach items="${events}" var="e">
													<option value="${e.eventId}">${e.eventName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label for="minute2">Vào lúc (phút)</label> <input
												type="number" class="form-control" name="minute"
												id="minute2">
										</div>
										<div class="form-group">
											<label for="description2">Mô tả</label> <input type="text"
												class="form-control" name="description" id="description2">
										</div>
										<button type="submit" class="btn btn-primary btn-block">Thêm
											sự kiện</button>
									</form>
								</div>
							</div>
						</div>

					</div>
				</div>
			</c:if>
	</section>

	<%@ include file="../templates/footer.jsp"%>
</body>
</html>