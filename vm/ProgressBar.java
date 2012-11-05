import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;


/**
 * @param args
 * @author Bodhi
 */

public class ProgressBar extends JProgressBar{
	private JFrame f;
	private Container content;
	
	public ProgressBar()
	{
		f = new JFrame("Vocal Matcher");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		content = f.getContentPane();
		this.setStringPainted(true);
		content.add(this, BorderLayout.CENTER);
		f.setSize(1000, 100);
		f.setVisible(true);
	}

	public void playProgressAnimation()
	{
		int i=0;
		while(i<=100)
		{
			try
			{
				Thread.sleep(30);
				this.setValue(i++);
				this.updateUI();
			}catch (InterruptedException e){
				System.out.println(e.getMessage());
			}
		}   
	}

	public void modifyDisplay(String displayString)
	{
		this.setString(displayString);
		this.updateUI();
	}


	/*public static void main(String args[]) {
		JFrame f = new JFrame("JProgressBar Sample");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = f.getContentPane();
		JProgressBar progressBar = new JProgressBar();
		int i =0;

		progressBar.setValue(i);
		progressBar.setStringPainted(true);
		Border border = BorderFactory.createTitledBorder("Playing Tone...");
		progressBar.setBorder(border);
		content.add(progressBar, BorderLayout.CENTER);
		f.setSize(300, 100);
		f.setVisible(true);
		while(i<=100)
		{
			try
			{
				Thread.sleep(20);
				progressBar.setValue(i++);
				progressBar.updateUI();
			}catch (InterruptedException e){
				System.out.println(e.getMessage());
			}
		}   
		progressBar.setString("Please get ready to record.");
		try{
			Thread.sleep(2000);
		}catch (InterruptedException e){
			System.out.println(e.getMessage());
		}
		progressBar.setString("Recording now. Please sing.");
		progressBar.updateUI();
	}*/

}

