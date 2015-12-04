<%@page import="beans.Userteaching"%>
<%@ page import="java.util.Optional" %>
<%@ page import="controllers.UserController" %>
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
			
			User user = (User) request.getAttribute("in");
			boolean isLogged = UserController.isLoggedIn(user, request);
			
			%>
			

            <div class="section">
                <div class="container panel panel-default" style="padding-bottom: 20px;">
                    <div class="panel-body">
                    <div class="row">
                        <h1><%= user.getName()%> <%= user.getSurname()%></h1>
                        <br>
                        <img style="height: 100px; width: 100px;" src="/profile/picture?id=<%=%>">
                        <br>
                        <br>
                        <br>
                        <p>Nick-name: <%= user.getNick() %><p>
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
                                        <% for(Userteaching teach : user.getUserteachings()) {
                                        	Course course = teach.getCourse();
                                            if (!course.isDenied()) {
                                                out.print("<a href=\"/restricted/course?id=" + course.getId() + "\"><li class=\"h4 list-group-item list-group-item-success\">" + course.getTitle());
                                                if (!course.isValid()) {
                                                    out.print(" [awaiting admin confirmation]</li></a>");
                                                } else out.print("</li></a>");
                                                if (course.isDenied()) {
                                                    out.print("<li class=\"h4 list-group-item list-group-item-danger\">" + course.getTitle());
                                                    out.print(" [course denied]</li></a></li>");
                                                }
                                            }
                                        } %>
                                    </ul>

                                    <%if(isLogged) { %>
                                        <h2>Accept Invitations</h2>
                                        <ul class="list-group">
                                        TODO
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

                    			<% } %>

                         
                    </div>
                    </div>
                    </div>
                </div>
            </div>
</body>
</html>