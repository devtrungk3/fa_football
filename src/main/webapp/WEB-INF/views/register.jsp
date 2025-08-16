<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<html>
<head>
    <title>Đăng ký</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="resources/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="resources/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="resources/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="resources/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="resources/css/style.css" type="text/css">
	<link rel="stylesheet" href="resources/css/login-style.css" type="text/css">
</head>
<body>
<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                <div class="wrap d-md-flex">
                    <div class="img" style="background-image: url(resources/img/register-image.webp);">
                    </div>
                    <div class="login-wrap p-4 p-md-5">
                        <div class="d-flex">
                            <div class="w-100">
                                <h3 class="mb-4">Đăng ký</h3>
                            </div>
                            <div class="w-100">
                                <p class="social-media d-flex justify-content-end">
                                    <a href="#" class="social-icon d-flex align-items-center justify-content-center"><span class="fa fa-facebook"></span></a>
                                    <a href="#" class="social-icon d-flex align-items-center justify-content-center"><span class="fa fa-twitter"></span></a>
                                </p>
                            </div>
                        </div>
                        <form:form action="${pageContext.request.contextPath }/register" id="form" class="signin-form" method="post" modelAttribute="account">
                            <div class="form-group mb-3">
                                <label class="label" for=userName>Họ tên</label>
                                <form:input type="text" path="userName" id="userName" class="form-control" placeholder="Họ tên" required="required" />
                            </div>
                            <div class="form-group mb-3">
                                <label class="label" for="email">Email</label>
                                <form:input type="email" path="email" id="email" class="form-control" placeholder="Email" required="required" />
                            </div>
                            <div class="form-group mb-3">
                                <label class="label" for="address">Địa chỉ</label>
                                <form:input type="text" path="" id="address" class="form-control" placeholder="Địa chỉ" />
                            </div>
                            <div class="form-group mb-3">
                                <label class="label" for="phone">Số điện thoại</label>
                                <form:input type="number" path="phone" id="phone" class="form-control" placeholder="0123456789" required="required" />
                            </div>
                            <div class="form-group mb-3">
                                <label class="label" for="password">Mật khẩu</label>
                                <form:input type="password" path="password" id="password" class="form-control" placeholder="Mật khẩu" required="required" />
                            </div>
                            <div class="form-group mb-3">
                                <label class="label" for="rppassword">Xác nhận mật khẩu</label>
                                <input type="password" id="rppassword" class="form-control" placeholder="Xác nhận mật khẩu" required>
                            </div>
                            <div class="form-group mb-3">
					            <label class="label" for="role">Vai trò</label>
					            <form:select path="role" id="role">
					            	<form:option label="Quản lý giải đấu" value="LEAGUE"/>
					            	<form:option label="Quản lý đội bóng" value="TEAM"/>
					            </form:select>                        
					        </div>
                            <div class="form-group">
                                <button type="submit" class="form-control btn btn-primary rounded submit px-3">Đăng ký</button>
                            </div>
                        </form:form>
                        <p class="text-center">Đã có tài khoản <a href="${pageContext.request.contextPath}/login">Đăng nhập </a>ngay!</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="${pageContext.request.contextPath }/resources/js/auth.js"></script>
</body>
</html>
