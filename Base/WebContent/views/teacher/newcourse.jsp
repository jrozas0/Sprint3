<%@ page import="java.util.Optional" %>
<%@ page import="beans.*" %>
<%@ page import="java.util.function.Predicate" %>
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

        <%  //controller code for deciding weather the jsp will be in edit mode or submit
            boolean updating = false;
            Course editing = null;
            String courseIdEdit = request.getParameter("edit");
            if (courseIdEdit != null) {
                long courseId = Long.parseLong(courseIdEdit);
                Optional<Course> course = Course.getTable(Course.class).getById(courseId);
                if (course.isPresent()) {
                    updating = true;
                    editing = course.get();
                }
            }
        %>

        <% if(request.getAttribute("saved") != null) { %>

            <h1>Your course was saved!</h1>
            <h3>You will now await administrator confirmation</h3>

        <% } else {%>

            <form class="form-horizontal" role="form" enctype="multipart/form-data" method="POST" action="">
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label" >Title</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="text" name="title" class="form-control" value="<% if (updating) out.print(editing.getTitle());%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label" >Description</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="text" name="description" class="form-control" value="<% if (updating) out.print(editing.getDescription());%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label" >Duration (hours)</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="number"  name="duration" class="form-control" value="<% if (updating) out.print(editing.getDuration());%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default btn-lg">Save</button>
                        </div>
                    </div>

                </div>
                <div class="col-md-6">

                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">Category</label>
                        </div>
                        <div class="col-sm-10">
                            <select name="category" id="category">
                                <% if (updating) out.print("<option value=\"" + editing.getCategory().getName() + "\">" + editing.getCategory().getName() + "</option>");%>
                                <% for(Category cat : Category.getTable(Category.class).allAsList()) {
                                    out.print("<option value=\"" + cat.getName() + "\">" + cat.getName() + "</option>");
                                } %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label">Picture</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="file" name="file">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label" >Syllabus</label></div>
                        <div class="col-sm-10">
                            <input type="textarea"  name="syllabus" class="form-control" value="<% if (updating) out.print(editing.getSyllabus());%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label" >Price</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="number" name="price"  class="form-control" value="<% if (updating) out.print(editing.getPrice());%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2">
                            <label class="control-label" >Difficulty</label>
                        </div>
                        <div class="col-sm-10">
                            <input type="range" name="difficulty"  min="1" max="3" class="" value="<% if (updating) out.print(editing.getDifficulty());%>">
                        </div>
                    </div>

                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <%
                            //handle response from the server, from when the form is submitted
                            if(request.getAttribute("notvalid") != null) {
                                out.println("<p class=\"col-sm-offset-1\">Form not entered correctly</p>");
                            }
                        %>
                    </div>
                </div>

            </form>

        <%}%>

    </div>
  </div>
  </div>
</body>
</html>