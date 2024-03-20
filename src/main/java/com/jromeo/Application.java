package com.jromeo;

import com.jromeo.api.AdminApi;
import com.jromeo.api.CourseApi;
import com.jromeo.api.StudentApi;
import com.jromeo.api.UserApi;
import com.jromeo.dto.*;
import com.jromeo.utils.InputScanner;
import com.jromeo.utils.Menu;

import java.io.IOException;
import java.net.URISyntaxException;

public class Application {
    private static InputScanner scanner;
    private static StudentApi studentApi;

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Menu menu = new Menu();
        AdminApi adminApi = new AdminApi();
        CourseApi courseApi = new CourseApi();
        UserApi userApi = new UserApi();
        InputScanner scanner = new InputScanner();

        
        boolean exit = false;

        while (!exit) {
            printMainMenu();

            int choice = scanner.intPut("Enter your choice: ");

            switch (choice) {
                case 1:
                    authMenu(scanner, adminApi);
                    break;
                case 2:

                    adminMenu(scanner, adminApi, userApi);
                    break;
                case 3:
                    studentMenu(scanner, studentApi);
                    break;
                case 4:
                    courseMenu(scanner, courseApi);
                    break;
                case 5:
                    userMenu(scanner, userApi);
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
    }

    public static void authMenu(InputScanner scanner, AdminApi adminApi) throws URISyntaxException, IOException, InterruptedException {
        boolean exit = false;
        while (!exit) {
            printAuthMenu();
            int choice = scanner.intPut("Enter your choice: ");

            switch (choice) {
                case 1:
                    // Login
                    LoginDto login = new LoginDto();
                    login.setEmail(InputScanner.stringPut("Enter your email: "));
                    login.setPassword(InputScanner.stringPut("Enter your password: "));
                    adminApi.adminLogin(login);
                    break;
                case 2:
                    // Register
                    RegisterDto register = new RegisterDto();
                    register.setEmail("Enter your email: ");
                    register.setPassword("Enter your password: ");
                    adminApi.userRegister(register);
                    break;
                case 3:
                    exit = true;
            }
        }
    }


    public static void adminMenu(InputScanner scanner, AdminApi adminApi, UserApi userApi) throws URISyntaxException, IOException, InterruptedException {
        boolean exit = false;

        while (!exit) {
            printAdminMenu();

            int choice = scanner.intPut("Enter your choice: ");

            switch (choice) {
                case 1:
                    // Call update user method
                    System.out.println("Performing update user...");
                    userApi.updateUser(InputScanner.stringPut("Enter your email"), InputScanner.stringPut("Enter New password"));
                    break;
                case 2:
                    // Call delete user method
                    System.out.println("Performing delete user...");
                    LoginDto loginDto = new LoginDto();
                    String email1 = InputScanner.stringPut("Enter your email: ");
                    loginDto.setEmail(email1);
                    loginDto.setPassword(InputScanner.stringPut("Enter your password: "));
                    adminApi.deleteUserByEmail(loginDto, email1);
                    break;
                case 3:
                    // Call update user to admin method
                    System.out.println("Performing update user to admin...");
                    LoginDto login = new LoginDto();
                    EmailDto email = new EmailDto();
                    String savedEmail = InputScanner.stringPut("Enter your email:");

                    email.setEmail(savedEmail);
                    login.setEmail(savedEmail);

                    login.setPassword(InputScanner.stringPut("Enter your password: "));

                    adminApi.updateToAdminRole(login, email);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    public static void studentMenu(InputScanner scanner, StudentApi studentApi) throws IOException, URISyntaxException, InterruptedException {


        boolean exit = false;

        while (!exit) {
            printStudentMenu();

            int choice = scanner.intPut("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println("Performing add student...");
                    StudentDto addStudentDto = new StudentDto();
                    addStudentDto.setName(scanner.stringPut("Enter student name: "));
                    addStudentDto.setAge(scanner.intPut("Enter student age: "));
                    addStudentDto.setDept(scanner.stringPut("Enter student department: "));

                    studentApi.createStudent(addStudentDto);
                    break;
                case 2:
                    System.out.println("Getting one student...");
                    StudentDto student = new StudentDto();
                    student.setId(InputScanner.longPut("Enter Student ID: "));
                    long studentId = scanner.longPut("Enter student ID: ");
                    studentApi.getOneStudent(student);
                    break;
                case 3:
                    System.out.println("Getting all students...");
                    studentApi.getAllStudents();
                    break;
                case 4:
                    System.out.println("Performing update student...");
                    long updateStudentId = scanner.longPut("Enter student ID to update: ");
                    StudentDto updatedStudent = new StudentDto();
                    updatedStudent.setId(updateStudentId);
                    updatedStudent.setName(scanner.stringPut("Enter updated student name: "));
                    updatedStudent.setAge(scanner.intPut("Enter updated student age: "));
                    updatedStudent.setDept(scanner.stringPut("Enter updated student department: "));
                    studentApi.updateStudent(updatedStudent);
                    break;
                case 5:
                    System.out.println("Performing delete student...");
                    long deleteStudentId = scanner.longPut("Enter student ID to delete: ");
                    studentApi.deleteStudent(deleteStudentId);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        System.out.println("Exiting student menu.");
    }


    public static void courseMenu(InputScanner scanner, CourseApi courseApi) throws IOException, URISyntaxException, InterruptedException {


        boolean exit = false;

        while (!exit) {
            printCourseMenu();

            int choice = scanner.intPut("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println("Performing add course...");
                    CourseDto addCourseDto = new CourseDto();
                    addCourseDto.setFee(InputScanner.doublePut("Enter Course Fee"));
                    addCourseDto.setModules(InputScanner.intPut("Enter Course Modules"));
                    addCourseDto.setAbbreviation(InputScanner.stringPut("Enter Course Abbreviation"));
                    courseApi.addCourse(addCourseDto);
                    break;
                case 2:
                    System.out.println("Getting one course...");
                    long courseId = scanner.longPut("Enter Course ID: ");
                    courseApi.getOneCourse(courseId);
                    break;
                case 3:
                    System.out.println("Getting all courses...");
                    courseApi.getAllCourses();
                    break;
                case 4:
                    System.out.println("Performing update course...");
                    long updateCourseId = scanner.longPut("Enter Course ID to update: ");
                    CourseDto updatedCourse = new CourseDto();
                    updatedCourse.setId(updateCourseId);
                    updatedCourse.setFee(InputScanner.doublePut("Enter Updated Course Fee"));
                    updatedCourse.setModules(InputScanner.intPut("Enter Updated Course Modules"));
                    updatedCourse.setAbbreviation(InputScanner.stringPut("Enter Updated Course Abbreviation"));
                    courseApi.updateCourse(updatedCourse);
                    break;
                case 5:
                    System.out.println("Performing delete course...");
                    long deleteCourseId = scanner.longPut("Enter Course ID to delete: ");
                    courseApi.deleteCourse(deleteCourseId);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        System.out.println("Exiting course menu.");
    }

    public static void userMenu(InputScanner scanner, UserApi userApi) throws IOException, URISyntaxException, InterruptedException {
        boolean exit = false;

        while (!exit) {
            printUserMenu();

            int choice = scanner.intPut("Enter your choice: ");

            switch (choice) {
                case 1:
                    System.out.println("Getting all users...");
                    userApi.getAllUsers();
                    break;
                case 2:
                    System.out.println("Promoting user to admin...");
                    String email = scanner.stringPut("Enter user email to promote: ");
                    userApi.promoteUserToAdmin(email);
                    break;
                case 3:
                    System.out.println("Updating user...");
                    String updateUserEmail = scanner.stringPut("Enter user email to update: ");
                    String newPassword = scanner.stringPut("Enter new password: ");
                    userApi.updateUser(updateUserEmail, newPassword);
                    break;
                case 4:
                    System.out.println("Deleting user...");
                    String deleteUserEmail = scanner.stringPut("Enter user email to delete: ");
                    userApi.deleteUser(deleteUserEmail);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }

        System.out.println("Exiting user menu.");
    }

    public static void printAuthMenu() {
        System.out.println("**********************");
        System.out.println("*      Auth Menu     *");
        System.out.println("**********************");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    public static void printAdminMenu() {
        System.out.println("**********************");
        System.out.println("*     Admin Menu     *");
        System.out.println("**********************");
        System.out.println("1. Update User");
        System.out.println("2. Delete User");
        System.out.println("3. Update User to Admin");
        System.out.println("4. Exit");
    }

    public static void printStudentMenu() {
        System.out.println("**********************");
        System.out.println("*    Student Menu    *");
        System.out.println("**********************");
        System.out.println("1. Update Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Add Course");
        System.out.println("4. Exit");
    }

    public static void printCourseMenu() {
        System.out.println("**********************");
        System.out.println("*    Course Menu     *");
        System.out.println("**********************");
        System.out.println("1. Add Course");
        System.out.println("2. Get One Course");
        System.out.println("3. Get All Courses");
        System.out.println("4. Update Course");
        System.out.println("5. Delete Course");
        System.out.println("6. Exit");
    }

    public static void printUserMenu() {
        System.out.println("**********************");
        System.out.println("*     User Menu      *");
        System.out.println("**********************");
        System.out.println("1. Get All Users");
        System.out.println("2. Promote User to Admin");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. Exit");
    }
}
