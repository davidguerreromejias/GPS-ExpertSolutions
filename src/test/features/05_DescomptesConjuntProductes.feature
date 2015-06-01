# language: ca

#noinspection SpellCheckingInspection
Característica: Aplicar descomptes de tipus % a conjunts de productes

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Bumblebee", preu 30€, iva 21% i codi de barres 1234568 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Gimli", preu 40€, iva 21% i codi de barres 1234569 i que pertany als tipus "figura d'acció,senyor dels anells"
    I un producte amb nom "Pilota verda", preu 10€, iva 21% i codi de barres 2234568 i que pertany als tipus "esports"
    I un producte amb nom "Pilota blava", preu 15€, iva 21% i codi de barres 2234569 i que pertany als tipus "esports"
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que existeix un login de tipus venedor pel treballador anomenat Joan amb el password 173529363
    I que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363
    I que hi ha una venta iniciada
    I que hi ha un descompte de tipus percentatge definit en el sistema pels productes de tipus transformer d'un 50%
    I que hi ha un descompte de tipus percentatge definit en el sistema pels productes de tipus esports d'un 20%

  Escenari: Aplicar un descompte de tipus percentatge a un producte que pertany al conjunt de productes transformer
    Quan afegeixo el producte de codi de barres 1234567 a la venta
    Aleshores la venta té 1 línia
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "percentatge" del "50%" per pertànyer a "transformer" per un total de 10€
    I el total de la venta actual és de 10€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 1u = 20€
    -50% en transformer -10€
    ---
    Total: 10€
    """
  Escenari: Aplicar un descompte de tipus percentatge a 2 productes que pertanyen a un conjunt de productes amb descompte
    Quan afegeixo el producte de codi de barres 2234568 a la venta
    I afegeixo el producte de codi de barres 2234569 a la venta
    Aleshores la venta té 2 línies
    I línia de venta 1 és de 1 unitats de "Pilota verda" a 10€ cada una amb un descompte de tipus "percentatge" del "20%" per pertànyer a "esports" per un total de 8€
    I línia de venta 2 és de 1 unitats de "Pilota blava" a 15€ cada una amb un descompte de tipus "percentatge" del "20%" per pertànyer a "esports" per un total de 12€
    I el total de la venta actual és de 20€
    I la pantalla del client del tpv mostra
    """
    Pilota verda - 10€/u x 1u = 10€
    -20% en esports -2€
    Pilota blava - 15€/u x 1u = 15€
    -20% en esports -3€
    ---
    Total: 20€
    """
  Escenari: Aplicar un descompte de tipus percentatge a 2 productes que pertanyen a un conjunt de productes amb descompte i no aplicar a un que no
    Quan afegeixo el producte de codi de barres 1234567 a la venta
    I afegeixo el producte de codi de barres 1234568 a la venta
    I afegeixo el producte de codi de barres 1234569 a la venta
    Aleshores la venta té 3 línies
    I línia de venta 1 és de 1 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "percentatge" del "50%" per pertànyer a "transformer" per un total de 10€
    I línia de venta 2 és de 1 unitats de "Bumblebee" a 30€ cada una amb un descompte de tipus "percentatge" del "50%" per pertànyer a "transformer" per un total de 15€
    I línia de venta 3 és de 1 unitats de "Gimli" a 40€ cada una per un total de 40€
    I el total de la venta actual és de 65€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 1u = 20€
    -50% en transformer -10€
    Bumblebee - 30€/u x 1u = 30€
    -50% en transformer -15€
    Gimli - 40€/u x 1u = 40€
    ---
    Total: 65€
    """