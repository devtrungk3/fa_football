<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tạo Giải Đấu Mới</title>
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
			<div class="col-md-10 offset-md-1 rounded" style="background-color: #f8f9fa;">
				<form:form action="${pageContext.request.contextPath}/league/save" method="post" modelAttribute="league">
					<div class="panel-heading text-center py-3">
						<h3>Tạo Giải</h3>
					</div>
					<div class="panel-body py-2">
						<div class="row">
							<div class="col-md-10 offset-md-1">
								<div class="row">
									<div class="col-sm-6">
										<div class="form-group mb-3">
											<label class="label mb-1" for="leagueName">Tên giải
												đấu</label> 
											<form:input path="leagueName" type="text" name="leagueName" id="leagueName" class="form-control" placeholder="Nhập tên giải đấu" required="required"/>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="teamsize">Số lượng
												người mỗi đội</label> 
											<form:select path="teamsize.teamsizeId" class="form-control" id="teamsize"
												name="teamsizeId">
												<form:options items="${teamsizes}" itemValue="teamsizeId" itemLabel="size"/>
											</form:select>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="startDate">Ngày bắt
												đầu</label> 
												<form:input path="startDate" type="date" name="startDate" id="startDate"
												class="form-control" required="required"/>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="form-group mb-3">
											<label class="label mb-1" for="teamNumber">Số đội
												tham gia</label> 
												<form:input path="teamNumber" type="number" name="teamNumber"
												class="form-control" value="2" id="teamNumber" required="required"/>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="format">Hình thức thi
												đấu</label> 
											<form:select path="format" class="form-control" id="format" name="format">
												<form:option value="knockout" selected="selected">Loại trực tiếp</form:option>
												<form:option value="mixed">Bảng đấu</form:option>
											</form:select>
										</div>
										<div class="form-group mb-3">
											<label class="label mb-1" for="endDate">Ngày kết thúc</label>
											<form:input path="endDate" type="date" name="endDate" id="endDate"
												class="form-control" required="required"/>
										</div>
									</div>
								</div>
								<hr>
								<%-- <div class="form-group mb-3">
									<label class="label mb-1" for="stage">Trạng thái giải
										đấu</label> 
										<form:select path="stage.stageId" class="form-control" id="stage"
												name="stage">
											<form:option value="" label="Select stage"/>
											<form:options items="${stages}" itemValue="stageId" itemLabel="stageName"/>
										</form:select>
								</div> --%>
								<div class="form-group mb-3">
									<label class="label mb-1" for="description">Mô tả</label>
									<form:textarea path="description" class="form-control" id="description"
										name="description" rows="5" required="required"></form:textarea>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer py-3">
						<div class="row">
							<div class="col-md-3 mx-auto text-center">
								<input type="submit" value="${league.leagueId == 0 ? 'Create' : 'Update'}" class="btn btn-danger text-center w-50"></input>
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