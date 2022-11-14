package com.game;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableSwagger2
@EnableSwaggerBootstrapUI
@MapperScan("com.game.mapper")
public class StudentsManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentsManageApplication.class, args);
        log.info("项目启动......");
    }
}
