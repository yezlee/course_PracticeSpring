<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<%@ include file ="../common/common_lib.jsp" %>

<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

<style>
body.login-page {
	background-image: url('/resources/images/intro.jpg');
	background-position: center;
	background-size: cover;
	background-repeat: no-repeat;
}
</style>

<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>
	<!-- https://github.com/js-cookie/js-cookie 요기서 가져온 스크립트-->


<script type="text/javascript">

//html 문서 로딩이 완료되고 나서 실행되는 코드
$(function(){
	//userid, rememberme 쿠키를 확인하여 존재할 경우 값 설정, 체크
//	if($("#rememberme").is(":checked") == true){
	if(Cookies.get('rememberme') == "Y"){				
		$("#userid").val(Cookies.get('userid'))
		$("#rememberme").attr("checked", true);
	}
	
	
	//signin 아이디를 select
	$("#signin").on("click", function(){
		
		//rememberme 체크박스가 체크 되어있는지 확인
		//체크되어있을 경우
		if($("#rememberme").is(":checked") == true){
			//userid input에 있는 값을 userid쿠키로 저장
			Cookies.set("userid", $("#userid").val());

			//remeberme 쿠키로 Y값을 저장
			Cookies.set("rememberme", "Y");
		}	
		
		//체크 해제 되어있는 경우 : 더 이상 사용하지 않는 다는 의미이므로 userid, rememberme 쿠키 삭제
		else{
			Cookies.remove("userid");					
			Cookies.remove("rememberme");					
		}
		
		//form태그 이용하여 signin 요청
//		$("form태그select").submit();
		$("#frm").submit();
		
	});
});	

</script>


</head>
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>로그인</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">Sign in to start your session</p>



				<form action="${pageContext.request.contextPath}/login/process" id="frm" method="post">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" name="userid" id="userid" placeholder="아이디를 입력하세요." value=""> <span
							class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" name="pass"	placeholder="패스워드를 입력하세요." value=""> <span
							class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<div class="checkbox icheck">
								<label> <input type="checkbox" id="rememberme" name="rememberme"> Remember Me</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-sm-4 checkbox">
							<button type="button" id="signin" class="btn btn-primary btn-block btn-flat">로그인</button>
						</div>
						<!-- /.col -->
					</div>
				</form>



			</div>
			<!-- /.login-box-body -->
		</div>
	</div>
	<!-- /.login-box -->


	<%@ include file ="../common/common_lib_js.jsp" %>

</body>
</html>