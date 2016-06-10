package pw.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Distributor {
    private static List<Distributor> distributors = new ArrayList<Distributor>();
    private Integer number;
    private Semaphore s;

    public static Distributor acquireFirstFree()
    {
        Distributor d = null;

        for (int i = 0, j = distributors.size(); i < j; i++) {
            try {
                if (distributors.get(i)
                                .s
                                .tryAcquire()) {
                    d = distributors.get(i);
                    break;
                }
            } catch (IndexOutOfBoundsException e) { }
        }

        return d;
    }

    public Distributor() {
        distributors.add(this);

        number = distributors.size();

        s = new Semaphore(1);
    }

    public static Distributor get(Integer number)
    {
        if (number >= distributors.size()) {
            return null;
        }

        return distributors.get(number);
    }

    public Integer getNumber()
    {
        return number;
    }

    public static void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;

        Color color = new Color(255, 130, 0);

        Semaphore s;

        for (int i = 0, j = distributors.size(); i < j; i++) {
            try {
                g2D.setColor(color);
                g2D.fill(new RoundRectangle2D.Double(222, 65 + (i * 40), 20, 14, 4, 4));

                s = distributors.get(i)
                                .s;
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
