<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<html>

<head>
    <title>Trang cá nhân</title>
</head>
<body>
<%@include file="templates/header.jsp" %>

<div class="container mt-4">
    <hr class="mt-0 mb-4">
    <div class="row">
        <div class="col-xl-12">
            <c:if test="${message != null }">
           		<h4>${message }</h4>
           	</c:if>
            <!-- Account details card-->
            <div class="card mb-4">
                <div class="card-header">Thông tin chi tiết</div>
                <div class="card-body">
                    <form:form action="${pageContext.request.contextPath }/profile/update" method="post" modelAttribute="account">
                        <!-- Form Group (userName)-->
                        <div class="form-group">
                            <label class="small mb-1" for="userName">Tên người dùng</label>
                            <form:input class="form-control" path="userName" type="text"/>
                        </div>
                        <!-- Form Group (email address)-->
                        <div class="form-group">
                            <label class="small mb-1" for="emailAddress">Địa chỉ email</label>
                            <form:input class="form-control" path="email" type="email" readonly="true"/>
                        </div>
                        <div class="form-row">
                            <!-- Form Group (phone number)-->
                            <div class="form-group col-md-6">
                                <label class="small mb-1" for="inputPhone">Số điện thoại</label>
                                <form:input class="form-control" path="phone" type="tel" pattern="[0-9]{10}"/>
                            </div>
                            <!-- Form Group (location)-->
                            <div class="form-group col-md-6">
                                <label class="small mb-1" for="inputLocation">Địa chỉ</label>
                                <form:input class="form-control" path="address" type="text"/>
                            </div>
                        </div>

                        <!-- Form Group (role)-->
                        <div class="form-group">
                            <label class="small mb-1" for="role">Role</label>
                            <form:input class="form-control" path="role" type="text" readonly="true"/>
                        </div>
                        <!-- Save changes button-->
                        <button class="btn btn-primary" type="submit">Lưu thay đổi</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="templates/footer.jsp" %>
</body>
</html>
