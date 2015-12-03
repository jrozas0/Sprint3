<%@ page import="java.util.Optional" %>
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
    Long userId = null;

    try {
        userId = Long.parseLong(request.getParameter("id"));
    } catch(NullPointerException e1) {
        response.sendError(404);
    } catch(NumberFormatException e2) {
        response.sendError(404);
    }

    if (userId == null) {
        //parameter not passed
        response.sendError(404);
    } else {
        Optional<User> foundUser = User.getTable(User.class).getById(userId);

        if (!foundUser.isPresent()) {
            //user not found
            response.sendError(404);
            return;
        } else {

            User user = foundUser.get();

            boolean isLogged = false;
            User logged = ((User) session.getAttribute("user"));
            if (logged != null && logged.getId() == user.getId()) {
                isLogged = true;
            }

            if(user.isAdmin() && !isLogged) {
                response.sendError(401);
            }
%>

            <div class="section">
                <div class="container panel panel-default" style="padding-bottom: 20px;">
                    <div class="panel-body">
                    <div class="row">
                        <h1><%= user.getName()%> <%= user.getSurname()%></h1>
                        <br>
                        <img style="height: 100px; width: 100px;" src="/profile/picture?id=<%=logged.getId()%>">
                        <br>
                        <br>
                        <br>
                        <p>Nick-name: <%= user.getNickname() %><p>
                            <p>Email:<%= user.getEmail() %></p>
                            <p>Description: <%= user.getDescription() %></p>
                            <p>Age: <%= user.getAge() %></p>
                            <% if(isLogged) { %>
                                <p>Telephone number: <%= user.getPhone() %></p>
                                <p>Address: <%= user.getAddress() %></p>
                                <% if(isLogged) { %><br><a href="/restricted/edit">Edit details</a><% }%>
                            <%}%>

                    </div>
                    <div class="row">
                        <ul class="col-md-12">
                            <% if(user.isTeacher()) { %>
                                    <h2>Teaching</h2>

                                    <% if(isLogged) { %><br><a href="/restricted/teacher/course">Add course</a><% }%>

                                    <ul class="list-group">
                                        <% for(Course course : ((Teacher) user).getTeaching()) {
                                            if (!course.isDenied() && course.exists()) {
                                                out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle());
                                                if (!course.isValid()) {
                                                    out.print(" [awaiting admin confirmation]</li></a>");
                                                } else out.print("</li></a>");
                                            }
                                        } %>
                                        <% for(Course course : ((Teacher) user).getTeaching()) {
                                            if (course.isDenied() && course.exists()) {
                                                out.print("<li class=\"h4 list-group-item list-group-item-danger\">" + course.getTitle());
                                                out.print(" [course denied]</li></a></li>");
                                            }
                                        } %>
                                    </ul>

                                    <%if(isLogged) { %>
                                        <h2>Accept Invitations</h2>
                                        <ul class="list-group">
                                            <% for(Course course : ((Teacher) user).getInvited())
                                                out.print("<a href=\"/restricted/teacher/invite/accept?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle() + "</li></a>");
                                            %>
                                        </ul>
                                    <%  }  %>

                            <% } if(user.isStudent()) { %>
                                    <h2>Attending</h2>
                                    <ul class="list-group">
                                        <% for(Course course : ((Student) user).getAttending()) {
                                            out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle() + "</li></a>");
                                        } %>
                                    </ul>

                                    <h2>Achievements</h2>
                                    <ul class="list-group">
                                        <% for(Certificate certificate : ((Student) user).getCertificates()) {
                                            out.print("<li class=\"h4 list-group-item list-group-item-success\">" + certificate.getCourseName() + "</li>");
                                        } %>
                                    </ul>

                                    <h2>Wishlist</h2>
                                    <ul class="list-group">
                                        <% for(Course course : ((Student) user).getWishlist()) {
                                            if (!((Student) user).isAttending(course) && course.exists()) {
                                                out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle() + "</li></a>");
                                            }
                                        } %>
                                    </ul>

                        </ul>

                        <% } %>

                            <% if(user.isAdmin()) { %>

                                <h2>Courses pending approval</h2>
                                <ul class="list-group">
                                    <% for(Course course : Course.getTable(Course.class).allAsList()) {
                                        if (!course.isValid() && !course.isDenied() && course.exists())
                                            out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle() + "</li></a>");
                                        }
                                    %>
                                </ul>

                                <h2>Courses denied</h2>
                                <ul class="list-group">
                                    <% for(Course course : Course.getTable(Course.class).allAsList()) {
                                        if (course.isDenied() && course.exists() && course.exists())
                                            out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-danger\">" + course.getTitle() + "</li></a>");
                                    } %>
                                </ul>


                                <h2>Courses Highlighted</h2>
                                    <ul class="list-group">
                                        <% for(Course course : Course.getTable(Course.class).allAsList()) {
                                            if (course.isHighlighted() && course.isValid() && course.exists())
                                                out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle() + "</li></a>");
                                        } %>
                                    </ul>

                                <% } %>

                        <% if(!user.isAdmin()) { %>
                            <br>
                            <br>
                            <div class="row">
                                <a class="col-md-2" href="/profile/remove"><button type="button" class="btn btn-sm btn-danger">Delete account</button></a>
                            </div>
                        <% } %>

                    </div>
                    </div>
                    </div>
                </div>
            </div>

        <% }

    }
%>
</body>
</html>