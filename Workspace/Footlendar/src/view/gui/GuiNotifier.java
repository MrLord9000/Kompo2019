package view.gui;

import view.INotifier;

public class GuiNotifier implements INotifier {

	@Override
	public void notify(String message) {
		System.out.println("The notifier works!");
	}

}
