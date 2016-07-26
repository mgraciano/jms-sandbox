package mgraciano.jms.request;

import javax.ejb.Singleton;
import javax.inject.Named;

@Singleton
@Named("counterBean")
public class SomeBean {

    private int counter;

    public String someMethod(String body) {
        return "Saying Hello World " + ++counter + " times";
    }

}
