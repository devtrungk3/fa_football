<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Giải đấu</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/league-dashboard.css"
	type="text/css">
</head>
<body>
	<%@ include file="../templates/header.jsp"%>

	<section class="schedule-section spad"
		style="background-image: url(${pageContext.request.contextPath }/resources/img/match/match-bg.jpg);">
		<div class="container-fluid">
			<div class="row">
				<div class="tab rounded">
					<button class="tablinks rounded"
						onclick="openTab(event, 'dashboard')" id="defaultOpen">Dashboard</button>
					<button class="tablinks rounded" onclick="openTab(event, 'list')">Danh
						sách Giải đấu</button>
				</div>

				<div id="dashboard" class="tabcontent bg-light rounded">
					<div class="container">
						<div class="row">
							<div class="col-md-8">
								<table class="table table-borderless text-center">
									<thead>
										<tr>
											<th colspan="3"><h3>Thống Kê Của Bạn</h3></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>
												<div class="rounded bg-info text-white p-2">
													<h4>${numberOfLeagues}</h4>
													<h5>Giải đấu đã tạo</h5>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div id="list" class="tabcontent bg-light rounded">
					<div class="container">
						<div class="row">
							<h3 class="py-3">Danh sách Giải Đấu Của Bạn</h3>
							<table class="table table-hover table-striped table-bordered">
								<thead class="bg-dark text-white">
									<tr>
										<th>Tên Giải</th>
										<th>Số Đội</th>
										<th>Hình Thức</th>
										<th>Ngày Bắt Đầu</th>
										<th>Ngày Kết Thúc</th>
										<th>Mô Tả</th>
										<th>Số Người Mỗi Đội</th>
										<th>Thao Tác</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="league" items="${listOfLeagues}">
										<tr>
											<td>${league.leagueName}</td>
											<td>${league.teamNumber}</td>
											<td>${league.format}</td>
											<td>${league.startDate}</td>
											<td>${league.endDate}</td>
											<td>${league.description}</td>
											<td>${league.teamsize.size}</td>
											<td><a
												href="${pageContext.request.contextPath }/league/dashboard/edit-tournament?leagueId=${league.leagueId}"
												class="btn btn-sm btn-warning mr-2"> <i
													class="fa fa-pencil-square-o"></i>
											</a> <a
												href="${pageContext.request.contextPath }/league/dashboard/delete-tournament?leagueId=${league.leagueId}"
												class="btn btn-sm btn-danger"> <i class="fa fa-trash"></i>
											</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="../templates/footer.jsp"%>
	<script
		src="${pageContext.request.contextPath }/resources/js/league-tabs-dashboard.js"></script>
</body>
</html>