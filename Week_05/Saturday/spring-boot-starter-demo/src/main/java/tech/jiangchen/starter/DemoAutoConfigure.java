package tech.jiangchen.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jiangchen.starter.entity.Student;

@Configuration
@ConditionalOnClass(Student.class)
@EnableConfigurationProperties(DemoStudentProperties.class)
public class DemoAutoConfigure {

    private final DemoStudentProperties properties;

    public DemoAutoConfigure(DemoStudentProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
//    @ConditionalOnProperty(prefix = "demo.starter", value = "enabled", havingValue = "true")
    Student student() {
        return new Student(properties.getId(), properties.getName());
    }

}
