# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un nou descompte al sistema

  Rerefons:
    Donat que estem a la botiga "Girona 1" i ens agradaria afegir un descompte del 10% als productes de tipo platja
    I que en "Joan" ha iniciat sessio com a gestor

  Escenari: Iniciar sessio
    Quan inicio la sessio com a "Joan"
    Aleshores obtinc un error de inici de sessio que diu: "Hi ha una sessió iniciada pel gestor Joan"

  Escenari: Un venedor no pot inicar sessio ja que hi ha un altre sessio oberta d'un altre gestor
    Donat que en "Martí" ha iniciat sessio
    Quan inicio la sessio com a "Joan"
    Aleshores obtinc un error de inici de sessio que diu: "Hi ha una sessió iniciada pel gestor Marti"