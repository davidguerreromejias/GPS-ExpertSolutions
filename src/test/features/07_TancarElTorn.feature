# language: ca

#noinspection SpellCheckingInspection
Característica: Tancar el torn al tpv

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Pilota vermella", preu 10€, iva 21% i codi de barres 1111111 i que pertany als tipus "esports"
    I un producte amb nom "Pilota groga", preu 12€, iva 21% i codi de barres 2222222 i que pertany als tipus "esports"

  Escenari: No es pot tancar un torn si no n'hi ha cap d'iniciat
    Donat que no hi ha un torn iniciat
    Quan vull tancar el torn i a la caixa hi ha 25€
    Aleshores obtinc un error que diu: "No hi ha cap torn iniciat"

  Escenari: Un venedor vol tancar el torn i es produeix un quadrament invàlid
    Donat que en "Martí" ha iniciat el torn al tpv
    I al iniciar el torn hi havia 5€ a la caixa
    I que hi ha una venta iniciada
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I indico que el client ha entregat 23€ per a pagar en metàlic
    Quan vull tancar el torn i a la caixa hi ha 25€
    I indico que no vull repetir el quadrament
    Aleshores el sistema m'informa que el quadrament de la caixa és invàlid i la diferència és de 3€

  Escenari: Un venedor vol tancar el torn i es produeix un quadrament invàlid que es soluciona repetint el quadrament
    Donat que en "Martí" ha iniciat el torn al tpv
    I al iniciar el torn hi havia 5€ a la caixa
    I que hi ha una venta iniciada
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I indico que el client ha entregat 23€ per a pagar en metàlic
    Quan vull tancar el torn i a la caixa hi ha 15€
    I indico que vull repetir el quadrament i a la caixa hi ha 28€
    Aleshores el sistema confirma el quadrament i tanca el torn

  Escenari: Un venedor vol tancar el torn i no es produeix cap problema
    Donat que en "Martí" ha iniciat el torn al tpv
    I al iniciar el torn hi havia 10€ a la caixa
    I que hi ha una venta iniciada
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I indico que el client ha entregat 23€ per a pagar en metàlic
    Quan vull tancar el torn i a la caixa hi ha 33€
    Aleshores el sistema confirma el quadrament i tanca el torn

  Escenari: Un gestor vol consultar el llistat de quadraments invàlids
    Donat que en "Joan" ha iniciat sessio
    I en "Martí" ha tancat el seu torn amb un quadrament invàlid de 5€ negatius
    I en "Josep" ha tancat el seu torn amb un quadrament invàlid de 12€
    Quan vull obtenir un llistat dels quadraments invàlids
    Aleshores el sistema em mostra un llistat de quadraments invàlids que és
    """
    --Botiga--  --Caixa--  --Venedor--  --Quantitat--
    Girona 1 , 1 , Martí , -5€
    Girona 1 , 1 , Josep , 12€
    ---
    2 quadraments invàlids registrats
    """


