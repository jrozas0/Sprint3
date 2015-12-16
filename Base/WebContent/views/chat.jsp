<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lib.controllers.View" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <jsp:include page="plain/assets.jsp"></jsp:include>
    <title></title>
</head>
<body>
<jsp:include page="plain/nav.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-md-6">   
        	<h1>Chat</h1>
        	<% List<String> chat = (List<String>) request.getAttribute("in"); %>
        	<% for(int i = 0; i < chat.size(); i++) { %>
        	<%   	out.print(chat.get(i) + "<br>");	%>
        	<% } %>
        </div>
    </div>
</div>
<jsp:include page="plain/footer.jsp"></jsp:include>
</body>
</html>