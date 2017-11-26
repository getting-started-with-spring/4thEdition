<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<html>
<head>
<title>Login</title>
<style type="text/css">
.td {
	font-family: 'arial';
	font-size: 14px;
	vertical-align: top;
}
</style>
</head>
<body>
	<form name="login" method="POST"
		action="${pageContext.request.contextPath}/login">
		<security:csrfInput/>
		<table align="left" style="padding-left: 300px;">
			<tr align="left">
				<td>
					<table class="border" cellpadding="10">
						<tr>
							<td style="font-family: 'arial'; font-size: 12px; font-weight: normal; color: red;"><c:out value="${param.exceptionMsg}"/></b></td>
						</tr>
						<tr>
							<td class="td"><b>Username:</b></td>
							<td class="td"><input type="text" name="username" value="" /></td>
						</tr>
						<tr>
							<td class="td"><b>Password:</b></td>
							<td class="td"><input type="password" name="password"
								value="" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr align="left">
				<td>
					<table style="padding-left: 100px;">
						<tr align="center">
							<td class="td"><input type="submit" value="Login" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>