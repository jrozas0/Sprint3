<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lib.controllers.View" %>
<html>
<head>
    <jsp:include page="plain/assets.jsp"></jsp:include>
    <title></title>
</head>
<body>
<jsp:include page="plain/nav.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            
            <% if(View.is(request, "ok")) {  %>
            	
            		<h1>Welcome again!</h1>
            	
            <% } else { %>        	
      
            <form class="form-horizontal" role="form" action="/Base/Main/login/post" method="post">
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="inputEmail3" class="control-label">Email</label>
                    </div>
                    <div class="col-sm-10">
                        <input name="email" type="email" class="form-control" id="inputEmail3" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2">
                        <label for="inputPassword3" class="control-label">Password</label>
                    </div>
                    <div class="col-sm-10">
                        <input name="password" type="password" class="form-control" id="inputPassword3" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default btn-lg">Log In</button>
                    </div>
                </div>
          	
	          	<%
                //handle response from the server, from when the form is submitted
                if(View.is(request, "notfound"))
                    out.println("<p class=\"col-sm-offset-2\">Not found user with that email/password combination</p>");
	            %>
        	</form>
        	
        	<% } %>
        	
       	</div>
       	
        <h3 class="text-center col-md-6">Don't have an account?
            <b>
                <a class="signUp" href="/Base/Main/register">Sign Up!</a>
            </b>
        </h3>
    </div>
</div>
</body>
</html>