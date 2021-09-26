package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;


public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[Add]\n"+ "Enter the category > ");
		
		category = sc.next();
				
		sc.nextLine();
		
		System.out.print("Enter the title > ");
		title = sc.nextLine().trim();
		if (list.isDuplicate(title)) {
			System.out.println("Title can't be duplicate!");
			return;
		}
		
		
		System.out.print("Enter the description > ");
		desc = sc.nextLine().trim();
		
		System.out.print("Enter the due date > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println();
		System.out.println("It's been added.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		//System.out.print("\n[Delete\n"+ "Enter the title of item to remove > ");
		//String title = sc.next();
		
		System.out.print("\n[Delete]\n"+ "Enter the number of item to remove > ");
		int num = sc.nextInt();
		
		if(num > l.listSize() || num < 1) {
			System.out.println("This number doesn't exist.");
			return;
		}
		
		int count=0;
		for (TodoItem item : l.getList()) {
			count++;
			//if (title.equals(item.getTitle())) {
			if(count == num) {
				System.out.println(item.toString());
				System.out.print("Do you want to delete? (y/n) > ");
				String str = sc.next();
				
				if (str.equals("y")) {
					l.deleteItem(item);
					System.out.println("It's been deleted.");
				} else {
					System.out.println("It's been canceled.");
				}
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		//System.out.print("\n[Edit]\n" + "Enter the title of the item you want to update > ");
		
		/*String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("Title doesn't exist!");
			return;
		}*/
		
		
		System.out.print("\n[Edit]\n" + "Enter the number of the item you want to update > ");
		int num = sc.nextInt();
		
		if(num> l.listSize() || num <1) {
			System.out.println("This number doesn't exist.");
			return;
		}
		
		int count =0;
		for(TodoItem item : l.getList()) {
			count++;
			if(count == num) {
				System.out.println(num+". "+item.toString());
				break;
			}
		}
		
		sc.nextLine();
		System.out.print("Enter the new category > ");
		String new_cate = sc.nextLine().trim();
		
		System.out.print("Enter the new title of the item > ");
		String new_title = sc.nextLine().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("Title can't be duplicate!");
			return;
		}
		
		System.out.print("Enter the new description > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("Enter the new due date > ");
		String new_due = sc.nextLine().trim();
		
		count=0;
		for (TodoItem item : l.getList()) {
			count++;
			if (count == num) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_cate, new_title, new_description , new_due);
				l.addItem(t);
				System.out.println("Item updated.");
			}
		}

	}
	
	public static void find(TodoList l, String string) {
		
		if(string.equals("")) {
			System.out.println("Please enter the keword. ex) find keyword");
			return;
		}
		
		int count=0, flag=0;
		for (TodoItem item : l.getList()) {
			count++;
			
			if( item.getTitle().indexOf(string) != -1 || item.getDesc().indexOf(string) != -1) {
				flag++;
				System.out.println(count + ". " + item.toString());
			}
		}
		
		if(flag == 0) {
			System.out.println("There is no keyword you're looking for.");
		} else {
			System.out.println("We found - Total: " + flag );
		}
		
	}
	
	public static void findCate(TodoList l, String string) {
		if(string.equals("")) {
			System.out.println("Please enter the category. ex) find_cate category");
			return;
		}
		
		int count=0, flag=0;
		for (TodoItem item : l.getList()) {
			count++;
			if(item.getCategory().indexOf(string) != -1) {
				flag++;
				System.out.println(count + ". " + item.toString());
			}
		}
		
		if(flag == 0) {
			System.out.println("There is no category you're looking for");
		} else {
			System.out.println("We found - Total: " + flag);
		}
	}

	public static void listAll(TodoList l) {
		
		System.out.println("[All List, Total: "+ l.listSize() + "]");
		
		int count=0;
		for (TodoItem item : l.getList()) {
			//System.out.println("Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc());
			count++;
			System.out.println(count+". "+item.toString());
		}
	}
	
	public static void listCate(TodoList l) {
		Set<String> set = new HashSet<String>();
		
		for (TodoItem item : l.getList()) {
			set.add(item.getCategory());
		}
		
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next());
			
			if(iter.hasNext()) {
				System.out.print(" / ");
			}
	    }
		
		if(set.size() > 0 ) {
			System.out.println("\nA total of " + set.size() + "categories are registered.");
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		
		try {
			
			File file = new File(filename);
			
			
			
			if(file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(filename));
				
				int count=0;
				String oneLine;
				
				while((oneLine = br.readLine()) != null) {
					count++;
					
					StringTokenizer st = new StringTokenizer(oneLine, "##");
					String category = st.nextToken();
					String title = st.nextToken();
					String desc = st.nextToken();
					String due_date=st.nextToken();
					String current_date = st.nextToken();
					
					TodoItem t = new TodoItem(category, title, desc, due_date);
					t.setCurrent_date(current_date);
					l.addItem(t);
				}
				br.close();
				System.out.print(count+"개의 항목을 읽었습니다.\n");
			} else {
				file.createNewFile();
				System.out.print(filename + "파일이 없습니다.\n");
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
				
			w.close();
			System.out.println("모든 데이터가 저장되었습니다.");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
