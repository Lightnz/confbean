<%-- 
    Document   : servletTest
    Created on : 15.12.2014, 17:55:15
    Author     : alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servlet Test</title>
    </head>
    <body>
        <div>
            <h1>Servlet Test</h1>
            <br/><br/>
            Test Prozess: <br/>
            1- Login User 0/User 1 <br/>
            --> Servlet erhält Stateful EJB mit referziertem User und seinen angemeldeten Konferenzen <br/><br/>
            2- Invoke der Session (Logout) <br/>
            --> sollte im Idealfall bewirken, dass die referenzierte EJB gelöscht wird <br/><br/><br/>
                      
        
        
        </div>
        
        <div>
            <form action="ConferenceServlet" method="POST">
            <p>
                
                <input type="submit" name="Login" value="Login as User 0"/>
                <input type="submit" name="Login" value="Login as User 1"/>             
                <input type ="submit" name="SkipLogin" value="Skip Login"/>
              
            </p>
            </form>
            
            
            
        </div>
        
    </body>
</html>
