package SpringApplication;

import SpringApplication.Controllers2.SpringFXMLLoader;
import javafx.stage.Screen;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import static javafx.application.Application.launch;


@SpringBootApplication
public class SpringAppApplication extends Application {


    private static ConfigurableApplicationContext context;
    private FXMLLoader loader;
    private Parent root;


    @Override
    public void init() throws Exception {
        context = SpringApplication.run(SpringAppApplication.class);
        root = context.getBean(SpringFXMLLoader.class).load("/AdminService.fxml");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Cinema");
        primaryStage.setScene(new Scene(root, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

    public static ConfigurableApplicationContext getContext() {
        return context;
    }

    @Override
    public void stop() {
        SpringAppApplication.getContext().close();
    }


}

