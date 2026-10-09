package co.edu.uniquindio.poo.model;

import javax.swing.*;
import java.time.LocalDate;
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

    //2. Obtener los codigos de los productos con una cantidad disponible mayor o igual a 10 y menor que 50
    public List<String> obtenerCodigoProductoMayor10(){
        return listaProductos.entrySet().stream().filter(llave->llave.getValue().getCantidadDisponible() >= 10 && llave.getValue().getCantidadDisponible() < 50).map(Map.Entry::getKey).collect(Collectors.toList());

    }
    //3. Obtener la lista de clientes que hayan comprado el 07 de octubre de 2026

    public List<Cliente> obtenerClientesFechaCompra() {
        return listaClientes.stream()
                .filter(cliente -> cliente.getListaFacturas().stream()
                        .anyMatch(factura -> factura.fecha().isEqual(LocalDate.of(2026, 10, 7))))
                .collect(Collectors.toList());
    }

    //Punto 4
    //Obtener las facturas que tengan un cliente donde su nombre empieze por R

    public List<Factura> obtenerFacturassNombre(){
        List<Factura> facturasClienteR = new ArrayList<>();
        for (Factura factura: listaFacturas){
            if (factura.cliente().getNombreCompleto().startsWith("R")){
                facturasClienteR.add(factura);
            }
        }
        return facturasClienteR;
    }


    //punto 5: Obtener las facturas donde se haya comprado un celular de marca Iphone 16 pro max
        public List<Factura> obtenerFacturasIphone16(){
        List<Factura> facturasIphone = new ArrayList<>();
        for (Factura f:listaFacturas){
            for (DetalleFactura detalle:f.listaDetallesFactura()){
                if (detalle.getProducto().getNombre().equals("Iphone 16 pro max")){
                    facturasIphone.add(f);
                    break;
                }
            }
        }
        return facturasIphone;
    }
    //punto 6: Obtener las facturas que tenga un cliente
    // donde su nombre sea juan y haya comprado un celular de marca Iphone 16 pro max
    public List<Factura> obtenerFacturasJuanIphone(){
        List<Factura> facturasJuanIphone = new ArrayList<>();
        for (Cliente cliente: listaClientes){
            if (cliente.getNombreCompleto().startsWith("Juan")){
            for (Factura f:cliente.getListaFacturas()){
                    for (DetalleFactura detalle:f.listaDetallesFactura()){
                        if (detalle.getProducto().getNombre().equals("Iphone 16 pro max")){
                            facturasJuanIphone.add(f);
                            break;
                        }
                    }
                }
            }
        }
        return facturasJuanIphone;
    }

    // Punto 7: Implementar un método que reciba una categoría
    // y retorne todos los productos registrados que pertenezcan a ella.
    public List<Producto> identificarProductosCategoria(Categoria categoria){
        List<Producto> productosCategoria = new ArrayList<>();

        for (Producto p:listaProductos.values()){
            if (p.getCategoria().equals(categoria)){
                productosCategoria.add(p);
            }
        }
        return productosCategoria;
    }

    //punto 8 : Implementar un método que reciba un precio mínimo y un precio máximo, y retorne
    // los productos cuyo precio se encuentre dentro de ese rango, incluyendo ambos límites.

    public List<Producto> identificarProductosRangoPrecio(double precioMinimo,double precioMaximo){
        List<Producto> productosRangoPrecio = new ArrayList<>();
        for (Producto p:listaProductos.values()){
            if (p.getValor() >= precioMinimo && p.getValor() <= precioMaximo){
                productosRangoPrecio.add(p);
            }
        }
        return productosRangoPrecio;
    }

    //punto 9: Implementar un método que retorne todos los productos
    // registrados en la tienda, ordenados de menor a mayor según su precio.
    public List<Producto> ordenarProductosPorPrecio(){
        List<Producto> productosOrdenados = new ArrayList<>(listaProductos.values());

        for (int i = 0; i < productosOrdenados.size() - 1; i++) {
            for (int j = 0; j < productosOrdenados.size() - i - 1; j++) {
                if (productosOrdenados.get(j).getValor() > productosOrdenados.get(j + 1).getValor()) {
                    Producto temp = productosOrdenados.get(j);
                    productosOrdenados.set(j, productosOrdenados.get(j + 1));
                    productosOrdenados.set(j + 1, temp);
                }
            }
        }
        return productosOrdenados;
    }
    //punto 10: Implementar un método que identifique el producto con el precio más alto de la tienda.
    // Si no existen productos registrados, el método debe retornar un Optional vacío.

    //Punto 11: Implementar un método que reciba el nombre de una ciudad y retorne
    // todos los clientes que residan en ella.
    // La búsqueda debe realizarse sin diferenciar entre mayúsculas y minúsculas.




}






