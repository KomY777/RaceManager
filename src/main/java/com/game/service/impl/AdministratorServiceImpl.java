package com.game.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.game.entity.Administrator;
import com.game.mapper.AdministratorMapper;
import com.game.service.AdministratorService;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

}
