package com.game.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.game.entity.Contestant;
import com.game.mapper.ContestantMapper;
import com.game.service.ContestantService;
import org.springframework.stereotype.Service;

@Service
public class ContestantServiceImpl extends ServiceImpl<ContestantMapper, Contestant> implements ContestantService {
}
