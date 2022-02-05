<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--html, el code-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer List</title>
    </head>
    <body>
        <!--把 user 拿出來-->
        <h3>${user}, 你好!</h3>
        <h3>客戶列表</h3>
        <c:choose>
            <c:when test = "${not empty customers}">
                <c:forEach var="customer" items="${customers}">
                    <p>姓名: ${customer.name}</p>
                    <c:if test = "${not empty customer.officeAddress}">
                    <p>辦公室地址:
                        ${customer.officeAddress.address1},
                        ${customer.officeAddress.address2},
                        ${customer.officeAddress.city},
                        ${customer.officeAddress.country},
                        ${customer.officeAddress.postcode}           
                    </p>
                    </c:if>
                    <c:if test = "${customer.billingAddress ne null}">
                    <p>發票地址:
                        ${customer["billingAddress"].address1},
                        ${customer["billingAddress"].address2},
                        ${customer["billingAddress"].city},
                        ${customer["billingAddress"].country},
                        ${customer["billingAddress"].postcode}         
                    </p>
                    </c:if>
                    <c:if test = "${not empty customer.addresses[2]}">
                    <p>送貨地址:
                        ${customer.addresses[2].address1},
                        ${customer.addresses[2].address2},
                        ${customer.addresses[2].city},
                        ${customer["addresses"][2].country},
                        ${customer["addresses"][2].postcode}          
                    </p>
                    </c:if>
                    <br>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h3>無客戶存在</h3>
            </c:otherwise>
        </c:choose>
    </body>
</html>
