package master_gardener_main;

import java.lang.management.ManagementFactory;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {
	/**
	 * Create a {@link Server}, but do not start it.
	 * 
	 * @param port which port to listen on
	 * @param warUrl the URL of the webapp war directory
	 */
	public Server launch(int port, String warUrl, String contextPath) throws Exception {
		// This is adapted from the Embedded Jetty example from Jetty 9.4.x:
		//	    https://www.eclipse.org/jetty/documentation/9.4.x/embedded-examples.html#embedded-webapp-jsp
		Server server = new Server(port);
		
		MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
		server.addBean(mbContainer);

		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(contextPath);
		webapp.setWar(warUrl);
		
		onCreateWebAppContext(webapp);

		Configuration.ClassList classList = Configuration.ClassList.setServerDefault(server);
		classList.addBefore(
				"org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");
		webapp.setAttribute(
				"org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );

		// Don't allow directory listings
		webapp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");

		// Allow the welcome file to be a servlet
		webapp.setInitParameter("org.eclipse.jetty.servlet.Default.welcomeServlets", "true");
		
		server.setHandler(webapp);
		
		return server;
	}

	protected void onCreateWebAppContext(WebAppContext webapp) {
		// Does nothing by default, subclasses may override
	}
}
