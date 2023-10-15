package ef.service;

import ef.model.Region;
import ef.repository.DatabaseRepoImpl.RegionRepoImpl;
import ef.repository.RegionRepository;

import java.util.List;

public class RegionService {
    private RegionRepository regionRepository = new RegionRepoImpl();

    public Region getRegionByID(Long id) {
        return regionRepository.getById(id);
    }

    public void deleteRegionByID(Long id) {
        regionRepository.deleteById(id);
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public List<Region> getAllRegions() {
        return regionRepository.getAll();
    }

    public Region updateRegion(Region region) {
        return regionRepository.update(region);
    }
}
