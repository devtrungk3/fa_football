<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%@ include file="templates/header.jsp" %>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-8 mt-2">
                <h2 class="text-uppercase">Thêm thông tin đội bóng</h2>
                <div style="border-bottom: solid 1px red;" class="mb-3"></div>
                <form action="${pageContext.request.contextPath}/team/create" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Tên huấn luyện viên</label>
                        <input type="text" class="form-control" id="name"  name="coachName">
                    </div>
                    <div class="mb-3">
                        <label for="birth" class="form-label">Tên đội bóng</label>
                        <input type="text" class="form-control" id="birth"  name="teamName">
                    </div>
                    <button class="btn btn-secondary">Hủy</button>
                    <button type="submit" class="btn btn-primary">Thêm đội</button>
                </form>

            </div>

        </div>

    <%@ include file="templates/footer.jsp" %>
</body>
</html>