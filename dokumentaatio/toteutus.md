# Toteutusdokumentti

## Ohjelman rakenne

```
polunhakija
├── UI
│   ├── UI.java
│   ├── GridCanvas.java
│   ├── Visualizer.java
├── utils
│   ├── BenchmarkScenario.java
│   └── MapLoader.java
├── BinaryHeap.java
├── Dijkstra.java
├── Main.java
├── JPS.java
└── Node.java
```


UI-luokka sisältää yksinkertaisen komentorivisovelluksen jonka avulla voi ajaa joko testit kartalle, tai avata visualisoinnin kartasta johon voi syöttää koordinaatteja ja näin etsiä ja havainnolistaa reittejä interaktiivisesti.

BinaryHeap on toteutus minimikeosta, Node aputietorakenne verkon solmuille ja algoritmit omissa luokissaan.

## Minimikeko
Molemmat algoritmit hyödyntävät toiminnassaan minimikekoa verkon solmuille joita seuraavaksi käsitellään.
Järjestys kriteerinä toimii etäisyys joko lähtösolmusta tai heurestiikka, niin että pienin arvo on keon päällimmäisenä.

### Tilavaatimus
Tämän hetkinen turvallinen tilavaatimus keolle on 0(n + 1), missä n on verkon solmujen lukumäärä, sillä keon indeksiä 0 ei käytetä toteutuksen helpottamiseksi, eli 0(n), kun keon sisältävän taulukon koko pitää määrittää.
Tätä voisi pyrkiä pienentämään optimoimalla, sillä on epätodennäköistä että jokainen verkon solmu tultaisiin käymään läpi, varsinkin solmuja poistetaan keosta käsittelyn yhteydessä. Etenkin JPS:n tilanteessa solmujen jotka kekoon täytyy lisätä jäävät lukumäärältään kohtuullisen pieniksi. Kuitenkin ehkä isossa mittakaavassa ei niin merkittävää, mutta voi olla mielenkiintoista tutkia.

### Aikavaatimukset
Keon järjestämisen kannalta oleellinen heapify- eli keon korjausoperaatio vie O(log n), rekursiivisia kutsuja täytyy tehdä pahimmassa tapauksessa puun korkeuden verran.

remove() eli keon ensimmäisen (arvoltaan pienimmän) alkion poistaminen on itse ajaltaan vakio, mutta koska keko pitää korjata poiston jälkeen heapify-operaatiolla, tulee aikavaatimukseksi 0(log n).

Uuden alkio lisäämisen aikavaatimus on sama kuin muidenkin 0(log n). Aloitetaan pohjalta ja vaihdetaan solmujen paikkaa kunnes kekoehto täyttyy, taas pahimmillaan puun korkeuden verran. 

## Dijkstra
Alustustoimeksi kekoon lisätään ensimmäinen alkio, lähtösolmu. Tämän kuten kaikkien muidenkin keko operaatioiden aikavaatimus on 0(log n), kuten yllätodettu.
Toistolauseessa poistetaan keon ensimmäinen solmu O(log |V|), tarkistetaan se ja jos se ei ole maalisolmu lisätään kaikki solmun sopivat naapurit kekoon eli 0(|E| log |V|), missä |E| solmun kaaret (naapurit).
Pahimmassa tapauksessa, eli jos kaikki solmut joudutaan käymään läpi, saadaan aikavaatimukseksi 0((|E|+|V|) log |V|).

Tilavaatimus on 0(|V|), mikä on suurin mahdollinen määrä toistoja algoritmin läpikäymiseksi.

Toteutettu Dijkstran algoritmi etenee 8-suuntaa suorat liikkeet arvoltaan 1, vinot sqrt(2).
Algoritmi ei myöskään leikkaa kulmia, kuten karttatesteissä oletettiin.

## JPS
Alustustoimena sama, lähtösolmu lisätään kekoon.
Toistolauseessa keosta poistetaan ensimmäinen solmu O(log |V|) ja tämä tutkitaan. Tutkiessa ensin skannataan verkosta rivi ja sarake jossa solmu on O(|V|) jonka jälkeen edetään rekursiivesti tutkimaan solmun diagonaaliset naapurit (|V|). Jos matkalla löydetään hyppypiste se lisätään kekoon 0(log |V|). Kun rekursiivinen solmun tutkimus on päättynyt siirrytään seuraavaan keon alkioon. Eli aikavaatimus on 0(|V| log|V|).

Tilavaatimus myös (0|V|).

Kuten Dijkstra, JPS etenee 8-suuntaa, hinnat 1 suoraan, sqrt(2) vinoon. JPS ei myöskään leikkaa kulmia, mikä tarkoittaa että "normaalista" hyppypisteet mennään askel ohi, tarkaillaan takana vinosti olevia läpikulkemattomia solmuja

Tarkempia testituloksia löytyy [testausdokumenttista.](https://github.com/ALindroos/Polunhakija/blob/main/dokumentaatio/testaus.md)


## Parannuksia
Tämän hetkiset algoritmit olettavat että saatu kartta on suljettu, siis kaikki ulkoisimmat solmut ei-kuljettavia.
Molemmat algoritmit myös päivittävät saamaansa karttaa käydessään sitä läpi, mikä osittain nopeuttaa hakua, mutta toisaalta tarkoittaa että kartta pitää aina ladata uudestaan tehdessä uutta hakua samalla kartalla.
