package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Enumeration;
import java.util.Optional;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import beans.Category;
import beans.User;
import beans.Userattending;
import beans.Userteaching;
import beans.managers.CategoryManager;
import beans.managers.CourseManager;
import beans.managers.DataSource;
import beans.Course;
import beans.managers.CategoryManager;
import beans.managers.CourseManager;
import lib.controllers.Action;
import lib.controllers.View;
import lib.controllers.ex.BadRequest;

public class CourseController {

	public static Optional<Category> coursesForCat(HttpServletRequest req) {
		Long id = null;
		try {
			 id = Long.parseLong(req.getParameter("category"));
		} catch(NumberFormatException e) {}
		if (id == null) return Optional.empty();
		else return Optional.ofNullable(CategoryManager.getById(id));
	}
	
	
	public static View newCourse(HttpServletRequest req) {
		
		int id = 0;
		byte denied = 0;
		String deniedInput = null;
		String description = null;
		BigInteger difficulty = null;
		BigInteger duration = null;
		byte highlighted = 0;
		String highlightedInput = null;
		String picture = null;
		int price = 0;
		int promotionPrice = 0;
		boolean isDiscounted = false;
		String discountedInput = null;
		String syllabus = null;
		String title = null;
		byte valid = 0;
		String validInput = null;
		
		id = Integer.parseInt(req.getParameter("id"));
		deniedInput = req.getParameter("denied");
		if (deniedInput!= null){
			denied = 1;
		}
		description = req.getParameter("description");
		difficulty = new BigInteger(req.getParameter("difficulty"));
		duration = new BigInteger(req.getParameter("duration"));
		highlightedInput = req.getParameter("highlighted");
		if(highlightedInput != null){
			highlighted = 1;
		}
		picture = req.getParameter("picture");
		price = Integer.parseInt(req.getParameter("price"));
		promotionPrice = Integer.parseInt(req.getParameter("promotionPrice"));
		discountedInput = req.getParameter("isDiscounted");
		if (discountedInput != null ){
			isDiscounted = true;
		}
		syllabus = req.getParameter("syllabus");
		title = req.getParameter("title");
		validInput = req.getParameter("valid");
		if(validInput != null){
			valid = 1;
		}
        if (Action.validate(req, id,denied, description, difficulty, duration, highlighted, picture, price, promotionPrice, discountedInput, syllabus, title, valid)) {
		
		Course course = new Course();
		course.setId(id);
		course.setDenied(denied);
		course.setDescription(description);
		course.setDifficulty(difficulty);
		course.setDuration(duration);
		course.setHighlighted(highlighted);
		course.setPicture(picture);
		course.setPrice(price);
		course.setPromotionPrice(promotionPrice);
		course.setDisccounted(isDiscounted);
		course.setSyllabus(syllabus);
		course.setValid(valid);
		course.setTitle(title);
		
		CourseManager.save(course);
		return View.FinateState("/views/course.jsp", "ok");
        }else{
        	throw new BadRequest();
        }
	}
	
	//each user-course pair will have it's own queue.
	public static String getQueueName(Integer userId, String courseId) {
		return "user-" + userId + "-course-" + courseId;
	}
	
	public static List<String> readChat(HttpServletRequest request) throws ServletException, IOException {
		if (UserController.getFromSession(request) == null || request.getParameter("course") == null)
			throw new BadRequest();
		User user = UserController.getFromSession(request);
		List<String> conv = new ArrayList<>();
		try {
			javax.naming.InitialContext jndiContext = new javax.naming.InitialContext(); 					
			ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("tiwconnectionfactory"); 
			Queue queue = (Queue) jndiContext.lookup(getQueueName(user.getId(), request.getParameter("course")));
			
			javax.jms.Connection con = connectionFactory.createConnection();

			javax.jms.Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

			con.start();

			javax.jms.MessageConsumer mc= ses.createConsumer(queue);
			
			Message mensaje = null;
			while (true) {
				mensaje = mc.receive(2000);
				if (mensaje != null) {
					if (mensaje instanceof TextMessage) {
						TextMessage m = (TextMessage) mensaje;
			            String msg= m.getStringProperty("sender") + ": "+ m.getText() +"\n\n";	
		            	conv.add(msg);
		            } else {
						break;
					}
				} else  { // there are no messages
					break;
				}

			}
			ses.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conv;
	}
	
	//the best thing to do would be to create new student topics programmatically.
	//but apparently we don't know if it's possible. Either way we think our implementation is the best option
	public static View sendChat(javax.servlet.http.HttpServletRequest request) throws javax.servlet.ServletException, java.io.IOException, NamingException, JMSException {
						
		if (UserController.getFromSession(request) == null || request.getParameter("message") == null || request.getParameter("course") == null)
			throw new BadRequest();
		
		User user = UserController.getFromSession(request);
		
		javax.naming.InitialContext jndiContext = new javax.naming.InitialContext(); 					
		ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("tiwconnectionfactory"); 
		
		//when a message is sent, make sure to redirect it to all the users that must receive it
		
		Optional<Course> course = CourseManager.getById(Integer.parseInt(request.getParameter("course")));
		if (!course.isPresent()) throw new BadRequest();
		for(Userattending attending: course.get().getUserattendings()) {
			User at = attending.getUser();
			pushChat(jndiContext, connectionFactory, user, at, course.get(), request.getParameter("message"));
		}
		
		for(Userteaching teaching: course.get().getUserteachings()) {
			User at = teaching.getUser();
			pushChat(jndiContext, connectionFactory, user, at, course.get(), request.getParameter("message"));
		}
																	
		return View.Ok();
			
	}
	
	public static void pushChat(javax.naming.InitialContext jndiContext, ConnectionFactory connectionFactory, User sender, User receiver, Course course, String message) throws JMSException, NamingException {
		Queue queue = (Queue) jndiContext.lookup(getQueueName(receiver.getId(), course.getId() + ""));
		
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer messageProducer = session.createProducer(queue); 
		TextMessage textMessage = session.createTextMessage();

		textMessage.setText(message);
		textMessage.setStringProperty("sender", sender.getName());
		
		messageProducer.send(textMessage);
		messageProducer.close();	
		
		session.close();
		connection.close();

	}
	
	
}
