# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un nou descompte al sistema

  Rerefons:
    Donat que estem a la botiga "Girona 1"
    I i ens agradaria afegir un descompte del 10%
    I un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567
    I que en "Joan" ha iniciat sessio coma  gestor

  Escenari: No es pot iniciar una venta si ja hi ha una venta iniciada
    Donat que hi ha una venta iniciada
    Quan inicio una nova venta
    Aleshores obtinc un error que diu: "Aquest tpv ja té una venta iniciada"

    """
    Optimus Prime - 23€/u x 1u = 23€
    10% descompte - 23€/u * 0.1 = 2.3€
    ---
    Total: 20.7€
    """