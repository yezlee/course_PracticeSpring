<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Dashboard Template for Bootstrap</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">
  </head>

  <body>
  
    <%@ include file ="../common/common_top_nav.jsp" %>
    
	<div class="container-fluid">
	  <div class="row">
       	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

	    <%@ include file ="../common/common_left_nav.jsp" %>
	
	
          <h2>Main Page</h2>
          <div class="table-responsive">
            
            <div class="createBoard">
	            <form action="${pageContext.request.contextPath}/board/createBoard" method="post">
	            	<b>게시판 이름</b>
	            	<input type="text" name="boardName" value="">
	            	<select name="actCheck">
	            		<option value="y">사용</option>
	            		<option value="n">미사용</option>
	            	</select> 
	            	<input type="submit" id="createBoardBtn" value="생성"><br>
	            </form>
            </div>
            
            <div class="modifyBoard">
	            <c:forEach items="${boardList}" var="board">
	            	<form action="${pageContext.request.contextPath}/board/modifyBoard" method="post">
	            		<b>게시판 이름</b>
	            		<input type="hidden" name="boardNo" value="${board.bd_no}">
	            		<input type="text" value="${board.bd_nm}" readonly>
		            	<select name="actCheck">
		            		<c:choose>
		            			<c:when test="${board.bd_act == 1}">
				            		<option value="y" selected="selected">사용</option>
				            		<option value="n">미사용</option>
		            			</c:when>
		            			<c:otherwise>
				            		<option value="y">사용</option>
				            		<option value="n" selected="selected">미사용</option>
		            			</c:otherwise>
		            		</c:choose>
						</select>            		
		            	<input type="submit" id="modifyBoardBtn" value="수정"><br>
	            	</form>
	            </c:forEach>
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
