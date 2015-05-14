# language: ca

#noinspection SpellCheckingInspection
Característica: Aplicar descomptes del % a conjunts de productes

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I que hi ha un descompte definit en el sistema de tipus m x n on m és 3 i n és 2

  Escenari: Aplicar un descompte 3 x 2 a un producte
    Quan apreto sobre el descompte m x n existent
    I afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 3
    Aleshores la venta té 1 línia
    I línia de venta 1 és de 3 unitats de "Optimus Prime" a 20€ cada una per un total de 60€
    I línia de venta 1 és de 3 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "m x n" de 3 x 2 per un total de 40€
    I el total de la venta actual és de 40€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 3u = 60€
    3x2 -20€
    40€
    ---
    Total: 40€
    """