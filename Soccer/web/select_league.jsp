<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%-- Set page title --%>
<c:set var="pageTitle">Duke's Soccer League: Registration</c:set>
<!DOCTYPE html>
<html>
    <head>
        <title> ${pageTitle} </title>
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
                <td bgcolor='#CCCCCC' align='center' valign='center' height='20'>
                    <b>1) Enter Player Info</b>
                </td>
                <td bgcolor='#CCCCFF' align='center' valign='center' height='20'>
                    <b>2) Select League</b>
                </td>
            </tr>
        </table>            
        <br>
        <c:if test='${not empty errorMsgs}'>
            <font color='red'> Please correct the following errors:
            <ul>
                <c:forEach items='${errorMsgs}' var='msg' >
                    <li> ${msg}</li>
                </c:forEach>
            </ul> 
            </font>
        </c:if>
        <form action='<c:url value="SelectLeague"/>' method='POST'>
            Year: <input type='text' name='year' value='${param.year}'/> <br><br/>
            Season: <select name='season'>
                <c:forEach items='${applicationScope.seasonList}' var='seasonStr'> 
                    <c:choose>
                        <c:when test='${seasonStr eq param.season}'>
                            <option value='${seasonStr}' selected> ${seasonStr}</option>
                        </c:when>
                        <c:otherwise>
                            <option value='${seasonStr}' > ${seasonStr}</option>
                        </c:otherwise>
                    </c:choose>        
                </c:forEach>
             </select> <br/><br/>
             <input type='Submit' value='Submit' />
        </form>
    </body>
</html>
