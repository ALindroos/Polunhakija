# Viikkoraportti 3

Työaika: ~12t

## 3.2
Sain raa'an version minimikeosta tehtyä, mutta sisältää vielä jotain bugeja sillä käytettäessä tätä Dijsktan algoritmin kanssa toimivuus eroa vielä huomattavasti PriorityQueue:ta käytettäessä. Ero 100 vs. 150 askelta löydetyn "lyhyimmän" reitin pituudessa. Vaati vielä korjaamista.

## 4.2
Minimikeon ongelma oli heapify metodissa, alimpia lehtiä ei swapattu oikein. Nyt korjattu, minimikeko toimii oikein.

## 5.2
Dijkstran algoritmi tehty omaksi luokakseen. Visualisointi nyt myös graafisena, mutta vaati vielä visuaalisten häiriöiden korjaamista.



## Viikon edistys
Minikeko toteutettu ja Dijkstran algoritmi muutettu toimimaan sen avulla. Projektin rakennetta paranneltu.
Paremman visualisoinnit vielä kesken.
JPS:n toteutusta en vielä ehtinyt aloittaa, mutta aiheesta luettu enemmän.


## Uutta opittua
Javan swing kirjastosta ja graafisista ominaisuuksista tuli opittua paljon.
Samoin minimikekoa toteuttaessa tuli ymmärrettyä sen hyödyllisyyttä ja toimintaa paremmin.


## Kohdattuja ongelmia
Javan swing-kirjasto ja ylipäänsä graafisen käyttöliittymän kanssa tuli tapeltua, kun ei ole tultu aikaisemmin käytettyä kovinkaan paljon, ja viime kerrasta jo aikaa. Periaatteiden kuten komponenttien päivittämisessä sai kertailla.
Pitää vielä parennella ja eriyttää osia, niin että algoritmejä voi ajaa käyttöliittymän kanssa ja ilman suorityskyvyn testaamiseksi.


## Seuraavat askeleet
* JPS toteutus
* Graafisen käyttöliittymän parannus
* Algoritmien vertailu, käyttöliittymä tätä varten
* Mahdollisuus valita ladattava kartta


