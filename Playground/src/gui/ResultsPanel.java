package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import infos.Result;
import infos.ResultSave;

public class ResultsPanel extends JPanel {
	private static final Font LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	
	
	private static final Color MESSAGE_ERROR_COLOR = Color.RED;
	private JLabel summaryLabel;

	private JButton detailedReportButton = new JButton("detailed report");

	private ResultSave list;

	public ResultsPanel(ResultSave list) {

		this.list = list;

		initLayout();

		initStyle();

		initActions();
	}

	private void initStyle() {
		summaryLabel.setFont(LABEL_FONT);
		detailedReportButton.setFont(BUTTON_FONT);

	}

	private void initLayout() {
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		this.setLayout(flow);
		summaryLabel = new JLabel();
		summaryLabel.setFont(LABEL_FONT);
		this.add(summaryLabel);
		update(list);
	}

	private void initActions() {
		detailedReportButton.addActionListener(new ReportAction());
	}

	public void update(ResultSave list) {
		if (!list.isEmpty()) {
			this.list = list;
			summaryLabel.setText(list.summarize());
			add(detailedReportButton);
		}
	}

	private class ReportAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame frame = new JFrame("Detailed report");

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setOpaque(true);
            JPanel resultsArea = new JPanel();
            resultsArea.setLayout(new BoxLayout(resultsArea, BoxLayout.Y_AXIS));
            
            for (Result result : list.getResults()) {
            	JTextArea subTextArea = new JTextArea();
            	if(result.getAnomaly().equals("true")) {
            		subTextArea.setForeground(MESSAGE_ERROR_COLOR);
            	}
            	subTextArea.setText(result.toString());
            	subTextArea.setWrapStyleWord(true);
                subTextArea.setEditable(false);
                subTextArea.setFont(Font.getFont(Font.SANS_SERIF));
                resultsArea.add(subTextArea);
    		}
            

            JScrollPane scroller = new JScrollPane(resultsArea);
            panel.add(scroller);

            frame.getContentPane().add(BorderLayout.CENTER, panel);
            frame.setSize(500, 480);
            frame.pack();
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.setResizable(false);
		}
	}
}