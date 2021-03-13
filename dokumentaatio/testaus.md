# Testausdokumentti

## Testien muoto
Testaus tapahtuu käyttäen Moving AI Labs:in karttoihin liittyviä scenarioita, joista löytyy useita valmiita reittejä optimaalipituuksilla.
Kumpaakin algoritmia (Dijkstra & JPS) ajetaan näillä reiteillä ja verrataan algoritmien löytämien reittien pituuksia (parhaassa tapauksessa samat), sekä hakuihin kuluvaa aikaa.
Lisäksi apuna on visualisointi, jolla voi havainnolistaa hakujen toimintaa, esimerkiksi: tutkitut solmut, hyppypisteet, löydetty reitti.
Testien pohjana käytetään Baldur's Gate 2-pelin karttoja, jotka ovat kooltaan 512*512 ruutua, mikä muuntuu suoraa verkon solmujen lukumääräksi.


## Tuloksia ja vertailua

Ohessa taulukko tuloksista muutamalla esimerkkikartalla

 Map                          | AR0011SR          | AR0205SR          | AR0018SR          | AR0406SR          | AR0400SR  
-----                         | ------            | ----              | -----             | -------           | ------
No. of tests                  | 2180              | 1900              | 1490              | 2240              | 2890
Average optimal path length   | 435.93            | 379.95            | 297.96            | 447.96            | 577.95
Dijkstra avg. runtime         | 10.56ms           | 5.92ms            | 8.41ms            | 5.09ms            | 9.01ms
Dijkstra avg. nodes added to heap | 73 910.60     | 42 587.48         | 54 415.55         | 37 156.66         | 67 336.53
JPS avg. runtime              | 1.73ms            | 0.94ms            | 1.50ms            | 0.95ms            | 1.67ms
JPS avg. nodes added to heap  | 55.77             | 83.09             | 24.88             | 137.99            | 116.76


Vaikka JPS ja Dijkstra ovat molemmat algoritmeja jotka löytävät aina optimaalisen reitin pituuden, on suorituskyvyissä merkittäviä eroja. Testien perusteella JPS on noin keskimäärin 5-kertaa Dijkstraa nopeampi. 

Testeistä nähdään että Dijkstra tehokkuus on lähes suoraan verrannollinen tutkittujen, ja täten kekoon lisättyjen solmujen mukaan, kuten pitääkin. Jokainen solmun lisäys ja poisto keosta on oleellisin operaatio johon aikaa kuluu. Näin tutkittujen solmujen määrän kasvaessa myös suoritusaika kasvaa. Käytännössä tämä tarkoittaa että etenkin laajoilla kartoilla Dijkstra on hidas, kun taas kartoilla joissa on paljon kapeita tai suoria käytäviä Dijkstra toimii kohtuullisen tehokkaasti. Testeistä tämä näkyy esimerkiksi kartoilla AR0406SR (paljon yksisuuntaisia, kapeita käytäviä) vs AR0011SR (paljon avointa, useita mahdollisia tapoja kiertaa esteet).
Tämä tarkoittaa myös että syötteen eli kartan koon kasvaessa Dijkstra hidastuu säännöllisesti, ja etenkin suurilla reitin pituuksilla joilloin huomattava määrä solmuja täytyy tutkia algoritmi hidastuu huomatttavasti.

JPS:n tehokkuutta ei voi taas verrata suoraa kekoon lisättyjen solmujen määrään, sillä vaikka vaikka JPS karsiikin näiden operaatoiden määrää huomattavasti ovat sen muut operaatio verrattain hinnakkaampia. Tässä asiassa JPS toimiikin jopa kuin päinvastoin, kartan ollessa avoimempi tarvitsee lisätä kekoon vähemmän solmuja. 
Ja tämä onkin juuri JPS nopeuden ydin eli kartalla olevien laajojen tyhjien alueiden yli voidaan hypätä. 
Testeistä huomataankin että avoimilla kartoilla JPS on jo lähes 10-kertaa nopeampi (AR0011SR), kun taas suljetummilla kartoilla ero on pienempi, joskin siltikin huomattavasti Dijkstraa nopeampi. 

JPS onkin siis kartasta riippumatta aina Dijkstraa nopeampi, mutta avoimilla kartoilla ja suurilla reitin pituuksilla ero korostuu entisestään.

## Yksikkötestien kattavuus
![testCoverage](https://github.com/ALindroos/Polunhakija/blob/main/dokumentaatio/testcoverage.png)