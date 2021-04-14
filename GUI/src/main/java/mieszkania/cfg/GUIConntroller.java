package mieszkania.cfg;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mieszkania.database.*;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUIConntroller implements Initializable {

    //klienci
    @FXML
    private TableView<klienci> tab1;
    @FXML
    private TableColumn<klienci, String> kId;
    @FXML
    private TableColumn<klienci, String> kImie;
    @FXML
    private TableColumn<klienci, String> kNazwisko;
    @FXML
    private TableColumn<klienci, String> kPesel;
    @FXML
    private TableColumn<klienci, String> kNr;

    @FXML
    private TextField getKI;
    @FXML
    private TextField getKN;
    @FXML
    private TextField getKP;
    @FXML
    private TextField getKNT;
    @FXML
    private Button addK;

    @FXML
    private ComboBox selectDelK;
    @FXML
    private Button delK;

    //miasta
    @FXML
    private TableView<miasta> tab2;
    @FXML
    private TableColumn<miasta, String> miId;
    @FXML
    private TableColumn<miasta, String> miM;
    @FXML
    private TableColumn<miasta, String> miK;

    @FXML
    private TextField getMiN;
    @FXML
    private TextField getMiK;
    @FXML
    private Button addMi;

    @FXML
    private ComboBox selectDelMi;
    @FXML
    private Button delMi;

    //mieszkania
    @FXML
    private TableView<mieszkania> tab3;
    @FXML
    private TableColumn<mieszkania, String> mieId;
    @FXML
    private TableColumn<mieszkania, String> mieM;
    @FXML
    private TableColumn<mieszkania, String> mieU;
    @FXML
    private TableColumn<mieszkania, String> mieNB;
    @FXML
    private TableColumn<mieszkania, String> mieNM;

    @FXML
    private ComboBox<String> selectMieMi;
    @FXML
    private TextField getMieU;
    @FXML
    private TextField getMieNB;
    @FXML
    private TextField getMieNM;
    @FXML
    private Button addMie;

    @FXML
    private ComboBox selectDelMie;
    @FXML
    private Button delMie;


    //pracownicy
    @FXML
    private TableView<pracownicy> tab4;
    @FXML
    private TableColumn<pracownicy, String> pId;
    @FXML
    private TableColumn<pracownicy, String> pI;
    @FXML
    private TableColumn<pracownicy, String> pN;
    @FXML
    private TableColumn<pracownicy, String> pD;

    @FXML
    private TextField getPI;
    @FXML
    private TextField getPN;
    @FXML
    private TextField getPDZ;
    @FXML
    private Button addP;

    @FXML
    private ComboBox selectDelP;
    @FXML
    private Button delP;

    //wynajem
    @FXML
    private TableView<wynajem> tab5;
    @FXML
    private TableColumn<wynajem, String> wId;
    @FXML
    private TableColumn<wynajem, String> wKIN;
    @FXML
    private TableColumn<wynajem, String> wPIN;
    @FXML
    private TableColumn<wynajem, String> wM;

    @FXML
    private ComboBox<String> selectWK;
    @FXML
    private ComboBox<String> selectWP;
    @FXML
    private ComboBox<String> selectWM;
    @FXML
    private Button addW;

    @FXML
    private ComboBox selectDelW;
    @FXML
    private Button delW;

    //łączenie oraz tworzenie list
    private Connection con;
    private ObservableList<klienci> kList;
    private ObservableList<miasta> miList;
    private ObservableList<mieszkania> mieList;
    private ObservableList<pracownicy> pList;
    private ObservableList<wynajem> wList;
    private DbConnect dbConnect;

    //listy pomocnicze
    ArrayList listMie = new ArrayList();
    ArrayList listK = new ArrayList();
    ArrayList listP = new ArrayList();
    ArrayList listM = new ArrayList();

    ArrayList listDelK = new ArrayList();
    ArrayList listDelMi = new ArrayList();
    ArrayList listDelMie = new ArrayList();
    ArrayList listDelP = new ArrayList();
    ArrayList listDelW = new ArrayList();

    public GUIConntroller() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        dbConnect = new DbConnect();

        wywolaj();
    }

    //działania na klientach
    private void klienciTableView(){
        try{
            kList = FXCollections.observableArrayList();
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getAllCustomers(?)}");//procedura z sql developera
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);

            while (rs.next()){
                klienci k = new klienci();
                k.setIdKlienta(rs.getInt("idKlienta"));
                k.setImie(rs.getString("imie"));
                k.setNazwisko(rs.getString("nazwisko"));
                k.setPesel(rs.getString("pesel"));
                k.setNrTelefonu(rs.getString("nrTelefonu"));
                kList.add(k);
            }

            kId.setCellValueFactory(new PropertyValueFactory<klienci, String>("idKlienta"));
            kImie.setCellValueFactory(new PropertyValueFactory<klienci, String>("imie"));
            kNazwisko.setCellValueFactory(new PropertyValueFactory<klienci, String>("nazwisko"));
            kPesel.setCellValueFactory(new PropertyValueFactory<klienci, String>("pesel"));
            kNr.setCellValueFactory(new PropertyValueFactory<klienci, String>("nrTelefonu"));
            tab1.setItems(kList);

        } catch (SQLException e){
            Logger.getLogger(GUIConntroller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void addK(ActionEvent event) throws ClassNotFoundException, SQLException{
        String imie,nazwisko,pesel,nr;
        imie = getKI.getText();
        nazwisko = getKN.getText();
        pesel = getKP.getText();
        nr = getKNT.getText();
        con = dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_addCustomers(?,?,?,?)}");
        st.setString(1,imie);
        st.setString(2,nazwisko);
        st.setString(3, pesel);
        st.setString(4, nr);
        st.execute();
        wywolaj();
        getKI.clear();
        getKN.clear();
        getKP.clear();
        getKNT.clear();
    }

    private void selectDelK(){
        try {
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getDelCustomer(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);
            while (rs.next()){
                selectDelK.getItems().add(rs.getString("imie")+" "+rs.getString("nazwisko")+" "+rs.getString("pesel"));
                listDelK.add(rs.getInt("idKlienta"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void delK(ActionEvent event) throws ClassNotFoundException, SQLException{
        int id= (int) listDelK.get(selectDelK.getSelectionModel().getSelectedIndex());
        con=dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_delCustomer(?)}");
        st.setInt(1,id);
        st.execute();
        wywolaj();

    }

    //działania na miastach
    private void miastaTableView(){
        try{
            miList = FXCollections.observableArrayList();
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getAllCities(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);

            while (rs.next()){
                miasta mi= new miasta();
                mi.setIdMiasta(rs.getInt("idMiasta"));
                mi.setMiasto(rs.getString("miasto"));
                mi.setKodPocztowy(rs.getString("kodPocztowy"));
                miList.add(mi);
            }

            miId.setCellValueFactory(new PropertyValueFactory<miasta, String>("idMiasta"));
            miM.setCellValueFactory(new PropertyValueFactory<miasta, String>("miasto"));
            miK.setCellValueFactory(new PropertyValueFactory<miasta, String>("kodPocztowy"));
            tab2.setItems(miList);

        } catch (SQLException e){
            Logger.getLogger(GUIConntroller.class.getName()).log(Level.SEVERE, null ,e);
        }
    }


    @FXML
    private void addMi(ActionEvent event) throws ClassNotFoundException, SQLException{
            String miasto, kod;
            miasto = getMiN.getText();
            kod = getMiK.getText();
            con = dbConnect.getConnection();
            CallableStatement st = con.prepareCall("{CALL P_addCities(?,?)}");
            st.setString(1, miasto);
            st.setString(2, kod);
            st.execute();
            wywolaj();
            getMiK.clear();
            getMiN.clear();
    }

    private void selectDelMi(){
        try {
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getDelCity(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);
            while (rs.next()){
                selectDelMi.getItems().add(rs.getString("miasto"));
                listDelMi.add(rs.getInt("idMiasta"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void delMi(ActionEvent event) throws ClassNotFoundException, SQLException{
        int id= (int) listDelMi.get(selectDelMi.getSelectionModel().getSelectedIndex());
        con=dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_delCity(?)}");
        st.setInt(1,id);
        st.execute();
        wywolaj();

    }

    //działania na mieszkaniach
    private void mieszkaniaTableView(){
        try{
            mieList = FXCollections.observableArrayList();
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getAllFlats(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);

            while (rs.next()){
                mieszkania mie = new mieszkania();
                mie.setIdMieszkania(rs.getInt("idMieszkania"));
                mie.setIdMiasta(rs.getString("idMiasta"));
                mie.setMiasto(rs.getString("miasto"));
                mie.setUlica(rs.getString("ulica"));
                mie.setNumerBudynku(rs.getString("numerBudynku"));
                mie.setNumerMieszkania(rs.getString("numerMieszkania"));
                mieList.add(mie);
            }

            mieId.setCellValueFactory(new PropertyValueFactory<mieszkania, String>("idMieszkania"));
            mieM.setCellValueFactory(new PropertyValueFactory<mieszkania, String>("miasto"));
            mieU.setCellValueFactory(new PropertyValueFactory<mieszkania, String>("ulica"));
            mieNB.setCellValueFactory(new PropertyValueFactory<mieszkania, String>("numerBudynku"));
            mieNM.setCellValueFactory(new PropertyValueFactory<mieszkania, String>("numerMieszkania"));
            tab3.setItems(mieList);

        } catch (SQLException e){
            Logger.getLogger(GUIConntroller.class.getName()).log(Level.SEVERE, null ,e);
        }
    }

    private void selectAddMieMi(){
        try {
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getAllCities(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);
            while (rs.next()){
                selectMieMi.getItems().add(rs.getString("miasto")+" "+rs.getString("kodpocztowy"));
                listMie.add(rs.getInt("idMiasta"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void addMie(ActionEvent event) throws ClassNotFoundException, SQLException{
        int idMie;
        String ulica,NB,NM;
        idMie= (int) listMie.get(selectMieMi.getSelectionModel().getSelectedIndex());
        ulica=getMieU.getText();
        NB=getMieNB.getText();
        NM=getMieNM.getText();
        con = dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_addFlats(?,?,?,?)}");
        st.setInt(1,idMie);
        st.setString(2,ulica);
        st.setString(3,NB);
        st.setString(4,NM);
        st.execute();
        wywolaj();
        getMieU.clear();
        getMieNB.clear();
        getMieNM.clear();
    }

    private void selectDelMie(){
        try {
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getDelFlat(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);
            while (rs.next()){
                selectDelMie.getItems().add(rs.getString("miasto")+" ul."+rs.getString("ulica")+" "+rs.getString("numerBudynku")+" "+rs.getString("numerMieszkania"));
                listDelMie.add(rs.getInt("idMieszkania"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void delMie(ActionEvent event) throws ClassNotFoundException, SQLException{
        int id= (int) listDelMie.get(selectDelMie.getSelectionModel().getSelectedIndex());
        con=dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_delFlat(?)}");
        st.setInt(1,id);
        st.execute();
        wywolaj();

    }

    //działania na pracownikach
    private void pracownicyTableView(){
        try{
            pList = FXCollections.observableArrayList();
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getAllEmployees(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);

            while (rs.next()){
                pracownicy p = new pracownicy();
                p.setIdPracownika(rs.getInt("idPracownika"));
                p.setImie(rs.getString("imie"));
                p.setNazwisko(rs.getString("nazwisko"));
                p.setDataZatrudnienia(rs.getString("dataZatrudnienia"));
                pList.add(p);
            }

            pId.setCellValueFactory(new PropertyValueFactory<pracownicy, String>("idPracownika"));
            pI.setCellValueFactory(new PropertyValueFactory<pracownicy, String>("imie"));
            pN.setCellValueFactory(new PropertyValueFactory<pracownicy, String>("nazwisko"));
            pD.setCellValueFactory(new PropertyValueFactory<pracownicy, String>("dataZatrudnienia"));
            tab4.setItems(pList);

        } catch (SQLException e){
            Logger.getLogger(GUIConntroller.class.getName()).log(Level.SEVERE, null ,e);
        }
    }

    @FXML
    public void addP(ActionEvent event) throws SQLException {
        String imie,nazwisko,data;
        imie=getPI.getText();
        nazwisko=getPN.getText();
        data=getPDZ.getText();
        con = dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_addEmployees(?,?,?)}");
        st.setString(1,imie);
        st.setString(2,nazwisko);
        st.setString(3,data);
        st.execute();
        getPI.clear();
        getPN.clear();
        getPDZ.clear();
        wywolaj();
    }

    private void selectDelP(){
        try {
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getDelEmployee(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);
            while (rs.next()){
                selectDelP.getItems().add(rs.getString("imie")+" "+rs.getString("nazwisko"));
                listDelP.add(rs.getInt("idPracownika"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void delP(ActionEvent event) throws ClassNotFoundException, SQLException{
        int id= (int) listDelP.get(selectDelP.getSelectionModel().getSelectedIndex());
        con=dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_delEmployee(?)}");
        st.setInt(1,id);
        st.execute();
        wywolaj();

    }

    //działania na wynajmie
    private void wynajemTableView(){
        try{
            wList = FXCollections.observableArrayList();
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getAllRents(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);

            while (rs.next()){
                wynajem w = new wynajem();
                w.setIdWynajmu(rs.getInt("idWynajmu"));
                w.setIdKlienta(rs.getString("idKlienta"));
                w.setK(rs.getString("k"));
                w.setIdPracownika(rs.getString("idPracownika"));
                w.setP(rs.getString("p"));
                w.setIdMieszkania(rs.getString("idMieszkania"));
                w.setMieszkanie(rs.getString("mieszkanie"));
                w.setIdMiasta(rs.getString("idMiasta"));
                wList.add(w);
            }

            wId.setCellValueFactory(new PropertyValueFactory<wynajem, String>("idWynajmu"));
            wKIN.setCellValueFactory(new PropertyValueFactory<wynajem, String>("k"));
            wPIN.setCellValueFactory(new PropertyValueFactory<wynajem, String>("p"));
            wM.setCellValueFactory(new PropertyValueFactory<wynajem, String>("mieszkanie"));
            tab5.setItems(wList);

        } catch (SQLException e){
            Logger.getLogger(GUIConntroller.class.getName()).log(Level.SEVERE, null ,e);
        }
    }

    private void selectAddW(){
        try {
            con=dbConnect.getConnection();

            //klienci
            CallableStatement pstmt = con.prepareCall("{call P_getAllCustomers(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);
            while (rs.next()){
                selectWK.getItems().add(rs.getString("imie")+" "+rs.getString("nazwisko"));
                listK.add(rs.getInt("idKlienta"));
            }

            //pracownicy
            CallableStatement pstmt2 = con.prepareCall("{call P_getAllEmployees(?)}");
            pstmt2.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt2.execute();
            ResultSet rs2 = ((OracleCallableStatement) pstmt2).getCursor(1);
            while (rs2.next()){
                selectWP.getItems().add(rs2.getString("imie")+" "+rs2.getString("nazwisko"));
                listP.add(rs2.getInt("idPracownika"));
            }

            //mieszkania
            CallableStatement pstmt3 = con.prepareCall("{call P_getRentFlats(?)}");
            pstmt3.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt3.execute();
            ResultSet rs3 = ((OracleCallableStatement) pstmt3).getCursor(1);
            while (rs3.next()){
                selectWM.getItems().add(rs3.getString("miasto")+" ul."+rs3.getString("ulica")+" "+rs3.getString("numerBudynku")+"/"+rs3.getString("numerMieszkania"));
                listM.add(rs3.getInt("idMieszkania"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void addW(ActionEvent event) throws ClassNotFoundException, SQLException{
        int idK,idP,idM;
        idK = (int) listK.get(selectWK.getSelectionModel().getSelectedIndex());
        idP = (int) listP.get(selectWP.getSelectionModel().getSelectedIndex());
        idM = (int) listM.get(selectWM.getSelectionModel().getSelectedIndex());
        con = dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_addRents(?,?,?)}");
        st.setInt(1,idK);
        st.setInt(2,idP);
        st.setInt(3,idM);
        st.execute();
        wywolaj();
    }

    private void selectDelW(){
        try {
            con=dbConnect.getConnection();
            CallableStatement pstmt = con.prepareCall("{call P_getAllRents(?)}");
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);
            pstmt.execute();
            ResultSet rs = ((OracleCallableStatement) pstmt).getCursor(1);
            while (rs.next()){
                selectDelW.getItems().add(rs.getString("k")+" "+rs.getString("mieszkanie"));
                listDelW.add(rs.getInt("idWynajmu"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void delW(ActionEvent event) throws ClassNotFoundException, SQLException{
        int id= (int) listDelW.get(selectDelW.getSelectionModel().getSelectedIndex());
        con=dbConnect.getConnection();
        CallableStatement st = con.prepareCall("{CALL P_delRent(?)}");
        st.setInt(1,id);
        st.execute();
        wywolaj();

    }


    //wywołania funkcji

    public void wywolaj(){
        czysc();
        klienciTableView();
        miastaTableView();
        mieszkaniaTableView();
        pracownicyTableView();
        wynajemTableView();
        selectAddMieMi();
        selectAddW();
        selectDelK();
        selectDelMi();
        selectDelMie();
        selectDelP();
        selectDelW();
    }

    //czyszczenie comboboxów oraz list
    private void czysc(){
        selectMieMi.getItems().clear();
        selectWM.getItems().clear();
        selectWK.getItems().clear();
        selectWP.getItems().clear();
        selectDelK.getItems().clear();
        selectDelMi.getItems().clear();
        selectDelMie.getItems().clear();
        selectDelP.getItems().clear();
        selectDelW.getItems().clear();
        listP.clear();
        listM.clear();
        listK.clear();
        listMie.clear();
        listDelK.clear();
        listDelMi.clear();
        listDelP.clear();
        listDelMie.clear();
        listDelW.clear();
    }

}
