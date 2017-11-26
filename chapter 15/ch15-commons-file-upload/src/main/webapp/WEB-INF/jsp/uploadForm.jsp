<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>File upload using Commons FileUpload</title>
</head>
<br>
<br>
<br>
<br>
<br>

<br>
<br>
<br>
<br>
<body>
	<form method="post" action="${pageContext.request.contextPath}/uploadFile"
		enctype="multipart/form-data">
		<table style="padding-left: 200px;">
			<tr>
				<td colspan="2"><c:out value="${uploadMessage}" /></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td><b>Select the file to be uploaded: &nbsp;</b></td>
				<td><input type="file" name="myFileField" /></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button"
					value="Upload file" onclick="document.forms[0].submit();" /></td>
			</tr>
		</table>

	</form>
</body>
</html>