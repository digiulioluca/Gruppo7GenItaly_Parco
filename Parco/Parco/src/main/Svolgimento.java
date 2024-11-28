/*Sistema di Simulazione per un Parco Divertimenti

Progetta un programma in Java che simuli la gestione di un parco divertimenti. L’obiettivo è creare un sistema
per monitorare l’affluenza, gestire le code alle attrazioni e calcolare ricavi e costi operativi.

Requisiti
Attrazioni:

Ogni attrazione ha un nome, una capacità massima di persone, un costo operativo per giro, e un prezzo del biglietto 
per persona. Ogni attrazione può funzionare più volte al giorno, ma non può superare il numero massimo di giri
giornalieri.

Visitatori:

Ogni visitatore ha un budget casuale assegnato all’ingresso e può scegliere quante attrazioni visitare
in base ai prezzi. Se un’attrazione è piena, il visitatore può aspettare (se il tempo di attesa stimato 
è sotto un limite casuale assegnato) o scegliere un’altra attrazione.

Turni di Gestione:

Il parco è aperto per un numero definito di giorni.
Ogni giorno si esegue una simulazione in cui:
I visitatori entrano nel parco.
Sceglieranno le attrazioni da visitare in base al budget e ai tempi di attesa.
Gli incassi e i costi operativi delle attrazioni vengono calcolati.

Statistiche Finali:

Calcola:
Il guadagno netto totale del parco.
L’attrazione più visitata.
Il tempo medio di attesa per attrazione.
La percentuale di visitatori soddisfatti (che hanno potuto visitare almeno un'attrazione).

Vincoli Tecnici

Non usare array o strutture complesse come liste o mappe. Devi gestire tutto tramite variabili e cicli.
Ogni attrazione e visitatore deve essere trattato singolarmente.
Il codice deve essere ben commentato e includere spiegazioni per ogni sezione logica.
*/
package main;

import java.util.Scanner;
import java.lang.Math;

public class Svolgimento {

