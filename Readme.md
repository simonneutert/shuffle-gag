# shuffle-gag<!-- omit from toc -->

**Zufällige Episode vom Podcast 'Geschichten aus der Geschichte'** für Nutzer der Apple Podcast App und Spotify!  
Du lernst weiter unten, wie du den Kurzbefehl auf deinen Geräten einrichtest.  

Viel Spaß beim Hören der Episoden.  
Buch das Abo, die Jungs machen einen tollen Job! 💰

[Kurzbefehl App](https://apps.apple.com/de/app/kurzbefehle/id915249334)

---

Mit der Änderung der Spotify API endet die Spotify-Unterstützung in diesem Projekt (vorerst?). 😢  
Der letzte Podcast mit Spotify-URL: GAG550 - Akbar und die Entstehung des Mogulreichs

---

## Was ist das hier?

1. eine einfache Liste aller Episoden im JSON Format (inkl. Bonus-Episoden und FGAGs)
2. eine Dockerfile um einen Webserver mit den Daten zu hosten

Und das macht zusammen: Ein simpler NGINX der JSON ausgibt. Was dir erlaubt per Apple Shortcut/Kurzbefehle eine zufällige Episode vom Podcast '[Geschichten aus der Geschichte](https://www.geschichte.fm)' zu hören.

## Motivation

Nutzer der **Podcast-App** auf dem **iPhone** haben vermutlich festgestellt, dass es ~~zumindest mir~~ unmöglich ist, sich eine zufällige Episode eines Podcasts ausgeben zu lassen.  
Und. Es. Nervt. Unendlich!  

Beim Zubettgehen und/oder Einschlafen läuft eine Folge "[Geschichten aus der Geschichte](https://www.geschichte.fm)". Jeden Mittwoch die neueste Folge. Spätestens samstags ist man dann damit durch. 🙈 Nun habe ich zwar meine absoluten Lieblingsfolgen und Evergreens ([meine Lieblings-Episoden](#meine-persönlichen-highlight-episoden)), aber nur eine zufällige Wiedergabe bringt eben zwangsläufig auch etwas frischen Wind rein.

Dieses Repository soll anderen Podcast-Hörern, die iPhones nutzen, die Freuden einer Zufallswiedergabe bieten. Mit Hilfe der unten beschriebenen Anleitung für die **Apple Kurzbefehle App**. 👇

## Ich will nur shufflen! 🚀

Dazu musst du nur den Shortcut aufsetzen und die `data.json` auf deinen Webhoster laden:  

- `https://meine.url.domain/data.json`

Wie du den [Shortcut erstellst](#shortcut-erstellen) 👈

## Shortcut erstellen

Bevor 1000 Wörter folgen, hier der Screenshots des Shortcuts:

![Screenshot des Shortcuts](./docs/screenshot.jpeg)

1. Inhalte von URL abrufen  
    ➡️ `https://meine.url.domain/data.json`
    ➡️ oder deine eigene URL zur JSON Datei
2. Objekt aus Liste abrufen  
    ➡️ "Zufälliges Objekt" von "Inhalt der URL"
3. Wörterbuch aus Eingabe abrufen  
    ➡️ "Objekt aus Liste" setzen
4. Objekt aus Liste abrufen  
    ➡️ "Zufälliges Objekt" von "Wörterbuch"
5. Wörterbuchwert abrufen  
    ➡️ "Wert" für `url_apple_podcasts` bzw. `url_spotify` in "Objekt aus Liste" abrufen
6. URL öffnen  
    ➡️ "Wörterbuchwert" öffnen

Wer nun wie wir auf dem Homepod im Schlafzimmer hören will, der kann noch weitere Schritte hinzufügen.

Mach das am besten an deinem iPhone wegen der "Übergabefunktion" in der Shortcuts App.

7. Warte  
    ➡️ 3 bis 10 Sekunden
8. Wiedergabe übergeben (nur iPhone)  
    ➡️ von "iPhone" an "Homepod"

### Automatisierte Ergänzung<!-- omit in toc -->

Automatisiert werden automatisch die neuesten Episoden aus dem iTunes Store und Spotify gezogen und in die `newest-episode-apple-podcasts.json` bzw. `newest-episode-spotify.json` geschrieben. Abschließend wird die `data.json` aktualisiert.

## Contributions / Mitmachen

Ich fände es natürlich irre gut, wenn du mir hilfst die Liste zu pflegen.  
Oder den Code verbessert, der die Daten aggregiert.  
Er tut es, aber es ist nicht elegant (oder vertestet 😅).

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
- GAG433 - Der Schinderhannes [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000641037112)
- GAG434 - Ein willkommener Mörder [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000641895496)
- GAG473 - Die Erfindung der Lochkarte [zur Folge](https://podcasts.apple.com/de/podcast/geschichten-aus-der-geschichte/id1044844618?i=1000673214924)

</details>

## FAQ

### Kann ich dir einen Kaffee spendieren?

Ne, musst du nicht, aber wenn du Gutes tun willst, dann kannst du woanders etwas spenden.

Wenn du selbst keine Ideen hast, hier ein paar Vorschläge:

- https://worldbicyclerelief.org
- https://github.com/pi-hole/pi-hole
- https://letsencrypt.org
- https://www.mozilla.org

# Code Testing

Ein paar kleine, erste Tests gibt es nun auch.

```bash
$ bb test:bb
```
