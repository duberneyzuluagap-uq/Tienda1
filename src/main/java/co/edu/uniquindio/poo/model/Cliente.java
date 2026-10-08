package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Cliente {

    private final String documentoIdentidad;
    private String nombreCompleto;

    private final String telefono;
    private final String ciudadResidencia;
    private final String correo;

    private final List<Factura> listaFacturas;

    private final Tienda ownedByTienda;

    public Cliente(String documentoIdentidad, String nombreCompleto, String ciudadResidencia, String telefono, String correo, List<Factura> listaFacturas, Tienda ownedByTienda) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.ciudadResidencia = ciudadResidencia;
        this.telefono = telefono;
        this.correo = correo;
        this.listaFacturas = listaFacturas;
        this.ownedByTienda = ownedByTienda;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCiudadResidencia() {
        return ciudadResidencia;
    }

    public String getCorreo() {
        return correo;
    }

    public List<Factura> getListaFacturas() {
        return Collections.unmodifiableList(listaFacturas);
    }
}



