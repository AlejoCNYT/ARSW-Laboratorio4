/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.filters.RedundancyFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.Arrays;
import java.util.List;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest
{

    private InMemoryBlueprintPersistence ibpp;

    @Before
    public void setUp()
    {
        ibpp = new InMemoryBlueprintPersistence();
    }
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest()
    {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }


    }

    @Test
    public void shouldSaveAndRetrieveBlueprint() throws BlueprintPersistenceException, BlueprintNotFoundException
    {
        // Arrange
        Blueprint bp = new Blueprint("Alice", "Plan1", new Point[]{new Point(10, 20), new Point(30, 40)});
        ibpp.saveBlueprint(bp);

        // Act
        Blueprint retrieved = ibpp.getBlueprint("Alice", "Plan1");

        // Assert
        assertNotNull("Blueprint should be retrieved", retrieved);
        assertEquals("Author should be Alice", "Alice", retrieved.getAuthor());
        assertEquals("Name should be Plan1", "Plan1", retrieved.getName());
    }

    @Test(expected = BlueprintNotFoundException.class)
    public void shouldThrowExceptionWhenBlueprintNotFound() throws BlueprintNotFoundException
    {
        ibpp.getBlueprint("Unknown", "NoPlan"); // No existe, debe lanzar excepción
    }

    @Test
    public void shouldRetrieveBlueprintsByAuthor() throws BlueprintPersistenceException, BlueprintNotFoundException
    {
        // Arrange
        Blueprint bp1 = new Blueprint("Bob", "PlanA", new Point[]{new Point(10, 10)});
        Blueprint bp2 = new Blueprint("Bob", "PlanB", new Point[]{new Point(20, 20)});
        ibpp.saveBlueprint(bp1);
        ibpp.saveBlueprint(bp2);

        // Act
        Set<Blueprint> blueprints = ibpp.getBlueprintsByAuthor("Bob");

        // Assert
        assertEquals(   "Bob should have 2 blueprints", 2, blueprints.size());
    }

    @Test(expected = BlueprintNotFoundException.class)
    public void shouldThrowExceptionWhenAuthorHasNoBlueprints() throws BlueprintNotFoundException
    {
        ibpp.getBlueprintsByAuthor("Charlie"); // No tiene planos, debe lanzar excepción
    }

}
