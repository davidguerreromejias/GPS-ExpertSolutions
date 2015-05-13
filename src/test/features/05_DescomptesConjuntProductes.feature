# language: ca

#noinspection SpellCheckingInspection
Característica: Aplicar descomptes del % a conjunts de productes

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567
    I un producte amb nom "Bumblebee", preu 30€, iva 21% i codi de barres 1234568
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I que hi ha un descompte definit en el sistema de tipus percentatge d'un 50%

  Escenari: Aplicar un descompte de tipus percentatge a un producte
    Quan apreto sobre el descompte 50% existent
    Aleshores la venta té 1 línia
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 20€ cada una per un total de 20€
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "percentatge" del 50% per un total de 10€
    I el total de la venta actual és de 10€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 1u = 20€
    -50% 10€
    10€
    ---
    Total: 10€
    """