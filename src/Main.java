import Controller.AnimalController;
import Controller.UIController;
import Databases.AnimalDatabase;
import Model.Services.AnimalService;
import Util.InputUtil;
import View.View;

public class Main {
    public static void main(String[] args) {
        // Util
        InputUtil inputUtil = new InputUtil();

        // Services
        AnimalService animalService = new AnimalService();

        // Controllers
        AnimalController animalController = new AnimalController(animalService);

        // View
        View view = new View();

        UIController uiController = new UIController(animalController, inputUtil, view);

        // Inicia a aplicação
        AnimalDatabase animalDatabase = new AnimalDatabase();

        // uiController.menuPrincipal();
    }
}
