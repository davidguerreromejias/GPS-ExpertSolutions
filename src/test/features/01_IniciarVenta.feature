# language: ca

#noinspection SpellCheckingInspection
Característica: Iniciar una venta

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I un administrador del sistema ha creat un nou login al sistema del tipus venedor pel treballador anomenat Joan amb el password 123098463
    I que l'usuari Joan accedeix al sistema amb el password 123098463

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