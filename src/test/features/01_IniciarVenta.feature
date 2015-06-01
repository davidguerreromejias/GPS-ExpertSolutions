# language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar una venta

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que existeix un login de tipus venedor pel treballador anomenat Joan amb el password 173529363
    I que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 173529363

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
    Quan inicio una nova venta
    Aleshores obtinc un error que diu: "Aquest tpv ja té una venta iniciada"