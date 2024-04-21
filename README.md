# README

## Achtergrond

Voor het vak Object Georiënteerd Ontwerpen (OGO) van de opleiding Informatie van het NHL Stenden in Emmen dient het programma *Jabberpoint* gerefactord te worden. Deze GitHub-repo bevat de uitwerking van deze opdracht door de student Daniël Roosken.

## Intro

JabberPoint is een primitief diapresentatie-programma geschreven in Java. Voor deze opdracht dient Jabberpoint in twee delen gerefactord te worden. Deel 1 bestaat uit het maken van diagrammen, deze zijn later in de README te vinden. Deel 2 bestaat uit het maken van diagrammen, problemen binnen Jabberpoint beschrijven *en* coderen. Het theoretische gedeelte van deel twee valt in deze README te lezen, de code in deze GitHub Repo.

## Deel 1

Voor deel 1 moeten een drietal diagrammen aangeleverd worden. Dit zijn, volgens het moduleboek:

*Voor deel 1 krijg je een gecompileerde versie van Jabberpoint. Deze versie moet je gaan analyseren op functionaliteit, gedrag en mogelijke structuur.*

*Aan het eind van dit deel moet je:*

- *Alle functionaliteiten in een **use-case diagram** in kaart gebracht hebben;*
  
- *De flow van acties binnen alle use-cases weergeven in een **activity diagram**;*
  
- *De grove structuur schatten van het programma in een **class diagram**.*
  
  - *Dit is een schatting. Houd het simpel. Besteed hier maximaal een half uur aan.*

### Use-case diagram

In figuur *uc* vind men het use-case diagram.

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-15-56-57-image.png?msec=1713644044991)

### Activity diagram

Er zijn meerdere activity diagrammen opgesteld. Namelijk een voor elke activiteit die kan worden uitgevoerd in Jabberpoint. Deze staan hieronder in figuren *act 1 t/m 8*.

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-02-34-image.png?msec=1713644044991)

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-02-56-image.png?msec=1713644044992)

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-03-04-image.png?msec=1713644044992)

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-03-11-image.png?msec=1713644044992)

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-03-19-image.png?msec=1713644044992)

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-03-26-image.png?msec=1713644044992)

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-03-36-image.png?msec=1713644044992)

### Class diagram

Voor deel 1 moest een schatting worden gemaakt van het class diagram. Deze valt te vinden in figuur pkg. Houd er rekening mee dat dit diagram incorrect is. Het is slecht een schatting van de student *voordat* de source code is bekeken.

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-02-14-image.png?msec=1713644044993)

## Deel 2

Om Jabberpoint te kunnen refactoren, is het eerst belangrijk te weten hoe het werkt. Vandaar wordt hier Jabberpoint *chronologisch* bij langs gelopen. Chronologisch in de zin van dat de code wordt langsgelopen in de zelfde volgorde waarin deze ook uitvoert. Te beginnen in de hoofdclass: `JabberPoint`.

```java
protected static final String IOERR = "IO Error: ";
protected static final String JABERR = "Jabberpoint Error ";
protected static final String JABVERSION = "Jabberpoint 1.6 - OU version";
```

Hier worden drie strings aangemaakt, die zijn verder niet zo heel belangrijk.

```java
Style.createStyles();
```

Hier worden de `styles` aangemaakt op een statische manier. Dit is waarschijnlijk niet de beste manier. Later wordt verder naar gekeken in `Style`.

```java
Presentation presentation = new Presentation();
```

Een nieuwe lege presentatie wordt aangemaakt.

```java
new SlideViewerFrame(JABVERSION, presentation);
```

Een `SlideViewerFrame` wordt aangemaakt die als input de nieuwe lege `presentation` heeft.

Hierna volgt een `try-catch`. De `catch` spreekt voor zich, dit is natuurlijk een error. Daarom wordt alleen de `try` hier verder toegelicht.

```java
if (argv.length == 0)
    Accessor.getDemoAccessor().loadFile(presentation,"");
```

