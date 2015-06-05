# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un regal a un producte

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I un administrador del sistema ha creat un nou login al sistema del tipus venedor pel treballador anomenat Joan amb el password 173529363
    I un administrador del sistema ha creat un nou login al sistema del tipus gestor pel treballador anomenat Pepe amb el password 173529366
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 20€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 2", preu 2€, iva 21% i codi de barres 2234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 3", preu 2€, iva 21% i codi de barres 2334567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 4", preu 2€, iva 21% i codi de barres 2334577 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Optimus Prime 5", preu 2€, iva 21% i codi de barres 1234777 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Pelota de fútbol", preu 2€, iva 21% i codi de barres 1111111 i que pertany als tipus "deporte"
    I que el producte amb codi de barres 2234567 ha estat afegit a la venta actual amb la quantitat 1
    I que hi ha un descompte definit de tipus regal que per la compra de Optimus Prime 2 et regalen 3 uds de productes de tipus figura d'acció
    I que hi ha un descompte definit de tipus regal que per la compra de Optimus Prime 5 et regalen 2 uds de productes de tipus deporte

  Escenari: Afegir producte regal com a gestor
    Donat que un usuari a accedit al sistema amb el nom d'usuari Pepe amb el password 173529366
    Quan vol afegir un descompte de tipus regal que per la compra de Optimus Prime et regalen 1 uds de productes de tipus platja
    Aleshores els productes que tenen regals per tipus son
"""
Productes que tenen regals per tipus son:
Per la compra de Optimus Prime 2 s'obté 3 uds de productes de tipus figura d'acció
Per la compra de Optimus Prime 5 s'obté 2 uds de productes de tipus deporte
Per la compra de Optimus Prime s'obté 1 uds de productes de tipus platja
"""

  Escenari: Afegir producte regal com a venedor
    Donat que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363
    Quan vol afegir un descompte de tipus regal que per la compra de Optimus Prime et regalen 1 uds de productes de tipus "platja"
    Aleshores obtinc un error que em diu
  """
  Aquesta operació només la pot realitzar un gestor
  """


  Escenari: Afegir producte regal a la venta
    Donat que un usuari a accedit al sistema amb el nom d'usuari Pepe amb el password 173529366
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 3
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime - 20€/u x 3u = 60€
    OFERTA REGAL: -60€
    ---
    Total: 2€
    """

  Escenari: Afegir productes de mes a la venta
    Donat que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 5
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime - 20€/u x 3u = 60€
    OFERTA REGAL: -60€
    Optimus Prime - 20€/u x 2u = 40€
    ---
    Total: 42€
    """

  Escenari: Afegir 2 regals diferents del mateix tipus
    Donat que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363
    Quan afegeixo el producte de codi de barres 1234567 a la venta amb quantitat 1
    I afegeixo el producte de codi de barres 2334567 a la venta amb quantitat 2
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Optimus Prime - 20€/u x 1u = 20€
    OFERTA REGAL: -20€
    Optimus Prime 3 - 2€/u x 2u = 4€
    OFERTA REGAL: -4€
    ---
    Total: 2€
    """


  Escenari: Afegir producte amb regals al final
    Donat que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363
    Quan afegeixo el producte de codi de barres 1111111 a la venta amb quantitat 2
    I afegeixo el producte de codi de barres 2334577 a la venta amb quantitat 3
    I afegeixo el producte de codi de barres 1234777 a la venta amb quantitat 1
    Aleshores la pantalla del client del tpv mostra

    """
    Optimus Prime 2 - 2€/u x 1u = 2€
    Pelota de fútbol - 2€/u x 2u = 4€
    OFERTA REGAL: -4€
    Optimus Prime 4 - 2€/u x 3u = 6€
    OFERTA REGAL: -6€
    Optimus Prime 5 - 2€/u x 1u = 2€
    ---
    Total: 4€
    """

  Escenari: Falten productes regals
    Donat que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363
    Quan afegeixo el producte de codi de barres 2334577 a la venta amb quantitat 3
    I afegeixo el producte de codi de barres 1234777 a la venta amb quantitat 1
    Aleshores la pantalla del client del tpv mostra

    """
 Falten 2 regals per afegir
    """