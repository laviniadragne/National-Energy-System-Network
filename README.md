# Proiect Energy System Etapa 2

## Despre

Proiectare Orientata pe Obiecte, Seriile CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: Dragne Lavinia-Stefana, 324CA
Email: lavinia.dragne@stud.acs.upb.ro
Link repo de pe GitHub: https://github.com/laviniadragne/proiect-etapa2-energy-system

## Rulare teste

Clasa Test#main
  * ruleaza solutia pe testele din checker/, comparand rezultatele cu cele de referinta
  * ruleaza checkstyle

Detalii despre teste: checker/README

Biblioteci necesare pentru implementare:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations

Tutorial Jackson JSON: 
<https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson>

## Implementare

	* Clasele utilizate pentru realizarea temei sunt impartite in 6 pachete:
input, output, simulation, strategies, entities si utils.
	* Pachetul input contine toate clasele folosite pentru citirea si
parsarea datelor din format JSON, pachetul simulation pe cele ce sunt utilizate 
in simularea sistemului energetic, iar output clasele folosite pentru scrierea 
in fisierele de iesire, in format JSON.
	* Clasele folosite in simulare contin campurile claselor din input, dar
le sunt adaugate si alte metode si variabile specifice. Astfel, se face o 
separare intre rolul fiecarei clase, din proiect, citirea datelor fiind o 
componenta aparte, de simularea jocului in sine. In mod similar, se procedeaza
si cu clasele folosite in scrierea datelor.
	* Pachetul entities contine clasa EnergyType cu tipurile de energie date
de producatori.
	* Pachetul utils contine clasa Constants cu cateva constante utile 
folosite.
	* Pachetul strategies contine clasele folosite pentru implementarea
strategiilor de alegere ale producatorilor, utilizandu-se design pattern-ul Strategy. 


### Entitati

Clasele folosite

	Citirea si scrierea datelor:

	* Clasa InputLoader foloseste biblioteca Jackson, pentru a citit 
fisierele in format JSON si a mapa field-urile obiectului clasei Input, 
la cele din fisierul JSON. Pentru asta, el primeste calea catre fisierul 
cu date de intrare.
	* Clasa Writer mapeaza un obiect de tip Output, ce contine datele 
rezultate in urma simularii, la un fisier JSON.

	Simularea sistemului energetic:

	* Clasa CreateDataBased: 
	-> Contine metode statice pentru crearea claselor folosite in simularea
fiecarei runde. Acestea sunt construite pe baza claselor de input. Astfel, este
creata o lista cu consumatorii, distribuitorii si producatorii din joc, un dictionar
cu id-ul distribuitorilor ca si key si valoarea, obiectul in sine si un dictionar
similar cheie-valoare pentru producatori. De asemenea, la clasele specifice sunt
facute update-urile lunare cu datele noi primite. Fata de etapa I am adaugat nou:

	-> Metoda updateProducers ce adauga lunar noua cantitate de energie la
producatorii din baza de date.

	* Clasele Consumer, Distributor si Producer contin campuri si metode specifice
fiecarei entitati in parte, implicata in sistemul energetic. Ele sunt utilizate pentru
controlul intregii simulari. Clasa Producer va extinde clasa abstracta Observable, iar
clasa Distributor va implementa interfata Observer, acestea fiind utilizate pentru
realizarea design pattern-ului Observatory.

	Fata de etapa precedenta si de campurile din clasele de input am adaugat nou,
in clasele de simulare:

	* In clasa Distributor:
		-> o lista de producatori la care este abonat
		-> un camp care indica daca a fost notificat sau nu de catre producator
		-> pretul contractului oferit

	* In clasa Producer:
		-> o lista de actuali distribuitori abonati
		-> numarul curent de distribuitori abonati
		-> o lista cu situatia abonarilor din fiecare luna

	* Clasa MonthlyStats 
	-> Este folosita pentru a retine situatia dintr-o luna (runda) a abonatilor
unui producator.

	* Interfata entity:
	-> Contine campurile comune pe care le au cele 3 entitati din joc:
distribuitorii, producatori si consumatorii, fiind implementata de toate
componentele sistemului energetic.

	* Clasa EntityFactory

	-> Creeaza, folosind design pattern-ul Factory, pe baza unui tip dat
- entityType, instante ale unor clase inrudite, si anume consumatorii, producatorii si
distribuitorii, ce sunt entitati ale jocului.
	-> Pentru ca nu este nevoie de mai mult de o instanta a unui obiect 
de acest tip, clasa este construita folosindu-se design pattern-ul Singleton,
restrictionandui-se numarul de instantieri.

	* Clasa Simulation

	-> Prin metoda getSimulation, primeste un obiect de tip Input si intoarce
