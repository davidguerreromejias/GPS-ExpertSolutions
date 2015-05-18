# language: ca

#noinspection SpellCheckingInspection
Característica: Historial de ventes

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv

  Escenari: Cobrar una venta en metàlic
    Donat que hi ha una venta iniciada
    I que he afegit el producte de codi de barres 1234567 a la venta
    Quan indico que el client ha entregat 30€ per a pagar en metàlic
    Aleshores el tpv mostra el següent: El canvi és: 7€ i la venta ha estat finalitzada i guardada al historial.

