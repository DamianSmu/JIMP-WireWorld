package Controller;

import Model.Automatons;
import Model.CellularAutomaton;
import Model.GameOfLifeCellType;
import Model.WireWorldCelltype;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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

    public void destroy()
    {
        timerTask.cancel();
        timer.cancel();
    }

    @FXML
    public void exitApplication(ActionEvent event) {


        Platform.exit();
    }
}
