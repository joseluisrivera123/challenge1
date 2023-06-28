package com.msproduct.msproduct.web.mapper;

import com.msproduct.msproduct.exception.BadRequestException;
import com.msproduct.msproduct.model.Ordenes;
import com.msproduct.msproduct.model.Producto;
import com.msproduct.msproduct.service.OrdenesService;
import com.msproduct.msproduct.web.message.OrdenesMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;

@Component
public class OrdenesMapper {

    private final OrdenesService ordenesService;

    public OrdenesMapper(OrdenesService ordenesService){
        this.ordenesService =ordenesService;
    }

    public Ordenes crearPrueba(OrdenesMessage message){


        Producto producto = ordenesService.validarPRoduct(message.getProducto_id())
                .orElseThrow(() -> new BadRequestException("La Producto no encontrado"));

            Ordenes ordenes = new Ordenes();
            double subtotal = producto.getPrecioVenta() * message.getCantidad();

     return new Ordenes(producto.getId(),message.getCantidad(),subtotal,true);
     }


     /**
      * public Ordenes crearPrueba(OrdenesMessage message){
      *         ResponseEntity<Producto> producto = ordenesService.validarPRoduct(message.getProducto_id())
      *                 .map(data -> ResponseEntity.ok().body(data))
      *                 .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build()).block();
      *
      *         Ordenes ordenes = new Ordenes();
      *
      *         Long cantidad = message.getCantidad();
      *         double precio = producto.getBody().getPrecioVenta().doubleValue();
      *
      *         ordenes.setProducto(producto.getBody());
      *         ordenes.setCantidad(cantidad);
      *         ordenes.setPrecio(precio * cantidad);
      *         ordenes.setEstado(true);
      *      return ordenes;
      *      }**/

}
