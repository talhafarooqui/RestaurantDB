package connector;
import java.io.*;
import java.sql.*;
import java.util.*;
public class Main
{
	public static void main(String[] args) throws Exception 
	{ 
		/*int rid;
		int zipcode; 
	    String name;
	    String cuisine;
		int Rid;
		String date;
		int ID;
		int score;
		int did;
		int zip_code;
		int restID;
		String company;
		PreparedStatement ps = null;
		//int x;
		//int max=99999;
		//int min=55555;*/
				
		try 
		{
			//BufferedReader bReader = new BufferedReader(new FileReader("C:\\Users\\Talha Farooqui\\Desktop\\restaurant data.txt") );
			//BufferedReader bReader = new BufferedReader(new FileReader("C:\\Users\\Talha Farooqui\\Desktop\\inspection_1.txt") );
			//BufferedReader bReader = new BufferedReader(new FileReader("C:\\Users\\Talha Farooqui\\Desktop\\Delivery.txt") );
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","P@ssword");
			Class.forName("com.mysql.jdbc.Driver");
			//Statement st= con.createStatement();	
			/*String line = null;
			while((line = bReader.readLine()) !=null)
			{
				String[] temp = line.split(",");
				//x=(int)(Math.random() * ((max - min) + 1)) + min;
				
				rid = Integer.parseInt(temp[0]);
				zipcode = Integer.parseInt(temp[2]);
				name= temp[1];
			    cuisine = temp[3];
				String sql="insert into restaurant values('"+rid+"','"+name+"','"+zipcode+"','"+cuisine+"')";
				Rid = Integer.parseInt(temp[1]);
				ID = Integer.parseInt(temp[0]);
				date= temp[2];
			    score = Integer.parseInt(temp[3]);
				String sql="insert into inspection values('"+ID+"','"+Rid+"','"+date+"','"+score+"')";
				did = Integer.parseInt(temp[0]);
				zip_code = Integer.parseInt(temp[1]);
				rID= Integer.parseInt(temp[3]);
				company=temp[2];
				String sql="insert into delivery values('"+did+"','"+zip_code+"','"+company+"','"+restID+"')";
				ps = con.prepareStatement(sql);
				ps.execute();
			}*/
			System.out.println();
			System.out.println("\t\t\t\tRestaurant Database");
			System.out.println();
			System.out.println("We as a group of two have created a restaurant database which includes restaurant \ninformation, delivery information , manager information, inspection report, violations done by the restaurant.\nAs of now there isn't any database that shows whether a restauarant has passed their inspection and whether they had any violation or not.\nThe project was created by a Syed Munawer Ali and Talha Farooqui.");
		    System.out.println();
		    System.out.println("Please select any of the queries described below:");	
		    System.out.println();
		    System.out.println("1.Total number of restaurant with an inspection score 1");
			System.out.println("2.All indian restaurant");
			System.out.println("3.Manager Information of all BURGERKING");
			System.out.println("4.All Inspection history in the year 2018");
			System.out.println("5.All restaurants GrubHub is catering to");
			System.out.println("6.All restaurants by restaurant ID and zipcode who have never had a violation with risk category of High");
			System.out.println("7.All inspection with a citation and score less than 5");
			System.out.println("8.Average inspection score of all BURGRKINGS");
			System.out.println("9.Dirtiest/Most unsafe restaurant : All restaurant by restaurant ID,zipcode and violation description where risk category says HIGH");
			System.out.println("10.Safest restaurant with the best score:All restaurants by id,name,zipcode and description with score equal to 10");
			System.out.println("0.Exit");
			Scanner a1= new Scanner(System.in);
			int opt;
			
			System.out.println();
			do {
				System.out.println("Enter your choice");
				opt=a1.nextInt();	
			switch(opt)
			{
			case 0:
				System.out.println("Thank you for using our database");
				System.out.println("Bye");
				return;
			case 1: 
				Statement s1= con.createStatement();
				ResultSet rs1=s1.executeQuery("select count(i.restID) as result from inspection as i where i.score=1");
				while(rs1.next())
			    {
			    	String result= rs1.getString("result");
			      System.out.println("Result:" +result);	
			    }
			   break; 
			case 2:
				Statement s2= con.createStatement();
				ResultSet rs2= s2.executeQuery("select rid,name from restaurant where cuisine='indian'");
				while(rs2.next()) {
				String id2= rs2.getString("rid");
				String name2= rs2.getString("name");
			    System.out.println("ID:" +id2+ "," +"name:" +name2);
				}
			   break;
			case 3:
				Statement s3= con.createStatement();
				ResultSet rs3= s3.executeQuery("select r.rid,r.name,m.phone from manager as m,restaurant as r where r.rid=m.RestID and r.name='BURGERKING'");
				while(rs3.next())
				{
				String id3= rs3.getString("rid");
				String name3=rs3.getString("name");
				String phone= rs3.getString("phone");
			    System.out.println("ID:" +id3+ "," +"Name : "+name3+ "," +"PhoneNo:" +phone);    
				}
			    break;
			case 4:
				Statement s4= con.createStatement();
				ResultSet rs4= s4.executeQuery("select * from inspection where inspectionDate like '%2018'");
				while(rs4.next())
				{	
				String id4= rs4.getString("restID");
				String inspecID= rs4.getString("inspectionID");
				String date= rs4.getString(3);
				String score=rs4.getString(4);
			    System.out.println("Rid:" +id4+ "," +"Iid:" +inspecID+ "," +"Date:" +date+ "," +"Score:"+score);    
				}
			    break;
			case 5:
				Statement s5= con.createStatement();
				ResultSet rs5= s5.executeQuery("select r.name from restaurant as r,delivery as d where r.rid=d.Rid and d.company='Grubhub'");
				while(rs5.next())
				{	
				String name5= rs5.getString("name");
			    System.out.println("name: " +name5);    
				}
			    break;
			case 6:
				Statement s6= con.createStatement();
				ResultSet rs6= s6.executeQuery("select rid,name,zipcode from restaurant where rid not in (select r.rid from restaurant as r,inspection as i, violation as v where r.rid=i.restID and i.inspectionID=v.InspecID and v.risk='High')");
				while(rs6.next())
				{
					String ID6=rs6.getString("rid");
					String zipcode= rs6.getString("zipcode");
					String name1=rs6.getString("name");
					System.out.println("ID: " +ID6+ "," +"Name: "+name1+ "," +"Zipcode:"+zipcode);
				}
				break;
			case 7:
				Statement s7= con.createStatement();
				ResultSet rs7= s7.executeQuery("select v.inspecID,v.vid,i.score from inspection as i,violation as v where v.inspecID=i.inspectionID and i.score<5");
				while(rs7.next())
				{
					String ID7=rs7.getString("inspecID");
					String vid=rs7.getString(2);
					String score=rs7.getString(3);
				    System.out.println("ID : " +ID7+ "," +"VID : "+vid+ "," +"Score :" +score);
				}
			    break; 	
			case 8:
				Statement s8= con.createStatement();
				ResultSet rs8= s8.executeQuery("select avg(i.score) AS AVERAGE_SCORE from inspection as i,restaurant as r where r.rid=i.restID and r.name='BURGERKING'");
				while(rs8.next())
				{
					String avg=rs8.getString(1);
					System.out.println("Average score:" +avg);
				}
				break;
			case 9:
				Statement s9= con.createStatement();
				ResultSet rs9= s9.executeQuery("select r.rid,r.zipcode,v.desc from restaurant as r,inspection as i,violation as v where i.restID=r.rid and i.inspectionID=v.inspecID and v.risk='High'");
				while(rs9.next())
				{
                  String ID9=rs9.getString("rid");
                  String zipcode=rs9.getString("zipcode");
                  String desc=rs9.getString("desc");
                  System.out.println("ID : "+ID9+ "," +"Zipcode : " +zipcode+ "," +"Description : " +desc);
				}
				break;	
			case 10:
				Statement s10= con.createStatement();
				ResultSet rs10= s10.executeQuery("select r.rid,r.name,r.zipcode,v.desc from restaurant as r, inspection as i, violation as v where r.rid=i.restID and i.inspectionID=v.InspecID and i.score =10");
				while(rs10.next())
				{
					String ID10=rs10.getString("rid");
					String name=rs10.getString("name");
					String zipcode=rs10.getString("zipcode");
					String desc=rs10.getString("desc");
					System.out.println("ID : "+ID10+ "," +"Zipcode : " +zipcode+ "," +"Description: " +desc+ "," +"name : " +name);
				}
				break;
			default:
			    	System.out.println("Wrong entry"); 
			}
		}while(opt<=10);
			//bReader.close();
			con.close();
			a1.close();
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}
	}
}
