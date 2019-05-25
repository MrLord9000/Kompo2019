package controller;

import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyTimer {
	
	private Timer startTimer;
	private Timer remindBeforeTimer;
	private INotifier notifier;
	
	public NotifyTimer()
	{
		this.startTimer = new Timer();
		this.remindBeforeTimer = new Timer();
		notifier = new ConsoleNotifier();
	}
	
	public void start(GregorianCalendar startTime, int minutesBefore, String message)
	{
		final String mes = message;
		final int mb = minutesBefore;
		this.startTimer.schedule(new TimerTask()
				{
					public void run()
					{	
						notifier.notify("Match starts:\n" + mes);
					}
				}
		, calculateDateToMiliseconds(startTime));
		
		this.remindBeforeTimer.schedule(new TimerTask()
		{
			public void run()
			{
				notifier.notify("Match starts in " + mb + " minutes \n" + mes);
			}
		}
, calculateDateToMiliseconds(startTime) - minutesBefore * 60 * 1000);
	}
	
	public void resetRemindTimer(GregorianCalendar startTime, int minutesBefore, String message) 
	{
		remindBeforeTimer.cancel();
		remindBeforeTimer = new Timer();
		final String mes = message;
		final int mb = minutesBefore;
		
		this.remindBeforeTimer.schedule(new TimerTask()
		{
			public void run()
			{
				notifier.notify("Match starts in " + mb + " minutes \n" + mes);
			}
		}
, calculateDateToMiliseconds(startTime) - minutesBefore * 60 * 1000);
	}
	
	private long calculateDateToMiliseconds(GregorianCalendar time)
	{
		return time.getTimeInMillis() - new GregorianCalendar().getTimeInMillis();
	}

}
