<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Biểu mẫu sự kiện</title>
</head>
<body>
<%@include file="templates/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Sự kiện</h1>
    </div>

    <div class="col-12">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Sự kiện</h6>
            </div>
            <div class="card-body">
                <form:form modelAttribute="event" action="#" method="post">
                    <div class="mb-3">
                        <label class="form-label" for="eventName">Tên sự kiện</label>
                        <form:input class="form-control" id="eventName" path="eventName" type="text" placeholder="Nhập tên sự kiện..."/>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary col-sm-auto">OK</button>
                    </div>
                </form:form>
            </div>
    </div>

    </div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->

<%@include file="templates/footer.jsp" %>
</body>
</html>