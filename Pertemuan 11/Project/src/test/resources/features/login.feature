@Tugas
Feature: Login functionality for SauceDemo

  Scenario Outline: Successful login with valid credentials
    Given aku lagi di halaman login SauceDemo
    When aku masukin username "<username>" sama password "<password>"
    And aku klik tombol loginnya
    Then aku harusnya langsung masuk ke halaman inventory

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  Scenario: Login failure with invalid credentials
    Given aku lagi di halaman login SauceDemo
    When aku masukin username "user_ngasal" sama password "pass_ngasal"
    And aku klik tombol loginnya
    Then harusnya muncul pesan error
