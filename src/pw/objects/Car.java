package pw.objects;

import pw.EntryQueue;
import pw.Path;
import pw.Point;
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
	private Integer q;
	private Integer qPosition;
	private Integer modifier;

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

		if (position == null) {
		    position = EntryQueue.instance()
                                 .getQueueIndex(this) * 2;
		}

		q = position;
		qPosition = 0;

		modifier = null;
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

	public Integer getModifier()
	{
	    return modifier;
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
        if (q == 2) {
            qPosition -= 36;
        } else if (q == 4) {
            qPosition -= 70;
        } else {
            qPosition = 0;
        }

        q = 0;
    }

    public void goToSecondPosition()
    {
        if (q == 4) {
            qPosition -= 36;
        } else {
            qPosition = 0;
        }

        q = 2;
    }

    public void goToThirdPosition()
    {
        q = 4;
        qPosition = 0;
    }

	/**
	 * Method necessary for multithreading.
	 */
	@Override
	public void run()
	{
		while (true) {
			synchronized(this) {
				try {
				    if (Path.increaseQPosition(this)) {
				        qPosition++;
				    }

					wait();
				} catch (InterruptedException e) { }
			}
		}
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
		Double rotation = Path.generateRotation(this);

		g2D.translate(position.getX(), position.getY());
        g2D.rotate(rotation);

		g2D.setColor(color);
		g2D.fill(new RoundRectangle2D.Double(0, 0, 32, 16, 8, 8));

        g2D.setColor(Color.BLACK);
        g2D.draw(new RoundRectangle2D.Double(0, 0, 32, 16, 8, 8));

        g2D.rotate(-rotation);
        g2D.translate(-position.getX(), -position.getY());
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
