package project;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

public class Client extends JFrame implements ActionListener{
	private BufferedReader br = null;      
	private PrintWriter pw = null;  
	private Socket sock = null; 
	private JScrollPane scrollPane;
	private ImageIcon icon;
	public static String name;
	public static int leaguecheck;
	private ImageIcon ii;
	private JTextField search = null;
	private JButton search_button = null;
	private JList jl = null;
	private DefaultListModel model =null;
	private JScrollPane jp = null;
	private JList jl_1 = null;
	private DefaultListModel model_1 =null;
	private JScrollPane jp_1 = null;
	private JLabel a = null;
	private JLabel b = null;
	private JLabel c = null;
	private TextArea area = null;
	private JLabel label = null;
	private JTextField input_ID = null;
	private JButton button = null;
	private JTextField input =null;
	private Font f1,f2,f3,f4;
	private JLabel jl_2 = null;
	private JButton button_2 = null;
	private JButton button_3 = null;
	private JButton button_4 = null;

	Calendar calendar1 = Calendar.getInstance(); 
	int hour = calendar1.get(Calendar.HOUR_OF_DAY); 
	int min = calendar1.get(Calendar.MINUTE); 
	int sec = calendar1.get(Calendar.SECOND); 

	javax.swing.Timer timer; 
	JLabel lbPresent; 

