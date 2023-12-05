package controller;

import dao.UserDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.User;

/**
 * FXML Controller Class
 *
 * @author AERO
 */
public class MainController implements Initializable {

    public static User user;

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPasswd;
    @FXML
    private RadioButton loginRadioBtn;
    @FXML
    private ToggleGroup access;
    @FXML
    private RadioButton registerRadioBtn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = null;
        btnLogin.setText("LOGIN");
    }

    @FXML
    @SuppressWarnings("CallToPrintStackTrace")
    private void handleButtonEvent(ActionEvent event) throws IOException {
        if (btnLogin.getText() == "LOGIN") {
            try {
                user = UserDAO.validate(txtUserName.getText(), txtPasswd.getText());
                if (user != null) {
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    URL url = new File("src/main/java/view/Home.fxml").toURI().toURL();
                    Parent root = FXMLLoader.load(url);
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } else {
                    JOptionPane.showMessageDialog(null, "INVALID USERNAME/PASSWORD!!!");
                }
            } catch (HeadlessException | IOException e) {
                e.printStackTrace();
            }
        } else {
            User u = new User(txtUserName.getText(), txtPasswd.getText());
            UserDAO.registerUser(u);
            JOptionPane.showMessageDialog(null, " Registered " + txtUserName.getText() + " Successfully. Please Login!");
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            URL url = new File("src/main/java/view/Home.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }

   
    @FXML
    private void chooseAccessType(ActionEvent event) {
        if (loginRadioBtn.isSelected()) {
            btnLogin.setText("LOGIN");
        } else if (registerRadioBtn.isSelected()) {
            btnLogin.setText("REGISTER AND LOGIN");
        }
    }
}
