<%@ page import="beans.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <jsp:include page="plain/assets.jsp"></jsp:include>
  <title></title>
</head>
<body>
<jsp:include page="plain/nav.jsp"></jsp:include>
<div class="container">
  <div class="row">
          <%  //handle response from the server, from when the form is submitted
          boolean edit = false;
          User user = null;

          if(request.getAttribute("registered") != null) { %>
              <h1>You have been registered!</h1>
              <h3>You can login <a href="login">here</a></h3>
          <% } else {%>

                <%
                    if(request.getAttribute("edituser") != null) {
                        //handle when the user is editing it's details
                        edit = true;
                        user = (User) request.getSession().getAttribute("user");
                    }
                %>
      <form class="form-horizontal" role="form" action="" enctype="multipart/form-data" method="post">

          <div class="col-md-6">

          <div class="form-group">
                  <div class="col-sm-2">
                      <label class="control-label">Email</label>
                  </div>
                  <div class="col-sm-10">
                      <input name="email" type="email" class="form-control" placeholder="Email" value="<% if (edit) out.print(user.getEmail());%>">
                  </div>
              </div>
              <div class="form-group">
                  <div class="col-sm-2">
                      <label class="control-label">Nickname</label>
                  </div>
                  <div class="col-sm-10">
                      <input name="nickname" class="form-control"  placeholder="Nickname" value="<% if (edit) out.print(user.getNick());%>">
                  </div>
              </div>
              <div class="form-group">
                  <div class="col-sm-2">
                      <label class="control-label">Name</label>
                  </div>
                  <div class="col-sm-10">
                      <input name="name" class="form-control"  placeholder="Name" value="<% if (edit) out.print(user.getName());%>">
                  </div>
              </div>
              <div class="form-group">
                  <div class="col-sm-2">
                      <label class="control-label">Surname</label>
                  </div>
                  <div class="col-sm-10">
                      <input name="surname" class="form-control"  placeholder="Surname" value="<% if (edit) out.print(user.getSurname());%>">
                  </div>
              </div>
              <div class="form-group">
                  <div class="col-sm-2">
                      <label class="control-label">Age</label>
                  </div>
                  <div class="col-sm-10">
                      <input name="age" type="number" class="form-control"  placeholder="Age" value="<% if (edit) out.print(user.getAge());%>">
                  </div>
              </div>
              <div class="col-md-offset-2" style="padding-bottom: 25px;">
                  <input type="file" name="file">
              </div>
          </div>
      <div class="col-md-6">
          <div class="form-group">
              <div class="col-sm-2">
                  <label class="control-label">Description</label>
              </div>
              <div class="col-sm-10">
                  <input name="description" type="text" class="form-control"  placeholder="Description" value="<% if (edit) out.print(user.getDescription());%>">
              </div>
          </div>
          <div class="form-group">
              <div class="col-sm-2">
                  <label class="control-label">Address</label>
              </div>
              <div class="col-sm-10">
                  <input name="address" class="form-control"  placeholder="Address" value="<% if (edit) out.print(user.getAddress());%>">
              </div>
          </div>
          <div class="form-group">
              <div class="col-sm-2">
                  <label class="control-label">Phone</label>
              </div>
              <div class="col-sm-10">
                  <input name="phone" type="tel" class="form-control"  placeholder="Phone" value="<% if (edit) out.print(user.getPhone());%>">
              </div>
          </div>
          <div class="form-group">
              <div class="col-sm-2">
                  <label class="control-label">Paypal</label>
              </div>
              <div class="col-sm-10">
                  <input name="paymentData" type="email" class="form-control"  placeholder="Paypal" value="<% if (edit) out.print(user.getPaymentData());%>">
              </div>
          </div>
          <div class="form-group">
              <div class="col-sm-2">
                  <label class="control-label">Password</label>
              </div>
              <div class="col-sm-10">
                  <input name="password" type="password" class="form-control" placeholder="Password" value="<% if (edit) out.print(user.getPassword());%>">
              </div>
          </div>
      </div>

          <div class="form-group">
              <div class="col-sm-offset-1 col-sm-11">
                  <% if (!edit) {%>
                  <input type="checkbox" name="teacher"> I am a teacher<br><br>
                  <%}%>
                  <button type="submit" class="btn btn-default btn-lg"><% if (edit) out.print("Update"); else out.print("Register");%></button>
              </div>
          </div>

      <div class="row">
            <div class="col-sm-12">
                <%
                    //handle response from the server, from when the form is submitted
                    if(request.getAttribute("alreadyexists") != null) {
                        out.println("<p class=\"col-sm-offset-2\">A user with that email already exists.</p>");
                    }
                    if(request.getAttribute("notvalid") != null) {
                        out.println("<p class=\"col-sm-offset-2\">Form not entered correctly</p>");
                    }
                %>
            </div>
      </div>
      </form>

  <% } %>

    </div>
</div>
</body>
</html>