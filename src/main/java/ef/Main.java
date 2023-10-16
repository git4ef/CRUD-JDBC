package ef;


import ef.util.LiquibaseUtil;
import ef.view.MenuView;

public class Main {
    public static void main(String[] args) {
        LiquibaseUtil.initDB();
        MenuView menuView = new MenuView();
        menuView.runMenuView();
    }
}

