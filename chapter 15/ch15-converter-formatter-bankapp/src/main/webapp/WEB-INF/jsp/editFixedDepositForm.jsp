<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
<title><spring:message code="fd.createNewFd.button" /></title>
<style type="text/css">
.td {
	font-family: 'arial';
	font-size: 12px;
	vertical-align: top;
}
</style>
</head>
<body>
	<form:form modelAttribute="editableFixedDepositDetails"
		name="editFixedDepositForm" method="POST"
		action="${pageContext.request.contextPath}/fixedDeposit?fdAction=edit">
		<table align="left" style="padding-left: 300px;">
			<tr>
				<td
					style="font-family: 'arial'; font-size: 16px; font-weight: bold;"
					align="left"><spring:message code="fd.openfd.title" /></td>
			</tr>
			<tr align="left">
				<td>
					<table class="border" cellpadding="10">
						<tr>
							<td class="td"><b><spring:message code="fd.id.title" />:</b></td>
							<td class="td"><form:input path="id" readonly="true" /></td>
						</tr>
						<tr>
							<td class="td"><b><spring:message
										code="fd.amount.inUSD.title" />:</b></td>
							<td class="td"><form:input path="depositAmount" /><font
								style="color: #C11B17;"><form:errors path="depositAmount" /></font></td>
						</tr>
						<tr>
							<td class="td"><b><spring:message
										code="fd.tenure.inMonth.title" />:</b></td>
							<td class="td"><form:input path="tenure" /><font
								style="color: #C11B17;"><form:errors path="tenure" /></font></td>
						</tr>
						<tr>
							<td class="td"><b><spring:message code="fd.email.title" />:</b></td>
							<td class="td"><form:input path="email" /><font
								style="color: #C11B17;"><form:errors path="email" /></font></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr align="left">
				<td>
					<table style="padding-left: 100px;">
						<tr align="center">
							<td class="td"><input type="submit"
								value="<spring:message code="fd.save.button" />" /> &nbsp;
								&nbsp;<a
								href="${pageContext.request.contextPath}/fixedDeposit/list"
								style="color: green"><b><spring:message
											code="fd.goback.button" /></b></a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>