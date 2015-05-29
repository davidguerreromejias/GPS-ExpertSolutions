# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un nou descompte al sistema

  Rerefons:
    Donat que estem a la botiga "Girona 1"
    I que en "Joan" ha iniciat sessió com a gestor
    I que s'ha afegit un descompte del tipus percentatge del 40% als productes de piscina
    I afegeixo un descompte del tipus m x n pels productes de tipus piscina tal que quan en compres 3 en pagues 2

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
    Quan afegeixo un descompte del tipus m x n pels productes de tipus platja tal que quan en compres 3 en pagues 2
    Aleshores existeix un descompte del tipus m x n pels productes de tipus platja tal que quan en compres 3 en pagues 2

  Escenari: Afegir un descompte de tipus m x n als objectes de tipus piscina quan ja n'hi ha un
    Quan afegeixo un descompte del tipus m x n pels productes de tipus piscina tal que quan en compres 5 en pagues 3
    Aleshores existeix un descompte del tipus m x n pels productes de tipus piscina tal que quan en compres 5 en pagues 2

  Escenari: Consultar els descomptes per tipus de productes i de tipus de descompte m x n que hi ha actius al sistema
    Quan afegeixo un descompte del tipus m x n pels productes de tipus platja tal que quan en compres 5 en pagues 2
    I afegeixo un descompte del tipus m x n pels productes de tipus esports tal que quan en compres 2 en pagues 1
    I afegeixo un descompte del tipus m x n pels productes de tipus jocsTaula tal que quan en compres 4 en pagues 3
    I afegeixo un descompte del tipus m x n pels productes de tipus electronica tal que quan en compres 3 en pagues 2
    Quan vull obtenir un llistat dels descomptes per tipus de productes i de tipus de descompte m x n que hi ha actius al sistema
    Aleshores el sistema em mostra un llistat descomptes per tipus de productes i de tipus de descompte m x n que hi ha actius al sistema
    """
    --Tipus Descompte--  --Compres--  --En pagues--  --Tipus Producte--
    m x n , 3 , 2 , piscina
    m x n , 5 , 2 , platja
    m x n , 2 , 1 , esports
    m x n , 4 , 3 , jocsTaula
    m x n , 3 , 2 , electronica

    """

  Escenari: Consultar els tots descomptes per tipus de productes que hi ha actius al sistema
    Quan afegeixo un descompte del tipus percentatge del 10% als productes de platja
    I afegeixo un descompte del tipus percentatge del 15% als productes de esports
    I afegeixo un descompte del tipus percentatge del 20% als productes de jocsTaula
    I afegeixo un descompte del tipus percentatge del 30% als productes de electronica
    I afegeixo un descompte del tipus m x n pels productes de tipus platja tal que quan en compres 5 en pagues 2
    I afegeixo un descompte del tipus m x n pels productes de tipus esports tal que quan en compres 2 en pagues 1
    I afegeixo un descompte del tipus m x n pels productes de tipus jocsTaula tal que quan en compres 4 en pagues 3
    I afegeixo un descompte del tipus m x n pels productes de tipus electronica tal que quan en compres 3 en pagues 2
    Quan vull obtenir un llistat dels descomptes per tipus de productes que hi ha actius al sistema
    Aleshores el sistema em mostra un llistat descomptes per tipus de productes que hi ha actius al sistema
    """
    --Tipus Descompte--  --Descompte--  --Tipus Producte--
    percentatge , 40.0% , piscina
    percentatge , 10.0% , platja
    percentatge , 15.0% , esports
    percentatge , 20.0% , jocsTaula
    percentatge , 30.0% , electronica

    --Tipus Descompte--  --Compres--  --En pagues--  --Tipus Producte--
    m x n , 3 , 2 , piscina
    m x n , 5 , 2 , platja
    m x n , 2 , 1 , esports
    m x n , 4 , 3 , jocsTaula
    m x n , 3 , 2 , electronica

    """