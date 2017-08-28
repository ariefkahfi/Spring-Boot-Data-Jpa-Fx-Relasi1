package com.arief.main;

import com.arief.config.AbstractFxController;
import com.arief.controllers.HomeController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EntityScan("com.arief.entity")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.arief.config","com.arief.controllers","com.arief.services"})
@EnableJpaRepositories("com.arief.services.repositories")
public class SpringbootdatajpafxApplication  extends Application{

	private ApplicationContext context;

	private static String []a;


	public static void main(String[] args) {
		a = args;
		launch(args);
	}

	//context = SpringApplication.run(SpringbootdatajpafxApplication.class,a);

	@Override
	public void init() throws Exception {
		super.init();
		context = SpringApplication.run(SpringbootdatajpafxApplication.class,a);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		AbstractFxController home =context.getBean(HomeController.class);

		Parent p = (Parent) home.initNodeForView("/scene-home/home.fxml");

		primaryStage.setScene(new Scene(p));
		primaryStage.setTitle("Spring Boot Data Jpa");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
