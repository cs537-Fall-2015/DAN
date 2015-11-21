package DAN.DAN_testMain;

import javax.swing.JFrame;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYSplineRenderer;


public class ClientGUI{
	
	JFrame frame;
	JButton Dan_on;
	JCheckBox automaticCheckBox;
	JTextArea textArea;
	JScrollPane scroll;
	XYSeries series;
	boolean isDanOn;
	static boolean closedButtonPressed;
	public static boolean isAutomatic;
	

	/**
	 * Launch the application.
	 */
	
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sample window = new sample();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	
	

	/**
	 * Create the application.
	 */
	 ClientGUI() {
		 isDanOn = false;
		 isAutomatic = false;
		 closedButtonPressed = false;
		 initialize();
	}
	 
	 void println(String message) {
		 textArea.append(message + "\n");
	 }
	 
	 void plotGraph(String time, double neutronCount) {
		 StringBuilder builder = new StringBuilder();
		 String timeInt [] = time.split(":");
		 for(int i =0; i<2;i++) {
		     builder.append(timeInt[i]);
		 }
		 System.out.println("time is "+ builder.toString());
		 
		 series.add(Integer.parseInt(builder.toString()), neutronCount);
	 }
	 
	 void changeButtonColor(String command) {
		 if (command.equals("DAN_ON")) {
			Dan_on.setForeground(Color.GREEN);
			isDanOn = true;
		 }
		 else if (command.equals("DAN_OFF")) {
			Dan_on.setForeground(Color.RED);
			isDanOn=false;
		 }
	 }
	 public static boolean isClosed() {
		 return closedButtonPressed;
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 3));
		Dan_on = new JButton("DAN ON");
		
		Dan_on.setSize(10, 10);
		Dan_on.setForeground(Color.RED);
		panel.add(Dan_on);
		
		automaticCheckBox = new JCheckBox("Automatic");
		panel.add(automaticCheckBox);
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closedButtonPressed = true;
				System.exit(0);
			}
		});
		exitButton.setSize(10,10);
		panel.add(exitButton);
		
		Dan_on.setSize(10, 10);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 1));
		
		textArea = new JTextArea();
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		scroll = new JScrollPane(textArea);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scroll);
		series = new XYSeries("Neutron Count");
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        NumberAxis domain = new NumberAxis("Time");
        NumberAxis range = new NumberAxis("Number of neutrons");
        domain.setTickUnit(new NumberTickUnit(100));
        XYSplineRenderer r = new XYSplineRenderer(1);
        r.setSeriesShape(0, new Ellipse2D.Double(-3, -3, 3, 3));
        XYPlot xyplot = new XYPlot(dataset, domain, range, r);
        JFreeChart chart = new JFreeChart(xyplot);
        ChartPanel cp = new ChartPanel(chart);

        
        panel_1.add(cp);

		Dan_on.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				isDanOn = true;
				//Dan_on.setForeground(Color.GREEN);
				if (automaticCheckBox.isSelected())
					isAutomatic = true;
				
				
			}
		});
		/*frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	try {
					
					DAN.server.DANServer.closeServer();
					DanClient.closeClient();
					Thread.sleep(1000);
		            System.exit(0);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    	
		    }
		});*/
	}
	
}
