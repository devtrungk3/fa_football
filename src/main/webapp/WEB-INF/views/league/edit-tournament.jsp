<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cập Nhật Giải Đấu</title>
<style type="text/css">
.panel-heading {
	border-bottom: 1px solid #dfe4ea;
}

.panel-footer {
	border-top: 1px solid #dfe4ea;
}
</style>
</head>
<body>
	<%@ include file="../templates/header.jsp"%>

	<section class="match-section"
		style="background-image: url(${pageContext.request.contextPath }/resources/img/match/match-bg.jpg); padding-top: 60px;">
		<div class="container">
			<div class="col-md-10 offset-md-1 rounded"
				style="background-color: #f8f9fa;">
				<form:form
					action="${pageContext.request.contextPath}/league/dashboard/update-tournament"
					method="post" modelAttribute="league">
					<div class="panel-heading text-center py-3">
						<h3>Cập Nhật Giải Đấu</h3>
					</div>
					<div class="panel-body py-2">
						<div class="row">
							<div class="col-md-10 offset-md-1">
								<div class="row">
									<div class="col-sm-6">
										<form:input type="hidden" path="leagueId" name="leagueId"
											value="${league.leagueId}" />
										<div class="form-group mb-3">
											<label class="label mb-1" for="leagueName">Tên giải
												đấu</label>
											<form:input type="text" path="leagueName" name="leagueName"
												id="leagueName" class="form-control"
												value="${league.leagueName}" placeholder="Nhập tên giải đấu" />
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="teamsize">Số lượng
												người mỗi đội</label>
											<form:select path="teamsize.teamsizeId" class="form-control"
												id="teamsize" name="teamsize">
												<c:forEach var="teamsizes" items="${listOfTeamsize}">
													<option value="${teamsizes.teamsizeId}"
														${league.teamsize.teamsizeId == teamsizes.teamsizeId ? 'selected' : ''}>${teamsizes.size}</option>
												</c:forEach>
											</form:select>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="startDate">Ngày bắt
												đầu</label>
											<form:input type="date" path="startDate" name="startDate"
												id="startDate" class="form-control"
												value="${league.startDate}" />
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group mb-3">
											<label class="label mb-1" for="teamNumber">Số đội
												tham gia</label>
											<form:input type="number" path="teamNumber" name="teamNumber"
												class="form-control" value="${league.teamNumber}"
												id="teamNumber" />
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="format">Hình thức thi
												đấu</label>
											<form:select path="format" class="form-control" id="format"
												name="format">
												<option value="knockout"
													${league.format == 'knockout' ? 'selected' : ''}>Loại
													trực tiếp</option>
												<option value="mixed"
													${league.format == 'mixed' ? 'selected' : ''}>Hỗn
													hợp</option>
											</form:select>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="endDate">Ngày kết thúc</label>
											<form:input type="date" path="endDate" name="endDate"
												id="endDate" class="form-control" value="${league.endDate}" />
										</div>
									</div>
								</div>
								<hr>
								<div class="form-group mb-3">
									<label class="label mb-1" for="stage">Trạng thái giải
										đấu</label>
									<form:select path="stage.stageId" class="form-control"
										id="stage" name="stage">
										<c:forEach var="stage" items="${listOfStages}">
											<option value="${stage.stageId}"
												${league.stage.stageId == stage.stageId ? 'selected' : ''}>${stage.stageName}</option>
										</c:forEach>
									</form:select>
								</div>
								<div class="form-group mb-3">
									<label class="label mb-1" for="description">Mô tả</label>
									<form:textarea path="description" class="form-control"
										id="description" name="description" rows="5"
										required="required"></form:textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer py-3">
						<div class="row">
							<div class="col-md-3 mx-auto text-center">
								<div class="row">
									<button type="submit" class="btn btn-danger text-center">Cập
										Nhật</button>
									<a href="${pageContext.request.contextPath }/league/dashboard"
										class="btn btn-secondary text-center text-white ml-2">Hủy
										Bỏ</a>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</section>

	<%@ include file="../templates/footer.jsp"%>
</body>
</html>