package ar.com.facturacion.servicios;

import java.math.BigDecimal;
import java.util.List;
import ar.com.facturacion.dominio.Item;
import org.springframework.stereotype.Service;

@Service
public class FacturacionServicio {


	public static BigDecimal calcularTotal(List<Item> items){
		BigDecimal total=new BigDecimal(0);
		for (Item i: items) {
			total=total.add(i.getSubTotal());
		}
		return total;
	}


}
