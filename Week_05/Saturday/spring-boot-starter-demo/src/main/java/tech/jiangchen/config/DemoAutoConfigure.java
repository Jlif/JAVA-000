package tech.jiangchen.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jiangchen.entity.Student;

import javax.annotation.Resource;

@Configuration
@ConditionalOnClass(Student.class)
@EnableConfigurationProperties(DemoProperties.class)
public class DemoAutoConfigure {

    @Resource
    private DemoProperties demoProperties;

    @Bean
    @ConditionalOnMissingBean(Student.class)
    @ConditionalOnProperty(prefix = "demo", value = "enabled", havingValue = "true")
    public Student student() {
        return new Student(demoProperties.getId(), demoProperties.getName());
    }

}
