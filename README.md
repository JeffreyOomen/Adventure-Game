# Adventure-Game
A small adventurous game written with Java Spring Boot. The focus lays on non-functional requirements.

## 1. lokale database aanmaken genaamd: 'ivh11a2bproftaak';

## 2. In de application.properties gewenste username en wachtwoord instellen (default: username: root, password: '');

## 3. Op regel 38 in het bestand context.xml de volgende regel uitcommenten: <!-- < prop key="hibernate.hbm2ddl.auto">create</ prop> -->

## 4. Applicatie starten via url: http://localhost:8080/login (inlogpagina)

*  keuze om een nieuwe account aan te maken of default account met username: test en wachtwoord password1.

## 5. Nadat je ingelogd bent is de start url: http://localhost:8080/

* Dit is de startpagina en verder is er naar een battle te navigeren door op de 'Find an opponent' (start gevecht) of 'Inventory' (inventory bekijken) button te klikken.
* Tijdens een gevecht is het mogelijk een special attack te doen nadat je een 'Overload Potion' hebt gehad en opgedronken vanuit de inventory (via inventory bekijken).
* Healen kan als je een 'Heal Potion' hebt in je inventory tijdens de battle.
* In de inventory kan je items 'usen' aandoen of potions drinken. Wanneer je gear aandoet wordt je sterker.

## 6. (Optioneel) comment regel 38 van de context.xml weer uit zodat de database niet telkens opnieuw aangemaakt wordt en je character behouden blijft.
