# Testausdokumentti


## Testien tuleva muoto
Testaus tulee tapahtumaan käyttäen Moving AI Labs:in karttoihin liittyviä scenarioita, joista löytyy useita valmiita reittejä optimaalipituuksilla.
Kumpaakin algoritmia (Dijkstra & JPS) ajetaan näillä reiteillä ja verrataan algoritmien löytämien reittien pituuksia (parhaassa tapauksessa samat), sekä hakuihin kuluvaa aikaa.
Lisäksi apuna on visualisointi, jolla voi havainnolistaa hakujen toimintaa, esimerkiksi: tutkitut solmut, hyppypisteet, löydetty reitti.
Testien pohjana käytetään Baldur's Gate 2 karttoja, jotka ovat kooltaan 512*512 ruutua, mikä muuntuu suoraa verkon solmujen lukumääräksi.

## Tämänhetkiset testi
Algoritmit voidaan jo ajaa scenario-reittejä vastaan, mutta vielä vertailussa vain reittien pituudet.


