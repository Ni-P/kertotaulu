import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

    private int suurinLuku = 11;
    private ArrayList<Kysymys> kysymykset;
    private Kysymys kysymys;
    Timer delay;

    public Controller(Stage primaryStage) {

        initialize();
    }

    public void init() {
        VastausButton.setOnMouseClicked(this::tarkistaVastaus);
        TopPane.setOnKeyPressed(this::keyPressHandler);
    }

    void keyPressHandler(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            tarkistaVastaus(null);
        }
        else if(event.getCode() == KeyCode.ESCAPE) {
            System.exit(0);
        }
    }

    void tarkistaVastaus(MouseEvent event) {
        if (kysymys == null) {
            alusta();
            uusiKysymys();
            VastausButton.setText("tarkistaVastaus");
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
            if (kysymys.getOikeaVastaus() == vastaus) {
                PalauteLabel.setText("Oikein!");
                delay = new Timer();
                delay.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(()-> uusiKysymys());

                    }
                },1000);
            }
            else {
                PalauteLabel.setText("Väärin, yritä uudestaan.");
                VastausTextField.setText("");
                VastausTextField.requestFocus();
            }
        }
    }

    private void alusta(){
        kysymykset = new ArrayList<>(suurinLuku *10);
        for (int i = 1; i < suurinLuku; i++) {
            for (int k = 1; k < 11; k++) {
                kysymykset.add(new Kysymys(i,k));
            }
        }
    }

    private void uusiKysymys() {
        if (kysymykset.size() > 0) {
            Random rng = new Random();
            kysymys = kysymykset.remove(rng.nextInt(kysymykset.size()));
            KysymysLabel.setText(kysymys.getKysymys());
            VastausTextField.setText("");
            PalauteLabel.setText("");
            VastausTextField.requestFocus();
        }
        else {
            kysymys = null;
            KysymysLabel.setText("Kaikki vastattu!");
            PalauteLabel.setText("Aloitetaanko alusta?");
            VastausButton.setText("Aloita");
        }
//        if (delay != null) {
//            delay.purge();
//        }
    }

}
