package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public record Factura(String codigo, LocalDate fecha, double total,
                      EstadoFactura estadoFactura, MetodoPago metodoPago,
                      Cliente cliente, ArrayList<DetalleFactura> listaDetallesFactura, Tienda ownedByTienda) {

    public Optional<DetalleFactura> buscarDetalleFactura(Producto producto){
        return listaDetallesFactura.stream().filter(detalleFactura -> detalleFactura.getProducto().equals(producto)).findFirst();

        }

    public String registrarDetalleFactura(DetalleFactura detalle){
        Optional<DetalleFactura> detalleFacturaEncontrada = buscarDetalleFactura(detalle.getProducto());
        if (detalleFacturaEncontrada.isEmpty()){
            listaDetallesFactura.add(detalle);
            return "Detalle factura registrada con exito";
        }
        return "Ya existe este producto en la factura";
    }

    public String eliminarDetalleFactura(Producto producto){
        Optional<DetalleFactura> detalleFactura = buscarDetalleFactura(producto);

        if (detalleFactura.isPresent()){
            listaDetallesFactura.remove(detalleFactura.get());
            return "detalle factura eliminado con exito";
        }
        return "El detalle factura con ese producto no se ha encontrado";
    }

    public double calcularTotalFactura(){
        double total = listaDetallesFactura.stream().mapToDouble(DetalleFactura::calcularSubTotal).sum();
        return total;

    }

}



