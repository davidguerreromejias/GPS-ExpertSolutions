# language: ca

#noinspection SpellCheckingInspection
Característica: Aplicar descomptes del % a conjunts de productes

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567
    I un producte amb nom "Bumblebee", preu 30€, iva 21% i codi de barres 1234568
    I un producte amb nom "Gimli", preu 40€, iva 21% i codi de barres 1234569
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I que hi ha un descompte definit en el sistema de tipus percentatge d'un 50%

  Escenari: Aplicar un descompte de tipus percentatge a un producte
    Quan apreto sobre el descompte 50% existent
    I afegeixo el producte de codi de barres 1234567 a la venta
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
  Escenari: Aplicar un descompte de tipus percentatge a 2 productes
    Quan apreto sobre el descompte 50% existent
    I afegeixo el producte de codi de barres 1234567 a la venta
    I afegeixo el producte de codi de barres 1234568 a la venta
    Aleshores la venta té 2 línies
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 20€ cada una per un total de 20€
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "percentatge" del 50% per un total de 10€
    I línia de venta 2 és de 1 unitats de "Bumblebee" a 30€ cada una per un total de 30€
    I línia de venta 2 és de 1 unitats de "Bumblebee" a 30€ cada una amb un descompte de tipus "percentatge" del 50% per un total de 15€
    I el total de la venta actual és de 25€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 1u = 20€
    -50% 10€
    10€
    Bumblebee - 30€/u x 1u = 30€
    -50% 15€
    15€
    ---
    Total: 25€
    """
  Escenari: Aplicar un descompte de tipus percentatge a 2 productes i al següent producte no
    Quan apreto sobre el descompte 50% existent
    I afegeixo el producte de codi de barres 1234567 a la venta
    I afegeixo el producte de codi de barres 1234568 a la venta
    I apreto sobre el descompte 50% existent una altra vegada
    I afegeixo el producte de codi de barres 1234569 a la venta
    Aleshores la venta té 3 línies
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 20€ cada una per un total de 20€
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "percentatge" del 50% per un total de 10€
    I línia de venta 2 és de 1 unitats de "Bumblebee" a 30€ cada una per un total de 30€
    I línia de venta 2 és de 1 unitats de "Bumblebee" a 30€ cada una amb un descompte de tipus "percentatge" del 50% per un total de 15€
    I línia de venta 3 és de 1 unitats de "Gimli" a 40€ cada una per un total de 40€
    I el total de la venta actual és de 65€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 1u = 20€
    -50% 10€
    10€
    Bumblebee - 30€/u x 1u = 30€
    -50% 15€
    15€
    Gimli - 40€/u x 1u = 40€
    ---
    Total: 65€
    """