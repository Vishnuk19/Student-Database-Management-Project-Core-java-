package sdbms;

import java.util.ArrayList; 
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import customexception.InvalidChoiceException;
import customexception.StudentNotFoundException;
import customsorting.SortStudentByAge;
import customsorting.SortStudentById;
import customsorting.SortStudentByMarks;
import customsorting.SortStudentByName;

public class StudentManagementSystemImpl implements StudentManagementSystem 
{

	Scanner ip = new Scanner(System.in);

	Map<String, Student> db = new LinkedHashMap<String, Student>();

	@Override
	public void addStudent() 
	{
		System.out.println("Enter Student Age");
		int age =ip.nextInt();
		System.out.println("Enter Student Name");
		String name = ip.next();
		System.out.println("Enter Student Marks");
		int marks = ip.nextInt();

		Student std = new Student(age, name, marks);
		db.put(std.getId(), std);
		System.out.println("Student Record Inserted Successfully");
		System.out.println("Student id is "+std.getId());
	}

	@Override
	public void displayStudent()
	{
		System.out.println("Enter Student id");
		String id = ip.next(); // String name = ip.next().toUppercase();
		id = id.toUpperCase();

		if(db.containsKey(id)) { // checking if id is present or not
			Student std = db.get(id); // getting the student object
			System.out.println("Id: "+std.getId());
			System.out.println("Age: "+std.getAge());
			System.out.println("Name: "+std.getName());
			System.out.println("Marks: "+std.getMarks());
			//System.out.println(std); as toString() is Overridden;
		}
		else 
		{
			try 
			{
				String msg = "Student with the Id "+id+ " is not Found!";
				throw new StudentNotFoundException(msg);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void displayAllStudents() 
	{
		if(db.size()!=0) 
		{
			System.out.println("Student Details are as Follows:");
			System.out.println("--------------------");
			Set<String> keys = db.keySet(); // JSP101, JSP102;
			for(String key : keys) 
			{
				Student std = db.get(key);
				System.out.println(std); //System.out.println(db.get(key));
			}
		}
		else 
		{
			try 
			{
				String msg = "Student Database is Empty, Nothing to Display!";
				throw new StudentNotFoundException(msg);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeStudent() 
	{
		System.out.println("Enter Student id");
		String id = ip.next(); // String name = ip.next().toUppercase();
		id = id.toUpperCase();

		if(db.containsKey(id)) { // checking if id is present or not
			System.out.println("Student Record Found");
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("Student record deleted Successfully");
		}
		else 
		{
			try 
			{
				String msg = "Student with the Id "+id+ " is not Found!";
				throw new StudentNotFoundException(msg);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudents() 
	{
		if(db.size()!=0) 
		{
			System.out.println("Student Records Available: "+db.size());
			db.clear();
			System.out.println("All Student Records Deleted Successfully");
			System.out.println("Student Records Available: "+db.size());
		}
		else 
		{
			try 
			{
				String msg = "Student Database is Empty, Nothing to Delete!";
				throw new StudentNotFoundException(msg);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void updateStudent() 
	{
		System.out.println("Enter Student id");
		String id = ip.next();
		id = id.toUpperCase();

		if(db.containsKey(id)) 
		{
			Student std = db.get(id);

			System.out.println("Display\n1:Update Age\n2:Update Name\n3.Update Marks");
			System.out.println("Enter Choice");
			int choice = ip.nextInt();

			switch(choice) 
			{
			case 1:
				System.out.println("Enter Age: ");
				int age = ip.nextInt();
				std.setAge(age); // std.setAge(ip.nextInt());
				break;
			case 2:
				System.out.println("Enter Name: ");
				String name = ip.next();
				std.setName(name); // std.setName(ip.nextInt());
				break;
			case 3:
				System.out.println("Enter Marks: ");
				int marks = ip.nextInt();
				std.setMarks(marks); // std.setMarks(ip.nextInt());
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
			}
		}

		else 
		{
			try 
			{
				String msg = "Student Database is Empty, Nothing to Delete!";
				throw new StudentNotFoundException(msg);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void countStudents() 
	{
		System.out.println("No of Student Records: "+db.size());
	}
	@Override
	public void sortStudent() {
		if(db.size() >= 2) {
		Set<String> keys = db.keySet();
		List<Student> list = new ArrayList<Student>();
		for(String key : keys) {
			list.add(db.get(key));
		}

		System.out.println("1: Sort By Id\n 2: Sort By Age\n 3: Sort By Name\n 4: Sort By Marks");
		System.out.println("Enter Choice");
		int choice = ip.nextInt();

		switch(choice) 
		{
		case 1:
			Collections.sort(list, new SortStudentById());
			//for(Student s : list) {
			//	System.out.println(s);}
			display(list);
			break;
		case 2:
			Collections.sort(list, new SortStudentByAge());
			//for(Student s : list) {
			//	System.out.println(s);}
			display(list);
			break;
		case 3:
			Collections.sort(list, new SortStudentByName());
			//for(Student s : list) {
			//	System.out.println(s);}
			display(list);
			break;
		case 4:
			Collections.sort(list, new SortStudentByMarks());
			//for(Student s : list) {
			//	System.out.println(s);}
			display(list);
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
		}
		}
		else {
			try 
			{
				String msg = "No Sufficient Student Records to Compare";
				throw new StudentNotFoundException(msg);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
		}
	
	
	private static void display(List<Student> list) 		
	{
		for(Student s : list) 
		{
			System.out.println(s);
		}
	}
	
	@Override
	public void getStudentWithHighestMarks() 
	{
		if(db.size() >= 2) 
		{
		Set<String> keys = db.keySet();
		List<Student> list =  new ArrayList<Student>();
		for(String key : keys)
		{
			list.add(db.get(key));
		}
		Collections.sort(list, new SortStudentByMarks());
		System.out.println(list.get(list.size()-1)); //getting student object
		}
		else 
		{
			try 
			{
				String msg = "No Sufficient Student Records to Compare";
				throw new StudentNotFoundException(msg);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
	
	@Override
	public void getStudentWithLowestMarks() 
	{
		if(db.size() >=2)
		{
		Set<String> keys = db.keySet();
		List<Student> list =  new ArrayList<Student>();
		for(String key : keys) 
		{
			list.add(db.get(key));
		}
		Collections.sort(list, new SortStudentByMarks());
		System.out.println(list.get(0)); //getting student object
		}
		else 
		{
			try 
			{
				String msg = "No Sufficient Student Records to Compare";
				throw new StudentNotFoundException(msg);
			}
			catch(Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}
}
