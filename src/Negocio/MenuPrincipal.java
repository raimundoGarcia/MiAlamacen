package Negocio;

import Modelo.Cliente;
import Modelo.FormatoFechaErroneo;
import Modelo.Lavadora;
import Modelo.Mayorista;
import Modelo.Mueble;
import Modelo.Particular;
import Modelo.Producto;
import Modelo.Televisor;
import Modelo.TipoMayorista;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MenuPrincipal {

    ServicioCliente clientela = new ServicioCliente();
    ServicioProducto products = new ServicioProducto();
    ServicioVenta sells = new ServicioVenta();

    MenuPrincipal() {
        sells.setProducts(products);
        sells.setClientela(clientela);
        products.setSells(sells);
        clientela.setSells(sells);

    }

    public void inciarAplicacion() {
        try {
            // menu Principal
            int opcion = -1;
            do {
                System.out.println("1.Productos");
                System.out.println("2.Clientes");
                System.out.println("3.Ventas");
                System.out.println("0. Para Salir");
                System.out.println("Introduzca la opcion");
                Scanner sc = new Scanner(System.in);
                opcion = sc.nextInt();
                if (opcion == 1) {
                    menuProductos();
                }
                if (opcion == 2) {
                    menuClientes();
                }
                if (opcion == 3) {
                    menuVentas();
                }

            } while (opcion != 0);

            System.out.println("Gracias por usar nuestra aplicacion");
            System.out.println("Hasta la proxima");

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.inciarAplicacion();
        }
    }

    private void menuProductos() {
        try {
            int opcionProductos = -1;
            do {
                System.out.println("1.Introducir Producto");
                System.out.println("2.Dar de baja un producto");
                System.out.println("3.Imprimir los datos de un producto");
                System.out.println("4.Imprimir todos los productos");
                System.out.println("0. Salir del menu");
                Scanner sc = new Scanner(System.in);
                opcionProductos = sc.nextInt();

                if (opcionProductos == 1) {
                    Producto p = datosProducto();
                    products.introducirProducto(p);
                }
                if (opcionProductos == 2) {
                    System.out.println("Introduzca el número de producto: ");
                    int num = sc.nextInt();
                    products.elimninarProducto(num);
                }
                if (opcionProductos == 3) {
                    System.out.println("Introduzca el número de producto: ");
                    int nprod = sc.nextInt();
                    System.out.println(products.buscarProducto(nprod).imprimirProducto());
                }
                if (opcionProductos == 4) {
                    System.out.println(products.imprimirTodosProductos());
                }

            } while (opcionProductos != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida" + e.getMessage());
            this.menuProductos();
        }
    }

    public Producto datosProducto() throws Exception {
        Scanner sc = new Scanner(System.in);
        Producto producto = null;
        String nombre;
        double precio;
        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca precio base: ");
            precio = Double.parseDouble(sc.nextLine());

            System.out.println("Introduzca el tipo de producto: ");
            System.out.println("1. Mueble");
            System.out.println("2. Lavadora");
            System.out.println("3. Televisor");
            opcion = sc.nextInt();
            if (opcion == 1) {
                producto = pedirMueble();
            }
            if (opcion == 2) {
                producto = pedirLavadora();
            }
            if (opcion == 3) {
                producto = pedirTelevisor();
            }
            if (producto != null) {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
            }

        } while (opcion != 1 && opcion != 2 && opcion != 3);

        return producto;
    }

    public Cliente datosClientes() throws Exception {
        Scanner sc = new Scanner(System.in);
        Cliente clien = null;
        String nombre, razon;

        int opcion = -1;
        do {
            System.out.println("Introduzca el nombre: ");
            nombre = sc.nextLine();

            System.out.println("Introduzca la razon social: ");
            razon = sc.nextLine();

            System.out.println("Introduzca el tipo de cliente: ");
            System.out.println("1. Mayorista");
            System.out.println("2. Particular");
            opcion = sc.nextInt();
            if (opcion == 1) {
                clien = pedirClienteMayor();
            }
            if (opcion == 2) {
                clien = pedirClienteParticular();
            }

            if (clien != null) {
                clien.setNombre(nombre);
                clien.setRazonSocial(razon);
            }

        } while (opcion != 1 && opcion != 2 && opcion != 3);

        return clien;
    }

    public Cliente pedirClienteMayor() {
        int contador = 0;
        Mayorista m = new Mayorista();
        Scanner sc = new Scanner(System.in);
        String cif, menu;
        double desc;
        System.out.println("Introduce tu numero de CIF");
        cif = sc.nextLine();
        m.setCif(cif);

        do {
            System.out.println("Que tipo de mayorista es?");
            TipoMayorista[] valores = TipoMayorista.values();

            for (TipoMayorista c : valores) {
                System.out.println(++contador + ". " + c);
            }

            menu = sc.nextLine();

            if (menu.equals("1")) {
                m.setTipoMayorista(TipoMayorista.GRAN_SUPERFICIE);
            }
            if (menu.equals("2")) {
                m.setTipoMayorista(TipoMayorista.TIENDA);
            }
        } while (!menu.equals("1") && !menu.equals("2"));

        System.out.println("Introduce el % del descuento");
        desc = Double.parseDouble(sc.nextLine());
        m.setDescuento(desc);

        return m;

    }

    public Cliente pedirClienteParticular() {
        Particular p = new Particular();
        String respuesta = "";
        Scanner sc = new Scanner(System.in);
        boolean repetir;
        do {
            repetir = true;
            System.out.println("Introduce tu numero de DNI");
            String dni = sc.nextLine();
            respuesta = p.setDni(dni);
            if (respuesta != null) {
                System.out.println(respuesta);
                repetir = false;
            }

        } while (!repetir);
        return p;

    }

    public Mueble pedirMueble() {
        Mueble m = new Mueble();
        Scanner sc = new Scanner(System.in);

        m.setTipoMadera(pedirMadera());

        System.out.println("Introduzca el estilo:");
        m.setEstilo(sc.nextLine());
        try {
            System.out.println("Introduzca la fecha (dd-MMMM-yyyy): ");
            m.setAnyoFab(this.validarFecha(sc.nextLine()));
        } catch (FormatoFechaErroneo e) {
            System.out.println(e.getMessage());
        }
        return m;

    }

    public Lavadora pedirLavadora() {
        Scanner sc = new Scanner(System.in);
        Lavadora l = new Lavadora();

        System.out.println("Introduzca las revoluciones(rpm): ");
        int rev = Integer.parseInt(sc.nextLine());
        l.setRevoluciones(rev);

        System.out.println("Introduzca la capacidad (kg): ");
        int c = Integer.parseInt(sc.nextLine());
        l.setCarga(c);

        return l;
    }

    public Televisor pedirTelevisor() {
        Televisor tv = new Televisor();
        Televisor.TipoTelevisor t = null;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca las pulgadas: ");
        double pulgadas = Double.parseDouble(sc.nextLine());
        tv.setPulgadas(pulgadas);

        tv.setTipo(pedirTipoTelevisor());
        return tv;
    }

    public Televisor.TipoTelevisor pedirTipoTelevisor() {
        int contador = 0;
        Televisor.TipoTelevisor t = null;
        Scanner sc = new Scanner(System.in);
        String opcion;
        do {
            System.out.println("Introduzca el tipo: ");
            Televisor.TipoTelevisor[] valores = Televisor.TipoTelevisor.values();

            for (Televisor.TipoTelevisor c : valores) {
                System.out.println(++contador + ". " + c);
            }

            opcion = (sc.nextLine());

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3") && !opcion.equals("4"));

        switch (opcion) {
            case "1":
                t = Televisor.TipoTelevisor.PLASMA;
                break;
            case "2":
                t = Televisor.TipoTelevisor.LED;
                break;
            case "3":
                t = Televisor.TipoTelevisor.LCD;
                break;
            case "4":
                t = Televisor.TipoTelevisor.OLED;
                break;
            default:
                break;
        }
        return t;
    }

    private Mueble.Madera pedirMadera() {
        int contador = 0;
        Mueble.Madera m = null;
        String opcion;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el tipo de Madera");
            Mueble.Madera[] valores = Mueble.Madera.values();

            for (Mueble.Madera c : valores) {
                System.out.println(++contador + ". " + c);
            }

            opcion = sc.nextLine();

        } while (!opcion.equals("1") && !opcion.equals("2") && !opcion.equals("3"));

        if (opcion.equals("1")) {
            m = Mueble.Madera.PINO;
        }
        if (opcion.equals("2")) {
            m = Mueble.Madera.ROBLE;
        }
        if (opcion.equals("3")) {
            m = Mueble.Madera.HAYA;
        }
        return m;
    }

    private void menuClientes() {
        Scanner sc = new Scanner(System.in);
        int id;
        String todos;
        Cliente cliente = null;
        try {
            int opcionClientes = -1;
            do {
                System.out.println("1.Introducir Cliente");
                System.out.println("2.Dar de baja un cliente");
                System.out.println("3.Imprimir los datos de un cliente");
                System.out.println("4.Imprimir todos los clientes");
                System.out.println("0. Salir del menu");

                opcionClientes = Integer.parseInt(sc.nextLine());
                if (opcionClientes == 1) {
                    cliente = datosClientes();
                    this.clientela.introducirCliente(cliente);
                }
                if (opcionClientes == 2) {
                    System.out.println("Introduce el numero de Cliente");
                    id = Integer.parseInt(sc.nextLine());
                    this.clientela.eliminarCliente(id);
                }
                if (opcionClientes == 3) {
                    System.out.println("Introduce el numero de Clinte");
                    id = Integer.parseInt(sc.nextLine());
                    cliente = this.clientela.buscarCliente(id);

                    System.out.println(cliente.imprimir());

                }
                if (opcionClientes == 4) {
                    todos = this.clientela.imprimirTodosClientes();
                    System.out.println(todos);
                }

            } while (opcionClientes != 0);
        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuClientes();
        }

    }

    private void menuVentas() {
        Scanner sc = new Scanner(System.in);

        try {
            String opcionVentas = "-1";
            do {
                System.out.println("1.Introducir Venta");
                System.out.println("2.Dar de baja una venta");
                System.out.println("3.Imprimir los datos de una venta");
                System.out.println("4.Imprimir todas las ventas");
                System.out.println("0. Salir del menu");
                opcionVentas = sc.nextLine();

                if (opcionVentas.equals("1")) {
                    System.out.println("Introduce el número de cliente.");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el número de producto.");
                    int np = Integer.parseInt(sc.nextLine());
                    System.out.println("Introduce el nombre del vendedor: ");
                    String v = sc.nextLine();
                    sells.introducirVenta(nv, np, v);
                }
                if (opcionVentas.equals("2")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    sells.eliminarVenta(nv);
                }
                if (opcionVentas.equals("3")) {
                    System.out.println("Introduzca número de venta: ");
                    int nv = Integer.parseInt(sc.nextLine());
                    System.out.println(sells.buscarVenta(nv).imprimirVenta());
                }
                if (opcionVentas.equals("4")) {
                    System.out.println(sells.imprimirtodasVentas());
                }

            } while (!opcionVentas.equals("0"));

        } catch (Exception e) {
            System.out.println("Opcion no valida");
            this.menuVentas();
        }

    }

    private LocalDate validarFecha(String fecha) {
        LocalDate f = null;
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");

            f = LocalDate.parse(fecha, formato);
        } catch (DateTimeParseException e) {
            throw new FormatoFechaErroneo(fecha);
        }
        return f;
    }

}
