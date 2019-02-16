package sciezka;

import java.time.Duration;
import java.time.Instant;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class GameScene extends Scene {



    public GameScene(StackPane stackGame, double Width, double Height, Canvas canvas, SettingScene setting, Instant startTime, GraphicsContext gc, String nick, Stage primaryStage, Configuration KONFIGURACJA, Scores scores, long pause, Stage stage) {
        super(stackGame,Width, Height);

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(gc.isPointInPath(event.getX(), event.getY())) {
                    Instant endTime = Instant.now();
                    StackPane scorePane=new StackPane();
                    BestScores wyniki = new BestScores(KONFIGURACJA.getTopNick(),KONFIGURACJA.getTopPoints());
                    long punkty= java.time.Duration.between(startTime,endTime).toMillis()-pause;
                    punkty = (long) (punkty/setting.getDifficultyLevel());
                    Text tekst1 = new Text("Twoje punkty "+ setting.getNick() + ": " +punkty);

                    wyniki.addNewScore(punkty,setting.getNick());
                    ScoreScene scoreScene = new ScoreScene(scorePane,Width,Height,wyniki);
                    Button scoreButton = new Button("WYNIKI");
                    scoreButton.setOnAction(actionEvent -> stage.setScene(scoreScene));

                    tekst1.setFont(new Font(20));
                    tekst1.setFill(Color.RED);
                    TextFlow punktyFlow = new TextFlow(tekst1);
                    StackPane stackEnd = new StackPane();
                    Button menuButton= new Button("KONIEC");
                    menuButton.setOnAction(actionEvent -> Platform.exit());

                    stackEnd.setAlignment(menuButton, Pos.CENTER);
                    stackEnd.getChildren().add(punktyFlow);
                    stackEnd.getChildren().add(menuButton);
                    stackEnd.getChildren().add(scoreButton);
                    stackEnd.setAlignment(punktyFlow, Pos.CENTER);
                    stackEnd.setAlignment(scoreButton, Pos.CENTER);
                    scoreButton.setTranslateY(-40);
                    Scene gameOver = new Scene(stackEnd,500,600);

                    primaryStage.setScene(gameOver);
                    primaryStage.setTitle("GAME OVER");
                }
                else if(event.getY()<49) {
                    StackPane stackFinish = new StackPane();
                    Scene gameWin = new Scene(stackFinish,500,600);
                    primaryStage.setScene(gameWin);
                    primaryStage.setTitle("WIN");

                    Instant endTime = Instant.now();
                    long punkty= java.time.Duration.between(startTime,endTime).toMillis()-pause;
                    punkty = (long) (punkty/setting.getDifficultyLevel());
                    Text tekst2 = new Text("Twoje punkty "+ setting.getNick() + ": " +punkty);
                    StackPane scorePane=new StackPane();
                    BestScores wyniki = new BestScores(KONFIGURACJA.getTopNick(),KONFIGURACJA.getTopPoints());
                    wyniki.addNewScore(punkty,setting.getNick());
                    ScoreScene scoreScene = new ScoreScene(scorePane,Width,Height,wyniki);
                    Button scoreButton = new Button("WYNIKI");
                    scoreButton.setOnAction(actionEvent -> stage.setScene(scoreScene));

                    tekst2.setFont(new Font(20));
                    tekst2.setFill(Color.RED);
                    TextFlow punktyFlow = new TextFlow(tekst2);
                    Button menuButton= new Button("KONIEC");
                    menuButton.setOnAction(actionEvent -> Platform.exit());
                    stackFinish.setAlignment(menuButton, Pos.CENTER);
                    stackFinish.getChildren().add(punktyFlow);
                    stackFinish.getChildren().add(scoreButton);
                    stackFinish.getChildren().add(menuButton);
                    stackFinish.setAlignment(punktyFlow, Pos.CENTER);
                    stackFinish.setAlignment(scoreButton, Pos.CENTER);
                    scoreButton.setTranslateY(-40);

                }
                else {}
            }

        });
        gc.stroke();
    }

};
