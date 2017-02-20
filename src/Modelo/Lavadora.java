
package Modelo;


public class Lavadora extends Electrodomestico {
    int revoluciones;
    double carga;
    public Lavadora(){
        super();
    }
    
    @Override
    public void setPrecio(double precioBase){
        double incremento;
    this.precio = precioBase;
   if (this.revoluciones > 500 ){
       incremento = precioBase * 1.1;
       this.precio = precioBase + incremento;
   }
    if (this.carga < 8){
       incremento = precioBase * 1.15;
       this.precio = precioBase - incremento; 
    }
    }
    public int getRevoluciones() {
        return revoluciones;
    }

    public void setRevoluciones(int revoluciones) {
        this.revoluciones = revoluciones;
    }

    public double getCarga() {
        return carga;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }
    
    @Override
    public String imprimirProducto(){
        String res = super.imprimirProducto() + "de revoluciones: "+this.revoluciones+ "con carga: "+this.carga;
        return res;
    }
    
 
}
