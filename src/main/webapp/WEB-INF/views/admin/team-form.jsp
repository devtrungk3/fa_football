<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="templates/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Team</h1>
    </div>

    <div class="col-12">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-primary">Create Team</h6>
            </div>
            <div class="card-body">
                <form:form modelAttribute="team">
                    <div class="mb-3">
                        <label class="form-label" for="teamName">Team Name</label>
                        <form:input class="form-control" id="teamName" path="teamName" type="text" placeholder="Enter Team Name"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="coachName">Coach Name</label>
                        <form:input class="form-control" id="coachName" path="coachName" type="text" placeholder="Enter Coach Name"/>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="league">League</label>
                        <form:select class="form-select" id="league" path="league.leagueId">
                            <form:option value="" label="Select League"/>
                            <c:forEach var="league" items="${leagues}">
                                <form:option value="${league.leagueId}" label="${league.leagueName}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="manager">Manager</label>
                        <form:select class="form-select" id="manager" path="manager.email">
                            <form:option value="" label="Select Manager"/>
                            <c:forEach var="manager" items="${managers}">
                                <form:option value="${manager.email}" label="${manager.userName}"/>
                            </c:forEach>
                        </form:select>
                    </div>
                    <div class="d-flex justify-content-end">
                        <button type="submit" class="btn btn-primary col-sm-auto">Submit</button>
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