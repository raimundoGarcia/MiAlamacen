
package Modelo;


public class FormatoFechaErroneo extends RuntimeException{
    
    public String fecha;

    public FormatoFechaErroneo() {
        super ("fecha errornea");
    }

    public FormatoFechaErroneo(String fecha) {
        super ("Formato erroneo, el formato correcto es 01-enero-1999 y has introducido" + fecha);
        this.fecha = fecha;
       
    }
    
}
