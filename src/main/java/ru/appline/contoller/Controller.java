package ru.appline.contoller;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {

    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(4);

    @PostMapping(value = "/createPet", consumes = "application/json")
    public void createPet(@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getPetList(id.get("id"));
    }

    @DeleteMapping(value = "/deletePet", consumes = "application/json")
    public Map<Integer, Pet> deletePet(@RequestBody Map<String, Integer> id) {
        petModel.getAll().remove(id.get("id"));
        return petModel.getAll();
    }

    @PutMapping(value = "/putPet", consumes = "application/json")
    public Map<Integer, Pet> putPet(@RequestBody Map<String, String> formParams) {
        petModel.getAll().put(Integer.parseInt(formParams.get("id")), new Pet(formParams.get("name"), formParams.get("type"), Integer.parseInt(formParams.get("age"))));
        return petModel.getAll();
    }
}
