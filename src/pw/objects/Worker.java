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
    private Integer wait;

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

    public void goBack()
    {
        q += 3;
        qPosition = 0;
    }

    public void goTo(Distributor distributor)
    {
        q = distributor.getNumber();
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
            try {
                switch (q) {
                    case 1:
                    case 2:
                    case 3:
                        if (wait >  0) {
                            sleep(wait * 1000);
                            wait = 0;
                    
                            DistributorCollection.instance()
                                                 .get(q - 1)
                                                 .freeCar();
                    
                            goBack();
                        }
                        break;
                }

                synchronized (this) {
                    if (Path.increaseQPosition(this)) {
                        qPosition++;
                    }

                    switch (q) {
                        case 0:
                            Distributor d = DistributorCollection.instance()
                                                                 .lockFirstFree();
                            if (d != null) {
                                goTo(d);
                            }
                            break;
                    }

                    wait();
                }
            } catch (InterruptedException e) { }
        }
    }

    public synchronized void toStartPosition()
    {
        q = 0;
        qPosition = 0;
    }

    public synchronized void waitMoment()
    {
        wait = (new Random()).nextInt(45) + 15;
    }

    private Worker(Color color)
    {
        super();

        WorkersCollection workersCollection = WorkersCollection.instance();

        this.color = color;

        number = workersCollection.getWorkersCount() + 1;

        q = 0;
        qPosition = 0;

        wait = 0;

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
