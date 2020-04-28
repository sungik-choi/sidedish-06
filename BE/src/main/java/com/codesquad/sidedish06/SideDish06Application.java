package com.codesquad.sidedish06;

import com.codesquad.sidedish06.utils.DaoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SideDish06Application {
    public static void main(String[] args) {
        SpringApplication.run(SideDish06Application.class, args);
        DaoUtils.setTitles();
    }
}
