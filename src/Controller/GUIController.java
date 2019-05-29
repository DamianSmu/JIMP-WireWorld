package Controller;

import Model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class GUIController
{

    public AnchorPane anchorPane;
    public Slider speedSlider;
    public ToggleButton wireWorldBtn;
    public ToggleButton gameOfLifeBtn;
    public Button startBtn;
    public Button stopBtn;
    public MenuItem saveBtn;

    public ToggleGroup typePickerWireWorld;
    public ToggleGroup typePickerGameOfLife;
    public ToggleGroup toggleGroup;

    private CellularAutomaton automaton;
    private Timer timer;
    private TimerTask timerTask;
    private boolean timerPaused;

    @FXML
    public void initialize()
    {

        speedSlider.setMin(1d);
        speedSlider.setMax(50d);
        speedSlider.setBlockIncrement(1d);

        speedSlider.valueProperty().addListener((observableValue, number, t1) -> {
            timerTask.cancel();
            startTimer();
        });

        gameOfLifeBtnClicked();

        typePickerWireWorld.getToggles().forEach(toggle -> {
            ((RadioButton) toggle).setDisable(true);
        });

        typePickerWireWorld.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if (typePickerWireWorld.getSelectedToggle() != null)
            {
                RadioButton selected = (RadioButton) typePickerWireWorld.getSelectedToggle();
                String type = selected.getText();
                switch (type)
                {
                    case "empty":
                        automaton.setPickedType(WireWorldCelltype.EMPTY);
                        break;
                    case "conductor":
                        automaton.setPickedType(WireWorldCelltype.CONDUCTOR);
                        break;
                    case "head":
                        automaton.setPickedType(WireWorldCelltype.HEAD);
                        break;
                    case "tail":
                        automaton.setPickedType(WireWorldCelltype.TAIL);
                        break;
                }
            }
        });

        typePickerGameOfLife.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            if (typePickerGameOfLife.getSelectedToggle() != null)
            {
                RadioButton selected = (RadioButton) typePickerGameOfLife.getSelectedToggle();
                String type = selected.getText();
                switch (type)
                {
                    case "dead":
                        automaton.setPickedType(GameOfLifeCellType.DEAD);
                        break;
                    case "alive":
                        automaton.setPickedType(GameOfLifeCellType.ALIVE);
                        break;
                }
            }
        });


        toggleGroup.selectedToggleProperty().addListener((observableValue, toggle, t1) -> {
            anchorPane.getChildren().removeAll(automaton.getRectangles());
            timerTask.cancel();
        });
    }

    @FXML
    void startBtnClicked()
    {
        timerPaused = false;
    }

    @FXML
    void stopBtnClicked()
    {
        timerPaused = true;
    }

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                if (!timerPaused)
                {
                    automaton.nextStep();
                    automaton.draw();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, (int) (1d / speedSlider.getValue() * 1000), (int) (1d / speedSlider.getValue() * 1000));
    }

    @FXML
    public void wireWorldBtnClicked()
    {
        typePickerWireWorld.getToggles().forEach(toggle -> {
            ((RadioButton) toggle).setDisable(false);
        });
        typePickerGameOfLife.getToggles().forEach(toggle -> {
            ((RadioButton) toggle).setDisable(true);
        });

        automaton = Automatons.createWireWorldAutomaton();
        anchorPane.getChildren().addAll(automaton.getRectangles());

        timerPaused = true;
        timer = new Timer();
        startTimer();
    }

    @FXML
    public void gameOfLifeBtnClicked()
    {
        typePickerGameOfLife.getToggles().forEach(toggle -> {
            ((RadioButton) toggle).setDisable(false);
        });
        typePickerWireWorld.getToggles().forEach(toggle -> {
            ((RadioButton) toggle).setDisable(true);
        });

        automaton = Automatons.createGameOfLifeAutomaton();
        anchorPane.getChildren().addAll(automaton.getRectangles());

        timerPaused = true;
        timer = new Timer();
        startTimer();
    }

    @FXML
    public void saveBtnClicked(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(saveBtn.getParentPopup().getOwnerWindow());
        if (file != null) {
            try {
                CellType[][] cells = new CellType[automaton.getBoard().length][(automaton.getBoard())[0].length];
                for (int i = 0; i<automaton.getBoard().length;i++)
                    for (int j=0;j<(automaton.getBoard())[0].length;j++)
                        cells[i][j]=(automaton.getBoard())[i][j].getType();
                Controller.FileIO.saveToFile(file, cells , automaton.getRuleSet() );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void destroy()
    {
        timerTask.cancel();
        timer.purge();
        timer.cancel();
    }
}
