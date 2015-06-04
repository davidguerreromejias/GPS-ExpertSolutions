# language: ca

#noinspection SpellCheckingInspection
Característica: Acabar una venta i imprimir el tiquet

  Rerefons:
    Donat un producte amb nom "Optimus Prime", preu 25€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Pilota de platja", preu 10€, iva 21% i codi de barres 1231231 i que pertany als tipus "esports,platja"
    I un producte amb nom "Reproductor de música", preu 30€, iva 10% i codi de barres 7777777 i que pertany als tipus "electrònica,música"
    I un producte amb nom "CD de música", preu 5€, iva 21% i codi de barres 2222222 i que pertany als tipus "música"
    I un producte amb nom "Monopoly", preu 50€, iva 2% i codi de barres 3333333 i que pertany als tipus "joc de taula"
    I que estem al tpv número 1 de la botiga "Girona 1"
    I que existeix un login de tipus venedor pel treballador anomenat Joan amb el password 173529363
    I que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363
    I que hi ha un descompte de tipus percentatge definit en el sistema pels productes de tipus transformer d'un 20%
    I que hi ha un descompte de tipus m x n on m és 2 i n és 1 definit en el sistema pels productes de tipus "platja"
    I que hi ha un descompte de tipus regal que per la compra de Reproductor de música et regalen CD de música


  Escenari: Cobrar una venta en metàlic
    Donat que hi ha una venta iniciada en data "2015-06-05"
    I que el producte amb codi de barres 1234567 ha estat afegit a la venta actual amb la quantitat 1
    I que el producte amb codi de barres 1231231 ha estat afegit a la venta actual amb la quantitat 2
    I que el producte amb codi de barres 7777777 ha estat afegit a la venta actual amb la quantitat 1
    I que el producte amb codi de barres 2222222 ha estat afegit a la venta actual amb la quantitat 1
    I que el producte amb codi de barres 3333333 ha estat afegit a la venta actual amb la quantitat 1
    I que el client ha pagat 70€ en "efectiu"
    Quan vull imprimir el tiquet
    Aleshores el sistema imprimeix
    """
    Joguets i Joguines
    2015-06-05
    L'atén Joan a la botiga Girona 1
    Caixa num 1
    -----   Producte   -----|-- €/u --|-- IVA --|-- # --|-- Total --
    Optimus Prime            25        21%       1       25
    Descompte de 20%                                     -5
    Pilota de platja         10        21%       2       20
    Descompte de 2x1                                     -10
    Reproductor de música    30        10%       1       30
    De regal: 1 unitats de CD de música
    Monopoly                 50        2%        1       50
    ----------------------------------------------------------------
    Total Base Imposable 21%                             35
    Impost IVA 21%                                       10
    Total Base Imposable 10%                             27
    Impost IVA 10%                                       3
    Total Base Imposable 2%                              49
    Impost IVA 2%                                        1
    Total                                                60
    ----------------------------------------------------------------
    Pagat:                                               70
    Canvi:                                               10
    """

  Escenari: Error si intentem fer un tiquet i la venta no està finalitzada
    Donat que hi ha una venta iniciada
    Quan vull imprimir el tiquet
    Aleshores obtinc un error que diu: "La venta no s'ha cobrat"