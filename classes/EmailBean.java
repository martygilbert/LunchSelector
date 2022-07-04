package marty.lunchProgram;

/**
* This class is used to help make mailing more standard.  The classes
* that do mailing should use the <code>EmailBean</code> as a standard
* unit of encompassing all data needed by mailing programs
* @author MG 07/2001
*/
public class EmailBean
{
	/** Default Contructor */
	public EmailBean()
	{ }

	/** Constructor */
	public EmailBean (String fromEmail, String to, String msg, String subj, String fromDispName )
	{
		fromAddress = fromEmail;
		email = to;
		message = msg;
		subject = subj;
		fromName = fromDispName;
	}

	/** Copy Constructor */
	public EmailBean(EmailBean eb)
	{
		subject = eb.getSubject();
		message = eb.getMessage();
		fromAddress = eb.getFromAddress();
		email = eb.getTo();
		fromAddress = eb.getFrom();
		fromName = eb.getFrom();
	}

	private String subject;
	/**
	* Set the subject field for this message.
	*/
	public void setSubject( String subj ){ subject = subj;	}

	/**
	* @return the subject field for this message
	*/
	public String getSubject(){	return subject;	}
		
	private String message;
	/**
	* Set the message field for this message.
	*/
	public void setMessage( String msg ){ message = msg;	}
	/**
	* @return the message field for this message
	*/
	public String getMessage(){	return message;	}

	private String fromName;
	/**
	* Set the from field for this message.
	*/
	public void setFrom(String s){	fromName = s;	}
	/**
	* @return the from field for this message
	*/
	public String getFrom(){	return fromName;		}

	private String fromAddress;
	/**
	* Set the from field for this message.
	*/
	public void setFromAddress(String s){	fromAddress = s;	}
	/**
	* @return the from field for this message
	*/
	public String getFromAddress(){	return fromAddress;		}

	
	private String email;
	/**
	* Set the to field for this message.
	*/
	public void setTo(String s){	email = s;	}
	/**
	* @return the to field for this message
	*/
	public String getTo(){	return email;		}
}
