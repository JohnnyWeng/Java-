<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ page import="java.util.*" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Duke's Soccer League: Error Page</title>
	</head>
	<body bgcolor='white'>
	<!-- Page Heading -->
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='red' align='center' valign='center' height='20'>
			<td><h3>Duke's Soccer League: Error Page</h3></td>
		</tr>
	</table>

        <!-- Generate main body -->
	<p>
	<font color='red'>Please correct the following errors:
	<ul>
	<%
            List errorMsgs = (List) request.getAttribute("errorMsgs");
            Iterator items = errorMsgs.iterator();
            while ( items.hasNext() ) {
                String message = (String) items.next();
	%>
		<li><%=message%></li>
        <%
            }
	%>
	</ul>
	<a href='AddLeagueForm'> Back up and try again.</a>
	</font>
        </p>

	</body>

</html>

