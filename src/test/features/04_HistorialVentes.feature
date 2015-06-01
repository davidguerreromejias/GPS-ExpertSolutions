# language: ca

#noinspection SpellCheckingInspection
Característica: Historial de ventes

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que existeix un login de tipus gestor pel treballador anomenat Pere amb el password 173529363
    I que existeix un login de tipus venedor pel treballador anomenat Joan amb el password pde925384
    I que en "Joan" ha iniciat el torn al tpv
    I que hi ha una venta iniciada en data "2015,05,18"
    I que la venta ha sigut pagada amb un import de 23€ i guardada al historial.
    I que hi ha una venta iniciada en data "2015,05,18"
    I que la venta ha sigut pagada amb un import de 50€ i guardada al historial.
    I que en "Joan" ha tancat el torn al tpv
    I que existeix un login de tipus venedor pel treballador anomenat Jordi amb el password pde652384
    I que en "Jordi" ha iniciat un altre torn al tpv
    I que hi ha una venta iniciada en data "2015,05,18"
    I que la venta ha sigut pagada amb un import de 10€ i guardada al historial.
    I que en "Pere" ha iniciat sessió com a gestor

  Escenari: Visualitzar tot l'historial
    Quan el gestor "Pere" visualitza tot l'historial
    Aleshores el resultat de tot l'historial és
    """
    Historial de ventes
    1 : 23€ - Realitzada per Joan en data 2015,05,18.
    ---
    2 : 50€ - Realitzada per Joan en data 2015,05,18.
    ---
    3 : 10€ - Realitzada per Jordi en data 2015,05,18.
    ---
    """

  Escenari: Visualitzar l'historial per data
    Quan el gestor "Pere" introdueix la data "2015,05,18"
    Aleshores el resultat de la cerca per data és
    """
    Data 2015,05,18
    1 : 23€ - Realitzada per Joan.
    ---
    2 : 50€ - Realitzada per Joan.
    ---
    3 : 10€ - Realitzada per Jordi.
    ---
    """

  Escenari: Visualitzar l'historial per venedor
    Quan el gestor "Pere" introdueix el venedor "Joan"
    Aleshores el resultat de la cerca per venedor és
    """
    Joan
    1 : 23€ - Data 2015,05,18.
    ---
    2 : 50€ - Data 2015,05,18.
    ---
    """
