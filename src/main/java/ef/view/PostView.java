package ef.view;

import ef.controller.PostController;
import ef.controller.RegionController;
import ef.model.Post;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PostView {
    private int number = 0;
    private final Scanner scanner = new Scanner(System.in);
    private final MenuView menuView = new MenuView();
    private final PostController postController = new PostController();

    public void findPostById() {
        System.out.println("enter id for find post");

        try {
            number = scanner.nextInt();
            if (postController.getPostByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                System.out.println(postController.getPostByID((long) number));
            }
        } catch (InputMismatchException e) {
            System.out.println("enter only numbers");
        } catch (NullPointerException e) {
            System.out.println("post not found");
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

    public void deletePostById() {
        System.out.println("enter id for delete post");

        try {
            number = scanner.nextInt();
            if (postController.getPostByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                postController.deletePostByID((long) number);
                System.out.println("post deleted");
            }
        } catch (InputMismatchException e) {
            System.out.println("enter only numbers");
        } catch (NullPointerException e) {
            System.out.println("post not found");
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

    public void updatePostById() {
        String content = null;
        try {
            System.out.println("enter id for update post");
            number = scanner.nextInt();
            if (postController.getPostByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                System.out.println("enter content for update post");
                content = scanner.next();
                postController.updatePost((long) number, content);
                System.out.println("post updated");
            }
        } catch (InputMismatchException e) {
            System.out.println("enter only numbers for id and only string for content and region");
        } catch (NullPointerException e) {
            System.out.println("region not found");
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

    public void getAllPosts() {
        System.out.println(postController.getAllPosts());

        System.out.println();
        System.out.println("please press ENTER to return CRUD menu..");
        try {
            System.in.read();
            menuView.runMenuView();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void savePost() {
        String content = null;
        try {
            System.out.println("enter content for save post");
            content = scanner.next();
            postController.savePost(content);
            System.out.println("post saved");
        } catch (NullPointerException e) {
            System.out.println("region not found");
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


}
