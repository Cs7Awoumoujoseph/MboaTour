package com.mbatour.mboa.service;

import com.mbatour.mboa.entite.Region;
import com.mbatour.mboa.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService{
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region saveRegion(Region r) {
        return regionRepository.save(r);
    }

    @Override
    public Region updateRegion(Region r) {
        return regionRepository.save(r);
    }

    @Override
    public void deleteRegion(Region r) {
        regionRepository.delete(r);
    }

    @Override
    public void deleteRegionById(Long id) {
       regionRepository.deleteById(id);
    }

    @Override
    public Region getRegion(Long id) {
        return regionRepository.findById(id).get();
    }

    @Override
    public List<Region> getAllRegion() {
        return regionRepository.findAll();
    }
}
