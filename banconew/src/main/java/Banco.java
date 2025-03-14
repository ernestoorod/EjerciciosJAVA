interface CuentaBancaria {
    double getSaldo();
    void realizarIngreso(double cantidad);
    void realizarTransferencia(Cuenta destino, double cantidad);
}

class Direccion {
    String via, portal, piso, puerta, ciudad, pais, codigoPostal;
    
    public Direccion(String via, String portal, String piso, String puerta, String ciudad, String pais, String codigoPostal) {
        this.via = via;
        this.portal = portal;
        this.piso = piso;
        this.puerta = puerta;
        this.ciudad = ciudad;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
    }
}

class Contacto {
    String email;
    String telefono;
    
    public Contacto(String email, String telefono) {
        this.email = email;
        this.telefono = telefono;
    }
}

class Cliente {
    String dni, nombre, apellidos;
    Direccion direccion;
    Contacto contacto;
    Cuenta cuenta;
    
    public Cliente(String dni, String nombre, String apellidos, Direccion direccion, Contacto contacto, Cuenta cuenta) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.contacto = contacto;
        this.cuenta = cuenta;
    }
    
    public double getSaldoTotal() {
        return cuenta.getSaldo();
    }
}

class Empleado {
    String dni, nombre, apellidos, usuario, contraseña;
    Direccion direccion;
    Contacto contacto;
    
    public Empleado(String dni, String nombre, String apellidos, String usuario, String contraseña, Direccion direccion, Contacto contacto) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.direccion = direccion;
        this.contacto = contacto;
    }
}

abstract class Cuenta implements CuentaBancaria {
    String numeroCuenta;
    double saldo;
    Cliente cliente;
    
    public Cuenta(String numeroCuenta, Cliente cliente, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.cliente = cliente;
        this.saldo = saldo;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public void realizarIngreso(double cantidad) {
        saldo += cantidad;
    }
    
    public void realizarTransferencia(Cuenta destino, double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            destino.realizarIngreso(cantidad);
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }
}

class CuentaNomina extends Cuenta {
    public CuentaNomina(String numeroCuenta, Cliente cliente, double saldo) {
        super(numeroCuenta, cliente, saldo);
    }
    
    public void realizarIngreso(double cantidad) {
        if (cantidad > 1500) {
            saldo += cantidad * 1.01;
        } else {
            saldo += cantidad;
        }
    }
    
    public void realizarTrnasferencia(Cuenta destino, double cantidad) {
        if (saldo >= cantidad + 0.05) {
            saldo -= cantidad + 0.05;
            destino.realizarIngreso(cantidad);
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }
}

class CuentaVivienda extends Cuenta {
    double hipotecaPendiente;
    
    public CuentaVivienda(String numeroCuenta, Cliente cliente, double saldo, double hipotecaPendiente) {
        super(numeroCuenta, cliente, saldo);
        this.hipotecaPendiente = hipotecaPendiente;
    }
    
    public void cancelarImporteHipoteca(double cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            hipotecaPendiente -= cantidad;
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }
    
    public double verRestoHipoteca() {
        return hipotecaPendiente;
    }
}

class CuentaAhorro extends Cuenta {
    double comisionesCobradas = 0;
    
    public CuentaAhorro(String numeroCuenta, Cliente cliente, double saldo) {
        super(numeroCuenta, cliente, saldo);
    }
    
    public void realizarTransferenciaConComision(Cuenta destino, double cantidad) {
        double comision = cantidad * 0.01;
        if (saldo >= cantidad + comision) {
            saldo -= cantidad + comision;
            destino.realizarIngreso(cantidad);
            comisionesCobradas += comision;
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }
    
    public double verComisionesCobradas() {
        return comisionesCobradas;
    }
}

public class Banco {
    public static void main(String[] args) {
        Direccion dir = new Direccion("Calle Falsa", "123", "1", "A", "Benavente", "España", "49600");
        Contacto cont = new Contacto("ernesto@gmail.com", "123456789");
        
        CuentaNomina cuentaNomina = new CuentaNomina("1111", null, 2000);
        Cliente cliente = new Cliente("12345678A", "Ernesto", "Rodriguez", dir, cont, cuentaNomina);
        cuentaNomina.cliente = cliente;
        
        CuentaAhorro cuentaAhorro = new CuentaAhorro("2222", cliente, 1500);
        
        System.out.println("Saldo inicial Cuenta Nómina: " + cuentaNomina.getSaldo());
        System.out.println("Saldo inicial Cuenta Ahorro: " + cuentaAhorro.getSaldo());
        
        cuentaNomina.realizarTransferencia(cuentaAhorro, 500);
        
        System.out.println("Saldo tras transferencia Cuenta Nómina: " + cuentaNomina.getSaldo());
        System.out.println("Saldo tras transferencia Cuenta Ahorro: " + cuentaAhorro.getSaldo());
        
        cuentaAhorro.realizarTransferenciaConComision(cuentaNomina, 200);
        
        System.out.println("Saldo final Cuenta Ahorro: " + cuentaAhorro.getSaldo());
        System.out.println("Saldo final Cuenta Nómina: " + cuentaNomina.getSaldo());
        
    }
}
