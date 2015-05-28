# language: ca

#noinspection SpellCheckingInspection
Característica: Aplicar un descompte de tipus % a un producte

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I que hi ha un descompte definit de tipus percentatge d'un 50%

  Escenari: Aplicar descompte percentatge existent a un producte
    Quan apreto aplicar descompte de tipus percentatge del 50% pel producte Optimus Prime
    Aleshores els descomptes per productes son
    """
    Descomptes actuals per productes:
    Optimus Prime: 50%
    """