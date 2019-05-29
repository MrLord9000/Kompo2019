package view.gui;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import javax.swing.border.BevelBorder;

public final class DayPanelFactory {
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source layout gl_DayPanel
	 */
	public static JPanel createDayPanel(LayoutManager layout) {
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setLayout(layout);
		return panel;
	}
}