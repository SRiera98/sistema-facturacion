package ar.com.facturacion.servicios;

import java.math.BigDecimal;
import java.util.List;

import ar.com.facturacion.dominio.Encabezado;
import ar.com.facturacion.dominio.Item;
import ar.com.facturacion.repositorio.EncabezadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.facturacion.dominio.Cliente;
import ar.com.facturacion.dominio.Producto;

@Service
public class FacturacionServicio {

	
	public void facturar(Cliente cliente, List<Producto> productos) {
	}


}
