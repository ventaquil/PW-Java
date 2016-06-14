package pw.frames.panels;

import pw.CarsCollection;
import pw.DistributorCollection;
import pw.WorkersCollection;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * @author ventaquil
 * @package pw.frames.panels
 * @version 0.1.0-alpha
 */
public class MainPanel extends JPanel {
	private static final long serialVersionUID = -7309287939553854491L;

	/**
	 * MainPanel class constructor. Sets default size of panel.
	 */
	public MainPanel()
	{
		super();

		setMinimumSize(new Dimension(270, 220));

        setMaximumSize(new Dimension(270, 220));

        setPreferredSize(new Dimension(270, 220));
	}

	/**
	 * Method paints panel content.
	 * 
	 * @param g Graphics object.
	 */
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;

		// Clear scene
        g2D.clearRect(0, 0, getWidth(), getHeight());

        // Fill background
        g2D.setColor(new Color(26, 141, 0));
        g2D.fillRect(0, 0, getWidth(), getHeight());

        // Draw scene
        g2D.setColor(new Color(250, 250, 250));
        g2D.fill(new Rectangle2D.Double(24, 0, 19, 104));
        g2D.fill(new RoundRectangle2D.Double(20, 104, 100, 90, 8, 8));
        g2D.fill(new Rectangle2D.Double(0, 170, 20, 19));
        g2D.fill(new Rectangle2D.Double(120, 110, 36, 25));
        g2D.fill(new RoundRectangle2D.Double(156, 60, 90, 130, 8, 8));

        g2D.setColor(new Color(205, 174, 136));
        g2D.fill(new RoundRectangle2D.Double(160, 199, 54, 18, 8, 8));
        g2D.setColor(new Color(193, 154, 107));
        g2D.draw(new RoundRectangle2D.Double(160, 199, 54, 18, 8, 8));

        g2D.setColor(new Color(160, 160, 160));
        g2D.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0));
        g2D.draw(new Line2D.Float(25, 101, 42, 101));

        g2D.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{4}, 0));
        g2D.draw(new RoundRectangle2D.Double(122, 112, 32, 21, 4, 4));

        // Stroke to default
        g2D.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));

        // Draw cars
        CarsCollection.instance()
        		      .paint(g);

        // Draw workers
        WorkersCollection.instance()
                         .paint(g);

        // Draw distributors
        DistributorCollection.instance()
                             .paint(g);
	}
}
