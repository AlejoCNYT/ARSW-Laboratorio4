package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BlueprintsServices {

    @Autowired
    private BlueprintsPersistence bpp;

    public void addBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    public Set<Blueprint> getAllBlueprints() {
        return bpp.getAllBlueprints();
    }

    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        return bpp.getBlueprint(author, name);
    }

    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        return bpp.getBlueprintsByAuthor(author);
    }
}
