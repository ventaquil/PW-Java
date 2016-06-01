package pw;

import pw.frames.MainFrame;
import javax.swing.SwingUtilities;

public abstract class PW {
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run()
			{
				new MainFrame();
			}
		});

		CarsCollection.instance()
					  .newCar();

		Timeline.instance()
				.start();
	}
}
