# language: ca

#noinspection SpellCheckingInspection
Característica: Historial de ventes

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I que en "Joan" ha iniciat el torn al tpv
    I que s'ha fet una venta de 20€
    I que s'ha fet una venta de 15€
    I que s'ha fet una venta de 30€
    I que s'ha fet una venta de 40€
    I que en "Pere" ha iniciat sessió com a gestor

  Escenari: Visualitzar l'historial per data
    Quan el gestor "Pere" visualitza les ventes en una data "2015,05,18"
    Aleshores el resultat de la cerca per data "2015,05,18" és
    """
    1 : 20€ - Realitzada per Joan.
    2 : 15€ - Realitzada per Joan.
    3 : 30€ - Realitzada per Joan.
    4 : 40€ - Realitzada per Joan.
    ---
    """
