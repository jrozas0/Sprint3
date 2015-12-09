package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Optional;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import beans.managers.CategoryManager;
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
		return View.Simple("");
	}
	
	public static View sendChat(HttpServletRequest req) throws JMSException, NamingException {
		String message = null;
		Integer courseId = null;
		try {
			message = (String) req.getParameter("message");
			System.out.println(message);
			//courseId = Integer.parseInt(req.getParameter("course"));
			courseId = 1;
		} catch(NumberFormatException e) {}
		if (message == null | courseId == null) throw new BadRequest();
		
		javax.naming.InitialContext jndiContext = new javax.naming.InitialContext(); 					
		ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("tiwconnectionfactory"); 
		Queue queue = (Queue) jndiContext.lookup("tiwqueue");
		Connection connection = connectionFactory.createConnection();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer messageProducer = session.createProducer(queue);
		TextMessage textMessage = session.createTextMessage(message);
		System.out.println(textMessage.getText());
		messageProducer.send(textMessage);
		messageProducer.close();	
		session.close();
		connection.close();
		return View.Ok();
	}
	
	public static View receiveChat(HttpServletRequest req) throws NamingException, JMSException, IOException {
		// TODO Auto-generated method stub

		try {

			//Initialize context and session
			
			javax.naming.InitialContext jndiContext = new javax.naming.InitialContext(); 					
			QueueConnectionFactory connectionFactory = (QueueConnectionFactory) jndiContext.lookup("tiwconnectionfactory"); 
			Queue queue = (Queue) jndiContext.lookup("tiwqueue");
			
			
			//Create the connection
			javax.jms.Connection con = connectionFactory.createConnection();

			//Create the session (no trasactions)
			javax.jms.Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);

			//Initi connection
			con.start();

			//Create a consumer
			javax.jms.MessageConsumer mc= ses.createConsumer(queue);
			
			Message mensaje = null;
			while (true) {
				mensaje = mc.receive(1000);
				if (mensaje != null) {
					System.out.println("some" + mensaje);
					if (mensaje instanceof TextMessage) {
						
						TextMessage m = (TextMessage) mensaje;
						System.out.println(m.getText());
					} else {
						// JHC ************ Not the right type
						//break;

					}
				} else // there are no messages
					{
					System.out.println("no more messages");
					break;
				}

			}

			//Close the session
			ses.close();

			//Close the connection
			con.close();

		} catch (Exception e) {
			System.out.println(
				"JHC *************************************** Error in doPost: "
					+ e);
		}
			
		return View.Ok();
	}
	
}
