package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import infos.ResultSave;
import infos.ToScanList;

public class PrototypeGUI extends JFrame {

	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	private static final Font TITLE_LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);

	protected JLabel titleLabel = new JLabel("Selective Scan");

	protected JButton addButton = new JButton("+ Add");
	protected JButton runButton = new JButton("> Run Scan");

	protected JPanel buttonsPanel;
	protected ResultsPanel resultsPanel;

	protected EnquiriesPanel eqPanel;

	private ToScanList analysisList = new ToScanList();
	private ResultSave results = new ResultSave();

	public PrototypeGUI(String title) {
		super(title);

		analysisList = new ToScanList();

		initStyle();
		initLayout();
		initActions();
	}

	protected void initActions() {
		addButton.addActionListener(new AddAction());
		runButton.addActionListener(new RunScanAction());
	}

	protected void initStyle() {
		titleLabel.setFont(TITLE_LABEL_FONT);

		addButton.setFont(BUTTON_FONT);
		runButton.setFont(BUTTON_FONT);
	}

	protected void initLayout() {
		GridLayout grid = new GridLayout(5, 1);
		Container contentPane = getContentPane();
		contentPane.setLayout(grid);

		// First line: Title
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.TOP);
		contentPane.add(titleLabel);

		// Second line: Buttons
		buttonsPanel = new JPanel();
		buttonsPanel.setSize(500, 100);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonsPanel.add(addButton);
		runButton.setEnabled(false);
		buttonsPanel.add(runButton);
		contentPane.add(buttonsPanel);

		// Third Component
		eqPanel = new EnquiriesPanel(analysisList, runButton);
		JScrollPane scrollPane = new JScrollPane(eqPanel);
		contentPane.add(scrollPane);

		// Fifth line
		resultsPanel = new ResultsPanel(results);
		contentPane.add(resultsPanel);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 480);
		setResizable(false);
		// pack();
		setVisible(true);
	}

	private class AddAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("Choose a file or a directory to scan: ");
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			int returnValue = jfc.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				analysisList.addToScanList(jfc.getSelectedFile());// try catch à ajouter

				eqPanel.layoutEnquiries(analysisList);


				revalidate();
				repaint();
			}
		}
	}

	private class RunScanAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			results.add(analysisList.scan());
			updateResultsArea(results);
		}
	}

	private void updateResultsArea(ResultSave results) {
		resultsPanel.update(results);
		
	}
	
	public static void main(String[] args) {
		new PrototypeGUI("Prototype GUI");
	}
}
