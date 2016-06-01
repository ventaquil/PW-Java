package pw;

public class Timeline extends Thread {
	private static Timeline instance;

	public static Timeline instance()
	{
		if (instance == null) {
			instance = new Timeline();
		}

		return instance;
	}

	@Override
	public void run()
	{
		while (true) {
			try {
				Thread.sleep(1000);

				CarsCollection.instance()
							  .notifyCars();
			} catch (InterruptedException e) { }
		}
	}

	private Timeline() { }
}
