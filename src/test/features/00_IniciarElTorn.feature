# language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar el torn al tpv

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"

  Escenari: Iniciar el torn
    Quan inicio el torn al tpv com a "Joan" i hi ha 15€ a la caixa
    Aleshores el tpv està en ús per en "Joan"

  Escenari: Un venedor no pot inicar el torn si ja hi ha un altre venedor al tpv
    Donat que en "Martí" ha iniciat el torn al tpv
    Quan inicio el torn al tpv com a "Joan" i hi ha 13€ a la caixa
    Aleshores obtinc un error que diu: "Aquest tpv està en ús per Martí"