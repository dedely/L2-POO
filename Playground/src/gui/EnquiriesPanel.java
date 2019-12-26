package gui;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import infos.ToScan;
import infos.ToScanList;

public class EnquiriesPanel extends JPanel {
	private static final Font LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
	protected JLabel instructionLabel = new JLabel("Add objects for scan.");

	protected JButton runButton;
	// protected JButton clearButton = new JButton("clear");
	// private ToScanList list;
	public EnquiriesPanel(ToScanList list, JButton runButton) {
		this.runButton = runButton;
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		initActions();
		instructionLabel.setFont(LABEL_FONT);
		layoutEnquiries(list);

	}

	private void initActions() {
		// clearButton.addActionListener(new ClearAction());

	}

	public void layoutEnquiries(ToScanList list) {
		removeAll();
		if (list.isEmpty()) {
			runButton.setEnabled(false);
			JPanel tmp = new JPanel();
			FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
			tmp.setLayout(flow);
			tmp.add(instructionLabel);
			add(tmp);
		} else {
			for (ToScan file : list.getScanList()) {
				EnquiryPanel eqPanel = new EnquiryPanel(file, list);
				add(eqPanel);
			}
			runButton.setEnabled(true);
		}
		revalidate();
	}
	/*
	 * private class ClearAction implements ActionListener {
	 * 
	 * @Override public void actionPerformed(ActionEvent e) {
	 * 
	 * 
	 * } }
	 */
}
