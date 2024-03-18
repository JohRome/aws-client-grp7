package com.jromeo;

import com.jromeo.utils.Menu;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display main menu
            printMainMenu();

            // Get user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Process user choice
            switch (choice) {
                case 1:
                    authMenu(scanner);
                    break;
                case 2:
                    adminMenu(scanner);
                    break;
                case 3:
                    studentMenu(scanner);
                    break;
                case 4:
                    courseMenu(scanner);
                    break;
                case 5:
                    userMenu(scanner);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        System.out.println("Exiting application. Goodbye!");
    }

    public static void printMainMenu() {
        System.out.println("********************");
        System.out.println("*     Main Menu    *");
        System.out.println("********************");
        System.out.println("1. Auth Menu");
        System.out.println("2. Admin Menu");
        System.out.println("3. Student Menu");
        System.out.println("4. Course Menu");
        System.out.println("5. User Menu");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void authMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            Menu.printAuthMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Call login method
                    System.out.println("Performing login...");
                    break;
                case 2:
                    // Call register method
                    System.out.println("Performing registration...");
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    public static void adminMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            Menu.printAdminMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Call update user method
                    System.out.println("Performing update user...");
                    break;
                case 2:
                    // Call delete user method
                    System.out.println("Performing delete user...");
                    break;
                case 3:
                    // Call update user to admin method
                    System.out.println("Performing update user to admin...");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    public static void studentMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            Menu.printStudentMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Call update student method
                    System.out.println("Performing update student...");
                    break;
                case 2:
                    // Call delete student method
                    System.out.println("Performing delete student...");
                    break;
                case 3:
                    // Call add course method
                    System.out.println("Performing add course...");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    public static void courseMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            Menu.printCourseMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Call add course method
                    System.out.println("Performing add course...");
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
            }
        }
    }

    public static void userMenu(Scanner scanner) {
        boolean exit = false;

        while (!exit) {
            Menu.printUserMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Call view profile method
                    System.out.println("Performing view profile...");
                    break;
                case 2:
                    // Call update profile method
                    System.out.println("Performing update profile...");
                    break;
                case 3:
                    // Call change password method
                    System.out.println("Performing change password...");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }
}
