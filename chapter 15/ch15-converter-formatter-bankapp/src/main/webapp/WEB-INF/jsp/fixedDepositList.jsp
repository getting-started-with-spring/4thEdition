<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title><spring:message code="fd.list.title" /></title>
<style type="text/css">
.border {
	border-width: 1px;
	border-style: solid;
	border-collapse: collapse;
}

.td,.th {
	border: 1px solid;
	font-family: 'arial';
	font-size: 12px;
}

.tdnoborder {
	font-family: 'arial';
	font-size: 12px;
}

.a {
	font-family: 'arial';
	font-size: 12px;
}
</style>
</head>
<body>
	<form name="fixedDepositList" method="POST"
		action="${pageContext.request.contextPath}/fixedDeposit?fdAction=createFDForm">
		<table>
			<tr>
				<td>
					<table align="left" style="padding-left: 300px;">
						<tr>
							<td
								style="font-family: 'arial'; font-size: 16px; font-weight: bold;"><spring:message
									code="fd.list.title" /></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
								<table class="border" cellpadding="10">
									<tr bgcolor="#99CCFF">
										<th class="th"><spring:message code="fd.id.title" /></th>
										<th class="th"><spring:message
												code="fd.depositAmount.title" /></th>
										<th class="th"><spring:message code="fd.tenure.title" /></th>
										<th class="th"><spring:message code="fd.email.title" /></th>
										<th class="th"><spring:message code="fd.action.title" /></th>
									</tr>
									<c:forEach items="${fdList}" var="fixedDeposit">
										<tr>
											<td class="td"><spring:eval expression="fixedDeposit.id" /></td>
											<td class="td"><spring:eval
													expression="fixedDeposit.depositAmount" /></td>
											<td class="td"><spring:eval
													expression="fixedDeposit.tenure" /></td>
											<td class="td"><spring:eval
													expression="fixedDeposit.email" /></td>
											<td class="td"><a
												href="${pageContext.request.contextPath}/fixedDeposit?fdAction=close&fixedDepositId=${fixedDeposit.id}"
												style="color: green"><spring:message
														code="fd.close.title" /></a> <a
												href="${pageContext.request.contextPath}/fixedDeposit?fdAction=view&fixedDepositId=${fixedDeposit.id}"
												style="color: green"><spring:message
														code="fd.edit.title" /></a></td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
						<tr align="center">
							<td><input type="submit"
								value="<spring:message code="fd.createNewFd.button" />" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<br>
		<table align="left" style="padding-left: 300px;">
			<tr>
				<td class="tdnoborder"><b>Language:</b> <a
					href="${pageContext.request.contextPath}/fixedDeposit/list?lang=en_US">English(US)</a>
					| <a
					href="${pageContext.request.contextPath}/fixedDeposit/list?lang=de_DE">German</a>
					<br> <b>Locale:</b> <c:out value="${currentLocale}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>