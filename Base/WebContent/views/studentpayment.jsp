<%@ page import="java.util.Optional" %>
<%@ page import="controllers.UserController" %>
<%@ page import="beans.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="plain/assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="plain/nav.jsp"></jsp:include>
			
			<%
			
			User user = (User) request.getAttribute("in");
			boolean isLogged = UserController.isLoggedIn(user, request);
			
			%>
			

            <div class="section">
                <div class="container panel panel-default" style="padding-bottom: 20px;">
                    <div class="panel-body">
					
 
                    </div>
                    </div>
                </div>

</body>
</html>