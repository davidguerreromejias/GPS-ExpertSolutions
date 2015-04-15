# language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar una venta

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv

  Escenari: Iniciar una venta
    Quan inicio una nova venta
    Aleshores la venta actual és de'n "Joan" al tpv 1 de la botiga "Girona 1"
    I la pantalla del client del tpv mostra
    """
    Li donem la benvinguda a Joguets i Joguines!
    L'atén Joan
    """

  Escenari: No es pot iniciar una venta si ja hi ha una venta iniciada
    Donat que hi ha una venta iniciada
    Quan intento iniciar una venta
    Aleshores obtinc un error que diu: "Aquest tpv ja té una venta iniciada"
