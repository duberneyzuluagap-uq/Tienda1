package co.edu.uniquindio.poo.model;

import java.util.*;
import java.util.stream.Collectors;

/* Esta clase representa una tienda
*
*
*
 */
public class Tienda {
    private final String nombre;
    private final String nit;
    private String telefono;

    private final ArrayList<Cliente> listaClientes = new ArrayList<>();
    private final List<Factura> listaFacturas = new LinkedList<>();
    private Map<String,Producto> listaProductos = new HashMap<>();

    public Tienda(String nombre, String nit, String telefono){
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNit() {
        return nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public String toString() {
        return "Tienda{" +
                "nombre='" + nombre + '\'' +
                ", nit='" + nit + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public String registrarCliente(Cliente cliente){

        Optional<Cliente> clienteEncontrado = buscarCliente(cliente.getDocumentoIdentidad());

        if (clienteEncontrado.isEmpty()){
            listaClientes.add(cliente);
            return "El cliente fue registrado exitosamente";
        }else return "No se puede registrar,ya existe un cliente con esa informacion registrado anteriormente";

        // TAREA PARA el martes ya el proyecto con todos los CRUD sin usar IA con los metodos
        // TAREA CAMBIAR UN : if (clienteEncontrado == null){ por un OPTIONAL hacer el metodo buscarcliente usando un OPTIONAL


    }

    public String eliminarCliente(String identificacion){
        Optional<Cliente> clienteEncontrado = buscarCliente(identificacion);

        if (clienteEncontrado.isEmpty()){
            return "El cliente no existe";
        }else{
            listaClientes.remove(clienteEncontrado.get());
            return "cliente eliminado con exito con nombre:" + clienteEncontrado.get().getNombreCompleto() ;
        }
    }


    public Optional<Cliente> buscarCliente(String documentoIdentidad) {
        return listaClientes.stream().filter(cliente -> documentoIdentidad.equals(cliente.getDocumentoIdentidad())).findFirst();

    }

    public String registrarProducto(Producto producto){
            Optional<Producto> productoEncontrado = buscarProducto(producto.getCodigo());

            if (productoEncontrado.isPresent()){
                return "El producto ya existe ";

            }else{
                listaProductos.put(producto.getCodigo(),producto);
                return "Producto agregado con exito";
            }


    }

    public String eliminarProducto(String codigo){
        Optional<Producto> productoEncontrado = buscarProducto(codigo);

        if (productoEncontrado.isPresent()){
            listaProductos.remove(codigo);
            return "Producto eliminado con exito";
        }


        return "No se ha encontrado el producto con ese codigo";
    }
    public String registrarFactura(Factura factura){
        Optional<Factura> facturaEncontrada = buscarFactura(factura.codigo());

        if (facturaEncontrada.isEmpty()){
            listaFacturas.add(factura);
            return "La factura ha sido añadida con exito";
        }
        return "La factura ya esta registrada";
    }

    public String eliminarFactura(String codigo){
        Optional<Factura> facturaEncontrada = buscarFactura(codigo);

        if (facturaEncontrada.isPresent()){
            listaFacturas.remove(facturaEncontrada.get());
            return "La factura se ha eliminado";
        }else return "La factura no se encuentra";

    }

    public Optional<Factura> buscarFactura(String codigo){
        return listaFacturas.stream().filter(f -> f.codigo().equals(codigo)).findFirst();
        }

    public Optional<Producto> buscarProducto(String codigo) {
        if (listaProductos.containsKey(codigo)) {
            return Optional.of(listaProductos.get(codigo));
        }
        return Optional.empty();
    }

    public void consultarFacturasCliente(String identificacion){
        Optional<Cliente> clienteEncontrado = buscarCliente(identificacion);
        if (clienteEncontrado.isPresent()){
             clienteEncontrado.get().getListaFacturas().forEach(System.out::println);
        }else System.out.println("cliente no encontrado");
    }
    //TALLER
    //1. Obtener los productos con una cantidad disponible mayor o igual a 10

    public List<Producto> obtenerProductosMayor10(){
        return listaProductos.values().stream().
                filter(producto -> producto.getCantidadDisponible()>=10)
                .collect(Collectors.toList());
    }


}






