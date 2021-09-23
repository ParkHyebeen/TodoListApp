package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;


public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[Add]\n"+ "Enter the title > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("Title can't be duplicate!");
			return;
		}
		
		sc.nextLine();
		System.out.print("Enter the description > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println();
		System.out.println("It's been added.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("\n[Delete\n"+ "Enter the title of item to remove > "
				);
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("It's been deleted.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n[Edit]\n" + "Enter the title of the item you want to update > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("Title doesn't exist!");
			return;
		}

		System.out.print("Enter the new title of the item > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("Title can't be duplicate!");
			return;
		}
		
		sc.nextLine();
		System.out.print("Enter the new description > ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("Item updated.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n[List]");
		for (TodoItem item : l.getList()) {
			//System.out.println("Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc());
			System.out.println(item.toString());
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
					String title = st.nextToken();
					String desc = st.nextToken();
					String current_date = st.nextToken();
					
					TodoItem t = new TodoItem(title, desc);
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
