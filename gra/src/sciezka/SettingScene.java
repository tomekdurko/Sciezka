package sciezka;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class SettingScene extends Scene {

    String nick;
    TextField nickField = new TextField();
    static Button playButton = new Button("Start");
    static Button easyButton = new Button("Latwy");
    static Button mediumButton = new Button("Sredni");
    static Button hardButton = new Button("Trudny");
    Label nickLabel = new Label("Podaj swoj nick");
    Label difficultyLabel = new Label("Na jakim poziomie trudnosci chcesz zagrac?");
    Label difficultyChoise = new Label("");
    double poziomTrudnosci;


    public SettingScene(StackPane stackPlay, double height, double width, Configuration KONFIGURACJA,Stage primaryStage) {
        super (stackPlay,height,width);

        double tempHeight=stackPlay.getHeight();
        stackPlay.getChildren().add(easyButton);
        stackPlay.getChildren().add(mediumButton);
        stackPlay.getChildren().add(hardButton);
        stackPlay.getChildren().add(playButton);
        stackPlay.getChildren().add(nickField);
        stackPlay.getChildren().add(nickLabel);
        stackPlay.getChildren().add(difficultyLabel);
        stackPlay.getChildren().add(difficultyChoise);
        difficultyChoise.setTranslateY(tempHeight/12);
        difficultyLabel.setTranslateY(-tempHeight/12);
        nickLabel.setTranslateY(tempHeight/10);
        nickField.setTranslateY(-tempHeight/3);
        easyButton.setTranslateX(-this.getWidth()/5);
        hardButton.setTranslateX(this.getWidth()/5);
        StackPane.setAlignment(playButton, Pos.BOTTOM_CENTER);
        StackPane.setAlignment(nickLabel, Pos.TOP_CENTER);
        playButton.setTranslateY(-tempHeight/12);


        EventHandler<ActionEvent> easyHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                poziomTrudnosci = ClientMessages.getEasy();
                difficultyChoise.setText("Wybrano niski poziom trudnosci");
                nick=nickField.getText();

            }
        };

        EventHandler<ActionEvent> mediumHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                poziomTrudnosci = ClientMessages.getMedium();
                difficultyChoise.setText("Wybrano sredni poziom trudnosci");
                nick=nickField.getText();

            }
        };

        EventHandler<ActionEvent> hardHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                poziomTrudnosci = ClientMessages.getHard();
                difficultyChoise.setText("Wybrano wysoki poziom trudnosci");
                nick=nickField.getText();

            }
        };


     /*   primaryStage.heightProperty().addListener((obs,oldVal,newVAl)->{
        	difficultyChoise.setTranslateY(this.getHeight()/10);
            difficultyLabel.setTranslateY(-this.getHeight()/10);
            nickLabel.setTranslateY(-this.getHeight()/2);
            nickField.setTranslateY(-this.getHeight()/2.5);
            playButton.setTranslateY(-this.getHeight()/12);

        });*/

        primaryStage.widthProperty().addListener((obs,oldVal,newVAl)->{

            easyButton.setTranslateX(-this.getWidth()/5);
            hardButton.setTranslateX(this.getWidth()/5);


        });

        SettingScene.easyButton.addEventHandler(ActionEvent.ACTION,easyHandler);
        SettingScene.mediumButton.addEventHandler(ActionEvent.ACTION,mediumHandler);
        SettingScene.hardButton.addEventHandler(ActionEvent.ACTION,hardHandler);


    }
    String getNick()
    {
        return nick;
    }

    double getDifficultyLevel() {
        return poziomTrudnosci;
    }

}
