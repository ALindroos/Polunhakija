# Testausdokumentti

## Testien muoto
Testaus tulee tapahtumaan käyttäen Moving AI Labs:in karttoihin liittyviä scenarioita, joista löytyy useita valmiita reittejä optimaalipituuksilla.
Kumpaakin algoritmia (Dijkstra & JPS) ajetaan näillä reiteillä ja verrataan algoritmien löytämien reittien pituuksia (parhaassa tapauksessa samat), sekä hakuihin kuluvaa aikaa.
Lisäksi apuna on visualisointi, jolla voi havainnolistaa hakujen toimintaa, esimerkiksi: tutkitut solmut, hyppypisteet, löydetty reitti.
Testien pohjana käytetään Baldur's Gate 2 karttoja, jotka ovat kooltaan 512*512 ruutua, mikä muuntuu suoraa verkon solmujen lukumääräksi.


## Tuloksia ja vertailua

Ohessa taulukko tuloksista muutamalla esimerkkikartalla

 Map                          | AR0011SR          | AR0205SR          | AR0018SR          | AR0406SR          | AR0400SR  
-----                         | ------            | ----              | -----             | -------           | ------
Average optimal path length   | 435.93            | 379.95            | 297.96            | 447.96            | 577.95
Dijkstra avg. runtime         | 22.54ms           | 8.93ms            | 26ms              | 6.16ms            | 18.97ms
JPS found optimal path        | 1783/2180 (81.7%) | 1198/1900 (63.0%) | 1273/1490 (85.4%) | 1886/2240 (84.2%) | 2521/2890 (87.2%)
JPS avg. runtime              | 1.49ms            | 0.89ms            | 1.25ms            | 0.79ms            | 1.69ms
JPS avg. diff in path length (all)  | 5.32              | 8.72              | 0.94              | 1.20              | 3.43
JPS avg. diff in path length (non-optimal cases) | 29.19 | 23.61 | 6.46 | 7.60 | 26.83
JPS max diff in path lenght   | 246.75            | 101.45            | 29.82             | 99.92             | 315.71


Vaikka JPS ei takaakan että löydetty reitti on lyhyin mahdollinen, toisin kuin Dijkstran algoritmi, tehdyillä testeillä JPS kuitenkin löytää lyhyimmän mahdollisen reitin keskimäärin noin 80% tapauksista.
Tapauksissa joissa löydetty reitti ei ole optimaalinen, se on keskimäärin 20 askelta pidempi. Tosin ei optimaalisia tuloksia alkaa esiintyä erityisesti suurilla reitin pituuksilla, mikä voidaan huomata huonoimmista tapauksista joissa löydetty reitti voi olla huomattavasti pidempi. Tällaisia tilanteita voi olla mahdollista parantaa tarkemmalla heurestiikkalla.
Kuitenkin, jos kaikki tapaukset otetaan huomioon, on keskimäärin löydetty reitti vain ~4 askelta pidempi.

Tärkeämmin JPS on suoritusnopeudeltaan huomattavasti nopeampi kuin Dijkstran algoritmi. Kun Dijkstran algoritmin suoritusajat pyörivät +20ms alueilla toimii JPS varsin tasaisesti noin muutamassa millisekunnissa. Etenkin suurilla reitin pituuksilla ja kartoilla joilla on laajoja avaria alueita voivat suoritusajat olla helposti +30ms vs. ~3ms. JPS toimii siis tehokkaasti lähes kaikilla syötteillä huomattavasti nopeammin.

Käytetyistä kartoista voi havaita että JPS on verrattain erityisen tehokas kartoilla jolla on laajoja avoimia alueita. Toisin kuin Dijkstra, JPS:n ei tarvitse laajentuessaan lisätä kaikkia naapureitaan kekoon, vaan voi nopeasti hypätä alueen yli ja löytää kiinnostavia solmuja tutkittavaksi.
Kartoilla joilla on paljon kapeita käytäviä eivät erot ole niin suuria, sillä näissä tapauksissa Dijkstra voi edetä kohtuullisen tehokkaasti. 

### Heurestiikka
Heurestiikalla voi olla mahdollista vielä parantaa JPS:n tehokkuutta.
Tällä hetkellä parhaimmaksi heurestiikaksi havaitsin kuljetun etäisyyden + nykyisen etäisyyden maalista: `distance + sqrt((dx * dx) + (dy * dy))`
Toinen testatta heurestiikka on vastaavassa jossa etäisyyttä maaliin on kompensoitu kohtisuorien liikkeiden kannalta:
`distance + (D * (dx + dy) + (D2 - 2 * D) * min(dx, dy));`, missä D on suoranliikkeen hinta(=1) ja D2 vinon(=sqrt(2)).

Erot näiden heurestiikkojen välillä eivät ole suuria, kuten alla olevasta voi huomata, mutta on vielä mahdollista että parempi heurestiikka on olemassa.

### AR0011SR.map
 Heuristic  | Distance + Diagonal Distance to Goal | Distance + Diagonal Distance with compensation for straight moves    
----- | ------ | ----
Average optimal path length | 435.93 | 435.93
Dijkstra avg. runtime | 22.54ms | 22.36ms
JPS found optimal path | 1783/2180 (81.7%) | 1758/2180 (80.6%)
JPS avg. runtime | 1.49ms | 1.46ms
JPS avg. diff in path length | 5.32 | 7.08
JPS max diff in path lenght | 246.75 | 270.89


