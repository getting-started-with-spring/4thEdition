<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

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
.button {
     background:none!important;
     border:none; 
     padding:0!important;
     font: inherit;
     color: blue;
     border-bottom:1px solid #444; 
     cursor: pointer;
}

</style>
</head>
<body>
	<form id="logoutForm" method="POST" action="${pageContext.request.contextPath}/logout">
		<security:csrfInput/>
	</form>
	
	<form name="fixedDepositList" method="POST"
		action="${pageContext.request.contextPath}/fixedDeposit?fdAction=createFDForm">
		<security:csrfInput/>
		<table align="left" style="padding-left: 300px;">
			<tr>
				<td style="font-family: 'arial'; font-size: 12px; font-weight: bold"
					align="right">
					<input type="button" class="button" value="Logout" onclick="document.getElementById('logoutForm').submit();"/>
					<p>
						Username:
						<security:authentication property="principal.username" />
					</p></td>
			</tr>
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
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<th class="th">Customer</th>
							</security:authorize>
							<th class="th">Deposit amount</th>
							<th class="th">Tenure</th>
							<th class="th">Email</th>
							<th class="th">Action</th>
						</tr>
						<c:forEach items="${fdList}" var="fixedDeposit">
							<tr>
								<td class="td"><c:out value="${fixedDeposit.id}" /></td>
								<security:authorize access="hasRole('ROLE_ADMIN')">
									<td class="td"><c:out value="${fixedDeposit.customerId}" /></td>
								</security:authorize>
								<td class="td"><c:out value="${fixedDeposit.depositAmount}" /></td>
								<td class="td"><c:out value="${fixedDeposit.tenure}" /></td>
								<td class="td"><c:out value="${fixedDeposit.email}" /></td>
								<td class="td"><security:authorize
										access="hasRole('ROLE_CUSTOMER')">
										<a
											href="${pageContext.request.contextPath}/fixedDeposit?fdAction=view&fixedDepositId=${fixedDeposit.id}"
											style="color: green">Edit</a>
									</security:authorize> <security:authorize access="hasRole('ROLE_ADMIN')">
										<a
											href="${pageContext.request.contextPath}/fixedDeposit?fdAction=close&fixedDepositId=${fixedDeposit.id}"
											style="color: green">Close</a>
									</security:authorize></td>
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