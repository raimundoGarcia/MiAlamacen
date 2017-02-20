
package Modelo;


public class Televisor extends Electrodomestico {
    
    public enum TipoTelevisor {
    PLASMA,LED,LCD,OLED;
}

    protected double pulgadas;
    protected TipoTelevisor tipo;
    

    public Televisor() {
        super();
    }

    @Override
    public void setPrecio(double precioBase) {
        double incremento;
    this.precio = precioBase;
   if (this.pulgadas >  40){
       incremento = precioBase * 1.1;
       this.precio = precioBase + incremento;
   }
    if (this.tipo == TipoTelevisor.PLASMA){
       incremento = precioBase * 1.1;
       this.precio = precioBase - incremento;
        
     
    }
    }

    @Override
    public String imprimirProducto() {
        String res = super.imprimirProducto() + "tipo de TV: " + this.tipo + "con " + this.pulgadas + " pulgadas";
        return res;

    }

    public double getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(double pulgadas) {
        this.pulgadas = pulgadas;
    }

    public TipoTelevisor getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelevisor tipo) {
        this.tipo = tipo;
    }

}
