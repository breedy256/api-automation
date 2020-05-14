Feature: API e2e demo with BDD
  As as user I want to be able to create new board, add couple of lists,
  add and move card, and I should be able to delete my board

  Scenario Outline: Title of your scenario outline
    Given I have created new board with <boardName> name
    And I have added <listOne> and <listTwo> list to my board
    When I add <cardName> card to TODO list
    Then I should be able to move my card to DONE list
    When I delete my board
    Then I should be able to verify that my board was deleted

    Examples: 
      | boardName  | listOne | listTwo | cardName |
      | Auto Board | DONE    | TODO    | My Card  |