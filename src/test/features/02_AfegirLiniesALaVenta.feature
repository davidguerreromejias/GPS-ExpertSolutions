# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir línies a la venta

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Pilota vermella", preu 10€, iva 21% i codi de barres 1111111 i que pertany als tipus "esports"
    I un producte amb nom "Pilota groga", preu 12€, iva 21% i codi de barres 2222222 i que pertany als tipus "esports"
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada

  Escenari: Afegir un producte per codi de barres i/o nom a la venta
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 1
    I afegeixo el producte de nom "Pilota vermella" a la venta amb quantitat 2
    Aleshores la venta té 2 línia
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 23€ cada una per un total de 23€
    I línia de venta 2 és de 2 unitats de "Pilota vermella" a 10€ cada una per un total de 20€
    I el total de la venta actual és de 43€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 23€/u x 1u = 23€
    Pilota vermella - 10€/u x 2u = 20€
    ---
    Total: 43€
    """

  Escenari: Buscar un producte i afegirlo a la venta
    Quan busco un producte que es digui "Pilota"
    I afegeixo el 1 element dels resultats a la venta amb quantitat 1
    I afegeixo el 2 element dels resultats a la venta amb quantitat 2
    Aleshores la venta té 2 línia
    I el resultat de la cerca és
    """
    1 : Pilota groga - 12€/u
    2 : Pilota vermella - 10€/u
    ---
    2 productes trobats
    """
    I línia de venta 1 és de 1 unitats de "Pilota groga" a 12€ cada una per un total de 12€
    I línia de venta 2 és de 2 unitats de "Pilota vermella" a 10€ cada una per un total de 20€
    I el total de la venta actual és de 32€
    I la pantalla del client del tpv mostra
    """
    Pilota groga - 12€/u x 1u = 12€
    Pilota vermella - 10€/u x 2u = 20€
    ---
    Total: 32€
    """