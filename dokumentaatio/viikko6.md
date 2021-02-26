# Viikkoraportti 6

Työaika: ~10t


## Viikon edistys
Dijkstra ja JPS eivät enään leikkaa kulmia etsiessään reittiä. Helpottaa testaamista kun Moving AI lab:in testit olettavat näin.

JPS:n heurestiikkaa parannettu, löytää nyt 1947/2181 tapauksesta lyhyimmän reitin. Heurestiikassa saattaa silti olla vielä parantamista, tällä hetkellä parhaiten on näyttänyt toimivan  solmun etäisyys lähdöstä + etäisyys maalista, ja yritykset optimoida tätä heurestiikkaa ovat tuottaneet huonompia tuloksia etäisyyksien summa. 

Toisaalta vaikka JPS ei löydä pitkillä reiteillä aina optimaalista polkua, saatu tulos on yleensä joitakin kymmeniä askelia pidempi, mutta toimii jopa moninkymmenkertaisesti nopeammin.
Pidemmillä reiteillä Dijkstra toimii keskimäärin ajassa ~30ms, kun taas JPS toimii varsin tasaisesti muutamassa millisekunnissa. 

JPS ei varsinaisesti takaa että se löytää lyhyimmän reitin, mutta kun on näin pitkälle päästy niin tekee mieli vielä yrittää löytää keinoa saada sitä toimimaan tarkemmin. Heurestiikka vaikuttaa tällä hetkellä asialta jossa on eniten mahdollisuuksia tehdä parannuksia.

Ohjelmassa nyt käyttöliittymä perustoiminnoille, kuten Scenarioiden testaamiselle ja valinnaisen reitin visualisoinnille molemmilla algoritmeilla.

## Kohdattuja ongelmia
Nyt algoritmit on toteutettu niin että ne päivittävät syötteenä saamaansa verkkoa sitä läpikäydessään, mikä teki algoritmien toteutuksesta yksinkertaisempaa, mutta haittapuolena on että suorituskykyvertailuissa ja muissa menee turhaa aikaa, karttojen uudelleen lataamiseen. Tämän korjaaminen vaatisi jonkin verran refaktorointia.


## Uutta opittua
Nyt kun sain automatisoidut suorituskyky testit toimimaan, niin tajusi viimein miten hieno optimointi JPS oikein onkaan. Vertaus Dijkstraan on ehkä vähän epäreilu, mutta kyllä se on tehokas.

## Seuraavan viikon askeleet
* Kunnon suorituskyky testaus -> statistiikkaa
* Dokumentaatiota kuntoon
* Testejä ja hiomista
