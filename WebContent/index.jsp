<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/PortNumberSelection/SelectionServlet" method="post">
		Student ID: <input type="text" name="id"> <br /> 
		Select port number1: <input type="text" name="portNum1"> <br /> 
		Select port number2: <input type="text" name="portNum2"> <br /> 
		<input type="submit" value="submit">
		<%=request.getAttribute("loginInfo") == null ? "" : request.getAttribute("loginInfo")%>


	</form>

</body>
</html>