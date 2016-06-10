package pw;

import pw.objects.Distributor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class DistributorCollection {
    private List<Distributor> distributors;
    private static DistributorCollection instance;

    public Distributor acquireFirstFree()
    {
        for (int i = 0, j = distributors.size(); i < j; i++) {
            try {
                if (distributors.get(i)
                                .getSemaphore()
                                .tryAcquire()) {
                    return distributors.get(i);
                }
            } catch (IndexOutOfBoundsException e) { }
        }

        return null;
    }

    public void add(Distributor distributor)
    {
        distributors.add(distributor);
    }

    private DistributorCollection()
    {
        distributors = new ArrayList<Distributor>();
    }

    public Distributor get(Integer number)
    {
        if (number >= distributors.size()) {
            return null;
        }

        return distributors.get(number);
    }

    public Integer getDistributorsCount()
    {
        return distributors.size();
    }

    public synchronized static DistributorCollection instance()
    {
        if (instance == null) {
            instance = new DistributorCollection();
        }

        return instance;
    }

    public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;

        Color color = new Color(255, 130, 0);

        Semaphore s;

        for (int i = 0, j = distributors.size(); i < j; i++) {
            try {
                g2D.setColor(color);
                g2D.fill(new RoundRectangle2D.Double(222, 65 + (i * 40), 20, 14, 4, 4));

                s = distributors.get(i)
                                .getSemaphore();
                if (s.tryAcquire()) {
                    s.release();
                    g2D.setColor(Color.GREEN);
                } else {
                    g2D.setColor(Color.RED);
                }

                g2D.fill(new Ellipse2D.Double(222, 65 + (i * 40), 5, 5));

                g2D.setColor(Color.BLACK);
                g2D.draw(new RoundRectangle2D.Double(222, 65 + (i * 40), 20, 14, 4, 4));
                g2D.draw(new Ellipse2D.Double(222, 65 + (i * 40), 5, 5));
            } catch (IndexOutOfBoundsException e) { }
        }
    }
}
