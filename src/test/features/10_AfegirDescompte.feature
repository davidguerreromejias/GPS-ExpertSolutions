# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un nou descompte al sistema

  Rerefons:
    Donat que estem a la botiga "Girona 1"
    I que en "Joan" ha iniciat sessio com a gestor

  Escenari: Afegir un descompte als objectes de tipos platja
    Quan afageix un descompte del 10% als productes de platja
    Aleshores existeix un descompte al sistema del 10% pels productes de platja


  Escenari: Afegir un descompte als objectes de tipos platja
    Quan afageix un descompte del 10% als productes de platja
    Quan afageix un descompte del 25% als productes de platja
    Aleshores existeix un descompte al sistema del 25% pels productes de platja

  Escenari: Afegir un descompte als objectes de tipos platja
    Quan afageix un descompte del 10% als productes de platja
    Quan borrar el descompte dels productes de platja
    Aleshores no existeix un descompte al sistema pels productes de  platja