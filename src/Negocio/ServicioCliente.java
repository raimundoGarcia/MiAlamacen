package Negocio;

import Modelo.Cliente;
import Modelo.Mayorista;
import Modelo.Particular;
import Modelo.Venta;
import java.util.ArrayList;
import java.util.List;

public class ServicioCliente {

    ServicioVenta sells;
    private List<Cliente> clientes = new ArrayList();

    public ServicioVenta getSells() {
        return sells;
    }

    public void setSells(ServicioVenta sells) {
        this.sells = sells;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean introducirCliente(Cliente c) {
        boolean añadido = false;
        try {
            if (buscarCliente(c.getIdCliente()) == null) {
                clientes.add(c);
                añadido = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return añadido;
    }

    public Cliente buscarCliente(int numeroCliente) {
        Cliente cliente = null;
        boolean encontrado = false;
        int c;
        for (int i = 0; i < clientes.size() && !encontrado; i++) {
            c = clientes.get(i).getIdCliente();
            if (c == numeroCliente) {
                cliente = clientes.get(i);
                encontrado = true;

            }

        }
        return cliente;
    }

    public void eliminarCliente(int numCliente) {
        try {
            // Al eliminar un cliente también eliminamos las ventas asociadas a el

            //Eliminamos las ventas del cliente seleccionado
            sells.eliminarVenta(numCliente);

            //Eliminamos el cliente
            Cliente clienteBorrar = null;
            for (int i = 0; i < clientes.size() && clienteBorrar == null; i++) {
                if (clientes.get(i).getIdCliente() == numCliente) {
                    clienteBorrar = clientes.get(i);
                }
            }

            clientes.remove(clienteBorrar);

        } catch (Exception e) {
            throw new RuntimeException("imposible eliminar cliente");
        }

    }

    public String imprimirTodosClientes() {
        String res = "";
        if (clientes.isEmpty()) {
            res = "No hay clientes introducidos.";

        } else {
            res = String.format("%-15s %-15s %-15s %-15s %-15s %-15s","ID","Nombre","RAZON SOCIAL","CIF/DNI","TIPO","DESCUENTO") + "\n";
           
        for (Cliente c : clientes) {

                if (c instanceof Mayorista) {
                    Mayorista m = (Mayorista) c;
                    res += String.format("%-15s %-15s %-15s %-15s %-15s %-15s",m.getIdCliente(),m.getNombre(),m.getRazonSocial(),m.getCif(),m.getTipoMayorista(),m.getDescuento())+"\n";

                }
                if (c instanceof Particular) {
                    Particular p = (Particular) c;
                    res += String.format("%-15s %-15s %-15s %-15s",p.getIdCliente(),p.getNombre(),p.getRazonSocial(),p.getDni())+ "\n";

                }
            }
        }
        return res;
    }

}
