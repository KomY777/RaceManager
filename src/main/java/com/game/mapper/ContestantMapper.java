package com.game.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.game.entity.Contestant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContestantMapper extends BaseMapper<Contestant> {
}
