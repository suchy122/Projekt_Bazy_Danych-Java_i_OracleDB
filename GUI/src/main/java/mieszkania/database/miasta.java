package mieszkania.database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class miasta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMiasta")
    private int idMiasta;
    @Column(name = "miasto")
    private String miasto;
    @Column(name = "kodPocztowy")
    private String kodPocztowy;

    public miasta() {
    }

    public miasta(int idMiasta, String miasto, String kodPocztowy) {
        this.idMiasta = idMiasta;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
    }

    public int getIdMiasta() {
        return idMiasta;
    }

    public void setIdMiasta(int idMiasta) {
        this.idMiasta = idMiasta;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    @Override
    public String toString() {
        return "miasta{" +
                "idMiasta=" + idMiasta +
                ", miasto='" + miasto + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                '}';
    }

}
