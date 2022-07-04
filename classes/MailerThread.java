package marty.lunchProgram;


/**
* This is the thread that actually does the mailing. It uses 
* the SMTP class, that was written by PR
* @author MG 07/2001
*/
//public class MailerThread extends MartyMail implements Runnable
public class MailerThread implements Runnable
{

	private String to = "";
	private String fromAddr = "";
	private String msg = "";
	private String subj = "";
	private String from = "";

	/**
	* Constructor.  Takes 1 email bean and prepares it for sending.
	* @param eb The Fully Qualified email bean to send mail to.
	*/
	public MailerThread(EmailBean eb)
	{	
		/*
		super();
		setToField(eb.getTo());
		setFromField(eb.getFrom());
		setBodyText(eb.getMessage());
		setSubjectField(eb.getSubject());
		*/

		to = eb.getTo();
		fromAddr = eb.getFromAddress();
		msg = eb.getMessage();
		subj = eb.getSubject();
		from = eb.getFrom();
	}

	/**
	* Attempts to send mail to the user given in the email bean.
	* Will print an error to stderr if it can't send it out. You can
	* also see if the send was successful by checking the .succeeded()
	* method
	*/
	public void run()
	{
		Thread t = Thread.currentThread();
		t.setPriority(Thread.MIN_PRIORITY);
		if(!send()) {
			System.err.println("Couldn't send mail to user: " + to );
		}
	}

	private boolean success = false;
	/**
	* Check to see if the email appears to have been sent
	* successfully.
	* @return true iff the email appears to have been sent
	* successfully.
	*/
	public boolean succeeded()
	{
		return success;
	}

	/**
	* Attempt to send the mail.  also adjusts the <code>success</code>
	* boolean appropriately.
	*/
	private boolean send()
	{
		try{

			SMTP.sendTxt(from,to,"servername.domain",subj,msg);

		} catch (Exception e){
			System.err.println(e.toString());
			return false;
		}
		success = true;
		return true;
	}
}
