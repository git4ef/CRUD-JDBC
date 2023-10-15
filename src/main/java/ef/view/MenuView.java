package ef.view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MenuView {

    public void runMenuView() {
        MenuView menuView = new MenuView();
        RegionView regionView = new RegionView();
        PostView postView = new PostView();
        WriterView writerView = new WriterView();
        Scanner scanner = new Scanner(System.in);

        System.out.println("CRUD Menu:");
        System.out.println("1 - ADD ARTICLE\n" +
                "2 - DELETE\n" +
                "3 - UPDATE\n" +
                "4 - SEARCH\n" +
                "5 - DISPLAY\n" +
                "6 - EXIT");
        System.out.println("Enter your choice: ");

        int number = 0;
        try {
            scanner = new Scanner(System.in);
            number = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("enter only numbers");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            menuView.runMenuView();
        }


        switch (number) {
            case 1:
                writerView.addWriter();
            case 2:
                System.out.println("Select object for delete:");
                System.out.println(
                        "1. Writer\n" +
                                "2. Post\n" +
                                "3. Region\n");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        writerView.deleteWriterById();
                    case 2:
                        postView.deletePostById();
                    case 3:
                        regionView.deleteRegionById();
                }

            case 3:
                System.out.println("Select object for update:");
                System.out.println(
                        "1. Writer\n" +
                                "2. Post\n" +
                                "3. Region\n");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        writerView.updateWriterById();
                    case 2:
                        postView.updatePostById();
                    case 3:
                        regionView.updateRegionById();
                }

            case 4:
                System.out.println("Select object for search:");
                System.out.println(
                        "1. Writer\n" +
                                "2. Post\n" +
                                "3. Region\n");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        writerView.findWriterById();
                    case 2:
                        postView.findPostById();
                    case 3:
                        regionView.findRegionById();
                }

            case 5:
                System.out.println("Select object for display:");
                System.out.println(
                        "1. Writer\n" +
                                "2. Post\n" +
                                "3. Region\n");
                number = scanner.nextInt();
                switch (number) {
                    case 1:
                        writerView.getAllWriters();
                    case 2:
                        postView.getAllPosts();
                    case 3:
                        regionView.getAllRegions();
                }

            case 6:
                System.exit(0);
        }
    }
}

