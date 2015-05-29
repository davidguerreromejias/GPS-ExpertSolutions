# language: ca

#noinspection SpellCheckingInspection
Característica: Aplicar descomptes m x n a conjunts de productes

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Gollum", preu 25€, iva 21% i codi de barres 1234568 i que pertany als tipus "figura d'acció,senyor dels anells"
    I que hi ha un descompte de tipus m x n on m és 3 i n és 2 definit en el sistema pels productes de tipus "figura d'acció"

  Escenari: Aplicar un descompte 3 x 2 a un producte que pertany al conjunt de productes tranformer
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 3
    Aleshores la venta té 1 línia
    I línia de venta 1 és de 3 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "m x n" del "3x2" per pertànyer a "figura d'acció" per un total de 40€
    I el total de la venta actual és de 40€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 3u = 60€
    3x2 -20€
    ---
    Total: 40€
    """

  Escenari: Aplicar un descompte 3 x 2 a productes d'un mateix conjunt, el producte actual és el més barat
    Donat que el producte amb codi de barres 1234568 ha estat afegit a la venta actual amb la quantitat 1
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 2
    Aleshores la venta té 2 línies
    I línia de venta 1 és de 1 unitats de "Gollum" a 25€ cada una per un total de 25€
    I línia de venta 2 és de 2 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "m x n" del "3x2" per pertànyer a "figura d'acció" per un total de 20€
    I el total de la venta actual és de 45€
    I la pantalla del client del tpv mostra
    """
    Gollum - 25€/u x 1u = 25€
    Optimus Prime - 20€/u x 2u = 40€
    3x2 -20€
    ---
    Total: 45€
    """

  Escenari: Aplicar un descompte 3 x 2 a productes d'un mateix conjunt, el producte anterior és el més baraat
    Donat que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 2
    Quan afegeixo el producte de codi de barres 1234568 a la venta amb quantitat 1
    Aleshores la venta té 2 línies
    I línia de venta 1 és de 2 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "m x n" del "3x2" per pertànyer a "figura d'acció" per un total de 20€
    I línia de venta 2 és de 1 unitats de "Gollum" a 25€ cada una per un total de 25€
    I el total de la venta actual és de 45€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 2u = 40€
    3x2 -20€
    Gollum - 25€/u x 1u = 25€
    ---
    Total: 45€
    """