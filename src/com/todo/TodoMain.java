package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
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
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("Sorted by name (asc).");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("Sorted by name (desc).");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("Sorted by date.");
				isList = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;
				
			case "find":
				String find=sc.nextLine().trim();
				TodoUtil.find(l, find);
				break;
				
			case "find_cate":
				String findc = sc.nextLine().trim();
				TodoUtil.findCate(l,findc);
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("Sorted by date (desc).");
				isList = true;
				break;
				
			default:
				System.out.println("please enter one of the above mentioned command. (use 'help' command)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todoList.txt");
	}
}
