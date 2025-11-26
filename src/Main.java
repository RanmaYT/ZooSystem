import Controller.AnimalController;
import Controller.RelatoController;
import Controller.UIController;
import Databases.AnimalDatabase;
import Databases.RelatoDatabase;
import Model.Services.AnimalService;
import Model.Services.RelatoService;
import Util.InputUtil;
import View.View;

public class Main {
    public static void main(String[] args) {
        // Util
        InputUtil inputUtil = new InputUtil();

        // Databases
        AnimalDatabase animalDatabase = new AnimalDatabase();
        RelatoDatabase relatoDatabase = new RelatoDatabase();

        // Services
        AnimalService animalService = new AnimalService(animalDatabase);
        RelatoService relatoService = new RelatoService(relatoDatabase);

        // Controllers
        AnimalController animalController = new AnimalController(animalService);
        RelatoController relatoController = new RelatoController(relatoService);

        // View
        View view = new View();

        UIController uiController = new UIController(animalController, relatoController, inputUtil, view);

        uiController.menuPrincipal();
    }
}
