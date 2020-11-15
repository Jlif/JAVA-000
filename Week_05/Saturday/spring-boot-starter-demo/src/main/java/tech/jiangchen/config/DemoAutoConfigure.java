package tech.jiangchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tech.jiangchen.School;

@Configuration
@ComponentScan("tech.jiangchen.entity")
public class DemoAutoConfigure {

    @Bean
    public School getSchool() {
        return new School();
    }
}
