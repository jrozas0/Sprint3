<%@ page import="java.util.Optional" %>
<%@ page import="java.util.*" %>
<%@ page import="beans.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="../plain/assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="../plain/nav.jsp"></jsp:include>

<div class="container panel panel-default" style="padding-bottom: 20px;">

    <div class="row panel-body">

			<% Optional<Course> course = (Optional<Course>)request.getAttribute("in");
			
				User user = (User) request.getAttribute("in");
			%>
			
            <h1><%=course.get().getTitle()%></h1>
            <br>
            <img style="height: 100px; width: 100px;" src="/restricted/course/picture?id=<%=course.get().getId()%>">
            <br>
            <br>
            <br>
            <p><a class="label label-default" href="/courses?category=<%=course.get().getCategory().getId()%>"><%=course.get().getCategory().getName()%></a></p>
            <p><%=course.get().getDescription()%></p>
            <p><strong>Difficulty: </strong>
                <%
                    if (course.get().getDifficulty().equals(1)) {
                        out.print("Basic");
                    } if (course.get().getDifficulty().equals(2)) {
                        out.print("Intermediate");
                    } if (course.get().getDifficulty().equals(3)) {
                        out.print("Advanced");
                    }
                %>
                <% if (!course.get().getDiscounted()) { %>
                    <p><strong>Price: </strong><%=course.get().getPrice()%> euros</p>
                <% } else { %>
                     <p><strong>Price: </strong><strike><%=course.get().getPrice()%></strike> <%=course.get().getPrice()%> euros</p>
                <% } %>
            <% 
            
            
           // if(	) { %>
                <br><a href="/restricted/teacher/course?edit=<%=course.get().getId()%>">Edit details</a><br>
                <% if(course.get().isValid()) { %>
                    <br><a href="/restricted/teacher/invite?id=<%=course.get().getId()%>">Invite staff</a>
                <% }%>
            <%//}%>

       

            <br>

       

    </div>
            <% 
            Course thisCourse = user.getCourse();
            if(user.isAttending(thisCourse) || user.isTeaching(thisCourse))  { %>

 <%if (!user.isAttending(thisCourse) && !user.isTeacher()) { %>

                    <% if(!wished) { %>
                        <a href="/restricted/course/wish?course=<%=course.getId()%>"><div class="btn btn-lg btn-info">Wish</div></a>
                        <br>
                    <% } else { %>
                        <a href="/restricted/course/notwish?course=<%=course.getId()%>"><div class="btn btn-lg btn-danger">Do not wish</div></a>
                        <br>
                    <% }%>

                    <br>
                    <a href="/restricted/course/register?course=<%=course.getId()%>"><div class="btn btn-lg btn-success" style="margin-bottom: 20px;">Enroll</div></a>

            </div>

            <% } %>


        <% } %>

    </div>



</body>
</html>
