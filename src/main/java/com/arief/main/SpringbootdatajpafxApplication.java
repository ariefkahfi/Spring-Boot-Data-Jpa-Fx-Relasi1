package com.arief.main;

import com.arief.config.AbstractFxController;
import com.arief.controllers.HomeController;
import com.arief.entity.Divisi;
import com.arief.entity.Jabatan;
import com.arief.entity.Karyawan;
import com.arief.entity.enums.Gender;
import com.arief.services.repositories.DivisiRepo;
import com.arief.services.repositories.JabatanRepo;
import com.arief.services.repositories.KaryawanRepo;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.awt.event.KeyAdapter;
import java.util.ArrayList;

@SpringBootApplication
@EntityScan("com.arief.entity")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.arief.config","com.arief.controllers","com.arief.services"})
@EnableJpaRepositories("com.arief.services.repositories")
public class SpringbootdatajpafxApplication extends Application{

	private ApplicationContext context;

	private static String []a;


	public static void main(String[] args) {
		a = args;
		//SpringApplication.run(SpringbootdatajpafxApplication.class,args);
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
