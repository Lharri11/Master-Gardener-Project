package master_gardener_main;

// Launch from an uberjar.
public class UberjarMain {
	public static void main(String[] args) throws Exception {
		UberjarDaemonController controller = new UberjarDaemonController();
		controller.exec(args);
	}
}
