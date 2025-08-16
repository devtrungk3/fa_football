<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <h2 class="text-uppercase">Chỉnh sửa cầu thủ</h2>
            <div style="border-bottom: solid 1px red;" class="mb-3"></div>
            <form action="${pageContext.request.contextPath}/player/edit/${player.playerId}" method="post">
                <div class="mb-3">
                  <label for="name" class="form-label">Tên cầu thủ</label>
                  <input type="text" class="form-control" id="name" value="${player.playerName}" name="name">
                </div>
                <div class="mb-3">
                  <label for="birth" class="form-label">Ngày sinh</label>
                  <input type="text" class="form-control" id="birth" value="${player.birth}" name="birth">
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Số điện thoại</label>
                    <input type="text" class="form-control" id="exampleInputPassword1" value="${player.phone}" name="phone">
                  </div>
                  <div class="mb-3">
                    <label for="num" class="form-label">Số áo</label>
                    <input type="number" class="form-control" id="num" value="${player.shirtNumber}" name="num">
                  </div>
                  <button class="btn btn-secondary">Hủy</button>
                <button type="submit" class="btn btn-primary">Chỉnh sửa</button>
              </form>

          </div>
            
        </div>

    </div>
	<%@ include file="templates/footer.jsp" %>
</body>
</html>