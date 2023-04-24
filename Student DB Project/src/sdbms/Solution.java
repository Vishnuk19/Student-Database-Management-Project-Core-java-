package sdbms;

import java.util.Scanner;

import customexception.InvalidChoiceException;

public class Solution {

	public static void main(String[] args) {

		System.out.println("-----Welcome to Student Database Management System-----");
		Scanner scan = new Scanner(System.in);
		StudentManagementSystem sms = new StudentManagementSystemImpl();

		while(true)
		{
			System.out.println("------Menu------");
			System.out.println("=====Display=====");
			System.out.println("1.Add Student\n2.Display Student\n3.Display All Students\n4.Remove Student");
			System.out.println("5.Remove All Students\n6.Update Student\n7.Count Students\n8.Sort Students");
			System.out.println("9.Get Students With Highest Marks\n10.Get Students With Lowest Marks\n11.Exit");

			int choice = scan.nextInt();

			switch(choice) {
			case 1:
				sms.addStudent();
				break;
				
			case 2:
				sms.displayStudent();
				break;
				
			case 3:
				sms.displayAllStudents();
				break;
				
			case 4:
				sms.removeStudent();
				break;
				
			case 5:
				sms.removeAllStudents();
				break;
				
			case 6:
				sms.updateStudent();
				break;
				
			case 7:
				sms.countStudents();
				break;
				
			case 8:
				sms.sortStudent();
				break;
				
			case 9:
				sms.getStudentWithHighestMarks();
				break;
				
			case 10:
				sms.getStudentWithLowestMarks();
				break;
				
			case 11:
				System.exit(0);
				break;
				
			default:
				try 
				{
					String msg = "Invalid Choice, Kindly Enter Valid Choice";
					throw new InvalidChoiceException(msg);
				}
				catch(Exception e) 
				{
					System.out.println(e.getMessage());
				}
			} // end of switch
			System.out.println("---------------");
		} // end of while
		
	} // end of main

}// end of class
