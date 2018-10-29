package project;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class parsing {
	public static ArrayList<Data> pa() // 순위,팀,승점,리그 최그 5경기 승무패를 파싱해온다.

	{
		ArrayList<Data> save = new ArrayList<Data>();
		save.clear();
		String url = "http://score.sports.media.daum.net/record/soccer/"+Client.name+"/trnk.daum";
		InputStream in = URLManager.getURLInputStream(url);
		try
		{
			Document doc = Jsoup.parse(in, URLManager.ENCODING_UTF8,"");

			Elements root = doc.select("#mArticle > div > div.wrap_schedule.wrap_record > div.team_rank.rank_soccer");
			Elements rank = root.select("td[class=num_rank]");//순위
			Elements team = root.select("td.txt_league");// 팀
			Elements win = root.select("td:nth-child(10)"); // 승점
			Elements recent = root.select("td:nth-child(11)"); // 리그 최근 5경기

			for(int i=0; i < rank.size(); ++i)
			{
				save.add(new Data(rank.get(i).text(), team.get(i).text(), win.get(i).text(), recent.get(i).text()));
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return save;
	}
	public static ArrayList<Data> im() // 이미지
	{
		ArrayList<Data> save = new ArrayList<Data>();
		save.clear();
		String url = "http://score.sports.media.daum.net/record/soccer/"+Client.name+"/trnk.daum";
		InputStream in = URLManager.getURLInputStream(url);
		try
		{
			Document doc = Jsoup.parse(in, URLManager.ENCODING_UTF8,"");
			Elements root = doc.select("#mArticle > div > div.wrap_schedule.wrap_record > div.team_rank.rank_soccer"); // 도메인 주소
			Elements mark = root.select("td.txt_league");
			Elements img = mark.select("img");
			if((Client.leaguecheck == 1) || (Client.leaguecheck == 2) || (Client.leaguecheck == 4) )
			{
				for(int i=0; i<20; i++)
				{
					byte[] img_data = URLManager.getImage(img.get(i).attr("src"), null);
					save.add(new Data(img_data));
				}
			}
			else if(Client.leaguecheck == 3)
			{
				for(int i=0; i<18; i++)
				{
					byte[] img_data = URLManager.getImage(img.get(i).attr("src"), null);
					save.add(new Data(img_data));
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return save;
		
	}
	public static ArrayList<Data> player() // 리그 선수 정보
	{
		ArrayList<Data> save = new ArrayList<Data>();
		save.clear();
		String url = "http://score.sports.media.daum.net/record/soccer/"+Client.name+"/prnk.daum";
		InputStream in = URLManager.getURLInputStream(url);
		try
		{
			Document doc = Jsoup.parse(in, URLManager.ENCODING_UTF8,"");

			Elements root = doc.select("#mArticle > div > div.wrap_schedule.wrap_record > div.team_rank.team_type2"); // 도메인 주소
			Elements rank = root.select("td.num_rank");//순위
			Elements name = root.select("td.txt_league");// 선수 이름
			Elements score = root.select("td:nth-child(3)");// 경기수
			Elements min = root.select("td.on"); // 득점
			if(Client.leaguecheck == 1)//epl
			{
				Elements pk = root.select("td:nth-child(6)"); //도움
				Elements subgoal = root.select("td:nth-child(7)"); // 공격 포인트
				for(int i=0; i < 60; ++i)
				{
					save.add(new Data(rank.get(i).text(), name.get(i).text(), score.get(i).text(), min.get(i).text(), pk.get(i).text(), subgoal.get(i).text() ));
				}
			}
			else if(Client.leaguecheck == 2)//리그앙
			{
				Elements pk = root.select("td:nth-child(5)"); //도움
				Elements subgoal = root.select("td:nth-child(6)"); // 공격 포인트
				for(int i=0; i < 35; ++i)
				{
					save.add(new Data(rank.get(i).text(), name.get(i).text(), score.get(i).text(), min.get(i).text(), pk.get(i).text(), subgoal.get(i).text() ));
				}
			}
			else if(Client.leaguecheck == 3) //분데스
			{
				Elements pk = root.select("td:nth-child(5)"); //도움
				Elements subgoal = root.select("td:nth-child(6)"); // 공격 포인트
				for(int i=0; i < 30; ++i)
				{
					save.add(new Data(rank.get(i).text(), name.get(i).text(), score.get(i).text(), min.get(i).text(), pk.get(i).text(), subgoal.get(i).text() ));
				}
			}
			else if(Client.leaguecheck == 4) //세리에 라리가
			{
				Elements pk = root.select("td:nth-child(5)"); //도움
				Elements subgoal = root.select("td:nth-child(6)"); // 공격 포인트
				for(int i=0; i < 32; ++i)
				{
					save.add(new Data(rank.get(i).text(), name.get(i).text(), score.get(i).text(), min.get(i).text(), pk.get(i).text(), subgoal.get(i).text() ));
				}
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return save;
	}
	public static ArrayList<Data> ball() // 발롱도르

	{
		ArrayList<Data> save = new ArrayList<Data>();
		save.clear();
		String url = "http://www.fifa.com/the-best-fifa-football-awards/best-fifa-mens-player/index.html";
		InputStream in = URLManager.getURLInputStream(url);
		try
		{
			Document doc = Jsoup.parse(in, URLManager.ENCODING_UTF8,"");

			Elements root = doc.select("#content-wrap"); // 도메인 주소
			Elements name1 = root.select("div > div.row.row-first > div:nth-child(1) > div:nth-child(2) > div > div > div.info > ul > li.p-name > h3");//순위
			Elements country1 = root.select("div > div.row.row-first > div:nth-child(1) > div:nth-child(2) > div > div > div.info > ul > li.p-team > div > div.t-n > span");// 팀
			Elements name2 = root.select("div > div:nth-child(3) > div > div.col-xs-12.clear-grid > div > div:nth-child(2) > div.info > ul > li.p-name > h3");//순위
			Elements country2 = root.select("div > div:nth-child(3) > div > div.col-xs-12.clear-grid > div > div:nth-child(2) > div.info > ul > li.p-team > div > div.t-n > span");// 팀
			Elements name3 = root.select("div > div:nth-child(3) > div > div.col-xs-12.clear-grid > div > div:nth-child(1) > div.info > ul > li.p-name > h3");//순위
			Elements country3 = root.select("div > div:nth-child(3) > div > div.col-xs-12.clear-grid > div > div:nth-child(1) > div.info > ul > li.p-team > div > div.t-n > span");// 팀
			save.add(new Data(name1.text(),country1.text()));
			save.add(new Data(name2.text(),country2.text()));
			save.add(new Data(name3.text(),country3.text()));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return save;
	}
	
}
