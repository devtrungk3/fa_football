<%--
  Created by IntelliJ IDEA.
  User: quoc
  Date: 8/16/2024
  Time: 11:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <title>${title }</title>
    <link href="${pageContext.request.contextPath}/resources/admin-assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <%@include file="header.jsp" %>


<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800 mb-3">Bảng</h1>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3 ">
            <h6 class="m-0 font-weight-bold text-primary ">${title}</h6>
        </div>
        <div class="card-body">
        	<c:if test="${message != null }">
        		<p>${message }</p>
        	</c:if>
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                    <tr>
                        <c:forEach items="${columnNames}" var="colName">
                            <th>${colName}</th>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${data}" var="d" varStatus="s">
                        <tr>
                            <c:forEach items="${d}" var="col">
                                <td>${col}</td>
                            </c:forEach>
                           	<c:if test="${columnNames.get(columnNames.size()-2).equals('sửa') }">
                        		<td><a class="btn btn-warning" href="${pageContext.request.contextPath}/admin/${nameOfTable}/update/${d[0]}">Sửa</a></td>
                        	</c:if>
                           	<c:if test="${columnNames.get(columnNames.size()-1).equals('xóa') }">
                           		<form action="${pageContext.request.contextPath}/admin/${nameOfTable}/delete" method="post">
                           			<input name="${id }" value="${d[0] }" type="hidden">
                           			<td><button class="btn btn-danger" type="submit">Xóa</button></td>
                           		</form>
                       			
                        	</c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${pages.hasPrevious() }">
                	<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/${nameOfTable}/table?page=${currentPage-1}&size=${currentSize}">Trước</a>
                </c:if>
                
                <c:if test="${pages.hasNext() }">
                   	<a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/${nameOfTable}/table?page=${currentPage+1}&size=${currentSize}">Sau</a>
                </c:if>
               
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
    <%@include file="footer.jsp" %>
</body>
</html>
