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

<%
    Category cat = null;
    String catInput = request.getParameter("category");
    Long catId = null;
    if (catInput != null) {
        catId = Long.parseLong(catInput);
        Optional<Category> foundCat = Category.getTable(Category.class).getById(catId);
        if (!foundCat.isPresent()) {
            //user not found
            response.sendError(404);
            return;
        } else {
            cat = foundCat.get();
        }
    }
%>

<div class="container">

    <% if (cat != null) { %>

        <div class="row">
            <h2>Highlights in <%=cat.getName()%></h2>
            <ul class="list-group">
                <% for(Course course : Course.getTable(Course.class))
                    if (course.isHighlighted() && course.isValid()) {
                        if (cat != null && course.getCategory().getId() == cat.getId())
                            out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle() + "</li></a>");
                    }
                %>
            </ul>
        </div>

        <div class="row">
            <h2>What else in <%=cat.getName()%></h2>
            <ul class="list-group">
                <% for (Course course : Course.getTable(Course.class).allAsList()) {
                    if (course.isValid() && course.getCategory().getId() == cat.getId() && !course.isHighlighted()) {
                        out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-info\">" + course.getTitle() + "</li></a>");
                    }
                } %>
            </ul>
        </div>


    <% } else { %>

        <div class="col-md-6">
            <h2>Highlights</h2>
            <ul class="list-group">
                <% for (Course course : Course.getTable(Course.class).allAsList()) {
                        if (course.isValid() && course.isHighlighted()) {
                            out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><h4 class=\"list-group-item list-group-item-info\">" + course.getTitle() + "</h4></a>");
                        }
                } %>
            </ul>
        </div>


        <div class="col-md-6">
            <h2>Courses by category</h2>
            <ul class="">
                <% for(Category cat1 : Category.getTable(Category.class).allAsList()) {
                    out.print("<a href=\"/courses?category=" + cat1.getId() + "\"><h3>" + cat1.getName() + "</h3></a>");
                } %>
            </ul>
        </div>

    <% } %>


</div>

</body>
</html>
