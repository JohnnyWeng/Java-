<%@ page contentType="text/html" pageEncoding="UTF-8"%> <!-- 如果註解要用中文,要用  pageEncoding="UTF-8"-->
<!--jsp 預設是沒有 Session 物件的, 這裡說不要用 session: false-->
<%@ page session="false" %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- Set page title --%>
<c:set var="pageTitle">Duke's Soccer League: List Leagues</c:set>

<!DOCTYPE html>
<html>
    <head>
        <title>${pageTitle}</title>
    </head>
    
    <body bgcolor='white'>
        
    <!-- Page Heading -->
    <table border='1' cellpadding='5' cellspacing='0' width='400'>
        <tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
            <td><h3>${pageTitle}</h3></td>
        </tr>
    </table>
        
    <p>The set of soccer leagues:</p>
    <ul>
        <!-- For-Each: 把 ListLeagues.java 傳送的(RequestDispatcher) leagueList 一個個拿出來-->
        <c:forEach items="${leagueList}" var="league" >   
             <li>${league.title}</li>
        </c:forEach>
     </ul>        
    <p>End of list.</p>
    
    </body>
</html>

