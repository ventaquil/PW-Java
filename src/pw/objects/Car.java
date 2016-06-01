package pw.objects;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Car extends Thread {
	private Color color;

	public Car(Color color)
	{
		super();

		this.color = color;
	}

	@Override
	public void run()
	{
		while (true) {
			synchronized(this) {
				try {
					System.out.println("RUNNING");
					wait();
				} catch (InterruptedException e) {
					
				}
			}
		}
	}

	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;

		g2D.setColor(color);

		g2D.fill(new RoundRectangle2D.Double(10, 10, 16, 32, 8, 8));
	}
}
