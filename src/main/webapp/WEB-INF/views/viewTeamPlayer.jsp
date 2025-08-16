<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="UTF-8">
      <title>Insert title here</title>
    </head>

    <body>
      <%@ include file="templates/header.jsp" %>
        <div class="container">
          <div class="row mt-4 justify-content-between">
            <div class="col-8">
              <h2 class="text-uppercase">Danh sách cầu thủ</h2>
            </div>
            <div class="col-2">
              <a href="${pageContext.request.contextPath}/player/addPlayer" style="text-decoration: none;">
                <button type="button" class="btn btn-outline-primary">Thêm cầu thủ</button>
              </a>
            </div>
          </div>
          <div style="border-bottom: solid 1px red;"></div>
          <div class="row">
            <c:forEach items="${players }" var="player">
              <div class="col-4 mt-3">
                <div class="card" style="background-color: aliceblue;">
                  <div class="card-body">
                    <h4 class="card-title">${player.playerName}</h4>
                  </div>
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item">Số áo: ${player.shirtNumber}</li>
                    <li class="list-group-item">Ngày sinh: ${player.birth}</li>
                    <li class="list-group-item">Số điện thoại: ${player.phone}</li>
                  </ul>
                  <div class="card-body">
                    <a href="${player.playerId}" class="card-link">Xem</a>
                    <a href="edit/${player.playerId}" class="card-link">Sửa</a>
                    <a href="delete/${player.playerId}" class="card-link">Xóa</a>
                  </div>
                </div>
              </div>
            </c:forEach>
          </div>


          <%@ include file="templates/footer.jsp" %>
    </body>

    </html>