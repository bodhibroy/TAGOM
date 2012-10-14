import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

public class ProgressBar {
  public static void main(String args[]) {
    JFrame f = new JFrame("JProgressBar Sample");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container content = f.getContentPane();
    JProgressBar progressBar = new JProgressBar();
    int i =0;
    
    progressBar.setValue(i);
    progressBar.setStringPainted(true);
    Border border = BorderFactory.createTitledBorder("Reading...");
    progressBar.setBorder(border);
    content.add(progressBar, BorderLayout.NORTH);
    f.setSize(300, 100);
    f.setVisible(true);
    while(i<=100)
    {
	    try
	    {
		    Thread.sleep(50);
		    progressBar.setValue(i++);
		    progressBar.updateUI();
	    }catch (InterruptedException e){
		System.out.println(e.getMessage());
	    }
    }   
    progressBar.setString("Success");
  }
}
