# language: ca

#noinspection SpellCheckingInspection
Característica: Cobrar una venta amb targeta

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I que estem al tpv número 1 de la botiga "Girona 1"
    I un administrador del sistema ha creat un nou login al sistema del tipus venedor pel treballador anomenat Joan amb el password 123098463
    I que l'usuari Joan accedeix al sistema amb el password 123098463

  Escenari: Cobrar una venta amb targeta
    Donat que hi ha una venta iniciada
    I que he afegit el producte de codi de barres 1234567 a la venta
    Quan indico que el client ha entregat la targeta per a pagar en targeta
    Aleshores el tpv ens mostra el següent: Targeta acceptada i la venta ha estat finalitzada.

  Escenari: Error si intentem cobrar una venta sense productes
    Donat que hi ha una venta iniciada
    Quan indico que el client ha entregat la targeta per a pagar en targeta
    Aleshores obtinc un error que diu: "No es pot cobrar una venta sense cap producte"

  Escenari: Error si intentem cobrar una venta no iniciada
    Quan indico que el client ha entregat la targeta per a pagar en targeta
    Aleshores obtinc un error que diu: "No es pot cobrar una venta si no està iniciada"
