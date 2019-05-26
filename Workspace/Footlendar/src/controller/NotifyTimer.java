package controller;

import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyTimer {
	
	private Timer startTimer;
	private Timer remindBeforeTimer;
	//private INotifier notifier;
	
	public NotifyTimer()
	{
		this.startTimer = new Timer();
		this.remindBeforeTimer = new Timer();
		//notifier = new ConsoleNotifier();
	}
	
	public void start(GregorianCalendar startTime, int minutesBefore, Match match)
	{
		//final String mes = message;
		final Match m = match;
		final int mb = minutesBefore;
		this.startTimer.schedule(new TimerTask()
				{
					public void run()
					{	
						User.getInstance().notifyOnStart(m);
					}
				}
		, calculateDateToMiliseconds(startTime));
		
		this.remindBeforeTimer.schedule(new TimerTask()
		{
			public void run()
			{
				User.getInstance().notifyBefore(m, mb);
			}
		}
, calculateDateToMiliseconds(startTime) - minutesBefore * 60 * 1000);
	}
	
	public void resetRemindTimer(GregorianCalendar startTime, int minutesBefore, Match match) 
	{
		remindBeforeTimer.cancel();
		remindBeforeTimer = new Timer();
		final Match m = match;
		final int mb = minutesBefore;
		
		this.remindBeforeTimer.schedule(new TimerTask()
		{
			public void run()
			{
				User.getInstance().notifyBefore(m, mb);
			}
		}
, calculateDateToMiliseconds(startTime) - minutesBefore * 60 * 1000);
	}
	
	private long calculateDateToMiliseconds(GregorianCalendar time)
	{
		return time.getTimeInMillis() - new GregorianCalendar().getTimeInMillis();
	}

}
