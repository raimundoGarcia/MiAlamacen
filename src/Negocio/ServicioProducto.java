
package Negocio;

import Modelo.Lavadora;
import Modelo.Mueble;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;


public class ServicioProducto {
    ServicioVenta sells;
    private List<Producto> productos=new ArrayList();

    public ServicioVenta getSells() {
        return sells;
    }

    public void setSells(ServicioVenta sells) {
        this.sells = sells;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

   
     public boolean introducirProducto(Producto p) {
        boolean añadido = false;
        try {
            if( buscarProducto(p.getId()) == null ){
            productos.add(p);
            añadido = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al introducir el producto\n" + e.getMessage());
        }
        return añadido;
    }
      public Producto buscarProducto(int np) throws Exception {
        Producto producto = null;
        boolean encontrado = false;
        for (int i = 0; i < productos.size()&& !encontrado; i++) {
            if (productos.get(i).getId() == np) {
                producto = productos.get(i);
                encontrado = true;
            }
        }

        if (producto == null) {
            throw new Exception("El producto no existe.");
        }
        return producto;
    }
      public void elimninarProducto(int nproducto) {
        try {
            Producto productoEliminar = null;
            //Eliminamos de ventas el producto seleccionado
            List<Venta> ventasEliminar = new ArrayList();
            for (Venta v : sells.getVentas()) {
                if (v.getProducto().getId() == nproducto) {
                    ventasEliminar.add(v);

                }
            }
            sells.getVentas().removeAll(ventasEliminar);

            //Eliminamos el producto
            for (Producto p : productos) {
                if (p.getId() == nproducto) {
                    productoEliminar = p;
                }
            }
            productos.remove(productoEliminar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar producto");
        }

    }
      public String imprimirTodosProductos() {
        String res = "";
        if (productos.isEmpty()) {
            res = "No hay productos introducidos.";

        } else {
            for (Producto p : productos) {
                if (p instanceof Televisor) {
                    Televisor t = (Televisor) p;
                    res += "\n ID NOMBRE  PRECIO  MARCA   FABRICANTE  TAMAÑO   TIPO    PULGADAS" + "\n" + t.getId() + "   " + t.getNombre() + "   " + t.getPrecio() + t.getMarca() + "   " + t.getFabricante() + "   " + t.getTamanyo() + t.getTipo() + "  " + t.getPulgadas();

                }

                if (p instanceof Lavadora) {
                    Lavadora l = (Lavadora) p;
                    res += "\n ID NOMBRE  PRECIO  MARCA   FABRICANTE  REVOLUCIONES  CARGA" + "\n" + l.getId() + "   " + l.getNombre() + "   " + l.getPrecio() + "  " + l.getMarca() + "         " + l.getFabricante() + "   " + l.getTamanyo() + "     " + l.getRevoluciones() + "     " + l.getCarga();

                }

                if (p instanceof Mueble) {
                    Mueble m = (Mueble) p;
                    res += "\n ID NOMBRE  PRECIO     AÑO FABRICACION              MADERA  ESTILO" + "\n" + m.getId() + "   " + m.getNombre() + "   " + m.getPrecio() + "  " + m.getAnyoFab() + "   " + m.getTipoMadera() + "     " + m.getEstilo();

                }
            }
        }
        return res;
    }
    
}
