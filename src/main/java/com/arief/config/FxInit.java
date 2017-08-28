package com.arief.config;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Arief on 8/28/2017.
 */
public interface FxInit extends ApplicationContextAware,Initializable {
    Node initNodeForView(String fxml);
    void changeScene(Stage st, Class < ? extends  AbstractFxController > c , String fxml);
    void sceneReloadToMainMenu(Stage st);
}
