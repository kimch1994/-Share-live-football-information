package project;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			ServerSocket server = new ServerSocket(8008);
			System.out.println("해외 축구 정보 공유서버입니다. 접속을 기다립니다.");
			HashMap hm = new HashMap();
			while(true)
			{
				Socket sock = server.accept();
				ChatThread thread = new ChatThread(sock, hm);
				thread.start();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


}

class ChatThread extends Thread
{
	private Socket sock;
	private String id;
	private BufferedReader br;
	private HashMap hm;
	private boolean initFlag = false;

	public ChatThread(Socket sock, HashMap hm)
	{
		this.sock = sock;
		this.hm = hm;
		try{
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			id = br.readLine();
			broadcast(id + "님이 접속했습니다.");
			System.out.println(getTime()+"접속한 사용자의 아이디는 "+ id +"입니다.");

			synchronized(hm)
			{
				hm.put(this.id, pw);
				System.out.println("현재 접속자 수는 " + hm.size() + "입니다.");
			}
			initFlag = true;
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	static String getTime()
	{
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
	public void run()
	{
		try{
			String line = null;
			while((line = br.readLine()) != null)
			{
				if(line.equals("/quit"))
				{
					break;
				}

				if(line.indexOf("/to") == 0)
				{
					sendmsg(line);
				}
				else
				{
					broadcast(id + " : " + line);
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}finally
		{
			synchronized(hm)
			{
				hm.remove(id);
				System.out.println("현재 서버접속자 수는 " + hm.size() + "입니다.");
			}
			broadcast(id + " 님이 접속 종료했습니다.");
			try{
				if(sock != null)
					sock.close();
			}catch(Exception ex){

			}
		}
	}

	public void broadcast(String msg)
	{
		synchronized(hm)  
		{
			Collection collection = hm.values();
			Iterator iter = collection.iterator();
			while(iter.hasNext())
			{
				PrintWriter pw = (PrintWriter)iter.next();
				pw.println(msg);
				pw.flush();
			}
		}
	}
	public void sendmsg(String msg)
	{
		int start = msg.indexOf(" ") +1;
		int end = msg.indexOf(" ", start);

		if(end != -1)
		{
			String to = msg.substring(start,end);
			String msg2 = msg.substring(end + 1);
			Object obj = hm.get(to);
			if(obj != null)
			{
				PrintWriter pw = (PrintWriter)obj;
				pw.println(id + "님이 다음의 귓속말을 보내셨습니다. : "+ msg2);
				pw.flush();
			}
		}
	}

}
