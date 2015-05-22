# language: ca

#noinspection SpellCheckingInspection
Característica: Cobrar una venta en metàlic

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv

  Escenari: Cobrar una venta en metàlic
    Donat que hi ha una venta iniciada
    I que he afegit el producte de codi de barres 1234567 a la venta
    Quan indico que el client ha entregat 30€ per a pagar en metàlic
    Aleshores el tpv mostra el següent: El canvi és: 7€ i la venta ha estat finalitzada.

  Escenari: Error si intentem cobrar una venta sense productes
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

