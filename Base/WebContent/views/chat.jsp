<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lib.controllers.View" %>
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
        	
        </div>
    </div>
</div>
<jsp:include page="plain/footer.jsp"></jsp:include>
</body>
</html>

<script type="text/javascript">
	$(document).ready(function() {
        var source = new EventSource('http://localhost:8080/Base/Main/course/chat/get'); 
        source.onmessage=function(event)
        {
            console.log(event.data);
        };
	});
</script>