<%@ page import="java.util.Optional" %>
<%@ page import="java.util.function.Predicate" %>
<%@ page import="beans.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>



<div class="container">

    <% 
    Optional<Category> cat = (Optional<Category>)request.getAttribute("in");
    
    if (cat.isPresent()) { %>

        <div class="row">
            <h2>Highlights in <%=cat.get().getName()%></h2>
            <ul class="list-group">
                <% for(Course course : Course.getAllCourses())
                    if (course.isHighlited() && course.isValid()) {
                        if (course.getCategory().getId() == cat.get().getId())
                            out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle() + "</li></a>");
                    }
                %>
            </ul>
        </div>

        <div class="row">
            <h2>What else in <%=cat.get().getName()%></h2>
            <ul class="list-group">
                <% for (Course course : Course.getAllCourses()) {
                    if (course.isValid() && course.getCategory().getId() == cat.get().getId() && !course.isHighlited()) {
                        out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-info\">" + course.getTitle() + "</li></a>");
                    }
                } %>
            </ul>
        </div>


    <% } else { %>

        <div class="col-md-6">
            <h2>Highlights</h2>
            <ul class="list-group">
                <% for (Course course : Course.getAllCourses()) {
                        if (course.isValid() && course.isHighlited()) {
                            out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><h4 class=\"list-group-item list-group-item-info\">" + course.getTitle() + "</h4></a>");
                        }
                } %>
            </ul>
        </div>


        <div class="col-md-6">
            <h2>Courses by category</h2>
            <ul class="">
            	<%
                	out.print("<a href=\"/courses?category=" + cat1.getId() + "\"><h3>" + cat1.getName() + "</h3></a>");
            	%>
            </ul>
        </div>

    <% } %>


</div>

</body>
</html>
