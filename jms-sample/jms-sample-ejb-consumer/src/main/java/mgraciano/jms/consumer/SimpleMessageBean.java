package mgraciano.jms.consumer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java:/jms/queue/ExpiryQueue")
})
public class SimpleMessageBean implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(SimpleMessageBean.class.getName());

    @Override
    public void onMessage(final Message msg) {
        try {
            TextMessage message = (TextMessage) msg;
            final String text = message.getText();
            LOGGER.log(Level.INFO, String.format("Got message '%s' from %s", text, message.getJMSReplyTo()));
        } catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
