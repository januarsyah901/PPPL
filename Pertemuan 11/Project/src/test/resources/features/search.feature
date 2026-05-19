@Latihan
Feature: Search functionality on Bing

  Scenario: Search for a keyword on Bing
    Given aku buka halaman pencarian Bing
    When aku ngetik kata kunci "Cucumber Java"
    And aku teken enter buat nyari
    Then harusnya muncul hasil yang ada hubungannya sama "Cucumber Java"
