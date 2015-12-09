<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="../plain/assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="../plain/nav.jsp"></jsp:include>

<div class="container">
  <div class="row">

    <% if (request.getAttribute("saved") == null) { %>

    <form class="form-horizontal" role="form" method="POST" action="/restricted/teacher/invite">
      <div class="form-group">
        <div class="col-sm-4">
          <input type="email" name="email" class="form-control" placeholder="Teacher's email">
        </div>
      </div>
      <br>
      <input  type="hidden" name="course" value="<%=request.getParameter("id")%>">
      <div class="form-group">
        <div class="col-sm-12">
          <button type="submit" class="btn btn-default">Submit</button>
        </div>
      </div>
    </form>

    <% if (request.getAttribute("notvalid") != null) { %>
        <p>A teacher with this email was not found.</p>
    <% } %>

    <% } else { %>


      <h1>Your invitation has been sent!</h1>

    <% } %>

  </div>
</div>

<body>
<head>