package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PetModel implements Serializable {

    private static final PetModel instance = new PetModel();
    private final Map<Integer, Pet> model;

    public PetModel() {
        model = new HashMap<Integer, Pet>();

        model.put(1, new Pet("Rex", "Dog", 5));
        model.put(2, new Pet("Tom", "Cat", 6));
        model.put(3, new Pet("Jerry", "Mouse", 4));
    }

    public static PetModel getInstance() {
        return instance;
    }

    public void add(Pet pet, int id) {
        model.put(id, pet);
    }

    public Pet getPetList(int id) {
        return model.get(id);
    }

    public Map<Integer, Pet> getAll() {
        return model;
    }
}
