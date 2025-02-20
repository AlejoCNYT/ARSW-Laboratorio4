package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/blueprints")
public class BlueprintAPIController {

    @Autowired
    private BlueprintsServices services;

    @PostMapping
    public void addBlueprint(@RequestBody Blueprint blueprint) throws BlueprintPersistenceException {
        services.addBlueprint(blueprint);
    }

    @GetMapping("/{author}/{name}")
    public Blueprint getBlueprint(@PathVariable String author, @PathVariable String name) throws BlueprintNotFoundException {
        return services.getBlueprint(author, name);
    }

    @GetMapping("/author/{author}")
    public Set<Blueprint> getBlueprintsByAuthor(@PathVariable String author) throws BlueprintNotFoundException {
        return services.getBlueprintsByAuthor(author);
    }
}
