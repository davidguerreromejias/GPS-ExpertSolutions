# language: ca

#noinspection SpellCheckingInspection
Característica: Tancar el torn al tpv

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"

  Escenari: No es pot tancar un torn si no n'hi ha cap d'iniciat
    Donat que no hi ha un torn iniciat
    Quan vull tancar el torn i a la caixa hi ha 25€
    Aleshores obtinc un error que diu: "No hi ha cap torn iniciat"

  Escenari: Un venedor no pot inicar el torn si ja hi ha un altre venedor al tpv
    Donat que en "Martí" ha iniciat el torn al tpv
    //ventes que s'han fet
    Quan vull tancar el torn i a la caixa hi ha 15€
    Aleshores el sistema m'informa que el quadrament de la caixa és invàlid

  Escenari: Un venedor no pot inicar el torn si ja hi ha un altre venedor al tpv
    Donat que en "Martí" ha iniciat el torn al tpv
    //ventes que s'han fet
    Quan vull tancar el torn i a la caixa hi ha 15€
    Aleshores el sistema confirma el quadrament i tanca el torn

