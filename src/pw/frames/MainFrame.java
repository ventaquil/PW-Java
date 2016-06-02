package pw.frames;

import pw.frames.panels.MainPanel;
import javax.swing.JFrame;

/**
 * @author ventaquil
 * @package pw.frames
 * @version 0.1.0-alpha
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 3638872605243330343L;
	private static MainFrame instance;

	/**
	 * Method returns existing instance of Timeline. If instance does not exists method creates it.
	 * 
	 * @return Timeline instance.
	 */
	public static synchronized MainFrame instance()
	{
		if (instance == null) {
			instance = new MainFrame();
		}

		return instance;
	}

	/**
	 * Private MainFrame class constructor.
	 */
	private MainFrame()
	{
		super("PW");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        getContentPane().add(new MainPanel());

        pack();

        setLocationRelativeTo(null);

		setVisible(true);
	}
}
