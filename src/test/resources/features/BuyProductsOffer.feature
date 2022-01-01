Feature: comprar productos en oferta
         Yo como cliente
         quiero visualizar en la página web los productos en oferta
         para comprarlos online

  Background: ingresar pagina de ofertas
    Given el cliente se encuentra en la categoría de ofertas

  Scenario: llenar carrito de compras con productos en oferta
    When el cliente seleccione los productos y los confirme en el carrito
    Then el sistema dirige a la página del formulario de pago
    Then el sistema debe calcular y mostrar el subtotal de los productos en la página del formulario de pago


  Scenario: enviar pedido con productos en oferta
    When el cliente seleccione los productos y los confirme en el carrito
    And llene la información del formulario de pago y envíe
    Then el sistema confirma el envió del pedido
    Then el sistema debe mostrar el correspondiente monto total en la factura