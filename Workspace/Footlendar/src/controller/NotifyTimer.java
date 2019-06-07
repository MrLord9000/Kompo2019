package controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class NotifyTimer {
	
	private Timer startTimer;
	private Timer remindBeforeTimer;
	private Timer updateChecker;
	//private INotifier notifier;
	
	public NotifyTimer()
	{
		//this.startTimer = new Timer();
		//this.remindBeforeTimer = new Timer();
		//notifier = new ConsoleNotifier();
	}
	
	public void start(GregorianCalendar startTime, int minutesBefore, Match match)
	{
		//final String mes = message;
		final Match m = match;
		final int mb = minutesBefore;
		
		//Run start Timer
		if(startTime.after(new GregorianCalendar()))
		{
			this.startTimer = new Timer();
			this.startTimer.schedule(new TimerTask()
			{
				public void run()
				{	m.setScore(new Score());
					User.getInstance().notifyOnStart(m);
				}
			}
			, calculateDateToMiliseconds(startTime));
		}
		
		
		
		//Run reminder Timer
		GregorianCalendar remTime = (GregorianCalendar) startTime.clone();
		remTime.add(Calendar.MINUTE, -minutesBefore);
		if(remTime.after(new GregorianCalendar()))
		{
			this.remindBeforeTimer = new Timer();
			this.remindBeforeTimer.schedule(new TimerTask()
			{
				public void run()
				{
					User.getInstance().notifyBefore(m, mb);
				}
			}
			, calculateDateToMiliseconds(startTime) - minutesBefore * 60 * 1000);
			
		}
		
		
		//Run updateChecker Timer
		GregorianCalendar endTime = (GregorianCalendar) startTime.clone();
		endTime.add(Calendar.HOUR, 3);
		//System.out.println("xD");
		if(endTime.after(new GregorianCalendar()))
		{
			
			this.updateChecker = new Timer();
			this.updateChecker.schedule(new TimerTask()
			{
				
				@Override
				public void run()
				{
					//User.getInstance().notifyOnUpdate(m);
					IHtmlScoreReader reader = new FlashScoreHtmlScoreReader();
					Score score = reader.getScore(m);
					if(score != null && !score.equals(m.getScore()))
					{
						m.setScore(score);
						User.getInstance().notifyOnUpdate(m);
					}
					
				}
			}, 1, 60 * 1000);
		}
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
