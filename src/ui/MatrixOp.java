package ui;

import alg.Matrix;
import alg.OperationType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MatrixOp extends Application {
	
	private OperationType opType;
	
	private Stage stage;
	private Pane root;
	private Scene scene;	
	
	private int aI, aJ, bI, bJ, digitLimit = 6, tFPrefWidht = 90, tFSpacing = 0;

	private Label lbTitle;
	private TextInputControl tFScalar, tFA11, tFA12, tFA13, tFA14, tFA15, tFA21, tFA22, tFA23, tFA24, tFA25, tFA31,
			tFA32, tFA33, tFA34, tFA35, tFA41, tFA42, tFA43, tFA44, tFA45, tFA51, tFA52, tFA53, tFA54, tFA55, tFB11,
			tFB12, tFB13, tFB14, tFB15, tFB21, tFB22, tFB23, tFB24, tFB25, tFB31, tFB32, tFB33, tFB34, tFB35, tFB41,
			tFB42, tFB43, tFB44, tFB45, tFB51, tFB52, tFB53, tFB54, tFB55;

	private CheckBox chBxAI1, chBxAI2, chBxAI3, chBxAI4, chBxAI5, chBxAJ1, chBxAJ2, chBxAJ3, chBxAJ4, chBxAJ5, chBxBI1,
			chBxBI2, chBxBI3, chBxBI4, chBxBI5, chBxBJ1, chBxBJ2, chBxBJ3, chBxBJ4, chBxBJ5;

	private Button btCalc, btQuit;
	private Image pI;

	// empty contructor
	public MatrixOp() {} 
	
	// enum param constructor 
	public MatrixOp(OperationType opType) 
	{
		this.opType = opType;
	}
	
	// entry point
	public static void main(String[] args) {
		launch(args);
	}
	
	// launch call
	public void start(Stage stage) throws Exception {
		// scene setting
		this.stage = stage;
		root = new Pane();
		scene = new Scene(root, 1024, 340);

		// icone do programa
		pI = new Image("img/icon.png");
		stage.getIcons().add(pI);

		// CSS
		scene.getStylesheets().add("/ui/stylesheet.css");

		// title label
		lbTitle = new Label("Teste");
		lbTitle.setId("title");
		lbTitle.setLayoutY(20);
		lbTitle.layoutXProperty().bind((scene.widthProperty().divide(2)).subtract(lbTitle.widthProperty().divide(2)));

		// back button
		btQuit = new Button("Menu Principal");
		btQuit.setLayoutX(5);
		btQuit.layoutYProperty().bind((scene.heightProperty().subtract(btQuit.heightProperty()).subtract(5)));
		btQuit.prefWidthProperty().bind(scene.widthProperty().subtract(btQuit.layoutXProperty()).subtract(5));

		// calc button
		btCalc = new Button("Calcular");
		btCalc.setLayoutX(5);
		btCalc.layoutYProperty().bind((btQuit.layoutYProperty().subtract(btCalc.heightProperty()).subtract(5)));
		btCalc.prefWidthProperty().bind(scene.widthProperty().subtract(btCalc.layoutXProperty()).subtract(5));
		btCalc.setPrefHeight(50);

		// add all elements to the root
		root.getChildren().addAll(lbTitle, btQuit, btCalc);
		
		// operation deviation
		switch (opType)
		{
			case SUM: 
				stage.setTitle("SOMA | MatrixFX");
				lbTitle.setText("IIIVX|      Soma de matrizes      |XVIII");
				addMatrixA(50, 90);
				addMatrixB(560, 90);
				break;
	
			case SUBT:
				stage.setTitle("DIFERENÇA | MatrixFX");
				lbTitle.setText("IIIVX|      Diferença de matrizes      |XVIII");
				addMatrixA(50, 90);
				addMatrixB(560, 90);
				break;
	
			case MULT:
				stage.setTitle("PRODUTO | MatrixFX");
				lbTitle.setText("IIIVX|      Produto de matrizes      |XVIII");
				addMatrixA(50, 90);
				addMatrixB(560, 90);
				break;
	
			case SCALAR:
				stage.setTitle("PRODUTO POR ESCALAR | MatrixFX");
				lbTitle.setText("IIIVX|      Produto de um escalar por uma matriz      |XVIII");
				addScalar(100, 120);
				addMatrixA(560, 90);
				break;
		}

		// window frame setting
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

		// start disabling check boxes
		chBxAI1.setSelected(true);
		chBxAJ1.setSelected(true);
		chBxAI1.setDisable(true);
		chBxAJ1.setDisable(true);
		
		if (opType != OperationType.SCALAR) {
			chBxBI1.setSelected(true);
			chBxBJ1.setSelected(true);

			chBxBI1.setDisable(true);
			chBxBI2.setDisable(true);
			chBxBI3.setDisable(true);
			chBxBI4.setDisable(true);
			chBxBI5.setDisable(true);

			if (opType != OperationType.MULT) {
				chBxBJ1.setDisable(true);
				chBxBJ2.setDisable(true);
				chBxBJ3.setDisable(true);
				chBxBJ4.setDisable(true);
				chBxBJ5.setDisable(true);
			}
		}

		// check box disabling based on neighbors
		updateCheckBoxes();

		// a��es disparadas entre as caixas de checagem
		chBxAI5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (chBxAI5.isSelected()) {
					chBxAI4.setSelected(true);
					chBxAI3.setSelected(true);
					chBxAI2.setSelected(true);
					chBxAI1.setSelected(true);
				} else {
				}
				if (opType == OperationType.SUM || opType == OperationType.SUBT) {
					chBxBI5.setDisable(false);
					chBxBI5.fire();
					chBxBI5.setDisable(true);
				}
				updateCheckBoxes();
			}
		});
		chBxAI4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (chBxAI4.isSelected()) {
					chBxAI3.setSelected(true);
					chBxAI2.setSelected(true);
					chBxAI1.setSelected(true);
				} else {
					chBxAI5.setSelected(false);
				}
				if (opType == OperationType.SUM || opType == OperationType.SUBT) {
					chBxBI4.setDisable(false);
					chBxBI4.fire();
					chBxBI4.setDisable(true);
				}
				updateCheckBoxes();
			}
		});
		chBxAI3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (chBxAI3.isSelected()) {
					chBxAI2.setSelected(true);
					chBxAI1.setSelected(true);
				} else {
					chBxAI5.setSelected(false);
					chBxAI4.setSelected(false);
				}
				if (opType == OperationType.SUM || opType == OperationType.SUBT) {
					chBxBI3.setDisable(false);
					chBxBI3.fire();
					chBxBI3.setDisable(true);
				}
				updateCheckBoxes();
			}
		});
		chBxAI2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (chBxAI2.isSelected()) {
					chBxAI1.setSelected(true);
				} else {
					chBxAI5.setSelected(false);
					chBxAI4.setSelected(false);
					chBxAI3.setSelected(false);
				}
				if (opType == OperationType.SUM || opType == OperationType.SUBT) {
					chBxBI2.setDisable(false);
					chBxBI2.fire();
					chBxBI2.setDisable(true);
				}
				updateCheckBoxes();
			}
		});

		chBxAJ5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (chBxAJ5.isSelected()) {
					chBxAJ4.setSelected(true);
					chBxAJ3.setSelected(true);
					chBxAJ2.setSelected(true);
					chBxAJ1.setSelected(true);
				} else {
				}
				if (opType == OperationType.SUM || opType == OperationType.SUBT) {
					chBxBJ5.setDisable(false);
					chBxBJ5.fire();
					chBxBJ5.setDisable(true);
				}
				if (opType == OperationType.MULT) {
					chBxBI5.setDisable(false);
					chBxBI5.fire();
					chBxBI5.setDisable(true);
				}
				updateCheckBoxes();
			}
		});
		chBxAJ4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (chBxAJ4.isSelected()) {
					chBxAJ3.setSelected(true);
					chBxAJ2.setSelected(true);
					chBxAJ1.setSelected(true);
				} else {
					chBxAJ5.setSelected(false);
				}
				if (opType == OperationType.SUM || opType == OperationType.SUBT) {
					chBxBJ4.setDisable(false);
					chBxBJ4.fire();
					chBxBJ4.setDisable(true);
				}
				if (opType == OperationType.MULT) {
					chBxBI4.setDisable(false);
					chBxBI4.fire();
					chBxBI4.setDisable(true);
				}
				updateCheckBoxes();
			}
		});
		chBxAJ3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (chBxAJ3.isSelected()) {
					chBxAJ2.setSelected(true);
					chBxAJ1.setSelected(true);
				} else {
					chBxAJ5.setSelected(false);
					chBxAJ4.setSelected(false);
				}
				if (opType == OperationType.SUM || opType == OperationType.SUBT) {
					chBxBJ3.setDisable(false);
					chBxBJ3.fire();
					chBxBJ3.setDisable(true);
				}
				if (opType == OperationType.MULT) {
					chBxBI3.setDisable(false);
					chBxBI3.fire();
					chBxBI3.setDisable(true);
				}
				updateCheckBoxes();
			}
		});
		chBxAJ2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (chBxAJ2.isSelected()) {
					chBxAJ1.setSelected(true);
				} else {
					chBxAJ5.setSelected(false);
					chBxAJ4.setSelected(false);
					chBxAJ3.setSelected(false);
				}
				if (opType == OperationType.SUM || opType == OperationType.SUBT) {
					chBxBJ2.setDisable(false);
					chBxBJ2.fire();
					chBxBJ2.setDisable(true);
				}
				if (opType == OperationType.MULT) {
					chBxBI2.setDisable(false);
					chBxBI2.fire();
					chBxBI2.setDisable(true);
				}
				updateCheckBoxes();
			}
		});

		if (opType != OperationType.SCALAR) {
			chBxBI5.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (chBxBI5.isSelected()) {
						chBxBI4.setSelected(true);
						chBxBI3.setSelected(true);
						chBxBI2.setSelected(true);
						chBxBI1.setSelected(true);
					} else {
					}
					updateCheckBoxes();
				}
			});
			chBxBI4.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (chBxBI4.isSelected()) {
						chBxBI3.setSelected(true);
						chBxBI2.setSelected(true);
						chBxBI1.setSelected(true);
					} else {
						chBxBI5.setSelected(false);
					}
					updateCheckBoxes();
				}
			});
			chBxBI3.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (chBxBI3.isSelected()) {
						chBxBI2.setSelected(true);
						chBxBI1.setSelected(true);
					} else {
						chBxBI5.setSelected(false);
						chBxBI4.setSelected(false);
					}
					updateCheckBoxes();
				}
			});
			chBxBI2.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (chBxBI2.isSelected()) {
						chBxBI1.setSelected(true);
					} else {
						chBxBI5.setSelected(false);
						chBxBI4.setSelected(false);
						chBxBI3.setSelected(false);
					}
					updateCheckBoxes();
				}
			});
			chBxBJ5.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (chBxBJ5.isSelected()) {
						chBxBJ4.setSelected(true);
						chBxBJ3.setSelected(true);
						chBxBJ2.setSelected(true);
						chBxBJ1.setSelected(true);
					} else {
					}
					updateCheckBoxes();
				}
			});
			chBxBJ4.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (chBxBJ4.isSelected()) {
						chBxBJ3.setSelected(true);
						chBxBJ2.setSelected(true);
						chBxBJ1.setSelected(true);
					} else {
						chBxBJ5.setSelected(false);
					}
					updateCheckBoxes();
				}
			});
			chBxBJ3.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (chBxBJ3.isSelected()) {
						chBxBJ2.setSelected(true);
						chBxBJ1.setSelected(true);
					} else {
						chBxBJ5.setSelected(false);
						chBxBJ4.setSelected(false);
					}
					updateCheckBoxes();
				}
			});
			chBxBJ2.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if (chBxBJ2.isSelected()) {
						chBxBJ1.setSelected(true);
					} else {
						chBxBJ5.setSelected(false);
						chBxBJ4.setSelected(false);
						chBxBJ3.setSelected(false);
					}
					updateCheckBoxes();
				}
			});
		}

		// bot�o para voltar para o menu principal
		btQuit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MainMenu menu = new MainMenu();
				try {
					menu.start(new Stage());
					stage.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});

		btCalc.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				calculate();
			}
		});
	}

	// m�todo que executa tudo o que precisa para calcular e projeta janela de
	// resultados, podendo projetar janela de erro
	private void calculate() {
		checkDim();
		if (anyFieldEmpty()) {
			CustomAlert alert = new CustomAlert(AlertType.ERROR, "Erro", "Campo vazio",
					"Verifique com cuidado se todos os campos foram preenchidos corretamente. \n ");
			alert.getAlert().showAndWait();
		} else {
			MatrixResult resultScreen = new MatrixResult();

			try {
				resultScreen.start(new Stage());
				int[][] c;

				switch (opType) {
				case SUM:
					c = Matrix.sum(Matrix.toDecimal(readA()), Matrix.toDecimal(readB()));
					resultScreen.populate(Matrix.toStr(c));
					break;
				case SUBT:
					c = Matrix.subtract(Matrix.toDecimal(readA()), Matrix.toDecimal(readB()));
					resultScreen.populate(Matrix.toStr(c));
					break;
				case MULT:
					c = Matrix.multiply(Matrix.toDecimal(readA()), Matrix.toDecimal(readB()));
					resultScreen.populate(Matrix.toStr(c));
					break;
				case SCALAR:
					c = Matrix.multplyByScalar(Matrix.toDecimal(readA()), Matrix.elementToDec(tFScalar.getText()));
					resultScreen.populate(Matrix.toStr(c));
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private String[][] readA() {
		String[][] a;
		TextInputControl[][] aux = new TextInputControl[5][5];

		aux[0][0] = tFA11;
		aux[0][1] = tFA12;
		aux[0][2] = tFA13;
		aux[0][3] = tFA14;
		aux[0][4] = tFA15;

		aux[1][0] = tFA21;
		aux[1][1] = tFA22;
		aux[1][2] = tFA23;
		aux[1][3] = tFA24;
		aux[1][4] = tFA25;

		aux[2][0] = tFA31;
		aux[2][1] = tFA32;
		aux[2][2] = tFA33;
		aux[2][3] = tFA34;
		aux[2][4] = tFA35;

		aux[3][0] = tFA41;
		aux[3][1] = tFA42;
		aux[3][2] = tFA43;
		aux[3][3] = tFA44;
		aux[3][4] = tFA45;

		aux[4][0] = tFA51;
		aux[4][1] = tFA52;
		aux[4][2] = tFA53;
		aux[4][3] = tFA54;
		aux[4][4] = tFA55;

		a = new String[aI][aJ];

		for (int i = 0; i < aI; i++) {
			for (int j = 0; j < aJ; j++) {
				a[i][j] = aux[i][j].getText();
			}
		}

		return a;
	}

	private String[][] readB() {
		String[][] b;
		TextInputControl[][] aux = new TextInputControl[5][5];

		aux[0][0] = tFB11;
		aux[0][1] = tFB12;
		aux[0][2] = tFB13;
		aux[0][3] = tFB14;
		aux[0][4] = tFB15;

		aux[1][0] = tFB21;
		aux[1][1] = tFB22;
		aux[1][2] = tFB23;
		aux[1][3] = tFB24;
		aux[1][4] = tFB25;

		aux[2][0] = tFB31;
		aux[2][1] = tFB32;
		aux[2][2] = tFB33;
		aux[2][3] = tFB34;
		aux[2][4] = tFB35;

		aux[3][0] = tFB41;
		aux[3][1] = tFB42;
		aux[3][2] = tFB43;
		aux[3][3] = tFB44;
		aux[3][4] = tFB45;

		aux[4][0] = tFB51;
		aux[4][1] = tFB52;
		aux[4][2] = tFB53;
		aux[4][3] = tFB54;
		aux[4][4] = tFB55;

		b = new String[bI][bJ];

		for (int i = 0; i < bI; i++) {
			for (int j = 0; j < bJ; j++) {
				b[i][j] = aux[i][j].getText();
			}
		}

		return b;
	}

	// m�todo que verifica se h� algum campo vazio
	private boolean anyFieldEmpty() {
		boolean empty = false;

		if ((!tFA11.isDisable() && tFA11.getText().isEmpty()) || (!tFA12.isDisable() && tFA12.getText().isEmpty())
				|| (!tFA13.isDisable() && tFA13.getText().isEmpty())
				|| (!tFA14.isDisable() && tFA14.getText().isEmpty())
				|| (!tFA15.isDisable() && tFA15.getText().isEmpty())
				|| (!tFA21.isDisable() && tFA21.getText().isEmpty())
				|| (!tFA22.isDisable() && tFA22.getText().isEmpty())
				|| (!tFA23.isDisable() && tFA23.getText().isEmpty())
				|| (!tFA24.isDisable() && tFA24.getText().isEmpty())
				|| (!tFA25.isDisable() && tFA25.getText().isEmpty())
				|| (!tFA31.isDisable() && tFA31.getText().isEmpty())
				|| (!tFA32.isDisable() && tFA32.getText().isEmpty())
				|| (!tFA33.isDisable() && tFA33.getText().isEmpty())
				|| (!tFA34.isDisable() && tFA34.getText().isEmpty())
				|| (!tFA35.isDisable() && tFA35.getText().isEmpty())
				|| (!tFA41.isDisable() && tFA41.getText().isEmpty())
				|| (!tFA42.isDisable() && tFA42.getText().isEmpty())
				|| (!tFA43.isDisable() && tFA43.getText().isEmpty())
				|| (!tFA44.isDisable() && tFA44.getText().isEmpty())
				|| (!tFA45.isDisable() && tFA45.getText().isEmpty())
				|| (!tFA51.isDisable() && tFA51.getText().isEmpty())
				|| (!tFA52.isDisable() && tFA52.getText().isEmpty())
				|| (!tFA53.isDisable() && tFA53.getText().isEmpty())
				|| (!tFA54.isDisable() && tFA54.getText().isEmpty())
				|| (!tFA55.isDisable() && tFA55.getText().isEmpty())) {
			empty = true;
		}

		if (opType != OperationType.SCALAR) {
			if ((!tFB11.isDisable() && tFB11.getText().isEmpty()) || (!tFB12.isDisable() && tFB12.getText().isEmpty())
					|| (!tFB13.isDisable() && tFB13.getText().isEmpty())
					|| (!tFB14.isDisable() && tFB14.getText().isEmpty())
					|| (!tFB15.isDisable() && tFB15.getText().isEmpty())
					|| (!tFB21.isDisable() && tFB21.getText().isEmpty())
					|| (!tFB22.isDisable() && tFB22.getText().isEmpty())
					|| (!tFB23.isDisable() && tFB23.getText().isEmpty())
					|| (!tFB24.isDisable() && tFB24.getText().isEmpty())
					|| (!tFB25.isDisable() && tFB25.getText().isEmpty())
					|| (!tFB31.isDisable() && tFB31.getText().isEmpty())
					|| (!tFB32.isDisable() && tFB32.getText().isEmpty())
					|| (!tFB33.isDisable() && tFB33.getText().isEmpty())
					|| (!tFB34.isDisable() && tFB34.getText().isEmpty())
					|| (!tFB35.isDisable() && tFB35.getText().isEmpty())
					|| (!tFB41.isDisable() && tFB41.getText().isEmpty())
					|| (!tFB42.isDisable() && tFB42.getText().isEmpty())
					|| (!tFB43.isDisable() && tFB43.getText().isEmpty())
					|| (!tFB44.isDisable() && tFB44.getText().isEmpty())
					|| (!tFB45.isDisable() && tFB45.getText().isEmpty())
					|| (!tFB51.isDisable() && tFB51.getText().isEmpty())
					|| (!tFB52.isDisable() && tFB52.getText().isEmpty())
					|| (!tFB53.isDisable() && tFB53.getText().isEmpty())
					|| (!tFB54.isDisable() && tFB54.getText().isEmpty())
					|| (!tFB55.isDisable() && tFB55.getText().isEmpty())) {
				empty = true;
			}
		}

		if (opType == OperationType.SCALAR) {
			if (tFScalar.getText().isEmpty()) {
				empty = true;
			}
		}

		return empty;
	}

	// m�todo que verifica todas as caixas de checagem e habilita/desabilita campos
	private void updateCheckBoxes() {
		if (chBxAI1.isSelected() && chBxAJ1.isSelected()) {
			tFA11.setDisable(false);
		} else {
			tFA11.setDisable(true);
			tFA11.clear();
		}
		if (chBxAI1.isSelected() && chBxAJ2.isSelected()) {
			tFA12.setDisable(false);
		} else {
			tFA12.setDisable(true);
			tFA12.clear();
		}
		if (chBxAI1.isSelected() && chBxAJ3.isSelected()) {
			tFA13.setDisable(false);
		} else {
			tFA13.setDisable(true);
			tFA13.clear();
		}
		if (chBxAI1.isSelected() && chBxAJ4.isSelected()) {
			tFA14.setDisable(false);
		} else {
			tFA14.setDisable(true);
			tFA14.clear();
		}
		if (chBxAI1.isSelected() && chBxAJ5.isSelected()) {
			tFA15.setDisable(false);
		} else {
			tFA15.setDisable(true);
			tFA15.clear();
		}
		if (chBxAI2.isSelected() && chBxAJ1.isSelected()) {
			tFA21.setDisable(false);
		} else {
			tFA21.setDisable(true);
			tFA21.clear();
		}
		if (chBxAI2.isSelected() && chBxAJ2.isSelected()) {
			tFA22.setDisable(false);
		} else {
			tFA22.setDisable(true);
			tFA22.clear();
		}
		if (chBxAI2.isSelected() && chBxAJ3.isSelected()) {
			tFA23.setDisable(false);
		} else {
			tFA23.setDisable(true);
			tFA23.clear();
		}
		if (chBxAI2.isSelected() && chBxAJ4.isSelected()) {
			tFA24.setDisable(false);
		} else {
			tFA24.setDisable(true);
			tFA24.clear();
		}
		if (chBxAI2.isSelected() && chBxAJ5.isSelected()) {
			tFA25.setDisable(false);
		} else {
			tFA25.setDisable(true);
			tFA25.clear();
		}
		if (chBxAI3.isSelected() && chBxAJ1.isSelected()) {
			tFA31.setDisable(false);
		} else {
			tFA31.setDisable(true);
			tFA31.clear();
		}
		if (chBxAI3.isSelected() && chBxAJ2.isSelected()) {
			tFA32.setDisable(false);
		} else {
			tFA32.setDisable(true);
			tFA32.clear();
		}
		if (chBxAI3.isSelected() && chBxAJ3.isSelected()) {
			tFA33.setDisable(false);
		} else {
			tFA33.setDisable(true);
			tFA33.clear();
		}
		if (chBxAI3.isSelected() && chBxAJ4.isSelected()) {
			tFA34.setDisable(false);
		} else {
			tFA34.setDisable(true);
			tFA34.clear();
		}
		if (chBxAI3.isSelected() && chBxAJ5.isSelected()) {
			tFA35.setDisable(false);
		} else {
			tFA35.setDisable(true);
			tFA35.clear();
		}
		if (chBxAI4.isSelected() && chBxAJ1.isSelected()) {
			tFA41.setDisable(false);
		} else {
			tFA41.setDisable(true);
			tFA41.clear();
		}
		if (chBxAI4.isSelected() && chBxAJ2.isSelected()) {
			tFA42.setDisable(false);
		} else {
			tFA42.setDisable(true);
			tFA42.clear();
		}
		if (chBxAI4.isSelected() && chBxAJ3.isSelected()) {
			tFA43.setDisable(false);
		} else {
			tFA43.setDisable(true);
			tFA43.clear();
		}
		if (chBxAI4.isSelected() && chBxAJ4.isSelected()) {
			tFA44.setDisable(false);
		} else {
			tFA44.setDisable(true);
			tFA44.clear();
		}
		if (chBxAI4.isSelected() && chBxAJ5.isSelected()) {
			tFA45.setDisable(false);
		} else {
			tFA45.setDisable(true);
			tFA45.clear();
		}
		if (chBxAI5.isSelected() && chBxAJ1.isSelected()) {
			tFA51.setDisable(false);
		} else {
			tFA51.setDisable(true);
			tFA51.clear();
		}
		if (chBxAI5.isSelected() && chBxAJ2.isSelected()) {
			tFA52.setDisable(false);
		} else {
			tFA52.setDisable(true);
			tFA52.clear();
		}
		if (chBxAI5.isSelected() && chBxAJ3.isSelected()) {
			tFA53.setDisable(false);
		} else {
			tFA53.setDisable(true);
			tFA53.clear();
		}
		if (chBxAI5.isSelected() && chBxAJ4.isSelected()) {
			tFA54.setDisable(false);
		} else {
			tFA54.setDisable(true);
			tFA54.clear();
		}
		if (chBxAI5.isSelected() && chBxAJ5.isSelected()) {
			tFA55.setDisable(false);
		} else {
			tFA55.setDisable(true);
			tFA55.clear();
		}

		if (opType != OperationType.SCALAR) {
			if (chBxBI1.isSelected() && chBxBJ1.isSelected()) {
				tFB11.setDisable(false);
			} else {
				tFB11.setDisable(true);
				tFB11.clear();
			}
			if (chBxBI1.isSelected() && chBxBJ2.isSelected()) {
				tFB12.setDisable(false);
			} else {
				tFB12.setDisable(true);
				tFB12.clear();
			}
			if (chBxBI1.isSelected() && chBxBJ3.isSelected()) {
				tFB13.setDisable(false);
			} else {
				tFB13.setDisable(true);
				tFB13.clear();
			}
			if (chBxBI1.isSelected() && chBxBJ4.isSelected()) {
				tFB14.setDisable(false);
			} else {
				tFB14.setDisable(true);
				tFB14.clear();
			}
			if (chBxBI1.isSelected() && chBxBJ5.isSelected()) {
				tFB15.setDisable(false);
			} else {
				tFB15.setDisable(true);
				tFB15.clear();
			}
			if (chBxBI2.isSelected() && chBxBJ1.isSelected()) {
				tFB21.setDisable(false);
			} else {
				tFB21.setDisable(true);
				tFB21.clear();
			}
			if (chBxBI2.isSelected() && chBxBJ2.isSelected()) {
				tFB22.setDisable(false);
			} else {
				tFB22.setDisable(true);
				tFB22.clear();
			}
			if (chBxBI2.isSelected() && chBxBJ3.isSelected()) {
				tFB23.setDisable(false);
			} else {
				tFB23.setDisable(true);
				tFB23.clear();
			}
			if (chBxBI2.isSelected() && chBxBJ4.isSelected()) {
				tFB24.setDisable(false);
			} else {
				tFB24.setDisable(true);
				tFB24.clear();
			}
			if (chBxBI2.isSelected() && chBxBJ5.isSelected()) {
				tFB25.setDisable(false);
			} else {
				tFB25.setDisable(true);
				tFB25.clear();
			}
			if (chBxBI3.isSelected() && chBxBJ1.isSelected()) {
				tFB31.setDisable(false);
			} else {
				tFB31.setDisable(true);
				tFB31.clear();
			}
			if (chBxBI3.isSelected() && chBxBJ2.isSelected()) {
				tFB32.setDisable(false);
			} else {
				tFB32.setDisable(true);
				tFB32.clear();
			}
			if (chBxBI3.isSelected() && chBxBJ3.isSelected()) {
				tFB33.setDisable(false);
			} else {
				tFB33.setDisable(true);
				tFB33.clear();
			}
			if (chBxBI3.isSelected() && chBxBJ4.isSelected()) {
				tFB34.setDisable(false);
			} else {
				tFB34.setDisable(true);
				tFB34.clear();
			}
			if (chBxBI3.isSelected() && chBxBJ5.isSelected()) {
				tFB35.setDisable(false);
			} else {
				tFB35.setDisable(true);
				tFB35.clear();
			}
			if (chBxBI4.isSelected() && chBxBJ1.isSelected()) {
				tFB41.setDisable(false);
			} else {
				tFB41.setDisable(true);
				tFB41.clear();
			}
			if (chBxBI4.isSelected() && chBxBJ2.isSelected()) {
				tFB42.setDisable(false);
			} else {
				tFB42.setDisable(true);
				tFB42.clear();
			}
			if (chBxBI4.isSelected() && chBxBJ3.isSelected()) {
				tFB43.setDisable(false);
			} else {
				tFB43.setDisable(true);
				tFB43.clear();
			}
			if (chBxBI4.isSelected() && chBxBJ4.isSelected()) {
				tFB44.setDisable(false);
			} else {
				tFB44.setDisable(true);
				tFB44.clear();
			}
			if (chBxBI4.isSelected() && chBxBJ5.isSelected()) {
				tFB45.setDisable(false);
			} else {
				tFB45.setDisable(true);
				tFB45.clear();
			}
			if (chBxBI5.isSelected() && chBxBJ1.isSelected()) {
				tFB51.setDisable(false);
			} else {
				tFB51.setDisable(true);
				tFB51.clear();
			}
			if (chBxBI5.isSelected() && chBxBJ2.isSelected()) {
				tFB52.setDisable(false);
			} else {
				tFB52.setDisable(true);
				tFB52.clear();
			}
			if (chBxBI5.isSelected() && chBxBJ3.isSelected()) {
				tFB53.setDisable(false);
			} else {
				tFB53.setDisable(true);
				tFB53.clear();
			}
			if (chBxBI5.isSelected() && chBxBJ4.isSelected()) {
				tFB54.setDisable(false);
			} else {
				tFB54.setDisable(true);
				tFB54.clear();
			}
			if (chBxBI5.isSelected() && chBxBJ5.isSelected()) {
				tFB55.setDisable(false);
			} else {
				tFB55.setDisable(true);
				tFB55.clear();
			}
		}
	}

	// checa as dimensoes das matrizes e atualiza nas vari�veis da classe
	private void checkDim() {
		this.aI = 1;
		if (chBxAI2.isSelected()) {
			this.aI = 2;
		}
		if (chBxAI3.isSelected()) {
			this.aI = 3;
		}
		if (chBxAI4.isSelected()) {
			this.aI = 4;
		}
		if (chBxAI5.isSelected()) {
			this.aI = 5;
		}

		this.aJ = 1;
		if (chBxAJ2.isSelected()) {
			this.aJ = 2;
		}
		if (chBxAJ3.isSelected()) {
			this.aJ = 3;
		}
		if (chBxAJ4.isSelected()) {
			this.aJ = 4;
		}
		if (chBxAJ5.isSelected()) {
			this.aJ = 5;
		}

		if (opType != OperationType.SCALAR) {
			this.bI = 1;
			if (chBxBI2.isSelected()) {
				this.bI = 2;
			}
			if (chBxBI3.isSelected()) {
				this.bI = 3;
			}
			if (chBxBI4.isSelected()) {
				this.bI = 4;
			}
			if (chBxBI5.isSelected()) {
				this.bI = 5;
			}

			this.bJ = 1;
			if (chBxBJ2.isSelected()) {
				this.bJ = 2;
			}
			if (chBxBJ3.isSelected()) {
				this.bJ = 3;
			}
			if (chBxBJ4.isSelected()) {
				this.bJ = 4;
			}
			if (chBxBJ5.isSelected()) {
				this.bJ = 5;
			}
		}
	}

	// m�todo para projetar campo do escalar na tela
	private void addScalar(int x, int y) {
		tFScalar = new LimitedRomanField(digitLimit);
		tFScalar.setId("scalar");
		tFScalar.setLayoutX(x);
		tFScalar.setLayoutY(y);
		tFScalar.setPrefWidth(165);
		root.getChildren().add(tFScalar);
	}

	// m�todo para projetar campos da matriz A
	private void addMatrixA(int x, int y) {
		tFA11 = new LimitedRomanField(digitLimit);
		tFA11.setLayoutX(x);
		tFA11.setLayoutY(y);
		tFA11.setPrefWidth(tFPrefWidht);
		tFA12 = new LimitedRomanField(digitLimit);
		tFA12.layoutXProperty().bind(tFA11.layoutXProperty().add(tFA11.widthProperty()).add(tFSpacing));
		tFA12.layoutYProperty().bind(tFA11.layoutYProperty());
		tFA12.setPrefWidth(tFPrefWidht);
		tFA13 = new LimitedRomanField(digitLimit);
		tFA13.layoutXProperty().bind(tFA12.layoutXProperty().add(tFA12.widthProperty()).add(tFSpacing));
		tFA13.layoutYProperty().bind(tFA12.layoutYProperty());
		tFA13.setPrefWidth(tFPrefWidht);
		tFA14 = new LimitedRomanField(digitLimit);
		tFA14.layoutXProperty().bind(tFA13.layoutXProperty().add(tFA13.widthProperty()).add(tFSpacing));
		tFA14.layoutYProperty().bind(tFA13.layoutYProperty());
		tFA14.setPrefWidth(tFPrefWidht);
		tFA15 = new LimitedRomanField(digitLimit);
		tFA15.layoutXProperty().bind(tFA14.layoutXProperty().add(tFA14.widthProperty()).add(tFSpacing));
		tFA15.layoutYProperty().bind(tFA14.layoutYProperty());
		tFA15.setPrefWidth(tFPrefWidht);

		tFA21 = new LimitedRomanField(digitLimit);
		tFA21.layoutXProperty().bind(tFA11.layoutXProperty());
		tFA21.layoutYProperty().bind(tFA11.layoutYProperty().add(tFA11.heightProperty()).add(tFSpacing));
		tFA21.setPrefWidth(tFPrefWidht);
		tFA22 = new LimitedRomanField(digitLimit);
		tFA22.layoutXProperty().bind(tFA21.layoutXProperty().add(tFA21.widthProperty()).add(tFSpacing));
		tFA22.layoutYProperty().bind(tFA21.layoutYProperty());
		tFA22.setPrefWidth(tFPrefWidht);
		tFA23 = new LimitedRomanField(digitLimit);
		tFA23.layoutXProperty().bind(tFA22.layoutXProperty().add(tFA22.widthProperty()).add(tFSpacing));
		tFA23.layoutYProperty().bind(tFA22.layoutYProperty());
		tFA23.setPrefWidth(tFPrefWidht);
		tFA24 = new LimitedRomanField(digitLimit);
		tFA24.layoutXProperty().bind(tFA23.layoutXProperty().add(tFA23.widthProperty()).add(tFSpacing));
		tFA24.layoutYProperty().bind(tFA23.layoutYProperty());
		tFA24.setPrefWidth(tFPrefWidht);
		tFA25 = new LimitedRomanField(digitLimit);
		tFA25.layoutXProperty().bind(tFA24.layoutXProperty().add(tFA24.widthProperty()).add(tFSpacing));
		tFA25.layoutYProperty().bind(tFA24.layoutYProperty());
		tFA25.setPrefWidth(tFPrefWidht);

		tFA31 = new LimitedRomanField(digitLimit);
		tFA31.layoutXProperty().bind(tFA21.layoutXProperty());
		tFA31.layoutYProperty().bind(tFA21.layoutYProperty().add(tFA21.heightProperty()).add(tFSpacing));
		tFA31.setPrefWidth(tFPrefWidht);
		tFA32 = new LimitedRomanField(digitLimit);
		tFA32.layoutXProperty().bind(tFA31.layoutXProperty().add(tFA31.widthProperty()).add(tFSpacing));
		tFA32.layoutYProperty().bind(tFA31.layoutYProperty());
		tFA32.setPrefWidth(tFPrefWidht);
		tFA33 = new LimitedRomanField(digitLimit);
		tFA33.layoutXProperty().bind(tFA32.layoutXProperty().add(tFA32.widthProperty()).add(tFSpacing));
		tFA33.layoutYProperty().bind(tFA32.layoutYProperty());
		tFA33.setPrefWidth(tFPrefWidht);
		tFA34 = new LimitedRomanField(digitLimit);
		tFA34.layoutXProperty().bind(tFA33.layoutXProperty().add(tFA33.widthProperty()).add(tFSpacing));
		tFA34.layoutYProperty().bind(tFA33.layoutYProperty());
		tFA34.setPrefWidth(tFPrefWidht);
		tFA35 = new LimitedRomanField(digitLimit);
		tFA35.layoutXProperty().bind(tFA34.layoutXProperty().add(tFA34.widthProperty()).add(tFSpacing));
		tFA35.layoutYProperty().bind(tFA34.layoutYProperty());
		tFA35.setPrefWidth(tFPrefWidht);

		tFA41 = new LimitedRomanField(digitLimit);
		tFA41.layoutXProperty().bind(tFA31.layoutXProperty());
		tFA41.layoutYProperty().bind(tFA31.layoutYProperty().add(tFA31.heightProperty()).add(tFSpacing));
		tFA41.setPrefWidth(tFPrefWidht);
		tFA42 = new LimitedRomanField(digitLimit);
		tFA42.layoutXProperty().bind(tFA41.layoutXProperty().add(tFA41.widthProperty()).add(tFSpacing));
		tFA42.layoutYProperty().bind(tFA41.layoutYProperty());
		tFA42.setPrefWidth(tFPrefWidht);
		tFA43 = new LimitedRomanField(digitLimit);
		tFA43.layoutXProperty().bind(tFA42.layoutXProperty().add(tFA42.widthProperty()).add(tFSpacing));
		tFA43.layoutYProperty().bind(tFA42.layoutYProperty());
		tFA43.setPrefWidth(tFPrefWidht);
		tFA44 = new LimitedRomanField(digitLimit);
		tFA44.layoutXProperty().bind(tFA43.layoutXProperty().add(tFA43.widthProperty()).add(tFSpacing));
		tFA44.layoutYProperty().bind(tFA43.layoutYProperty());
		tFA44.setPrefWidth(tFPrefWidht);
		tFA45 = new LimitedRomanField(digitLimit);
		tFA45.layoutXProperty().bind(tFA44.layoutXProperty().add(tFA44.widthProperty()).add(tFSpacing));
		tFA45.layoutYProperty().bind(tFA44.layoutYProperty());
		tFA45.setPrefWidth(tFPrefWidht);

		tFA51 = new LimitedRomanField(digitLimit);
		tFA51.layoutXProperty().bind(tFA41.layoutXProperty());
		tFA51.layoutYProperty().bind(tFA41.layoutYProperty().add(tFA41.heightProperty()).add(tFSpacing));
		tFA51.setPrefWidth(tFPrefWidht);
		tFA52 = new LimitedRomanField(digitLimit);
		tFA52.layoutXProperty().bind(tFA51.layoutXProperty().add(tFA51.widthProperty()).add(tFSpacing));
		tFA52.layoutYProperty().bind(tFA51.layoutYProperty());
		tFA52.setPrefWidth(tFPrefWidht);
		tFA53 = new LimitedRomanField(digitLimit);
		tFA53.layoutXProperty().bind(tFA52.layoutXProperty().add(tFA52.widthProperty()).add(tFSpacing));
		tFA53.layoutYProperty().bind(tFA52.layoutYProperty());
		tFA53.setPrefWidth(tFPrefWidht);
		tFA54 = new LimitedRomanField(digitLimit);
		tFA54.layoutXProperty().bind(tFA53.layoutXProperty().add(tFA53.widthProperty()).add(tFSpacing));
		tFA54.layoutYProperty().bind(tFA53.layoutYProperty());
		tFA54.setPrefWidth(tFPrefWidht);
		tFA55 = new LimitedRomanField(digitLimit);
		tFA55.layoutXProperty().bind(tFA54.layoutXProperty().add(tFA54.widthProperty()).add(tFSpacing));
		tFA55.layoutYProperty().bind(tFA54.layoutYProperty());
		tFA55.setPrefWidth(tFPrefWidht);

		chBxAI1 = new CheckBox();
		chBxAI1.layoutXProperty().bind(tFA11.layoutXProperty().subtract(chBxAI1.widthProperty()).subtract(tFSpacing));
		chBxAI1.layoutYProperty().bind(tFA11.layoutYProperty());
		chBxAI2 = new CheckBox();
		chBxAI2.layoutXProperty().bind(chBxAI1.layoutXProperty());
		chBxAI2.layoutYProperty().bind(chBxAI1.layoutYProperty().add(tFA11.heightProperty()).add(tFSpacing));
		chBxAI3 = new CheckBox();
		chBxAI3.layoutXProperty().bind(chBxAI2.layoutXProperty());
		chBxAI3.layoutYProperty().bind(chBxAI2.layoutYProperty().add(tFA11.heightProperty()).add(tFSpacing));
		chBxAI4 = new CheckBox();
		chBxAI4.layoutXProperty().bind(chBxAI3.layoutXProperty());
		chBxAI4.layoutYProperty().bind(chBxAI3.layoutYProperty().add(tFA11.heightProperty()).add(tFSpacing));
		chBxAI5 = new CheckBox();
		chBxAI5.layoutXProperty().bind(chBxAI4.layoutXProperty());
		chBxAI5.layoutYProperty().bind(chBxAI4.layoutYProperty().add(tFA11.heightProperty()).add(tFSpacing));

		chBxAJ1 = new CheckBox();
		chBxAJ1.layoutXProperty().bind(tFA11.layoutXProperty());
		chBxAJ1.layoutYProperty().bind(tFA11.layoutYProperty().subtract(chBxAJ1.heightProperty()).subtract(5));
		chBxAJ2 = new CheckBox();
		chBxAJ2.layoutXProperty().bind(chBxAJ1.layoutXProperty().add(tFA11.widthProperty()));
		chBxAJ2.layoutYProperty().bind(chBxAJ1.layoutYProperty());
		chBxAJ3 = new CheckBox();
		chBxAJ3.layoutXProperty().bind(chBxAJ2.layoutXProperty().add(tFA11.widthProperty()));
		chBxAJ3.layoutYProperty().bind(chBxAJ2.layoutYProperty());
		chBxAJ4 = new CheckBox();
		chBxAJ4.layoutXProperty().bind(chBxAJ3.layoutXProperty().add(tFA11.widthProperty()));
		chBxAJ4.layoutYProperty().bind(chBxAJ3.layoutYProperty());
		chBxAJ5 = new CheckBox();
		chBxAJ5.layoutXProperty().bind(chBxAJ4.layoutXProperty().add(tFA11.widthProperty()));
		chBxAJ5.layoutYProperty().bind(chBxAJ4.layoutYProperty());

		root.getChildren().addAll(tFA11, tFA12, tFA13, tFA14, tFA15, tFA21, tFA22, tFA23, tFA24, tFA25, tFA31, tFA32,
				tFA33, tFA34, tFA35, tFA41, tFA42, tFA43, tFA44, tFA45, tFA51, tFA52, tFA53, tFA54, tFA55,

				chBxAI1, chBxAI2, chBxAI3, chBxAI4, chBxAI5, chBxAJ1, chBxAJ2, chBxAJ3, chBxAJ4, chBxAJ5);
	}

	// m�todo para projetar campo da matriz B
	private void addMatrixB(int x, int y) {
		tFB11 = new LimitedRomanField(digitLimit);
		tFB11.setLayoutX(x);
		tFB11.setLayoutY(y);
		tFB11.setPrefWidth(tFPrefWidht);
		tFB12 = new LimitedRomanField(digitLimit);
		tFB12.layoutXProperty().bind(tFB11.layoutXProperty().add(tFB11.widthProperty()).add(tFSpacing));
		tFB12.layoutYProperty().bind(tFB11.layoutYProperty());
		tFB12.setPrefWidth(tFPrefWidht);
		tFB13 = new LimitedRomanField(digitLimit);
		tFB13.layoutXProperty().bind(tFB12.layoutXProperty().add(tFB12.widthProperty()).add(tFSpacing));
		tFB13.layoutYProperty().bind(tFB12.layoutYProperty());
		tFB13.setPrefWidth(tFPrefWidht);
		tFB14 = new LimitedRomanField(digitLimit);
		tFB14.layoutXProperty().bind(tFB13.layoutXProperty().add(tFB13.widthProperty()).add(tFSpacing));
		tFB14.layoutYProperty().bind(tFB13.layoutYProperty());
		tFB14.setPrefWidth(tFPrefWidht);
		tFB15 = new LimitedRomanField(digitLimit);
		tFB15.layoutXProperty().bind(tFB14.layoutXProperty().add(tFB14.widthProperty()).add(tFSpacing));
		tFB15.layoutYProperty().bind(tFB14.layoutYProperty());
		tFB15.setPrefWidth(tFPrefWidht);

		tFB21 = new LimitedRomanField(digitLimit);
		tFB21.layoutXProperty().bind(tFB11.layoutXProperty());
		tFB21.layoutYProperty().bind(tFB11.layoutYProperty().add(tFB11.heightProperty()).add(tFSpacing));
		tFB21.setPrefWidth(tFPrefWidht);
		tFB22 = new LimitedRomanField(digitLimit);
		tFB22.layoutXProperty().bind(tFB21.layoutXProperty().add(tFB21.widthProperty()).add(tFSpacing));
		tFB22.layoutYProperty().bind(tFB21.layoutYProperty());
		tFB22.setPrefWidth(tFPrefWidht);
		tFB23 = new LimitedRomanField(digitLimit);
		tFB23.layoutXProperty().bind(tFB22.layoutXProperty().add(tFB22.widthProperty()).add(tFSpacing));
		tFB23.layoutYProperty().bind(tFB22.layoutYProperty());
		tFB23.setPrefWidth(tFPrefWidht);
		tFB24 = new LimitedRomanField(digitLimit);
		tFB24.layoutXProperty().bind(tFB23.layoutXProperty().add(tFB23.widthProperty()).add(tFSpacing));
		tFB24.layoutYProperty().bind(tFB23.layoutYProperty());
		tFB24.setPrefWidth(tFPrefWidht);
		tFB25 = new LimitedRomanField(digitLimit);
		tFB25.layoutXProperty().bind(tFB24.layoutXProperty().add(tFB24.widthProperty()).add(tFSpacing));
		tFB25.layoutYProperty().bind(tFB24.layoutYProperty());
		tFB25.setPrefWidth(tFPrefWidht);

		tFB31 = new LimitedRomanField(digitLimit);
		tFB31.layoutXProperty().bind(tFB21.layoutXProperty());
		tFB31.layoutYProperty().bind(tFB21.layoutYProperty().add(tFB21.heightProperty()).add(tFSpacing));
		tFB31.setPrefWidth(tFPrefWidht);
		tFB32 = new LimitedRomanField(digitLimit);
		tFB32.layoutXProperty().bind(tFB31.layoutXProperty().add(tFB31.widthProperty()).add(tFSpacing));
		tFB32.layoutYProperty().bind(tFB31.layoutYProperty());
		tFB32.setPrefWidth(tFPrefWidht);
		tFB33 = new LimitedRomanField(digitLimit);
		tFB33.layoutXProperty().bind(tFB32.layoutXProperty().add(tFB32.widthProperty()).add(tFSpacing));
		tFB33.layoutYProperty().bind(tFB32.layoutYProperty());
		tFB33.setPrefWidth(tFPrefWidht);
		tFB34 = new LimitedRomanField(digitLimit);
		tFB34.layoutXProperty().bind(tFB33.layoutXProperty().add(tFB33.widthProperty()).add(tFSpacing));
		tFB34.layoutYProperty().bind(tFB33.layoutYProperty());
		tFB34.setPrefWidth(tFPrefWidht);
		tFB35 = new LimitedRomanField(digitLimit);
		tFB35.layoutXProperty().bind(tFB34.layoutXProperty().add(tFB34.widthProperty()).add(tFSpacing));
		tFB35.layoutYProperty().bind(tFB34.layoutYProperty());
		tFB35.setPrefWidth(tFPrefWidht);

		tFB41 = new LimitedRomanField(digitLimit);
		tFB41.layoutXProperty().bind(tFB31.layoutXProperty());
		tFB41.layoutYProperty().bind(tFB31.layoutYProperty().add(tFB31.heightProperty()).add(tFSpacing));
		tFB41.setPrefWidth(tFPrefWidht);
		tFB42 = new LimitedRomanField(digitLimit);
		tFB42.layoutXProperty().bind(tFB41.layoutXProperty().add(tFB41.widthProperty()).add(tFSpacing));
		tFB42.layoutYProperty().bind(tFB41.layoutYProperty());
		tFB42.setPrefWidth(tFPrefWidht);
		tFB43 = new LimitedRomanField(digitLimit);
		tFB43.layoutXProperty().bind(tFB42.layoutXProperty().add(tFB42.widthProperty()).add(tFSpacing));
		tFB43.layoutYProperty().bind(tFB42.layoutYProperty());
		tFB43.setPrefWidth(tFPrefWidht);
		tFB44 = new LimitedRomanField(digitLimit);
		tFB44.layoutXProperty().bind(tFB43.layoutXProperty().add(tFB43.widthProperty()).add(tFSpacing));
		tFB44.layoutYProperty().bind(tFB43.layoutYProperty());
		tFB44.setPrefWidth(tFPrefWidht);
		tFB45 = new LimitedRomanField(digitLimit);
		tFB45.layoutXProperty().bind(tFB44.layoutXProperty().add(tFB44.widthProperty()).add(tFSpacing));
		tFB45.layoutYProperty().bind(tFB44.layoutYProperty());
		tFB45.setPrefWidth(tFPrefWidht);

		tFB51 = new LimitedRomanField(digitLimit);
		tFB51.layoutXProperty().bind(tFB41.layoutXProperty());
		tFB51.layoutYProperty().bind(tFB41.layoutYProperty().add(tFB41.heightProperty()).add(tFSpacing));
		tFB51.setPrefWidth(tFPrefWidht);
		tFB52 = new LimitedRomanField(digitLimit);
		tFB52.layoutXProperty().bind(tFB51.layoutXProperty().add(tFB51.widthProperty()).add(tFSpacing));
		tFB52.layoutYProperty().bind(tFB51.layoutYProperty());
		tFB52.setPrefWidth(tFPrefWidht);
		tFB53 = new LimitedRomanField(digitLimit);
		tFB53.layoutXProperty().bind(tFB52.layoutXProperty().add(tFB52.widthProperty()).add(tFSpacing));
		tFB53.layoutYProperty().bind(tFB52.layoutYProperty());
		tFB53.setPrefWidth(tFPrefWidht);
		tFB54 = new LimitedRomanField(digitLimit);
		tFB54.layoutXProperty().bind(tFB53.layoutXProperty().add(tFB53.widthProperty()).add(tFSpacing));
		tFB54.layoutYProperty().bind(tFB53.layoutYProperty());
		tFB54.setPrefWidth(tFPrefWidht);
		tFB55 = new LimitedRomanField(digitLimit);
		tFB55.layoutXProperty().bind(tFB54.layoutXProperty().add(tFB54.widthProperty()).add(tFSpacing));
		tFB55.layoutYProperty().bind(tFB54.layoutYProperty());
		tFB55.setPrefWidth(tFPrefWidht);

		chBxBI1 = new CheckBox();
		chBxBI1.layoutXProperty().bind(tFB11.layoutXProperty().subtract(chBxBI1.widthProperty()).subtract(tFSpacing));
		chBxBI1.layoutYProperty().bind(tFB11.layoutYProperty());
		chBxBI2 = new CheckBox();
		chBxBI2.layoutXProperty().bind(chBxBI1.layoutXProperty());
		chBxBI2.layoutYProperty().bind(chBxBI1.layoutYProperty().add(tFB11.heightProperty()).add(tFSpacing));
		chBxBI3 = new CheckBox();
		chBxBI3.layoutXProperty().bind(chBxBI2.layoutXProperty());
		chBxBI3.layoutYProperty().bind(chBxBI2.layoutYProperty().add(tFB11.heightProperty()).add(tFSpacing));
		chBxBI4 = new CheckBox();
		chBxBI4.layoutXProperty().bind(chBxBI3.layoutXProperty());
		chBxBI4.layoutYProperty().bind(chBxBI3.layoutYProperty().add(tFB11.heightProperty()).add(tFSpacing));
		chBxBI5 = new CheckBox();
		chBxBI5.layoutXProperty().bind(chBxBI4.layoutXProperty());
		chBxBI5.layoutYProperty().bind(chBxBI4.layoutYProperty().add(tFB11.heightProperty()).add(tFSpacing));

		chBxBJ1 = new CheckBox();
		chBxBJ1.layoutXProperty().bind(tFB11.layoutXProperty());
		chBxBJ1.layoutYProperty().bind(tFB11.layoutYProperty().subtract(chBxBJ1.heightProperty()).subtract(5));
		chBxBJ2 = new CheckBox();
		chBxBJ2.layoutXProperty().bind(chBxBJ1.layoutXProperty().add(tFB11.widthProperty()));
		chBxBJ2.layoutYProperty().bind(chBxBJ1.layoutYProperty());
		chBxBJ3 = new CheckBox();
		chBxBJ3.layoutXProperty().bind(chBxBJ2.layoutXProperty().add(tFB11.widthProperty()));
		chBxBJ3.layoutYProperty().bind(chBxBJ2.layoutYProperty());
		chBxBJ4 = new CheckBox();
		chBxBJ4.layoutXProperty().bind(chBxBJ3.layoutXProperty().add(tFB11.widthProperty()));
		chBxBJ4.layoutYProperty().bind(chBxBJ3.layoutYProperty());
		chBxBJ5 = new CheckBox();
		chBxBJ5.layoutXProperty().bind(chBxBJ4.layoutXProperty().add(tFB11.widthProperty()));
		chBxBJ5.layoutYProperty().bind(chBxBJ4.layoutYProperty());

		root.getChildren().addAll(tFB11, tFB12, tFB13, tFB14, tFB15, tFB21, tFB22, tFB23, tFB24, tFB25, tFB31, tFB32,
				tFB33, tFB34, tFB35, tFB41, tFB42, tFB43, tFB44, tFB45, tFB51, tFB52, tFB53, tFB54, tFB55,

				chBxBI1, chBxBI2, chBxBI3, chBxBI4, chBxBI5, chBxBJ1, chBxBJ2, chBxBJ3, chBxBJ4, chBxBJ5);
	}
	
}