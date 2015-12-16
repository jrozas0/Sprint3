<%@page import="beans.managers.UserManager"%>
<%@ page import="java.util.*" %>
<%@ page import="beans.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="/views/plain/assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="/views/plain/nav.jsp"></jsp:include>



<div class="container">

    <% 
    Optional<User> user = (Optional<User>)request.getAttribute("in");
        
  %>

        <div class="row">
            <h2>Users:</h2>
            <ul class="list-group">
                <% for(User user1 : UserManager.getUsers()){
                            out.print(user.get().getName()); %><button type="button" if="DeleteUser">Delete User</button>
                    
               <%} %>
            </ul>
        </div>


</div>

</body>
</html>
