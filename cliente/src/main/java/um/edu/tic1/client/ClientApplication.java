package um.edu.tic1.client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import um.edu.tic1.client.models.ClienteFinal;

import static org.springframework.boot.SpringApplication.*;


@SpringBootApplication
public class ClientApplication extends Application {


	private static ConfigurableApplicationContext context;
	private FXMLLoader fxmlLoader;
	private static Parent root;
	static Stage primaryStage;



	public static void setRoot(Parent root) {

		ClientApplication.root = root;
	}

	public static Stage getStage() {

		return primaryStage;
	}

	public static Parent getRoot() {
		return root;
	}

	@Override
	public void init() {
		context = SpringApplication.run(ClientApplication.class);
		System.out.println(context.getBeanDefinitionCount());

//
//		for (String bean : context.getBeanDefinitionNames()) {
//			String name = context.getBean(bean).getClass().toString();
//			if(name.contains("ovie")){
//				System.out.println(name);
//			}
//		fxmlLoader = new FXMLLoader();
//		fxmlLoader.setControllerFactory(context::getBean);
//		}

	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(context::getBean);
		root = fxmlLoader.load(getClass().getResourceAsStream("/templates/login.fxml"));
		root.getStylesheets().add("/templates/styles.css");
		primaryStage.setTitle("MovieFast");
		primaryStage.setScene( new Scene(root,1000,500));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static ConfigurableApplicationContext getContext() { return context; }


	@Override
	public void stop() { ClientApplication.getContext().close();
	}


}
