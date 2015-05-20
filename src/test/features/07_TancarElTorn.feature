# language: ca

#noinspection SpellCheckingInspection
Característica: Tancar el torn al tpv

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"

  Escenari: No es pot tancar un torn si no n'hi ha cap d'iniciat
    Donat que no hi ha un torn iniciat
    Quan vull tancar el torn i a la caixa hi ha 25€
    Aleshores obtinc un error que diu: "No hi ha cap torn iniciat"

  Escenari: Un venedor vol tancar el torn i es produeix un quadrament invàlid
    Donat que en "Martí" ha iniciat el torn al tpv
    I al iniciar el torn hi havia 5€ a la caixa
    I s'ha fet una venta en efectiu per valor de 15€
    Quan vull tancar el torn i a la caixa hi ha 15€
    Aleshores el sistema m'informa que el quadrament de la caixa és invàlid i

  Escenari: Un venedor vol tancar el torn i no es produeix cap problema
    Donat que en "Martí" ha iniciat el torn al tpv
    I al iniciar el torn hi havia 10€ a la caixa
    I s'ha fet una venta en efectiu per valor de 10€
    Quan vull tancar el torn i a la caixa hi ha 20€
    Aleshores el sistema confirma el quadrament i tanca el torn

  Escenari: Un gestor vol consultar el llistat de quadraments invàlids
    Donat que en "Josep" ha iniciat el

