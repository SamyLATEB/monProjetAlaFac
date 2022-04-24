import java.util.ArrayList;
import java.util.Scanner;

public class Conway {
	/*
	 * Méthode permettant de demander les noms des joueurs et leurs roles.
	 */
	private static Joueur[] entrerJoueur(){
		// On crée un scanner pour prendre l'entrer de l'utilisateur.
		Scanner sc = new Scanner(System.in);

		// On crée une variable pour entrer le nom du joueur.
		String nom;

		// Variable contenant le nombre de joueur.
		int nbJ, nbJ1;

		// Permet de redemander si un caractère a été entré au lieu d'un chiffre.
		boolean erreur;

		// Tant qu'on a une erreur.
		do {
			// Gestion d'erreur au cas où un charactère a été entrée.
			try {
				erreur = false;
				System.out.print("Entrer nombre de joueur (1-9) : ");
				nbJ = sc.nextInt();
			} catch (java.util.InputMismatchException e) {
				sc.nextLine();
				System.out.println("Erreur.");
				nbJ = 0;
				erreur = true;
			}
			// On ne peux avoir que 9 joueurs au max.
			if(nbJ < 1 || nbJ > 9) erreur = true;
		}while(erreur);

		// Pour le retour à la ligne.
		sc.nextLine();

		// On crée une liste des joueurs.
		Joueur[] joueurs = new Joueur[nbJ];

		// Liste permettant d'eviter d'attribuer deux même role à differantes personnes.
		ArrayList<Integer> rolesAttribue = new ArrayList<>();

		// On entre tous les noms des joueurs et leurs roles.
		for(int i = 0; i < nbJ; i++){
			System.out.print("Entrer le nom du joueur n°" + Integer.toString(i+1) + " : ");
			nom = sc.nextLine();

			System.out.println("\nRôle 0 : Aucun\nRôle 1 : Pilote : peut se déplacer vers une zone non submergée arbitraire (coûte une action).\n" +
					"Rôle 2 : Ingénieur :  peut assécher deux zones pour une seule action.\n" +
					"Rôle 3 : Explorateur : peut se déplacer et assécher diagonalement.\n" +
					"Rôle 4 : Navigateur : peut déplacer un autre joueur (coûte une action).\n" +
					"Rôle 5 : Plongeur : peut traverser une zone submergée (coûte une action).\n" +
					"Rôle 6 : Messager : peut donner une clé qu’il possède à un joueur distant (coûte une action).\n");

			// Tant qu'on a une erreur.
			do {
				// Gestion d'erreur au cas où un charactère a été entrée.
				try {
					erreur = false;
					System.out.print("Entrer role (0-6) : ");
					nbJ1 = sc.nextInt();
				} catch (java.util.InputMismatchException e) {
					sc.nextLine();
					System.out.println("Erreur.");
					nbJ1 = 0;
					erreur = true;
				}
				// On a que 6 roles + 1.
				if(nbJ1 < 0 || nbJ1 > 6) erreur = true;
				// Pas deux fois les même roles.
				else if(rolesAttribue.contains(nbJ1)) erreur = true;
				// Sinon, c'est bon, on crée le nouveau joueur.
				else{
					rolesAttribue.add(nbJ1);
					joueurs[i] = new Joueur(nom, nbJ1);
				}
			}while(erreur);
			sc.nextLine();
		}

		return joueurs;
	}

	public static void main(String[] args){
		System.out.println("-------------------------\nProjet POGL : Île Interdite\n-------------------------");
	    Controleur j = new Controleur(9, 6, entrerJoueur());
		j.start();
		return;
	}
}