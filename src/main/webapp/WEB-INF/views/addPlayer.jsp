<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
		</head>

		<body>
			<%@ include file="templates/header.jsp" %>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-8 mt-2">
							<h2 class="text-uppercase">Thêm cầu thủ</h2>
							<div style="border-bottom: solid 1px red;" class="mb-3"></div>
							<form action="addPlayer" method="post">
								<div class="mb-3">
									<label for="name" class="form-label">Tên cầu thủ</label>
									<input type="text" class="form-control" id="name" placeholder="Nguyen Van A"
										name="name">
								</div>
								<div class="mb-3">
									<label for="birth" class="form-label">Ngày sinh</label>
									<input type="text" class="form-control" id="birth" placeholder="01/01/2003"
										name="birth">
								</div>
								<div class="mb-3">
									<label for="phone" class="form-label">Số điện thoại</label>
									<input type="text" class="form-control" id="exampleInputPassword1"
										placeholder="0912430332" name="phone">
								</div>
								<div class="mb-3">
									<label for="num" class="form-label">Số áo</label>
									<input type="number" class="form-control" id="num" placeholder="99" name="num">
								</div>
								<button class="btn btn-secondary" onclick="history.back()">Hủy</button>
								<button type="submit" class="btn btn-primary">Thêm</button>
							</form>

						</div>

					</div>
					<%@ include file="templates/footer.jsp" %>
		</body>

		</html>