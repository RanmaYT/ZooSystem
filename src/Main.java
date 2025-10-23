import model.Animal;
import model.AnimalData;
import view.Menu;

public class Main {
    public static void main(String[] args) {
        AnimalData animData = new AnimalData();
        Animal animal1 = new Animal("Saiki", "Cariocatis Perigosatiz", "RJ", "Próximo a boca de fumo");
        Animal animal2 = new Animal("Rony", "Ikno wexeclty", "Sertão", "João alfreedddo");
        animData.addAnimals(animal1);
        animData.addAnimals(animal2);

        Menu menu = new Menu();
        menu.callMenu();
    }
}