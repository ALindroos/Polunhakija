# Viikkoraportti 5

työaika: ~18t


## Viikon edistys
JPS bugeja korjailtu ja siihen lisätty heurestiikka, toimii tällä hetkellä varsin luotettavasti.
Ohjelmaan lisätty toiminallisuus ajaa Moving AI labs:in scenarioita kartoille, mikä auttaa debuggaamisessa ja toimii vertailukohteena.
Scenariot olettavat että reitti ei leikkaa kulmia, jota omat algoritmini eivät ota huomioon, mutta silti yhdellä kartalla tämän hetkinen JPS löytää optimaalisen reitin 1147/2181 tapauksesta, virhetapaukset suurilla reitin pituuksilla, jolloin JPS palauttaa muutamaa kymmentä askelta pidemmän reitin. Vaatii vielä hiomista.
JavaDoc aloitettu ja lähes kaikki luokat kommentoitu.
Toteutus- ja testausdokumentaatio aloitettu.

## Kohdattuja ongelmia
JPS toteutus on ollut vieläkin odetettua hankalampaa pienten bugien takia, mutta nyt edistys tuntuu hyvältä. 
Etenemisen hahmottaminen on ollut hankalaa, kun haku etenee rekursiivisesti kahdella tasolla, mutta pienten korjausten kanssa on jo päästy pitkälle. 

## Seuraavat askeleet
* Suorityskykytestaukset
* Käyttöliittymä ohjelmalle
* JPS hiominen