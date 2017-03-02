Feature:

  Scenario: GET request
    When I send a GET request to "/hello-world"
    Then the response status should be "200"
    
  Scenario: GET request with query parameter
    When I send a GET request to "/hello-world" with name "preeth"
    Then the response status should be "200"
    And the response should contain "Hello, preeth!"
    
  Scenario: POST request Add a Person
    When I send a POST request to "/hello-world/save" with first name "preeth"  and id "1". 
    Then the response status should be "200"
    And the response should contain first name "preeth"  and id "1". 

  Scenario: PUT request to update a Person
    When I send a PUT request to "/hello-world/save/1" with first name "preeth" and id "2". 
    Then the response status should be "200"
    And the response should contain first name "preeth" and id "2".      