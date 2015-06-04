# language: ca

#noinspection SpellCheckingInspection
Característica: Llistat de Productes

  Rerefons:
    Donat que estem al tpv número 1 de la botiga "Girona 1"
    I un producte amb nom "Optimus Prime", preu 23€, iva 21% i codi de barres 1234567 i que pertany als tipus "figura d'acció,transformer"
    I un producte amb nom "Pilota vermella", preu 10€, iva 21% i codi de barres 1111111 i que pertany als tipus "esports"
    I un producte amb nom "Pilota groga", preu 12€, iva 21% i codi de barres 2222222 i que pertany als tipus "esports"
    I un producte amb nom "Pilota verda", preu 11€, iva 21% i codi de barres 3333333 i que pertany als tipus "esports"
    I que existeix un login de tipus gestor pel treballador anomenat Pere amb el password 173529363
    I que existeix un login de tipus venedor pel treballador anomenat Joan amb el password pde925384
    I que en "Joan" ha iniciat el torn al tpv
    I que s'ha venut el producte 1234567 per un import de 23€
    I que s'ha venut el producte 2222222 per un import de 12€
    I que s'ha venut el producte 1234567 per un import de 23€
    I que s'ha venut el producte 1111111 per un import de 10€
    I que s'ha venut el producte 1234567 per un import de 23€
    I que s'ha venut el producte 2222222 per un import de 12€
    I que s'ha venut el producte 1234567 per un import de 23€
    I que s'ha venut el producte 1111111 per un import de 10€
    I que s'ha venut el producte 1234567 per un import de 23€
    I que s'ha venut el producte 2222222 per un import de 12€
    I que s'ha venut el producte 1234567 per un import de 23€
    I que s'ha venut el producte 1234567 per un import de 23€
    I que s'ha venut el producte 2222222 per un import de 12€
    I que s'ha venut el producte 1111111 per un import de 10€
    I que s'ha venut el producte 2222222 per un import de 12€
    I que s'ha venut el producte 2222222 per un import de 12€
    I que s'ha venut el producte 2222222 per un import de 12€
    I que en "Joan" ha tancat el torn al tpv
    I que en "Pere" ha iniciat sessió com a gestor

  Escenari: Visualitzar producte més popular
    Donat que en "Pere" ha iniciat sessió com a gestor
    Quan demana visualitzar el producte més popular
    Aleshores el resultat de tot l'historial és
    """
    El producte més venut és la Pilota groga i s'ha venut 7 cops.
    ---
    """