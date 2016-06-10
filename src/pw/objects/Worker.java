package pw.objects;

import pw.Path;
import pw.Point;
import pw.WorkersCollection;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

public class Worker extends Thread {
    private Color color;
    private Integer q;
    private Integer qPosition;

    public static Worker addNew()
    {
        if (WorkersCollection.instance()
                             .getWorkersCount() < 4) {
            return new Worker();
        }

        return null;
    }

    public Integer getQ()
    {
        return q;
    }

    public Integer getQPosition()
    {
        return qPosition;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;

        Point position = Path.generatePosition(this);

        Integer x = position.getX();
        Integer y = position.getY();

        g2D.setColor(color);
        g2D.fill(new RoundRectangle2D.Double(x, y, 10, 10, 10, 10));

        g2D.setColor(Color.BLACK);
        g2D.draw(new RoundRectangle2D.Double(x, y, 10, 10, 10, 10));
    }

    @Override
    public void run()
    {
        while (true) {
            synchronized (this) {
                try {
                    if (Path.increaseQPosition(this)) {
                        qPosition++;
                    }

                    wait();
                } catch (InterruptedException e) { }
            }
        }
    }

    private Worker(Color color)
    {
        super();

        WorkersCollection workersCollection = WorkersCollection.instance();

        this.color = color;

        q = workersCollection.getWorkersCount();
        qPosition = 0;

        workersCollection.add(this);
    }

    private Worker()
    {
        this(new Color(
            new Integer((new Random()).nextInt(226) + 30),
            new Integer((new Random()).nextInt(226) + 30),
            new Integer((new Random()).nextInt(226) + 30)
        ));
    }
}
