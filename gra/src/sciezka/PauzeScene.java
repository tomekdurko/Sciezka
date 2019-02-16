package sciezka;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class PauzeScene extends Scene {
    PauzeScene(StackPane stackGame,int Width,int Height ){
        super(stackGame,Width, Height);
        Text tekst = new Text("PAUZA");
        tekst.setFont(Font.font(20));
        tekst.setFill(Color.RED);
        stackGame.getChildren().add(tekst);

    }
}
