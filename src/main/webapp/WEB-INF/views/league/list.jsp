<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Danh sách Giải đấu</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath }/resources/css/league-style.css"
      type="text/css"
    />
  </head>
  <body>
    <%@ include file="../templates/header.jsp"%>

    <main
      id="leagues-container"
      style="
        background-image: url(${pageContext.request.contextPath}/resources/img/match/match-bg.jpg);
        padding: 20px;
      "
    >
      <div id="filter" class="container">
        <form class="league-search-box">
          <input type="hidden" name="tab" value="card" />
          <div class="container">
            <div class="row">
              <div class="col-sm-8 col-xs-12 league-filter">
                <div class="league-filter-box row">
                  <div class="col-sm-4">
                    <select onchange="updateFilters()"
                            class="form-control"
                            id="typeLeague"
                            name="typeLeague">
                      <option value="" ${param.format == '' ? 'selected' : ''}>Hình Thức</option>
                      <option value="knockout" ${param.format == 'knockout' ? 'selected' : ''}>Loại trực tiếp</option>
                      <option value="mixed" ${param.format == 'mixed' ? 'selected' : ''}>Hỗn hợp</option>
                    </select>
                  </div>
                  <div class="col-sm-4">
                    <select class="form-control" id="status" name="status" onchange="updateFilters()">
                      <option value="" ${param.status == '' ? 'selected' : ''}>Trạng Thái</option>
                      <option value="1" ${param.status == '1' ? 'selected' : ''}>Vòng bảng</option>
                      <option value="2" ${param.status == '2' ? 'selected' : ''}>Vòng 1/8</option>
                      <option value="3" ${param.status == '3' ? 'selected' : ''}>Vòng tứ kết</option>
                      <option value="4" ${param.status == '4' ? 'selected' : ''}>Vòng bán kết</option>
                      <option value="5" ${param.status == '5' ? 'selected' : ''}>Vòng chung kết</option>
                    </select>
                  </div>
                </div>	
              </div>
              <div class="col-sm-4 col-xs-10">
                <div class="search-input-group">
                  <input
                  onchange="updateFilters()"
                    type="text"
                    name="keyword"
                    class="form-control"
                    placeholder="Nhập tên giải đấu"
                  />
                  <span class="search-btn">
                    <button type="submit" class="btn btn-primary" onclick="updateFilters()">
                      <i class="fa fa-search"></i>
                    </button>
                  </span>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div id="leagues" class="container">
        <section id="league-list">
          <div class="container">
            <div class="row">
            <c:forEach var="league" items="${leagues}">
            <div class="col-md-4 mb-3">
                <a
                  href="${pageContext.request.contextPath }/league/view-detail?leagueId=${league.leagueId}"
                  class="tournament-card"
                >
                  <h4 class="card-title text-center">#ID(${league.leagueId}) - ${league.leagueName}</h4>
                  <p class="card-text text-center">
                    <i class="fa fa-users"></i
                    ><span class="icon-text">${league.teamNumber}</span>
                  </p>
                  <div class="row">
                    <div class="col-6">
                      <p class="card-text text-center">
                        <i class="fa fa-calendar-o"></i
                        ><span class="icon-text">${league.startDate}</span>
                      </p>
                    </div>
                    <div class="col-6">
                      <p class="card-text text-center">
                        <i class="fa fa-calendar-check-o"></i
                        ><span class="icon-text">${league.endDate}</span>
                      </p>
                    </div>
                  </div>
                  <p class="card-text text-center mt-3">
                    <span class="tournament-status status-ongoing">
                      <i class="fa fa-bolt"></i
                      ><span class="icon-text">${league.stage.stageName}</span>
                    </span>
                  </p>
                </a>
              </div>
            </c:forEach>
            </div>
          </div>
        </section>
      </div>
    </main>

    <%@ include file="../templates/footer.jsp"%>
    <script>
      function updateFilters() {
        var keywordValue = document.querySelector('input[name="keyword"]').value;
        var formatValue = document.getElementById('typeLeague').value;
        var statusValue = document.getElementById('status').value;
        var url = '${pageContext.request.contextPath}/league/list';
        var queryParams = [];

        if (keywordValue) {
          queryParams.push('keyword=' + encodeURIComponent(keywordValue));
        }
        if (formatValue) {
          queryParams.push('format=' + encodeURIComponent(formatValue));
        }
        if (statusValue) {
          queryParams.push('status=' + encodeURIComponent(statusValue));
        }

        if (queryParams.length > 0) {
          url += '?' + queryParams.join('&');
        }
        window.location.href = url;
      }
    </script>
  </body>
</html>
