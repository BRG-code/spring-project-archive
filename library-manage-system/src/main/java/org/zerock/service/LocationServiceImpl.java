package org.zerock.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.domain.Location;
import org.zerock.mapper.LocationMapper;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationMapper locationMapper;

    @Override
    public List<Location> selectLocation() {
        return locationMapper.selectLocation();
    }
}
