<%@ page import="java.util.Optional" %>
<%@ page import="java.util.function.Predicate" %>
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
    Long courseId = null;
    try {
        courseId = Long.parseLong(request.getParameter("id"));
    } catch(NullPointerException e1) {
        response.sendError(404);
    } catch(NumberFormatException e2) {
        response.sendError(404);
    }

  if (courseId == null) {
    response.sendError(404);
  } else {
      User loggedIn = User.getTable(User.class).getById(((User) session.getAttribute("user")).getId()).get();
      Optional<Course> courseFound = Course.getTable(Course.class).getById(courseId);
    if (!courseFound.isPresent() || (courseFound.get().isDenied() && !loggedIn.isAdmin())) {
      //user not found
      response.sendError(404);
    } else {
        Course course = courseFound.get();

        //this should be in the models, fix this later
        final Long finalCourseId = courseId;
        boolean isUserAttending = loggedIn.isStudent() && ((Student) loggedIn).getAttending().all().anyMatch(new Predicate<Course>() {
            @Override
            public boolean test(Course course) {
                return course.getId() == finalCourseId;
            }
        });
        final Long finalCourseId1 = courseId;
        boolean isUserTeaching = loggedIn.isTeacher() && ((Teacher) loggedIn).getTeaching().all().anyMatch(new Predicate<Course>() {
            @Override
            public boolean test(Course course) {
                return course.getId() == finalCourseId1;
            }
        });
        boolean isUserAdmin = loggedIn.isAdmin();
        boolean wished = loggedIn.isStudent() && ((Student) loggedIn).isWishing(course);
        boolean isOwner = course.getOwner().getId() == loggedIn.getId();
%>

<div class="container panel panel-default" style="padding-bottom: 20px;">

    <div class="row panel-body">

            <h1><%=course.getTitle()%></h1>
            <br>
            <img style="height: 100px; width: 100px;" src="/restricted/course/picture?id=<%=course.getId()%>">
            <br>
            <br>
            <br>
            <p><a class="label label-default" href="/courses?category=<%=course.getCategory().getId()%>"><%=course.getCategory().getName()%></a></p>
            <p><%=course.getDescription()%></p>
            <p><strong>Difficulty: </strong>
                <%
                    if (course.getDifficulty() == 1) {
                        out.print("Basic");
                    } if (course.getDifficulty() == 2) {
                        out.print("Intermediate");
                    } if (course.getDifficulty() == 3) {
                        out.print("Advanced");
                    }
                %>
                <% if (!course.hasDiscount()) { %>
                    <p><strong>Price: </strong><%=course.getPrice()%> euros</p>
                <% } else { %>
                     <p><strong>Price: </strong><strike><%=course.getPrice()%></strike> <%=course.getDiscountedPrice()%> euros</p>
                <% } %>
            <% if(isOwner) { %>
                <br><a href="/restricted/teacher/course?edit=<%=course.getId()%>">Edit details</a><br>
                <% if(course.isValid()) { %>
                    <br><a href="/restricted/teacher/invite?id=<%=course.getId()%>">Invite staff</a>
                <% }%>
            <%}%>

        <% if (isUserAdmin) { %>

            <div class="row">

                <% if(!course.isValid() && !course.isDenied()) { %>
                    <a class="col-md-2" href="/restricted/admin/approve?course=<%=course.getId()%>"><button type="button" class="btn btn-success btn-lg">Approve</button></a>
                    <a class="col-md-1" href="/restricted/admin/deny?course=<%=course.getId()%>"><button type="button" class="btn btn-danger btn-lg">Deny</button></a>
                <% } if(course.isValid() && !course.isDenied()) { %>
                    <a class="col-md-2" href="/restricted/admin/deny?course=<%=course.getId()%>"><button type="button" class="btn btn-danger btn-lg">Deny</button></a>
                <% } if(!course.isValid() && course.isDenied()) { %>
                    <a class="col-md-1" href="/restricted/admin/approve?course=<%=course.getId()%>"><button type="button" class="btn btn-success btn-lg">Approve</button></a><br>
                <% }%>
            </div>

        <% } %>

        <% if (isUserAdmin || isOwner) { %>

            <br>
            <br>
            <div class="row">
                <a class="col-md-2" href="/restricted/teacher/remove?course=<%=course.getId()%>"><button type="button" class="btn btn-lg btn-danger">Remove</button></a>
            </div>

        <% } %>

        <% if(!course.isValid()) { %>
            <br>
            <% if (!isUserAdmin) { %>
                    <br>
                    <p>[This course is awaiting administrator approval]</p>
            <% } %>
        <% } else { %>

    </div>
            <% if(isUserAttending || isUserTeaching || loggedIn.isAdmin())  { %>

                <% if(loggedIn.isAdmin()) {
                    if (!course.isHighlighted()) { %>
                        <a href="/restricted/admin/course/highlight?course=<%=course.getId()%>"><div class="btn btn-lg btn-info">Highlight</div></a>
                    <% } else { %>
                        <a href="/restricted/admin/course/nothighlight?course=<%=course.getId()%>"><div class="btn btn-lg btn-danger">Remove highlight</div></a>
                    <% } %>
                <% } %>

                <h2>Lessons</h2>
                        <% for(Lesson lesson : course.getLessons()) { %>
                                <a href="/restricted/lesson?id=<%=lesson.getId()%>"><li class="h4 list-group-item list-group-item-info"><%=lesson.getDescription()%></li></a>
                        <% } %>


                <% if (isUserTeaching) { %>
                    <br>
                    <jsp:include page="/restricted/lesson.jsp"></jsp:include>

                <% } else if(isUserAttending) { %>

                        <row>
                            <br>
                            <a href="/restricted/course/deregister?course=<%=course.getId()%>"><div class="btn btn-danger">Stop enrolling</div></a>
                        </row>

                    <% } %>
                    <% if (isUserTeaching && !isOwner) { %>
                    <a href="/restricted/teacher/course/leave?course=<%=course.getId()%>"><div class="btn btn-danger">Stop teaching</div></a>
                    <% }%>


        </div>



            <% } else if (!loggedIn.isAdmin() && !isUserAttending && !loggedIn.isTeacher()) { %>

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


<% }

}
%>
</body>
</html>