un obiect Output, cu datele rezultate in urma jocului. 
	-> Apeleaza metodele din clasa CreateDateBased si parcurge fiecare runda, 
updatand listele de date.
	-> Toate actiunile lunare ce depinde atat de distribuitori si consumatori, cat 
si de producatori, sunt grupate in metode in clasa Simulation, spre exemplu: 

		 - metoda updateDistributors scoate atat distribuitorii 
falimentari din joc, cat si sterge, din listele consumatorilor si producatorilor,
contractele cu acestia. 
		 - metoda payRate simuleaza plata lunara a ratei consumatorilor,
catre distribuitori, updatand bugetul amandurora si setand starea de falimentar
pentru un consumator, daca este necesar.
		 - metoda failedContract elimina consumatorii falimentari, atat
din lista din baza de date, cat si din contractele distribuitorilor.
	-> Metoda beginningMonth contine ordinea operatiilor de la inceputul unei luni
si apeleaza metodele, folosindu-se de aceasta.

	* Clasele DistributorList, ConsumerList si ProducerList contin liste de
distribuitori, respectiv consumatori si producatori si metode asociate acestora.
In plus fata de etapa precedenta, in aceste clase, s-au adaugat: aplicarea unor
strategii de alegere asupra tuturor distribuitorilor, aplicare schimbarilor costurilor
de productie, actualizarea producatorilor. Operatii precum stergerea unui distribuitor
din listele unui producator, adaugarea update-urilor lunare sunt operatii ce
trebuiesc aplicate pentru toti producatorii, deci vor fi specifice lui ProducerList.
	Operatiile sunt implementate, ca metode, in clasele cu entitati ale
jocului, iar in DistributorList, ConsumerList si ProducerList doar se parcurg listele
si se aplica acele metode, pentru fiecare entitate, in parte.
	
	* Clasa abstracta Strategy 

	-> Este extinsa de toate tipurile de strategii ce pot fi aplicate. Este
folosita deoarece este nevoie de o strategie de alegere cu mai multe implementari
posibile, in cadrul simularii. Aceasta contine, de asemenea, cateva metode de sortare
ale producatorilor, comune, pentru fiecare strategie in parte.

	* Clasele GreenStrategy, PriceStrategy si QuantityStrategy extind clasa
abstracta anterioara, implementand strategiile specifice.

	Pentru optimizarea cautarilor de distribuitori, am utilizat un
dictionar ce contine id-ul ca si key si distribuitorul ca si value. Am procedat
similar si pentru cautarile de producatori.


### Flow

Ce se intampla in fiecare runda (luna), cum comunica entitatile intre ele, ce 
clasa controleaza flowul etc

	Simularea jocului este controlata de catre clasa Simulation. Aceasta, prin
metoda getSimulation, primeste datele din clasele de input si creeaza, folosindu-se de
clasa CreateDataBase, baza de date a consumatorilor, distribuitorilor si
producatorilor. 
	In fiecare runda, sunt facute update-urilor specifice, din input si se aplica
metoda de schimbare de cost din clasa cu lista de distribuitori, DistributorList. Se
apeleaza metoda beginningMonth, ce mentine ordinea operatiilor de la inceputul unei
lunii. Ea interactioneaza cu listele de Consumatori, Distribuitori si Producatori,
pentru a actualiza valorile pentru entitati si a plati costurile. Apoi, se aplica
strategiile de alegere ale producatorilor, printr-o metoda specifica listei de
distribuitori, chooseProducers. In final se face update la listele de distribuitori
ale producatorilor, din acea luna si se reiau operatiile. 
	La finalul simularii, gratie metodei createOutput, informatiile sunt scrise
in clasele de output, in format Json.
	Clasa Simulation este cea care interactioneaza direct cu clasele
CreateDataBase, ConsumerList, DistributorList si ProducerList, controland evolutia
intregului joc. Mai departe, operatiile sunt implementate, ca metode, in clasele cu
entitati ale jocului, iar in DistributorList, ConsumerList si ProducerList doar se
parcurg listele si se aplica acele metode, pentru fiecare entitate, in parte.

### Elemente de design OOP

	# Am folosit cele 4 principii POO de bază astfel:

	-> Abstractizarea: prin construirea clasei abstracte Strategy, dar
si a interfetei Entity, cat si folosirea, in implementare, a clasei abstracte Observable
si a interfetei Observer, pentru a nu permite ca anumite caracteristici (ale
entitatilor, respectiv strategiilor) sa fie vizibile in exterior. Astfel, am construit
un sablon general pe care se bazeaza categoriile acestea de obiecte, fara a le descrie
explicit caractersiticile fiecaruia.
	-> Mostenirea: pentru a extinde clasa Observable, cu clasa Producer si
