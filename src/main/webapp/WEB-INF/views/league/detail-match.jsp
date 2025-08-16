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
				<div class="col-lg-8">
					<div class="overview-match bg-light p-3 rounded">
						<div class="text-center border-bottom pb-2">
							<h3 class="text-uppercase fw-500">Giải đấu bóng đá world cup
								2026</h3>
							<span>Thời gian</span>
						</div>
						<div>
							<table class="table table-borderless mt-3 mb-0">
								<tbody>
									<tr class="text-center">
										<td class="text-right pt-3">
											<h4 class="fw-500 pt-1">Công An Hà Nội</h4>
										</td>
										<td>
											<h2 class="primary-red-color fw-600">0 - 0</h2>
											<h6>Hết trận đấu</h6>
										</td>
										<td class="text-left pt-3">
											<h4 class="fw-500 pt-1">Sông Lam Nghệ An</h4>
										</td>
									</tr>
								</tbody>
							</table>
							<table class="table table-borderless m-0">
								<tbody>
									<tr class="text-center">
										<td class="text-right pt-3">
											<div class="player-goal-list">
												<p>Michel Jackson 77'</p>
											</div>
										</td>
										<td><i class="fa fa-futbol-o mt-3"></i></td>
										<td class="text-left pt-3">
											<div class="player-goal-list">
												<p>Michel Jackson 77'</p>
												<p>Michel Jackson 77'</p>
												<p>Michel Jackson 77'</p>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="footer-stadium text-center">Sân: Mỹ Đình</div>
					</div>
					<div class="detail-match bg-light p-3 rounded mt-3">
						<div class="text-center border-bottom pb-2">
							<h3 class="text-uppercase fw-500">chi tiết trận đấu</h3>
						</div>
						<div id="body-detail-match" class="pt-2">
							<div id="content-detail-match">
								<article class="player-goal clearfix">
									<div class="left">
										<span class="player-name text-uppercase text-secondary">Michel
											Jackson <b style="color: #000;">(2 - 0)</b>
										</span> <i class="fa fa-futbol-o mx-2"></i>
									</div>
									<div class="minute">1'</div>
								</article>
								<article class="player-goal clearfix">
									<div class="left">
										<span class="player-name text-uppercase text-secondary">Michel
											Jackson <b style="color: #000;">(2 - 0)</b>
										</span> <i class="fa fa-futbol-o mx-2"></i>
									</div>
									<div class="minute">1'</div>
								</article>
								<article class="player-goal clearfix">
									<div class="right">
										<i class="fa fa-futbol-o mx-2"></i> <span
											class="player-name text-uppercase text-secondary">Michel
											Jackson <b style="color: #000;">(2 - 0)</b>
										</span>
									</div>
									<div class="minute">1'</div>
								</article>
								<article class="player-goal clearfix">
									<div class="right">
										<i class="fa fa-futbol-o mx-2"></i> <span
											class="player-name text-uppercase text-secondary">Michel
											Jackson <b style="color: #000;">(2 - 0)</b>
										</span>
									</div>
									<div class="minute">1'</div>
								</article>
								<div class="line"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="statistics">123</div>
					<div class="achievenment">123</div>
				</div>
			</div>
		</div>
	</section>

	<%@ include file="../templates/footer.jsp"%>
</body>
</html>