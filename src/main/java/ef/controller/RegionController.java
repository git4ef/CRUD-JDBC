package ef.controller;

import ef.model.Region;
import ef.service.RegionService;

import java.util.List;

public class RegionController {
    private RegionService regionService = new RegionService();

    public Region saveRegion(String name){
        return regionService.saveRegion(new Region(name));
    }

    public void deleteRegion(Long id){
        regionService.deleteRegionByID(id);
    }

    public Region getRegionByID(Long id){
        return regionService.getRegionByID(id);
    }

    public Region updateRegion(Long id,String name){
        return regionService.updateRegion(new Region(id,name));
    }

    public List<Region> getAllRegions(){
        return regionService.getAllRegions();
    }
}
