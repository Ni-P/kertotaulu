import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML
    private Label PalauteLabel;

    @FXML
    private TextField VastausTextField;

    @FXML
    private Label KysymysLabel;

    @FXML
    private Button VastausButton;

    @FXML
    private Pane TopPane;

    @FXML
    void initialize() {
        assert PalauteLabel != null : "fx:id=\"PalauteLabel\" was not injected: check your FXML file 'gui.fxml'.";
        assert VastausTextField != null : "fx:id=\"VastausTextField\" was not injected: check your FXML file 'gui.fxml'.";
        assert KysymysLabel != null : "fx:id=\"KysymysLabel\" was not injected: check your FXML file 'gui.fxml'.";
        assert VastausButton != null : "fx:id=\"VastausButton\" was not injected: check your FXML file 'gui.fxml'.";
        assert TopPane != null : "fx:id=\"TopPane\" was not injected: check your FXML file 'gui.fxml'.";

    }

    private int _suurinLuku = 11;
    private ArrayList<Kysymys> _kysymykset;
    private Kysymys _kysymys;
    Timer delay;

    public Controller(Stage primaryStage) {


    }

    public void init() {
        VastausButton.setOnMouseClicked(this::Tarkista);
    }

    void Tarkista(MouseEvent event) {
        if (_kysymys == null) {
            Alusta();
            UusiKysymys();
            VastausButton.setText("Tarkista");
            PalauteLabel.setText("");
        }
        else {
            int vastaus;
            try {
                vastaus = Integer.parseInt(VastausTextField.getText());

            } catch (NumberFormatException e) {
                PalauteLabel.setText("Anna kokonnaisluku.");
                VastausTextField.setText("");
                VastausTextField.requestFocus();
                return;
            }
            if (_kysymys.get_result() == vastaus) {
                PalauteLabel.setText("Oikein!");
                delay = new Timer();
                delay.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(()-> UusiKysymys());

                    }
                },1000);
                //                try {
//                    Thread.currentThread().join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
            else {
                PalauteLabel.setText("Väärin, yritä uudestaan.");
                VastausTextField.setText("");
                VastausTextField.requestFocus();
            }
        }
    }

    private void Alusta(){
        _kysymykset = new ArrayList<>(_suurinLuku*10);
        for (int i = 1; i < _suurinLuku; i++) {
            for (int k = 1; k < 11; k++) {
                _kysymykset.add(new Kysymys(i,k));
            }
        }
    }

    private void UusiKysymys() {
        if (_kysymykset.size() > 0) {
            Random rng = new Random();
            _kysymys = _kysymykset.remove(rng.nextInt(_kysymykset.size()));
            KysymysLabel.setText(_kysymys.get_kysymys());
            VastausTextField.setText("");
            PalauteLabel.setText("");
            VastausTextField.requestFocus();
        }
        else {
            _kysymys = null;
            KysymysLabel.setText("Kaikki vastattu!");
            PalauteLabel.setText("Aloitetaanko alusta?");
            VastausButton.setText("Aloita");
        }
//        if (delay != null) {
//            delay.purge();
//        }
    }

}
