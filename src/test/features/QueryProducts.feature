# language: ca

#noinspection SpellCheckingInspection
Característica: Consulta del catàleg

  Rerefons:
    Donat un producte "ProdA"
    I un producte "ProdC"
    I un producte "ProdB"

  Escenari: Llistar productes
    Quan llisto els productes
    Aleshores obtinc una llista amb 3 elements
    I l'element número 1 és el producte "ProdA"
    I l'element número 2 és el producte "ProdC"
    I l'element número 3 és el producte "ProdB"

  Escenari: Llistar productes ordenats per nom
    Quan llisto els productes ordenats per nom
    Aleshores obtinc una llista amb 3 elements
    I l'element número 1 és el producte "ProdA"
    I l'element número 2 és el producte "ProdB"
    I l'element número 3 és el producte "ProdC"

  Escenari: Consultar un producte per nom
    Quan I look for the product "ProdA"
    Aleshores I get a product
    I the product name is "ProdA"
    I the product has an id
