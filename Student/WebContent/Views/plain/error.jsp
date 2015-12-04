<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lib.controllers.ex.Error" %>
<html>
<head>
  <jsp:include page="assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<%//set HTTP status code to not found
  Error error = (Error) request.getAttribute("in");
  response.setStatus(error.getCode());
%>

<div class="container">
  <div class="row">
    <div class="col-md-12 text-center">
      <h1 class="h1color">
        <b>
          <%=error.getCode()%>! <%=error.getMessage()%>
        </b>
      </h1>
    </div>
  </div>
</div>

</body>
</html>
