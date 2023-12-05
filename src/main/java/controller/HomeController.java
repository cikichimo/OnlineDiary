package controller;

import model.Story;
import dao.StoryDAO;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AERO
 */
public class HomeController implements Initializable {

    boolean update = false;
    Story updt = new Story();

    @FXML
    private DatePicker dtDate;
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Story> tableView;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblEntry;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAdd.setText("CREATE");
        enableOption(false);
        fillTable();
    }

    @FXML
    @SuppressWarnings("CallToPrintStackTrace")
    private void handleButtonEvent(ActionEvent event) {
        if (event.getSource() == btnBack) {
            try {
                Stage stage = (Stage) btnBack.getScene().getWindow();
                URL url = new File("src/main/java/view/Main.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getSource() == btnAdd) {
            Story s = new Story(
                    Date.valueOf(dtDate.getValue()),
                    txtDescription.getText(),
                    MainController.user);

            if (update == false) {
                StoryDAO.insertEntry(s);
            } else {
                StoryDAO.updateEntry(s, updt);
                updateMode(false);
            }
            this.txtDescription.clear();
            this.dtDate.getEditor().clear();
            fillTable();
        }

        if (event.getSource() == btnDelete) {
            try {
                Story s = tableView.getSelectionModel().getSelectedItem();
                StoryDAO.removeEntry(s);
                fillTable();
            } catch (HeadlessException e) {
                e.printStackTrace();
            }

        }
        if (event.getSource() == btnUpdate) {
            try {
                update = true;
                updt =  tableView.getSelectionModel().getSelectedItem();
                txtDescription.setText(updt.getDescrip());
               
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate =  LocalDate.parse(updt.getSdate().toString(), format);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
                localDate.format(formatter);
                dtDate.setValue(localDate);
                btnAdd.setText("DONE");
                updateMode(true);
            } catch (HeadlessException e) {
                e.printStackTrace();
            }

        }
    }

    public void enableOption(boolean b) {
        btnDelete.setDisable(!b);
        btnUpdate.setDisable(!b);
    }

    public void updateMode(boolean b) {
        btnDelete.setDisable(b);
        tableView.setDisable(b);
        btnBack.setDisable(b);
        lblEntry.setText("Update Entry");
        btnUpdate.setDisable(b);
    }

    private void fillTable() {
        LinkedList<Story> res = StoryDAO.getAll(MainController.user);
        
        tableView.getColumns().clear();
        tableView.getItems().clear();
        
        //tableView menyimpan objek story
        //Tapi di sini kita hanya akan menampilkan
        //2 atribut dari objek story,
        //yaitu sdate dan descrip seperti yang dilakukan di baris berikut
        //sebutkan nama kolom yang ingin muncul di tampilan tabel
        //kemudian sebutkan nama atribut dari objek story yang akan digunakan untu
        //mengisi kolom-kolom tsbt
        TableColumn<Story, Date> cl1 = new TableColumn<>("Story Date");
        cl1.setCellValueFactory(new PropertyValueFactory<>("sdate"));
        TableColumn<Story, String> cl2 = new TableColumn<>("Story Description");
        
        cl2.setCellValueFactory(new PropertyValueFactory<>("descrip"));// Add two columns into TableView
        cl1.setPrefWidth(200);
        cl2.setPrefWidth(650);
        tableView.getColumns().add(cl1);
        tableView.getColumns().add(cl2);

        // Add data to table
        for (Story story : res) {
            tableView.getItems().add(story);
        }
        enableOption(false);
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
    }

    private void keyRelelease(KeyEvent event) {
        fillTable();
    }

    public static String getSelColValue(String col, TableView tableView) {
        String res = null;
        try {
            int rowIndex = tableView.getSelectionModel().getSelectedIndex();
            TableColumn column = getTableColumnByName(tableView, col);
            if (column != null && column.getCellData(rowIndex) != null) {
                res = column.getCellData(rowIndex).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static <T> TableColumn<T, ?> getTableColumnByName(TableView<T> tableView, String name) {
        for (TableColumn<T, ?> col : tableView.getColumns()) {
            if (col.getText().equalsIgnoreCase(name)) {
                return col;
            }
        }
        return null;
    }
}
