<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!-- Google Font -->
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900&display=swap" rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" type="text/css">
<header class="header-section">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="ht-info">
                        <ul>
                        	<security:authorize access="!isAuthenticated()">
                            	<li><a href="${pageContext.request.contextPath }/login">Đăng nhập</a></li>
                            </security:authorize>
                            <li><a href="#">Liên hệ</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="ht-links">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-vimeo"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-google-plus"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="header__nav">
        <div class="container">
            <div class="row">
                <div class="col-lg-2"></div>
                <div class="col-lg-10">
                    <div class="nav-menu">
                        <ul class="main-menu">
                            <li><a href="${pageContext.request.contextPath }/home">Trang chủ</a></li>
                            <li><a href="#">Giải đấu</a>
                                <ul class="dropdown">
                                    <li><a href="${pageContext.request.contextPath }/league/list">Danh sách giải</a></li>
                                    <c:if test="${account.role.equals('LEAGUE')}">
                                    	<li><a href="${pageContext.request.contextPath }/league/create">Tạo giải mới</a></li>
                                    </c:if>
                                </ul>
                            </li>
                            <li><a href="${pageContext.request.contextPath }/team/all">Đội bóng</a></li>
                            <security:authorize access="isAuthenticated()">
        						<li><a href="">Tài khoản</a>
	                                <ul class="dropdown">
	                                	<li><a href="${pageContext.request.contextPath }/profile">Thông tin chung</a></li>
	                                	<c:if test="${account.role.equals('ADMIN') }">
	                                		<li><a href="${pageContext.request.contextPath }/admin/">Trang quản lý</a></li>
	                                	</c:if>
	                                	<c:if test="${account.role.equals('LEAGUE') }">
	                                    		<li><a href="${pageContext.request.contextPath }/league/dashboard">Quản lý các giải đấu</a></li>		
	                                    </c:if>
	                                	<c:if test="${account.role.equals('TEAM') }">
                                			<li><a href="${pageContext.request.contextPath }/team/home">Đội bóng của tôi</a></li>	
	                                	</c:if>
										<li>
											<form action="${pageContext.request.contextPath }/logout" method="post">
		                                    	<button type="submit">Đăng xuất</button>
		                                    </form>
										</li>
	                                </ul>
                            	</li>
    						</security:authorize>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas-open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </div>
</header>