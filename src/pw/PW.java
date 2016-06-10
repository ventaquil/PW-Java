package pw;

import pw.frames.MainFrame;
import pw.objects.Distributor;
import pw.objects.Worker;
import pw.semaphores.BuildingSemaphore;
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

		BuildingSemaphore.instance();

        Distributor.addNew();
        Distributor.addNew();
        Distributor.addNew();

        Worker.addNew();
        Worker.addNew();
        Worker.addNew();
        Worker.addNew();

		Timeline.instance()
		        .start();

        EntryQueue.instance()
                  .newCar();

        Timeline.instance()
                .waitCycle(30);
        EntryQueue.instance()
                  .newCar();

        Timeline.instance()
                .waitCycle(30);
        EntryQueue.instance()
                  .newCar();

        Timeline.instance()
                .waitCycle(50);
        EntryQueue.instance()
                  .newCar();
	}
}
