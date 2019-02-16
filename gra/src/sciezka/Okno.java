package sciezka;

import java.time.Instant;
import java.util.Vector;
import javafx.animation.TranslateTransition;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Okno extends Application {

	Instant startTime = Instant.now();
	Instant pauseStart;
	Instant pauseStop;
	long pauseTime=0;
	String nick;

	public static void main(String[] args)
	{
		launch(args);

	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		ConfigMaker FirstStage = new ConfigMaker();
		Configuration KONFIGURACJA = new Configuration();
		Vector<Integer> Mapa=ClientMessages.loadMap(1);
		Scores scores= new Scores();
		StackPane stackPane = new StackPane();
		Scene scenaGra = new Scene(stackPane, 500, 600);
		Canvas canvas = new Canvas(500, 8000);
		GraphicsContext gc=canvas.getGraphicsContext2D();
		KONFIGURACJA.writeMap(stackPane,  gc, canvas,  Mapa);

		StackPane stackStart = new StackPane();
		StackPane stackScore = new StackPane();
		StackPane stackPlay = new StackPane();
		StackPane stackGame = new StackPane();
		StackPane stackHelp = new StackPane();
		StackPane stackpauze = new StackPane();
		PauzeScene pauzeScene = new PauzeScene(stackpauze, 500, 600);
		SettingScene settingScene =new SettingScene(stackPlay,500,600,KONFIGURACJA,primaryStage);
		BestScores bestScores = new BestScores(KONFIGURACJA.getTopNick(),KONFIGURACJA.getTopPoints());
		ScoreScene scoreScene = new ScoreScene(stackScore,500,600,bestScores);
		StartScene startScene = new StartScene(stackStart,500,600,primaryStage,settingScene,scoreScene);
		GameScene game = new GameScene(stackGame, 500, 600,  canvas, settingScene,  startTime, gc, nick,primaryStage,KONFIGURACJA,scores, pauseTime, primaryStage);
		EventHandler<ActionEvent> playHandler = new EventHandler<ActionEvent>() {

			TranslateTransition transition = new TranslateTransition();
			boolean animacja;

			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(scenaGra);

				startTime = Instant.now();

				//TranslateTransition transition = new TranslateTransition();
				transition.setDuration(Duration.seconds(ClientMessages.getSpeed()*settingScene.getDifficultyLevel()));
				transition.setByX(0);
				transition.setByY(8000);
				transition.setNode(canvas);
				transition.play();
				pauzeScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (event.getCode()==KeyCode.SPACE)
						{
								transition.play();
								primaryStage.setScene(scenaGra);
							pauseStop = Instant.now();
							pauseTime = pauseTime+java.time.Duration.between(pauseStart,pauseStop).toMillis();
							}
					}
				});
				scenaGra.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if (event.getCode()==KeyCode.SPACE)
								transition.stop();
								primaryStage.setScene(pauzeScene);
						pauseStart = Instant.now();

					}
				});
			}

			public void KeyPressed(KeyEvent evt) {
				if (animacja) {
					transition.pause();
				} else {
					transition.play();
				}

			}


		};







		primaryStage.setTitle("Sciezka");
		primaryStage.setScene(startScene);
		primaryStage.show();
		SettingScene.playButton.addEventHandler(ActionEvent.ACTION,playHandler);
		StartScene.returnButton.setOnAction(e->primaryStage.setScene(startScene));


	}


}