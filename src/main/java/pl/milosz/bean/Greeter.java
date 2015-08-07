package pl.milosz.bean;

import javax.ejb.*;

@Stateless
@Remote(GreeterIf.class)
public class Greeter implements GreeterIf{
    public String greet() {
        return "Hello world";
    }
}
