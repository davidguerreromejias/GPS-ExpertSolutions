# language: ca

#noinspection SpellCheckingInspection
Característica: Aplicar descomptes m x n a conjunts de productes

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que existeix un login de tipus venedor pel treballador anomenat Joan amb el password 173529363
    I que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Gollum", preu 25€, iva 21% i codi de barres 1234568 i que pertany als tipus "figura d'acció,senyor dels anells"
    I un producte amb nom "Pilota verda", preu 10€, iva 21% i codi de barres 2234567 i que pertany als tipus "esports"
    I un producte amb nom "Raqueta", preu 20€, iva 21% i codi de barres 2234568 i que pertany als tipus "esports"
    I que hi ha un descompte de tipus m x n on m és 3 i n és 2 definit en el sistema pels productes de tipus "figura d'acció"
    I que hi ha un descompte de tipus m x n on m és 5 i n és 3 definit en el sistema pels productes de tipus "esports"

  Escenari: Aplicar un descompte 3 x 2 a un producte que pertany al conjunt de productes figura d'acció
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 3
    Aleshores la venta té 1 línia
    I línia de venta 1 és de 3 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "m x n" del "3x2" per pertànyer a "figura d'acció" per un total de 40€
    I el total de la venta actual és de 40€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 3u = 60€
    3x2 en figura d'acció -20€
    ---
    Total: 40€
    """

  Escenari: Aplicar un descompte 3 x 2 a un producte amb quantitat superior al descompte i que pertany al conjunt de productes figura d'acció
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 4
    Aleshores la venta té 1 línia
    I línia de venta 1 és de 4 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "m x n" del "3x2" per pertànyer a "figura d'acció" per un total de 60€
    I el total de la venta actual és de 60€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 4u = 80€
    3x2 en figura d'acció -20€
    ---
    Total: 60€
    """

  Escenari: Aplicar un descompte 3 x 2 a un producte amb quantitat exactament el doble que el descompte i que pertany al conjunt de productes figura d'acció
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 6
    Aleshores la venta té 1 línia
    I línia de venta 1 és de 6 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "m x n" del "3x2" per pertànyer a "figura d'acció" per un total de 80€
    I el total de la venta actual és de 80€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 6u = 120€
    3x2 en figura d'acció x2 -40€
    ---
    Total: 80€
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
    3x2 en figura d'acció -20€
    ---
    Total: 45€
    """

  Escenari: Aplicar un descompte 3 x 2 a productes d'un mateix conjunt, el producte anterior és el més barat
    Donat que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 2
    Quan afegeixo el producte de codi de barres 1234568 a la venta amb quantitat 1
    Aleshores la venta té 2 línies
    I línia de venta 1 és de 2 unitats de "Optimus Prime" a 20€ cada una amb un descompte de tipus "m x n" del "3x2" per pertànyer a "figura d'acció" per un total de 20€
    I línia de venta 2 és de 1 unitats de "Gollum" a 25€ cada una per un total de 25€
    I el total de la venta actual és de 45€
    I la pantalla del client del tpv mostra
    """
    Optimus Prime - 20€/u x 2u = 40€
    3x2 en figura d'acció -20€
    Gollum - 25€/u x 1u = 25€
    ---
    Total: 45€
    """

  Escenari: Aplicar un descompte 5 x 3 a productes d'un mateix conjunt, total d'unitats més gran que el descompte i el producte actual és el més barat
    Donat que el producte amb codi de barres 2234568 ha estat afegit a la venta actual amb la quantitat 6
    Quan afegeixo el producte de codi de barres 2234567 a la venta amb quantitat 1
    Aleshores la venta té 2 línies
    I línia de venta 1 és de 6 unitats de "Raqueta" a 20€ cada una amb un descompte de tipus "m x n" del "5x3" per pertànyer a "esports" per un total de 100€
    I línia de venta 2 és de 1 unitats de "Pilota verda" a 10€ cada una amb un descompte de tipus "m x n" del "5x3" per pertànyer a "esports" per un total de 0€
    I el total de la venta actual és de 100€
    I la pantalla del client del tpv mostra
    """
    Raqueta - 20€/u x 6u = 120€
    5x3 en esports -20€
    Pilota verda - 10€/u x 1u = 10€
    5x3 en esports -10€
    ---
    Total: 100€
    """