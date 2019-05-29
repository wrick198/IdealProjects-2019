package com.steven.SpringDemo;

public class Zoo {
    private Animal animal;  //组装

    public Zoo(Animal animal){
        this.animal=animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        if (animal == null) {
            return null;
        }
        return animal.toString();
    }
}

