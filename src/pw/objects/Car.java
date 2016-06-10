package pw.objects;

import pw.DistributorCollection;
import pw.EntryQueue;
import pw.Path;
import pw.Point;
import pw.semaphores.BuildingSemaphore;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * @author ventaquil
 * @package pw.objects
 * @version 0.1.0-alpha
 */
public class Car extends Thread {
	private Color color;
    private Distributor distributor;
	private Integer q;
	private Integer qPosition;

	/**
	 * Car class constructor. Defines default car color.
	 * 
	 * @param color Car's color.
	 * @param position Position in EntryQueue
	 */
	public Car(Color color, Integer position)
	{
		super();

		this.color = color;

		distributor = null;

		if (position == null) {
		    position = EntryQueue.instance()
                                 .getQueueIndex(this) * 2;
		}

		q = position;
		qPosition = 0;
	}

    /**
     * Car class constructor. Defines default car color.
     * 
     * @param color Car's color.
     */
    public Car(Color color)
    {
        this(color, 0);
    }

	public Integer getQ()
	{
	    return q;
	}

	public Integer getQPosition()
	{
	    return qPosition;
	}

    public void goToFirstPosition()
    {
        if (q == null) {
            qPosition = 0;
        } else if (q == 3) {
            qPosition = 82;
        } else if (q == 5) {
            qPosition = 62;
        }

        q = 0;
    }

    public void goToSecondPosition()
    {
        if (q == null) {
            qPosition = 0;
        } else if (q == 5) {
            qPosition = 62;
        }

        q = 2;
    }

    public void goToThirdPosition()
    {
        q = 4;
        qPosition = 0;
    }

    public void goToFirstDistributor()
    {
        q = 6;
        qPosition = 0;
    }

    public void onFirstDistributor()
    {
        q = 7;
        qPosition = 0;

        BuildingSemaphore.instance()
                         .release();

        DistributorCollection.instance()
                             .get(1 - 1)
                             .capture(this);
    }

    public void goToSecondDistributor()
    {
        q = 8;
        qPosition = 0;
    }

    public void onSecondDistributor()
    {
        q = 9;
        qPosition = 0;

        BuildingSemaphore.instance()
                         .release();

        DistributorCollection.instance()
                             .get(2 - 1)
                             .capture(this);
    }

    public void goToThirdDistributor()
    {
        q = 10;
        qPosition = 0;
    }

    public void onThirdDistributor()
    {
        q = 11;
        qPosition = 0;

        BuildingSemaphore.instance()
                         .release();

        DistributorCollection.instance()
                             .get(3 - 1)
                             .capture(this);
    }

    /**
     * Method paints Car.
     * 
     * @param g Graphics object.
     */
    public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;

        Point position = Path.generatePosition(this);

        Integer x = position.getX();
        Integer y = position.getY();

        g2D.setColor(color);
        g2D.fill(new RoundRectangle2D.Double(x, y, 16, 16, 8, 8));

        g2D.setColor(Color.BLACK);
        g2D.draw(new RoundRectangle2D.Double(x, y, 16, 16, 8, 8));
    }

	/**
	 * Method necessary for multithreading.
	 */
	@Override
	public void run()
	{
		while (true) {
            try {
                synchronized(this) {
				    if (Path.increaseQPosition(this)) {
				        qPosition++;
				    }

				    EntryQueue queue = EntryQueue.instance();
				    Integer index = queue.getQueueIndex(this);
				    switch (index) {
				        case 0:
		                    switch (q) {
		                        case 1:
		                            if (distributor == null) {
		                                distributor = DistributorCollection.instance()
		                                                                   .acquireFirstFree();
		                            }

		                            if (distributor != null) {
    		                            if (BuildingSemaphore.instance()
    		                                                 .tryAcquire()) {
            		                        queue.pop();
            
            		                        switch (distributor.getNumber()) {
            		                            case 1:
                                                    goToFirstDistributor();
            		                                break;
            		                            case 2:
                                                    goToSecondDistributor();
            		                                break;
            		                            case 3:
                                                    goToThirdDistributor();
            		                                break;
            		                        }
    		                            }
		                            }
    		                        break;
		                        case 3:
                                case 5:
                                    goToFirstPosition();
                                    break;
		                    }
                            break;
				        case 1:
				            switch (q) {
				                case 5:
			                        goToSecondPosition();
                                    break;
				            }
				            break;
				    }

					wait();
                }
            } catch (InterruptedException e) { }
		}
	}

    public synchronized void setFirstPosition()
    {
        q = 1;
        qPosition = 0;
    }

    public synchronized void setSecondPosition()
    {
        q = 3;
        qPosition = 0;
    }

    public synchronized void setThirdPosition()
    {
        q = 5;
        qPosition = 0;
    }
}
