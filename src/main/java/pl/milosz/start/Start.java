package pl.milosz.start;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.container.JARArchive;
import org.wildfly.swarm.infinispan.InfinispanFraction;
import org.wildfly.swarm.management.ManagementFraction;
import org.wildfly.swarm.messaging.MessagingFraction;

import pl.milosz.bean.Greeter;
import pl.milosz.bean.IGreeter;

public class Start {

	public static void main(String[] args) throws Exception {
		Container container = new Container();

		ManagementFraction mngm = ManagementFraction.createDefaultFraction()
				.httpInterfaceManagementInterface((iface) -> {
					iface.securityRealm("ManagementRealm");
				}).securityRealm("ManagementRealm", (realm) -> {
					realm.inMemoryAuthentication((authn) -> {
						authn.add("appuser", "appuser", true);
					});
					realm.inMemoryAuthorization((authz) -> {
						authz.add("appuser", "appuser");
					});
				}).securityRealm("ApplicationRealm", (realm) -> {
					realm.inMemoryAuthentication((authn) -> {
						authn.add("appuser", "appuser", true);
					});
				});
		container.fraction(mngm);
		container.fraction(MessagingFraction.createDefaultFraction());

		container.fraction(InfinispanFraction.createDefaultFraction());
		container.start();
		JARArchive archive = ShrinkWrap.create(JARArchive.class, "SwarmRemote");
		archive.addModule("org.infinispan");
		archive.as(JARArchive.class).addClasses(Greeter.class, IGreeter.class);
		container.deploy(archive);
		System.out.println("OK");
	}
}
