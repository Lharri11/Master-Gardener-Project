package master_gardener_main;

import org.eclipse.jetty.server.Server;

import java.io.File;

public class Main {
	public static void main(String[] args) throws Exception {
		String webappCodeBase = "./war";
		File warFile = new File(webappCodeBase);
		Launcher launcher = new Launcher();
		Server server = launcher.launch(8081, warFile.getAbsolutePath(), "/Master-Gardener");
		server.start();
		//server.dumpStdErr();
		System.out.println("Web server started at http://localhost:8081/Master-Gardener/home");
		server.join();
	}
}
