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
    I que en "Pere" ha iniciat el torn al tpv
    I que s'ha fet una venta de 10€
    I que s'ha fet una venta de 50€
    I que en "Josep" ha iniciat el torn al tpv
    I que s'ha fet una venta de 70€
    I que s'ha fet una venta de 80€
    I que en "Pere" ha iniciat sessió com a gestor

  Escenari: Visualitzar l'historial per data
    Quan el gestor "Pere" visualitza les ventes en una data "2015-05-18"
    Aleshores el sistema mostra la venta del producte "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567
    I que s'ha realitzat al tpv número 1 de la botiga "Girona 1"
    I que ha estat realitzada per en "Joan"

