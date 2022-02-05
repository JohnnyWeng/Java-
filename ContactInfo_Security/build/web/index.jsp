<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>客戶查詢</title>
    </head>
    <body>
        <!--HTML 程式--> 
        <h3>客戶查詢</h3>
        <form action="SelectCustomer">
            <p>
                輸入客戶編號(輸入0查詢所有客戶): <input name="custid" value="1"/>
            </p>
            <p>
                <input type="submit"/>
            </p>
        </form> 
    </body>
</html>




