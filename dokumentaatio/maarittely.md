# Määrittelydokumentti

Tarkoitus on luoda reitinhakusovellus 2D-kartoille, jolla etsitään etsitään nopeasti lyhyin reitti pisteiden välillä hyödyntäen erilaisia reitinhakualgoritmeja, ja vertailla näiden toimintaa. Kielenä käytössä Java. Projekti on osa tietojenkäsittelytieteen kandidaatin (TKT) tutkielmaa.

## Algoritmit
Tarkoituksena on toteuttaa reitinhaku ainakin Djikstran- ja A* algoritmien avulla, mahdollisesti myös joillakin A* muunnelmilla kuten JMP:llä.

Molemmat algoritmit, Dijkstra ja A* toimivat noin logaritmisessa ajassa. Pahimman tapauksen aikavaativuus on molemmissa sama O((|V| + |E|) log(|V|)), mutta A* voi olla heurestiikansa ansiosta keskimäärin nopeampi. Tilavaatimus molemmilla luokkaa luokkaa O(n). 

## Tietorakenteet
Jotta reitinhakeminen olisi tehokasta, verkon solmujen läpikäyntiä varten tulee toteuttaa Binaarikeko. Lisäksi kartat ja saadut tulokset voidaan esittää esimerkiksi kaksiulotteisessa taulukossa.
Tilavaativuudet ovat siis luokkaa O(n), verkon solmujen perusteella. 

## Syöte ja tulokset
Syötteenä käytetään kaksiulotteista ascii-merkeistä tehtyä ruudukkoa, jonka perusteella etsitään reitti anettujen pisteiden välillä. Suoritettu reitinhaku esitetään piirrettynä päivittyvään havaintokuvaan kartasta.


## Lähteet
* [https://theory.stanford.edu/~amitp/GameProgramming/index.html](https://theory.stanford.edu/~amitp/GameProgramming/index.html)
* [https://movingai.com/benchmarks/grids.html](https://movingai.com/benchmarks/grids.html)
* [https://www.researchgate.net/publication/ 282488307_Pathfinding_Algorithm_Efficiency_Analysis_in_2D_Grid](https://www.researchgate.net/publication/282488307_Pathfinding_Algorithm_Efficiency_Analysis_in_2D_Grid)
* [https://en.wikipedia.org/wiki/Binary_heap](https://en.wikipedia.org/wiki/Binary_heap)
* [https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
* [https://courses.helsinki.fi/en/tkt20001/119558719](https://courses.helsinki.fi/en/tkt20001/119558719)