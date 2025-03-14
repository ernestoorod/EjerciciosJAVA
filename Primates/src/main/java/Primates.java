class AlmacenLleno extends Exception {
    public AlmacenLleno(String mensaje) {
        super(mensaje);
    }
}

abstract class Primate {
    protected String nombre;
    protected char[] almacen;
    protected int capacidadMaxima;
    protected int consumo;
    protected int cantidadActual;

    public Primate(String nombre, int capacidadMaxima, int consumo) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.consumo = consumo;
        this.almacen = new char[capacidadMaxima];
        this.cantidadActual = 0;
    }

    public void recogerComida() throws AlmacenLleno {
        if (cantidadActual >= capacidadMaxima) {
            throw new AlmacenLleno("El almacén está lleno");
        }
        almacen[cantidadActual++] = 'C';
    }

    public abstract void comer();
}

class Mono extends Primate {
    public Mono(String nombre) {
        super(nombre, 10, 1);
    }

    public void comer() {
    }
}

class Chimpance extends Primate {
    public Chimpance(String nombre) {
        super(nombre, 15, 2);
    }

    public void comer() {
    }
}

class Gorila extends Primate {
    private char[] zonaEscondida;
    private int cantidadEscondida;

    public Gorila(String nombre) {
        super(nombre, 20, 3);
        this.zonaEscondida = new char[capacidadMaxima];
        this.cantidadEscondida = 0;
    }

    public void comer() {
    }

    public void guardarComida(int cantidad) throws AlmacenLleno {
        if (cantidadEscondida + cantidad > capacidadMaxima) {
            throw new AlmacenLleno("No se puede guardar tanta comida");
        }
        for (int i = 0; i < cantidad; i++) {
            zonaEscondida[cantidadEscondida++] = 'C';
        }
    }

    public void robarComida(Primate victima) {
    }
}

interface HabilidadCacahuete {
    void romperCacahuete();
}

interface HabilidadPlatano {
    void pelarPlatano();
}

class MonoCacahuete extends Mono implements HabilidadCacahuete {
    public MonoCacahuete(String nombre) {
        super(nombre);
    }

    public void romperCacahuete() {
    }
}

class MonoPlatano extends Mono implements HabilidadPlatano {
    public MonoPlatano(String nombre) {
        super(nombre);
    }

    public void pelarPlatano() {
    }
}

class ChimpanceCacahuete extends Chimpance implements HabilidadCacahuete {
    public ChimpanceCacahuete(String nombre) {
        super(nombre);
    }

    public void romperCacahuete() {
    }
}

class ChimpancePlatano extends Chimpance implements HabilidadPlatano {
    public ChimpancePlatano(String nombre) {
        super(nombre);
    }

    public void pelarPlatano() {
    }
}

class GorilaCacahuete extends Gorila implements HabilidadCacahuete {
    public GorilaCacahuete(String nombre) {
        super(nombre);
    }

    public void romperCacahuete() {
    }
}

class GorilaPlatano extends Gorila implements HabilidadPlatano {
    public GorilaPlatano(String nombre) {
        super(nombre);
    }

    public void pelarPlatano() {
    }
}

public class Primates {
    public static void main(String[] args) {
    }
}
