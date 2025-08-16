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
    <div class="container mt-2">
      <h2 class="text-uppercase">Thông tin cầu thủ</h2>
      <div style="border-bottom: solid 1px red;"></div>
      <div class="row d-flex justify-content-center mt-4">
        <div class="col-6">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title">${player.playerName}</h4>
              <p class="card-text">Số áo: ${player.shirtNumber}</p>
              <p class="card-text">Ngày sinh: ${player.birth}</p>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item">Số bàn thắng: ${goal}</li>
              <li class="list-group-item">Số thẻ đỏ: ${redCard}</li>
              <li class="list-group-item">Số thẻ vàng: ${yellowCard}</li>
            </ul>
            <div class="card-body">
              <a href="#" class="card-link">${player.phone}</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <%@ include file="templates/footer.jsp" %>
</body>
</html>