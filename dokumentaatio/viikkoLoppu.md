# Viimeinen viikkoraportti

työaika ~10h

## Edistys
JPS saatiin vihdoin toimimaan optimaalisesti, kun huomasin tarkistaa naapurin karsintasäännöt oikein. Nyt karsitaan myös sivuttaisten liikkeiden kohdalla alkiot joihin on jo päästy lyhemmällä reitillä aikaisemmin. Samoin mikäli löytyy solmu jonka kuljettu etäisyys olikin suurempi nyt uudestaan saavutettu, se päivitetään oikein pienemmäksi.

Vastaavasti Dijkstrasta löytyi pieni bugi, sivuttaisen naapurin tarkistuksessa tulevaa etäisyyttä verrattiin väärin. Tämän huomasi vasta kun optimaalinen reitti erosi hyvin suurilla syötteillä, mutta paransi huomattavasti suorituskykyä kaikissa tilanteissa.

Vertailua tehty, ohjelma paketissa ja dokumentaatio kunnossa.