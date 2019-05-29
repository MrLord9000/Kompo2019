package view.tui;

import view.INotifier;

public class ConsoleNotifier implements INotifier {

	@Override
	public void notify(String message) {
		System.out.println(message);

	}

}
