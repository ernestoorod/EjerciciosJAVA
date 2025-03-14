package Cliente;

import java.util.List;

public class Contacto {
    private List<String> emails;
    private List<String> telefonos;

    public Contacto(List<String> emails, List<String> telefonos) {
        this.emails = emails;
        this.telefonos = telefonos;
    }

    @Override
    public String toString() {
        return "Emails: " + emails + ", Teléfonos: " + telefonos;
    }
}
