<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<%@ include file="templates/header.jsp" %>
	<section class="hero-section set-bg" data-setbg="${pageContext.request.contextPath }/resources/img/1735126.webp">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="hs-item">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="hs-text">
                                        <h2>Tổ chức và tham gia giải đấu trực tuyến</h2>
                                        <a href="${pageContext.request.contextPath }/league/create" class="primary-btn">Tạo giải đấu</a>
                                        <a href="${pageContext.request.contextPath }/league/list" class="primary-btn">Tham gia giải đấu</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
	<%@ include file="templates/footer.jsp" %>
</body>
</html>