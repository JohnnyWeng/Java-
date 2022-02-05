<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Info</title>
    </head>
    <body>
        <%--  必須用jsp commennd 標籤,當解譯為 Servlet 時,此標籤中內容會被忽略--%>
        <%-- 把 姓名 拿出來 --%>
        <h3>${user}, 你好!</h3>
        <h3>客戶資訊</h3>
        <%-- JSTL 標籤 --%>
        <%-- c:choose: 提供條件判斷 --%>
        <c:choose>
            <%-- c:when: 提供 c:choose 其中一個選擇方案 --%>
            <%-- 這裡的 customer 是指在 Controller 最後面的 setAttribute 的 customer --%>
            <c:when test = "${not empty customer}">                
                <p>姓名: ${customer.name}</p>
                <c:if test = "${not empty customer.officeAddress}"> <%-- 如果 officeAddress 不是 empty 才做下面內容 --%>
                <p>辦公室地址:<br>
                    ${customer.officeAddress.address1},<br> <%-- <br> : 換行 --%> 
                    ${customer.officeAddress.address2},<br>
                    ${customer.officeAddress.city},<br>
                    ${customer.officeAddress.country},<br>
                    ${customer.officeAddress.postcode}<br>            
                </p>
                </c:if>
                <c:if test = "${customer.billingAddress ne null}"> <%-- ne null ==  not empty --%>
                <p>發票地址:<br>
                    ${customer["billingAddress"].address1},<br>
                    ${customer["billingAddress"].address2},<br>
                    ${customer["billingAddress"].city},<br>
                    ${customer["billingAddress"].country},<br>
                    ${customer["billingAddress"].postcode}<br>            
                </p>
                </c:if>
                <c:if test = "${not empty customer.addresses[2]}">
                <p>送貨地址:<br>
                    ${customer.addresses[2].address1},<br>
                    ${customer.addresses[2].address2},<br>
                    ${customer.addresses[2].city},<br>
                    ${customer["addresses"][2].country},<br>
                    ${customer["addresses"][2].postcode}<br>            
                </p>
                </c:if>
            </c:when>
                <%-- c:otherwise: 提供 c:choose 最後一個選擇方案 --%>
            <c:otherwise>
               <h3>查詢的客戶不存在</h3>
            </c:otherwise>
        </c:choose>
    </body>
</html>

