package controller;

public class ConsoleNotifier implements INotifier {

	@Override
	public void notify(String message) {
		System.out.println(message);

	}

}
