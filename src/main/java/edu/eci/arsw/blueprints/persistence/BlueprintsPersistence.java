package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Set;

/**
 * Interface defining the persistence layer for Blueprints.
 */
public interface BlueprintsPersistence {

    /**
     * Saves a new blueprint.
     *
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;

    /**
     * Retrieves a specific blueprint by author and name.
     *
     * @param author blueprint's author
     * @param bprintname blueprint's name
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException;

    /**
     * Retrieves all blueprints available in the system.
     *
     * @return a set of all stored blueprints
     */
    Set<Blueprint> getAllBlueprints();

    /**
     * Retrieves all blueprints by a specific author.
     *
     * @param author the author's name
     * @return a set of blueprints created by the specified author
     * @throws BlueprintNotFoundException if no blueprints are found for the given author
     */
    Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException;

    /**
     * Deletes a specific blueprint by author and name.
     *
     * @param author blueprint's author
     * @param bprintname blueprint's name
     * @throws BlueprintNotFoundException if the blueprint does not exist
     */
    void deleteBlueprint(String author, String bprintname) throws BlueprintNotFoundException;
}
