package com.arief.config;

import com.arief.controllers.HomeController;
import com.arief.controllers.divisi.FormSimpanDivisiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * Created by Arief on 8/28/2017.
 */
public abstract class AbstractFxController implements FxInit {

    protected ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void changeScene(Stage st, Class<? extends AbstractFxController> c, String fxml) {
        Parent p = (Parent)(context.getBean(c)).initNodeForView(fxml);
        st.setScene(new Scene(p));
        st.show();
    }

    @Override
    public void sceneReloadToMainMenu(Stage st) {
        Parent p = (Parent)(context.getBean(HomeController.class)).initNodeForView("/scene-home/home.fxml");
        st.setScene(new Scene(p));
        st.show();
    }

    @Override
    public Node initNodeForView(String fxml) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        loader.setController(context.getBean(getClass()));
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
