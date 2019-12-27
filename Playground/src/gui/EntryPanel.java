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

/**
 * @author Adel Génère un JPanel correspondant à un élément sélectionné par
 *         l'utilisateur pour l'analyse (Chemin d'accès + boutton 'x').
 * 
 *         Permet également à l'utilisateur de déselectionner cet élément: Cette
 *         fonctionnalité (facultative) est encore en construction. Si nous ne
 *         sommes pas encore parvenus à mettre à jour correctement l'affichage,
 *         en particulier le runScanButton, les éléments déselectionnés sont
 *         bien supprimés de la liste d'éléments à analyser.
 *
 */
public class EntryPanel extends JPanel {

	private static final Font LABEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 12);
	private static final Font DELETE_BUTTON_FONT = new Font(Font.DIALOG, Font.BOLD, 20);
	private JLabel filePathLabel;

	private JButton removeButton = new JButton("x");

	private ToScanList list;
	private ToScan file;

	public EntryPanel(ToScan file, ToScanList list) {
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
		/**
		 * Supprime l'élément de la liste d'éléments à analyser.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// L'élément est supprimé de la liste
			list.remove(file);
			// On supprime les composants graphiques
			removeAll();
			// On rafraichit
			revalidate();
			repaint();
		}
	}
}