<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết Giải Đấu</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/league-detail-style.css"
	type="text/css">
</head>
<body>
	<%@ include file="../templates/header.jsp"%>

	<section class="breadcrumb-section set-bg"
		data-setbg="${pageContext.request.contextPath }/resources/img/breadcrumb-bg.jpg"
		style="background-image: url(${pageContext.request.contextPath }/resources/img/breadcrumb-bg.jpg);">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="bs-text">
						<h2>ID(${league.leagueId}) - ${league.leagueName}</h2>
						<h4 style="color: #EEEEEE; margin-bottom: 10px">
							${league.teamNumber} đội | <strong style="color: yellow">${league.format}</strong>
							| Bóng đá sân ${league.teamsize.size}
						</h4>
						<h5 style="color: #DDDDDD">${league.startDate}|
							${league.endDate}</h5>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="schedule-section spad"
		style="background-image: url(${pageContext.request.contextPath }/resources/img/match/match-bg.jpg);">
		<div class="container">
			<div class="tab rounded">
				<button class="tablinks" onclick="openTab(event, 'team')">Đội
					Bóng</button>
				<button class="tablinks" id="default-tablink"
					onclick="openTab(event, 'home')">Lịch Đấu</button>
				<button class="tablinks" onclick="openTab(event, 'statistical')">Thống
					Kê</button>
			</div>

			<!-- Tab content -->
			<div id="home" class="tabcontent rounded bg-light">
				<div class="container">
					<div class="row">
						<div class="col-12">
							<div class="schedule-text">
								<h4 class="st-title rounded">LỊCH THI ĐẤU</h4>
								<div class="d-flex justify-content-between mb-4">
									<c:if test="${role == 'ROLE_LEAGUE' }">
										<c:if test="${league.format == 'mixed'}">
												data-toggle="modal" data-target="#createGroupModal">
												Tạo Bảng Đấu</button>
										</c:if>
										<button class="btn btn-primary" id="addMatchButton"
											data-toggle="modal" data-target="#addMatchModal"
											<c:if test="${!canCreateMatches ||!owner}">disabled</c:if>>
											Thêm Trận Đấu</button>
									</c:if>
								</div>
								<ul class="nav nav-tabs mb-3" id="roundTabs" role="tablist">
									<li class="nav-item" role="presentation"><a
										class="nav-link active" id="all-matches-tab" data-toggle="tab"
										href="#all-matches" role="tab" aria-controls="all-matches"
										aria-selected="true"> Tất cả </a></li>
									<c:forEach var="stage" items="${sortedStages}">
										<li class="nav-item" role="presentation"><a
											class="nav-link" id="round-${stage.stageId}-tab"
											data-toggle="tab" href="#round-${stage.stageId}" role="tab"
											aria-controls="round-${stage.stageId}" aria-selected="false">
												${stage.stageName} </a></li>
									</c:forEach>
								</ul>

								<!-- Add trận -->
								<div class="modal fade" id="addMatchModal" tabindex="-1"
									role="dialog" aria-labelledby="addMatchModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="addMatchModalLabel">Thêm
													Trận Đấu</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<form id="addMatchForm"
												action="${pageContext.request.contextPath }/league/add-match"
												method="post">
												<input type="hidden" name="leagueId"
													value="${league.leagueId}">
												<div class="modal-body">
													<div class="form-group">
														<label for="stage">Vòng đấu</label> <select
															class="form-control" id="stage" name="stageId">
															<c:forEach var="stage" items="${stageList}">
																<option value="${stage.stageId}">${stage.stageName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="form-group">
														<label for="firstTeam">Đội 1</label> <select
															class="form-control" id="firstTeam" name="firstTeamId" required>
															<c:forEach var="team" items="${teams}">
																<option value="${team.teamId}">${team.teamName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="form-group">
														<label for="secondTeam">Đội 2</label> <select
															class="form-control" id="secondTeam" name="secondTeamId" required>
															<c:forEach var="team" items="${teams}">
																<option value="${team.teamId}">${team.teamName}</option>
															</c:forEach>
														</select>
													</div>
													<div class="form-group">
														<label for="matchDatetime">Thời gian</label> <input
															type="datetime-local" class="form-control"
															id="matchDatetime" name="matchDatetime" required>
													</div>
													<div class="form-group">
														<label for="location">Địa điểm</label> <input type="text"
															class="form-control" id="location" name="location"
															required>
													</div>
												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-dismiss="modal">Đóng</button>
													<button type="submit" class="btn btn-primary">Lưu</button>
												</div>
											</form>
										</div>
									</div>
								</div>

								<!-- Add bảng đấu -->
								<div class="modal fade" id="createGroupModal" tabindex="-1"
									role="dialog" aria-labelledby="createGroupModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="createGroupModalLabel">Tạo
													Bảng Đấu</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<form id="createGroupForm"
													action="${pageContext.request.contextPath }/league/addGroup"
													method="post">
													<input type="hidden" name="leagueId"
														value="${league.leagueId}">
													<div class="form-group">
														<label for="groupName">Tên bảng đấu:</label> <input
															list="existingGroups" id="groupName" name="groupName"
															placeholder="Nhập bảng đấu" required />
														<datalist id="existingGroups">
															<c:forEach var="group" items="${existingGroups}">
																<option value="${group}"></option>
															</c:forEach>
														</datalist>
													</div>
													<div class="form-group">
														<label for="teamsInGroup">Chọn các đội tham gia
															bảng</label> <select multiple class="form-control" id="teamIds"
															name="teamIds" required>
															<c:forEach var="team" items="${availableTeams}">
																<option value="${team.teamId}">${team.teamName}</option>
															</c:forEach>
														</select>
													</div>
												</form>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Đóng</button>
												<button type="submit" form="createGroupForm"
													class="btn btn-primary">Lưu Bảng Đấu</button>
											</div>
										</div>
									</div>
								</div>

								<!-- All -->
								<div class="tab-content" id="roundTabContent">
									<div class="tab-pane fade show active" id="all-matches"
										role="tabpanel" aria-labelledby="all-matches-tab">
										<div class="st-table">
											<table>
												<tbody>
													<c:forEach var="match" items="${sortedMatches}">
														<tr data-match-id="${match.matchId}"
															data-link="${pageContext.request.contextPath}/match/detail/${match.matchId}"
															onclick="location.href=this.getAttribute('data-link');">
															<td class="left-team">
																<h4>${match.firstTeam.teamName}</h4>
															</td>
															<td class="st-option">
																<div class="so-text">${match.location}</div> <c:choose>
																	<c:when test="${match.matchDatetime.isBefore(now)}">
																		<h4>${match.firstTeamScore}-
																			${match.secondTeamScore}</h4>
																	</c:when>
																	<c:otherwise>
																		<h4>VS</h4>
																	</c:otherwise>
																</c:choose>
																<div class="so-text">${match.matchDatetime}</div>
															</td>
															<td class="right-team">
																<h4>${match.secondTeam.teamName}</h4>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>

									<!-- Vòng Bảng -->
									<c:forEach var="stage" items="${sortedStages}">
										<c:if test="${stage.stageName eq 'Vòng bảng'}">
											<div class="tab-pane fade" id="round-${stage.stageId}"
												role="tabpanel" aria-labelledby="round-${stage.stageId}-tab">
												<c:forEach var="entry" items="${matchesByGroup}">
													<div class="group-section">
														<div class="text-center mb-4">
															<hr class="my-3" style="border-top: 2px solid #007bff;">
															<h4 class=" font-weight-bold text-primary">Bảng
																${entry.key}</h4>
														</div>
														<div class="st-table">
															<table>
																<tbody>
																	<c:forEach var="match" items="${entry.value}">
																		<tr data-match-id="${match.matchId}"
																			data-link="${pageContext.request.contextPath}/match/detail/${match.matchId}"
																			onclick="location.href=this.getAttribute('data-link');">
																			<td class="left-team">
																				<h4>${match.firstTeam.teamName}</h4>
																			</td>
																			<td class="st-option">
																				<div class="so-text">${match.location}</div> <c:choose>
																					<c:when test="${match.matchDatetime.isBefore(now)}">
																						<h4>${match.firstTeamScore}-
																							${match.secondTeamScore}</h4>
																					</c:when>
																					<c:otherwise>
																						<h4>VS</h4>
																					</c:otherwise>
																				</c:choose>
																				<div class="so-text">${match.matchDatetime}</div>
																			</td>
																			<td class="right-team">
																				<h4>${match.secondTeam.teamName}</h4>
																			</td>
																		</tr>
																	</c:forEach>
																</tbody>
															</table>
														</div>
													</div>
												</c:forEach>
											</div>
										</c:if>
									</c:forEach>

									<!-- Đây là các vòng đấu -->
									<c:forEach var="stage" items="${matchesByStage.keySet()}">
										<div class="tab-pane fade" id="round-${stage.stageId}"
											role="tabpanel" aria-labelledby="round-${stage.stageId}-tab">
											<div class="st-table">
												<table>
													<tbody>
														<c:forEach var="match" items="${matchesByStage[stage]}">
															<tr data-match-id="${match.matchId}"
																data-link="${pageContext.request.contextPath}/match/detail/${match.matchId}"
																onclick="location.href=this.getAttribute('data-link');">
																<td class="left-team">
																	<h4>${match.firstTeam.teamName}</h4>
																</td>
																<td class="st-option">
																	<div class="so-text">${match.location}</div> <c:choose>
																		<c:when test="${match.matchDatetime.isBefore(now)}">
																			<h4>${match.firstTeamScore}-
																				${match.secondTeamScore}</h4>
																		</c:when>
																		<c:otherwise>
																			<h4>VS</h4>
																		</c:otherwise>
																	</c:choose>
																	<div class="so-text">${match.matchDatetime}</div>
																</td>
																<td class="right-team">
																	<h4>${match.secondTeam.teamName}</h4>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="team" class="tabcontent rounded bg-light">
				<div class="container">
					<div class="row">
						<div class="col-lg-8 left-blog-pad">
							<div class="schedule-text">
								<h4 class="st-title rounded">DANH SÁCH ĐỘI BÓNG</h4>
								<div class="container">
									<div class="row">
										<c:forEach var="team" items="${teams}">
											<div class="col-md-3 mb-2" style="padding-left: 0;">
												<a href="${pageContext.request.contextPath}/team/detail/${team.teamId}" class="rounded bg-light py-3"
													style="border: 1px solid #cccccc; display: block; height: 100%;">
													<%-- <img
													src="${pageContext.request.contextPath}/resources/img/schedule/flag-1.jpg"
													style="display: block; margin: auto"> --%>
													<h4 class="text-center">${team.teamName}</h4>
												</a>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-4">
							<div class="schedule-sidebar">
								<%-- <div class="ss-widget">
									<div class="section-title sidebar-title">
										<h5>Thêm Đội</h5>
									</div>
									<form
										action="${pageContext.request.contextPath}/league/invite-team"
										method="POST">
										<input type="hidden" name="leagueId"
											value="${league.leagueId}"> <select
											name="teamManagerId" class="form-control">
											<c:forEach var="teamManager" items="${allTeamManagers}">
												<option value="${teamManager.accountId}">${teamManager.userName}</option>
											</c:forEach>
										</select>
										<button type="submit" class="btn btn-primary mt-2">Gửi
											lời mời</button>
									</form>
								</div> --%>
								<c:if test="${role == 'ROLE_LEAGUE' }">
									<div class="ss-widget mt-4">
										<div class="section-title sidebar-title">
											<h5>Duyệt Yêu Cầu</h5>
										</div>
										<table class="table table-striped">
											<c:choose>
												<c:when test="${league.stage.stageName == null}">
													<thead>
														<tr>
															<th>Quản lý đội</th>
															<th>Trạng thái</th>
															<th>Hành động</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="request" items="${pendingRequests}">
															<tr>
																<td>${request.teamManager.userName}</td>
																<td>${request.status}</td>
																<td>
																	<form
																		action="${pageContext.request.contextPath}/league/accept-request"
																		method="POST" style="display: inline;">
																		<input type="hidden" name="leagueId"
																			value="${request.league.leagueId}"> <input
																			type="hidden" name="teamManagerId"
																			value="${request.teamManager.email}">
																		<button type="submit" class="btn btn-success">Chấp
																			nhận</button>
																	</form>
																	<form
																		action="${pageContext.request.contextPath}/league/reject-request"
																		method="POST" style="display: inline;">
																		<input type="hidden" name="leagueId"
																			value="${request.league.leagueId}"> <input
																			type="hidden" name="teamManagerId"
																			value="${request.teamManager.email}">
																		<button type="submit" class="btn btn-danger">Từ
																			chối</button>
																	</form>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</c:when>
												<c:otherwise>
													<tbody>
														<tr>
															<td class="table-danger">Không thể thêm đội khi đã
																kích hoạt</td>
														</tr>
													</tbody>
												</c:otherwise>
											</c:choose>
										</table>
									</div>


									<div class="ss-widget mt-4">
										<div class="section-title sidebar-title">
											<h5>Kích hoạt giải đấu</h5>
										</div>
										<table class="table table-striped">
											<thead>
												<tr>
													<th>Trạng thái</th>
													<th>Hành động</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<c:if test="${league.stage.stageName  != null}">
														<td class="table-success">Đã kích hoạt</td>
													</c:if>
													<c:if test="${league.stage.stageName == null}">
														<td class="table-danger">Chưa kích hoạt</td>
													</c:if>
													<td>
														<form
															action="${pageContext.request.contextPath}/league/activate-league"
															method="POST" style="display: inline;">
															<input type="hidden" name="leagueId"
																value="${league.leagueId}"> <input type="hidden"
																name="stageId" value="1">
															<button type="submit" class="btn btn-success">Kích
																hoạt</button>
														</form>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</c:if>

								<c:if test="${role == 'ROLE_TEAM' }">
									<div class="ss-widget mt-4">
										<div class="section-title sidebar-title">
											<h5>Gửi yêu cầu tham gia giải đấu</h5>
										</div>
										<table class="table table-striped">
											<thead>
												<tr>
													<th>Hành động</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<form
															action="${pageContext.request.contextPath}/team/registerLeague/${league.leagueId}"
															method="POST" style="display: inline;">
															<input type="hidden" name="leagueId"
																value="${league.leagueId}"> <input type="hidden"
																name="stageId" value="1">
															<button type="submit" class="btn btn-success">Tham
																gia</button>
														</form>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div id="statistical" class="tabcontent rounded bg-light">
				<div class="container">
					<div class="row">
						<div class="col-lg-8 left-blog-pad">
							<div class="schedule-text">
								<h4 class="st-title rounded">THỐNG KÊ GIẢI ĐẤU</h4>
								<div class="st-table">
									<table class="table table-bordered text-center">
										<tbody>
											<tr>
												<td class="bg-info text-white">
													<h2>${teamCount}</h2>
													<h5>Số đội tham dự</h5>
												</td>
												<td class="bg-success text-white">
													<h2>${playersCount}</h2>
													<h5>Cầu thủ</h5>
												</td>
												<td class="bg-primary text-white">
													<h2>${footballMatchCount}</h2>
													<h5>Số trận</h5>
												</td>
											</tr>
											<tr>
												<td class="bg-danger text-white">
													<h2>${countRedCards != null ? countRedCards : 0}</h2>
													<h5>Số thẻ đỏ</h5>
												</td>
												<td class="bg-warning text-white">
													<h2>${countYellowCards != null ? countYellowCards : 0}</h2>
													<h5>Số thẻ vàng</h5>
												</td>
												<td class="bg-secondary text-white">
													<h2>${countGoals != null ? countGoals : 0}</h2>
													<h5>Số bàn thắng</h5>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>

						<div class="col-lg-4">
							<div class="schedule-sidebar">
								<!-- <div class="ss-widget">
									<div class="section-title sidebar-title">
										<h5>Bảng xếp hạng</h5>
									</div>
									<table class="table table-bordered text-center">
										<thead class="bg-dark text-white">
											<tr>
												<th scope="col">Đội bóng</th>
												<th scope="col">Số trận</th>
												<th scope="col">Điểm</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="teamTop" items="${listOfTeamStanding}">
												<tr>
													<td><h6>${teamTop.team.teamName}</h6></td>
													<td>${teamTop.standing.win + teamTop.standing.draw + teamTop.standing.loss}</td>
													<td>${teamTop.standing.win * 3 + teamTop.standing.draw}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div> -->
								<div class="ss-league">
									<div class="section-title sidebar-title">
										<h5>Top Ghi bàn</h5>
									</div>
									<table class="table table-bordered text-center">
										<thead class="bg-dark text-white">
											<tr>
												<th scope="col">Đội bóng</th>
												<th scope="col">Tên</th>
												<th scope="col">Số áo</th>
												<th scope="col">Số bàn</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="player" items="${listPlayerGoals}">
												<tr>
													<td class="left-team">
														<h6>${player[0]}</h6></td>
													<td>${player[1]}</td>
													<td>${player[2]}</td>
													<td>${player[3]}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<%@ include file="../templates/footer.jsp"%>

	<script
		src="${pageContext.request.contextPath }/resources/js/league-tabs.js"></script>
</body>
</html>