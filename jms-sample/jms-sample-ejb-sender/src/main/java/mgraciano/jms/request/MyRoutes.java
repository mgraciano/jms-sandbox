package mgraciano.jms.request;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.Uri;

@ApplicationScoped
public class MyRoutes extends RouteBuilder {

    @Inject
    @Uri("timer:foo?period=5000")
    private Endpoint inputEndpoint;

    @Inject
    @Uri("log:output")
    private Endpoint resultEndpoint;

    @Override
    public void configure() {
        // you can configure the route rule with Java DSL here
        from(inputEndpoint)
                .to("bean:counterBean")
//                .to("bean:messenger")
                .to("jms:java:/jms/queue/ExpiryQueue")
                .to(resultEndpoint);
    }

}