	public static void main(String[] args) {
		// DICHIARAZIONE VARIABILI
		int sceltaMenu = 0, sceltaParco = 0; // variabili per i menù
		int continuaMenu = 0; // variabile per il primo ciclo DO-WHILE
		int continuaSim = 0; // variabile per il ciclo DO-WHILE della simulazione
		int visitTot = 0, budget = 0; // contenitori visitatori e budget
		int visitGiostra1 = 0, visitGiostra2 = 0, visitGiostra3 = 0;
		/*
		 * dati relativi alle giostre (in ordine): visitatori, prezzo, tempo del giro,
		 * tempo d'attesa per coda, capienza, giri max
		 */
		int visitTot1 = 0, visitTot2 = 0, visitTot3 = 0;
		int prezzoGiostra1 = 5, prezzoGiostra2 = 3, prezzoGiostra3 = 8;
		int tempoGiro1 = 10, tempoGiro2 = 5, tempoGiro3 = 15;
		int coda1 = 0, coda2 = 0, coda3 = 0; // tempi delle singole code
		int capienza1 = 20, capienza2 = 20, capienza3 = 20;
		int giriEff1 = 0, giriEff2 = 0, giriEff3 = 0;
		int tolleranza = 0; // tempo d'attesa max per il visitatore
		int guadagno1 = 0, guadagno2 = 0, guadagno3 = 0; // guadagni giornalieri giostre
		int costoOp1 = 4, costoOp2 = 2, costoOp3 = 5; // costi operativi per giro
		int costiOperativiTot = 0; // contenitore costi operativi totali
		int guadTot = 0, guadNetto = 0; // contenitori dei guadagni (il primo tiene conto dei costi operativi, il
										// secondo no)
		float soddisfatti = 0; // due variabili numeriche decimali per il calcolo della percentuale dei
								// visitatori soddisfatti
		float percSod = 0;
		// APERTURA SCANNER
		Scanner scanner = new Scanner(System.in);

		do {
			// SELEZIONE MENU' PRINCIPALE regolamentata da un do-while per far sì che venga
			// inserito un input corretto
			System.out.println("Benvenuti al parco della Sessione 7! Scegliere un'opzione dal menù: "
					+ "\n1) SIMULAZIONE		2) STATISTICHE		3) ESCI");
			sceltaMenu = scanner.nextInt();

			switch (sceltaMenu) { // SWITCH MENU' PRINCIPALE
			case 1:
				/*
				 * azzeramento delle variabili calcolate nella precedente simulazione (se
				 * effettuata)
				 */
				visitTot = 0;
				guadTot = 0;
				costiOperativiTot = 0;
				soddisfatti = 0;
				percSod = 0;
				guadNetto = 0;
				guadagno1 = 0;
				guadagno2 = 0;
				guadagno3 = 0;
				giriEff1 = 0;
				giriEff2 = 0;
				giriEff3 = 0;
				coda1 = 0;
				coda2 = 0;
				coda3 = 0;

				System.out.println("Benvenuto nella simulazione: ");
				budget = (int) (Math.random() * 21 + 5); // calcoli randomici sul budget e sulle file delle giostre

				visitGiostra1 = (int) (Math.random() * 50 + capienza1);

				visitGiostra2 = (int) (Math.random() * 50 + capienza2);

				visitGiostra3 = (int) (Math.random() * 50 + capienza3);

				visitTot1 = visitGiostra1; // iniziamo a riempire i contenitori dei visitatori totali delle giostre
				visitTot2 = visitGiostra2;
				visitTot3 = visitGiostra3;
				visitTot += visitGiostra1 + visitGiostra2 + visitGiostra3;

				// Stampa info sui visitatori totali per la simulazione
				System.out.println("Sei nel parco assieme a " + (visitTot - 1) + " visitatori.");

				// assegno un numero casuale a tolleranza, la variabile che verrà confrontata
				// con i tempi di attesa
				tolleranza = (int) (Math.random() * 31 + 15);
				System.out.println("Tempo d'attesa max tollerabile: " + tolleranza + " minuto/i.");

				do {
					// SECONDO DO-WHILE. Necessario per mantenere una corretta immissione dei
					// comandi
					// SELEZIONE MENU' SIMULAZIONE
					System.out.println("Hai a disposizione: € " + budget + ".");
					System.out.println(
							"Fila giostra ROSSA: " + visitGiostra1 + "\nFila giostra BLU: " + visitGiostra2 + "\nFila giostra VERDE: " + visitGiostra3);

					// scelta attrazione
					System.out
							.println("Scegli una tra le tre attrazioni disponibili: " + "\n1)ROSSA		2)BLU		3)VERDE");
					sceltaParco = scanner.nextInt();

					// ingresso nelle singole attrazioni
					switch (sceltaParco) { // SWITCH SIMULAZIONE

					case 1:
						// calcolo sui tempi d'attesa per il numero di persone in coda
						coda1 += tempoGiro1 * (visitGiostra1 / capienza1);
						/*
						 * se il budget e il tempo d'attesa max tollerabile dallo spettatore è maggiore
						 * o uguale rispetto al budget e al tempo d'attesa contenuto in coda1, il
						 * visitatore può salire sulla giostra
						 */
						if (budget >= prezzoGiostra1 && tolleranza >= coda1) {
							System.out.println("Giostra ROSSA\n" + "Tempo d'attesa: " + coda1
									+ " minuti. Persone in fila: " + visitGiostra1 + ". Costo: " + prezzoGiostra1);
							/*
							 * se ci sono abbastanza persone in coda (maggiore o uguale) per effettuare
							 * almeno un giro, la giostra parte
							 */
							if (visitGiostra1 >= capienza1) {
								budget -= prezzoGiostra1;
								giriEff1 += (visitGiostra1 / capienza1);
								System.out.println("Acquisto biglietto in corso, attendere" + " l'inizio del giro...");
								/*
								 * while per calcolare il numero di giri effettuato (in base alla coda) e il
								 * guadagno della giostra
								 */
								while ((visitGiostra1 / capienza1) > 0) {
									visitGiostra1 -= capienza1;
									guadagno1 += (prezzoGiostra1 * capienza1) - (costoOp1 * (giriEff1));
								}
								// opzioni per ritornare indietro al menù SIMULAZIONE
								System.out.println(
										"Giro concluso. Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
								continuaSim = scanner.nextInt();
								sceltaParco = 0;
							} else { /*
										 * else che viene eseguito se le persone in coda non riescono a riempire la
										 * giostra (per il totale della sua capienza) per effettuare ALMENO un giro
										 */
								System.out.println(
										"Capienza minima non raggiunta, cambiare giostra. Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
								continuaSim = scanner.nextInt();
								sceltaParco = 0;
							}
						} else {
							/*
							 * nel caso in cui il visitatore che stiamo controllando non riesca a salire
							 * sulla giostra (per motivi di budget o tempi d'attesa), i calcoli sui guadagni
							 * e i giri effettuati vengono fatti lo stesso
							 */
							giriEff1 += (visitGiostra1 / capienza1);
							if (guadagno1 == 0) {
								guadagno1 += (prezzoGiostra1 * capienza1) - (costoOp1 * (giriEff1));
							}

							// scelta per proseguire la simulazione o uscire e tornare al menù principale
							System.out.println("Budget o tolleranza non sufficiente per accedere alla giostra.");
							System.out
									.println("Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
							continuaSim = scanner.nextInt();
							sceltaParco = 0;
						}
						/*
						 * moltiplichiamo le variabili sui costi operativi della giostra e i giri
						 * effettuati. Il risultato lo sommiamo al contenitore dei costi operativi
						 * totali
						 */
						costiOperativiTot += (costoOp1 * giriEff1);

						/*
						 * svuotiamo la fila e reindirizziamo le persone che non riescono a effettuare
						 * un giro verso la giostra successiva (e poi azzerriamo la variabile che ne
						 * tiene conto)
						 */
						visitGiostra2 += (visitGiostra1 % capienza1);
						visitTot2 += (visitGiostra1 % capienza1);
						

						visitGiostra1 = 0;

						break;
					case 2:
						
						
						coda2 += tempoGiro2 * (visitGiostra2 / capienza2);
						if (budget >= prezzoGiostra2 && tolleranza >= coda2) {
							System.out.println("Giostra BLU\n" + "Tempo d'attesa: " + coda2
									+ " minuti. Persone in fila: " + visitGiostra2 + ". Costo: " + prezzoGiostra2);

							if (visitGiostra2 >= capienza2) {
								budget -= prezzoGiostra2;
								giriEff2 += (visitGiostra2 / capienza2);
								System.out.println("Acquisto biglietto in corso, attendere" + " l'inizio del giro...");
								while ((visitGiostra2 / capienza2) > 0) {
									visitGiostra2 -= capienza2;
									guadagno2 += (prezzoGiostra2 * capienza2) - (costoOp2 * (giriEff2));
								}
								System.out.println(
										"Giro concluso. Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
								continuaSim = scanner.nextInt();
								sceltaParco = 0;
							} else {
								System.out.println(
										"Capienza minima non raggiunta, cambiare giostra. Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
								continuaSim = scanner.nextInt();
								sceltaParco = 0;
							}

						} else {
							giriEff2 += (visitGiostra2 / capienza2);
							if (guadagno2 == 0) {
								guadagno2 += (prezzoGiostra2 * capienza2) - (costoOp2 * (giriEff2));
							}
							System.out.println("Budget o tolleranza non sufficiente per accedere alla giostra.");
							System.out
									.println("Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
							continuaSim = scanner.nextInt();
							sceltaParco = 0;
						}

						costiOperativiTot += (costoOp2 * giriEff2);
						visitGiostra3 += (visitGiostra2 % capienza2);
						visitTot2 -= (visitGiostra2 % capienza2);
					
						visitTot3 += (visitGiostra2 % capienza2);

						visitGiostra2 = 0;

						break;
					case 3:
						coda3 += tempoGiro3 * (visitGiostra3 / capienza3);
						if (budget >= prezzoGiostra3 && tolleranza >= coda3) {
							System.out.println("Giostra VERDE\n" + "Tempo d'attesa: " + coda3
									+ " minuti. Persone in fila: " + visitGiostra3 + ". Costo: " + prezzoGiostra3);
							if (visitGiostra3 >= capienza3) {
								budget -= prezzoGiostra3;
								giriEff3 += (visitGiostra3 / capienza3);
								System.out.println("Acquisto biglietto in corso, attendere" + " l'inizio del giro...");
								while ((visitGiostra3 / capienza3) > 0) {
									visitGiostra3 -= capienza3;
									guadagno3 += (prezzoGiostra3 * capienza3) - (costoOp3 * (giriEff3));
								}
								System.out.println(
										"Giro concluso. Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
								continuaSim = scanner.nextInt();
								sceltaParco = 0;
							} else {
								System.out.println(
										"Capienza minima non raggiunta, cambiare giostra. Premi 1 per continuare o un altro numero"
												+ " per uscire dalla simulazione: ");
								continuaSim = scanner.nextInt();
								sceltaParco = 0;
							}

						} else {
							giriEff3 += (visitGiostra3 / capienza3);
							if (guadagno3 == 0) {
								guadagno3 += (prezzoGiostra3 * capienza3) - (costoOp3 * (giriEff3));
							}
							System.out.println("Budget o tolleranza non sufficiente per accedere alla giostra.");
							System.out
									.println("Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
							continuaSim = scanner.nextInt();
							sceltaParco = 0;
						}

						costiOperativiTot += (costoOp3 * giriEff3);
						visitTot3 -= (visitGiostra3 % capienza3);
						visitGiostra3 = 0;

						break;
					default:
						/*
						 * nel caso in cui venga inserito un codice errato (numero>3), mettiamo il
						 * visitatore di fronte a una scelta: restare nella simulazione o tornare al
						 * menù principale
						 */
						System.out.println("Codice errato");
						System.out.println("Premi 1 per continuare o un altro numero per uscire dalla simulazione: ");
						continuaSim = scanner.nextInt();
						sceltaParco = 0;
					}
					// decisione finale per restare o meno nella simulazione
					if (continuaSim != 1) {
						System.out.println("Fine della simulazione: \nPremi 1 per continuare: ");
						continuaMenu = scanner.nextInt();
						sceltaMenu = 0;
					}

				} while (continuaSim == 1);

				break;
			// STATISTICHE DEL PARCO (per turno)
			case 2:
				System.out.println("STATISTICHE PARCO SESSIONE 7 (per turno)\n");
				/*
				 * calcolo sui guadagni totali del parco (tenendo conto dei costi operativi
				 * delle singole giostre, moltiplicati nel do-while della simulazione ai giri
				 * effettuati)
				 */
				guadTot = guadagno1 + guadagno2 + guadagno3;

				// stampa dei costi operativi totali
				System.out.println("Totale dei costi operativi: € " + costiOperativiTot + ".");

				// calcolo sul guadagno netto (e stampa)
				guadNetto = guadTot + costiOperativiTot;
				System.out.println("\nGuadagno lordo: € " + guadNetto + ".");
				// stampa guadagni totali del parco
				System.out.println("\nGuadagno netto: € " + guadTot + ".\n\nAttrazione più visitata: ");

				// CALCOLO PERCENTUALE VISITATORI SODDISFATTI:

				/*
				 * 1) calcolo sulle singole variabili dei visitatori soddisfatti per rimuovere
				 * le persone che non sono riuscite a salire su ALMENO una giostra
				 */
				visitTot1 = visitTot1 - (visitTot1 % capienza1);
				visitTot2 = visitTot2 - (visitTot2 % capienza2);
				visitTot3 = visitTot3 - (visitTot3 % capienza3);

				/*
				 * 2) attraverso una serie di if-else if controlliamo quale attrazione ha
				 * registrato il maggior numero di visite
				 */
				if (visitTot1 > visitTot2 && visitTot1 > visitTot3) {
					System.out.println("Giostra rossa, con " + visitTot1 + " visite.");
				} else if (visitTot2 > visitTot1 && visitTot2 > visitTot3) {
					System.out.println("Giostra blu, con " + visitTot2 + " visite.");
				} else if (visitTot3 > visitTot1 && visitTot3 > visitTot2) {
					System.out.println("Giostra verde, con " + visitTot3 + " visite.");
				} else if (visitTot1 == visitTot2 && visitTot1 > visitTot3 && visitTot2 > visitTot3) {
					System.out.println("Giostra rossa e giostra blu, con " + visitTot1 + " visite.");
				} else if (visitTot2 == visitTot3 && visitTot2 > visitTot1 && visitTot3 > visitTot1) {
					System.out.println("Giostra verde e giostra blu, con " + visitTot2 + " visite.");
				} else if (visitTot1 == visitTot2 && visitTot1 == visitTot3 && visitTot2 == visitTot3) {
					System.out.println("Tutte e 3 le attrazioni hanno registrato un numero di visite uguali");
				} else {
					System.out.println("Giostra rossa e giostra verde, con " + visitTot3 + " visite.");
				}

				// 3) somma dei visitatori soddisfatti totale
				soddisfatti = (int) (visitTot1 + visitTot2 + visitTot3);

				// 4) calcolo e arrotondamento della percentuale sulla variabile percSod
				percSod = (int) ((soddisfatti / visitTot) * 100);

				// stampa
				System.out.println("\nPercentuale visitatori soddisfatti: " + percSod + "% (" + (int) soddisfatti
						+ " persone su un totale di " + visitTot + ").");

				System.out.println("\nTempo per ogni attrazione: \n\nGiostra rossa: " + coda1
						+ " minuto/i. Giostra blu: " + coda2 + " minuto/i. Giostra verde: " + coda3 + " minuto/i.");

				/*
				 * azzeramento delle variabili calcolate in precedenza in vista di una eventuale
				 * simulazione successiva
				 */
				visitTot = 0;
				guadTot = 0;
				costiOperativiTot = 0;
				soddisfatti = 0;
				percSod = 0;
				guadNetto = 0;
				guadagno1 = 0;
				guadagno2 = 0;
				guadagno3 = 0;
				giriEff1 = 0;
				giriEff2 = 0;
				giriEff3 = 0;
				coda1 = 0;
				coda2 = 0;
				coda3 = 0;

				System.out.println("\nFine delle statistiche: \nPremi 1 per tornare al menù principale: ");
				continuaMenu = scanner.nextInt();
				sceltaMenu = 0;
				break;

			// caso 3: uscita dal programma con il metodo exit();
			case 3:
				System.out.println("Stai uscendo dal programma.");
				System.exit(0);
				break;

			default:
				System.out.println("Codice errato \nInserisci 1 per continuare: ");
				continuaMenu = scanner.nextInt();
				sceltaMenu = 0;
			}

		} while (continuaMenu == 1);
		// chiusura scanner
		scanner.close();

	}

}