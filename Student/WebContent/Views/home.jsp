<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="assets.jsp"></jsp:include>
    <title></title>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>

<div class="container">
    <div class="row" style="padding: 100px;">
        <% if(request.getAttribute("loggedout") != null) { %>
            <h1 class="text-center">See ya!</h1>
        <% } else { %>
            <h1 class="text-center">Welcome</h1>
        <%}%>
    </div>
        <div class="col-md-4">
            <h3>About Us</h3>
            <p> We are a e-learning based platform that aims to revolutionise the academic experience of students and teachers alike. </p>
        </div>
        <div class="col-md-4">
            <h3>How does it work?</h3>
            <p>Teachers can create courses were they publish materials. Students can enroll and see those materials.</p>
        </div>
        <div class="col-md-4">
            <h3>How it it different?</h3>
            <p>It is not. This is a dumbed down version of moodle.</p>
        </div>
</div>

</body>
</html>