package pw.frames;

import javax.swing.JFrame;

import pw.frames.panels.MainPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 3638872605243330343L;

	public MainFrame()
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
