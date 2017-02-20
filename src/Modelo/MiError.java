package Modelo;

public class MiError extends RuntimeException {

    public String dni;

    public MiError() {
        super("Faltan o sobran caracteres");
    }

    public MiError(String dni) {
        super("Dni " + dni + " no valido");
        this.dni = dni;
    }
}
