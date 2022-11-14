package com.game.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.game.entity.Game;
import com.game.mapper.GameMapper;
import com.game.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {
}
