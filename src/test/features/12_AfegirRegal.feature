# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un regal a un producte

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 2", preu 2€, iva 21% i codi de barres 2234567 i que pertany als tipus "figura d'acció,transformer"
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1

  Escenari: Afegir regal a la venta
    Quan apreto sobre el afegir regal y selecciono el producte "Optimus Prime 2"
    Aleshores el total de la venta actual és de 20€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 1u = 20€
    -50% 10€
    10€
    ---
    Total: 10€
    """