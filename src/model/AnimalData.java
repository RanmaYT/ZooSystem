package model;

import java.util.ArrayList;

public class AnimalData {
    private static ArrayList<Animal> animaisCadastrado = new ArrayList<>();

    public ArrayList<Animal> getAnimaisCadastrado() {
        return animaisCadastrado;
    }

    public void setAnimaisCadastrado(ArrayList<Animal> animaisCadastrado) {
        this.animaisCadastrado = animaisCadastrado;
    }

    public void addAnimals(Animal animal){
        animaisCadastrado.add(animal);
    }

    public void displayAnimalInfo(int animalIndex) {
        // Pegar o animal naquele índice
        Animal animal = animaisCadastrado.get(animalIndex);

        System.out.println(animal);
    }

    public boolean hasAnimal(){
        return !animaisCadastrado.isEmpty();
    }
}
