import java.util.*;
import java.text.*;
import java.sql.*;
public class FrontEnd
{
	static Connection conn = null;

	public static void main(String[] args) 
	{   final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	    final  String DB_URL = "jdbc:odbc:dbfinal";
	    final String USER = "root";
	    final String PASS = "pikachu";
	    
	    Statement stmt = null;
	    try
	    {
	    	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    	System.out.println("Connecting to database...");
	        conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      /*  
	        System.out.println("Creating statement...");
	        stmt = conn.createStatement();
	        String sql;
	        sql = "SELECT sid,spassword,sname FROM student";
	        ResultSet rs = stmt.executeQuery(sql);
	        
	        while(rs.next()){
	            //Retrieve by column name
	            String sid  = rs.getString("sid");
	           
	            String spassword = rs.getString("spassword");
	            String sname = rs.getString("sname");

	            //Display values
	            System.out.print("SID: " + sid);
	            
	            System.out.print(", spassword: " + spassword);
	            System.out.println(", sname " + sname);
	            
	            */
	            
	         }
	        
	    
	    catch(Exception e)
	    {
	        //Handle errors for Class.forName
	        System.out.println(e);
	     }
	    
	    System.out.println("JDBC connected successfully");
		
	    boolean exit = false;
		Scanner input = new Scanner(System.in);


		do{
			//System.out.println(" ");
			System.out.println(" ");
			System.out.println("1 Login");
			System.out.println("2 Create User");
			System.out.println("3 Exit");
			System.out.print("Enter your choice: ");
			int choice = input.nextInt();
			switch(choice)
			{
			case 1: 
				// add login code
				//System.out.println(" ");
				System.out.println(" ");
				System.out.print("Enter your username: ");
				String username = input.next();
				username = username.trim();
				
				System.out.print("Enter password: ");
				String password = input.next();
				password = password.trim();

				//check whether the user is a student or a lecturer
				// retrieve userid password from database
				// if student, call Student();
								
				  try
				    {
				    
					//   conn = null;
					   Statement stmt1 = null;
					   Statement stmt2 = null;
					  System.out.println("Extracting username and pwd...");
				        stmt1 = conn.createStatement();
				        String sql1,sql2;
				        sql1 = "SELECT sid,spassword FROM student";
				        
				        ResultSet rs1 = stmt1.executeQuery(sql1);
				        
				        
				        boolean flag1 = false;
				        boolean flag2 = false;
				        
				        while(rs1.next())
				        {
				        	
				        
				            //Retrieve by column name
				            String sid  = rs1.getString("sid");
				            sid = sid.trim();
				           
				            String spassword = rs1.getString("spassword");
				            spassword = spassword.trim();
				           
				            if(sid.equals(username) && spassword.equals(password))
				            	{
				            		flag1 = true;
				            		break;
				            	}
				         	       
				         }
				         if(flag1 == false)
				        {
				        
				        sql2 = "SELECT fid,fpassword FROM faculty";
				        stmt2 = conn.createStatement();
				        ResultSet rs2 = stmt2.executeQuery(sql2);
				        while(rs2.next())
				        {
				        
				            //Retrieve by column name
				            String fid  = rs2.getString("fid");
				            fid = fid.trim();
				           
				            String fpassword = rs2.getString("fpassword");
				            fpassword = fpassword.trim();
				          
				            if(fid.equals(username) && fpassword.equals(password))
				            	{
				            		flag2 = true;
				            		break;
				            	}
				         	
				         }
				        
				        }
				         
				        if(flag1 == false && flag2 == false)
				        {
				        	System.out.println("Invalid Username / password. Try again.");
				        }
				        if(flag1 == true)
				        {
				        	// student login
				        	student(username);				        	
				        }
				        if(flag2 == true)
				        {
				        	// student login
				        	lecturer(username);			        	
				        }
				    
				       
				    }
				    catch(Exception e)
				    {
				        //Handle errors for Class.forName
				        System.out.println(e);
				     }
				
				break;
			case 2: 

				System.out.println(" ");
				System.out.println(" ");
				System.out.println("Creating new user");
				System.out.print("Enter your firstname: ");
				String fn = input.next();
				System.out.print("Enter your lastname: ");
				String ln = input.next();
				String n= fn+" "+ln;
				n=n.trim();
				System.out.print("Enter your username: ");
				String u = input.next();
				u=u.trim();
				System.out.print("Enter new password: ");
				String p1 = input.next();
				System.out.print("Enter password again: ");
				String p2 = input.next();

				if(p1.equals(p2))
				{
					// insert user name and pwd in database
					
					p2=p2.trim();
				
					try
					{
						final PreparedStatement stmt3 = conn.prepareStatement(
					        "INSERT INTO student  (sid,spassword,sname) VALUES (?,?,?)");
					stmt3.setString(1,u);
					stmt3.setString(2,p2);
					stmt3.setString(3,n);
					stmt3.execute();
					}
					
					catch(Exception e)
				    {
				        //Handle errors for Class.forName
				        System.out.println(e);
				     }
				
					System.out.print("New User created successfully: \n");
				
				
				}
				else{
					System.out.print("Passwords do not match: \n");
				}
				
				
				break;

			case 3: 
				exit = true;
				break;

			default:
				System.out.println("");
				System.out.println("Wrong input, try again!");
			}
		}
		while(!exit);
		
	}


