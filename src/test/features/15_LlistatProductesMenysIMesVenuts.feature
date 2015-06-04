# language: ca

#noinspection SpellCheckingInspection
Característica: Llistat de Productes més/menys venuts

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Pilota vermella", preu 10€, iva 21% i codi de barres 1111111 i que pertany als tipus "esports"
    I un producte amb nom "Pilota groga", preu 12€, iva 21% i codi de barres 2222222 i que pertany als tipus "esports"
    I un producte amb nom "Pilota verda", preu 11€, iva 21% i codi de barres 3333333 i que pertany als tipus "esports"
    I que existeix un login de tipus gestor pel treballador anomenat Pere amb el password 173529363
    I que existeix un login de tipus venedor pel treballador anomenat Joan amb el password pde925384
    I que en "Joan" ha iniciat el torn al tpv
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1234567 per un import de 23€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 2222222 per un import de 12€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1234567 per un import de 23€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1111111 per un import de 10€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1234567 per un import de 23€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 2222222 per un import de 12€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1234567 per un import de 23€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1111111 per un import de 10€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1234567 per un import de 23€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1234567 per un import de 23€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 2222222 per un import de 12€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 2222222 per un import de 12€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 1111111 per un import de 10€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 2222222 per un import de 12€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 2222222 per un import de 12€
    I que s'ha efectuat una venta on s'han venut 1 unitats del producte 2222222 per un import de 12€
    I que en "Joan" ha tancat el torn al tpv

  Escenari: Visualitzar producte més popular
    Donat que en "Pere" ha iniciat sessió com a gestor
    Quan demana visualitzar el producte més popular
    Aleshores la llista dels productes més venuts és
    """
    El productes més venuts:
    Pilota groga i s'ha venut 7 cops.
    Optimus Prime i s'ha venut 7 cops.
    ---
    """

  Escenari: Visualitzar producte menys popular
    Donat que en "Pere" ha iniciat sessió com a gestor
    Quan demana visualitzar el producte menys popular
    Aleshores la llista dels productes menys venuts és
    """
    El producte menys venut és la Pilota verda i s'ha venut 0 cops.
    """

  Escenari: Error si un venedor intenta visualitzar el producte més venut
    Donat que en "Joan" ha iniciat el torn al tpv
    Quan demana visualitzar el producte més popular
    Aleshores obtinc un error que diu: "Un venedor no pot visualitzar el producte més venut."

  Escenari: Error si un venedor intenta visualitzar el producte menys venut
    Donat que en "Joan" ha iniciat el torn al tpv
    Quan demana visualitzar el producte menys popular
    Aleshores obtinc un error que diu: "Un venedor no pot visualitzar el producte menys venut."