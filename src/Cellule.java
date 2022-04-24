public class Cellule {
	private boolean heliport;
	private int artefact; //0 rien, 1, 2, 3, 4 unique artefact.
	private int level = 2; // 2 normal, 1 inondé, 0 sub.
	private int posx, posy;

	/*
	 * Constructeur.
	 * @param isHeliport : défini si la case est un héliport.
	 * @param artefact : défini l'artefact qui est dans la case.
	 * @param posx et posy : la position de la case dans la grille.
	 */
	public Cellule(boolean isHeliport, int artefact, int posx, int posy){
		this.heliport = isHeliport;
		this.artefact = artefact;
		this.posx = posx;
		this.posy = posy;
		return;
	}

	/*
	 * On monte le niveau de la case (on baisse le niveau de l'eau).
	 * @return 0 si il y a eu un effet, 1 sinon.
	 */
	public int upLevel(){
		if(level >= 2) return 1;
		level++;
		return 0;
	}

	/*
	 * On descend le niveau de la case (on monte le niveau de l'eau).
	 * @return 0 si il y a eu un effet, 1 sinon.
	 */
	public int downLevel(){
		if(level <= 0) return 1;
		level--;
		return 0;
	}

	/*
	 * On retire l'artefact de la case.
	 */
	public void removeArtefact(){
		artefact = 0;
		return;
	}

	// Accesseurs.
	public int getPosx(){
		return posx;
	}

	public int getPosy(){
		return posy;
	}

	public int getArtefact(){
		return artefact;
	}

	public int getLevel(){
		return level;
	}

	public boolean isHeliport() {
		return heliport;
	}

}