Als de argv.length gelijk is aan 0, met andere woorden, er zijn geen argumenten meegegeven, wordt de `Accesor` aangeroepen, hierin zit een statische functie `getDemoAcceser`. In deze functie zit weer een functie genaamd `loadFile`. Hierin wordt de net aangemaakte `presentation` als input gegeven.

Het gebruik van de argv.length is opvallend. , wie opent nu een diapresentatieprogramma via de terminal? Maar goed, bij een refactoring mogen geen functionaliteiten worden weggehaald. Dus dit blijft zo.

Verder de `getDemoAccessor().loadFile(presentation,"")`, hier wordt een functie van een functie aangeroepen, dat is best wel lelijk. Hier wordt later verder nagekeken in `Accessor`.

```java
else {
    new XMLAccessor().loadFile(presentation, argv[0]);
}
```

Als er een argument is meegegeven bij het openen van Jabberpoint. Wordt geprobeerd dit argument te openen via de `XMLAccessor().loadFile`.

```java
presentation.setSlideNumber(0);
```

Het `slideNumber` van de `presentation` wordt op `0` gezet.

Dit was de `JabberPoint`class. Wat gelijk al opvalt is dat er erg veel in de main gebeurt, dit is een code smell. De main moet zo kort mogelijk zijn. Dit probleem zal worden opgelost door meer funties aan `JabberPoint` toe te voegen waarin ieder functie verantwoordelijk is voor een ding.

