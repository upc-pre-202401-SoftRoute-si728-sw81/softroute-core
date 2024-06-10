package edu.pe.softroute.trackingservice.domain.models.enums;

public enum StatusEvent {
  RECEIVED,             // El paquete ha sido recibido por la empresa de mensajería.
  PREPARING,            // El paquete está siendo preparado para su envío.
  DISPATCHED,           // El paquete ha sido despachado desde el almacén o punto de origen.
  IN_TRANSIT,           // El paquete está en camino hacia su destino.
  ARRIVED_AT_DESTINATION, // El paquete ha llegado a la ciudad o área de destino.
  OUT_FOR_DELIVERY,     // El paquete está siendo entregado al destinatario.
  DELIVERED,            // El paquete ha sido entregado al destinatario.
  DELIVERY_ATTEMPT_FAILED, // Se intentó entregar el paquete, pero no fue posible.
  ON_HOLD,              // El paquete está en espera debido a algún problema o falta de información adicional.
  RETURNED_TO_SENDER    // El paquete no pudo ser entregado y ha sido devuelto al remitente.
}
