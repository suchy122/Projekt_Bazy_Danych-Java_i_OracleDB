package mieszkania.database;

import javafx.fxml.FXML;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class wynajem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idWynajmu")
    private int idWynajmu;
    @Column(name="idKlienta")
    private String idKlienta;
    @Column(name = "k")
    private String k;
    @Column(name="idPracownika")
    private String idPracownika;
    @Column(name = "p")
    private String p;
    @Column(name= "idMieszkania")
    private String idMieszkania;
    @Column(name = "mieszkanie")
    private String mieszkanie;
    @Column(name="idMiasta")
    private String idMiasta;


    public wynajem() {
    }

    public wynajem(int idWynajmu, String idKlienta, String k, String idPracownika, String p, String idMieszkania, String mieszkanie, String idMiasta) {
        this.idWynajmu = idWynajmu;
        this.idKlienta = idKlienta;
        this.k = k;
        this.idPracownika = idPracownika;
        this.p = p;
        this.idMieszkania = idMieszkania;
        this.mieszkanie = mieszkanie;
        this.idMiasta = idMiasta;
    }

    public int getIdWynajmu() {
        return idWynajmu;
    }

    public void setIdWynajmu(int idWynajmu) {
        this.idWynajmu = idWynajmu;
    }

    public String getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(String idKlienta) {
        this.idKlienta = idKlienta;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(String idPracownika) {
        this.idPracownika = idPracownika;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getIdMieszkania() {
        return idMieszkania;
    }

    public void setIdMieszkania(String idMieszkania) {
        this.idMieszkania = idMieszkania;
    }

    public String getMieszkanie() {
        return mieszkanie;
    }

    public void setMieszkanie(String mieszkanie) {
        this.mieszkanie = mieszkanie;
    }

    public String getIdMiasta() {
        return idMiasta;
    }

    public void setIdMiasta(String idMiasta) {
        this.idMiasta = idMiasta;
    }

    @Override
    public String toString() {
        return "wynajem{" +
                "idWynajmu=" + idWynajmu +
                ", idKlienta='" + idKlienta + '\'' +
                ", k='" + k + '\'' +
                ", idPracownika='" + idPracownika + '\'' +
                ", p='" + p + '\'' +
                ", idMieszkania='" + idMieszkania + '\'' +
                ", mieszkanie='" + mieszkanie + '\'' +
                ", idMiasta='" + idMiasta + '\'' +
                '}';
    }
}
