package ef.service;

import ef.model.Region;
import ef.repository.DatabaseRepoImpl.RegionRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RegionServiceTest {
    @InjectMocks
    RegionService regionService;
    @Mock
    RegionRepoImpl regionRepo;
    @Captor
    ArgumentCaptor<Region> requestCaptorRegion = ArgumentCaptor.forClass(Region.class);
    Region region;
    @BeforeEach
    void setup() {
        region = new Region(1L, "name");
    }

    @Test
    public void shouldGetRegionObject_whenSaveRegion() {
        when(regionService.saveRegion(region)).thenReturn(region);
        Region resultRegion = regionService.saveRegion(region);
        verify(regionRepo, times(1)).save(requestCaptorRegion.capture());
        assertEquals(region, resultRegion);
    }

    @Test
    public void shouldGetExistRegionObject_whenFindRegionByID() {
        regionService.saveRegion(region);
        when(regionService.getRegionByID(1L)).thenReturn(region);
        Region expectedRegion = regionService.getRegionByID(1L);
        verify(regionRepo, times(1)).getById(anyLong());
        assertEquals(region, expectedRegion);
    }

    @Test
    public void shouldDoNothing_whenDeleteRegionByID() {
        doNothing().when(regionRepo).deleteById(1L);
        regionService.saveRegion(region);
        regionService.deleteRegionByID(1L);
        assertTrue(regionService.getAllRegions().isEmpty());
        verify(regionRepo,times(1)).deleteById(1L);
    }

    @Test
    public void shouldGetUpdatedRegionObject_whenUpdateRegionByID() {
        when(regionService.updateRegion(any())).thenReturn(region);
        region.setName("update");
        Region expectedRegion = regionService.updateRegion(region);
        verify(regionRepo, times(1)).update(any());
        assertEquals(expectedRegion.getName(), "update");
    }

    @Test
    public void shouldGetAllRegions(){
        regionService.saveRegion(region);
        when(regionService.getAllRegions()).thenReturn(List.of(region));
        List<Region> regionList = regionService.getAllRegions();
        verify(regionRepo,times(1)).getAll();
        assertEquals(regionList.size(),1);
    }
}
