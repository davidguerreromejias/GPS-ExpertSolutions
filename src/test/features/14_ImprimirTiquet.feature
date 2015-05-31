# language: ca

#noinspection SpellCheckingInspection
Característica: Acabar una venta i imprimir el tiquet

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Pilota de platja", preu 10€, iva 21% i codi de barres 1231231 i que pertany als tipus "esports,platja"
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv

  Escenari: Cobrar una venta en metàlic
    Donat que hi ha una venta iniciada
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I que el producte amb codi de barres 1231231 ha estat afegit a la venta actual amb la quantitat 2
    I que el client ha pagat 50€ en "efectiu"
    Quan vull imprimir el tiquet
    Aleshores el sistema imprimeix
    """
    Joguets i Joguines
    DATA
    L'atén Joan a la botiga Girona 1
    Caixa num 1
    -----   Producte   -----|-- €/u --|-- IVA --|-- # --|-- Total --
    Optimus Prime            23        21%       1       23
    Pilota de platja         10        21%       2       20
    ----------------------------------------------------------------
                                                         43
    Pagat:                                               50
    Canvi:                                               7
    """

  Escenari: Error si intentem fer un tiquet i la venta no està finalitzada
    Donat que hi ha una venta iniciada
    Quan vull imprimir el tiquet
    Aleshores obtinc un error que diu: "La venta no s'ha cobrat"