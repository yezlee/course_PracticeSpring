<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>게시판</title>

<%@ include file="../common/common_lib.jsp"%>

<link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/dashboard/">

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/dashboard.css" rel="stylesheet">

<!-- Font Awesome -->
<link rel="stylesheet"	href="${pageContext.request.contextPath}/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">


<!-- summernote -->
<!-- include libraries(jQuery, bootstrap) -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"	rel="stylesheet">
<script	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>



<style type="text/css">
	.container {
		margin-left: 0;
		padding-left: 0;
	}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		$('#summernote').summernote();
	});
</script>
</head>

<body>

	<%@ include file="../common/common_top_nav.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

				<%@ include file="../common/common_left_nav.jsp"%>


				<h2>게시판</h2>
				<div class="table-responsive">


					<div class="container">
						<table class="table">
							<thead class="thead-light">
								<tr>
									<th style="text-align: left; padding-left: 30px;">게시글 작성 페이지</th>
								</tr>
							</thead>
						</table>

						<form action="${pageContext.request.contextPath}/board/createPost" method="post">
							<input type="hidden" name="boardNo" value="${boardNo}"> 
							<input type="hidden" name="userId" value="${S_USER.userid}">

							<div>
								<strong>제목</strong> 
								<span>
									<input type="text" name="title"	value="${title}" placeholder="제목을 입력하세요" />
								</span>
							</div>
							<div>
								<strong>내용</strong> 
								<span>
									<textarea id="summernote" name="editordata">${editordata}</textarea>
								</span>
							</div>
							<div>
								<strong>첨부파일</strong> 
								<span>
									<input type="button" name="file" value="첨부파일 추가하기" />
								</span>
							</div>
							<div>
								<strong>댓글</strong> 
								<span>
									<input type="text" name="reply"	value="" placeholder="댓글을 입력하세요" />
								</span> 
								<span>
									<input type="button" name="replyBtn" value="댓글입력하기" />
								</span>
							</div>

							<input type="submit" id="createPostBtn" value="게시글 등록하기">

						</form>
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
