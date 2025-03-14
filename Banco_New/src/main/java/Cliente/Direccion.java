package Cliente;

public class Direccion {
    private String via;
    private String portal;
    private String piso;
    private String puerta;
    private String ciudad;
    private String pais;
    private String codigoPostal;

    public Direccion(String via, String portal, String piso, String puerta, String ciudad, String pais, String codigoPostal) {
        this.via = via;
        this.portal = portal;
        this.piso = piso;
        this.puerta = puerta;
        this.ciudad = ciudad;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return via + " " + portal + ", Piso " + piso + " Puerta " + puerta + ", " + ciudad + ", " + pais + " - " + codigoPostal;
    }
}
