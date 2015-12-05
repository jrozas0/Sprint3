<%@ page import="beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar navbar-default">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">DokkuLearning</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-ex-collapse">
      <ul class="nav navbar-nav navbar-right">

        <li><a href="/">Home</a></li>

        <li><a href="/courses">Courses</a></li>

          <%  User loggedIn = (User) session.getAttribute("user");
            if (loggedIn != null) { %>

              <li><a href="<%="/profile?id=" + loggedIn.getId()%>">My Profile</a></li>

              <li><a href="/courses">Courses</a></li>

              <li><a href="/logout">Logout</a></li>

        <%  } else { %>

              <li><a href="/login">Login</a></li>

          <% } %>
      </ul>
    </div>
  </div>
</div>