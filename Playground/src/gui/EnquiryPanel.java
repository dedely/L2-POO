package gui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import infos.ToScan;
import infos.ToScanList;

public class EnquiryPanel extends JPanel {

	private static final Font LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
	private static final Font DELETE_BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	protected JLabel filePathLabel;

	protected JButton removeButton = new JButton("x");
	
	private ToScanList list;
	private ToScan file;
	
	public EnquiryPanel(ToScan file, ToScanList list) {
		super();
		
		this.list = list;
		this.file = file;
		filePathLabel = new JLabel(file.getPath().toString());

		initStyle();

		initLayout();

		initActions();
	}

	private void initActions() {
		removeButton.addActionListener(new RemoveAction());
	}

	private void initLayout() {
		filePathLabel.setFont(LABEL_FONT);
		removeButton.setFont(DELETE_BUTTON_FONT);
	}

	private void initStyle() {
		FlowLayout flow = new FlowLayout(FlowLayout.CENTER);
		this.setLayout(flow);
		this.add(filePathLabel);
		this.add(removeButton);
	}

	private class RemoveAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			list.remove(file);
			removeAll();
			revalidate();
			repaint();
		}
	}
}
