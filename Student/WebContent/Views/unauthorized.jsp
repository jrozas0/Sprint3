<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="plain/assets.jsp"></jsp:include>
    <title></title>
</head>
<body>
<jsp:include page="plain/nav.jsp"></jsp:include>

<%//set HTTP status code to not authorized
    response.setStatus(401);
%>

<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <h1 class="h1color">
                <b>
                    401! Not authorized to view this page
                </b>
            </h1>
            <p class="text-inverse">
                <%
                    if (request.getAttribute("denied") != null) {
                        out.print(request.getAttribute("denied"));
                    }
                %>
            </p>
        </div>
    </div>
</div>

</body>
</html>
