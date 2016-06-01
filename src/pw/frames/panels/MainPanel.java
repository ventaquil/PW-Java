package pw.frames.panels;

import pw.CarsCollection;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = -7309287939553854491L;

	public MainPanel()
	{
		super();

		setMinimumSize(new Dimension(600, 300));

        setMaximumSize(new Dimension(600, 300));

        setPreferredSize(new Dimension(600, 300));
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;

		// Clear scene
        g2D.clearRect(0, 0, getWidth(), getHeight());

        // Fill background
        g2D.setColor(Color.BLACK);
        g2D.fillRect(0, 0, getWidth(), getHeight());

        // Draw scene
        g2D.setColor(Color.WHITE);

        // Draw cars
        CarsCollection.instance()
        		      .paint(g);
	}
}
