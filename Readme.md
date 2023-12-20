# shuffle-gag<!-- omit from toc -->

**Zufällige Episode vom Podcast 'Geschichten aus der Geschichte'** für Nutzer der Apple Podcast App und Spotify!  
Du lernst weiter unten, wie du den Kurzbefehl auf deinen Geräten einrichtest.  

[Kurzbefehl App](https://apps.apple.com/de/app/kurzbefehle/id915249334)

🦊 Schlaue Füchse setzen sich ein Lesezeichen zu dieser URL, um bei Änderungen an der API bzw. der Datenstruktur, oder überhaupt wenn was nicht geht, schnell Hilfe zu bekommen 🐒

🙏 SCHAU AUCH MAL IN DIE [ROADMAP](Roadmap.md)<!-- omit in toc -->

> PS: Die Bonus-Episoden sind in meinem Shuffle für Apple Podcast nicht enthalten.  
> Aber es sollte ein Leichtes sein, sie zu ergänzen.  
> Ich nehme gerne [Pull Requests](https://docs.github.com/de/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request) an. ❤️

- [Was ist das hier?](#was-ist-das-hier)
- [Motivation](#motivation)
- [Ich will nur shufflen - nichts coden! 🚀](#ich-will-nur-shufflen---nichts-coden-)
- [Shortcut erstellen](#shortcut-erstellen)
- [Wie füge ich eine neue Episode von Apple Podcasts hinzu?](#wie-füge-ich-eine-neue-episode-von-apple-podcasts-hinzu)
- [Wie füge ich eine neue Episode von Spotify hinzu?](#wie-füge-ich-eine-neue-episode-von-spotify-hinzu)
- [Contributions / Mitmachen](#contributions--mitmachen)
- [An die Podcasters](#an-die-podcasters)
- [Meine persönlichen Highlight Episoden](#meine-persönlichen-highlight-episoden)
- [FAQ](#faq)
  - [Wenn alles über deinen Server läuft, dann DSGVO und so, oder?](#wenn-alles-über-deinen-server-läuft-dann-dsgvo-und-so-oder)
  - [Wie geht das für Spotify?](#wie-geht-das-für-spotify)
  - [Kann ich mitmachen?](#kann-ich-mitmachen)
  - [Kann ich dir einen Kaffee spendieren?](#kann-ich-dir-einen-kaffee-spendieren)

## Was ist das hier?

1. eine einfache Liste aller Episoden im JSON Format (aktuell ohne Bonus-Episoden und FGAGs)
2. eine Dockerfile um einen Webserver mit den Daten zu hosten

Und das macht zusammen: Ein simpler NGINX der JSON ausgibt. Was dir erlaubt per Apple Shortcut/Kurzbefehle eine zufällige Episode vom Podcast '[Geschichten aus der Geschichte](https://www.geschichte.fm)' zu hören.

## Motivation

Nutzer der **Podcast-App** auf dem **iPhone** haben vermutlich festgestellt, dass es ~~zumindest mir~~ unmöglich ist, sich eine zufällige Episode eines Podcasts ausgeben zu lassen.  
Und. Es. Nervt. Unendlich!  

Beim Zubettgehen und/oder Einschlafen läuft eine Folge "[Geschichten aus der Geschichte](https://www.geschichte.fm)". Jeden Mittwoch die neueste Folge. Spätestens samstags ist man dann damit durch. 🙈 Nun habe ich zwar meine absoluten Lieblingsfolgen und Evergreens ([meine 😍Episoden](#meine-persönlichen-highlight-episoden)), aber nur eine zufällige Wiedergabe bringt eben zwangsläufig auch etwas frischen Wind rein.

Dieses Repository soll anderen Podcast-Hörern, die Apple Podcasts nutzen, die Freude einer Zufallswiedergabe bieten. Vor allem mithilfe der unten beschriebenen Anleitung für die **Kurzbefehle App** auf aktuellen iPhones. 👇

## Ich will nur shufflen - nichts coden! 🚀

Du musst nicht coden können, sondern darfst gerne meinen Server nutzen.  
Dazu musst du nur den Shortcut aufsetzen und darin die von mir zur Verfügung gestellte URL einsetzen:  

- `https://gag.trojanischeresel.de/data.json` Apple Podcasts
- `https://gag.trojanischeresel.de/data-spotify.json` Spotify

Wie du den [Shortcut erstellst](#shortcut-erstellen) 👈

## Shortcut erstellen

Bevor 1000 Wörter folgen, hier der Screenshots des Shortcuts:

![Screenshot des Shortcuts](./docs/screenshot.jpeg)

1. Inhalte von URL abrufen  
    ➡️ `https://gag.trojanischeresel.de/data.json` für Apple Podcasts  
    ➡️ `https://gag.trojanischeresel.de/data-spotify.json` für Spotify  
    ➡️ oder deine eigene URL zur JSON Datei
2. Objekt aus Liste abrufen  
    ➡️ "Zufälliges Objekt" von "Inhalt der URL"
3. Wörterbuch aus Eingabe abrufen  
    ➡️ "Objekt aus Liste" setzen
4. Objekt aus Liste abrufen  
    ➡️ "Zufälliges Objekt" von "Wörterbuch"
5. Wörterbuchwert abrufen  
    ➡️ "Wert" für `url` in "Objekt aus Liste" abrufen
6. URL öffnen  
    ➡️ "Wörterbuchwert" öffnen

Wer nun wie wir auf dem Homepod im Schlafzimmer hören will, der kann noch weitere Schritte hinzufügen.

Mach das am besten an deinem iPhone wegen der "Übergabefunktion" in der Shortcuts App.

7. Warte  
    ➡️ 3 bis 10 Sekunden
8. Wiedergabe übergeben (nur iPhone)  
    ➡️ von "iPhone" an "Homepod"

## Wie füge ich eine neue Episode von Apple Podcasts hinzu?

Es gibt mehrere Wege. Ich habe sie nach zunehmendem Aufwand sortiert.

<details><summary>Toggle me! 🥳</summary>

### The CI/CD way (empfohlen)<!-- omit in toc -->

Jeden Tag wird automatisch die neueste Episode aus dem iTunes Store gezogen und in die `newest-episode.json` geschrieben.

https://github.com/simonneutert/shuffle-gag/blob/main/newest-episode.json

Man muss sie dann nur noch in die `data.json` manuell einfügen.

### Easy way<!-- omit in toc -->

Besuche den neuesten Podcast in der Podcast App und teile die Episode per "Link kopieren".  
Anschliessend legst du ein zusätzliches JSON Objekt in der Datei `data.json` an.

Das Zielschema erkennst du dann schon an den anderen Objekten. Achte bei der URL auf das "kurze" Format!

Dieser Teil der URL ist fix: `https://podcasts.apple.com/de/podcast/id1044844618?i=`  
Ergänze ihn um die ID der Episode, die du kopiert hast (im Beispiel: `1000637567530`).

Das geht übrigens super easy wenn du während du in deinem Browser hier auf der Seite bist und das liest, einfach die Taste "." (PUNKT) drückst. Dann öffnet sich die Datei in deinem Editor und du kannst sie bearbeiten. 🤯

```json
[
    // ... shortened for brevity
    {
      "tag": "gag428",
      "title": "Der Fälscher Konstantinos Simonides",
      "url": "https://podcasts.apple.com/de/podcast/id1044844618?i=1000637567530"
    }
]
```

### Moby Docker's way 🐳<!-- omit in toc -->

Du kannst auch Docker nutzen, wenn du diese Repo geklont hast:

```bash
# das shell script ist absichtlich nicht ausführbar, daher:
docker run -ti --rm ubuntu bash -c "$(cat ./bin/get-newest-episode.sh)"
```

### The Hard Way 🤓<!-- omit in toc -->

Richtig nerdig wird es, wenn du die Daten selbst aus dem iTunes Store ziehst. Und durch CLI Tools jagst.

Am besten geht das an einen Mac oder Linux (also auch unter Windows dann mit WSL).

Du musst dazu folgende Abhängigkeiten installieren:

- [httpie](https://httpie.org/) //  `brew install httpie`
- [jq](https://stedolan.github.io/jq/) // `brew install jq`

#### 1. Lade die neusten 10 Episoden aus dem iTunes Store<!-- omit in toc -->

```bash
https "https://itunes.apple.com/lookup?id=1044844618&country=DE&media=podcast&entity=podcastEpisode&limit=10" > /tmp/itunes.json
```

#### 2. Daten aufbereiten<!-- omit in toc -->

Mittels `jq` kannst du die Daten aufbereiten.

```bash
cat /tmp/itunes.json | jq ".results[1] | {tag: ((.trackName|split(\":\")[0])|ascii_downcase), title: (.trackName|split(\": \")[1]), url: ([\"https://podcasts.apple.com/de/podcast/id1044844618?i=\",((.trackViewUrl|split(\"i=\")[1])|split(\"&\")[0])]|join(\"\"))}"
```

`jq ".results[1]` ist das zweite Element in der Liste. Das erste ist der Podcast selbst 🤷‍♂️  
wenn du also eine ältere Episode brauchst, dann musst du das `1` durch eine h¨here Zahl ersetzen.

Naja, auf jeden Fall, wenn du das erledigt hast, kommt ungefähr sowas raus:

```json
{
  "tag": "gag429",
  "title": "Der Eimerkrieg",
  "url": "https://podcasts.apple.com/de/podcast/id1044844618?i=1000638321858"
}
```

und das widerrum gehört dann ans Ende der `data.json`.

```json
[
    // ... shortened for brevity
    {
      "tag": "gag428",
      "title": "Der Fälscher Konstantinos Simonides",
      "url": "https://podcasts.apple.com/de/podcast/id1044844618?i=1000637567530"
    },
    {
      "tag": "gag429",
      "title": "Der Eimerkrieg",
      "url": "https://podcasts.apple.com/de/podcast/id1044844618?i=1000638321858"
    }
]
```

</details>

## Wie füge ich eine neue Episode von Spotify hinzu?

Das geht leider (noch?) nicht automatisiert.  
Aber du kannst die Daten manuell in der Datei `data-spotify.json` ergänzen, das von mir eingesetzte Schema sollte hoffentlich selbsterklärend sein. 🤞

## Contributions / Mitmachen

Ich fände es natürlich irre gut, wenn du mir hilfst die Liste zu pflegen. 

**Super geeky** wäre es, wenn wir **alles** in eine GitHub Action packen, die den Code per trigger updaten kann. Aber das ist nur ein Gedanke und vermutlich aufwendiger als es jede Woche schnell selbst zu erledigen ([Source](https://xkcd.com/1205/)). Schliesslich kann man von jeder Maschine mit Internetzugang auf diese Seite surfen und dann die Taste "." / "PUNKT" auf der Tastatur drücken und die Daten schnell ergänzen.

Oder wir machen es in einem Google Sheet. Oder wir pflegen die Daten in einer csv file und konvertieren sie dann automatisiert in ein JSON Objekt. Und packen das dann auf einen NGINX Server. Da kann man sich so richtig ausnerden.

Oder ... oder ... oder ...

**Danke** für deine Hilfe! ❤️ UND/ODER viel Spaß beim Hören der Episoden. Buch das Abo, die Jungs machen einen tollen Job! 💰

## An die Podcasters

@meszner und @stormgrass 👋

Ich habe das hier nur gemacht, weil ich es UNBEDINGT gebraucht hätte, als ich krank im Bett lag. Und ich nicht ständig im Podcast Feed umherscrollen wollte, um dann doch immer wieder die gleichen Episoden zu hören.

Natürlich ist dieses Projekt hinfällig, wenn ihr irgendwann eine URL für das Shuffling unterhaltet. Dann bitte ich um eine kurze Info, damit ich dieses Repo hier archivieren kann. Danke! ❤️

Aber eigentlich macht es ja mehr Sinn, wenn ihr unter eurer Domain eine URL für so einen Kurzbefehl zur Verfügung stellt. Mit Anleitung auf dem Blog ... und so. Ich würde mich freuen, wenn ihr das macht ❤️

Am Ende ist es ja vielleicht wie im Supermarkt, wenn man etwas sucht. Sich dann endlich ein Herz fasst und nach Hilfe fragt. Man angelächelt wird und gesagt bekommt, man müsse sich nur bücken oder umdrehen. Das Produkt fällt einem aus dem Regal in die Hand entgegen. Alle lachen, man bekommt auf die Schulter geklopft und beim Rausgehen schwenkt sogar wer Fahnen und ein Kinderchor bläst auf Vuvuzelas das Lied von der Sendung mit der Maus.  
**Alle haben und nutzen bereits Shuffle, jeder weiss es, alle, nur ich nicht?**

## Meine persönlichen Highlight Episoden

Ohne Anspruch auf Vollständigkeit 🥸 aber in chronologischer Reihenfolge:

<details><summary>Toggle me! 🥳</summary>

- GAG26 - Wie der Champagner zu seinen Bläschen kam [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000365199724)
- GAG61 - Die niederländische 'Tulpenmanie' (und warum sie gar nicht so schlimm war) [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000378200683)
- GAG85 - Ein Arm, ein Hai, ein Kriminalfall [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000385388548)
- GAG104 - Crécy - Chronik eines Versagens [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000392482829)
- GAG120 - Die Rückkehr des Martin Guerre [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000399513604)
- GAG139 - Als Voltaire die Lotterie knackte und steinreich wurde [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000412192106)
- GAG151 - Manjirō, der erste Japaner in Amerika [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000417848647)
- GAG154 - La Maupin, die duellierende Opernsängerin [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000419145347)
- GAG173 - Der gefährliche Garten von Vaux-le-Vicomte [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000427760439)
- GAG184 - Katharina Kepler – ein Hexenprozess in der Frühen Neuzeit [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000434026525)
- GAG199 - UC 71 und der U-Boot-Krieg im Ersten Weltkrieg [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000444665828)
- GAG205 - Die Befreiung von Schloss Itter [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000447991082)
- GAG219 - Die Kotze-Affäre [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000458628401)
- GAG244 - Die Mühle von Auriol und warum ihre Zerstörung eine Besetzung Frankreichs verhindert hat [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000475920023)
- GAG245 - Operation Paul Bunyan [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000476630178)
- GAG248 - Der Venustransit von 1761/69 und das erste wissenschaftliche Großprojekt [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000479385823)
- GAG258 - Der Andrews Raid - Eine Lokomotive auf Abwegen [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000489908196)
- GAG259 - Operation Mincemeat – Eine Geheimdienstaktion während des Zweiten Weltkriegs [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000490585058)
- GAG266 - Die Schlacht von Azincourt [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000496401450)
- GAG275 - Victor Lustig – Der Mann, der den Eiffelturm verkaufte [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000503871113)
- GAG309 - Die Bestie des Gévaudan [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000533067723)
- GAG312 - Der beste aller Ritter – das Leben von Guillaume le Maréchal [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000535387902)
- GAG331 - Wie Tetris die Welt eroberte [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000549035712)
- GAG354 - Die Halsbandaffäre [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000568931104)
- GAG362 - Bayerns letzte Kurfürstin [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000577840766)
- GAG365 - The Ghost Army [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000580158090)
- GAG377 - Aufstieg und Fall des Templerordens [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000590045562)
- GAG383 - Bletchley Park [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000596383457)
- GAG406 - Die SMS Wolf und die Piraten des Kaisers [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000619254302)
- GAG413 - Paracelsus – Arzt und Alchemist [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000625306816)

</details>

## FAQ

### Wenn alles über deinen Server läuft, dann DSGVO und so, oder?

Na, aber, aber, ich bitte dich. 

Ich will weder von dir irgendwelche Daten haben, noch gebe ich meine Daten gerne her.  
Also speichere ich selbst aktiv keine Daten/IPs und Cookies bekommst du von mir auch keine!  
Das hast du davon!

### Wie geht das für Spotify?

Es ist das gleiche Prinzip. Du musst nur die URL anpassen.

Natürlich musst du den Auth Flow noch machen und und und ...

Dann ist es am Ende aber nur noch:

`http GET 'https://api.spotify.com/v1/shows/0cPsvdqTreF6sKg6VwSrMl/episodes?market=DE&limit=50&offset=0' \
  Authorization:'Bearer this-is-mysupersecretbearertoken'`

Anstatt `gag.trojanischeresel.de/data.json`  
musst du in deinem Kurzbefehl  
`gag.trojanischeresel.de/data-spotify.json`  
verwenden.

🚨 Hier kann ich leider nicht ohne etwas Aufwand automatisieren (aber ich denke es ginge auch).  🚨  

Hilfe ist willkommen. ❤️

### Kann ich mitmachen?

Klar, gerne. Schau dir die [Contributions](#contributions--mitmachen) an.

### Kann ich dir einen Kaffee spendieren?

Ne, musst du nicht, aber wenn du Gutes tun willst, dann kannst du woanders etwas spenden.

Wenn du selbst keine Ideen hast, hier ein paar Vorschläge:

- https://worldbicyclerelief.org
- https://github.com/pi-hole/pi-hole
- https://letsencrypt.org
- https://www.mozilla.org
