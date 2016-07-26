package mgraciano.jms.request;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.TextMessage;

@Stateless
//@JMSDestinationDefinition(
//        name = "java:app/jms/ShippingRequestQueue",
//        interfaceName = "javax.jms.Queue",
//        destinationName = "jms/ShippingRequestQueue"
//)
//@JMSConnectionFactoryDefinition(
//        name = "java:app/jms/QueueConnectionFactory",
//        user = "jms-user",
//        password = "jms.123"
//)
@Named("messenger")
public class SimpleMessageSender {

    @Inject
    @JMSConnectionFactory("java:/ConnectionFactory")
    private JMSContext context;

    @Resource(name = "java:/jms/queue/ExpiryQueue")
    private Destination destination;

    @Resource(name = "java:/jms/queue/DLQ")
    private Destination reply;

    public void sendMessage(final String text) throws JMSException {
        final TextMessage message = context.createTextMessage("Mensagem de teste: " + text);
        message.setJMSReplyTo(reply);

        final JMSProducer producer = context.createProducer();
        producer.send(destination, message);
    }

    public void sendMessage() throws JMSException {
        final TextMessage message = context.createTextMessage("Mensagem de teste");
        message.setJMSReplyTo(reply);

        final JMSProducer producer = context.createProducer();
        producer.send(destination, message);
    }
}
