import edu.eci.arsw.springdemo.GrammarChecker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public static void main(String[] args)
{
    ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
    GrammarChecker gc=ac.getBean(GrammarChecker.class);
    System.out.println(gc.check("la la la "));
}
