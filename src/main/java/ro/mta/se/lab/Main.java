package ro.mta.se.lab;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * Main class of the program
 */
public class Main extends Application {

    /**
     * App entry point
     * @param args
     */
    public static void main(String[] args)  {
        launch(args);
    }

    /**
     * Function to initialize the view
     * @param primaryStage the primary stage of the app view
     */
    @Override
    public void start(Stage primaryStage) {
        var view = AppView.getView();

        var cdr = new CityDataReader();
        try {
            var resource = getClass().getClassLoader().getResource("city_data.txt").getFile();
            MyUtils.inputData = cdr.read(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        AppView.setScene(primaryStage);

        var cbCountries = view.getCbCountries();
        var cbCities = view.getCbCities();

    }
}
