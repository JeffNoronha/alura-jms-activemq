package br.com.caelum.log;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TesteProdutorFila {

	public static void main(String[] args) throws Exception {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("log");
		
		MessageProducer producer = session.createProducer(fila);
		
		Message message = session.createTextMessage("INFO ! ActiveMQ log 5456481161-4484 192.168.0.0");
		producer.send(message, DeliveryMode.NON_PERSISTENT, 3, 5000);
		
		//Message / DeliveryMode / Priority / TimeToLive
		
		session.close();
		connection.close();
		context.close();
		
	}

}
