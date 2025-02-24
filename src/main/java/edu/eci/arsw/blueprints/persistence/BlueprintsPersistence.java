package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public interface BlueprintsPersistence
{
    /**
     *
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     *
     * @param author blueprint's author
     * @param bprintname blueprint's name
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException;

    /**
     * @param author blueprint's author
     * @return a set containing all blueprints of the given author
     * @throws BlueprintNotFoundException if there are no blueprints for the given author
     */
    Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;
}
