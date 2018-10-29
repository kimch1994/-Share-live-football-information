package project;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Data {
	private String rank , team , win , recent;
	private ImageIcon image;
	private String rank_1, name, score, pk, subpoint, point;
	private String name_1, country_1;
	// 발롱도르 파싱
	public Data(String name_1, String country_1)
	{
		this.name_1 = name_1; //이름 
		this.country_1 = country_1; // 국가
		
	}
	public String getName_1() {
		return name_1;
	}

	public void setName_1(String name_1) {
		this.name_1 = name_1;
	}

	public String getCountry_1() {
		return country_1;
	}

	public void setCountry_1(String country_1) {
		this.country_1 = country_1;
	}

	// 선수 순위 파싱
	public Data(String rank_1, String name, String score, String pk, String subpoint , String point  )
	{
		this.rank_1=rank_1; // 순위
		this.name=name; // 선수 이름
		this.score=score;// 경기수
		this.pk=pk; // 득점
		this.subpoint=subpoint; //도움
		this.point=point; // 공격 포인트
		
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getRank_1() {
		return rank_1;
	}

	public void setRank_1(String rank_1) {
		this.rank_1 = rank_1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubpoint() {
		return subpoint;
	}

	public void setSubpoint(String subpoint) {
		this.subpoint = subpoint;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	//리그 순위 파싱
	public Data(String rank, String team, String win, String recent)
	{
		this.rank=rank; // 순위
		this.team=team; // 팀 이름
		this.win=win; // 승리
		this.recent=recent; // 최근 승무패
				
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getWin() {
		return win;
	}

	public void setWin(String win) {
		this.win = win;
	}

	public String getRecent() {
		return recent;
	}

	public void setRecent(String recent) {
		this.recent = recent;
	}
	// 이미지 파싱
	public Data(byte[]image)
	{
		if(image != null)
		{
			this.image = new ImageIcon(image);
			Image img = this.image.getImage();
			BufferedImage bi = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(img,0,0,20,20,null);
			this.image = new ImageIcon(bi);
		}
		else
		{
			this.image = new ImageIcon();
		}
	}
	
	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}
	
	
}
