package sciezka;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StartScene extends Scene {

    static Button startButton = new Button("Nowa Gra");
    static Button scoreListButton = new Button("Wyniki");
    static Button helpButton = new Button("Pomoc");
    static Button endButton = new Button("Koniec");
    static Button returnButton = new Button("Powrot");


    public StartScene (StackPane stackStart, double height, double width, Stage primaryStage,Scene setting,Scene wyniki) {
        super (stackStart,height,width);

        double tempHeight=stackStart.getHeight();
        stackStart.getChildren().add(startButton);
        stackStart.getChildren().add(scoreListButton);
        stackStart.getChildren().add(helpButton);
        stackStart.getChildren().add(endButton);
        StackPane.setAlignment(startButton, Pos.TOP_CENTER);
        startButton.setTranslateY(tempHeight/12);
        scoreListButton.setTranslateY(-tempHeight/10);
        helpButton.setTranslateY(tempHeight/10);
        endButton.setTranslateY(-tempHeight/12);
        StackPane.setAlignment(endButton, Pos.BOTTOM_CENTER);

        StackPane stackPane = new StackPane();
        Scene sceneHelp = new Scene(stackPane, 500, 600);
        stackPane.getChildren().add(returnButton);
        returnButton.setTranslateY(200);
        Text helpText = new Text("W grze Sciezka zadaniem gracza jest przeprowadzenie postaci do konca scie�ki, zderzenie z przeszkoda konczy sie przegrana gracza. Od wyboru poziomu "
                + "trudnosci oraz czasu spedzonego w grze zalezy liczba punktow uzyskanych przez gracza.");
        helpText.setWrappingWidth(sceneHelp.getWidth());
        helpText.setFont(new Font(20));
        stackPane.getChildren().add(helpText);

        primaryStage.heightProperty().addListener((obs,oldVal,newVAl)->{
            startButton.setTranslateY(this.getHeight()/12);
            scoreListButton.setTranslateY(-this.getHeight()/10);
            helpButton.setTranslateY(this.getHeight()/10);
            endButton.setTranslateY(-this.getHeight()/12);
        });


        EventHandler<ActionEvent> startHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(setting);
            }
        };


        EventHandler<ActionEvent> helpHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(sceneHelp);
            }
        };

        StartScene.scoreListButton.setOnAction(actionEvent -> primaryStage.setScene(wyniki));
        StartScene.startButton.addEventHandler(ActionEvent.ACTION,startHandler);
        StartScene.helpButton.addEventHandler(ActionEvent.ACTION, helpHandler);
        StartScene.endButton.setOnAction(actionEvent -> Platform.exit());
    }
}
