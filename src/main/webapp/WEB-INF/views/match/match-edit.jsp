<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa trận đấu</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/detail-match-style.css"
	type="text/css">
</head>
<body>
	<%@ include file="../templates/header.jsp"%>

	<section class="match-section"
		style="background-image: url(${pageContext.request.contextPath }/resources/img/match/match-bg.jpg); padding-top: 60px;">
		<div class="container mt-6">
			<div class="card">
				<div class="card-header">
					<h4>${match.firstTeam.teamName}- ${match.secondTeam.teamName}</h4>
				</div>
				<div class="card-body">
					<form action="${pageContext.request.contextPath}/match/editMatch"
						method="post">
						<input type="hidden" name="matchId" value="${match.matchId}">

						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="firstTeamScore">${match.firstTeam.teamName}
									score</label> <input type="text" class="form-control"
									id="firstTeamScore" name="firstTeamScore"
									value="${match.firstTeamScore}"
									placeholder="Enter firstTeamScore">
							</div>
							<div class="form-group col-md-6">
								<label for="secondTeamScore">${match.secondTeam.teamName}
									score</label> <input type="text" class="form-control"
									id="secondTeamScore" name="secondTeamScore"
									value="${match.secondTeamScore}"
									placeholder="Enter secondTeamScore">
							</div>
						</div>

						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="location">Location</label> <input type="text"
									class="form-control" id="location" name="location"
									value="${match.location}" placeholder="Enter location">
							</div>
							<div class="form-group col-md-6">
								<label for="matchDateTime">Time</label> <input
									type="datetime-local" class="form-control" id="matchDateTime"
									name="matchDateTime" value="${match.matchDatetime}">
							</div>
						</div>

						<button type="submit" class="btn btn-primary btn-custom">Edit
							Match</button>
					</form>
					<form action="${pageContext.request.contextPath}/deleteMatch"
						method="post" style="margin-top: 15px;">
						<input type="hidden" name="matchId" value="${match.matchId}">
						<button type="submit" class="btn btn-danger btn-custom">Delete
							Match</button>
					</form>
				</div>
			</div>
			<div class="row mt-4">
				<div class="col-md-6">
					<div class="card">
						<div class="card-header">
							<h5>${match.firstTeam.teamName}event</h5>
						</div>
						<div class="card-body">
							<ul class="list-group">
								<c:forEach items="${eventMatch1}" var="event1">
									<li
										class="list-group-item d-flex justify-content-between align-items-center">
										${event1.player.playerName} - ${event1.event.eventName} -
										${event1.minute}
										<div>
											<button class="btn btn-warning btn-sm btn-custom">Edit</button>
											<form
												action="${pageContext.request.contextPath}/match/deleteEvent"
												method="post" style="display: inline;">
												<input type="hidden" name="matchId" value="${match.matchId}">
												<input type="hidden" name="playerId"
													value="${event1.player.playerId}"> <input
													type="hidden" name="eventId"
													value="${event1.event.eventId}"> <input
													type="hidden" name="minute" value="${event1.minute}">
												<button type="submit"
													class="btn btn-danger btn-sm btn-custom">Delete</button>
											</form>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="card">
						<div class="card-header">
							<h5>${match.secondTeam.teamName} event</h5>
						</div>
						<div class="card-body">
							<ul class="list-group">
								<c:forEach items="${eventMatch2}" var="event1">
									<li
										class="list-group-item d-flex justify-content-between align-items-center">
										${event1.player.playerName} - ${event1.event.eventName} -
										${event1.minute}
										<div>
											<button class="btn btn-warning btn-sm btn-custom">Edit</button>
											<button class="btn btn-danger btn-sm btn-custom">Delete</button>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>

		</div>
	</section>

	<%@ include file="../templates/footer.jsp"%>
</body>
</html>