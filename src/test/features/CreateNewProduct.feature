# language: ca

#noinspection SpellCheckingInspection
Característica: Create new product

  Escenari: Crear un nou producte
    Donat un producte "Prod1"
    Quan llisto els productes
    Aleshores obtinc una llista amb 1 element
    I l'element número 1 és el producte "Prod1"

  Escenari: Evitar la creació de productes amb noms duplicats
    Donat un producte "Prod1"
    Quan intento crear un producte "Prod1"
    Aleshores obtinc un error que diu: "Ja existeix un producte amb aquest nom"