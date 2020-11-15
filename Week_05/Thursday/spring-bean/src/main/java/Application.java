import annotation.AppConfig;
import annotation.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xml.Student;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student s = (Student) context1.getBean("student");
        System.out.println(s);

        ApplicationContext context2 = new AnnotationConfigApplicationContext(AppConfig.class);
        Teacher t = context2.getBean(Teacher.class);
        System.out.println(t);
    }
}