![](file://C:\Users\daniel.roosken\AppData\Roaming\marktext\images\2024-04-18-16-13-08-image.png?msec=1713644044993)

*Huidige situatie*

Er zal nu verder verdiept worden, de classes die in `JabberPoint` worden aangeroepen worden ook beschreven. Dit wordt weer chronologisch gedaan, op volgerde waarin deze classes voorkomen in `JabberPoint`.

# Style

```java
public static void createStyles() {
    styles = new Style[5];    
    // De styles zijn vast ingecodeerd.
    styles[0] = new Style(0, Color.red,   48, 20);    // style voor item-level 0
    styles[1] = new Style(20, Color.blue,  40, 10);    // style voor item-level 1
    styles[2] = new Style(50, Color.black, 36, 10);    // style voor item-level 2
    styles[3] = new Style(70, Color.black, 30, 10);    // style voor item-level 3
    styles[4] = new Style(90, Color.black, 24, 10);    // style voor item-level 4
}
```

Hier gebeurt iets heel raars. Er worden namelijk `styles` aangemaakt in de class `Style`. Een `Style` bevat dus zowel stijlelementen (kleur, lettertype etc.) als ook een verzameling `styles`. Om er voor te zorgen dat dit niet een *invinity* in elkaar *genest* zitten, is de verzameling `styles` op een statische manier gemaakt. Dit werkt welleswaar, maar is niet volgens *best practises*. Een class moet alleen doen wat een class moet doen. Een `style` moet dus alleen stijlelementen bijhouden. De verzameling `styles` moet ergens anders worden bijgehouden. Hiervoor ga ik een nieuwe class maken genaamd `Theme`. Mijn idee is dat een `style` de stijling van een component is, terwijl een `theme` de stijling van het het programma is. Ofwel een verzameling `styles`. Hiervoor gebruik ik het [Singleton pattern](https://refactoring.guru/design-patterns/singleton). Dit zorgt ervoor dat er slecht een instantie van `Theme` bestaat.

# Presentation

```java
public class Presentation {
    private String showTitle; //The title of the presentation
    private ArrayLiest<Slide> showList = null; //An ArrayList with slides
    private int currentSlideNumber = 0; //The number of the current slide
    private SlideViewerComponent slideViewComponent = null; //The view component of the slides

    public Presentation() {
        slideViewComponent = null;
        clear();
    }

    ...

    void clear() {
        showList = new ArrayList<Slide>();
        setSlideNumber(-1);
    }
```

Dit is hoe een `presentation` wordt aangemaakt in `JabberPoint`. `Presentation` is voor te veel dingen verantwoordelijk. Een `presentation` is in de essentie namelijk gewoon een verzameling `slides`. Zo zou het ook moeten zijn opgebouwd. Dingen als bijhouden op welke `slide` `presentation` is, is niet de verantwoordelijkheid van `presentation`. In de echte wereld zou dit worden gedaan door een projector. Daarom zal ik een class genaamde `Projector` toevoegen die zulke restinformatie over de `presentation` bijhoudt.

# SlideViewerFrame/SlideViewerComponent

```java
public class SlideViewerFrame extends JFrame {
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    public SlideViewerFrame(String title, Presentation presentation) {
        super(title);
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.setShowView(slideViewerComponent);
        setupWindow(slideViewerComponent, presentation);
    }
```

Deze class maakt het `frame` waar alle grafische elementen van het programma zich in bevinden. Op zich is het meeste in deze class vrij simpel en hoeft niet verder gerefactord te worden. Het enige is dat er een `slideViewerComponent` wordt gemaakt, deze *heeft* een `presentation`, maar ook *heeft* een `presentation` een `slideViewerComponent`, zo ontstaat er dus een soort *circelverwijzing*. Dit wil ik oplossen door `slideViewComponent` uit `Presentation` te halen. `SlideViewerComponent` is namelijk de *bovenste* class van de twee, dus die zou een `presentation` moeten *hebben* en niet andersom. De comunicatie tussen beiden loopt dan via een *observer*. Helaas blijkt het heel lastig om een *observer* in een `JComponent` te bouwen. Dus komt hier een tussenclass tussen die de communicatie regelt. Deze class heet `Painter` omdat het de `repaint()` van `SlideViewerComponent` aanroept.

# Acceser

```java
public abstract class Accessor {
    public static final String DEMO_NAME = "Demo presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

    public static Accessor getDemoAccessor() {
        return new DemoPresentation();
    }
```

`Accessor` is een abstracte class die twee keer wordt geïmplementeerd. Een keer als `DemoAccessor`. Dit is een gehardcode demo`presentation`. De andere keer is als `XMLAccessor`. Hierin wordt een `presentation` opgebouwd aan de hand van een XML-bestand.

Het is raar dat `Accessor.getDemoAccessor()` bestaat. `Accessor` is een abstracte class. Het doel hiervan is om een soort *bouwplan* te zijn. Het is niet de bedoeling om functies direct vanaf hier aan te roepen.

Hardcoden is een *code smell* omdat het de onderhoudbaarheid van de code negatief beïnvloed. Als iemand de `DemoPresentation` wil wijzigen, zou dat moeten kunnen zonder de code te wijzigen. Vandaar ga ik de gehardcode `DemoPresentation` in een *XML-bestand* zetten. Bijkomend voordeel is dat er dan niet meer twee implementaties van `Accessor` bestaan. Een *abstractie* is dus niet meer nodig. Dit versimpeld de code.

Dit waren de classes die in `JabberPoint` worden gebruikt. Er zijn nog meer classes die weer in *deze* classes worden gebruikt. Die zullen hier verder worden uitgelegd.

# Menu

Een aantal classes zijn verantwoordelijk voor het menu. Dit zijn `AboutBox`, `KeyController` en `MenuController`. Hierin staan twee problemen. Ten eerste is `MenuController` voor zowel de front-end als de back-end verantwoordelijk. Dit is maakt de onderhoudbaarheid lastig. Het is makkelijker als een frontenter en backenter allebei in een eigen bestand kunnen werken. Daarom wordt deze opgesplitst in `MenuLogic` en `MenuView`.

Verder is het in JabberPoint mogelijk te navigeren naar een slide die helemaal niet bestaat. Dan vertoond het programma vreemd gedrag. Tijdens refactoren is het niet de bedoeling functionaliteiten toe te voegen, maar dit is eerder het oplossen van een bug dan een functionaliteit. Er komt een pop-up als de gebruiker naar een `slide` wil gaan die niet bestaat.

```java
if (pageNumber < 1 || pageNumber > projector.getPresentation().getSize()) {
```

### SlideItem

Op een `slide` kunnen verschillende `items` staan. Hiervoor wordt gebruik gemaakt van de abstracte class `SlideItem`. Deze wordt geïmplementeerd door `BitmapItem` en `TextItem`. Ik vind dat dit eigenlijk wel mooi gedaan op deze manier, dit laat ik dus zo.
