Feature: Validating place Api's

  @AddPlace
  Scenario Outline:Verifying place is successfully added in google maps
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" with "Post" http request
    Then verify status code is 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id  mapped to "<name>"  using "GetPlaceApi"

    Examples:
      | name    | language | address     |
      | AAhouse | English  | East Colony |

  @DeletePlace
    Scenario:Verifying place is successfully deleted
      Given Delete place payload
      When user calls "DeletePlaceApi" with "Post" http request
      Then verify status code is 200
      And "status" in response body is "OK"



