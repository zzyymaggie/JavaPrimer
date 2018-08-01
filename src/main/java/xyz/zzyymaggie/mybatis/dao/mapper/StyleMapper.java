package xyz.zzyymaggie.mybatis.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import xyz.zzyymaggie.mybatis.model.Style;

public interface StyleMapper {
    public List<Style> batchSelectStyleList(@Param("maxCount")Integer maxCount);
    
    public List<Style> selectStyleList(@Param("maxCount")Integer maxCount);
}
