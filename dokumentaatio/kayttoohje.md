## Käyttöohje

1. Ltaa uusin release ja pura se kansioon

2. Ohjelma käyttää syötteenä [Moving AI-lab:in](https://movingai.com/benchmarks/grids.html) .map muotoisia karttoja ja .scen muotoisia testiskenarioita. Releasen mukana tulee esimerkkikartta ja skenaario. Jos haluat kokeilla muita karttoja, lataa se ja siihen liittyvä skenaario ja sijoita samaan kansioon kuin .jar.

3. Aja ohjelma `java -jar Polunhakija.jar`

4. Ohjelma kysyy aluksi kartan nimeä. Kirjoita se pyydetyssä muodossa, esim. "AR0015SR.map"

5. Komentorivikäyttöliittymä sisältää seuraavat ominaisuudet:
  * ajaa läpi kaikki testit skenaariosta
  * avaa interaktiivisen visualisoijan
  * vaihtaa käytettävää karttaa

6. Visualisoija sisältää seuraavat toiminallisuudet:
  * ylimässä nappulassa lukee esitettävän algoritmin nimi ja sitä painamalla esitettevä algoritmi vaihtuu Dijkstra <-> JPS
  * 4 syötettä ovat koordinaateille, vasemmalta oikealla, ylhäältä alas järjestyksessa: 
    + lähdön x-koordinaatti
    + lähdön y-koordinaatti
    + maalin x-koordinaatti
    + maalin y-koordinaatti

    Huom! koordinaatit kasvavat X: vasemmalta oikealle, Y:ylhäältä alas.

  * Nappula "find path" etsii reitin pisteiden välillä
  * "clear" tyhjentää kentät
