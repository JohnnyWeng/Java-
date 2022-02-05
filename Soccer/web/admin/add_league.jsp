<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<c:set var="pageTitle">Duke's Soccer League: Add a New League</c:set>

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
            <td><h3>${pageTitle}</h3></td> <!-- td: table data -->
        </tr> <!-- tr: table roll -->
    </table>
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
    
    <p>
    This form allows you to create a new soccer league.
    </p>
    
    <form action='AddLeague' method='POST'>
        Year: <input type='text' name='year' value='${param.year}'/> <br/><br/>
        
        Season: <select name='season'>  
            <option value='UNKNOWN'> --Select-- </option>

            <c:forEach items="${applicationScope.seasonList}" var="seasonStr" >
                <c:choose>
                    <%-- eq: equals  --%>

                    <c:when test="${seasonStr eq param.season}"> 
                        <option value='${seasonStr}' selected> ${seasonStr} </option> 
                    </c:when>
                   
                    <c:otherwise>
                        <option value='${seasonStr}'> ${seasonStr} </option>           
                    </c:otherwise>
                </c:choose>
            </c:forEach> 
            </select> <br><br>
            
        Title: <input type='text' name='title' value='${param.title}'/> <br/><br/>
    <input type='Submit' value='Add League' />
    </form>
    </body>
</html>
