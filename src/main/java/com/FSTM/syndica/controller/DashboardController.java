package com.FSTM.syndica.controller;

import com.FSTM.syndica.DAL.CorproprieteDal;
import com.FSTM.syndica.DAL.PDFProvider;
import com.FSTM.syndica.DAL.ReunionDal;
import com.FSTM.syndica.Model.Corpropriete;
import com.FSTM.syndica.Model.Reunion;
import com.FSTM.syndica.Model.Syndic;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class DashboardController implements Initializable {
    public Syndic currentUser;
    public Reunion currentReunion;
    public ReunionDal reunionDal = new ReunionDal();
    public List<Reunion> reunionList;
    Random random = new Random();
    List<Corpropriete> corproprietes ;
    CorproprieteDal corproprieteDal = new CorproprieteDal();

    @FXML
    private ChoiceBox<Integer> Corpropriete;
    @FXML
    private DatePicker DateReunion;

    @FXML
    private TextArea ReunionAgenda;
    @FXML
    private TextField ReunionTitle;

    @FXML
    private Button imprimer_invitation;
    @FXML
    private TableView<Reunion> addEmployee_tableView;
    @FXML
    private TableColumn<?, ?> ReunionDateColumn;

    @FXML
    private TableColumn<?, ?> ReunionFinalDecisionColumn;

    @FXML
    private TableColumn<?, ?> ReunionIdColumn;

    @FXML
    private TableColumn<?, ?> ReunionReportColumn;

    @FXML
    private TableColumn<?, ?> ReunionTitleColumn;

    @FXML
    private Button Reunion_btn;

    @FXML
    private TextField Reunion_search;

    @FXML
    private AnchorPane addEmployee_form;



    @FXML
    private Button addReunion_addBtn;

    @FXML
    private Button addReunion_clearBtn;

    @FXML
    private Button addReunion_deleteBtn;

    @FXML
    private Button addReunion_updateBtn;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Label username;
    @FXML
    void AddReunion(ActionEvent event) {
        Reunion reunion = new Reunion();
        reunion.setSyndic_id(this.currentUser.id);
        LocalDate localDate = DateReunion.getValue();
        reunion.setId(random.nextInt(0, 700));
        reunion.setDate(localDate);
        reunion.setTitre(ReunionTitle.getText());
        reunion.setOrdre(ReunionAgenda.getText());
        reunion.setCorpropriete_id(1);
        reunionList.add(reunion);
        reunionDal.createReunion(reunion);
        ClearInputs(null);
        refreshTable();
    }
    @FXML
    void Exite(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void ClearInputs(ActionEvent event) {
        ReunionAgenda.setText(null);
        ReunionTitle.setText(null);
        // TODO: Set DateReunion to empty
    }

    @FXML
    void DeleteReunion(ActionEvent event) {
        if (currentReunion != null){
            reunionDal.deleteReunion(currentReunion.id);
            ClearInputs(null);
            refreshTable();
        }
    }

    @FXML
    void UpdateReunion(ActionEvent event) {
        if (currentReunion != null){
            LocalDate localDate = DateReunion.getValue();
            currentReunion.setDate(localDate);
            currentReunion.setTitre(ReunionTitle.getText());
            currentReunion.setOrdre(ReunionAgenda.getText());
            currentReunion.setCorpropriete_id(1);
            reunionDal.updateReunion(currentReunion);
            ClearInputs(null);
            refreshTable();
        }

    }
    @FXML
    void minimize(ActionEvent event) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized table meeting");
        addEmployee_tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Call a method or perform any action with the selected item
                handleRowSelection(newSelection);
            }
        });
    }

    private void handleRowSelection(Reunion newSelection) {
        currentReunion = newSelection;
        Corpropriete.setValue(newSelection.getCorpropriete_id());
        DateReunion.setValue(newSelection.date);
        ReunionAgenda.setText(newSelection.ordre);
        ReunionTitle.setText(newSelection.titre);
    }

    public void InitUser(Syndic syndic) {
        this.currentUser = syndic;
        username.setText(syndic.getPrenom() +" "+ syndic.getNom());
        initListMeuble(syndic);
        initTable(syndic);

    }
    private void refreshTable(){
        reunionList = reunionDal.selectReunionsBySyndicId(currentUser.id);
        addEmployee_tableView.setItems(FXCollections.observableArrayList(reunionList));
        addEmployee_tableView.refresh();
    }
    private void initTable(Syndic syndic) {
        reunionList = reunionDal.selectReunionsBySyndicId(syndic.id);
        addEmployee_tableView.setItems(FXCollections.observableArrayList(reunionList));

        ReunionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ReunionDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        ReunionTitleColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        ReunionReportColumn.setCellValueFactory(new PropertyValueFactory<>("ordre"));
        ReunionFinalDecisionColumn.setCellValueFactory(new PropertyValueFactory<>("decission_final"));
    }

    private void initListMeuble(Syndic syndic) {
        corproprietes = corproprieteDal.getCorproprietesBySyndicId(syndic.id);
        List<Integer> idList = corproprietes.stream()
                .map(t->{
                    return t.getId();
                })
                .collect(Collectors.toList());
        Corpropriete.setItems(FXCollections.observableArrayList(idList));
    }

    @FXML
    void print(ActionEvent event) throws DocumentException {
        PDFProvider pdfProvider = new PDFProvider();
        pdfProvider.generate(currentReunion);
    }
}

