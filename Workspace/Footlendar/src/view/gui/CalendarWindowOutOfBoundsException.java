package view.gui;

/**
 * This exception should be thrown if constructing the calendar panel goes wrong.
 * @author Lord9000
 *
 */
public class CalendarWindowOutOfBoundsException extends Exception
{
	private static final long serialVersionUID = 857124230382465713L;

	public CalendarWindowOutOfBoundsException(String msg)
	{
		super(msg);
	}
}
