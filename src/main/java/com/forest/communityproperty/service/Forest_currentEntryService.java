package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_currentEntry;
import com.forest.communityproperty.mapper.Forest_currentEntryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_currentEntryService {


    @Resource
    Forest_currentEntryMapper forest_currentEntryMapper;

    /**
     * 查询登记的信息
     *
     * @return
     */
    public List<Forest_currentEntry> selectEmployee() {
        return forest_currentEntryMapper.selectEmployee();
    }

    /**
     * 新增日常信息
     *
     * @param forest_currentEntry
     * @return
     */
    public int insertSelectiveS(Forest_currentEntry forest_currentEntry) {
        return forest_currentEntryMapper.insertSelectiveS(forest_currentEntry);
    }
}
