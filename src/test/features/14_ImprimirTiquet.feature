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
    Quan indico que el client ha entregat 30€ per a pagar en metàlic
    Aleshores el tpv mostra el següent: El canvi és: 7€ i la venta ha estat finalitzada.

  Escenari: Error si intentem fer un tiquet i la venta no està finalitzada
    Donat que hi ha una venta iniciada
    Quan indico que el client ha entregat 30€ per a pagar en metàlic
    Aleshores obtinc un error que diu: "No es pot cobrar una venta sense cap producte"

  Escenari: Error si intentem cobrar una venta no iniciada
    Quan indico que el client ha entregat 30€ per a pagar en metàlic
    Aleshores obtinc un error que diu: "No es pot cobrar una venta si no està iniciada"

  Escenari: Error si intentem cobrar una venta amb menys diners de l'import total de la venda
    Donat que hi ha una venta iniciada
    I que he afegit el producte de codi de barres 1234567 a la venta
    Quan indico que el client ha entregat 20€ per a pagar en metàlic
    Aleshores obtinc un error que diu: "La quantitat rebuda és inferior a l'import de la venda."

