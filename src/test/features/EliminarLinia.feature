# language: ca

#noinspection SpellCheckingInspection
Característica: Eliminar una línia

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567
    I un producte amb nom "Optimus Prime 2", preu 25€, iva 21% i codi de barres 2345678
    I un producte amb nom "Optimus Prime 3", preu 30€, iva 21% i codi de barres 4567891

  Escenari: Eliminar línia
    Quan elimino el producte "Optimus Prime 2" de la venta
    Aleshores la venta només li queden 2 productes