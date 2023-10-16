package ef.view;

import ef.controller.PostController;
import ef.controller.RegionController;
import ef.controller.WriterController;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WriterView {
    private int number = 0;
    private Scanner scanner = new Scanner(System.in);
    private final MenuView menuView = new MenuView();
    private final WriterController writerController = new WriterController();

    public void findWriterById() {
        System.out.println("enter id for find writer");

        try {
            scanner = new Scanner(System.in);
            number = scanner.nextInt();
            if (writerController.getWriterByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                writerController.deleteWriterByID((long) number);
                System.out.println(writerController.getWriterByID((long) number));
            }
        } catch (InputMismatchException e) {
            System.out.println("enter only numbers");
        } catch (NullPointerException e) {
            System.out.println("writer not found");
        }

        System.out.println();
        System.out.println("please press ENTER to return CRUD menu..");
        try {
            System.in.read();
            menuView.runMenuView();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteWriterById() {
        System.out.println("enter id for delete writer");
        try {
            number = scanner.nextInt();
            if (writerController.getWriterByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                writerController.deleteWriterByID((long) number);
                System.out.println("writer deleted");
            }
        } catch (InputMismatchException e) {
            System.out.println("enter only numbers");
        } catch (NullPointerException e) {
            System.out.println("writer not found");
        }

        System.out.println();
        System.out.println("please press ENTER to return CRUD menu..");
        try {
            System.in.read();
            menuView.runMenuView();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addWriter() {
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        try {
            System.out.println("Enter First Name");
            str = scanner.nextLine();
            System.out.println("Enter Last Name");
            str2 = scanner.nextLine();
            System.out.println("Enter name for Region");
            str3 = scanner.nextLine();
            System.out.println("Enter content for Post");
            str4 = scanner.nextLine();


            writerController.saveWriter(str, str2, str3, str4);
            System.out.println("post from writer saved");
        } catch (InputMismatchException e) {
            System.out.println("enter only string");
        } catch (NullPointerException e) {
            System.out.println("first record");
            writerController.saveWriter(str, str2, str3, str4);
            System.out.println("post saved");
        }

        System.out.println();
        System.out.println("please press ENTER to return CRUD menu..");
        try {
            System.in.read();
            menuView.runMenuView();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateWriterById() {

        System.out.println("Enter id writer");
        try {
            number = scanner.nextInt();
            if (writerController.getWriterByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                System.out.println("Enter First Name");
                String firstname = scanner.next();
                System.out.println("Enter Last Name");
                String lastname = scanner.next();
                writerController.updateWriter((long) number, firstname,lastname);
                System.out.println("update from writer saved");
            }
        } catch (InputMismatchException e) {
            System.out.println("enter only string");
        } catch (NullPointerException e) {
            System.out.println("writer not found");
        }

        System.out.println();
        System.out.println("please press ENTER to return CRUD menu..");
        try {
            System.in.read();
            menuView.runMenuView();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllWriters() {
        System.out.println(writerController.getAllWriters());

        System.out.println();
        System.out.println("please press ENTER to return CRUD menu..");
        try {
            System.in.read();
            menuView.runMenuView();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
