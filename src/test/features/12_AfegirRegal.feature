# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un regal a un producte

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 2", preu 2€, iva 21% i codi de barres 2234567 i que pertany als tipus "figura d'acció,transformer"
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I que hi ha un descompte definit de tipus regal que inclou Optimus Prime 2

  Escenari: Afegir regal a la venta
    Quan apreto aplicar descompte de tipus regal que inclou Optimus Prime 2 pel producte Optimus Prime
    Aleshores els productes que tenen regals son
    """
    Productes que tenen regals:
    Per la compra de Optimus Prime s'obté de regal Optimus Prime 2
    """