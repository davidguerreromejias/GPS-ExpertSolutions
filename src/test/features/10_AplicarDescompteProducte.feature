# language: ca

#noinspection SpellCheckingInspection
Característica: Aplicar un descompte de tipus % a un producte

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I que hi ha un descompte definit de tipus % d'un 50%

  Escenari: Aplicar descompte existent a un producte
    Quan apreto sobre el descompte 50% existent pel producte "Optimus Prime 3" de la venta
    Aleshores el producte "Optimus Prime 3" val 15€