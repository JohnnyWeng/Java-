<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%-- Set page title --%>
<c:set var="pageTitle">Duke's Soccer League: Registration</c:set>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${pageTitle}</title>
    </head>
    <body bgcolor='white'>
    <!-- Page Heading -->
        <table border='1' cellpadding='5' cellspacing='0' width='400'>
            <tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
                <td><h3>${pageTitle}</h3></td>
            </tr>
	</table>
        <table border='1' cellspacing='0' cellpadding='0' width='400'>
            <tr height='20'>
                <td bgcolor='#CCCCFF' align='center' valign='center' height='20'>
                    <b>1) Enter Player Info</b>
                </td>
                <td bgcolor='#CCCCCC' align='center' valign='center' height='20'>
                    <b>2) Select League</b>
                </td>
            </tr>
        </table> <br>
        <c:if test="${not empty errorMsgs}">
        <p>
          <font color='red'>Please correct the following errors:
          <ul>
              <c:forEach items="${errorMsgs}" var='msg'>
                  <li>${msg}</li>
              </c:forEach>
          </ul>
          </font>
        </p>
        </c:if>
        <form action='<c:url value="EnterPlayer"/>' method='POST'>
            Name: <input type='text' name='name' value='${param.name}'/> <br><br>
            Address: <input type='text' name='address' value='${param.address}'/> <br><br>
            City: <input type='text' name='city' value='${param.city}'/> <br><br>
            Province: <input type='text' name='province' value='${param.province}'/> <br><br>
            Postal code: <input type='text' name='postalCode' value='${param.postalCode}'/> <br><br>
            <input type='Submit' value='Continue...' />
	</form>
    </body>
</html>
