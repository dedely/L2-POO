package gui;

import java.awt.Color;
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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

//import infos.Result;
import infos.ToScanList;

public class PrototypeGUI extends JFrame {

	private static final Font MESSAGE_FONT = new Font(Font.SANS_SERIF, Font.ITALIC, 20);
	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	//private static final Font TEXT_FIELD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);
	private static final Font LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
	private static final Font TITLE_LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 20);
	private static final Color MESSAGE_STANDARD_COLOR = Color.BLUE;
	//private static final Color MESSAGE_ERROR_COLOR = Color.RED;

	protected JLabel titleLabel = new JLabel("Analyse");
	protected JLabel instructionLabel = new JLabel("Ajoutez des objets.");
	protected JLabel messageLabel = new JLabel("Everything is OK ! ");

	//protected JTextField filePathField = new JTextField(30);

	protected JButton addButton = new JButton("+ Add");
	protected JButton runButton = new JButton("> Run Scan");

	private JPanel linePanel1;
	private JPanel linePanel3;
	private JPanel linePanel4;
	
	private ToScanList analysisList = new ToScanList();
	
	public PrototypeGUI(String title) {
		super(title);

		analysisList = new ToScanList();
		
		initStyle();

		initLayout();

		initActions();
	}

	protected void initActions() {
		addButton.addActionListener(new AddAction());
	}

	protected void initStyle() {
		titleLabel.setFont(TITLE_LABEL_FONT);
		instructionLabel.setFont(LABEL_FONT);

		//filePathField.setFont(TEXT_FIELD_FONT);

		addButton.setFont(BUTTON_FONT);
		runButton.setFont(BUTTON_FONT);

		messageLabel.setFont(MESSAGE_FONT);
		messageLabel.setForeground(MESSAGE_STANDARD_COLOR);
	}

	protected void initLayout() {
		GridLayout grid = new GridLayout(5, 1);
		Container contentPane = getContentPane();
		contentPane.setLayout(grid);

		// First line Titre(ok)
		linePanel1 = new JPanel();
		linePanel1.setSize(500, 100);
		linePanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		linePanel1.add(titleLabel);
		contentPane.add(linePanel1);

		// Second line
		//contentPane.add(instructionLabel);

		// Third line
		linePanel3 = new JPanel();
		linePanel3.setSize(500, 100);
		linePanel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		linePanel3.add(addButton);
		//linePanel3.add(filePathField);
		contentPane.add(linePanel3);

		// Fourth line
		
		linePanel4 = new JPanel();
		linePanel4.setSize(500, 100);
		linePanel4.setLayout(new FlowLayout(FlowLayout.CENTER));
		runButton.setEnabled(false); // The user can't run the scan until appropriate objets (either files or folders are added)
		linePanel4.add(runButton);
		contentPane.add(linePanel4);
		// Encapsuler les boutons dans le même container...
		
		// Fifth line
		

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 480);
		setResizable(false);
		//pack();
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
				analysisList.addToScanList(jfc.getSelectedFile());//try catch à ajouter.
				//updateStandardMessage("Element added !");
				updateListDisplay();
				runButton.setEnabled(true);
			}
		}
	}

	private void updateListDisplay() {

	}
	/*
	private void updateStandardMessage(String message) {
		messageLabel.setForeground(MESSAGE_STANDARD_COLOR);
		messageLabel.setText(message);
	}*/
	/*
	private void updateErrorMessage(String message) {
		messageLabel.setForeground(MESSAGE_ERROR_COLOR);
		messageLabel.setText(message);
	}*/

	public static void main(String[] args) {
		new PrototypeGUI("Prototype GUI");
	}
}
