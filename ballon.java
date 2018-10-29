package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ballon extends JFrame  {

	
	private JScrollPane scrollPane;
	private ImageIcon icon;
	private ImageIcon ii;
	private Font f3;
	
	private JList jl = null;
	private DefaultListModel model =null;
	private JList jl_1 = null;
	private DefaultListModel model_1 =null;
	private JList jl_2 = null;
	private DefaultListModel model_2 =null;
	
	private JLabel label = null;
	private JLabel label2 = null;
	private JLabel label3 = null;
	private JList jl_3 = null;
	private DefaultListModel model_3 =null;
	
	private JLabel label4= null;
	private JLabel label5 = null;
	private JLabel label6 = null;
	private JList jl_4 = null;
	private DefaultListModel model_4 =null;
	
	private JLabel label7= null;
	private JLabel label8 = null;
	private JLabel label9 = null;
	private JList jl_5 = null;
	private DefaultListModel model_5 =null;
	
	private JLabel label10= null;
	parsing sc = new parsing();


	public ballon()
	{
		
		
		super("발롱도르 수상자");
		f3 = new Font("HYPost",Font.BOLD,20);
		ii=new ImageIcon("발롱도르.gif");
		this.setIconImage(ii.getImage());
		setBounds(0,0,895,960);
		setResizable(false);
		icon = new ImageIcon("배경1.jpg");
		
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false); 
				super.paintComponent(g);
			}
		};
		background.setLayout(null);
		
		
		jl = new JList(new DefaultListModel());  // 1위
		model = (DefaultListModel) jl.getModel();
		jl.setBounds(170,80,500,365);
		
		label = new JLabel("1 위");
		label.setBounds(170, 0,100, 30);
		label.setFont(f3);
		label.setForeground(Color.BLUE);
		
		label2= new JLabel("이름 : "+ sc.ball().get(0).getName_1());
		label2.setBounds(170,20,300,30);
		label2.setFont(f3);
		label2.setForeground(Color.BLUE);
		
		label3 = new JLabel(sc.ball().get(0).getCountry_1());
		label3.setBounds(210,48,200,30);
		label3.setFont(f3);
		label3.setForeground(Color.BLUE);
		
		jl_3 = new JList(new DefaultListModel());
		model_3 = (DefaultListModel) jl_3.getModel();
		jl_3.setBounds(170,50,34,22); //1위
		

		jl_1 = new JList(new DefaultListModel()); // 2위
		model_1 = (DefaultListModel) jl_1.getModel();
		jl_1.setBounds(0,530,380,400);
		
		label4 = new JLabel("2 위");
		label4.setBounds(0, 450,100, 30);
		label4.setFont(f3);
		label4.setForeground(Color.GREEN);
		
		label5= new JLabel("이름 : "+ sc.ball().get(1).getName_1());
		label5.setBounds(0,470,200,30);
		label5.setFont(f3);
		label5.setForeground(Color.GREEN);
		
		label6 = new JLabel(sc.ball().get(1).getCountry_1());
		label6.setBounds(40,498,200,30);
		label6.setFont(f3);
		label6.setForeground(Color.GREEN);
		
		jl_4 = new JList(new DefaultListModel());
		model_4 = (DefaultListModel) jl_4.getModel();
		jl_4.setBounds(0,500,34,22);
		
		jl_2 = new JList(new DefaultListModel()); // 3위
		model_2 = (DefaultListModel) jl_2.getModel();
		jl_2.setBounds(501,530,390,400);
		
		label7 = new JLabel("3 위");
		label7.setBounds(501, 450,100, 30);
		label7.setFont(f3);
		label7.setForeground(Color.lightGray);
		
		label8= new JLabel("이름 : "+ sc.ball().get(2).getName_1());
		label8.setBounds(501,470,300,30);
		label8.setFont(f3);
		label8.setForeground(Color.lightGray);
		
		label9 = new JLabel(sc.ball().get(2).getCountry_1());
		label9.setBounds(541,498,200,30);
		label9.setFont(f3);
		label9.setForeground(Color.lightGray);
		
		jl_5 = new JList(new DefaultListModel());
		model_5 = (DefaultListModel) jl_5.getModel();
		jl_5.setBounds(501,500,34,22);
		
		label10 = new JLabel("");
		label10.setBounds(0, 0, 0, 0);
		
		
		
		byte[] image = URLManager.getImage( //1위
				"http://img.fifa.com/mm/photo/ballon-dor/playeroftheyear-men/02/86/28/66/2862866_full-lnd.jpg",
				"http://www.fifa.com/the-best-fifa-football-awards/best-fifa-mens-player/index.html");
		
		byte[] image1 = URLManager.getImage( //2위
				"http://img.fifa.com/mm/photo/ballon-dor/playeroftheyear-men/02/84/75/09/2847509_full-prt.jpg",
				"http://www.fifa.com/the-best-fifa-football-awards/best-fifa-mens-player/index.html");
		
		byte[] image2 = URLManager.getImage( // 3위
				"http://img.fifa.com/mm/photo/ballon-dor/playeroftheyear-men/02/84/75/26/2847526_full-prt.jpg",
				"http://www.fifa.com/the-best-fifa-football-awards/best-fifa-mens-player/index.html");
		
		byte[] image3 = URLManager.getImage( // 국가
				"http://img.fifa.com/images/flags/2/por.png",
				"http://www.fifa.com/the-best-fifa-football-awards/best-fifa-mens-player/index.html");
		
		byte[] image4 = URLManager.getImage( // 국가
				"http://img.fifa.com/images/flags/2/arg.png",
				"http://www.fifa.com/the-best-fifa-football-awards/best-fifa-mens-player/index.html");
		
		byte[] image5 = URLManager.getImage( // 국가
				"http://img.fifa.com/images/flags/2/fra.png",
				"http://www.fifa.com/the-best-fifa-football-awards/best-fifa-mens-player/index.html");

		ImageIcon imageView = new ImageIcon(image);
		ImageIcon imageView1 = new ImageIcon(image1);
		ImageIcon imageView2 = new ImageIcon(image2);
		ImageIcon imageView3 = new ImageIcon(image3);
		ImageIcon imageView4 = new ImageIcon(image4);
		ImageIcon imageView5 = new ImageIcon(image5);
		
		model.addElement(imageView);
		model_3.addElement(imageView3);
		
		model_1.addElement(imageView1);
		model_4.addElement(imageView4);
		
		model_2.addElement(imageView2);
		model_5.addElement(imageView5);
		

		background.add(jl);
		background.add(label);
		background.add(label2);
		background.add(label3);
		background.add(jl_3);
		background.add(jl_1);
		background.add(label4);
		background.add(label5);
		background.add(label6);
		background.add(jl_4);
		background.add(jl_2);
		background.add(label7);
		background.add(label8);
		background.add(label9);
		background.add(jl_5);
		background.add(label10);
		
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		setVisible(true);
	}
	
}
