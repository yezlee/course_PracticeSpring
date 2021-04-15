<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.or.ddit.common.model.PageVo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file ="../common/common_lib.jsp" %>

<script type="text/javascript">

$(function(){
	$("#modifyBtn").on("click", function(){
		$("#frm").attr("method", "get");
		$("#frm").attr("action", "${pageContext.request.contextPath}/member/userModify");
		$("#frm").submit();
	});
	
	$("#deleteBtn").on("click", function(){
		
		if(confirm('삭제하시겠습니까?')){
			$("#frm").attr("method", "post");
			$("#frm").attr("action", "${pageContext.request.contextPath}/member/userDelete");
			$("#frm").submit();
		} else {
			return false;
		}
		
		
	});
})

</script>

</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">

	<%@ include file ="../common/common_navi.jsp" %>	

		<div id="if_list_div" style="position: relative; padding: 0; overflow: hidden;">
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">

				<!-- Main content -->
				<section class="content register-page" style="height:100%;">
					<div class="container-fluid">
						<div class="login-logo">
							<b>회원 상세페이지</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
							
								<form class="form-horizontal" id="frm" role="form">
									<input type="hidden" name="userid" value="${user.userid}">
									
									<div class="input-group mb-3">
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img src="${pageContext.request.contextPath}/member/profile?userid=${user.userid}">
											</div>
										</div>
										<br/>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">아이디	</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.userid}</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">이름</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.usernm}</span>
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">패스워드</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.pass}</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm">${user.alias}</span>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">등록일시</label>
										<div class="col-sm-9 input-group input-group-sm">
											<span class="input-group-append-sm"><fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd"/></span>
										</div>
									</div>


									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<span class="input-group-append-sm">${user.addr1}</span>
										</div>
										<div class="col-sm-3 input-group-sm">
											<span class="input-group-append-sm">${user.addr2}</span>
										</div>
										
										<div class="col-sm-2 input-group-sm">
											<span class="input-group-append-sm">${user.zipcode}</span>
										</div>
										
									</div>
									
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="modifyBtn" class="btn btn-info">수정</button>
												<button type="button" id="deleteBtn" class="btn btn-info">삭제</button>
											</div>
										</div>
									</div>
								</form>
							</div>
							<!-- register-card-body -->
						</div>
					</div>
				</section>
				<!-- /.content -->
			</div>
			<!-- /.content-wrapper -->
		</div>
	</div>
	<!-- ./wrapper -->
	
	<%@ include file ="../common/common_footer.jsp" %>
	<%@ include file ="../common/common_lib_js.jsp" %>
	
</body>
</html>