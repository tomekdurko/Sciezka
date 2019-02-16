package sciezka;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreScene extends Scene {
    public ScoreScene(StackPane stackGame, double width, double height,BestScores bestScores) {
        super(stackGame, width, height);
        Text tekst = new Text(bestScores.showScore());
        tekst.setFont(Font.font(20));
        tekst.setFill(Color.RED);
        stackGame.getChildren().add(tekst);
        Button koniec = new Button("KONIEC");
        stackGame.getChildren().add(koniec);
        stackGame.setAlignment(koniec,Pos.BOTTOM_CENTER);
        koniec.setTranslateY(-50);
        koniec.setOnAction(actionEvent -> Platform.exit());

    }
}
