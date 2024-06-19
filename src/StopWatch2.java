import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;

import javax.swing.*;

public class StopWatch2 
{
	JFrame frame=new JFrame("Stop Watch");
	JLabel label=new JLabel("00:00:00");
	JButton button=new JButton("START");
	int h=0,m=0,s=0;
	boolean start=true;
	public StopWatch2()
	{
		frame.setSize(400,400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER,200,80));
		label.setFont(new Font("arial",Font.BOLD,35));
		frame.add(label);
		button.setFont(new Font("arial",Font.PLAIN,20));
		frame.add(button);
		button.addActionListener(new StartListener());
		frame.setVisible(true);
	}
	class WatchThread extends Thread
	{
		public void run()
		{
			while(start)
			{
				String time=(h<10?"0"+h:h)+":"+(m<10?"0"+m:m)+":"+(s<10?"0"+s:s);
				label.setText(time);
				try {
					Thread.sleep(1000);
				}catch(Exception ex) {}
				s++;
				if(s==60)
				{
					s=0;
					m++;
					if(m==60)
					{
						m=0;
						h++;
					}
				}
			}
		}
	}
	class StartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			if(button.getText().equals("START"))
			{
				start=true;
				new WatchThread().start();
				button.setText("STOP");
			}
			else if(button.getText().equals("STOP"))
			{
				button.setText("RESET");
				start=false;
			}
			else
			{
				h=m=s=0;
				label.setText("00:00:00");
				button.setText("START");
			}
		}
	}
	public static void main(String[] args) 
	{
		new StopWatch2();
	}
}
