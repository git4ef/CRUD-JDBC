package ef;

import ef.model.Region;
import ef.repository.DatabaseRepoImpl.RegionRepoImpl;
import ef.repository.RegionRepository;

public class Main {
    public static void main(String[] args) {
        RegionRepository regionRepository = new RegionRepoImpl();
        System.out.println(regionRepository.getAll());
    }
}
