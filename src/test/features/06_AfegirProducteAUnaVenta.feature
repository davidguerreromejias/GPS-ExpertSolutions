# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un producte a una venta

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada

  Escenari: Afegir un producte per codi de barres a la venta
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 2
    Aleshores la última línia de la venta és de 2 unitats de "Optimus Prime" a 23€ cada una per un total de 46€