	public static void student(String sid)
	{

		boolean exit = false;
		Scanner input = new Scanner(System.in);

		System.out.println("");


		do{
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("Welcome, "+sid);
			System.out.println("1 Select Course");
			System.out.println("2 Add Course");
			System.out.println("3 Back");
			System.out.print("Enter your choice: ");
			int choice = input.nextInt();
			switch(choice){
			case 1: 
				// display courses which the user has registered for

				//take input from user abt which course he wants to select
				//call select_course_student();
				
				try
				{
				
				sid = sid.trim();
				final PreparedStatement stmt5 =conn.prepareStatement("SELECT cid,token,cname FROM course,enroll WHERE cid = c_id AND s_id = ?");
				stmt5.setString(1, sid);
				ResultSet rs5 = stmt5.executeQuery();
				
				
				while(rs5.next()){
		            //Retrieve by column name
		            String cid  = rs5.getString("cid");
		            String token = rs5.getString("token");
		            String cname = rs5.getString("cname");

		            //Display values
		            System.out.print("Course ID: " + cid);
		            System.out.print(", Course Token: " + token);
		            System.out.println(", Course Name: " + cname);      
		         }
				
				}
				
				
				catch(Exception e)
				{
					System.out.println(e);
				}
				

				System.out.println("Enter course ID: (eg: CSC501)");
				String cin = input.next();
				cin = cin.trim();
				
				
				// handle the case where user enters garbage input
				
				boolean f = validate(sid,cin);
				if (f == true)
				{
				System.out.println("Success!");
				// displaying menu for the sid,cid pair
				select_course_student(sid,cin);
				}
				else
				{
					System.out.println("Wrong Input! Try again");
				}
				break;
			case 2: 

				System.out.println("Enter course token");
				String token = input.next();
				
				//validate course token
				boolean v = validate_token(token);
				if(v == false)
					System.out.println("Invalid Token Number. Try again");

				//check if token belongs to a course after due date
				else
					if(!validate_date(token))
					System.out.println("Course Over. Cannot Register.");
				
				//else, update tables and print enrolled

				if(v == true && validate_date(token))
				{
					
					try
					{
						
						final PreparedStatement stmt9 =conn.prepareStatement("SELECT cid  FROM course WHERE token = ?");
						stmt9.setString(1, token);
						ResultSet rs9 = stmt9.executeQuery();
						String cid=null;
						
						while(rs9.next())
						
						{
							 cid = rs9.getString("cid");
					         
				         }
						
						cid=cid.trim();
						
						// check whether user is already enrolled
						final PreparedStatement stmt10 =conn.prepareStatement("SELECT count(*) AS count FROM enroll WHERE c_id = ? AND s_id = ?");
						stmt10.setString(1, cid);
						stmt10.setString(2, sid);
						ResultSet rs10 = stmt10.executeQuery();
						int c = 0;
						
						while(rs10.next())
						{
							 c = rs10.getInt("count"); 
				        }
						
						if(c != 0)
						{
							System.out.println("Already Registered! Enter different token.");
						}
						else
						{
							
							final PreparedStatement stmt8 = conn.prepareStatement(
							        "INSERT INTO enroll (c_id,s_id) VALUES (?,?)");
							stmt8.setString(1,cid);
							stmt8.setString(2,sid);
						
							stmt8.executeUpdate();
							
							System.out.println("Congratulations! You are now enrolled in "+token);
						}
						
					
					}
						
					
					catch(Exception e)
				    {
				        //Handle errors for Class.forName
				        System.out.println(e);
				        
				     }
					
				}
				
				break;

			case 3: 
				//exit = true;
				// break;
				return;

			default:
				System.out.println("");
				System.out.println("Wrong input, try again!");
			}
		}
		while(!exit);


	}

	
	public static boolean validate(String sid, String cid)
	{
		boolean f = false;
		try
		{	
		sid = sid.trim();
		cid = cid.trim();
			
		final PreparedStatement stmt6 =conn.prepareStatement("SELECT count(*) AS count FROM enroll WHERE c_id = ? AND s_id = ?");
		stmt6.setString(1, cid);
		stmt6.setString(2, sid);
		ResultSet rs6 = stmt6.executeQuery();
		int count = 0;
		 while(rs6.next())
	        {
			 count  = rs6.getInt("count");
	        }
		if(count == 1)
			f = true;
				
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return f;
	}
	
	public static boolean validate_token(String token)
	{
		boolean f = false;
		try
		{
		
		token = token.trim();
		final PreparedStatement stmt7 =conn.prepareStatement("SELECT count(*) AS count FROM course WHERE token = ?");
		stmt7.setString(1, token);
		ResultSet rs7 = stmt7.executeQuery();
		int c = 0;
		
		while(rs7.next())
		{
			 c = rs7.getInt("count");
		}
		
		if(c == 1)
			f = true;
		
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		return f;
	}

	
	public static boolean validate_date(String token)
	{
		boolean f = false;
		try
		{
		
		token = token.trim();
		final PreparedStatement stmt7 =conn.prepareStatement("SELECT count(*) AS count FROM course WHERE cend > (select date(now())as DateToday) AND token = ?");
		stmt7.setString(1, token);
		ResultSet rs7 = stmt7.executeQuery();
		int c = 0;
		
		while(rs7.next())
		{
			 c = rs7.getInt("count");  
         }
		
		if(c == 1)
			f = true;
		
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		return f;
	}
	
	
	
	
	public static void select_course_student(String sid, String cid)
	{

		boolean exit = false;
		Scanner input = new Scanner(System.in);


		do{

			System.out.println(" ");
			System.out.println(" ");
			System.out.println("1 View Scores");
			System.out.println("2 Attempt Homework");
			System.out.println("3 View past Submissions");
			System.out.println("4 Back");

			System.out.print("Enter your choice: ");
			int choice = input.nextInt();
			switch(choice){
			case 1: 
				// display scores to the user
				
				try
				{
				
				sid = sid.trim();
				cid = cid.trim();
				final PreparedStatement stmt4 =conn.prepareStatement("SELECT DISTINCT ename AS ExerciseName,tname AS TopicName,attemptno AS AttemptNo,score,final_score FROM enroll,course_has_topic,exercise_has_topic,student_solves_exercise,topic,exercise WHERE student_solves_exercise.exerciseid = exercise_has_topic.e_id AND enroll.s_id = ? AND enroll.c_id = ? AND course_has_topic.tid IN (SELECT tid from course_has_topic WHERE cid = ?) AND exercise_has_topic.e_id IN (SELECT e_id from course_has_topic WHERE t_id IN (SELECT tid from course_has_topic WHERE cid = ?)) AND exercise.eid IN (SELECT e_id from course_has_topic WHERE t_id IN (SELECT tid from course_has_topic WHERE cid = ?)) AND exercise_has_topic.t_id = topic.tid AND course_has_topic.cid = ? AND student_solves_exercise.studentid = ?;");
			//	SELECT DISTINCT studid,tname,score FROM student_attempts_topic,course_has_topic,topic WHERE course_has_topic.tid = topic.tid AND course_has_topic.tid = student_attempts_topic.topic_id AND course_has_topic.cid = 'CSC440'AND studid = 'amsonmal'; 
				stmt4.setString(1, sid);
				stmt4.setString(2, cid);
				stmt4.setString(3, cid);
				stmt4.setString(4, cid);
				stmt4.setString(5, cid);
				stmt4.setString(6, cid);
				stmt4.setString(7, sid);
				
				/*
				SELECT DISTINCT ename AS ExerciseName,tname AS TopicName,attemptno AS AttemptNo,score,final_score 
				FROM enroll,course_has_topic,exercise_has_topic,student_solves_exercise,topic,exercise
				WHERE 
				enroll.s_id ='amsonmal' AND
				enroll.c_id = 'CSC440' AND
				student_solves_exercise.exerciseid = exercise_has_topic.e_id AND	
				course_has_topic.tid IN (SELECT tid from course_has_topic WHERE cid = 'CSC440') AND
				exercise_has_topic.e_id IN (SELECT e_id from course_has_topic WHERE t_id IN (SELECT tid from course_has_topic WHERE cid = 'CSC440')) AND
				exercise.eid IN (SELECT e_id from course_has_topic WHERE t_id IN (SELECT tid from course_has_topic WHERE cid = 'CSC440')) AND
				exercise_has_topic.t_id = topic.tid AND
				course_has_topic.cid = 'CSC440'AND
				student_solves_exercise.studentid = 'amsonmal';
				
				*/
				
				ResultSet rs4 = stmt4.executeQuery();
				
				int c = 0;
				
			
				while(rs4.next())
				{
		            //Retrieve by column name
					  String exercise_name = rs4.getString("ExerciseName");
					  String topic_name = rs4.getString("TopicName");
					  String s  = rs4.getString("AttemptNo");
		              Double score = rs4.getDouble("score");
		              Double max_score = rs4.getDouble("final_score");
		           
		            //Display values
		            System.out.print("ExerciseName " + exercise_name);
		            System.out.print(", Topic Name: " + topic_name);
		            System.out.print(", AttemptNo " + s); 
		            System.out.print(", Score " + score);
		            System.out.println(", Score for the exercise\t" + max_score);
		            c++;
		           
		         }
				
				if(c == 0)
				{
					System.out.println("You haven't attempted any exercise for this course yet.");
				}
				
				}
				
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				break;
			case 2: 

				//display a list of open homeworks
				
				try
				{
				sid = sid.trim();
				cid = cid.trim();
			
				
				String exname = null;
				String eid = null;
				int count = 0;
				String at_no=null;
				int attempt_no = 0;
				System.out.print("Homeworks Within Due Date: \n");
				
				String store_eid[] = new String[50];
				String store_atno[] = new String[50];
				
				String store_eid1[] = new String[50];
				
				
				final PreparedStatement stmt1x =conn.prepareStatement("select DISTINCT exercise.eid,exercise.ename,topic.tname,exercise.due_date from exercise, course_has_topic, exercise_has_topic,enroll,topic where enroll.c_id = ? AND enroll.s_id = ? AND course_has_topic.tid = exercise_has_topic.t_id AND exercise_has_topic.e_id IN (select e_id from exercise_has_topic where t_id IN (SELECT tid from course_has_topic where cid = ?)) AND exercise_has_topic.e_id = exercise.eid AND course_has_topic.tid = topic.tid AND topic.tid = exercise_has_topic.t_id AND exercise.due_date >= (select date(now())as DateToday);");		
				
				stmt1x.setString(1, cid);
				stmt1x.setString(2, sid);
				stmt1x.setString(3, cid);
				ResultSet rs1x = stmt1x.executeQuery();
				int j1 = 1;
				while(rs1x.next())
				{
		            //Retrieve by column name
				//	count++;
					exname  = rs1x.getString("eid");
					String ename  = rs1x.getString("ename");
		            String tname  = rs1x.getString("tname");
		            String due_date = rs1x.getString("due_date");
                    
		            //Display values
		            exname = exname.trim();
		            store_eid[j1] = exname;
		           
		            System.out.print(j1+")");
		            System.out.print("  Exercise ID: " + exname);
		           
		            System.out.print("\tExercise Name: " + ename);
		           
		            System.out.print("\tTopic Name: " + tname);
		            System.out.print("\tDue Date: " + due_date);
		            j1++;
		            System.out.println();
		            
		         }
				

				System.out.println("Enter ur choice.");
				int ch = input.nextInt();
				
				eid = store_eid[ch];
				eid = eid.trim();

				
				// counting the number of attempts made by this student on this assignment
				final PreparedStatement stmta =conn.prepareStatement("select count(attemptno) as count from student_solves_exercise where exerciseid = ? AND studentid = ?;");		
				stmta.setString(1, eid);
				stmta.setString(2, sid);
				ResultSet rs1a = stmta.executeQuery();
				int c = 0;
				while(rs1a.next())
				{
		            c  = rs1a.getInt("count");
		            System.out.println("No of attempts found: "+c);
		            
		         }
				
				if(c == 0)
				{
				attempt_no = 1;
				}
				if(c == 1)
				{
				attempt_no = 2;
				}
			
				if(c>=2) 
				{
					System.out.println("Maximum Attempts reached: 2");
					return;
				}

				
				
				// a = Integer.parseInt(at_no);	// int value of attempt no
				
				System.out.print("Exercises: \n");
				
				
				int confirm = 0;
				int quit = 0;
				do
				 {
				
				final PreparedStatement stmt14 =conn.prepareStatement("SELECT qid,question from question,exercise_has_question where questionid = qid AND exid = ?;");
				stmt14.setString(1, eid);
				
				ResultSet rs14 = stmt14.executeQuery();
				int j = 1;
				 int option_selected [] = new int[200]; 	// to store the options selected by the user
				 
				 
				//Displaying Questions
				while(rs14.next())
				{
					
		            //Retrieve by column name
					String quesid  = rs14.getString("qid");
					quesid = quesid.trim();
		            String question  = rs14.getString("question");
		            /*
		            String atno = rs11.getString("attemptno");
		            Double score = rs11.getDouble("score");
		            */

		            //Display values
		            System.out.print("Question " +j+":" + question);
		            
		            // Displaying options
		            String yes = "yes";
		            String no = "no";
		            yes = yes.trim();
		            no = no.trim();
		            final PreparedStatement stmt15 =conn.prepareStatement("SELECT answer.answer FROM ((SELECT ansid,answer FROM answer WHERE answer.qid = ? AND answer.correct_flag = ? ORDER BY RAND() LIMIT 1) UNION (SELECT ansid,answer FROM answer WHERE qid = ? AND correct_flag = ? ORDER BY RAND() LIMIT 3)) AS abc,answer Where answer.ansid = abc.ansid ORDER BY RAND() LIMIT 4;");
					 
					stmt15.setString(1, quesid);  //q1
					stmt15.setString(2, yes);  //yes
					stmt15.setString(3, quesid);  //q1
					stmt15.setString(4, no);  //no
					ResultSet rs15 = stmt15.executeQuery();
				
					
					//String store[] = new String[50];
					int k = 1;
					System.out.println("\nAlternatives: ");
					String answer_option = null;
					String answer_store[] = new String[200];
					
					//Displaying Options
					while(rs15.next())
					{
			            //Retrieve by column name
						
						 answer_option  = rs15.getString("answer");
						 
				            
			            //Display values
						answer_option = answer_option.trim();
						answer_store[k] = answer_option;
			            System.out.print(k+")");
			            System.out.print(answer_option);
			            k++;
			            System.out.println();
			            
			         }
		                      
		            // End of displaying one Q and its options
		            // Now storing the option selected by user
					
						int user_choice = 0;
						int chosen_option = 0;
					do{
						System.out.println("Enter your answer: ");
			            chosen_option = input.nextInt();
			       //     chosen_option = chosen_option.trim();
			            System.out.println("Are u sure? Press 1 for YES or 0 for NO.");
			            user_choice = input.nextInt();
			            
					}while(user_choice!=1);
					
		         //   System.out.println("Enter your answer: ");
		       //     String chosen_option = input.next();
		       //    chosen_option = chosen_option.trim();
					option_selected[j] = chosen_option;
					
					System.out.println("Selected option: "+answer_store[chosen_option]);
					
					// checking whether the chosen option is right
					
					
					
					final PreparedStatement stmt16 =conn.prepareStatement("SELECT  answer from answer WHERE qid = ? AND correct_flag = 'Yes';");
					 
					stmt16.setString(1, quesid);
					
					ResultSet rs16 = stmt16.executeQuery();
				//	System.out.print(" Verifying answers : \n");
					
					String right_answers[] = new String[2];
					
					int l = 0;

					while(rs16.next())
					{
			            //Retrieve by column name
						
						 right_answers[l]  = rs16.getString("answer");
						
						l++;
					
					}
					
					int points = 0;
					///////////////////////////////////////////////////////////////////////
					//Storing the right answer in a variable "temp"
					
					
					String temp=null;
					
						if(answer_store[1].equals(right_answers[0]) || answer_store[1].equals(right_answers[1]))
						{
							temp=answer_store[1];
						}
						if(answer_store[2].equals(right_answers[0]) || answer_store[2].equals(right_answers[1]))
						{
							temp=answer_store[2];
						}
						if(answer_store[3].equals(right_answers[0]) || answer_store[3].equals(right_answers[1]))
						{
							temp=answer_store[3];
						}
						if(answer_store[4].equals(right_answers[0]) || answer_store[4].equals(right_answers[1]))
						{
							temp=answer_store[4];
						}
					
					
				////////////////////////////////////////////////////////////////////////////////////////////////////	
					
					if(answer_store[chosen_option].equals(right_answers[0]) || answer_store[chosen_option].equals(right_answers[1]))
					{
						// answer is correct, calculate score
						
						final PreparedStatement stmt17 =conn.prepareStatement("SELECT correct_points from exercise WHERE eid = ?;"); 
						stmt17.setString(1, exname);
						ResultSet rs17 = stmt17.executeQuery();
						
						
						while(rs17.next())
						{
				            //Retrieve by column name
							points  = rs17.getInt("correct_points");
						
						}
						
					}
					else
					{
						final PreparedStatement stmt17 =conn.prepareStatement("SELECT incorrect_points from exercise WHERE eid = ?;"); 
						stmt17.setString(1, exname);
						ResultSet rs17 = stmt17.executeQuery();
						
						while(rs17.next())
						{
				            //Retrieve by column name
							points  = rs17.getInt("incorrect_points");
						
						}
						
					}
							
					
				//	System.out.println(option_selected[j]);
					
					// Now storing these selected alternatives in the database
					
						final PreparedStatement stmtx = conn.prepareStatement("INSERT INTO storage  (stud_id,ex_id,attempt_no,q_id,answer,score,right_answer) VALUES (?,?,?,?,?,?,?)");
						stmtx.setString(1,sid);
						stmtx.setString(2,eid);
						stmtx.setInt(3,attempt_no);
						stmtx.setString(4,quesid);
						stmtx.setString(5,answer_store[chosen_option]);
						stmtx.setDouble(6,points);
						stmtx.setString(7,temp);
						stmtx.execute();
					
					
					// Done storing
					
					j++;
		            System.out.println();
		            
				}
				
				System.out.println("Do you want to submit the assignment?");
				System.out.println("Enter 1 for YES or 0 for NO");
				confirm = input.nextInt();
				
				if(confirm == 0)
				{
					System.out.println("Do you want to quit the assignment and go to the previous menu?");
					System.out.println("Enter 1 for YES or 0 for NO");
					quit = input.nextInt();
					
				}
				
				if(quit == 1)
					break;
			
			}while(confirm!=1);
				
				
				
				// Displaying whole Attempt to the user:
				 int final_marks = 0;
				
				if(quit!= 1)
				{
					// calculating score 
					final PreparedStatement stmt18 =conn.prepareStatement("SELECT SUM(score) as marks from storage WHERE stud_id = ? AND ex_id = ? AND attempt_no = ?;"); 
					stmt18.setString(1,sid);
					stmt18.setString(2,eid);
					stmt18.setInt(3,attempt_no);
					ResultSet rs18 = stmt18.executeQuery();
					
					while(rs18.next())
					{
			            //Retrieve by column name
						final_marks  = rs18.getInt("marks");
					
					}
										
					
					// Increment Attempt number
					
					final PreparedStatement stmtx1 = conn.prepareStatement("INSERT INTO student_solves_exercise  (exerciseid,studentid,attemptno,score,attempted,final_score) VALUES (?,?,?,?,?,?)");
					stmtx1.setString(1,eid);
					stmtx1.setString(2,sid);
					stmtx1.setInt(3,attempt_no);
					stmtx1.setDouble(4,final_marks);
					stmtx1.setInt(5,1);
					stmtx1.setDouble(6,0);
					
					stmtx1.execute();
					
					
					//Calculating final score of this exercise
					
					final PreparedStatement stmt171 =conn.prepareStatement("SELECT score from student_solves_exercise WHERE exerciseid = ? AND studentid = ?;"); 
					stmt171.setString(1, eid);
					stmt171.setString(2,sid);
					ResultSet rs171 = stmt171.executeQuery();
					
					 int score_store[]=new int[2];
					 score_store[0] = -100;
					 score_store[1] = -100;
					int x = 0;
					int y = 0;
					while(rs171.next())
					{
			            //Retrieve by column name
						x = rs171.getInt("score");
						score_store[y] = x;
						y++;
						
					}
					
					int max = Math.max(score_store[0],score_store[1]);
					
					// Update final score in student_solves_exercise
					
					
					final PreparedStatement stmty = conn.prepareStatement("UPDATE student_solves_exercise SET final_score = ? WHERE exerciseid = ? AND studentid = ?");
					stmty.setDouble(1,max);
					stmty.setString(2,eid);
					stmty.setString(3,sid);
					
					stmty.execute();
					
					System.out.println("Your answers have been recorded!");
					
				}
				
				
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				break;

			case 3: 
				// display hw which are past and within due date
				
				try
				{
				sid = sid.trim();
				final PreparedStatement stmt11 =conn.prepareStatement("SELECT DISTINCT exercise.ename,tname,attemptno,score FROM enroll,student_solves_exercise,exercise_has_topic,topic,exercise,course_has_topic WHERE enroll.s_id = ? AND enroll.c_id = ? AND enroll.s_id = student_solves_exercise.studentid AND student_solves_exercise.exerciseid = exercise_has_topic.e_id AND exercise_has_topic.t_id = topic.tid AND course_has_topic.cid = enroll.c_id AND course_has_topic.tid = exercise_has_topic.t_id AND student_solves_exercise.exerciseid = exercise.eid AND exercise.due_date < (select date(now())as DateToday);");
				 
				stmt11.setString(1, sid);
				stmt11.setString(2, cid);
				ResultSet rs11 = stmt11.executeQuery();
				System.out.println();
				System.out.println("Homeworks Within Due Date: ");
				System.out.println();
                
                String store_eid[] = new String[50];
                int store_atno[] = new int[50];
                
                int display = 1;
                
				while(rs11.next())
				{
					
		            //Retrieve by column name
					System.out.print(display+")");
					String ename  = rs11.getString("ename");
					ename = ename.trim();
					store_eid[display] = ename;
					
		            String tname  = rs11.getString("tname");
		            int atno1 = rs11.getInt("attemptno");
		            store_atno[display] = atno1;
		          
		            Double score = rs11.getDouble("score");
                    
		            //Display values
		            System.out.print("Exercise Name: " + ename);
		            System.out.print("\tTopic Name: " + tname);
		            System.out.print(",\t Attempt number: " + atno1);
		            System.out.println(", \tScore " + score); 
		         
		            display++;
		         }
				
				final PreparedStatement stmt12 =conn.prepareStatement("SELECT DISTINCT eid,exercise.ename,tname,attemptno,score FROM enroll,student_solves_exercise,exercise_has_topic,topic,exercise,course_has_topic WHERE enroll.s_id = ? AND enroll.c_id = ? AND enroll.s_id = student_solves_exercise.studentid AND student_solves_exercise.exerciseid = exercise_has_topic.e_id AND exercise_has_topic.t_id = topic.tid AND course_has_topic.cid = enroll.c_id AND course_has_topic.tid = exercise_has_topic.t_id AND student_solves_exercise.exerciseid = exercise.eid AND exercise.due_date >= (select date(now())as DateToday);");
				 
				stmt12.setString(1, sid);
				stmt12.setString(2, cid);
				ResultSet rs12 = stmt12.executeQuery();
				System.out.println();
				System.out.print("Homeworks After Due Date(Completed): ");
				System.out.println();
				
				while(rs12.next())
				{
		            //Retrieve by column name
					System.out.print(display+")");
					String eid  = rs12.getString("eid");
					eid = eid.trim();
					String ename  = rs12.getString("ename");
					ename = ename.trim();
					store_eid[display] = eid;
					
		            String tname  = rs12.getString("tname");
		            int atno2 = rs12.getInt("attemptno");
		            store_atno[display] = atno2;
		            
		            Double score = rs12.getDouble("score");
                    
		            //Display values
		            System.out.print("Exercise Name: " + ename);
		            System.out.print("\tTopic Name: " + tname);
		            System.out.print(",\t Attempt number: " + atno2);
		            System.out.println(",\t Score " + score); 
		           
		            display++;
		         }
				
				System.out.println("Enter your choice.");
				int ch = input.nextInt();
				
				String ename = store_eid[ch];
				ename = ename.trim();
				int atno = store_atno[ch];
				
				
				final PreparedStatement stmt20 =conn.prepareStatement("select q_id,question,answer,score,right_answer from question,storage where storage.q_id = question.qid AND stud_id = ? AND ex_id = ? AND attempt_no = ?;");
				 
				stmt20.setString(1, sid);
				stmt20.setString(2, ename);
				stmt20.setInt(3, atno);
				ResultSet rs20 = stmt20.executeQuery();
				
				int d = 1;
				String ques_store[] = new String[100];
				
				//Displaying the attempt to the user
				System.out.println("Here is your attempt and the corresponding feedback: ");
				while(rs20.next())
				{
		            //Displaying question, answer and its score
					
					System.out.print(d+")");
					String qid  = rs20.getString("q_id");
					ques_store[d] = qid;
					String ques  = rs20.getString("question");
					String ans  = rs20.getString("answer");
		            Double score = rs20.getDouble("score");
		            String right_answer  = rs20.getString("right_answer");
		            		
                    
		            //Display values
		            System.out.print("Question: " + ques);
		            System.out.print("\t Your Answer : " + ans);
		            System.out.print("\t Right Answer : " + right_answer);
		            System.out.print("\t Score: " + score);
		           
		            
		            String yes = "Yes";
		            String no = "No";
		            yes = yes.trim();
		            no = no.trim();
		            
		            if(score > 0)
		            {
		            	//right answer, display hint
		            	final PreparedStatement stmt21 =conn.prepareStatement("select hint from hint,ans_has_hint,answer where hint.hid = ans_has_hint.hid AND answer.ansid = ans_has_hint.ansid AND answer.qid = ? AND correct_flag = ?;");
						 
						stmt21.setString(1, qid);
						stmt21.setString(2, yes);
						ResultSet rs21 = stmt21.executeQuery();
						 System.out.println("\t You answered correctly");
						 String hint = null;
						while(rs21.next())
						{   
							 hint  = rs21.getString("hint");
							
							
						}
						System.out.println("\t Hint: " +hint);
		            }
		            else
		            {
		            	// wrong answer, display explanation
		            	final PreparedStatement stmt21 =conn.prepareStatement("select explanation from explanation,ans_has_explanation,answer where explanation.expid = ans_has_explanation.explanationid AND answer.ansid = ans_has_explanation.answerid AND answer.qid = ? AND correct_flag = ?;");
						 
						stmt21.setString(1, qid);
						stmt21.setString(2, no);
						ResultSet rs21 = stmt21.executeQuery();
						System.out.println("\t You answer was incorrect");
						String explanation=null;
						while(rs21.next())
						{   
					
							explanation = rs21.getString("explanation");
							
						
						}
						 System.out.println("\t Explanation: " +explanation);
						
		            	
		            }  
		            
		            d++;
		         }
				
				
				}
				
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				break;

			case 4: 
				return;

			default:
				System.out.println("");
				System.out.println("Wrong input, try again!");

			}
		}
		while(!exit);

	}

	public static void lecturer(String f)
	{
		boolean exit = false;
		Scanner input = new Scanner(System.in);
		System.out.println(" ");
        String fid=f;
        fid=fid.trim();
		do{
			System.out.println(" ");
			System.out.println(" ");

			System.out.println("Welcome, Lecturer!");
			System.out.println("1 Select Course");
			System.out.println("2 Add Course");
			System.out.println("3 Back");


			System.out.print("Enter your choice: ");
			int choice = input.nextInt();
			switch(choice){
			case 1: 
				// display courses which the user has registered for

				//take input from user abt which course he wants to select
				//call select_course();
				
				
				try
				{
				
				
				final PreparedStatement stmt15 =conn.prepareStatement("SELECT cid,token,cname,cstart,cend FROM course WHERE fid =?");
				stmt15.setString(1, fid);
				ResultSet rs15 = stmt15.executeQuery();
				
				
				while(rs15.next()){
		            //Retrieve by column name
		            String cid  = rs15.getString("cid");
		            String token = rs15.getString("token");
		            String cname = rs15.getString("cname");
		            String cstart = rs15.getString("cstart");
		            String cend = rs15.getString("cend");
		            //Display values
		            System.out.print("Course ID: " + cid);
		            System.out.print(", Course Token: " + token);
		            System.out.print(", Course Name: " + cname); 
		            System.out.print(", Course Start date: " + cstart); 
		            System.out.println(", Course End date: " + cend); 
		         }
				
				}
				
				
				catch(Exception e)
				{
					System.out.println(e);
				}
				

				System.out.println("Enter course ID: (eg: CSC501)");
				String cin = input.next();
				cin = cin.trim();
				
				boolean f2 = validate_faculty(fid,cin);
				if (f2 == true)
				{
				System.out.println("Success!");
				// displaying menu for the fid,cid pair
				select_course_lecturer(fid,cin);
				}
				else
				{
					System.out.println("Wrong Input! Try again");
				}
				break;
				
				
				
				
				

				
				
			case 2: 

				System.out.println("Enter course token");
				String token = input.next();
				//validate course token
				boolean v = validate_token_faculty(token);
				if(v == false)
					System.out.println("Invalid Token Number. Try again");

				//check if token belongs to a course after due date

				else
					if(!validate_date_faculty(token))
					System.out.println("Course Over. Cannot Register.");
				//check invalid token, if so, go back

				//else, update tables and print enrolled

				if(v == true && validate_date_faculty(token))
				{
					
					try
					{
						
						final PreparedStatement stmt16 =conn.prepareStatement("SELECT cid  FROM course WHERE token = ?");
						stmt16.setString(1, token);
						ResultSet rs16 = stmt16.executeQuery();
						String cid=null;
						
						while(rs16.next())
						
						{
							 cid = rs16.getString("cid");
					         
				         }
						
						cid=cid.trim();
						
						// check whether user is already enrolled
						final PreparedStatement stmt17 =conn.prepareStatement("SELECT count(*) AS count FROM course WHERE cid=? && fid<>'NULL';");
						stmt17.setString(1, cid);
						
						ResultSet rs17 = stmt17.executeQuery();
						int c = 0;
						
						while(rs17.next())
						{
							 c = rs17.getInt("count"); 
				        }
						
						if(c != 0)
						{
							System.out.println("Already faculty registered! Enter different token.");
						}
						else
						{
							System.out.println("Enter the id of TA");
							String taname=input.next();
							taname=taname.trim();
							
							final PreparedStatement stmt18 = conn.prepareStatement(
							        "UPDATE course SET fid = ?,taname = ? WHERE cid = ?;");
							stmt18.setString(1,fid);
							stmt18.setString(2,taname);
							stmt18.setString(3,cid);
							stmt18.executeUpdate();
							
							System.out.println("Congratulations! You are now enrolled as a faculty for "+token+' '+cid);
						}
						
					
					}
						
					
					catch(Exception e)
				    {
				        //Handle errors for Class.forName
				        System.out.println(e);
				        
				     }
					
				}
				
				
				
				
				break;

			case 3: 
				exit = true;
				break;


			default:
				System.out.println("");
				System.out.println("Wrong input, try again!");

			}
		}
		while(!exit);



	}
////////////////////////////////////////////////////////////////////////////////
	
	public static boolean validate_faculty(String fid, String cid)
	{
		boolean f3 = false;
		try
		{	
		fid = fid.trim();
		cid = cid.trim();
			
		final PreparedStatement stmt19 =conn.prepareStatement("SELECT count(*) AS count FROM course WHERE cid = ? AND fid = ?");
		stmt19.setString(1, cid);
		stmt19.setString(2, fid);
		ResultSet rs19 = stmt19.executeQuery();
		int count = 0;
		 while(rs19.next())
	        {
			 count  = rs19.getInt("count");
	        }
		if(count == 1)
			f3 = true;
				
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return f3;
	}
	
	public static boolean validate_token_faculty(String token)
	{
		boolean f4 = false;
		try
		{
		
		token = token.trim();
		final PreparedStatement stmt20 =conn.prepareStatement("SELECT count(*) AS count FROM course WHERE token = ?");
		stmt20.setString(1, token);
		ResultSet rs20 = stmt20.executeQuery();
		int c = 0;
		
		while(rs20.next())
		{
			 c = rs20.getInt("count");
		}
		
		if(c == 1)
			f4 = true;
		
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		return f4;
	}

	
	public static boolean validate_date_faculty(String token)
	{
		boolean f5 = false;
		try
		{
		
		token = token.trim();
		final PreparedStatement stmt21 =conn.prepareStatement("SELECT count(*) AS count FROM course WHERE cend > (select date(now())as DateToday) AND token = ?");
		stmt21.setString(1, token);
		ResultSet rs21 = stmt21.executeQuery();
		int c = 0;
		
		while(rs21.next())
		{
			 c = rs21.getInt("count");  
         }
		
		if(c == 1)
			f5 = true;
		
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		return f5;
	}
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////

	public static void select_course_lecturer(String fid,String cid)
	{

		boolean exit = false;
		Scanner input = new Scanner(System.in);


		do{
			System.out.println(" ");
			System.out.println(" ");

			System.out.println("1 Add Homework");
			System.out.println("2 Edit Homework");
			System.out.println("3 Add Question");
			System.out.println("4 Add answer");
			System.out.println("5 Reports	");
			System.out.println("6 Back");


			System.out.print("Enter your choice: ");
			int choice = input.nextInt();
			switch(choice){
			case 1: 
				// taking input for different parameters

				System.out.println(" ");
				System.out.println(" ");
			//	System.out.println("Enter the homework id (e.g. e1):");
				String eid;
				String start_date = null;
				String due_date = null;
			//	eid = eid.trim();
				
				System.out.println("Enter the homework name:");
				String ename= input.next();
				ename = ename.trim();       
				
				System.out.println("Enter start date(YYYY-MM-DD)");
				start_date = input.next();
				System.out.println(start_date);
				start_date = start_date.trim();
			
				System.out.println("Enter due date(YYYY-MM-DD)");
				due_date = input.next();
				due_date = due_date.trim();
				
				System.out.println("Enter number of attempts");
				int att = input.nextInt();
				
				System.out.println("Enter score selection scheme: Enter 1 for Max or 0 for Avg ");
				String scheme = input.next();
				scheme = scheme.trim();
				
				System.out.println("List of topics is as follows: ");
				
				
				try
				{
					final PreparedStatement stmt17 =conn.prepareStatement("SELECT * from topic;"); 
					
					ResultSet rs17 = stmt17.executeQuery();
					
					String store_tid[] = new String[50];
					int i = 1;
					int it = 1;
					int in;
					System.out.println();
					System.out.println("Select topics from below: Enter 1 for YES or 0 for NO.");
					System.out.println();
					while(rs17.next())
					{
			            //Retrieve by column name
						String tid  = rs17.getString("tid");
						tid = tid.trim();
						String tname  = rs17.getString("tname");
						
						System.out.println("Topic " +it+") " + tname);
						System.out.println("Do you want to select this topic? Enter 1 for YES or 0 for NO.");
						in = input.nextInt();
						if(in == 1)
						{
							store_tid[i] = tid;
							i++;
						}
					it++;
					}
					
					
					// copied till here
					
					String store_qid[] = new String[50];
					
					System.out.println("Select questions from below: Enter 1 for YES or 0 for NO.");
					
					int n = 1;
					
					for(int m = 1; m<i; m++)
					{
						final PreparedStatement stmt18 =conn.prepareStatement("SELECT qid,question from question where topicid = ?;"); 
						stmt18.setString(1, store_tid[m]);
						ResultSet rs18 = stmt18.executeQuery();
						
						
						
						int ti = 1;
						int inp;
						
						while(rs18.next())
						{
				            //Retrieve by column name
							String qid  = rs18.getString("qid");
							qid = qid.trim();
							String qname  = rs18.getString("question");
							
							System.out.println("Question " +ti+") " + qname);
							System.out.println("Do you want to select this question? Enter 1 for YES or 0 for NO.");
							inp = input.nextInt();
							if(inp == 1)
							{
								store_qid[n] = qid;
								n++;
							}
						ti++;
						}
					}
					
					System.out.println("Enter correct answer points: ");
					int cap = input.nextInt();
								
					
					System.out.println("Enter incorrect answer points: ");
					int wap = input.nextInt();
					
					//calculating the eid
					
					final PreparedStatement stmt18m =conn.prepareStatement("SELECT COUNT(eid) as count from exercise;"); 
					ResultSet rs18m = stmt18m.executeQuery();
					
					int c_e = 0;
					while(rs18m.next())
					{
			            //Retrieve by column name
						c_e  = rs18m.getInt("count");
						
					}
					
				// now insert into answer
					int c_e1 = c_e+1;
					 eid = "e"+c_e1;
					eid=eid.trim();
					
					
					
					// inserting this data into exercise table
					
					
					final PreparedStatement stmtx = conn.prepareStatement("INSERT INTO exercise (eid,ename,due_date,correct_points,incorrect_points,start_date,grading_scheme,no_of_attempts) VALUES (?,?,?,?,?,?,?,?);");
					stmtx.setString(1,eid);
					stmtx.setString(2,ename);
					stmtx.setString(3,due_date);
					stmtx.setInt(4,cap);
					stmtx.setInt(5,wap);
					stmtx.setString(6,start_date);
					stmtx.setString(7,scheme);
					stmtx.setInt(8,att);
					stmtx.execute();
				
					//updating exercise_has question
					
					
					for(int z = 1; z<n; z++)
					{
						final PreparedStatement stmty = conn.prepareStatement("INSERT INTO exercise_has_question (exid,questionid) VALUES (?,?);");
						stmty.setString(1,eid);
						stmty.setString(2,store_qid[z]);
						
						stmty.execute();
												
					}
					
					//updating exercise_has_topic
					
					
					for(int z1 = 1; z1<i; z1++)
					{
						final PreparedStatement stmty = conn.prepareStatement("INSERT INTO exercise_has_topic (e_id,t_id) VALUES (?,?);");
						stmty.setString(1,eid);
						stmty.setString(2,store_tid[z1]);
						stmty.execute();
												
					}
					
					
					
					System.out.println("Homework created. Questions added succcessfully! ");
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				

				break;
			case 2: 
				
				//Edit HWs
				//Display the list of HWs
				fid = fid.trim();
				cid = cid.trim();
				System.out.println("");
				System.out.println("Displaying list of Homeworks");
				System.out.println("Select homework to edit");
			
			//	System.out.println(fid);System.out.println(cid);
				
				try
				{
				final PreparedStatement stmt21 =conn.prepareStatement("SELECT DISTINCT eid,ename,start_date,due_date FROM exercise,course,course_has_topic,exercise_has_topic WHERE course_has_topic.cid IN (SELECT cid FROM course WHERE fid = ?) AND exercise.eid IN (SELECT e_id FROM course_has_topic,exercise_has_topic WHERE course_has_topic.tid=exercise_has_topic.t_id AND course_has_topic.cid IN (SELECT cid FROM course WHERE fid = ?));");		
				
				stmt21.setString(1, fid);
				stmt21.setString(2, fid);
				
				/*
				SELECT DISTINCT eid,ename FROM exercise,course,course_has_topic,exercise_has_topic
				WHERE course_has_topic.cid IN (SELECT cid FROM course WHERE fid = 'kogan') 
				AND exercise.eid IN (SELECT e_id FROM course_has_topic,exercise_has_topic 
				WHERE course_has_topic.tid=exercise_has_topic.t_id AND course_has_topic.cid IN (SELECT cid FROM course WHERE fid = 'kogan'));
				
				*/
				String exname = null;
				String exname1 = null;
				
				String store_eid[] = new String[50];
				
				
				ResultSet rs1x = stmt21.executeQuery();
				int j1 = 1;
				while(rs1x.next())
				{
		            //Retrieve by column name
				//	count++;
					exname  = rs1x.getString("eid");
					exname1  = rs1x.getString("ename");
		            String start  = rs1x.getString("start_date");
		            String due = rs1x.getString("due_date");
                    
		            //Display values
		            exname = exname.trim();
		            store_eid[j1] = exname;
		           
		            System.out.print(j1+")");
		            System.out.print("  Exercise ID: " +exname);
		           
		            System.out.print("\tExercise Name: " +exname1);
		           
		            System.out.print("\tStart Date: " +start);
		            System.out.print("\tDue Date: " +due);
		            j1++;
		            System.out.println();
		            
		         }
				
				System.out.println("Enter ur choice.");
				int ch = input.nextInt();
				
				eid = store_eid[ch];
				eid = eid.trim();
				
				int choice1 = 0;
				
				do{
					System.out.println(" ");
					
					System.out.println(" Choose what to update: ");
					System.out.println(" ");
					System.out.println("1 Start date");
					System.out.println("2 End date");
					System.out.println("3 Number of attempts");
					System.out.println("4 Score selection");
					System.out.println("5 Question numbers");
					System.out.println("6 Correct answer points");
					System.out.println("7 Inorrect answer points ");
					System.out.println("8 Back");
					
					choice1 = input.nextInt();
					
					switch(choice1)
					{
					case 1: 
						boolean flag;
						String start_date1 = null;
						do
						{
							System.out.println("Enter new start date: (YYYY-MM-DD)");
							start_date1 = input.next();
							start_date1 = start_date1.trim();
							
							
							// validating date entered by user
							flag = true;
							if (start_date1 == null || !start_date1.matches("\\d{4}-[01]\\d-[0-3]\\d"))
							{
								flag = false;
							}
							
							
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						    df.setLenient(false);
						    try {
						        df.parse(start_date1);
						       
						    } 
						    catch (ParseException ex) 
						    {
						    	System.out.println("Wrong date format.");	
						    	System.out.println("Try again: ");
						    }
						}while(flag!=true);
						
						// updating date
						final PreparedStatement stmty = conn.prepareStatement("UPDATE exercise SET start_date = ? WHERE eid = ?");
						stmty.setString(1,start_date1);
						stmty.setString(2,eid);
						
						stmty.execute();
						
						System.out.print("Start date updated successfully!");
						break;
						
					case 2:
						boolean flag1;
						String end_date1 = null;
						do
						{
							System.out.println("Enter new start date: (YYYY-MM-DD)");
							end_date1 = input.next();
							end_date1 = end_date1.trim();
							
							
							// validating date entered by user
							flag1 = true;
							if (end_date1 == null || !end_date1.matches("\\d{4}-[01]\\d-[0-3]\\d"))
							{
								flag1 = false;
							}
							
							
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						    df.setLenient(false);
						    try {
						        df.parse(end_date1);
						       
						    } 
						    catch (ParseException ex) 
						    {
						    	System.out.println("Wrong date format.");	
						    	System.out.println("Try again: ");
						    }
						}while(flag1!=true);
									
						
						// updating end date
						final PreparedStatement stmty1 = conn.prepareStatement("UPDATE exercise SET end_date = ? WHERE eid = ?");
						stmty1.setString(1,end_date1);
						stmty1.setString(2,eid);
						
						stmty1.execute();
						
						System.out.print("End date successfully updated!");
						break;
						
					case 3: 
						System.out.println("Enter new number_of_attempts:");
						int no_of_attempts1 = input.nextInt();
					//	no_of_attempts1 = no_of_attempts1.trim();
						
						final PreparedStatement stmty2 = conn.prepareStatement("UPDATE exercise SET no_of_attempts = ? WHERE eid = ?");
						stmty2.setInt(1,no_of_attempts1);
						stmty2.setString(2,eid);
						
						stmty2.execute();
						
						System.out.print("Number of attempts successfully updated!");
						break;
					
					case 4: 
						System.out.println("Enter new score:(MAX or AVG)");
						String score1=input.next();
						score1 = score1.trim();
						
						final PreparedStatement stmty3 = conn.prepareStatement("UPDATE exercise SET grading_scheme = ? WHERE eid = ?");
						stmty3.setString(1,score1);
						stmty3.setString(2,eid);
						
						stmty3.execute();
						
						System.out.print("Grading scheme successfully updated!");
						break;
						
						
					case 5: 
						
						System.out.println("Choose an option:1 or 2 or anything else for back ");
						System.out.println("1) Delete from exiting questions: ");
						System.out.println("2) Add more questions: ");
						int choiceq = input.nextInt();
						
						if(choiceq==1)
						{
							
					    System.out.println("Here are your questions.Enter 1 for YES or 0 for NO:");
					    
					    final PreparedStatement stmt18 =conn.prepareStatement("Select qid,question from exercise_has_question,question where exercise_has_question.questionid=question.qid and exercise_has_question.exid= ?;"); 
					    //Select qid,question from exercise_has_question,question where exercise_has_question.questionid=question.qid and exercise_has_question.exid='e1';
						stmt18.setString(1, eid);
						ResultSet rs18 = stmt18.executeQuery();
						
						String store_qid[] = new String[100];
						
					
						int inp;
						int n=1;
						int t1=1;
						while(rs18.next())
						{
				            //Retrieve by column name
							String qid  = rs18.getString("qid");
							qid = qid.trim();
							String qname  = rs18.getString("question");
							
							System.out.println("Question " +t1+") " + qname);
							System.out.println("Do you want to select this question for deletion? Enter 1 for YES or 0 for NO.");
							inp = input.nextInt();
							if(inp == 1)
							{
								store_qid[n] = qid;
								n++;
							}
						    t1++;
						}
							
						
						for(int z = 1; z<=n; z++)
						{
							final PreparedStatement stmtb = conn.prepareStatement("DELETE FROM exercise_has_question WHERE exid = ? AND questionid = ?;");
							stmtb.setString(1,eid);
							stmtb.setString(2,store_qid[z]);
							
							stmtb.execute();
													
						}
						System.out.println("Questions deleted successfully");	
							
						//	Select qid,question from exercise_has_question,question where exercise_has_question.questionid=question.qid and exercise_has_question.exid='e1';

							
							//Query for exercise has questions i.e. questions already allotted to that exercise
							
							//modify exercise_has_questions i.e. delete entry
						}
						
						else if(choiceq==2)
						{
							
							//Query for choosing questions that come in the topics related to the course
							//Modify exercise_has_questions i.e. add entries
							
							
							System.out.println("List of topics is as follows: ");
							
							
							try
							{
								final PreparedStatement stmt17 =conn.prepareStatement("SELECT * from topic;"); 
								
								ResultSet rs17 = stmt17.executeQuery();
								
								String store_tid[] = new String[50];
								int i = 1;
								int it = 1;
								int in;
								System.out.println();
								System.out.println("Select topics from below: Enter 1 for YES or 0 for NO.");
								System.out.println();
								while(rs17.next())
								{
						            //Retrieve by column name
									String tid  = rs17.getString("tid");
									tid = tid.trim();
									String tname  = rs17.getString("tname");
									
									System.out.println("Topic " +it+") " + tname);
									System.out.println("Do you want to select this topic? Enter 1 for YES or 0 for NO.");
									in = input.nextInt();
									if(in == 1)
									{
										store_tid[i] = tid;
										i++;
									}
								it++;
								}
								
								
								// copied till here
								
								String store_qid[] = new String[50];
								
								System.out.println("Select questions from below: Enter 1 for YES or 0 for NO.");
								
								int n = 1;
								
								for(int m = 1; m<i; m++)
								{
									final PreparedStatement stmt18 =conn.prepareStatement("SELECT qid,question from question where topicid = ?;"); 
									stmt18.setString(1, store_tid[m]);
									ResultSet rs18 = stmt18.executeQuery();
									
									
									
									int ti = 1;
									int inp;
									
									while(rs18.next())
									{
							            //Retrieve by column name
										String qid  = rs18.getString("qid");
										qid = qid.trim();
										String qname  = rs18.getString("question");
										
										System.out.println("Question " +ti+") " + qname);
										System.out.println("Do you want to select this question? Enter 1 for YES or 0 for NO.");
										inp = input.nextInt();
										if(inp == 1)
										{
											store_qid[n] = qid;
											n++;
										}
									ti++;
									}
									for(int z = 1; z<n; z++)
									{
										final PreparedStatement stmt44 = conn.prepareStatement("INSERT INTO exercise_has_question (exid,questionid) VALUES (?,?);");
										stmt44.setString(1,eid);
										stmt44.setString(2,store_qid[z]);
										
										stmt44.execute();
																
									}
									
									
									System.out.println("Questions added successfully!!!");
									
									
								}
							
				
							}
							catch(Exception e)
							
							{
								System.out.println(e);
								
							}
							
						
						}
						
						else
							
						{
							break;
						
						}
						
						
						
						
						
						
						
						
						// Done updating question numbers
						
						
						
						break;
						
					case 6: 
						System.out.println("Enter new correct_answer_points:");
						int cap1 = input.nextInt();
					//	cap1=cap1.trim();
						
						final PreparedStatement stmty5 = conn.prepareStatement("UPDATE exercise SET correct_points = ? WHERE eid = ?");
						stmty5.setInt(1,cap1);
						stmty5.setString(2,eid);
						
						stmty5.execute();
						
						System.out.print("Correct answer points successfully updated!");
						break;
						
						
					case 7: 
						System.out.println("Enter new incorrect_answer_points:");
						int wap1 = input.nextInt();
					//	wap1=wap1.trim();
						
						final PreparedStatement stmty6 = conn.prepareStatement("UPDATE exercise SET incorrect_points = ? WHERE eid = ?");
						stmty6.setInt(1,wap1);
						stmty6.setString(2,eid);
						
						stmty6.execute();
						
						System.out.print("Incorrect answer points successfully updated!");
						break;
						
						
						
					case 8: 
						break;
						
					}
				}while(choice1!=8);
				
				
				
				
				
				
				}
				catch(Exception e)
				{
					System.out.println(e);
				}

				break;

			case 3: 
				// Add question
				
				try
				{

					System.out.println("Menu for Adding questions: ");
					System.out.println("Displaying a list of all topics: ");
					
					final PreparedStatement stmt17 =conn.prepareStatement("SELECT * from topic;"); 
					
					ResultSet rs17 = stmt17.executeQuery();
					
					String store_tid[] = new String[50];
					int i = 1;
					int it = 1;
					int in;
					System.out.println();
					System.out.println("Select topics from below: Enter 1 for YES or 0 for NO.");
					System.out.println();
					while(rs17.next())
					{
			            //Retrieve by column name
						String tid  = rs17.getString("tid");
						tid = tid.trim();
						String tname  = rs17.getString("tname");
						
						System.out.println("Topic " +it+") " + tname);
						System.out.println("Do you want to select this topic? Enter 1 for YES or 0 for NO.");
						in = input.nextInt();
						if(in == 1)
						{
							store_tid[i] = tid;
							i++;
						}
					it++;
					}
					
					
					String store_qid[] = new String[50];
					
					System.out.println("Select questions from below: Enter 1 for YES or 0 for NO.");
					
					int n = 1;
					
					for(int m = 1; m<i; m++)
					{

						// Input question from the user
						System.out.println("For topic number "+m+"that you selected: ");
						
						System.out.println("Enter new question to be added: ");	
						String question = input.next();
						question = question.trim();
						
						System.out.println("Enter its correct answer: ");	
						String correct = input.next();
						
						System.out.println("Enter 1st incorrect answer: ");	
						String inc1 = input.next();
						
						System.out.println("Enter 2nd incorrect answer: ");	
						String inc2 = input.next();
						
						System.out.println("Enter 3rd incorrect answer: ");	
						String inc3 = input.next();
						
						// Now enter these values in the database
						
						
						// first insert into question table
						// get total qids from question
						final PreparedStatement stmt18d =conn.prepareStatement("SELECT COUNT(qid) as count from question;"); 
						ResultSet rs18d = stmt18d.executeQuery();
						
						int c = 0;
						while(rs18d.next())
						{
				            //Retrieve by column name
							c  = rs18d.getInt("count");
							
						}
						
					// now insert into question
						int c1 = c+1;
						String q = "q"+c1;
						final PreparedStatement stmt = conn.prepareStatement("INSERT INTO question (qid,topicid,question,qlevel) VALUES (?,?,?,?);");
						stmt.setString(1,q);
						stmt.setString(2,store_tid[m]);
						stmt.setString(3,question);
						stmt.setInt(4,3);
				
						stmt.execute();
							
						// get eid of this topic
						final PreparedStatement stmt18 =conn.prepareStatement("SELECT e_id from exercise_has_topic where t_id = ?;"); 
						stmt18.setString(1, store_tid[m]);
						ResultSet rs18 = stmt18.executeQuery();
						
						
						while(rs18.next())
						{
				            //Retrieve by column name
							String exid  = rs18.getString("e_id");
							exid = exid.trim();
						
							// now insert new Q into table exercise_has_question
							final PreparedStatement stmty1 = conn.prepareStatement("INSERT INTO exercise_has_question (exid,questionid) VALUES (?,?);");
							stmty1.setString(1,exid);
							stmty1.setString(2,q);
							
							stmty1.execute();
						}
						
						System.out.println("Question added successfully!");
					}
					
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				

				break;

			case 4: 
				// Adding answer
				
				System.out.println("Displaying all questions: ");
				
				try
				{
					final PreparedStatement stmta =conn.prepareStatement("SELECT qid,question from question;"); 
					
					ResultSet rsa = stmta.executeQuery();
					
					String store_qid[] = new String[50];
					int i = 1;
					int in = 0;
					String ques = null;
					System.out.println();
					
	
					while(rsa.next())
					{
			            //Retrieve by column name
						String qid  = rsa.getString("qid");
						qid = qid.trim();
						String question  = rsa.getString("question");
						
						System.out.println("Question " +i+") " + question);
						
						store_qid[i] = qid;
							
						i++;
						
					}
					
					System.out.println("Select question: ");
					in = input.nextInt();
					ques = store_qid[in];
					ques = ques.trim();
					
					System.out.println("Enter answer type: Press 1 for CORRECT or 2 for INCORRECT.");
					int type = input.nextInt();
					
					System.out.println("Enter answer: ");
					String answer = input.next();
					answer = answer.trim();
					System.out.println("Enter hint/explanation:");
					String hint = input.next();
					hint=hint.trim();
					
					// Store this answer in answer table
					
					// getting count of ansids in answer table
					final PreparedStatement stmt18d =conn.prepareStatement("SELECT COUNT(ansid) as count from answer;"); 
					ResultSet rs18d = stmt18d.executeQuery();
					
					int c = 0;
					while(rs18d.next())
					{
			            //Retrieve by column name
						c  = rs18d.getInt("count");
						
					}
					
				// now insert into answer
					int c1 = c+1;
					String a = "a"+c1;
					
					String yes = "Yes";
					yes = yes.trim();
					
					String no = "No";
					no = no.trim();
					
					if(type == 1)
					{
						// correct answer
						final PreparedStatement stmtb = conn.prepareStatement("INSERT INTO answer (ansid,answer,qid,correct_flag) VALUES (?,?,?,?);");
						stmtb.setString(1,a);
						stmtb.setString(2,answer);
						stmtb.setString(3,ques);
						stmtb.setString(4,yes);	
						
						stmtb.execute();
						
						//insert hint for the answer
					
						final PreparedStatement stmt18f =conn.prepareStatement("SELECT COUNT(hid) as count from hint;"); 
						ResultSet rs18f = stmt18f.executeQuery();
						
						int c_h = 0;
						while(rs18f.next())
						{
				            //Retrieve by column name
							c_h  = rs18f.getInt("count");
							
						}
						
					// now insert into answer
						int c_h1 = c_h+1;
						String hid = "h"+c1;
						hid=hid.trim();
						final PreparedStatement stmtg = conn.prepareStatement("INSERT INTO hint (hid,hint) VALUES (?,?);");
						stmtg.setString(1,hid);
						stmtg.setString(2,hint);
						stmtg.execute();
						
						final PreparedStatement stmth = conn.prepareStatement("INSERT INTO ans_has_hint (ansid,hid) VALUES (?,?);");
						stmth.setString(1,a);
						stmth.setString(2,hid);
									
						stmth.execute();
						
						
						
					}
					else
					{
						// incorrect answer
						final PreparedStatement stmtb = conn.prepareStatement("INSERT INTO answer (ansid,answer,qid,correct_flag) VALUES (?,?,?,?);");
						stmtb.setString(1,a);
						stmtb.setString(2,answer);
						stmtb.setString(3,ques);
						stmtb.setString(4,no);	
						
						stmtb.execute();
						
						//insert explanation
						
						final PreparedStatement stmt18f =conn.prepareStatement("SELECT COUNT(expid) as count from explanation;"); 
						ResultSet rs18f = stmt18f.executeQuery();
						
						int c_h = 0;
						while(rs18f.next())
						{
				            //Retrieve by column name
							c_h  = rs18f.getInt("count");
							
						}
						
					// now insert into answer
						int c_h1 = c_h+1;
						String exid = "ex"+c1;
						exid=exid.trim();
						final PreparedStatement stmtg = conn.prepareStatement("INSERT INTO hint (expid,explanation) VALUES (?,?);");
						stmtg.setString(1,exid);
						stmtg.setString(2,hint);
						stmtg.execute();
						
						final PreparedStatement stmth = conn.prepareStatement("INSERT INTO ans_has_explanation (ansid,hid) VALUES (?,?);");
						stmth.setString(1,a);
						stmth.setString(2,exid);
									
						stmth.execute();
						
						
					}
					
					
					
					
					System.out.println("Answer stored successfully with hint/explanation!");
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				
				
				
				
				
				
				break;

			case 5: 
				// Generating reports
				
				
				System.out.println("Report generated for course "+cid+" for faculty "+fid+" is as follows");
				
				try
				{
					final PreparedStatement report =conn.prepareStatement("SELECT DISTINCT exerciseid as ExerciseID,studentid as StudentID,tname as TopicName,attemptno AS AttemptNo,score as Score,attempted AS Attempted_flag,final_score FROM exercise,course,course_has_topic,exercise_has_topic,student_solves_exercise,topic WHERE course_has_topic.cid IN (SELECT cid FROM course WHERE fid = ?) AND exercise_has_topic.t_id = topic.tid AND topic.tid = exercise_has_topic.t_id AND exercise_has_topic.e_id = student_solves_exercise.exerciseid AND student_solves_exercise.exerciseid IN (SELECT e_id FROM course_has_topic,exercise_has_topic WHERE course_has_topic.tid=exercise_has_topic.t_id AND course_has_topic.cid IN (SELECT cid FROM course WHERE fid = ?));"); 
					report.setString(1,fid);
					report.setString(2,fid);
					
					ResultSet rsa = report.executeQuery();
					
				/*	
					

				1	SELECT DISTINCT exerciseid as ExerciseID,studentid StudentID,tname as TopicName,attemptno AS AttemptNo,score as Score,attempted AS Attempted_flag,final_score FROM exercise,course,course_has_topic,exercise_has_topic,student_solves_exercise,topic
				1	WHERE course_has_topic.cid IN (SELECT cid FROM course WHERE fid = 'kogan') 
				1	AND exercise_has_topic.t_id = topic.tid
				1	AND topic.tid = exercise_has_topic.t_id
				1	AND exercise_has_topic.e_id = student_solves_exercise.exerciseid
				1	AND student_solves_exercise.exerciseid IN (SELECT e_id FROM course_has_topic,exercise_has_topic 
				1	WHERE course_has_topic.tid=exercise_has_topic.t_id AND course_has_topic.cid IN (SELECT cid FROM course WHERE fid = 'kogan'));
					
					*/
					System.out.println();
					int i = 1;
	
					while(rsa.next())
					{
			            //Retrieve by column name
						String a  = rsa.getString("ExerciseID");
						String b  = rsa.getString("StudentID");
						String c  = rsa.getString("TopicName");
						String d  = rsa.getString("AttemptNo");
						String e  = rsa.getString("Score");
						String f  = rsa.getString("Attempted_flag");
						String g  = rsa.getString("final_score");

			            System.out.print(i+")");
			            System.out.print("  Exercise ID: " +a);
			            System.out.print("  Student ID: " +b);
			            System.out.print(" \t Topic Name: " +c);
			            System.out.print(" \t Attempt No: " +d);
			            System.out.print("  Score: " +e);
			            System.out.print(" \t Attempted_flag: " +f);
			            System.out.print("  Final Score: " +g);
			           
			            i++;
			            System.out.println();
						
					}
					
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
				
				
				break;

			case 6: 
				return;
				
				default:
					System.out.println("");
					System.out.println("Wrong input. Try again!");

			}
		}
		while(!exit);


	}
	
	


}
