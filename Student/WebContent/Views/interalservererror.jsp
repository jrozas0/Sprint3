<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<%//set HTTP status code to not found
  response.setStatus(500);
%>

<div class="container">
  <div class="row">
    <div class="col-md-12 text-center">
      <h1 class="h1color">
        <b>
          500! Internal Server Error
        </b>
      </h1>
      <p class="text-inverse">
        <%
          if (request.getAttribute("error") != null) {
            out.print(request.getAttribute("error"));
          }
        %>
      </p>
    </div>
  </div>
</div>

</body>
</html>
