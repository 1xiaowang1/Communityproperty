package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_currentEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface Forest_currentEntryMapper {
    /**
     * 查询登记的信息
     * @return
     */
    List<Forest_currentEntry> selectEmployee();

    /**
     * 新增日常信息
     * @param forest_currentEntry
     * @return
     */
    int insertSelectiveS(Forest_currentEntry forest_currentEntry);
}
