package ui;
import ui.MatrixOp;
import alg.OperationType;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.application.Application;

public class MainMenu extends Application
{
	// local variable
	private Image pI, logo;
	private ImageView logoIV;	
	private Button btSum, btSubt, btMult, btScalar, btQuit;
	
	// entry point
	public static void main(String[] args)
	{
		launch(args);
	}	
	
	// launch call
	public void start(Stage stage) throws Exception
	{
		// scene setting
		Pane pane = new Pane();
		Scene scene = new Scene(pane, 350, 175);
		
		// window icon
		pI = new Image("img/icon.png");
		stage.getIcons().add(pI);
		
		// css
		scene.getStylesheets().add("/ui/stylesheet.css");
		
		// background logo setting
		logo = new Image("img/icon.png");
		logoIV = new ImageView(logo);
		logoIV.setLayoutX(-25);
		logoIV.setLayoutY(10);
		logoIV.setFitHeight(220);
		logoIV.setFitWidth(220);
		
		// background logo adding
		pane.getChildren().add(logoIV);
		
		// buttons definition
		int btWidth = 140, btSpacing = 5;		
		
		btSum = new Button("Soma de matrizes");
		btSum.layoutXProperty().bind(scene.widthProperty().subtract(btSum.widthProperty()).subtract(10));
		btSum.setLayoutY(20);
		btSum.setPrefWidth(btWidth);
		
		btSubt = new Button("Diferença de matrizes");
		btSubt.layoutXProperty().bind(btSum.layoutXProperty());
		btSubt.layoutYProperty().bind(btSum.layoutYProperty().add(btSum.heightProperty()).add(btSpacing));
		btSubt.setPrefWidth(btWidth);
		
		btMult = new Button("Produto de matrizes");
		btMult.layoutXProperty().bind(btSubt.layoutXProperty());
		btMult.layoutYProperty().bind(btSubt.layoutYProperty().add(btSubt.heightProperty()).add(btSpacing));
		btMult.setPrefWidth(btWidth);
		
		btScalar = new Button("Escalar X Matriz");
		btScalar.layoutXProperty().bind(btMult.layoutXProperty());
		btScalar.layoutYProperty().bind(btMult.layoutYProperty().add(btMult.heightProperty()).add(btSpacing));
		btScalar.setPrefWidth(btWidth);
		
		btQuit = new Button("Sair");
		btQuit.layoutXProperty().bind(btScalar.layoutXProperty());
		btQuit.layoutYProperty().bind(btScalar.layoutYProperty().add(btScalar.heightProperty()).add(btSpacing));
		btQuit.setPrefWidth(btWidth);
		
		// buttons adding
		pane.getChildren().addAll(btSum, btSubt, btMult, btScalar, btQuit);
		
		// window frame setting
		stage.setTitle("Menu Principal | MatrixFX");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();		
		
		btSum.setOnAction(getOpEventHandler(OperationType.SUM, stage));		
		btSubt.setOnAction(getOpEventHandler(OperationType.SUBT, stage));		
		btMult.setOnAction(getOpEventHandler(OperationType.MULT, stage));		
		btScalar.setOnAction(getOpEventHandler(OperationType.SCALAR, stage));		
		btQuit.setOnAction(getQuitEventHandler(stage));		
	}
	
	// operation button event handler
	private EventHandler<ActionEvent> getOpEventHandler(OperationType opType, Stage stage) {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MatrixOp window = new MatrixOp(opType);
				
				try	{ window.start(new Stage());	}
				catch (Exception e) { System.out.println("Erro:"+e); }
				finally	{ stage.close(); }
			}
		};
	}
	
	// exclusive quit action
	private EventHandler<ActionEvent> getQuitEventHandler(Stage stage) {
		return new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				stage.close();
			}
		};
	}
}
