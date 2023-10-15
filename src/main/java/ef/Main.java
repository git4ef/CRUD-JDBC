package ef;


import ef.controller.RegionController;
import ef.model.Region;
import ef.repository.DatabaseRepoImpl.RegionRepoImpl;
import ef.repository.RegionRepository;
import ef.view.MenuView;

import java.io.IOException;


public class Main {
    public static void main(String[] args)  {

        MenuView menuView = new MenuView();
        menuView.runMenuView();
    }
}

