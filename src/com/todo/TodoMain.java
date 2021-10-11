package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		boolean quit = false;
		
		Menu.displaymenu();
		do {
			Menu.prompt();
			String choice = sc.next();
			String keyword = sc.nextLine().trim();
			
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "find":
				TodoUtil.find(l, keyword);
				break;
				
			case "find_cate":
				TodoUtil.findCate(l, keyword);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				break;

			case "ls_name":
				System.out.println("Sorted by name (asc).");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("Sorted by name (desc).");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("Sorted by date (asc).");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("Sorted by date (desc).");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "comp":
				TodoUtil.completeItem(l, keyword);
				break;
							
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;
				
						
			default:
				System.out.println("please enter one of the above mentioned command. (use 'help' command)");
				break;
			}
		} while (!quit);
		TodoUtil.saveList(l, "todoList.txt");
	}
}
