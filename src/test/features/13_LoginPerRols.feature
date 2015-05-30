# language: ca

#noinspection SpellCheckingInspection
Característica: Afegir un regal a un producte

  #PREGUNTES QUE FER:
    #s'ha de crear dos logins, un pel gestor i per treballador? O un gestor tambe es treballador a la vegada (gestor=treballador+gestor?)

  Rerefons:
    Donat que estem a la botiga "Girona 1"

  Escenari: Afegir un gestor al sistema
    Quan és vol crear un nou login al sistema del tipus gestor pel treballador anomenat "Joan" amb el password "123456789"
    Aleshores hi ha un login del tipus gestor creat pel treballador anomenat "Joan"

  Escenari: Afegir un venedor al sistema
    Quan és vol crear un nou login al sistema del tipus venedor pel treballador anomenat "Pere" amb el password "987654321"
    Aleshores hi ha un login del tipus venedor creat pel treballador anomenat "Pere"

  Escenari: Consultar llista de gestors
    Donat que existeix un login de tipus gestor pel treballador anomenat "Joan" amb el password "123456789"
    I que existeix un login de tipus gestor pel treballador anomenat "Pere" amb el password "173529363"
    I que existeix un login de tipus gestor pel treballador anomenat "Claudia" amb el password "pde925384"
    I que existeix un login de tipus gestor pel treballador anomenat "Daniel" amb el password "111111111"
    I que existeix un login de tipus gestor pel treballador anomenat "Maria" amb el password "987654321"
    Quan vull obtenir un llistat dels noms dels gestors
    Aleshores el sistema em mostra el llistat de gestors amb els seus respectius noms
    """
    --Tipus Login--  --Nom--  --Password--
    gestor , Joan , 123456789
    gestor , Pere , 173529363
    gestor , Claudia , pde925384
    gestor , Daniel , 111111111
    gestor , Maria , 987654321

    """

  Escenari: Consultar llista de venedors
    Donat que existeix un login de tipus venedor pel treballador anomenat "Joan" amb el password "123456789"
    I que existeix un login de tipus venedor pel treballador anomenat "Pere" amb el password "173529363"
    I que existeix un login de tipus venedor pel treballador anomenat "Claudia" amb el password "pde925384"
    I que existeix un login de tipus venedor pel treballador anomenat "Daniel" amb el password "111111111"
    I que existeix un login de tipus venedor pel treballador anomenat "Maria" amb el password "987654321"
    Quan vull obtenir un llistat dels noms dels venedors
    Aleshores el sistema em mostra el llistat de venedors amb els seus respectius noms
    """
    --Tipus Login--  --Nom--  --Password--
    venedor , Joan , 123456789
    venedor , Pere , 173529363
    venedor , Claudia , pde925384
    venedor , Daniel , 111111111
    venedor , Maria , 987654321

    """

  Escenari: Consultar llista d'usuaris en el sistema
    Donat que existeix un login de tipus gestor pel treballador anomenat "Joan" amb el password "123456789"
    I que existeix un login de tipus gestor pel treballador anomenat "Pere" amb el password "173529363"
    I que existeix un login de tipus gestor pel treballador anomenat "Claudia" amb el password "pde925384"
    I que existeix un login de tipus gestor pel treballador anomenat "Daniel" amb el password "111111111"
    I que existeix un login de tipus gestor pel treballador anomenat "Maria" amb el password "987654321"
    I que existeix un login de tipus venedor pel treballador anomenat "Miquel" amb el password "173529363"
    I que existeix un login de tipus venedor pel treballador anomenat "Joana" amb el password "pde925384"
    I que existeix un login de tipus venedor pel treballador anomenat "Lidia" amb el password "111111111"
    I que existeix un login de tipus venedor pel treballador anomenat "Jordi" amb el password "987654321"
    Quan vull obtenir un llistat dels noms dels gestors
    Aleshores el sistema em mostra el llistat de gestors amb els seus respectius noms
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
  