<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>登入畫面</h1>
        
        <!--下面的 j_security_check 名稱 還有 method 是 POST 都是規定好的,必須這樣寫
        , 下面丟給 j_security_check 去做驗證 -->
        <!--POST : 在 URL 網址列不希望顯示太多資訊, 像是 帳號 和 密碼--> 
        <form action="j_security_check" method="POST">
            
            <!--可以讓使用者輸入姓名,文字格式, name 也必須叫做 j_username -->
            <!--一樣密碼也必須叫做 j_password, 這都是內建的機制,我們只是拿來用而已-->
            <p>姓名: <input type="text" name="j_username"/><br><br>
               密碼: <input type="password" name="j_password"/><br><br></p>
            
            <input type="submit" value="登入">
            <input type="reset" value="清除"/>
        </form>
    </body>
</html>

