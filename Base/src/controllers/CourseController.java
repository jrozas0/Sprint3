package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import beans.managers.CategoryManager;
import lib.controllers.View;

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
	
	public static View readChat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/event-stream");   
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
		try {

			//Initialize context and session
			
			javax.naming.InitialContext jndiContext = new javax.naming.InitialContext(); 					
			ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("tiwconnectionfactory"); 
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
				mensaje = mc.receive(2000);
				if (mensaje != null) {
					if (mensaje instanceof TextMessage) {
						TextMessage m = (TextMessage) mensaje;
			            writer.write("Message: "+ m.getText() +"\n\n");	
			            writer.flush();
			            System.out.println("got message " + m.getText());
		            } else {
						// JHC ************ Not the right type
						//break;

					}
				} else // there are no messages
					{
					System.out.println(" Waiting for more messages </br>");
					//break;
				}

			}

			//Close the session
			//ses.close();

			//Close the connection
			//con.close();

		} catch (Exception e) {
			System.out.println(
				"JHC *************************************** Error in doPost: "
					+ e);
		}
		
		return View.Ok();
			
	}
	
	public static View sendChat(
			javax.servlet.http.HttpServletRequest request)
			throws javax.servlet.ServletException, java.io.IOException {
						

			try {

				//Init context and session ...........................................
				
				javax.naming.InitialContext jndiContext = new javax.naming.InitialContext(); 					
				ConnectionFactory connectionFactory = (ConnectionFactory) jndiContext.lookup("tiwconnectionfactory"); 
				Queue queue = (Queue) jndiContext.lookup("tiwqueue");
						
				
				// - Now we write the message
				
				// First create a conection
				 //javax.jms.QueueConnection Qcon = null; //... COMPLETE ....
				 
				 Connection connection = connectionFactory.createConnection();
			      
			      
				// Next create the session. Indicate that transaction will not be supported
				 //javax.jms.QueueSession QSes = null; //... COMPLETE ....

				 Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

					
					// Assign the Queue to the session to create the sender
					 javax.jms.QueueSender Qsen =  null; //... COMPLETE ....
					 
				 MessageProducer messageProducer = session.createProducer(queue);

					// Create a text message
				 javax.jms.TextMessage men = null; //... COMPLETE ....

				 
				 TextMessage textMessage = session.createTextMessage();



				//  We retrieve the message from the parameter and assign in to the one of the queue
				 //men.setText(request.getParameter("message"));
				 textMessage.setText(request.getParameter("message"));

				// Send the message through the sender
				messageProducer.send(textMessage);


				// Close the producer
				// ... COMPLETE ...
				messageProducer.close();	
				
				// Close the session 
				// ... COMPLETE ...
				session.close();
				
				// Close the connection 
				// ... COMPLETE ...
				connection.close();
				

				System.out.println("message sent");
				
			} catch (javax.jms.JMSException e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e);
				System.out.println(
					"JHC *************************************** Error MQ: "
						+ e.getLinkedException().getMessage());
				System.out.println(
					"JHC *************************************** Error MQ: "
						+ e.getLinkedException().toString());		
				System.out.println(" Error when sending the message</BR>");
		
				
			}catch (Exception e) {
				System.out.println(
					"JHC *************************************** Error in doPost: "
						+ e.toString());
				
			}
			return View.Ok();
			
		}

	
	
	
	
	
}
