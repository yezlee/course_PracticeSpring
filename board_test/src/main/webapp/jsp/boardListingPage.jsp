<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>게시판</title>

    <%@ include file ="/common/common_lib.jsp" %>
    
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">
  
    <!-- Font Awesome -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">	

  <style type="text/css">
  
	.container{
		margin-left: 0;
		padding-left: 0;
	}
	
	.table tr{
		text-align : center;
	}
	.table tr th:nth-child(1){
		width : 15%;
	}
	.table tr th:nth-child(2){
		width : 40%;
	}
	.table tr th:nth-child(3){
		width : 20%;
	}
	.table tr th:nth-child(4){
		width : 25%;
	}
	  
  
  </style>  
      
  	<script type="text/javascript">

  		$(function() {
			$(".post").on("click", function(){
				
				var post_no = $(this).data("post_no")
				var bd_no = $(this).data("bd_no")
				var writer = $(this).data("writer")
				
				$("#post_no").val(post_no);
				$("#bd_no").val(bd_no);
				$("#writer").val(writer);
				
				$("#frm").submit();
			});
			
			$("#writePostbtn").on("click", function(){
				
				
				
			})
			
		});
  		
  	</script>  
  </head>

  <body>
  	
    <%@ include file ="/common/common_top_nav.jsp" %>
    
    <form id="frm" action="${pageContext.request.contextPath}/postController.do">
		<input type="hidden" id="post_no" name="post_no" value=""/>
		<input type="hidden" id="bd_no" name="bd_no" value=""/>
		<input type="hidden" id="writer" name="writer" value=""/>
	</form>
    
	<div class="container-fluid">
	  <div class="row">
       	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

	    <%@ include file ="/common/common_left_nav.jsp" %>
	
	
          <h2>게시판</h2>

          <div class="table-responsive">
			<div class="container">
			  <table class="table">
			    <thead class="thead-light">
			      <tr>
			        <th>게시글번호</th>
			        <th>제목</th>
			        <th>작성자아이디</th>
			        <th>작성일시</th>
			      </tr>
			    </thead>
			    
			    <tbody>
			    	<c:if test="${postList!= null}">
			    		<c:forEach items="${postList}" var="post">
					    	<tr class="post" data-post_no ="${post.post_no}" data-bd_no="${post.bd_no}" data-writer="${post.writer}">
						        <td>${post.post_no}</td>
						        <td>${post.title}</td>
						        <td>${post.writer}</td>
						        <td><fmt:formatDate value="${post.reg_dt}" pattern="yyyy-MM-dd"/></td>
					    	</tr>
				    	</c:forEach>
			   		</c:if>
			      
			    </tbody>
			  </table>
			  						<!-- card-body -->
						 <div class="card-footer">
							<nav aria-label="member list Navigation">
								<ul class="pagination justify-content-center m-0">
									
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/boardListingPageController.do?boardNo=${pageVo.getBoardNo()}&page=1&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-double-left"></i></a></li>
									
									<c:choose>
										<c:when test="${pageVo.getPage()-1 <= 1 }">
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/boardListingPageController.do?boardNo=${pageVo.getBoardNo()}&page=1&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-left"></i></a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/boardListingPageController.do?boardNo=${pageVo.getBoardNo()}&page=${pageVo.getPage()-1}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-left"></i></a></li>
										</c:otherwise>
									</c:choose>
									
									<c:forEach begin="1" end="${pagination}" var="i">
										<c:choose>
											<c:when test="${pageVo.getPage() == i }">
												<li class="page-item active"><a class="page-link" href="${pageContext.request.contextPath}/boardListingPageController.do?boardNo=${pageVo.getBoardNo()}&page=${i}&pageSize=${pageVo.getPageSize()}">${i}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/boardListingPageController.do?boardNo=${pageVo.getBoardNo()}&page=${i}&pageSize=${pageVo.getPageSize()}">${i}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									<c:choose>
										<c:when test="${pageVo.getPage()+1 > pagination }">
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/boardListingPageController.do?boardNo=${pageVo.getBoardNo()}&page=${pageVo.getPage()}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-right"></i></a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/boardListingPageController.do?boardNo=${pageVo.getBoardNo()}&page=${pageVo.getPage()+1}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-right"></i></a></li>
										</c:otherwise>
									</c:choose>
									
									<li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/boardListingPageController.do?boardNo=${pageVo.getBoardNo()}&page=${pagination}&pageSize=${pageVo.getPageSize()}"><i class="fas fa-angle-double-right"></i></a></li>
								
								</ul>
							</nav>
						</div>
						<!-- card-footer -->
			</div>
			          	  
           <input type="button" id="writePostbtn" name="writePostbtn" value="게시글 작성" onClick="location.href='${pageContext.request.contextPath}/createPost.do?boardNo=${pageVo.getBoardNo()}'">	

            
          </div>
          
          
        </main>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>

   
  </body>
</html>
