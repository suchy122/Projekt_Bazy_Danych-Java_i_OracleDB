package mieszkania.database;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class klienci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idKlienta")
    private int idKlienta;
    @Column(name="imie")
    private String imie;
    @Column(name="nazwisko")
    private String nazwisko;
    @Column(name="pesel")
    private String pesel;
    @Column(name="nrTelefonu")
    private String nrTelefonu;

    public klienci() {
    }

    public klienci(int idKlienta, String imie, String nazwisko, String pesel, String nrTelefonu) {
        this.idKlienta = idKlienta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.nrTelefonu = nrTelefonu;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

    @Override
    public String toString() {
        return "klienci" + "idKlienta=" + idKlienta + ", imie='" + imie  + ", nazwisko='" + nazwisko + ", pesel=" + pesel + ", nrtelefonu=" + nrTelefonu; }
}
