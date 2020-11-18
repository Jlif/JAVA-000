package tech.jiangchen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author jiangchen
 * @date 2020/11/18
 */
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoProperties{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
