# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un regal a un producte

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 2", preu 2€, iva 21% i codi de barres 2234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 3", preu 2€, iva 21% i codi de barres 2334567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 4", preu 2€, iva 21% i codi de barres 2334577 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 5", preu 2€, iva 21% i codi de barres 1234777 i que pertany als tipus "figura d'acció,transformer"
    I que el producte amb codi de barres 2234567 ha estat afegit a la venta actual amb la quantitat 1
    I que hi ha un descompte de tipus regal que per la compra de Optimus Prime 2 et regalen Optimus Prime, Optimus Prime 3
    I que hi ha un descompte de tipus regal que per la compra de Optimus Prime 4 et regalen Optimus Prime, Optimus Prime 3
    I que hi ha un descompte de tipus regal que per la compra de Optimus Prime 5 et regalen Optimus Prime, Optimus Prime


  Escenari: Afegir producte regal
    Quan afegeixo un descompte de tipus regal que per la compra de Optimus Prime et regalen Optimus Prime 2
    Aleshores els productes que tenen regals son

"""
Productes que tenen regals:
Per la compra de Optimus Prime 2 s'obté de regal Optimus Prime i Optimus Prime 3
Per la compra de Optimus Prime 4 s'obté de regal Optimus Prime i Optimus Prime 3
Per la compra de Optimus Prime 5 s'obté de regal Optimus Prime i Optimus Prime
Per la compra de Optimus Prime s'obté de regal Optimus Prime 2
"""

  Escenari: Afegir producte regal a la venta
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 1
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime - 20€/u x 1u = 0€ (REGAL)
    ---
    Total: 2€
    """

  Escenari: Afegir producte no regal a la venta
    Quan afegeixo el producte de codi de barres 2334577 a la venta amb quantitat 1
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime 4 - 2€/u x 1u = 2€
    ---
    Total: 4€
    """

  Escenari: Afegir producte 1 regal i 1 no regal a la venta
    Quan afegeixo el producte de codi de barres 2334567 a la venta amb quantitat 2
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime 3 - 2€/u x 1u = 0€ (REGAL)
    Optimus Prime 3 - 2€/u x 1u = 2€
    ---
    Total: 4€
    """

  Escenari: Afegir 2 productes amb 2 regals
    Quan afegeixo el producte de codi de barres 2334577 a la venta amb quantitat 2
    I afegeixo el producte de codi de barres 2334567 a la venta amb quantitat 2
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime 4 - 2€/u x 2u = 4€
    Optimus Prime 3 - 2€/u x 2u = 0€ (REGAL)
    ---
    Total: 6€
    """


  Escenari: Afegir 2 regals del mateix producte
    Quan afegeixo el producte de codi de barres 1234777 a la venta amb quantitat 1
    I afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 3
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime 5 - 2€/u x 1u = 2€
    Optimus Prime - 20€/u x 3u = 0€ (REGAL)
    ---
    Total: 4€
    """


  Escenari: Afegir producte amb regals al final
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 2
    I afegeixo el producte de codi de barres 1234777 a la venta amb quantitat 1
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime - 20€/u x 1u = 0€ (REGAL)
    Optimus Prime - 20€/u x 1u = 0€ (REGAL)
    Optimus Prime 5 - 2€/u x 1u = 2€
    ---
    Total: 4€
    """