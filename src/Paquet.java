import java.util.ArrayList;
import java.util.Random;

public class Paquet {
	protected ArrayList<Integer> paquet;
	protected ArrayList<Integer> paquetDefausse;
	private Random random = new Random();

	/*
	 * Constructeur
	 * @param size : la taille du paquet.
	 */
	public Paquet(int size){
		paquet = new ArrayList<>();
		paquetDefausse = new ArrayList<>();

		// On met des chiffres de 0 à size-1.
		for(int i = 0; i < size; i++)
			paquet.add(i);

		// On melange le paquet.
		melangePaquet();
	}

	/*
	 * Méthode permettant de mélanger le paquet.
	 */
	public void melangePaquet(){

		// On crée un paquet temporaire.
		ArrayList<Integer> temp = new ArrayList<>(paquet.size());

		// Variable pour sauvegarder la position de la carte.
		int tirage;

		// On vide le paquet.
		while (!paquet.isEmpty()){

			// On prend une carte aléatoire.
			tirage = random.nextInt(paquet.size());

			// On ajout cette carte dans notre paquet temporaire.
			temp.add(paquet.get(tirage));

			// On enleve la carte du paquet.
			paquet.remove(tirage);
		}
		// On ajoute le paquet temporaire dans le paquet vide.
		paquet.addAll(temp);
	}

	/*
	 * Méthode permettant de tirer la première carte du paquet.
	 * @return la première carte.
	 */
	public int premiereCarte(){
		// Si le paquet est vide, on remet la defausse.
		if(paquet.isEmpty()) melangePaquetDefausse();

		// On prend la première carte.
		int carte = paquet.get(paquet.size()-1);

		// On l'enleve du paquet.
		paquet.remove(paquet.size()-1);

		// On l'a pose sur la defausse.
		poseDefausse(carte);

		// On return la carte.
		return carte;
	}

	/*
	 * Méthode permettant de poser une carte sur la defausse.
	 * @param carte : La carte à poser sur la defausse.
	 */
	public void poseDefausse(int carte){
		paquetDefausse.add(carte);
	}

	/*
	 * Méthode permettant de mélanger le paquet defausse (même commentaires que melangePaquet()).
	 */
	public void melangePaquetDefausse(){
		ArrayList<Integer> temp = new ArrayList<>(paquetDefausse.size());
		int tirage;
		while (!paquetDefausse.isEmpty()){
			tirage = random.nextInt(paquetDefausse.size());
			temp.add(paquetDefausse.get(tirage));
			paquetDefausse.remove(tirage);
		}
		// On ajoute le paquet temporaire sur le paquet (et donc le paquet defausse devient vide).
		paquet.addAll(temp);
	}
}