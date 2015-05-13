# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un nou descompte al sistema

  Rerefons:
    Donat que estem a la botiga "Girona 1"
    I i ens agradaria afegir un descompte del 10% als productes de tipo platja
    I que en "Joan" ha iniciat sessio com a gestor

  Escenari: Afegir un descompte mitjançant el teclat
    Quan un gestor ha iniciat la sessio
    Aleshores pot afegir un descompte nou
    I selecciona el tipo de productes els qual vol afegir el descompte i escriu el descompte en %
    I la pantalla mostra el nou descompte afegit al sistema

    """
    Pilota Platja - 23€/u x 1u = 23€
    10% descompte - 23€/u * 0.1 = 2.3€
    ---
    Total: 20.7€
    """