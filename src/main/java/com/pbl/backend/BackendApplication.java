package com.pbl.backend;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pbl.backend.constant.ConfigConstants;
import com.zhazhapan.util.FileExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.io.IOException;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BackendApplication {
    public static JSONObject file_manage_settings = null;


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        file_manage_settings = JSON.parseObject(FileExecutor.read(BackendApplication.class.getResourceAsStream(ConfigConstants.FILE_MANAGE_SETTING_PATH)));
        SpringApplication.run(BackendApplication.class, args);
    }

}
