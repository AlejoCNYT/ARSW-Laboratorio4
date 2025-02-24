package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlueprintsApplication {

    public static void main(String[] args)
    {
        // Cargar el contexto de Spring desde applicationContext.xml
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("Contexto cargado correctamente.");

        // Obtener la instancia de BlueprintsServices desde el contexto de Spring
        BlueprintsServices blueprintsService = context.getBean("blueprintsServices", BlueprintsServices.class);

        if (blueprintsService == null)
        {
            System.err.println("Error: No se pudo obtener el bean blueprintsServices.");
            context.close();
            return;
        }

        System.out.println("Bean blueprintsServices cargado correctamente.");

        // Crear y registrar un nuevo blueprint
        Blueprint bp1 = new Blueprint("Antonio", "Plano1", new Point[]{new Point(10, 10), new Point(20, 20)});
        Blueprint bp2 = new Blueprint("Maria", "Casa", new Point[]{new Point(30, 30), new Point(40, 40)});

        try
        {
            blueprintsService.addNewBlueprint(bp1);
            blueprintsService.addNewBlueprint(bp2);
            System.out.println("Planos registrados con éxito.");

            // Consultar un blueprint específico
            Blueprint retrieved1 = blueprintsService.getBlueprint("Antonio", "Plano1");
            System.out.println("Plano recuperado: " + retrieved1.getName() + " de " + retrieved1.getAuthor());

            // Consultar todos los planos de un autor
            System.out.println("Planos de Maria: " + blueprintsService.getBlueprintsByAuthor("Maria"));

        } catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }

        // Cerrar el contexto de Spring
        context.close();
    }


}
