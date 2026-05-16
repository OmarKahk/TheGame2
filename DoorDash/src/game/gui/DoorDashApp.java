
package game.gui;

import game.engine.*;
import game.engine.cells.*;
import game.engine.monsters.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DoorDashApp extends Application {

    private Game game;
    private GridPane boardGrid;
    private Label statusLabel;
    private Label rollLabel;
    private VBox playerInfo;
    private VBox opponentInfo;

    @Override
    public void start(Stage stage) {
        stage.setTitle("DoorDash - Monsters Inc Edition");

        VBox menu = createStartMenu(stage);

        Scene scene = new Scene(menu, 1400, 900);
        stage.setScene(scene);
        stage.show();
    }

    private VBox createStartMenu(Stage stage) {
        Label title = new Label("DoorDash");
        title.setFont(new Font(40));

        Label subtitle = new Label("Scare vs Laugh Touchdown");
        subtitle.setFont(new Font(20));

        Button scarer = new Button("Play as SCARER");
        Button laugher = new Button("Play as LAUGHER");

        scarer.setPrefWidth(240);
        laugher.setPrefWidth(240);

        scarer.setOnAction(e -> startGame(stage, Role.SCARER));
        laugher.setOnAction(e -> startGame(stage, Role.LAUGHER));

        TextArea instructions = new TextArea(
                "Goal:\\n" +
                "- Reach cell 99 with at least 1000 energy.\\n\\n" +
                "Gameplay:\\n" +
                "- Roll the dice each turn.\\n" +
                "- Use powerups strategically.\\n" +
                "- Doors affect monster energy.\\n" +
                "- Card cells trigger random effects.\\n" +
                "- Conveyor belts and socks change positions.\\n"
        );

        instructions.setEditable(false);
        instructions.setPrefHeight(250);

        VBox root = new VBox(20, title, subtitle, scarer, laugher, instructions);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);

        return root;
    }

    private void startGame(Stage stage, Role role) {
        try {
            game = new Game(role);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        BorderPane root = new BorderPane();

        boardGrid = new GridPane();
        boardGrid.setHgap(3);
        boardGrid.setVgap(3);
        boardGrid.setPadding(new Insets(10));

        drawBoard();

        statusLabel = new Label("Game Started");
        statusLabel.setFont(new Font(18));

        rollLabel = new Label("Roll Result: -");
        rollLabel.setFont(new Font(16));

        Button powerButton = new Button("Use Powerup");
        Button rollButton = new Button("Roll Dice");

        powerButton.setOnAction(e -> {
            try {
                game.usePowerup();
                statusLabel.setText(game.getCurrent().getName() + " used powerup!");
                refresh();
            } catch (Exception ex) {
                showAlert(ex.getMessage());
            }
        });

        rollButton.setOnAction(e -> {
            Monster before = game.getCurrent();
            int oldPos = before.getPosition();

            try {
                game.playTurn();

                Monster after = game.getCurrent() == game.getPlayer()
                        ? game.getOpponent()
                        : game.getPlayer();

                int newPos = after.getPosition();
                rollLabel.setText("Moved from " + oldPos + " to " + newPos);

                if (game.getWinner() != null) {
                    showAlert("Winner: " + game.getWinner().getName());
                }

                refresh();

            } catch (Exception ex) {
                showAlert(ex.getMessage());
            }
        });

        HBox controls = new HBox(15, powerButton, rollButton);
        controls.setAlignment(Pos.CENTER);

        VBox bottom = new VBox(10, statusLabel, rollLabel, controls);
        bottom.setPadding(new Insets(10));
        bottom.setAlignment(Pos.CENTER);

        playerInfo = new VBox(10);
        opponentInfo = new VBox(10);

        refreshMonsterPanels();

        root.setCenter(boardGrid);
        root.setBottom(bottom);
        root.setLeft(playerInfo);
        root.setRight(opponentInfo);

        Scene scene = new Scene(root, 1600, 950);
        stage.setScene(scene);
    }

    private void refresh() {
        drawBoard();
        refreshMonsterPanels();
    }

    private void refreshMonsterPanels() {
        playerInfo.getChildren().clear();
        opponentInfo.getChildren().clear();

        playerInfo.getChildren().add(createMonsterCard("PLAYER", game.getPlayer()));
        opponentInfo.getChildren().add(createMonsterCard("OPPONENT", game.getOpponent()));
    }

    private VBox createMonsterCard(String title, Monster monster) {
        Label head = new Label(title);
        head.setFont(new Font(22));

        VBox box = new VBox(
                6,
                head,
                new Label("Name: " + monster.getName()),
                new Label("Type: " + monster.getClass().getSimpleName()),
                new Label("Role: " + monster.getRole()),
                new Label("Original Role: " + monster.getOriginalRole()),
                new Label("Energy: " + monster.getEnergy()),
                new Label("Position: " + monster.getPosition()),
                new Label("Shield: " + monster.isShielded()),
                new Label("Frozen: " + monster.isFrozen()),
                new Label("Confusion Turns: " + monster.getConfusionTurns())
        );

        box.setPadding(new Insets(12));
        box.setStyle("-fx-border-color: black; -fx-background-color: #f3f3f3;");
        box.setPrefWidth(250);

        return box;
    }

    private void drawBoard() {
        boardGrid.getChildren().clear();

        Cell[][] cells = game.getBoard().getBoardCells();

        for (int row = 0; row < Constants.BOARD_ROWS; row++) {
            for (int col = 0; col < Constants.BOARD_COLS; col++) {

                int index = row * Constants.BOARD_COLS + col;
                if (row % 2 == 1) {
                    index = row * Constants.BOARD_COLS + (Constants.BOARD_COLS - 1 - col);
                }

                Cell cell = cells[row][col];

                VBox cellBox = new VBox(4);
                cellBox.setAlignment(Pos.CENTER);
                cellBox.setPrefSize(90, 90);

                Label indexLabel = new Label(String.valueOf(index));
                indexLabel.setFont(new Font(11));

                Label typeLabel = new Label(cell.getName());
                typeLabel.setWrapText(true);
                typeLabel.setFont(new Font(9));

                String color = "#d9d9d9";

                if (cell instanceof DoorCell) {
                    DoorCell d = (DoorCell) cell;
                    color = d.getDoorRole() == Role.SCARER ? "#ffb3b3" : "#b3d9ff";

                    typeLabel.setText(
                            d.getDoorRole() + " Door\\nEnergy: " + d.getCanisterValue()
                    );
                }
                else if (cell instanceof CardCell) {
                    color = "#fff0a6";
                }
                else if (cell instanceof ConveyorBelt) {
                    color = "#c7ffd1";
                }
                else if (cell instanceof ContaminationSock) {
                    color = "#d6c4ff";
                }
                else if (cell instanceof MonsterCell) {
                    color = "#ffc48c";
                }

                cellBox.setStyle(
                        "-fx-background-color: " + color + ";" +
                        "-fx-border-color: black;"
                );

                if (cell.getMonster() != null) {
                    Circle marker = new Circle(10);
                    marker.setFill(
                            cell.getMonster() == game.getPlayer()
                                    ? Color.RED
                                    : Color.BLUE
                    );

                    Label monsterName = new Label(cell.getMonster().getName());

                    cellBox.getChildren().addAll(indexLabel, typeLabel, marker, monsterName);
                } else {
                    cellBox.getChildren().addAll(indexLabel, typeLabel);
                }

                boardGrid.add(cellBox, col, Constants.BOARD_ROWS - 1 - row);
            }
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("DoorDash");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
