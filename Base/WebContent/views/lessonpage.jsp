<%@ page import="java.util.Optional" %>
<%@ page import="Models.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../common/assets.jsp"></jsp:include>
    <title></title>
</head>
<body>
<jsp:include page="../common/nav.jsp"></jsp:include>
<%
    Optional<User> loggedIn = User.getTable(User.class).getById(((User) session.getAttribute("user")).getId());

    Long lessonId = null;
    Lesson lesson = null;
    try {
        lessonId = Long.parseLong(request.getParameter("id"));
    } catch(NullPointerException e1) {
        response.sendError(404);
    } catch(NumberFormatException e2) {
        response.sendError(404);
    }

    if (lessonId == null) {
        response.sendError(404);
    } else {
        Optional<Lesson> lessonFound = Lesson.getTable(Lesson.class).getById(lessonId);
        if (!lessonFound.isPresent()) {
            //user not found
            response.sendError(404);
        } else {
            lesson = lessonFound.get();
%>

<div class="container panel panel-default" syle="padding-bottom: 20px;">

        <div class="row panel-body">
            <h1><%=lesson.getDescription()%></h1>

        <br>

        <% if (loggedIn.isPresent() && loggedIn.get().isTeacher()) { %>
            <br>
            <jsp:include page="/restricted/teacher/newsection.jsp"></jsp:include>
         <% }%>

        <h2>Sections</h2>
        <div class="">
            <% for(Section section : lesson.getSections()) { %>
                <div class="">
                    <% if(loggedIn.isPresent() && loggedIn.get().isTeacher()) { %>
                        <a href="/restricted/teacher/course/lesson/section?id=<%=section.getId()%>"><li class="h4 list-group-item list-group-item-info"><%=section.getDescription()%></li></a>
                    <%} else { %>
                        <li class="h4 list-group-item list-group-item-info"><%=section.getDescription()%></li>
                    <% }%>
                </div>
                <div class="col-md-offset-2">
                    <% for(Material material : section.getMaterial()) { %>
                    <a href="/restricted/material/get?id=<%=material.getId()%>"><li class="h4 list-group-item"><%=material.getDescription()%></li></a>
                    <% } %>
                </div>
            <%}%>
        </div>
    </div>


</div>

<% } }%>

</body>
</html>