<%@ page contentType="text/html"%>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Set page title --%>
<c:set var="pageTitle">Duke's Soccer League: Registration Success</c:set>
<!DOCTYPE html>

<%-- Generate the HTML response --%>
<html>
<head>
  <title>${pageTitle}</title>
</head>
<body bgcolor='white'>

<%-- Generate page heading --%>
<!-- Page Heading -->
<table border='1' cellpadding='5' cellspacing='0' width='500'>
<tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
  <td><h3>${pageTitle}</h3></td>
</tr>
</table>

<%-- Generate main body --%>
<p>
    Thank you, ${player.name}. <br/>
    Your request to register the <i> ${league.title} </i> league was successful.
</p><br/>
<a href='<c:url value="select_league.jsp" />'> Register another league </a><br/>
<a href='index.html'> Return to homepage </a> 
</body>
</html>