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
	
	
          <h2><a href="${pageContext.request.contextPath}/board/boardList">WANNA MAKE A BOARD?</a></h2>
          <div class="table-responsive">
            
           <img src="${pageContext.request.contextPath}/image/unknown.png" alt="main">
            
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
