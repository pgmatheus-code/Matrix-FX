package ui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.MatrixOp;

public class MainMenu extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	private Button btSum, btSub, btMul, btEsc, btQuit;
	private Image pI, logo;
	private ImageView logoIV;
	
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root, 350, 175);
		
		pI = new Image("img/icon.png");
		stage.getIcons().add(pI);
		scene.getStylesheets().add("/ui/stylesheet.css");
		
		logo = new Image("img/icon.png");
		logoIV = new ImageView(logo);
		logoIV.setLayoutX(-25);
		logoIV.setLayoutY(10);
		logoIV.setFitHeight(220);
		logoIV.setFitWidth(220);
		root.getChildren().add(logoIV);
		
		int btWidth = 140, btSpacing = 5;
		
		btSum = new Button("Soma de matrizes");
		btSum.layoutXProperty().bind(scene.widthProperty().subtract(btSum.widthProperty()).subtract(10));
		btSum.setLayoutY(20);
		btSum.setPrefWidth(btWidth);
		
		btSub = new Button("Diferen�a de matrizes");
		btSub.layoutXProperty().bind(btSum.layoutXProperty());
		btSub.layoutYProperty().bind(btSum.layoutYProperty().add(btSum.heightProperty()).add(btSpacing));
		btSub.setPrefWidth(btWidth);
		
		btMul = new Button("Produto de matrizes");
		btMul.layoutXProperty().bind(btSub.layoutXProperty());
		btMul.layoutYProperty().bind(btSub.layoutYProperty().add(btSub.heightProperty()).add(btSpacing));
		btMul.setPrefWidth(btWidth);
		
		btEsc = new Button("Escalar X Matriz");
		btEsc.layoutXProperty().bind(btMul.layoutXProperty());
		btEsc.layoutYProperty().bind(btMul.layoutYProperty().add(btMul.heightProperty()).add(btSpacing));
		btEsc.setPrefWidth(btWidth);
		
		btQuit = new Button("Sair");
		btQuit.layoutXProperty().bind(btEsc.layoutXProperty());
		btQuit.layoutYProperty().bind(btEsc.layoutYProperty().add(btEsc.heightProperty()).add(btSpacing));
		btQuit.setPrefWidth(btWidth);
		
		root.getChildren().addAll(btSum, btSub, btMul, btEsc, btQuit);
		stage.setTitle("Menu Principal | MatrixFX");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		
		btSum.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MatrixOp window = new MatrixOp(0);
				try {
					window.start(new Stage());
				}catch (Exception e) {
					System.out.println("Erro:"+e);
				}finally {
					stage.close();
				}
			}
		});
		
		btSub.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MatrixOp window = new MatrixOp(1);
				try {
					window.start(new Stage());
				}catch (Exception e) {
					System.out.println("Erro:"+e.getMessage());
				}finally {
					stage.close();
				}
			}
		});
		
		btMul.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MatrixOp window = new MatrixOp(2);
				try {
					window.start(new Stage());
				}catch (Exception e) {
					System.out.println("Erro:"+e.getMessage());
				}finally {
					stage.close();
				}
			}
		});
		
		btEsc.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MatrixOp window = new MatrixOp(3);
				try {
					window.start(new Stage());
				}catch (Exception e) {
					System.out.println("Erro:"+e.getMessage());
				}finally {
					stage.close();
				}
			}
		});
		
		btQuit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MainMenu menu = new MainMenu();
				try {
					stage.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}
}
