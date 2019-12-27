package gui;

import java.awt.BorderLayout;
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

/**
 * @authors Adel, Paul
 *
 */

public class ScannerGUI extends JFrame {

	private static final Font BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	private static final Font TITLE_LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 24);
	
	private final String DEFAULT_SAVE_FILE = "autosave.ser";

	protected JLabel titleLabel = new JLabel("Scan App");

	protected JButton addButton = new JButton("+ Add...");
	protected JButton runButton = new JButton("> Run Scan");

	protected JPanel buttonsPanel;
	protected ResultsPanel resultsPanel;

	protected EntriesPanel entPanel;

	private ToScanList analysisList = new ToScanList();
	private ResultSave results = new ResultSave();

	public ScannerGUI(String title) {
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
		
		BorderLayout border = new BorderLayout();
		Container contentPane = getContentPane();
		contentPane.setLayout(border);

		//Title
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setVerticalAlignment(JLabel.CENTER);
		contentPane.add(titleLabel, BorderLayout.NORTH);
		
		
		//Body
		
		JPanel bodyPanel = new JPanel();
		GridLayout grid = new GridLayout(2, 1);
		bodyPanel.setLayout(grid);
		
			//Before Scan:
		JPanel intermediatePanel = new JPanel();
		intermediatePanel.setLayout(new BorderLayout());
		
				//Buttons
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonsPanel.add(addButton);
		runButton.setEnabled(false);
		buttonsPanel.add(runButton);
		
				//Selected elements
		entPanel = new EntriesPanel(analysisList, runButton);
		entPanel.setSize(500, 250);
		JScrollPane scrollPane = new JScrollPane(entPanel);

		intermediatePanel.add(buttonsPanel, BorderLayout.NORTH);
		intermediatePanel.add(scrollPane, BorderLayout.CENTER);
		bodyPanel.add(intermediatePanel);

			//Results component
		resultsPanel = new ResultsPanel(results);
		bodyPanel.add(resultsPanel, BorderLayout.SOUTH);
		
		contentPane.add(bodyPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 480);
		//pack();
		setResizable(true);
		setVisible(true);
	}

	private class AddAction implements ActionListener {

		/**
		 *génère un JFileChooser
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			jfc.setDialogTitle("Choose a file or a directory to scan: ");
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			//Ouvre une boite de dialogue personnalisée, avec un bouton de validation spécial.
			int returnValue = jfc.showDialog(null, "Select");
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				analysisList.addToScanList(jfc.getSelectedFile());
				entPanel.layoutEntries(analysisList);
			}
		}
	}

	private class RunScanAction implements ActionListener {
		/**
		 *lance l'analyse
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			results.add(analysisList.scan());
			analysisList.clear();
			entPanel.layoutEntries(analysisList);
			updateResults();
		}
	}

	/**
	 * Met à jour le composant graphique responsable de l'affichage des résultats.
	 */
	private void updateResults() {
		//On sauvegarde les résultats dans un fichier binaire.
		results.serializationSave(DEFAULT_SAVE_FILE);
		//On libère l'espace pour les éventuelles analyses futures.
		results.clear();
		//On consulte le fichier de sauvegarde...
		ResultSave tmp = new ResultSave();
		tmp.serializationRead(DEFAULT_SAVE_FILE);
		//pour afficher les résultats de l'analyse.
		resultsPanel.update(tmp);
	}
	
	public static void main(String[] args) {
		new ScannerGUI("Scanner GUI");
	}
}
