package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda("Next Tech Store", "900123456-1", "3001234567");
        cargarDatos(tienda);
        tienda.consultarFacturasCliente("1");
    }

    public static void cargarDatos(Tienda tienda) {
        Producto p1 = new Producto("Laptop de ultimo modelo", "401", "Laptop gaming", 6, 3000000.0, tienda);
        Producto p2 = new Producto("Mouse Inalámbrico", "402", "Mouse ergonómico", 15, 120000.0, tienda);
        Producto p3 = new Producto("Teclado Mecánico", "403", "Teclado RGB", 10, 250000.0, tienda);

        tienda.registrarProducto(p1);
        tienda.registrarProducto(p2);
        tienda.registrarProducto(p3);

        ArrayList<Factura> facturasJuan = new ArrayList<>();

        Cliente c1 = new Cliente("1", "Juan Perez", "Bogota", "320", "pepe@gmail.com", facturasJuan, tienda);
        Cliente c2 = new Cliente("2", "Alberto Perez", "Cartagena", "325", "Colrs@gmail.com", new ArrayList<>(), tienda);
        Cliente c3 = new Cliente("3", "Cortazar Perez", "New york", "325123", "Colr132s@gmail.com", new ArrayList<>(), tienda);

        tienda.registrarCliente(c1);
        tienda.registrarCliente(c2);
        tienda.registrarCliente(c3);

        Factura facturaPrueba = new Factura("F-001", LocalDate.now(), 0.0, EstadoFactura.GENERADA, MetodoPago.EFECTIVO, c1, new ArrayList<>(), tienda);

        DetalleFactura detalle1 = new DetalleFactura(1, p1, facturaPrueba);
        DetalleFactura detalle2 = new DetalleFactura(2, p2, facturaPrueba);

        facturaPrueba.registrarDetalleFactura(detalle1);
        facturaPrueba.registrarDetalleFactura(detalle2);

        facturasJuan.add(facturaPrueba);
        tienda.registrarFactura(facturaPrueba);
    }
}