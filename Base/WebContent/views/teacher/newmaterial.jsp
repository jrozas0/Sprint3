<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="/../views/plain/assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="/../views/plain/nav.jsp"></jsp:include>

    <div class="container">
        <div class="row">

            <% if (request.getAttribute("saved") == null) { %>

                <form class="form-horizontal" role="form" method="POST" enctype="multipart/form-data" action="/restricted/teacher/course/lesson/section/material">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <input type="text" name="description" class="form-control" placeholder="Material name">
                        </div>
                    </div>
                    <input type="file" name="file">
                    <br>
                    <input  type="hidden" name="section" value="<%=request.getParameter("id")%>">
                    <div class="form-group">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-default">Submit</button>
                        </div>
                    </div>
                </form>

            <% } else { %>


                <h1>Your material has been submitted!</h1>

            <% } %>

        </div>
    </div>

<body>
<head>