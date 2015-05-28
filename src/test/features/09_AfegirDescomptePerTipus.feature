# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un nou descompte al sistema

  Rerefons:
    Donat que estem a la botiga "Girona 1"
    I que en "Joan" ha iniciat sessió com a gestor
    I que s'ha afegit un descompte del tipus percentatge del 40% als productes de piscina
    I que s'ha afegit un descompte del tipus m x n del 20% als productes de piscina

  Escenari: Afegir un descompte del tipus percentatge als objectes de tipus platja
    Quan afegeixo un descompte del tipus percentatge del 10% als productes de platja
    Aleshores existeix un descompte del tipus percentatge al sistema del 10% pels productes de platja

  Escenari: Afegir un descompte de tipus percentatge als objectes de tipus piscina quan ja n'hi ha un
    Quan afegeixo un descompte del tipus percentatge del 25% als productes de piscina
    Aleshores existeix un descompte del tipus percentatge al sistema del 25% pels productes de piscina

  Escenari: Consultar els descomptes per tipus de productes i de tipus de descompte percentatge que hi ha actius al sistema
    Quan afegeixo un descompte del tipus percentatge del 10% als productes de platja
    I afegeixo un descompte del tipus percentatge del 15% als productes de esports
    I afegeixo un descompte del tipus percentatge del 20% als productes de jocsTaula
    I afegeixo un descompte del tipus percentatge del 30% als productes de electronica
    Quan vull obtenir un llistat dels descomptes per tipus de productes i de tipus de descompte percentatge que hi ha actius al sistema
    Aleshores el sistema em mostra un llistat descomptes per tipus de productes i de tipus de descompte percentatge que hi ha actius al sistema
    """
    --Tipus Descompte--  --Descompte--  --Tipus Producte--
    percentatge , 40.0% , piscina
    percentatge , 10.0% , platja
    percentatge , 15.0% , esports
    percentatge , 20.0% , jocsTaula
    percentatge , 30.0% , electronica

    """

  Escenari: Afegir un descompte del tipus m x n als objectes de tipus platja
    Quan afegeixo un descompte del tipus m x n del 10% als productes de platja
    Aleshores existeix un descompte del tipus m x n al sistema pels productes de platja

  Escenari: Afegir un descompte de tipus m x n als objectes de tipus piscina quan ja n'hi ha un
    Quan afegeixo un descompte del tipus m x n del 45% als productes de piscina
    Aleshores existeix un descompte del tipus 3 x 2 al sistema del 40% pels productes de piscina

  Escenari: Consultar els descomptes per tipus de productes i de tipus de descompte m x n que hi ha actius al sistema
    Quan afegeixo un descompte del tipus m x n del 10% als productes de platja
    I afegeixo un descompte del tipus m x n del 15% als productes de esports
    I afegeixo un descompte del tipus m x n del 20% als productes de jocsTaula
    I afegeixo un descompte del tipus m x n del 30% als productes de electronica
    Quan vull obtenir un llistat dels descomptes per tipus de productes i de tipus de descompte percentatge que hi ha actius al sistema
    Aleshores el sistema em mostra un llistat descomptes per tipus de productes i de tipus de descompte percentatge que hi ha actius al sistema
    """
    --Tipus Descompte--  --Descompte--  --Tipus Producte--
    m x n , 40.0% , piscina
    m x n , 10.0% , platja
    m x n , 15.0% , esports
    m x n , 20.0% , jocsTaula
    m x n , 30.0% , electronica

    """