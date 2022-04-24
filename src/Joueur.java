public class Joueur {
	private String nom;
	private int[] clefs; // Air 0, Eau 1, Terre 2, Feu 3
	private boolean[] artefact;
	private Cellule caseJoueur;
	private int role; // 0 aucun, 1 pilote, 2 ingé, 3 explorer, 4 navi, 5 plong, 6 message

	/*
	 * Constructeur.
	 * @param nom : le nom du joueur.
	 * @param caseJoueur : La case ou doit apparaitre le joueur.
	 */
	public Joueur(String nom, int role, Cellule caseJoueur){
		this.nom = nom;
		this.artefact = new boolean[4];
		this.clefs = new int[]{0, 0, 0, 0}; //{1, 1, 1, 1};
		this.caseJoueur = caseJoueur;
		this.role = role;
		return;
	}

	public Joueur(String nom, int role){
		this.nom = nom;
		this.artefact = new boolean[4];
		this.clefs = new int[]{0, 0, 0, 0}; //{1, 1, 1, 1};
		this.role = role;
		return;
	}

	public int addClef(int clef){
		if(clef > 3 || clef < 0) return 1;
		this.clefs[clef]++;
		return 0;
	}

	public int delClef(int clef){
		if(clef > 3 || clef < 0) return 1;
		if(clefs[clef] == 0) return 1;
		this.clefs[clef]--;
		return 0;
	}

	public int addArtefact(int artefact){
		if(artefact > 3 || artefact < 0) return 1;
		this.artefact[artefact] = true;
		return 0;
	}

	public void setCase(Cellule newCase){
		this.caseJoueur = newCase;
		return;
	}

	public int getPosx(){
		return caseJoueur.getPosx();
	}

	public int getPosy(){
		return caseJoueur.getPosy();
	}

	public Cellule getCase(){
		return caseJoueur;
	}

	public String getName(){
		return nom;
	}

	public int getClef(int num){
		return clefs[num];
	}

	/*public int setRole(int role){
		if(role > 6 || role < 0) return 1;
		this.role = role;
		return 0;
	}*/

	public int getRole() {
		return role;
	}

	public boolean verifClef(int clef){
		if(clef > 3 || clef < 0) return false;
		return clefs[clef] > 0 ? true : false;
	}

	public boolean verifArtefact(int artefact){
		if(artefact > 3 || artefact < 0) return false;
		return this.artefact[artefact];
	}
}