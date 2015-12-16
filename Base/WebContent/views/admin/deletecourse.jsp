<%@page import="beans.managers.CourseManager"%>
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
                <% for(Course course : CourseManager.getCourses()){
                            out.print(course.getTitle()); %><button type="button" id ="DeleteCOurse">Delete Course</button><button type="button" id="Validate">Validate Course</button>
                    
               <%} %>
            </ul>
        </div>


</div>

</body>
</html>
