package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import infos.Result;
import infos.ResultList;
import infos.ResultSave;

/**
 * @authors Adel, Paul Permet l'affichage du rapport détaillé de l'analyse dans
 *          une nouvelle fenêtre, à la demande de l'utilisateur. Les éventuelles
 *          anomalies détectées sont mises en évidence graphiquement.
 */
public class ReportFrame extends JFrame {

	private static final Color MESSAGE_ERROR_COLOR = Color.RED;

	private ResultSave list;

	private JPanel panel;

	private JScrollPane scroller;

	public ReportFrame(ResultSave list) {
		super("Detailed report");
		this.list = list;
		initLayout();
	}

	private void initLayout() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);

		scroller = new JScrollPane(highlightAnomalies(list));
		panel.add(scroller);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().add(BorderLayout.CENTER, panel);
		setSize(500, 480);
		setVisible(true);
		setResizable(false);
	}

	private JPanel highlightAnomalies(ResultList list) {
		JPanel resultsArea = new JPanel();
		resultsArea.setLayout(new BoxLayout(resultsArea, BoxLayout.Y_AXIS));
		// On met en évidence les anomalies en parcourant les résultats.
		for (Result result : list.getResults()) {
			JTextArea subTextArea = new JTextArea();
			if (result.getAnomaly().equals("true")) {
				// Il suffit pour cela de changer la couleur du texte.
				subTextArea.setForeground(MESSAGE_ERROR_COLOR);
			}
			subTextArea.setText(result.toString());
			subTextArea.setEditable(false);
			subTextArea.setFont(Font.getFont(Font.SANS_SERIF));
			resultsArea.add(subTextArea);
		}
		return resultsArea;
	}
}
