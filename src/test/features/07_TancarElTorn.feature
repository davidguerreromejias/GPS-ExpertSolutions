# language: ca

#noinspection SpellCheckingInspection
Característica: Tancar el torn al tpv

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"

  Escenari: No es pot tancar un torn si no n'hi ha cap d'iniciat
    Donat que no hi ha un torn iniciat
    Quan vull tancar el torn
    Aleshores obtinc un error que diu: "No hi ha cap torn iniciat"

  Escenari: Un venedor no pot inicar el torn si ja hi ha un altre venedor al tpv
    Donat que en "Martí" ha iniciat el torn al tpv
    Quan inicio el torn al tpv com a "Joan"
    Aleshores obtinc un error que diu: "Aquest tpv està en ús per Martí"