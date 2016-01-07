package pl.milosz.bean;

import javax.annotation.Resource;
import javax.ejb.*;


import org.infinispan.manager.CacheContainer;

@Stateless
@Remote(IGreeter.class)
public class Greeter implements IGreeter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3102294286707155625L;

	@Resource(lookup = "java:jboss/infinispan/container/web")
	private CacheContainer container;

	public String greet() {
		/*Object hw = container.getCache().get("hello_world");
		if (hw != null) {
			return "GET: "+hw.toString();
		}*/
		return "GET: Default Greetings";
	}

	public String change() {
		return null;
		/*Object original = container.getCache().get("hello_world");
		if (original != null)
		{
			original = original.toString() + "1";
		}
		else
		{
			original = "Default greetings";
		}
		container.getCache().put("hello_world", original);
		return original.toString();*/
	}
}
