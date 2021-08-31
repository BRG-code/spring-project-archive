package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Location;

import java.util.List;

@Mapper
public interface LocationMapper {
    List<Location> selectLocation();
}