a aplica design pattern-ul Observatory, dar si pentru a extinde clasa Strategy si
a implementa un nou tip de strategie. Astfel, se refoloseste codul deja existent,
adaugandu-i-se noi functionalitati. Spre exemplu, in cadrul tuturor strategiilor se
folosesc aceleasi tipuri de sortari ale producatorilor, dar dupa criterii diferite.
	-> Incapsularea strategiilor: (adaugarii de noi producatori), aplicand
design pattern-ului Strategy. 
	-> Polimorfismul dinamic: folosind o anumita instanta a tipului de
strategie (ex: Strategy greenStrategy = new GreenStrategy()); aceasta va fi pasata in
toate locurile unde este nevoie de Strategy.

### Design patterns


	In cadrul realizarii proiectului, am folosit design pattern-urile: Singleton,
Factory, Strategy si Observatory.

	Factory a fost folosit prin intermediul clasei EntityFactory, care creeaza, pe
baza unui tip dat - entityType, instante ale unor clase inrudite, si anume
consumatorii, producatorii si distribuitorii.

	Pentru ca nu este nevoie de mai mult de o instanta a unui obiect 
de acest tip, clasa este construita folosindu-se design pattern-ul Singleton,
restrictionandui-se astfel numarul de instantieri.

	Strategy este folosit pentru a decupla comunicarea dintre clasa care aplica 
o strategie de alegere (a producatorilor): Distributor si implementarea strategiilor in
sine. Algoritmul de alegere este selectat la runtime. S-a ales acest pattern pentru ca
era nevoie de un tip de strategie cu mai multe implementari posibile si s-a dorit
alegerea dinamica a acestora. Astfel, s-a definit clasa abstracta Strategy, comuna
pentru toate strategiile implementate, extinsa de clasele GreenStrategy, QuantityStrategy 
si PriceStrategy. Clasa Distributor ce le foloseste, nu este legata
de implementarile acestora concrete, doar le utilizeaza, gratie metodei applyStrategy.

	Observatory este aplicat pentru ca fiecare distribuitor sa afle daca au aparut
modificari la producatorii sai. Distribuitorii sunt Observers, iar producatorii
Observables. In momentul in care un producator primeste de la sistemul de simulare
update pentru energia oferita, atunci isi notifica si distribuitorii. Distribuitorii
iau in considerare ca s-a efectuat o schimbare si seteaza campul update cu True, iar
in luna urmatoare isi realeg producatorii.


### Dificultati intalnite, limitari, probleme

	O problema intalnita a fost utilizarea initiala doar a unor clase cu
informatii pentru fiecare entitate. Astfel, manipularea listelor cu aceste entitati
devenea greoaie, trebuind sa fac cate o metoda de fiecare data cand voiam sa parcurg
una din liste. Astfel, clasele ar fi devenit foarte lungi ca si numar de linii de cod.
Faptul ca fiecare lista trebuia sa aiba metode specifice m-a facut sa
creez cate o clasa ce contine un camp asociat unei liste de entitati (consumerList, 
distributorList, producerList) si o lista de metode ce pot fi aplicate acesteia.
	Pentru a rezolva problema de checkstyle, "magic numbers", am introdus cateva campuri 
pentru constante, in clasa Constants din pachetul utils.
	Pentru a rezolva problema DesignForExtension de checkstyle am facut metodele
corespunzatoare de tip final.
	De asemenea, tot pentru a rezolvare erori de coding style, am adaugat javadoc
pentru fiecare clasa construita.


[optional] ## Feedback, comments

	Partea a 2-a a proiectului a fost, din punctul meu de vedere, mai bine explicata
in enunt, decat prima si asta mi-a facilitat rezolvarea ei.
	Am invatat, in urma acestui proiect, ca daca lucrez organizat de la inceput la
cod, adaugarea unor functionalitati suplimentare, ulterior (in a 2-a etapa respectiv)
va fi facila.	
	Este util ca am primit feedback la prima etapa inainte sa scriem codul pentru
cea de-a doua.
	Unele detalii din prima etapa (de exemplu: Rețeaua de curent electric a unei
țări este un sistem complex, format din producători, distribuitori, consumatori
(casnici sau industriali) și instituții ale statului.) nu au avut continuitate in
cea de-a doua etapa si asta m-a indus putin in eroare.
	A fost util ca am fost indemnati sa invatam cum sa folosim citirea si scrierea
in fisiere de tip JSON, de la 0.
	Obs: README-ul acesta contine, in prima parte, detalii legate si de
implementarile de la etapa I (considerand ca este un README pentru intreg proiectul),
de aceea este destul de lung.
	





