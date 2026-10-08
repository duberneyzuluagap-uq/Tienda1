package co.edu.uniquindio.poo.model;

public class DetalleFactura {

    private final int cantidadComprada;
    private  double subTotal;
    private final Producto producto;
    private final Factura ownedByFactura;

    public DetalleFactura(int cantidadComprada, Producto producto, Factura ownedByFactura) {
        this.cantidadComprada = cantidadComprada;
        this.producto = producto;
        this.subTotal = calcularSubTotal();
        this.ownedByFactura = ownedByFactura;
    }

    public int getCantidadComprada() {
        return cantidadComprada;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public Factura getOwnedByFactura() {
        return ownedByFactura;
    }

    public double calcularSubTotal(){

        this.subTotal = producto.getValor()*cantidadComprada;
        return  subTotal;
    }
}
