# language: ca

#noinspection SpellCheckingInspection
Característica: Tancar el torn al tpv

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Pilota vermella", preu 10€, iva 21% i codi de barres 1111111 i que pertany als tipus "esports"
    I un producte amb nom "Pilota groga", preu 12€, iva 21% i codi de barres 2222222 i que pertany als tipus "esports"
    I un administrador del sistema ha creat un nou login al sistema del tipus venedor pel treballador anomenat Joan amb el password 123098463
    I un administrador del sistema ha creat un nou login al sistema del tipus venedor pel treballador anomenat Marti amb el password 123098463
    I un administrador del sistema ha creat un nou login al sistema del tipus gestor pel treballador anomenat Josep amb el password 123098463

  Escenari: No es pot tancar un torn si no n'hi ha cap d'iniciat
    Donat que no hi ha un torn iniciat
    Quan vull tancar el torn i a la caixa hi ha 25€
    Aleshores obtinc un error que diu: "No hi ha cap torn iniciat"

  Escenari: Un venedor vol tancar el torn i es produeix un quadrament invàlid de menys de 10 euros
    Donat que l'usuari Joan accedeix al sistema amb el password 123098463
    I al iniciar el torn hi havia 5€ a la caixa
    I que hi ha una venta iniciada
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I indico que el client ha entregat 23€ per a pagar en metàlic
    Quan vull tancar el torn i a la caixa hi ha 25€
    Aleshores el sistema mostra el missatge
    """
    Hi ha 10 o menys euros de diferència en el quadrament
    """

  Escenari: Un venedor vol tancar el torn i es produeix un quadrament invàlid de més de 10 euros
    Donat que l'usuari Joan accedeix al sistema amb el password 123098463
    I al iniciar el torn hi havia 5€ a la caixa
    I que hi ha una venta iniciada
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I indico que el client ha entregat 23€ per a pagar en metàlic
    Quan vull tancar el torn i a la caixa hi ha 7€
    Aleshores el sistema mostra el missatge
    """
    Hi ha més de 10 euros de diferència en el quadrament
    """

  Escenari: Un venedor vol tancar el torn i es produeix un quadrament invàlid que es soluciona repetint el quadrament
    Donat que l'usuari Joan accedeix al sistema amb el password 123098463
    I al iniciar el torn hi havia 5€ a la caixa
    I que hi ha una venta iniciada
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I indico que el client ha entregat 23€ per a pagar en metàlic
    Quan vull tancar el torn i a la caixa hi ha 15€
    I indico que vull repetir el quadrament i a la caixa hi ha 28€
    Aleshores el sistema mostra el missatge
    """
    El torn s'ha tancat correctament
    """

  Escenari: Un venedor vol tancar el torn i no es produeix cap problema
    Donat que l'usuari Joan accedeix al sistema amb el password 123098463
    I al iniciar el torn hi havia 10€ a la caixa
    I que hi ha una venta iniciada
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I indico que el client ha entregat 23€ per a pagar en metàlic
    Quan vull tancar el torn i a la caixa hi ha 33€
    Aleshores el sistema mostra el missatge
    """
    El torn s'ha tancat correctament
    """

  Escenari: Un gestor vol consultar el llistat de quadraments invàlids
    Donat que en "Josep" ha iniciat sessio
    I en "Martí" ha tancat el seu torn amb un quadrament invàlid de 5€ negatius
    I en "Joan" ha tancat el seu torn amb un quadrament invàlid de 10€
    Quan vull obtenir un llistat dels quadraments invàlids
    Aleshores el sistema em mostra un llistat de quadraments invàlids que és
    """
    --Botiga--  --Caixa--  --Venedor--  --Quantitat--
    Girona 1 , 1 , Martí , -5€
    Girona 1 , 1 , Joan , 10€
    ---
    2 quadraments invàlids registrats
    """


