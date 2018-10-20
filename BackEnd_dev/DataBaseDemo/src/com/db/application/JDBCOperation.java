package com.db.application;

import java.util.Scanner;
import com.db.users.Student;
import com.db.daos.DAO;

public class JDBCOperation {
	
	public static void main(String[] args) {
		do{
		System.out.println("++++++++++欢迎使用数据库增删改查功能++++++++++++");
		System.out.println("+++1.insert 2.delete 3.update 4.query+++");
		System.out.println("+++++++++++++请输入您选择的操作+++++++++++++++");
		Scanner input = new Scanner(System.in);
		int c = input.nextInt();
		switch(c){
		case 1: System.out.println("Insert");
		       DAO.getAll();
		           DAO.insert(new Student("Achilles", "Male", "14"));
		           break; 
		case 2: System.out.println("Delete"); 
		       DAO.delete("Achilles");
		           DAO.getAll();
		                    break; 
		case 3: System.out.println("Update");
		       DAO.getAll();
		       DAO.update(new Student("Achilles", "", "7"));
		                    break; 
		case 4: System.out.println("Query"); 
		       DAO.getAll();
		                    break; 
		   default: 
		               System.out.println("consonant"); 
		     }   
		} while(true);
	}
}
