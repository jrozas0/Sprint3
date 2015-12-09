<%@ page import="java.util.Optional" %>
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
  Long materialId = null;
  Material material = null;
  try {
    materialId = Long.parseLong(request.getParameter("id"));
  } catch(NullPointerException e1) {
    response.sendError(404);
  } catch(NumberFormatException e2) {
    response.sendError(404);
  }

  if (materialId == null) {
    response.sendError(404);
  } else {
    Optional<Material> materialFound = Material.getTable(Material.class).getById(materialId);
    if (!materialFound.isPresent()) {
      //user not found
      response.sendError(404);
    } else {
      material = materialFound.get();
%>

<div class="container">

  <div class="row">
    <h1><%=material.getDescription()%></h1>
    <br>
  </div>

  <div class="row">
    <h2>File</h2>
    <div class="list-group col-md-4">
      <a href="<%=material.getFileLink()%>"><li class="h4 list-group-item list-group-item-info"><%=material.getfileName()%></li></a>
    </div>
  </div>


</div>

<% } }%>

</body>
</html>
