package Negocio;

import Modelo.Cliente;
import Modelo.Producto;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class ServicioVenta {
    ServicioCliente clientela;
    ServicioProducto products;
    private List<Venta> ventas=new ArrayList();

    public ServicioCliente getClientela() {
        return clientela;
    }

    public void setClientela(ServicioCliente clientela) {
        this.clientela = clientela;
    }

    public ServicioProducto getProducts() {
        return products;
    }

    public void setProducts(ServicioProducto products) {
        this.products = products;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    
    
     public void introducirVenta(int ncliente, int nproducto, String vend) {
        try {

            Cliente clienteVenta = null;
            for (int i = 0; i < clientela.getClientes().size() && clienteVenta == null; i++) {
                if (clientela.getClientes().get(i).getIdCliente() == ncliente) {
                    clienteVenta = clientela.getClientes().get(i);
                }
            }
            if (clienteVenta == null) {
                throw new RuntimeException("El cliente no existe.");
            }

            Producto productoVenta = null;
            for (int i = 0; i < products.getProductos().size() && productoVenta == null; i++) {
                if (products.getProductos().get(i).getId() == nproducto) {
                    productoVenta = products.getProductos().get(i);
                }
            }
            if (productoVenta == null) {
                throw new RuntimeException("El producto no existe.");
            }

            Venta v = new Venta();
            v.setCliente(clienteVenta);
            v.setVendedor(vend);
            v.setProducto(productoVenta);
            v.setPrecioVenta(); //calcula el precio de la venta segun el cliente-mayorista

            ventas.add(v);

            clienteVenta.getCompras().add(v);
            productoVenta.getVentas().add(v);

        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible introducir la venta" + e.getMessage());
        }

    }

   

   

    public Venta buscarVenta(int nv) {
        boolean encontrado = false ;
        Venta venta = null;
        try {

            
            for (int i = 0; i < ventas.size()&& !encontrado; i++) {
                if (ventas.get(i).getIdVenta() == nv) {
                    venta = ventas.get(i);
                    encontrado = true;
                }
            }
            if (venta == null) {
                throw new Exception("La venta con id: " + nv + " no existe");
            }
          
        } catch (Exception e) {
            throw new RuntimeException("No ha sido posible imprimir la venta" + e.getMessage());
        }
        return venta;
    }

    

   
    public void eliminarVenta(int nv) {

        try {

            Venta ventaBorrar = null;
            for (int i = 0; i < ventas.size() && ventaBorrar==null; i++) {

                if (ventas.get(i).getIdVenta() == nv) {
                    ventaBorrar = ventas.get(i);

                }
            }
            if (ventaBorrar == null) {
                // hacemos saltar una excepcion que nos lleva directamente al catch
                throw new Exception("No existe ninguna venta con ese Id");
            }
            // este código solo se ejecuta si todo va bien
            ventas.remove(ventaBorrar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar la venta");
        }

    }

    

   
    public String imprimirtodasVentas() {
        String res = "";
        if (ventas.isEmpty()) {
            res = "No hay ventas introducidas.";

        } else {
            for (Venta v : ventas) {
                res += "\n ID VENTA VENDEDOR  CLIENTE PRODUCTO PRECIO VENTA" + "\n" + v.getIdVenta() + "   " + v.getVendedor() + "   " + v.getCliente().getIdCliente() + "  " + v.getProducto().getId() + "   " + v.getPrecioVenta();

            }
        }
        return res;
    }

}
