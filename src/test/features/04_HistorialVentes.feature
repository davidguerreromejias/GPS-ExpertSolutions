# language: ca

#noinspection SpellCheckingInspection
Característica: Historial de ventes

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada
    I un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I que he afegit el producte de codi de barres 1234567 a la venta
    I que la venta ha sigut pagada i guardada al historial.
    I que hi ha una venta iniciada
    I un producte amb nom "Godzilla", preu 50€, iva 21% i codi de barres 1234568 i que pertany als tipus "figura d'acció,monstre"
    I que he afegit el producte de codi de barres 1234568 a la venta
    I que la venta ha sigut pagada i guardada al historial.
    I que en "Pere" ha iniciat sessió com a gestor
    #falta fer que hi hagi ventes d'una altre venedor
#en en el qual potser només es vol capturar la data i guardar-la per fer l'aleshores
  Escenari: Visualitzar l'historial per data
    Quan el gestor "Pere" introdueix la data "2015,05,18"
    Aleshores el resultat de la cerca per data és
    """
    Data 2015,05,18
    1 : 23€ - Realitzada per Joan.
    ---
    2 : 50€ - Realitzada per Joan.
    ---
    """
