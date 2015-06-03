# language: ca

#noinspection SpellCheckingInspection
Característica: Login per rols

  #PREGUNTES QUE FER:
    #un gestor també és venedor, no es posa dos usuaris iguals!

  Rerefons:
    Donat que estem a la botiga "Girona 1"

  Escenari: Afegir un gestor al sistema
    Quan és vol crear un nou login al sistema del tipus gestor pel treballador anomenat "Joan" amb el password "123456789"
    Aleshores existeix un login del tipus gestor pel treballador anomenat "Joan"

  Escenari: Afegir un venedor al sistema
    Quan és vol crear un nou login al sistema del tipus venedor pel treballador anomenat "Pere" amb el password "987654321"
    Aleshores existeix un login del tipus venedor pel treballador anomenat "Pere"

  Escenari: Consultar llista de gestors
    Donat que existeix un login de tipus gestor pel treballador anomenat Joan amb el password 123456789
    I que existeix un login de tipus gestor pel treballador anomenat Pere amb el password 173529363
    I que existeix un login de tipus gestor pel treballador anomenat Claudia amb el password pde925384
    I que existeix un login de tipus gestor pel treballador anomenat Daniel amb el password 111111111
    I que existeix un login de tipus gestor pel treballador anomenat Maria amb el password 987654321
    Quan vull obtenir un llistat dels noms dels gestor
    Aleshores el sistema em mostra el llistat de gestor amb els seus respectius noms
    """
    --Tipus Login--  --Nom--  --Password--
    gestor , Joan , 123456789
    gestor , Pere , 173529363
    gestor , Claudia , pde925384
    gestor , Daniel , 111111111
    gestor , Maria , 987654321

    """

  Escenari: Consultar llista de venedors
    Donat que existeix un login de tipus venedor pel treballador anomenat Joan amb el password 123456789
    I que existeix un login de tipus venedor pel treballador anomenat Pere amb el password 173529363
    I que existeix un login de tipus venedor pel treballador anomenat Claudia amb el password pde925384
    I que existeix un login de tipus venedor pel treballador anomenat Daniel amb el password 111111111
    I que existeix un login de tipus venedor pel treballador anomenat Maria amb el password 987654321
    Quan vull obtenir un llistat dels noms dels venedor
    Aleshores el sistema em mostra el llistat de venedor amb els seus respectius noms
    """
    --Tipus Login--  --Nom--  --Password--
    venedor , Joan , 123456789
    venedor , Pere , 173529363
    venedor , Claudia , pde925384
    venedor , Daniel , 111111111
    venedor , Maria , 987654321

    """

  Escenari: Consultar llista d'usuaris en el sistema
    Donat que existeix un login de tipus gestor pel treballador anomenat Joan amb el password 123456789
    I que existeix un login de tipus gestor pel treballador anomenat Pere amb el password 173529363
    I que existeix un login de tipus gestor pel treballador anomenat Claudia amb el password pde925384
    I que existeix un login de tipus gestor pel treballador anomenat Daniel amb el password 111111111
    I que existeix un login de tipus gestor pel treballador anomenat Maria amb el password 987654321
    I que existeix un login de tipus venedor pel treballador anomenat Miquel amb el password 173529363
    I que existeix un login de tipus venedor pel treballador anomenat Joana amb el password pde925384
    I que existeix un login de tipus venedor pel treballador anomenat Lidia amb el password 111111111
    I que existeix un login de tipus venedor pel treballador anomenat Jordi amb el password 987654321
    Quan vull obtenir un llistat dels usuaris del sistema
    Aleshores el sistema em mostra el llistat usuaris del sistema
    """
    --Tipus Login--  --Nom--  --Password--
    gestor , Joan , 123456789
    gestor , Pere , 173529363
    gestor , Claudia , pde925384
    gestor , Daniel , 111111111
    gestor , Maria , 987654321
    venedor , Miquel , 173529363
    venedor , Joana , pde925384
    venedor , Lidia , 111111111
    venedor , Jordi , 987654321

    """

  Escenari: Un gestor intenta accedir al sistema amb el nom i la contrasenya
    Donat que existeix un login de tipus gestor pel treballador anomenat Joan amb el password 123456789
    Quan un usuari accedeix al sistema posa el nom d'usuari Joan amb el password 123456789
    Aleshores el gestor amb el nom Joan ha iniciat sessió

  Escenari: Un venedor intenta accedir al sistema amb el nom i la contrasenya
    Donat que existeix un login de tipus venedor pel treballador anomenat Joan amb el password 123456789
    Quan un usuari accedeix al sistema posa el nom d'usuari Joan amb el password 123456789
    Aleshores el venedor amb el nom Joan ha iniciat sessió


  Escenari: Saber l'usuari que ha iniciat sessió
    Donat que existeix un login de tipus gestor pel treballador anomenat Joan amb el password 123456789
    I que ha iniciat sessió al sistema l'usuari Joan amb el password 123456789
    Quan vull obtenir las dades de qui està amb la sessió iniciada
    Aleshores el sistema em mostra l'usuari que està amb la sessió iniciada
    """
    ------ USUARIS ACTIUS AL SISTEMA -----
    --Tipus Login--  --Nom--  --Password--
    gestor , Joan , 123456789

    """

  Escenari: Saber l'usuari que ha iniciat sessió
    Donat que existeix un login de tipus gestor pel treballador anomenat Joan amb el password 123456789
    I que existeix un login de tipus venedor pel treballador anomenat Pere amb el password 173529363
    I que ha iniciat sessió al sistema l'usuari Joan amb el password 123456789
    Quan l'usuari Pere intenta accedir al sistema amb el password 173529363
    Aleshores obtinc un error que diu: "Ja hi ha un usuari actiu al sistema"

  Escenari: Un gestor intenta sortir del sistema amb el nom
    Donat que existeix un login de tipus gestor pel treballador anomenat Joan amb el password 123456789
    I que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 123456789
    Quan en Joan tanca sessió
    Aleshores el gestor amb el nom Joan ha tancat sessió

  Escenari: Un venedor intenta accedir al sistema amb el nom i la contrasenya
    Donat que existeix un login de tipus venedor pel treballador anomenat Joan amb el password 123456789
    I que un usuari a accedit al sistema amb el nom d'usuari Joan amb el password 123456789
    Quan en Joan tanca sessió
    Aleshores el venedor amb el nom Joan ha tancat sessió

  Escenari: Error al intentar accedir a un usuari
    Donat que existeix un login de tipus venedor pel treballador anomenat Joan amb el password 123456789
    Quan  un usuari intenta accedir al sistema amb el nom d'usuari Pere amb el password 123456789
    Aleshores obtinc un error que diu: "El nom o la contrasenya és incorrecte"

  Escenari: Usuaris inicies i tanquen sessió
    Donat que existeix un login de tipus gestor pel treballador anomenat Joan amb el password 123456789
    I que existeix un login de tipus venedor pel treballador anomenat Jordi amb el password 987654321
    I que existeix un login de tipus venedor pel treballador anomenat Anna amb el password 192734272
    I que existeix un login de tipus venedor pel treballador anomenat Pere amb el password 102937465
    I que ha iniciat sessió al sistema l'usuari Joan amb el password 123456789
    I que en Joan tanca sessió
    I que ha iniciat sessió al sistema l'usuari Jordi amb el password 987654321
    Quan vull obtenir las dades de qui està amb la sessió iniciada
    Aleshores el sistema em mostra l'usuari que està amb la sessió iniciada
    """
    ------ USUARIS ACTIUS AL SISTEMA -----
    --Tipus Login--  --Nom--  --Password--
    venedor , Jordi , 987654321

    """