package tde.core;


public class Main {

	public static void main(String[] args) {
		TestDrivenEnviroment.init(args);
	}
		/*
		ich habe mal einen Programmverlauf hierhingeschrieben der übersicht halber, könnt ihr jederzeit umschreiben!

		zunächst test schreiben:
		er kann eingaben machen bis er auf den button für weiter klickt.
		nun wird sein text compiled und auf fehler überprüft.
		sobald nur ein einzelner test fehlschlägt, kann er sich an den nächsten schritt setzen

		dann den Code modifizieren:
		der benutzer kann den code modifizieren bis alle tests laufen oder er wechselt wieder rübe rzu den tests
		beim zurückwechseln wird aber der geschriebene code wieder gelöscht
		wenn alles soweit läuft(alles compiliert und alle tests laufen durch), darf der benutzer refactoren

		und nun refactoren:
		nun darf der benutzer den code verbessern
		vor dem refactoren udn nach dem refactoren müssen immer alle tests laufen!
		und alles beginnt wieder von vorn

		 */
}
