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
	
  
  </style>  
      
  	<script type="text/javascript">

  		
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
			        <th style="text-align:left; padding-left:30px;">${postVo.title}</th>
			      </tr>
			    </thead>
			  </table>
				<table>
					<tr>
						<td>제목</td>
						<td>${postVo.title}</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>${postVo.cont}</td>
					</tr>
					<tr>
						<td>첨부파일</td>
						<td></td>
					</tr>
					<tr>
						<td>댓글</td>
						<td></td>
					</tr>
				</table>	
							
			</div>
			          	  
           
            
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
