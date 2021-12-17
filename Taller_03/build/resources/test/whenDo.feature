Feature: Nota

  Scenario: Crear Nota

    Given que tengo acceso a la aplicacion whenDo
    When agrego una nota con los datos
      | titulo de nota | descripcion de actividad    |
      | Nota10         | actividad10 |
    Then la nota "Nota10" debe ser creado