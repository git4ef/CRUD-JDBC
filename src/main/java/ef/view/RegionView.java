package ef.view;

import ef.controller.RegionController;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RegionView {
    private int number = 0;
    private String str = null;
    private final Scanner scanner = new Scanner(System.in);
    private final RegionController regionController = new RegionController();
    private final MenuView menuView = new MenuView();


    public void findRegionById() {
        System.out.println("enter id for find region");

        try {
            number = scanner.nextInt();
            if (regionController.getRegionByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                System.out.println(regionController.getRegionByID((long) number));
            }
        } catch (InputMismatchException e) {
            System.out.println("enter only numbers");
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

    public void deleteRegionById() {
        System.out.println("enter id for delete region");

        try {
            number = scanner.nextInt();
            if (regionController.getRegionByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                regionController.deleteRegion((long) number);
                System.out.println("region deleted");
            }
        } catch (InputMismatchException e) {
            System.out.println("enter only numbers");
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
            ;
        }
    }

    public void addRegion() {
        System.out.println("enter name region for save");

        try {
            str = scanner.nextLine();
            regionController.saveRegion(str);
            System.out.println("region saved");
        } catch (IllegalArgumentException e) {
            System.out.println("region with this name exist");
        } catch (NullPointerException e) {
            System.out.println("first record");
            regionController.saveRegion(str);
            System.out.println("region saved");
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

    public void updateRegionById() {
        String str = null;
        try {
            System.out.println("enter id for update region");
            number = scanner.nextInt();
            if (regionController.getRegionByID((long) number).getId() == null) {
                throw new NullPointerException();
            } else {
                System.out.println("enter name for update region");
                str = scanner.next();
                regionController.updateRegion((long) number, str);
                System.out.println("region updated");
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter only numbers for id and only string for name region");
        } catch (NullPointerException e) {
            System.out.println("Region not found");
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

    public void getAllRegions() {
        System.out.println(regionController.getAllRegions());

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

