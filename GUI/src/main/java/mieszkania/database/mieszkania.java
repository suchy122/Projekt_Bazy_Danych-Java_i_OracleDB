package mieszkania.database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class mieszkania{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMieszkania")
    private int idMieszkania;
    @Column(name="idMiasta")
    private String idMiasta;
    @Column(name="miasto")
    private String miasto;
    @Column(name="ulica")
    private String ulica;
    @Column(name="numerBudynku")
    private String numerBudynku;
    @Column(name="numerMieszkania")
    private String numerMieszkania;

    public mieszkania() {
    }

    public mieszkania(int idMieszkania, String idMiasta, String miasto, String ulica, String numerBudynku, String numerMieszkania) {
        this.idMieszkania = idMieszkania;
        this.idMiasta = idMiasta;
        this.miasto = miasto;
        this.ulica = ulica;
        this.numerBudynku = numerBudynku;
        this.numerMieszkania = numerMieszkania;
    }

    public int getIdMieszkania() {
        return idMieszkania;
    }

    public void setIdMieszkania(int idMieszkania) {
        this.idMieszkania = idMieszkania;
    }

    public String getIdMiasta() {
        return idMiasta;
    }

    public void setIdMiasta(String idMiasta) {
        this.idMiasta = idMiasta;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNumerBudynku() {
        return numerBudynku;
    }

    public void setNumerBudynku(String numerBudynku) {
        this.numerBudynku = numerBudynku;
    }

    public String getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(String numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }

    @Override
    public String toString() {
        return "mieszkania{" +
                "idMieszkania=" + idMieszkania +
                ", idMiasta='" + idMiasta + '\'' +
                ", miasto='" + miasto + '\'' +
                ", ulica='" + ulica + '\'' +
                ", numerBudynku='" + numerBudynku + '\'' +
                ", numerMieszkania='" + numerMieszkania + '\'' +
                '}';
    }
}
