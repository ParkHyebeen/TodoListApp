package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<TodoList command manual>");
        System.out.println("1. add - Add a new item");
        System.out.println("2. del - Delete an existing item");
        System.out.println("3. edit - Update an item");
        System.out.println("4. ls - List all items");
        System.out.println("5. ls_name_asc - Sort the list by name in order");
        System.out.println("6. ls_name_desc - Sort the list by name in reverse order");
        System.out.println("7. ls_date - Sort the list by date");
        System.out.println("8. exit - (Or press escape key to exit)");
    }
    
    public static void prompt()
    {
    	System.out.print("\nCommand > ");
    }
}
