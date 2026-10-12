package com.mbatour.mboa.service;


import com.mbatour.mboa.entite.Image;
import com.mbatour.mboa.entite.Region;

import java.io.IOException;
import java.util.List;

public interface RegionService {
    Region saveRegion(Region r);
    Region updateRegion(Region r);
    void deleteRegion(Region r);
    void deleteRegionById(Long id);
    Region getRegion(Long id);
    List<Region> getAllRegion();
}
