package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * In-memory implementation of the BlueprintsPersistence interface.
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<String, Set<Blueprint>> blueprints = new HashMap<>();

    @Override
    public void saveBlueprint(Blueprint blueprint) throws BlueprintPersistenceException {
        blueprints.computeIfAbsent(blueprint.getAuthor(), k -> new HashSet<>());

        boolean exists = blueprints.get(blueprint.getAuthor()).stream()
                .anyMatch(bp -> bp.getName().equals(blueprint.getName()));

        if (exists) {
            throw new BlueprintPersistenceException("Blueprint with name '" + blueprint.getName() + "' already exists.");
        }

        blueprints.get(blueprint.getAuthor()).add(blueprint);
    }

    @Override
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        return blueprints.getOrDefault(author, Collections.emptySet())
                .stream()
                .filter(bp -> bp.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new BlueprintNotFoundException("Blueprint '" + name + "' not found for author '" + author + "'."));
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> allBlueprints = new HashSet<>();
        blueprints.values().forEach(allBlueprints::addAll);
        return allBlueprints;
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        if (!blueprints.containsKey(author)) {
            throw new BlueprintNotFoundException("No blueprints found for author: " + author);
        }
        return blueprints.get(author);
    }

    @Override
    public void deleteBlueprint(String author, String name) throws BlueprintNotFoundException {
        if (!blueprints.containsKey(author)) {
            throw new BlueprintNotFoundException("Author '" + author + "' does not have any blueprints.");
        }

        Set<Blueprint> authorBlueprints = blueprints.get(author);
        boolean removed = authorBlueprints.removeIf(bp -> bp.getName().equals(name));

        if (!removed) {
            throw new BlueprintNotFoundException("Blueprint '" + name + "' not found.");
        }

        if (authorBlueprints.isEmpty()) {
            blueprints.remove(author);
        }
    }

}
