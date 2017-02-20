package Modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mueble extends Producto {

    public enum Madera {
        PINO, ROBLE, HAYA
    };

    private LocalDate anyoFab;
    private Madera tipoMadera;
    private String estilo;

    public Mueble() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {
        double incremento;
        this.precio = precioBase;
        if (this.tipoMadera == Madera.ROBLE) {
            incremento = precioBase * 1.1;
            this.precio = precioBase + incremento;
        }
        if (this.tipoMadera == Madera.PINO) {
            incremento = precioBase * 1.1;
            this.precio = precioBase - incremento;

        }
    }

    @Override
    public String imprimirProducto() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yy");
        String res = super.imprimirProducto() + "el año de fabricación: " + 
                sdf.format(anyoFab) + " el tipo de madera: " + this.tipoMadera + "el estilo: " + getEstilo();
        return res;

    }

    public LocalDate getAnyoFab() {
        return anyoFab;
    }

    public void setAnyoFab(LocalDate anyoFab) {
        
        this.anyoFab = anyoFab;
    }

    public Madera getTipoMadera() {
        return tipoMadera;
    }

    public void setTipoMadera(Madera madera) {
        this.tipoMadera = madera;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

}
