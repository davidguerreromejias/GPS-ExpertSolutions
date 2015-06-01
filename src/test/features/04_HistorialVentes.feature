# language: ca

#noinspection SpellCheckingInspection
Característica: Historial de ventes

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que existeix un login de tipus gestor pel treballador anomenat Pere amb el password 173529363
    I que existeix un login de tipus venedor pel treballador anomenat Joan amb el password pde925384
    I que existeix un login de tipus venedor pel treballador anomenat Jordi amb el password rtu987384
    I que existeix un login de tipus venedor pel treballador anomenat Carles amb el password sag967384
    I que existeix un login de tipus venedor pel treballador anomenat Victor amb el password fug543334
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
    I que en "Jordi" ha tancat el torn al tpv
    I que en "Carles" ha iniciat el torn al tpv
    I que hi ha una venta iniciada en data "2015,05,20"
    I que la venta ha sigut pagada amb un import de 40€ i guardada al historial.
    I que hi ha una venta iniciada en data "2015,05,20"
    I que la venta ha sigut pagada amb un import de 27€ i guardada al historial.
    I que en "Carles" ha tancat el torn al tpv
    I que existeix un login de tipus venedor pel treballador anomenat Jordi amb el password pde652384
    I que en "Victor" ha iniciat un altre torn al tpv
    I que hi ha una venta iniciada en data "2015,05,20"
    I que la venta ha sigut pagada amb un import de 15€ i guardada al historial.
    I que en "Pere" ha iniciat sessió com a gestor

  Escenari: Visualitzar tot l'historial
    Quan demana visualitzar tot l'historial
    Aleshores el resultat de tot l'historial és
    """
    Historial de ventes
    1 : 23€ - Realitzada per Joan en data 2015,05,18.
    ---
    2 : 50€ - Realitzada per Joan en data 2015,05,18.
    ---
    3 : 10€ - Realitzada per Jordi en data 2015,05,18.
    ---
    4 : 40€ - Realitzada per Carles en data 2015,05,20.
    ---
    5 : 27€ - Realitzada per Carles en data 2015,05,20.
    ---
    6 : 15€ - Realitzada per Victor en data 2015,05,20.
    ---
    """

  Escenari: Visualitzar l'historial per data
    Quan demana les ventes per data "2015,05,18"
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

  Escenari: Visualitzar l'historial per data
    Quan demana les ventes per data "2015,05,20"
    Aleshores el resultat de la cerca per data és
    """
    Data 2015,05,20
    1 : 40€ - Realitzada per Carles.
    ---
    2 : 27€ - Realitzada per Carles.
    ---
    3 : 15€ - Realitzada per Victor.
    ---
    """

  Escenari: Visualitzar l'historial per venedor
    Quan demana les ventes del venedor "Joan"
    Aleshores el resultat de la cerca per venedor és
    """
    Joan
    1 : 23€ - Data 2015,05,18.
    ---
    2 : 50€ - Data 2015,05,18.
    ---
    """

  Escenari: Visualitzar l'historial per venedor
    Quan demana les ventes del venedor "Victor"
    Aleshores el resultat de la cerca per venedor és
    """
    Victor
    1 : 15€ - Data 2015,05,20.
    ---
    """