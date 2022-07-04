package marty.lunchProgram;

import java.io.*;
import java.net.*;

class SMTPException extends Exception {
	public SMTPException(String msg) {
		super(msg);
	}
}


public class SMTP {

	public static void sendHTML(String from, String to, String host,
			String subject, String message) throws IOException, SMTPException {
		Socket sock;
		sock = new Socket(host, 25);
		BufferedReader in =
			new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter out =
			new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));


		getResponse(in, 220);
		out.println("EHLO myserver.mydomain");
		out.flush();
		getResponse(in, 250);
		out.println("MAIL FROM: "+from);
		out.flush();
		getResponse(in, 250);
		out.println("RCPT TO: <"+to+">");
		out.flush();
		getResponse(in, 250);
		out.println("DATA");
		out.flush();
		getResponse(in, 354);
		out.println("From: "+from);
		out.println("To: "+to);
		//XXX FOR HTML EMAILS!!
		out.println("Content-type: text/html");
		if (subject != null) out.println("Subject: "+subject);
		out.println();
		out.println(message);
		out.println(".");
		out.flush();
		getResponse(in, 250);
		out.println("quit");
		out.flush();
		sock.close();
    }
	public static void sendTxt(String from, String to, String host,
			String subject, String message) throws IOException, SMTPException {
		Socket sock;
		sock = new Socket(host, 25);
		BufferedReader in =
			new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter out =
			new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));


		getResponse(in, 220);
		out.println("EHLO myserver.mydomain");
		out.flush();
		getResponse(in, 250);
		out.println("MAIL FROM: "+from);
		out.flush();
		getResponse(in, 250);
		out.println("RCPT TO: <"+to+">");
		out.flush();
		getResponse(in, 250);
		out.println("DATA");
		out.flush();
		getResponse(in, 354);
		out.println("From: "+from);
		out.println("To: "+to);
		//XXX FOR HTML EMAILS!!
		//out.println("Content-type: text/html");
		if (subject != null) out.println("Subject: "+subject);
		out.println();
		out.println(message);
		out.println(".");
		out.flush();
		getResponse(in, 250);
		out.println("quit");
		out.flush();
		sock.close();
	}

	public static void send(String from, String to, String host,
			String subject, String message) throws IOException, SMTPException {
		Socket sock;
		sock = new Socket(host, 25);
		BufferedReader in =
			new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter out =
			new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));


		getResponse(in, 220);
		out.println("EHLO myserver.mydomain");
		out.flush();
		getResponse(in, 250);
		out.println("MAIL FROM: "+from);
		out.flush();
		getResponse(in, 250);
		out.println("RCPT TO: <"+to+">");
		out.flush();
		getResponse(in, 250);
		out.println("DATA");
		out.flush();
		getResponse(in, 354);
		out.println("From: "+from);
		out.println("To: "+to);
		//XXX FOR HTML EMAILS!!
		out.println("Content-type: text/html");
		if (subject != null) out.println("Subject: "+subject);
		out.println();
		out.println(message);
		out.println(".");
		out.flush();
		getResponse(in, 250);
		out.println("quit");
		out.flush();
		sock.close();
	}

	protected static void getResponse(BufferedReader in, int code)
			throws IOException, SMTPException {
		String line;
		do {
			line = in.readLine();
		} while (line.charAt(3) == '-');
		if (Integer.parseInt(line.substring(0, 3)) != code)
			throw new SMTPException("SMTP error: "+line);
	}

	//For testing Purposes only
	public static void main(String args[]){
	}
}
