<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Fixed Deposit list</title>
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

.a {
	font-family: 'arial';
	font-size: 12px;
}
</style>
</head>
<body>
	<form name="fixedDepositList" method="POST"
		action="${pageContext.request.contextPath}/fixedDeposit?fdAction=createFDForm">
		<table align="left" style="padding-left: 300px;">
			<tr>
				<td
					style="font-family: 'arial'; font-size: 16px; font-weight: bold;">Fixed
					deposit list</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
					<table class="border" cellpadding="10">
						<tr bgcolor="#99CCFF">
						  <th class="th">ID</th>
							<th class="th">Deposit amount</th>
							<th class="th">Tenure</th>
							<th class="th">Email</th>
							<th class="th">Action</th>
						</tr>
						<c:forEach items="${fdList}" var="fixedDeposit">
							<tr>
								<td class="td"><c:out value="${fixedDeposit.id}" /></td>
								<td class="td"><c:out value="${fixedDeposit.depositAmount}" /></td>
								<td class="td"><c:out value="${fixedDeposit.tenure}" /></td>
								<td class="td"><c:out value="${fixedDeposit.email}" /></td>
								<td class="td"><a
									href="${pageContext.request.contextPath}/fixedDeposit?fdAction=close&fixedDepositId=${fixedDeposit.id}"
									style="color: green">Close</a> <a
									href="${pageContext.request.contextPath}/fixedDeposit?fdAction=view&fixedDepositId=${fixedDeposit.id}"
									style="color: green">Edit</a></td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr align="center">
				<td><input type="submit" value="Create new Fixed Deposit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>