package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MatrixResult extends Application{	
	
	private Stage stage;
	private Pane root;
	private Scene scene;
	
	private Label lbTitle;
		
	private TextInputControl
	tFA11, tFA12, tFA13, tFA14, tFA15,
	tFA21, tFA22, tFA23, tFA24, tFA25,
	tFA31, tFA32, tFA33, tFA34, tFA35,
	tFA41, tFA42, tFA43, tFA44, tFA45,
	tFA51, tFA52, tFA53, tFA54, tFA55;
	
	private Button btQuit;
	
	private int tFPrefWidht = 90, tFSpacing = 0; 
	
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		root = new Pane();
		scene = new Scene(root, 460, 240);
		
		scene.getStylesheets().add("/ui/stylesheet.css");
		
		lbTitle = new Label("Resultado: ");
		lbTitle.layoutXProperty().bind((scene.widthProperty().divide(2)).subtract(lbTitle.widthProperty().divide(2)));
		lbTitle.setLayoutY(15);
		lbTitle.setId("title");
		
		btQuit = new Button("Fechar");
		btQuit.setLayoutX(5);
		btQuit.layoutYProperty().bind((scene.heightProperty().subtract(btQuit.heightProperty()).subtract(5)));
		btQuit.prefWidthProperty().bind(scene.widthProperty().subtract(btQuit.layoutXProperty()).subtract(5));
		
		addMatrixA(5,50);
		
		btQuit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				stage.close();
			}
		});
		
		stage.initStyle(StageStyle.UTILITY);
		stage.setTitle("Resultado | MatrixFX");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	public void addMatrixA(int x, int y) {
		
		tFA11 = new TextField();
		tFA11.setLayoutX(x);
		tFA11.setLayoutY(y);
		tFA11.setPrefWidth(tFPrefWidht);
		
		tFA12 = new TextField();
		tFA12.layoutXProperty().bind(tFA11.layoutXProperty().add(tFA11.widthProperty()).add(tFSpacing));
		tFA12.layoutYProperty().bind(tFA11.layoutYProperty());
		tFA12.setPrefWidth(tFPrefWidht);
		
		tFA13 = new TextField();
		tFA13.layoutXProperty().bind(tFA12.layoutXProperty().add(tFA12.widthProperty()).add(tFSpacing));
		tFA13.layoutYProperty().bind(tFA12.layoutYProperty());
		tFA13.setPrefWidth(tFPrefWidht);

		tFA14 = new TextField();
		tFA14.layoutXProperty().bind(tFA13.layoutXProperty().add(tFA13.widthProperty()).add(tFSpacing));
		tFA14.layoutYProperty().bind(tFA13.layoutYProperty());
		tFA14.setPrefWidth(tFPrefWidht);
		
		tFA15 = new TextField();
		tFA15.layoutXProperty().bind(tFA14.layoutXProperty().add(tFA14.widthProperty()).add(tFSpacing));
		tFA15.layoutYProperty().bind(tFA14.layoutYProperty());
		tFA15.setPrefWidth(tFPrefWidht);
		
		tFA21 = new TextField();
		tFA21.layoutXProperty().bind(tFA11.layoutXProperty());
		tFA21.layoutYProperty().bind(tFA11.layoutYProperty().add(tFA11.heightProperty()).add(tFSpacing));
		tFA21.setPrefWidth(tFPrefWidht);
		
		tFA22 = new TextField();
		tFA22.layoutXProperty().bind(tFA21.layoutXProperty().add(tFA21.widthProperty()).add(tFSpacing));
		tFA22.layoutYProperty().bind(tFA21.layoutYProperty());
		tFA22.setPrefWidth(tFPrefWidht);
		
		tFA23 = new TextField();
		tFA23.layoutXProperty().bind(tFA22.layoutXProperty().add(tFA22.widthProperty()).add(tFSpacing));
		tFA23.layoutYProperty().bind(tFA22.layoutYProperty());
		tFA23.setPrefWidth(tFPrefWidht);

		tFA24 = new TextField();
		tFA24.layoutXProperty().bind(tFA23.layoutXProperty().add(tFA23.widthProperty()).add(tFSpacing));
		tFA24.layoutYProperty().bind(tFA23.layoutYProperty());
		tFA24.setPrefWidth(tFPrefWidht);
		
		tFA25 = new TextField();
		tFA25.layoutXProperty().bind(tFA24.layoutXProperty().add(tFA24.widthProperty()).add(tFSpacing));
		tFA25.layoutYProperty().bind(tFA24.layoutYProperty());
		tFA25.setPrefWidth(tFPrefWidht);

		tFA31 = new TextField();
		tFA31.layoutXProperty().bind(tFA21.layoutXProperty());
		tFA31.layoutYProperty().bind(tFA21.layoutYProperty().add(tFA21.heightProperty()).add(tFSpacing));
		tFA31.setPrefWidth(tFPrefWidht);
		
		tFA32 = new TextField();
		tFA32.layoutXProperty().bind(tFA31.layoutXProperty().add(tFA31.widthProperty()).add(tFSpacing));
		tFA32.layoutYProperty().bind(tFA31.layoutYProperty());
		tFA32.setPrefWidth(tFPrefWidht);
		
		tFA33 = new TextField();
		tFA33.layoutXProperty().bind(tFA32.layoutXProperty().add(tFA32.widthProperty()).add(tFSpacing));
		tFA33.layoutYProperty().bind(tFA32.layoutYProperty());
		tFA33.setPrefWidth(tFPrefWidht);

		tFA34 = new TextField();
		tFA34.layoutXProperty().bind(tFA33.layoutXProperty().add(tFA33.widthProperty()).add(tFSpacing));
		tFA34.layoutYProperty().bind(tFA33.layoutYProperty());
		tFA34.setPrefWidth(tFPrefWidht);
		
		tFA35 = new TextField();
		tFA35.layoutXProperty().bind(tFA34.layoutXProperty().add(tFA34.widthProperty()).add(tFSpacing));
		tFA35.layoutYProperty().bind(tFA34.layoutYProperty());
		tFA35.setPrefWidth(tFPrefWidht);

		tFA41 = new TextField();
		tFA41.layoutXProperty().bind(tFA31.layoutXProperty());
		tFA41.layoutYProperty().bind(tFA31.layoutYProperty().add(tFA31.heightProperty()).add(tFSpacing));
		tFA41.setPrefWidth(tFPrefWidht);
		
		tFA42 = new TextField();
		tFA42.layoutXProperty().bind(tFA41.layoutXProperty().add(tFA41.widthProperty()).add(tFSpacing));
		tFA42.layoutYProperty().bind(tFA41.layoutYProperty());
		tFA42.setPrefWidth(tFPrefWidht);
		
		tFA43 = new TextField();
		tFA43.layoutXProperty().bind(tFA42.layoutXProperty().add(tFA42.widthProperty()).add(tFSpacing));
		tFA43.layoutYProperty().bind(tFA42.layoutYProperty());
		tFA43.setPrefWidth(tFPrefWidht);

		tFA44 = new TextField();
		tFA44.layoutXProperty().bind(tFA43.layoutXProperty().add(tFA43.widthProperty()).add(tFSpacing));
		tFA44.layoutYProperty().bind(tFA43.layoutYProperty());
		tFA44.setPrefWidth(tFPrefWidht);
		
		tFA45 = new TextField();
		tFA45.layoutXProperty().bind(tFA44.layoutXProperty().add(tFA44.widthProperty()).add(tFSpacing));
		tFA45.layoutYProperty().bind(tFA44.layoutYProperty());
		tFA45.setPrefWidth(tFPrefWidht);
		
		tFA51 = new TextField();
		tFA51.layoutXProperty().bind(tFA41.layoutXProperty());
		tFA51.layoutYProperty().bind(tFA41.layoutYProperty().add(tFA41.heightProperty()).add(tFSpacing));
		tFA51.setPrefWidth(tFPrefWidht);
		
		tFA52 = new TextField();
		tFA52.layoutXProperty().bind(tFA51.layoutXProperty().add(tFA51.widthProperty()).add(tFSpacing));
		tFA52.layoutYProperty().bind(tFA51.layoutYProperty());
		tFA52.setPrefWidth(tFPrefWidht);
		
		tFA53 = new TextField();
		tFA53.layoutXProperty().bind(tFA52.layoutXProperty().add(tFA52.widthProperty()).add(tFSpacing));
		tFA53.layoutYProperty().bind(tFA52.layoutYProperty());
		tFA53.setPrefWidth(tFPrefWidht);

		tFA54 = new TextField();
		tFA54.layoutXProperty().bind(tFA53.layoutXProperty().add(tFA53.widthProperty()).add(tFSpacing));
		tFA54.layoutYProperty().bind(tFA53.layoutYProperty());
		tFA54.setPrefWidth(tFPrefWidht);
		
		tFA55 = new TextField();
		tFA55.layoutXProperty().bind(tFA54.layoutXProperty().add(tFA54.widthProperty()).add(tFSpacing));
		tFA55.layoutYProperty().bind(tFA54.layoutYProperty());
		tFA55.setPrefWidth(tFPrefWidht);
		
		//editable
		tFA11.setEditable(false);tFA12.setEditable(false);tFA13.setEditable(false);tFA14.setEditable(false);tFA15.setEditable(false);
		tFA21.setEditable(false);tFA22.setEditable(false);tFA23.setEditable(false);tFA24.setEditable(false);tFA25.setEditable(false);
		tFA31.setEditable(false);tFA32.setEditable(false);tFA33.setEditable(false);tFA34.setEditable(false);tFA35.setEditable(false);
		tFA41.setEditable(false);tFA42.setEditable(false);tFA43.setEditable(false);tFA44.setEditable(false);tFA45.setEditable(false);
		tFA51.setEditable(false);tFA52.setEditable(false);tFA53.setEditable(false);tFA54.setEditable(false);tFA55.setEditable(false);
				
		root.getChildren().addAll(	tFA11, tFA12, tFA13, tFA14, tFA15,
									tFA21, tFA22, tFA23, tFA24, tFA25,
									tFA31, tFA32, tFA33, tFA34, tFA35,
									tFA41, tFA42, tFA43, tFA44, tFA45,
									tFA51, tFA52, tFA53, tFA54, tFA55,
									lbTitle,
									btQuit
		);
	}
	
	public void populate(String[][] m) {
		TextInputControl[][] field = new TextInputControl[5][5];
		
		field[0][0]=tFA11; field[0][1]=tFA12; field[0][2]=tFA13; field[0][3]=tFA14; field[0][4]=tFA15;
		field[1][0]=tFA21; field[1][1]=tFA22; field[1][2]=tFA23; field[1][3]=tFA24; field[1][4]=tFA25;
		field[2][0]=tFA31; field[2][1]=tFA32; field[2][2]=tFA33; field[2][3]=tFA34; field[2][4]=tFA35;
		field[3][0]=tFA41; field[3][1]=tFA42; field[3][2]=tFA43; field[3][3]=tFA44; field[3][4]=tFA45;
		field[4][0]=tFA51; field[4][1]=tFA52; field[4][2]=tFA53; field[4][3]=tFA54; field[4][4]=tFA55;
		
		for(int i = 0; i < m.length; i++) {
				for(int j = 0; j < m[0].length; j++) {
					field[i][j].setText(m[i][j]);
				}
		}		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}