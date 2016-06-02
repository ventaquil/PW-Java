package pw;

import pw.frames.MainFrame;
import javax.swing.SwingUtilities;

/**
 * @author ventaquil
 * @package pw
 * @version 0.1.0-alpha
 */
public class Timeline extends Thread {
	private static Timeline instance;

	/**
	 * Method returns existing instance of Timeline. If instance does not exists method creates it.
	 * 
	 * @return Timeline instance.
	 */
	public static Timeline instance()
	{
		if (instance == null) {
			instance = new Timeline();
		}

		return instance;
	}

	/**
	 * Method necessary for multithreading. Every second do all necessary actions.
	 */
	@Override
	public void run()
	{
		while (true) {
			try {
				Thread.sleep(30);

				(new Thread() {
					@Override
					public void run()
					{
						CarsCollection.instance()
						  			  .notifyCars();

						try {
						    SwingUtilities.updateComponentTreeUI(MainFrame.instance());
						} catch (NullPointerException e) { }
					}
				}).start();
			} catch (InterruptedException e) { }
		}
	}

	/**
	 * Private class constructor.
	 */
	private Timeline() { }
}
