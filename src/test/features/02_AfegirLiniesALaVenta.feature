# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir línies a la venta

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada

  Escenari: Afegir un producte per codi de barres a la venta
    Quan afegeixo el producte de codi de barres 1234567 a la venta
    Aleshores la venta té 1 línia
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 23€ cada una per un total de 23€
    I el total de la venta actual és de 23€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 23€/u x 1u = 23€
    ---
    Total: 23€
    """