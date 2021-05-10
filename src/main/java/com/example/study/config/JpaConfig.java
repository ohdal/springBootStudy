package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 설정파일 관련 소스가 들어간다는 선언
@EnableJpaAuditing // JPA에 대해서 감시를 활성화 시키겠다 선언
public class JpaConfig {
}
