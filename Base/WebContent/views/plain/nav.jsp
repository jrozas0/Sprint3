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
      <a class="navbar-brand" href="/Base/Main/home">DokkuLearning</a>
    </div>
    <div class="collapse navbar-collapse" id="navbar-ex-collapse">
      <ul class="nav navbar-nav navbar-right">

        <li><a href="/Base/Main/home">Home</a></li>

        <li><a href="/Base/Main/courses">Courses</a></li>

          <%  Integer userId = (Integer) session.getAttribute("userId");
            if (userId != null) { %>

              <li><a href="/Base/Main/profile?id=<%=userId%>">My Profile</a></li>

              <li><a href="/Base/Main/logout">Logout</a></li>

        <%  } else { %>

              <li><a href="/Base/Main/login">Login</a></li>

          <% } %>
      </ul>
    </div>
  </div>
</div>