	parsing sc = new parsing();
	public Client()
	{
		super("해외 축구 정보 공유");
		ii=new ImageIcon("아이콘.jpg");
		this.setIconImage(ii.getImage());

		f1 = new Font("돋음",Font.BOLD,15);
		f2 = new Font("함초롱바탕",Font.ROMAN_BASELINE,12);
		f3 = new Font("HYPost",Font.BOLD,20);
		f4 = new Font("휴먼둥근헤드라인",Font.BOLD,23);
		icon = new ImageIcon("배경.jpg");

		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false); 
				super.paintComponent(g);
			}
		};
		background.setLayout(null);

		search = new JTextField();
		search.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		search.setBounds(220, 50, 120, 30);
		search_button = new JButton("버튼");
		search_button.setFont(f2);
		search_button.setBounds(350,50,80,30);
		search_button.setForeground(Color.BLACK);
		search_button.setBackground(Color.ORANGE);

		jl = new JList(new DefaultListModel());
		model = (DefaultListModel) jl.getModel();
		jp = new JScrollPane(jl);
		//jp.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jp.setBounds(7,100,250,320);

		jl_1 = new JList(new DefaultListModel());
		model_1 = (DefaultListModel) jl_1.getModel();
		jp_1 = new JScrollPane(jl_1);
		//jp_1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		jp_1.setBounds(270,100,375,320);

		a = new JLabel("프리미어리그, 라리가, 분데스리가, 세리에A, 리그앙1 만 검색가능합니다.");
		a.setBounds(90, 10, 500, 30);
		a.setFont(f1);
		a.setForeground(Color.ORANGE);

		b = new JLabel("실시간 순위");
		b.setBounds(95, 70, 500, 30);
		b.setFont(f2);
		b.setForeground(Color.ORANGE);

		c = new JLabel("실시간 선수 기록");
		c.setBounds(440, 70, 500, 30);
		c.setFont(f2);
		c.setForeground(Color.ORANGE);

		area = new TextArea();
		area.setBounds(270,455 ,375 ,280);
		area.setEditable(false);


		label = new JLabel("닉네임 :");
		label.setFont(f2);
		label.setBounds(360, 425, 50, 30);
		label.setForeground(Color.ORANGE);

		input_ID = new JTextField();
		input_ID.setBounds(410, 425, 100, 30);

		button = new JButton("로그인");
		button.setBounds(520,425,80,30);
		button.addActionListener(this); // 로그인 처리

		input = new JTextField();
		input.setBounds(270,737,375,30);
		input.addActionListener(this);
		input.setEditable(false);

		jl_2 = new JLabel("기타 옵션");
		jl_2.setFont(f3);
		jl_2.setBounds(90, 400, 100, 100);
		jl_2.setForeground(Color.getHSBColor(120, 84, 146));

		button_2 = new JButton("발롱도르 수상자 ");
		button_2.setBounds(55,480,160,60);
		button_2.setFont(f2);
		button_2.setForeground(Color.BLACK);
		button_2.setBackground(Color.ORANGE);
		button_2.addActionListener(this);

		button_3 = new JButton("하이라이트 영상");
		button_3.setBounds(55,550,160,60);
		button_3.setFont(f2);
		button_3.setForeground(Color.BLACK);
		button_3.setBackground(Color.ORANGE);
		button_3.addActionListener(this);

		//시간
		timer = new javax.swing.Timer(1000, this); 
		timer.setInitialDelay(0); 
		timer.start(); 

		lbPresent = new JLabel(hour + ":" + min + ":" + sec, 
				Label.RIGHT); 
		lbPresent.setBounds(10, 660, 270, 100);
		lbPresent.setForeground(Color.getHSBColor(1, 11, 1));
		lbPresent.setFont(f4);

		background.add(search);
		background.add(search_button);
		background.add(jp);
		background.add(jp_1);
		background.add(a);
		background.add(b);
		background.add(c);

		background.add(area);
		background.add(label);
		background.add(input_ID);
		background.add(button);
		background.add(input);
		background.add(lbPresent);

		background.add(jl_2);
		background.add(button_2);
		background.add(button_3);

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);

		search_button.addActionListener(this);

		try{

			sock = new Socket("localhost", 8008);
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

		}catch(Exception ex){     
			System.out.println(ex);
		}     

	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		String check = search.getText(); // 입력한 키워드를 불러온다.

		if(o == search_button)
		{
			int a=1;

			name=check;
			if(name.equals("프리미어리그"))
			{
				name ="epl";
				leaguecheck=1;
			}
			else if(name.equals("라리가"))
			{
				name ="primera";
				leaguecheck=4;
			}

			else if(name.equals("분데스리가"))
			{
				name ="bundesliga";
				leaguecheck=3;
			}

			else if(name.equals("세리에A"))
			{
				name ="seriea";
				leaguecheck=4;
			}

			else if(name.equals("리그앙1"))
			{
				name ="ligue1";
				leaguecheck=2;
			}

			else
			{
				JOptionPane.showMessageDialog(null, "검색 명령어가 아닙니다.", "검색 오류", JOptionPane.ERROR_MESSAGE);
				a=2;
			}

			if(a==1)
			{
				model.removeAllElements();
				model.addElement("순위        팀       승점     리그 최근 5경기");
				if((leaguecheck == 1) || (leaguecheck == 2) || (leaguecheck == 4) )
				{
					for(int i=0; i<20; i++)
					{
						model.addElement(sc.pa().get(i).getRank()+"위    "+sc.pa().get(i).getTeam()+"    "+sc.pa().get(i).getWin()+"    "+sc.pa().get(i).getRecent());
						model.addElement(sc.im().get(i).getImage());
					}
				}
				else if(leaguecheck == 3)
				{
					for(int i=0; i<18; i++)
					{
						model.addElement(sc.pa().get(i).getRank()+"위    "+sc.pa().get(i).getTeam()+"    "+sc.pa().get(i).getWin()+"    "+sc.pa().get(i).getRecent());
						model.addElement(sc.im().get(i).getImage());
					}
				}
				model_1.removeAllElements();
				model_1.addElement("순위    선수(팀)                   경기수  득점(PK)  도움  공격포인트");
				if(leaguecheck == 1) // epl
				{
					for(int i=0; i<60; i++)
					{
						model_1.addElement(sc.player().get(i).getRank_1()+"위     "+sc.player().get(i).getName()+"       "+
								sc.player().get(i).getScore()+"경기       "+sc.player().get(i).getPk()+"득점       "+sc.player().get(i).getSubpoint()+"도움       "+sc.player().get(i).getPoint()+"포인트");
					}
				}
				else if(leaguecheck == 2) //리그앙
				{
					for(int i=0; i<35; i++)
					{
						model_1.addElement(sc.player().get(i).getRank_1()+"위     "+sc.player().get(i).getName()+"       "+
								sc.player().get(i).getScore()+"경기       "+sc.player().get(i).getPk()+"득점       "+sc.player().get(i).getSubpoint()+"도움       "+sc.player().get(i).getPoint()+"포인트");
					}
				}
				else if(leaguecheck == 3) //분데스리가
				{
					for(int i=0; i<30; i++)
					{
						model_1.addElement(sc.player().get(i).getRank_1()+"위     "+sc.player().get(i).getName()+"       "+
								sc.player().get(i).getScore()+"경기       "+sc.player().get(i).getPk()+"득점       "+sc.player().get(i).getSubpoint()+"도움       "+sc.player().get(i).getPoint()+"포인트");
					}
				}
				else if(leaguecheck == 4) //세리에A , 라리가
				{
					for(int i=0; i<32; i++)
					{
						model_1.addElement(sc.player().get(i).getRank_1()+"위     "+sc.player().get(i).getName()+"       "+
								sc.player().get(i).getScore()+"경기       "+sc.player().get(i).getPk()+"득점       "+sc.player().get(i).getSubpoint()+"도움       "+sc.player().get(i).getPoint()+"포인트");
					}
				}
			}
		}

		if(o == button)//닉네임 입력부분
		{
			String id = input_ID.getText();  // 텍스트필드에 입력된 아아디 값을 서버로 보내기 위해 설정.
			if(id == null || id.trim().equals("")){ // 아이디를 입력 안할시 아이디를 입력하도록 메세지를 보여준다.
				area.append("사용하고자 하는 닉네임를 입력하세요.");
				area.append("\n");
				return;
			} 
			else
			{ 
				area.append("-----"+getTime()+" 접속완료    닉네임 : " + input_ID.getText()+"-----"); // 입력한 경우 서버로 id값을 보내게 설정.
				area.append("\n");
				area.append("※해외 축구 정보 에 오신것을 환영합니다.※");
				area.append("\n");
				input_ID.setText("");
				input_ID.setEditable(false);
				button.setEnabled(false);
				input.setEditable(true);

			}

			pw.println(id.trim());    // 해당 id 값을 서버로 전송.
			pw.flush(); 

			InputThread sc = new InputThread(sock, br);  // 스레드 시작
			sc.start();
		}
		if(o == input)
		{
			String msg = input.getText();          
			pw.println(msg);          
			pw.flush();        
			if(msg.equals("/quit")){       
				try{  
					sock.close();
				}catch(Exception ex){}
				System.exit(0);
			}           
			input.setText("");          
		}    

		if(o == button_2)
		{
			ballon ba = new ballon();
		}

		if(o == button_3)
		{
			try {
				Desktop.getDesktop().browse(new java.net.URI("http://sports.news.naver.com/wfootball/vod/index.nhn"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if(o == button_4)
		{
			try {
				Desktop.getDesktop().browse(new java.net.URI("https://sports.bwin.com/en/sports#categoryIds=&eventId=&leagueIds=&marketGroupId=&page=0&sportId=4&templateIds="));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		++sec; 
		Calendar calendar2 = Calendar.getInstance(); 
		hour = calendar2.get(Calendar.HOUR_OF_DAY); 
		min = calendar2.get(Calendar.MINUTE); 
		sec = calendar2.get(Calendar.SECOND); 
		lbPresent.setText("현재시각 : "+ hour + ":" + min + ":" + sec); 

	}

	public static void main(final String[] args) {
		// TODO Auto-generated method stub

		Client frame = new Client();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,660,800);
		frame.setResizable(false);
		frame.setVisible(true);
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
			}
		}
				); 
	}

	class InputThread extends Thread
	{	

		private Socket sock = null;
		private BufferedReader br = null;
		public InputThread(Socket sock, BufferedReader br)
		{
			this.sock = sock;
			this.br = br;
		}

		public void run()
		{
			try{
				String line = null;
				while((line = br.readLine())!= null)
				{
					area.append(getTime()+line); 
					area.append("\n");
				}
			}catch(Exception e)
			{

			}finally
			{

				try{
					if(br != null)
					{
						br.close();

					}
				}catch(Exception ex)
				{

				}try{
					if(sock != null)
					{
						sock.close();
					}
				}catch(Exception ex)
				{

				}
			}
		}
	}  
	static String getTime() // 시간을 보여주기위한 DATE 형식
	{
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date());
	}
}
