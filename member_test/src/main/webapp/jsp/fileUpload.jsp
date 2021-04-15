<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- enctype="multipart/form-data" 우리가 인코딩하는게 파일이 있는 거다. 문자열만있는게 아니니까 데이터 잘 확인해라 --> 	
	<!-- 09:49:18.662 [http-nio-80-exec-13] DEBUG kr.or.ddit.servlet.FileUploadServlet - contentType : multipart/form-data; boundary=----WebKitFormBoundaryLrPJjzRZKKGBpKBO
		내가 보내는 데이터가 한개가 아니다 멀티다. 그 바운더리~~~
	
	 -->

	
	
	<form action="${pageContext.request.contextPath}/fileUploadServlet.do" method="post" enctype="multipart/form-data">
		userid : <input type="text" name="userid" value="brown" /> <br>
				 <input type="file" name="file" />
		<input type="submit" value="전송" >
	</form>
	
	
	
	
</body>
</html>
