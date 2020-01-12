Feature: user api test

Background:
  * url 'http://localhost:8080/'

  Scenario: get user
    Given path 'users/1'
    When method get
    Then status 200
      And match response == {id: 1, name: 'user1'}
      And assert response.id == 1
      And assert response.name == 'user1'
      And print response

  Scenario: create, update, delete user
    Given path 'users'
      And request {name: 'user6'}
    When method post
    Then status 201
    * def id = response.id

    Given path 'users/' + id
      And request {id: #(id), name: 'user66'}
    When method put
    Then status 200
      And match response == {id: #(id), name: 'user66'}
      And assert response.id == id
      And assert response.name == 'user66'
      And print response

    Given path 'users/' + id
    When method delete
    Then status 204
