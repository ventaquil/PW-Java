package pw.objects;

import pw.DistributorCollection;
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
    private Integer number;
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

    public Integer getNumber()
    {
        return number;
    }

    public Integer getQ()
    {
        return q;
    }

    public Integer getQPosition()
    {
        return qPosition;
    }

    public void goTo(Distributor distributor)
    {
        q = distributor.getNumber() + 1;
        qPosition = 0;
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

                    switch (q) {
                        case 1:
                            Distributor d = DistributorCollection.instance()
                                                                 .lockFirstFree();
                            if (d != null) {
                                goTo(d);
                            }
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

        number = workersCollection.getWorkersCount() + 1;

        q = 1;
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
