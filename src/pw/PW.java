package pw;

import pw.frames.MainFrame;
import javax.swing.SwingUtilities;

/**
 * @author ventaquil
 * @package pw
 * @version 0.1.0-alpha
 */
public abstract class PW {
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				MainFrame.instance();
			}
		});

		Timeline.instance()
		        .start();

        EntryQueue.instance()
                  .newCar();
        EntryQueue.instance()
                  .newCar();
//        EntryQueue.instance()
//                  .newCar();
	}
}
