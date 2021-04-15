<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.or.ddit.common.model.PageVo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="x-ua-compatible" content="ie=edge">

<title>회원 리스트</title>

<%@ include file ="../common/common_lib.jsp" %>

<script type="text/javascript">

$(function(){
	$(".user").on("click", function(){
		
		console.log($(this).data("user_id"));
		
		var userid = $(this).data("user_id");
		
		$("#userid").val(userid);
		$("#frm").submit();
	});	
	
	$("#perPageNum").on("change", function(){
		
		var vl = $(this).val();
		
		$("#perPageNumId").val(vl);
		$("#frm2").attr("action", "${pageContext.request.contextPath}/login/pagingUser");
		$("#frm2").submit();
	});
	
	$("#perPageNum").val('${pageVo.pageSize}');
	
	
	
	$("#searchBtn").on("click", function(){
		var type = $("#searchType").val();
		var keyword = $("#data").val();
		var pageSize = $("#perPageNum").val();
		
		location.href = "/${pageContext.request.contextPath}/searchlogin/pagingUser?type=" + type + "&keyword=" + keyword + "&pageSize" + pageSize + "&page=1" ;
	});
	
});

</script>

</head>
<body class="hold-transition sidebar-mini">

	<form id="frm" action="${pageContext.request.contextPath}/member/user">
		<input type="hidden" id="userid" name="userid" value=""/>
	</form>
	<form id="frm2" action="${pageContext.request.contextPath}/userController.do">
		<input type="hidden" id="perPageNumId" name="pageSize" value="" />
	</form>
	
	
	

	<div class="wrapper">

	<%@ include file ="../common/common_navi.jsp" %>		

		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden; height: 750px;">
			<div class="content-wrapper" style="min-height: 584px;">
				<!-- Content Header (Page header) -->
				<section class="content-header">
					<div class="container-fluid">
						<div class="row md-2">
							<div class="col-sm-6">
								<h1>회원리스트</h1>
							</div>
							<div class="col-sm-6">
								<ol class="breadcrumb float-sm-right">
									<li class="breadcrumb-item">회원리스트</li>
									<li class="breadcrumb-item">목록</li>
								</ol>
							</div>
						</div>
					</div>
				</section>
				<!-- Main content -->
				<section class="content">
					<div class="card">
						<div class="card-header with-border">
							<a href="${pageContext.request.contextPath}/member/userRegistView" class="btn btn-primary">회원등록</a>
							<div id="keyword" class="card-tools" style="width: 550px;">
								<div class="input-group row">
									<!-- sort num -->
									<select class="form-control col-md-3" name="perPageNum" id="perPageNum">
										<option value="">정렬개수</option>
										<option value="3">3개씩</option>
										<option value="5">5개씩</option>
										<option value="7">7개씩</option>
									</select>
									<!-- search bar -->
									<select class="form-control col-md-3" name="searchType" id="searchType">
										<option value="">검색구분</option>
										<option value="i">아이디</option>
										<option value="n">이름</option>
										<option value="a">별명</option>
									</select> <input class="form-control" id="data" type="text" name="data" placeholder="검색어를 입력하세요." value=""> <span class="input-group-append">
										<button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search">
											<i class="fa fa-fw fa-search"></i>
										</button>
									</span>
									<!-- end : search bar -->
								</div>
							</div>
						</div>
						<div class="card-body" style="text-align: center;">
							<div class="row">
								<div class="col-sm-12">
									<table class="table table-bordered">
										<tbody>
											<tr>
												<th>아이디</th>
												<th>이름</th>
												<th>별명</th>
												<th>도로주소</th>
												<th>등록날짜</th>
											</tr>

													<!-- 
													아래 data-userid="${user.userid} 이거는 user.userid이 값이
													위에 스크립트 var userid = $(this).data("userid");에서 데이터 안에 들어있는 "userid" 이게 그 위에 값이 들어간거  
													-->

											<c:if test="${userList != null }">
												<c:forEach items="${userList}" var="user">
													<tr class="user" data-user_id="${user.userid}">
														<td>${user.userid}</td>
														<td>${user.usernm}</td>
														<td>${user.alias}</td>
														<td>${user.addr1}</td>
														<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd"/></td>
													</tr>
												</c:forEach>	
											</c:if>
											
										</tbody>
									</table>
								</div>
								<!-- col-sm-12 -->
							</div>
							<!-- row -->
						</div>
						<!-- card-body -->
						 <div class="card-footer">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/login/pagingUser?page=1&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-double-left"></i></a></li>
									
									<c:choose>
										<c:when test="${pageVo.getPage()-1 <= 1 }">
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/login/pagingUser?page=1&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-left"></i></a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/login/pagingUser?page=${pageVo.getPage()-1}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-left"></i></a></li>
										</c:otherwise>
									</c:choose>
									
									<c:forEach begin="1" end="${pagination}" var="i">
										<c:choose>
											<c:when test="${pageVo.getPage() == i }">
												<li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/login/pagingUser?page=${i}&pageSize=${pageVo.getPageSize()}">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/login/pagingUser?page=${i}&pageSize=${pageVo.getPageSize()}">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									<c:choose>
										<c:when test="${pageVo.getPage()+1 > pagination }">
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/login/pagingUser?page=${pageVo.getPage()}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-right"></i></a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/login/pagingUser?page=${pageVo.getPage()+1}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-right"></i></a></li>
										</c:otherwise>
									</c:choose>
									
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/login/pagingUser?page=${pagination}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-double-right"></i></a></li>
								
								</ul>
							</nav>
						</div>
						<!-- card-footer -->
						
						
					</div>
					<!-- card  -->
				</section>
			</div>
		</div>
	</div>
	<!-- ./wrapper -->

	<%@ include file ="../common/common_footer.jsp" %>
	<%@ include file ="../common/common_lib_js.jsp" %>
	

</body>
</html>