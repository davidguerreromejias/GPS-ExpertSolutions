# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un nou descompte al sistema

  Rerefons:
    Donat que estem a la botiga "Girona 1"
    I que en "Joan" ha iniciat sessió com a gestor
    I que s'ha afegit un descompte del tipus percentatge del 40% als productes de piscina

  Escenari: Afegir un descompte als objectes de tipus platja
    Quan afegeixo un descompte del tipus percentatge del 10% als productes de platja
    Aleshores existeix un descompte del tipus percentatge al sistema del 10% pels productes de platja


  Escenari: Afegir un descompte de tipus percentatge als objectes de tipus piscina quan ja n'hi ha un
    Quan afegeixo un descompte del tipus percentatge del 25% als productes de piscina
    Aleshores existeix un descompte del tipus percentatge al sistema del 25% pels productes de piscina