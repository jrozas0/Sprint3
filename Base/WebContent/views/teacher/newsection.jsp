<%@ page import="Models.Lesson" %>
<%@ page import="java.util.Optional" %>

<div class="">
  <form class="form-horizontal" role="form" method="POST" action="/restricted/teacher/course/lesson/section">
    <div class="form-group">
      <div class="col-sm-4">
        <input type="text" name="description" class="form-control" placeholder="Section name">
      </div>
    </div>
    <input  type="hidden" name="lesson" value="<%=request.getParameter("id")%>">
    <div class="form-group">
      <div class="col-sm-12">
        <button type="submit" class="btn btn-default">Submit</button>
      </div>
    </div>
  </form>
</div>