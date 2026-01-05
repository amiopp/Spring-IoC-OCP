package presentation;

import metier.IMetier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "dao", "metier", "config" })
public class Presentation2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        // Uncomment one of these lines to test a specific profile
        // ctx.getEnvironment().setActiveProfiles("dev"); // Expect 300.0
        // ctx.getEnvironment().setActiveProfiles("prod"); // Expect 200.0
        // ctx.getEnvironment().setActiveProfiles("file"); // Expect 360.0
        ctx.getEnvironment().setActiveProfiles("api"); // Expect 440.0

        ctx.register(Presentation2.class);
        ctx.refresh();

        IMetier metier = ctx.getBean(IMetier.class);
        System.out.println("Result = " + metier.calcul());

        ctx.close();
    }
}
