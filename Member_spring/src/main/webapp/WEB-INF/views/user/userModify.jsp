<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="kr.or.ddit.common.model.PageVo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정페이지</title>
<%@ include file ="../common/common_lib.jsp" %>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	
	$(function(){
		//주소 검색 버튼이 클릭 되었을 때 다음 주소 api 팝업을 연다.
		$("#addrBtn").on("click", function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		            console.log(data);
		            
		            $("#addr1").val(data.roadAddress); //도로주소 
		            $("#zipcode").val(data.zonecode);  //우편번호
		            
		            //사용자 편의성을 위해 상세주소 입력 input태그로 focus설정
		            $("#addr2").focus(); //이러면 커서가 이리로 딱 간다
		        }
		    }).open();
		});
		
		
		$("#motidyBtn").on("click", function () {
			nm = $('#usernm').val().trim();
			pass = $('#pass').val().trim();
			
			if(nm.length == 0){
			   alert("이름을 입력해주세요")
			   return false;
			}
			if(pass.length == 0){
			   alert("비밀번호를 입력해주세요")
			   return false;
			}
			$("#frm").submit();
		});
		
		
	});
	
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
							<b>회원 수정페이지</b>
						</div>
						<!-- form start -->
						<div class="card">
							<div class="register-card-body">
							
								<form method="post" class="form-horizontal" id="frm" role="form" action="${pageContext.request.contextPath}/member/userModify" enctype="multipart/form-data">
									<input type="hidden" name="userid" value="${user.userid}">
									<div class="input-group mb-3">
										
										<!-- 사용자사진 -->
										<div class="mailbox-attachments clearfix" style="text-align: center; width:100%;">
											
											<div class="mailbox-attachment-icon has-img" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;">
												<img id="pictureViewImg" src="${pageContext.request.contextPath}/member/profile?userid=${user.userid}">
											</div>
											
											<div class="mailbox-attachment-info">
												<div class="input-group input-group-sm">
													<input id="profile" class="form-control" type="file" name="profile" style="height:37px;"/>
												</div>
											</div>
										</div>
										<br />
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
											<input type="text" class="form-control" id="usernm" name="usernm" value="${user.usernm}">
										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">패스워드</label>
										<div class="col-sm-9 input-group input-group-sm">
											<input type="password" class="form-control" id="pass" name="pass" value="${user.pass}">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">별명</label>
										<div class="col-sm-9 input-group input-group-sm">
											<input type="text" class="form-control" id="alias" name="alias" value="${user.alias}">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-sm-3" style="font-size: 0.9em;">등록일시</label>
										<div class="col-sm-9 input-group input-group-sm">
											<input type="text" class="form-control" id="reg_dt" name="reg_dt" value="<fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd"/>">
										</div>
									</div>


									<div class="form-group row">
										<label for="addr1" class="col-sm-3 control-label">주소</label>
										<div class="col-sm-3 input-group-sm">
											<input type="text" class="form-control" id="addr1" name="addr1" value="${user.addr1}" placeholder="주소" readonly>
										</div>
										<div class="col-sm-3 input-group-sm">
											<input type="text" class="form-control" id="addr2" name="addr2" value="${user.addr2}" placeholder="상세주소">	
										</div>
										
										<div class="col-sm-2 input-group-sm">
											<input type="text" class="form-control" id="zipcode" name="zipcode" value="${user.zipcode}" placeholder="우편번호" readonly>
										</div>
										
										<div class="col-sm-1 input-group-sm">
											<span class="input-group-append-sm">
												<button type="button" id="addrBtn" class="btn btn-info btn-sm btn-append">주소검색</button>
											</span>
										</div>
										
									</div>
																		
									<div class="card-footer">
										<div class="row">
											<div class="col-sm-6">
												<button type="button" id="motidyBtn" class="btn btn-info">수정하기</button>
											</div>

											<div class="col-sm-6">
												<button type="button" onclick="history.back()" id="cancelBtn" class="btn btn-default float-right">&nbsp;&nbsp;&nbsp;취 &nbsp;&nbsp;소&nbsp;&nbsp;&nbsp;</button>
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
	
	<script>
	
	$(document).ready(function(){
		// picture input의 파일 변경시 이벤트 
		$("#profile").change(function(){
		   readURL(this);
		});
	});
	
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
		  
			reader.onload = function (e) {
				$('#pictureViewImg').attr('src', e.target.result);  
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
			
	</script>	
	
</body>
</html>