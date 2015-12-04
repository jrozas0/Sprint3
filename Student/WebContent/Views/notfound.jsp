<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="plain/assets.jsp"></jsp:include>
    <title></title>
</head>
<body>
<jsp:include page="plain/nav.jsp"></jsp:include>

<%//set HTTP status code to not found
  response.setStatus(404);
%>

<div class="container">
  <div class="row">
    <div class="col-md-12 text-center">
      <h1 class="h1color">
        <b>
            404! Page not found
        </b>
      </h1>
      <p class="text-inverse">
        <%
          if (request.getAttribute("notfound") != null) {
            out.print(request.getAttribute("notfound"));
          }
        %>
      </p>
    </div>
  </div>
</div>

</body>
</html>

