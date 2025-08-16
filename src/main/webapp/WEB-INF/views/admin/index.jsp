<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<title>Quản lý</title>
<%@include file="templates/header.jsp"%>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Tổng quan</h1>
                </div>

                <!-- Content Row -->
                <div class="row">

                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Tài khoản</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${accountNum }</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-user fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                           Giải đấu</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${leagueNum }</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-trophy fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                           Đội bóng</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${teamNum }</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-futbol fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                           Trận đấu</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">${matchNum }</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-users fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            
            	<!-- Content Row -->
                <div class="row">

                    <!-- Area Chart -->
                    <div class="col-xl-8 col-lg-7">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Tài khoản được tạo trong năm</h6>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-area">
                                    <canvas id="myAreaChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pie Chart -->
                    <div class="col-xl-4 col-lg-5">
                        <div class="card shadow mb-4">
                            <!-- Card Header - Dropdown -->
                            <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Trạng thái giải đấu</h6>
                            </div>
                            <!-- Card Body -->
                            <div class="card-body">
                                <div class="chart-pie pt-4 pb-2">
                                    <canvas id="myPieChart"></canvas>
                                </div>
                                <div class="mt-4 text-center small">
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-primary"></i>Đã diễn ra
                                        </span>
                                    <span class="mr-2">
                                            <i class="fas fa-circle text-success"></i>Đang diễn ra
                                        </span>
                                    <span class="mr-2">
                                            <i class="fas fa-circle text-info"></i>Sắp diễn ra
                                        </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
           	    <div class="row mb-4">
			        <div class="col-xl-6 col-sm-12">
			            <div class="card shadow">
			                <div class="card-header">
			                    <h6 class="m-0 font-weight-bold text-primary">Phân quyền người dùng</h6>
			                </div>
			                <div class="card-body" >
			                    <div class="chart-pie">
			                        <canvas id="myPieChart2"></canvas>
			                    </div>
			                </div>
			            </div>
			        </div>
			        <div class="col-xl-6 col-sm-12">
			            <div class="card shadow">
			                <div class="card-header">
			                    <h6 class="m-0 font-weight-bold text-primary">Số lượng trận đấu trong năm</h6>
			                </div>
			                <div class="card-body">
			                    <div class="chart-pie">
			                        <canvas id="myBarChart3"></canvas>
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

<%@include file="templates/footer.jsp"%>
<!-- Page level plugins -->
<script src="${pageContext.request.contextPath}/resources/admin-assets/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script>
	var leagueBefore = "${leagueBefore}";
	var leaguePresent = "${leaguePresent}";
	var leagueAfter = "${leagueAfter}";
    var data = [0,0,0,0,0,0,0,0,0,0,0,0];
    <c:forEach var="entry" items="${accountsByMonth}">
    	data["${entry.key}"-1] = "${entry.value}";
	</c:forEach>
</script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@0.7.0/dist/chartjs-plugin-datalabels.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin-assets/js/demo/chart-area-demo.js"></script>
<script src="${pageContext.request.contextPath}/resources/admin-assets/js/demo/chart-pie-demo.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-moment@0.1.1"></script>
<script src="${pageContext.request.contextPath}/resources/admin-assets/js/draw-chart.js"></script>
<script>
    fetch('${pageContext.request.contextPath}/admin/account/api/account-role-percentage')
        .then(response => response.json())
        .then(data => {
            roles = data.roles;
            percentages = data.percentages;
            draw_role_percentage_chart(roles, percentages);
        });

    fetch('${pageContext.request.contextPath}/admin/match/api/count/month-of-year')
        .then(response => response.json())
        .then(data => {
            draw_match_count(data.time, data.matchCounts, data.unit, 'myBarChart3');
        });
</script>


</body>

</html>
