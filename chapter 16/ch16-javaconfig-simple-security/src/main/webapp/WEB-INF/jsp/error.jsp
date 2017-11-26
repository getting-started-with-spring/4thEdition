<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>Error occurred during request processing</h3>

Exception message : <c:out value="${msg}" />
<p>
<a href="<c:url value="/"/>">Go back</a>
</p>