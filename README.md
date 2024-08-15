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

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-15-56-57-image.png?raw=true)

### Activity diagram

Er zijn meerdere activity diagrammen opgesteld. Namelijk een voor elke activiteit die kan worden uitgevoerd in Jabberpoint. Deze staan hieronder in figuren *act 1 t/m 8*.

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-16-02-34-image.png?raw=true)

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-16-02-56-image.png?raw=true)

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-16-03-04-image.png?raw=true)

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-16-03-11-image.png?raw=true)

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-16-03-19-image.png?raw=true)

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-16-03-26-image.png?raw=true)

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-16-03-36-image.png?raw=true)

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-21-19-51-48-image.png?raw=true)

### Class diagram

Voor deel 1 moest een schatting worden gemaakt van het class diagram. Deze valt te vinden in figuur pkg. Houd er rekening mee dat dit diagram incorrect is. Het is slecht een schatting van de student *voordat* de source code is bekeken.

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/2024-04-18-16-02-14-image.png?raw=true)

## Deel 2

In deel twee is het de bedoeling om de code in kaart te brengen en te refactoren. 

# Style

Hier gebeurt iets heel raars. Er worden namelijk `styles` aangemaakt in de class `Style`. Een `Style` bevat dus zowel stijlelementen (kleur, lettertype etc.) als ook een verzameling `styles`. Om er voor te zorgen dat dit niet een *invinity* in elkaar *genest* zitten, is de verzameling `styles` op een statische manier gemaakt. Dit werkt welleswaar, maar is niet volgens *best practises*. Een class moet alleen doen wat een class moet doen. Een `style` moet dus alleen stijlelementen bijhouden. De verzameling `styles` moet ergens anders worden bijgehouden. Hiervoor ga ik een nieuwe class maken genaamd `Theme`. Mijn idee is dat een `style` de stijling van een component is, terwijl een `theme` de stijling van het het programma is. Ofwel een verzameling `styles`. Hiervoor gebruik ik het [Singleton pattern](https://refactoring.guru/design-patterns/singleton). Dit zorgt ervoor dat er slecht een instantie van `Theme` bestaat.

## Presentation

`Presentation` is voor te veel dingen verantwoordelijk. Een `presentation` is in de essentie namelijk gewoon een verzameling `slides`. Zo zou het ook moeten zijn opgebouwd. Dingen als bijhouden op welke `slide` `presentation` is, is niet de verantwoordelijkheid van `presentation`. In de echte wereld zou dit worden gedaan door een projector. Daarom zal ik een class genaamde `Projector` toevoegen die zulke restinformatie over de `presentation` bijhoudt.

## SlideViewerFrame/SlideViewerComponent

Deze class maakt het `frame` waar alle grafische elementen van het programma zich in bevinden. Op zich is het meeste in deze class vrij simpel en hoeft niet verder gerefactord te worden. Het enige is dat er een `slideViewerComponent` wordt gemaakt, deze *heeft* een `presentation`, maar ook *heeft* een `presentation` een `slideViewerComponent`, zo ontstaat er dus een soort *circelverwijzing*. Dit wil ik oplossen door `slideViewComponent` uit `Presentation` te halen. `SlideViewerComponent` is namelijk de *bovenste* class van de twee, dus die zou een `presentation` moeten *hebben* en niet andersom. De comunicatie tussen beiden loopt dan via een [Observer](https://refactoring.guru/design-patterns/observer). Helaas blijkt het heel lastig om een *observer* in een `JComponent` te bouwen. Dus komt hier een tussenclass tussen die de communicatie regelt. Deze class heet `Painter` omdat het de `repaint()` van `SlideViewerComponent` aanroept.

## Acceser

`Accessor` is een abstracte class die twee keer wordt geïmplementeerd. Een keer als `DemoAccessor`. Dit is een gehardcode demo`presentation`. De andere keer is als `XMLAccessor`. Hierin wordt een `presentation` opgebouwd aan de hand van een XML-bestand.

Het is raar dat `Accessor.getDemoAccessor()` bestaat. `Accessor` is een abstracte class. Het doel hiervan is om een soort *bouwplan* te zijn. Het is niet de bedoeling om functies direct vanaf hier aan te roepen.

Hardcoden is een *code smell* omdat het de onderhoudbaarheid van de code negatief beïnvloed. Als iemand de `DemoPresentation` wil wijzigen, zou dat moeten kunnen zonder de code te wijzigen. Vandaar ga ik de gehardcode `DemoPresentation` in een *XML-bestand* zetten. Bijkomend voordeel is dat er dan niet meer twee implementaties van `Accessor` bestaan. Een *abstractie* is dus niet meer nodig. Dit versimpeld de code.

Nu is de `Accessor` voor zowel het laden als het opslaan verantwoordelijk. Om doelgericht te kunnen testen, is het beter dat elke class zijn eigen verantwoordelijkheid heeft. Ik splits deze class dus op in een `XMLLoader` en `XMLSaver`.

## Menu

Een aantal classes zijn verantwoordelijk voor het menu. Dit zijn `AboutBox`, `KeyController` en `MenuController`. Hierin staan twee problemen. Ten eerste is `MenuController` voor zowel de front-end als de back-end verantwoordelijk. Dit is maakt de onderhoudbaarheid lastig. Het is makkelijker als een frontenter en backenter allebei in een eigen bestand kunnen werken. Daarom wordt deze opgesplitst in `MenuLogic` en `MenuView`.

Wat nog meer opvalt is dat er meerdere instanties van `Menu` worden aangemaakt, dit komt omdat elk knopje codetechnisch gezien een `Menu` is. Er is een designpattern hiervoor bedacht, namelijk het `Factory pattern`, dit draagt bij aan de uitbreidbaarheid, in een `Factory` is het makkelijk om een extra `Menu` toe te voegen als dat in de toekomst nodig is. Daarom voeg ik een `Factory` toee.

Verder is het in JabberPoint mogelijk te navigeren naar een slide die helemaal niet bestaat. Dan vertoond het programma vreemd gedrag. Tijdens refactoren is het niet de bedoeling functionaliteiten toe te voegen, maar dit is eerder het oplossen van een bug dan een functionaliteit. Er komt een pop-up als de gebruiker naar een `slide` wil gaan die niet bestaat.

## Diagram

Nu van elke class de huidig en gerefactorde situatie gedocumenteerd is, is het mogelijk om een totaal diagram te geven waarin alles samenkomt.

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/huidig.png?raw=true)

![](https://github.com/RooskenDaniel/JabberpointDaniel/blob/master/images/Refactor.png?raw=true)
