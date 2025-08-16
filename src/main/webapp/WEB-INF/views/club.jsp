<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<%@ include file="templates/header.jsp"%>
	<div class="container">
		<div class="container">
			<div class="content">
				<div class="teams">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Name</th>
								<th>Coach</th>
								<th>League</th>
								<th>Manager</th>
								<th>Standing</th>
								<th>Players</th>
								<th>Details</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${teamList}" var="team">
								<tr>
									<td><c:out value="${team.teamId}" /></td>
									<td><c:out value="${team.teamName}" /></td>
									<td><c:out value="${team.coachName}" /></td>
									<td><c:out value="${team.league.leagueName}" /></td>
									<td><c:out value="${team.manager.userName}" /></td>
									<td><c:out value="${team.standing.standingName}" /></td>
									<td><a class="btn btn-outline-danger btn-sm" href="#" role="button">See more</a></td>
									<td><a class="btn btn-danger btn-sm" href="#" role="button">See more</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="templates/footer.jsp"%>
</body>
</html>
