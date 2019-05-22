package controller;

import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyTimer {
	
	private Timer timer;
	private INotifier notifier;
	
	public NotifyTimer()
	{
		this.timer = new Timer();
		notifier = new ConsoleNotifier();
	}
	
	public void start(GregorianCalendar startTime)
	{
		this.timer.schedule(new TimerTask()
				{
					public void run()
					{
						notifier.notify("Mecz siê zaczyna!");
					}
				}
		, calculateDateToMiliseconds(startTime));
	}
	
	private long calculateDateToMiliseconds(GregorianCalendar time)
	{
		return time.getTimeInMillis() - new GregorianCalendar().getTimeInMillis();
	}

}
