package gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import infos.ResultList;

public class ResultsPanel extends JPanel {
	private static final Font LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	private JLabel summaryLabel;

	private JButton detailedReportButton = new JButton("detailed Report");

	private ResultList list;

	public ResultsPanel(ResultList list) {

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
		// detailedReportButton.addActionListener(new ReportAction());
	}

	public void update(ResultList list) {
		if (!list.isEmpty()) {
			this.list = list;
			summaryLabel.setText(list.summarize());
		}
	}
	/*
	 * private class ReportAction implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { JTextArea results area
	 * 
	 * } }
	 */
}