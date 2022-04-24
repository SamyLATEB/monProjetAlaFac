import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Vue extends JPanel{

	// Classe qui représente une figure (équivalent des struct en C).
	private class Figure{
		protected int x, y;
		protected String texte;
		protected Color color;
		public Figure(int x, int y, Color color){
			this.x = x;
			this.y = y;
			this.texte = "";
			this.color = color;
		}
	}

	private Figure[][] cases;
	private Figure[] pions;

	private Modele grille;
	private Joueur[] joueurs;

	private boolean afficheAide;

	private Joueur joueurActuel;

	// Differantes couleurs pour la grille.
	private Color[] CNORMAL = {new Color(10,255,10,50), Color.gray, Color.black};
	private Color[] CHELI = {new Color(250, 0, 0), new Color(77, 0, 0), Color.black};
	private Color[] CAIR = {new Color(0, 150, 255), new Color(0, 66, 84), Color.black};
	private Color[] CEAU = {new Color(79, 79, 255), new Color(0, 0, 128), Color.black};
	private Color[] CTERRE = {new Color(44, 231, 0), new Color(23, 95, 0), Color.black};
	private Color[] CFEU = {new Color(255, 114, 0), new Color(99, 49, 0), Color.black};
	private Color[] CJOUEUR = {Color.red, Color.blue, Color.cyan, Color.green, Color.magenta,
							   Color.orange, Color.darkGray, Color.pink, Color.yellow};

	/*
	 * Constructeur.
	 * @param grille : la grille du jeu.
	 * @param j : la liste des joueurs.
	 */
	public Vue(Modele grille, Joueur[] j){
		this.grille = grille;
		cases = new Figure[this.grille.getTaillex()][this.grille.getTailley()];
		joueurs = j;
		pions = new Figure[j.length];

		// Permet d'afficher l'aide en premier.
		afficheAide = true;
			
		return;
		//f.getContentPane().add(new JPanelWithBackground("sample.jpeg"));
	}

	/*
	 * Méthode permettant d'initialiser la fenetre. On rempli le tableau de Figure cases
	 * avec des Figure ayant les position des Case qu'elles vont représenter et de la
	 * couleur case normale puis va remplir le tableau des Figure pions avec la
	 * position des joueurs et une couleur de CJOUEUR. Enfin, elle va créer la
	 * fenètre et appeler update() pour colorer les cases.
	 */
	public void init(){
		// On donne la position et la couleur blanche à chaque cases.
		for(int i = 0; i < grille.getTaillex(); i++){
			for(int j = 0; j < grille.getTailley(); j++)
				cases[i][j] =  new Figure(i, j, CNORMAL[0]);
		}

		// On donne à chaque pion la position de son joueur et une couleur.
		for(int i = 0; i < pions.length; i++)
			pions[i] = new Figure(joueurs[i].getPosx(), joueurs[i].getPosy(), CJOUEUR[i]);

		// On crée la fenètre avec son titre et on met à jour les couleurs des cases.
		creerFenetre2("Boitier des commandes");
		update();
		creerFenetre("Projet POGL : Île Interdite");
		nomCases(false);

		return;
	}

	/*
	 * Méthode permettant d'afficher les textes sur les cases.
	 * @param posCase : true si on veux les positions des cases, false si on veux les noms des cases spéciales.
	 */
	public void nomCases(boolean posCase){
		// Si on veux que chaque cases affichent sa position.
		if(posCase){
			for(int i = 0; i < cases.length; i++){
				for(int j = 0; j < cases[i].length; j++)
					cases[i][j].texte = Integer.toString(j * grille.getTaillex() + i);
			}
		}
		// Sinon on affiche les noms des cases spéciales.
		else{
			// Si un 0 est trouvé sur la première case, c'est que les positions ont été affichées donc on les efface.
			if(cases[0][0].texte.equals("0")) {
				for (int i = 0; i < cases.length; i++) {
					for (int j = 0; j < cases[i].length; j++)
						cases[i][j].texte = "";
				}
			}
			// On donne des noms aux cases.
			cases[grille.getCaseSpe(0).getPosx()][grille.getCaseSpe(0).getPosy()].texte = "Héliport";
			cases[grille.getCaseSpe(1).getPosx()][grille.getCaseSpe(1).getPosy()].texte = "Air";
			cases[grille.getCaseSpe(2).getPosx()][grille.getCaseSpe(2).getPosy()].texte = "Eau";
			cases[grille.getCaseSpe(3).getPosx()][grille.getCaseSpe(3).getPosy()].texte = "Terre";
			cases[grille.getCaseSpe(4).getPosx()][grille.getCaseSpe(4).getPosy()].texte = "Feu";
		}
	}

	/*
	 * Methode permettant de récuperer les coordonnées d'une fenetre 
	 */
/*	private void creerFenetre(String name) {
	}*/
	/*
	 * Méthode permettant de créer une fenètre avec comme seul élément cette classe.
	 * @param name : Le nom de la fenètre.
	 */
	private void creerFenetre(String name) {
		// On crée une JFrame avec le nom de la fenètre.
		JFrame fenetre = new JFrame(name);

		// On donne l'instruction de fermer la fenètre si on clique sur la croix.
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On ajoute JPanel dans la JFrame.
		fenetre.getContentPane().add(this);

		// On donne une dimension initiale à la fenètre.
		fenetre.setPreferredSize(new Dimension(600, 400));

		// On place le JPanel dans la JFrame.
		fenetre.pack();

		// On rend la fenètre visible.
		fenetre.setVisible(true);
		JPanel Information = new JPanel();
		Font Ecrit = new Font("Tahoma",Font.CENTER_BASELINE,18);
		fenetre.add(Information);		
		//JLabel Couver = new JLabel(icone) ;
		//fenetre.add(Couver,BorderLayout.NORTH);
		return;
	}

	private void creerFenetre2(String name) {
		// On crée une JFrame avec le nom de la fenètre.
		JFrame fenetre = new JFrame(name);

		// On donne l'instruction de fermer la fenètre si on clique sur la croix.
		//fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// On ajoute JPanel dans la JFrame.
		fenetre.getContentPane().add(this);

		// On donne une dimension initiale à la fenètre.
		fenetre.setPreferredSize(new Dimension(600, 200));

		// On place le JPanel dans la JFrame. 
		fenetre.pack();

		// On rend la fenètre visible.
		fenetre.setVisible(true);
		
		
		
		JPanel Information = new JPanel();
		Font Ecrit = new Font("Tahoma",Font.CENTER_BASELINE,18);
		fenetre.add(Information);		
		//JLabel Couver = new JLabel(icone) ;
		//fenetre.add(Couver,BorderLayout.NORTH);
		return;
	}
	/*
	 * Méthode permettant de mettre à jour notre JPanel en redessinnant les differents éléement
	 * à dessiner.
	 * @param g : contient tous les éléments dessinés.
	 */
	public void paintComponent(Graphics g){
		// Permet d'effacer l'ancien dessin.
		super.paintComponent(g);

		// Si on veux afficher l'aide, on appelle drawNotice().
		if(afficheAide)
			drawNotice(g);

		// Sinon on dessine le plateau de jeu.
		else {
			// On crée un tableau avec deux places, la 1ere pour la marge Haut Bas et la 2eme pour la marge Gauche Droite.
			int[] marge = new int[2];

			// On calcul les marges permettant de centrer la grille quelque soit la taille de la fenètre,
			// et renvoi la taille des cases calculé.
			int tailleCase = calculMarge(marge, getHeight() / 10);

			// On crée la grille selon les points Haut Gauche et Bas Droite.
			drawGrille(g, marge[1], marge[0], getWidth() - marge[1], getHeight() - marge[0]);

			// On dessine les cases de couleurs en prenant en compte la taille des cases et les marges.
			drawCases(g, tailleCase, marge[0], marge[1]);

			// On dessine les pions des joueurs.
			drawJoueurs(g, tailleCase, marge[0], marge[1]);

			// On dessine les deux lignes d'infos.
			drawLigneInfo(g, tailleCase);
		}
		return;
	}

	/*
	 * Méthode permettant de calculer les marges entre la grille et les bords de la fenètre selon la
	 * taille de la fenètre.
	 * @param tab : permet de renvoyer la taille des quatre marges HautBas et GaucheDroite.
	 * @param margeMini : la marge minimale à appliquer.
	 * @return la taille des cases.
	 */
	private int calculMarge(int[] tab, int margeMini){

		// Calcul permettant d'inclure la taille des cases en plus de la hauteur (dans le cas ou la grille représenterai un rectangle).
		int tailleCase = (grille.getTaillex() - grille.getTailley()) * (getHeight() - margeMini*2) / grille.getTailley();

		// Si on a une fenètre "en mode paysage" (en prenant en compte le "mode" de la grille).
		if(getWidth() > getHeight() + tailleCase){

			// On prend la hauteur de la fenètre, on enleve les deux marges donc on aura la hauteur que doit faire
			// la grille. Après, suffit de diviser par le nombre de lignes pour avoir la taille d'une case (que
			// l'on veux carré).
			tailleCase = (getHeight() - margeMini*2) / grille.getTailley();

			// Les marges Haute et Basse prennent la marge minimal
			tab[0] = margeMini;

			// On prend la largeur, on enleve la largeur de la grille donc il nous reste les deux marges,
			// on divise par deux pour avoir la marge GaucheDroite.
			tab[1] = (getWidth() - (tailleCase * grille.getTaillex())) / 2;
		}

		// Si on a une fenètre "en mode portrait".
		else{
			tailleCase = (getWidth() - margeMini*2) / grille.getTaillex();
			tab[0] = (getHeight() - (tailleCase * grille.getTailley())) / 2;
			tab[1] = margeMini;
		}
		return tailleCase;
	}

	/*
	 * Méthode permettant de dessiner la grille selon le point Haut Gauche et le point Bas Droite
	 * et selon le nombre de lignes et de colonnes.
	 * @param g : contient tous les éléments dessinées.
	 * @param x0 y0 : le point Haut Gauche.
	 * @param x1 y : le point Bas Droite.
	 */
	private void drawGrille(Graphics g, int x0, int y0, int x1, int y1){
		// Un peu de trigo pour calculer les positions des lignes à dessiner.
		for(int i = 0; i < grille.getTaillex()+1; i++) {
			int x0temp = x0 + ((x1 - x0) / (grille.getTaillex())) * i;
			int y0temp = y0;
			int x1temp = x0 + ((x1 - x0) / (grille.getTaillex())) * i;

			// Au lieu de y1, on calcul les positions des derniers points, pour éviter de faire dépasser
			// les lignes.
			int y1temp = y0 + ((y1 - y0) / (grille.getTailley())) * (grille.getTailley());
			g.drawLine(x0temp, y0temp, x1temp, y1temp);
		}
		for(int i = 0; i < grille.getTailley()+1; i++) {
			int x0temp = x0;
			int y0temp = y0 + ((y1 - y0) / (grille.getTailley())) * i;
			int x1temp = x0 + ((x1 - x0) / (grille.getTaillex())) * (grille.getTaillex());
			int y1temp = y0 + ((y1 - y0) / (grille.getTailley())) * i;
			g.drawLine(x0temp, y0temp, x1temp, y1temp);
		}
		    //g.drawImage(icone, 0, 0,null);
		return;
	}

	/*
	 * Méthode permettant de dessiner les cases dans la grille (pour mettre des couleurs).
	 * @param g : contient tous les éléments dessinées.
	 * @param tailleCase : taille des cases à dessiner.
	 * @param margeHB : la marge du haut et du bas.
	 * @param margeHB : la marge de droite et de gauche.
	 */
	private void drawCases(Graphics g, int tailleCase, int margeHB, int margeGD){
		// Police pour l'affichage du texte dans les cases.
		Font fontCase = new Font("Arial", Font.BOLD, tailleCase/5);

		int tailleTexte;

		// Pour toutes les cases, on defini les couleurs et les positions.
		for(Figure[] tf : cases){
			for(Figure f : tf) {
				g.setColor(f.color);
				g.fillRect(f.x * tailleCase + margeGD + 1, f.y * tailleCase + margeHB + 1, tailleCase - 1, tailleCase - 1);

				// Si la case doit avoir un texte.
				if(f.texte != "") {

					// On met le texte en noir.
					g.setColor(Color.black);

					// On defini la police.
					g.setFont(fontCase);

					// On calcul la taille du texte (pour le mettre au milieu).
					tailleTexte = g.getFontMetrics().stringWidth(f.texte);

					// On affiche le texte devant la case.
					g.drawString(f.texte, (f.x * tailleCase + margeGD + 1) + tailleCase / 2 - tailleTexte / 2, (f.y * tailleCase + margeHB + 1) + tailleCase / 2 + tailleCase/10);
				}
			}
		}
		return;
	}

	/*
	 * Méthode permettant de dessiner les pions des joueurs.
	 * @param g : contient tous les éléments dessinées.
	 * @param tailleCase : taille des cases à dessiner.
	 * @param margeHB : la marge du haut et du bas.
	 * @param margeHB : la marge de droite et de gauche.
	 */
	private void drawJoueurs(Graphics g, int tailleCase, int margeHB, int margeGD){
		// Liste contenant toutes les pos des pions à analyser.
		ArrayList<Integer> pionsDis = new ArrayList();

		// Liste contenant les regroupements des pions dans les cases (les pions dans les mêmes cases).
		ArrayList<ArrayList> caseJ = new ArrayList<>();

		// On rempli le pionsdis avec les pos des pions (de 0 a size).
		for(int i = 0; i < pions.length; i++)
			pionsDis.add(i);

		// On vide progressivement la liste.
		while(!pionsDis.isEmpty()){

			// On crée une liste dans la liste.
			caseJ.add(new ArrayList<Figure>());

			// A la fin de cette liste, on met le premier pion de pionsdis, qui sera étudié puis supprimé.
			caseJ.get(caseJ.size()-1).add(pions[pionsDis.get(0)]);

			// i=1 pour eviter de réetudier le premier pion.
			for(int i = 1; i < pionsDis.size(); i++){

				// On compare les deux pions.
				if(pions[pionsDis.get(0)].x == pions[pionsDis.get(i)].x && pions[pionsDis.get(0)].y == pions[pionsDis.get(i)].y){

					// Si ils sont sur la même case, on l'ajout avec le premier pion (donc même liste = même case).
					caseJ.get(caseJ.size()-1).add(pions[pionsDis.get(i)]);

					// Puis on le supprime.
					pionsDis.remove(i);

					// On enleve un element de la liste en cours donc i--;
					i--;
				}
			}
			// On retire le premier pion car on l'a automatiquement placer dans caseJ.
			pionsDis.remove(0);
		}

		// On defini les positions les pions sur une case selon le nombre de pions sur la case
		// (comme sur un dé). Ici, les chiffres représente le paré numérique d'un clavier (
		// comme si le pavé était une case et les touches de celui-ci les differantes positions).
		int[] posPions;
		for(ArrayList<Figure> l : caseJ){
			if(l.size() == 1) 	   posPions = new int[]{5};
			else if(l.size() == 2) posPions = new int[]{7, 3};
			else if(l.size() == 3) posPions = new int[]{7, 5, 3};
			else if(l.size() == 4) posPions = new int[]{7, 1, 9, 3};
			else if(l.size() == 5) posPions = new int[]{7, 1, 9, 3, 5};
			else if(l.size() == 6) posPions = new int[]{7, 8, 9, 1, 2, 3};
			else if(l.size() == 7) posPions = new int[]{7, 8, 9, 1, 2, 3, 5};
			else if(l.size() == 8) posPions = new int[]{7, 8, 9, 1, 2, 3, 4, 6};
			else 				   posPions = new int[]{7, 8, 9, 1, 2, 3, 4, 5, 6};

			// Pour tous les pions, on les place comme défini.
			for(int i = 0; i < l.size(); i++)
				placePions(g, posPions[i], l.get(i), tailleCase, margeHB, margeGD);
		}
		return;
	}

	/*
	 * Méthode permettant de placer un pion sur une position d'une case.
	 * @param g : contient tous les éléments dessinées.
	 * @param pos : la position sur laquelle mettre le pion (le chiffre correspond au pavé num).
	 * @param pion : le pion à dessiner.
	 * @param tailleCase : taille des cases à dessiner.
	 * @param margeHB : la marge du haut et du bas.
	 * @param margeHB : la marge de droite et de gauche.
	 */
	private void placePions(Graphics g, int pos, Figure pion, int tailleCase, int margeHB, int margeGD){
		// Selon la position qu'on veux, on donne un x et un y.
		int x = 0, y = 0;
		if(pos == 7 || pos == 8 || pos == 9) y = pion.y * tailleCase + margeHB + tailleCase * 3 / 16 - (tailleCase / 6);
		if(pos == 4 || pos == 5 || pos == 6) y = pion.y * tailleCase + margeHB + tailleCase / 2 - (tailleCase / 6);
		if(pos == 1 || pos == 2 || pos == 3) y = pion.y * tailleCase + margeHB + tailleCase * 13 / 16 - (tailleCase / 6);

		if(pos == 7 || pos == 4 || pos == 1) x = pion.x * tailleCase + margeGD + tailleCase * 3 / 16 - (tailleCase / 6);
		if(pos == 8 || pos == 5 || pos == 2) x = pion.x * tailleCase + margeGD + tailleCase / 2 - (tailleCase / 6);
		if(pos == 9 || pos == 6 || pos == 3) x = pion.x * tailleCase + margeGD + tailleCase * 13 / 16 - (tailleCase / 6);

		// Et on place le pion à la position voulu (rond plein coloré et rond vide bord noir).
		g.setColor(pion.color);
		g.fillOval(x, y, tailleCase / 3, tailleCase / 3);
		g.setColor(Color.black);
		g.drawOval(x, y, tailleCase / 3, tailleCase / 3);

		return;
	}

	/*
	 * Méthode permettant de dessiner les instructions dans la fenètre.
	 * @param g : contient tous les éléments dessinées.
	 */
	private void drawNotice(Graphics g){

		// On place des carrés.
		g.setColor(CAIR[0]);
		// getWidth()/2 milieu de la fenètre, -25 moitié du carré(donc carré au milieu),
		// -75 moitié du carré + carré.
		g.fillRect(getWidth()/2-25-75, getHeight()/2-100, 50, 50);

		g.setColor(CEAU[0]);
		g.fillRect(getWidth()/2-25-25, getHeight()/2-100, 50, 50);

		g.setColor(CTERRE[0]);
		g.fillRect(getWidth()/2-25+25, getHeight()/2-100, 50, 50);

		g.setColor(CFEU[0]);
		g.fillRect(getWidth()/2-25+75, getHeight()/2-100, 50, 50);

		g.setColor(CNORMAL[0]);
		g.fillRect(getWidth()/2-25-50, getHeight()/2-50, 50, 50);

		g.setColor(CNORMAL[1]);
		g.fillRect(getWidth()/2-25+00, getHeight()/2-50, 50, 50);

		g.setColor(CNORMAL[2]);
		g.fillRect(getWidth()/2-25+50, getHeight()/2-50, 50, 50);

		// On met les bords noirs.
		g.setColor(Color.black);
		g.drawRect(getWidth()/2-25+25, getHeight()/2-100, 50, 50);
		g.drawRect(getWidth()/2-25-25, getHeight()/2-100, 50, 50);
		g.drawRect(getWidth()/2-25-75, getHeight()/2-100, 50, 50);
		g.drawRect(getWidth()/2-25+75, getHeight()/2-100, 50, 50);

		g.drawRect(getWidth()/2-25-50, getHeight()/2-50, 50, 50);
		g.drawRect(getWidth()/2-25-0, getHeight()/2-50, 50, 50);
		g.drawRect(getWidth()/2-25+50, getHeight()/2-50, 50, 50);

		// On met un titre.
		g.drawString("Projet POGL : Île Interdite",   getWidth()/2-65, getHeight()/2-120);

		// On met les légendes des cases dans ses cases.
		g.drawString("Air",   getWidth()/2-25-75+18, getHeight()/2-75);
		g.drawString("Eau",   getWidth()/2-25-25+15, getHeight()/2-75);
		g.drawString("Terre", getWidth()/2-25+25+12, getHeight()/2-75);
		g.drawString("Feu",   getWidth()/2-25+75+15, getHeight()/2-75);

		g.drawString("Normale",   getWidth()/2-25-50+1, getHeight()/2-25);
		g.drawString("Inondé",   getWidth()/2-25-00+6, getHeight()/2-25);
		g.setColor(Color.white);
		g.drawString("Sub", getWidth()/2-25+50+15, getHeight()/2-25);

		// Variable pour écrive les roles des joueurs (pour que le code soit plus lisible).
		String aEcrire;

		// On place les pions sous les cases, avec les noms des joueurs associés.
		for(int i = 0; i < joueurs.length; i++){
			g.setColor(CJOUEUR[i]);
			g.fillOval(getWidth()/2-50, getHeight()/2 + (i*30) + 10, 20, 20);
			g.setColor(Color.black);
			g.drawOval(getWidth()/2-50, getHeight()/2 + (i*30) + 10, 20, 20);

			aEcrire = ": " + joueurs[i].getName();

			// On cherche le role du joueur.
			if(joueurs[i].getRole() == 1) aEcrire += " - le Pilote";
			else if(joueurs[i].getRole() == 2) aEcrire += " - l'Ingénieur";
			else if(joueurs[i].getRole() == 3) aEcrire += " - l'Explorateur";
			else if(joueurs[i].getRole() == 4) aEcrire += " - le Navigateur";
			else if(joueurs[i].getRole() == 5) aEcrire += " - le Plongeur";
			else if(joueurs[i].getRole() == 6) aEcrire += " - le Messager";

			g.drawString(aEcrire, getWidth()/2-25, getHeight()/2 + (i*30) + 25);
		}

		// On met un joli rectangle autour.
		g.drawRect(getWidth()/2-150, getHeight()/2 - 150, 300, joueurs.length*30 + 160);

		return;
	}

	/*
	 * Accesseur.
	 * @param j : le joueur à mettre dans l'attribut joueurActuel.
	 */
	public void setJoueurActuel(Joueur j){
		joueurActuel = j;
		return;
	}

	/*
	 * Méthode permettant de créer les deux lignes, au dessus et en dessous.
	 * @param g : contient tous les éléments dessinées.
	 * @param tailleCase : taille des cases à dessiner.
	 */
	private void drawLigneInfo(Graphics g, int tailleCase){
		// Couleur des lignes.
		g.setColor(new Color(182, 182, 182));

		// Dessin des lignes.
		g.fillRect(0, 0, this.getWidth(), tailleCase / 2);
		g.fillRect(0, this.getHeight() - tailleCase / 2, this.getWidth(), tailleCase / 2);

		// On cherche la position du joueur dans la liste de joueur.
		int posJoueur;
		for(posJoueur = 0; posJoueur < joueurs.length; posJoueur++){
			if(joueurs[posJoueur] == joueurActuel)
				break;
		}

		// On crée le texte à afficher, la police et on calcul la taille du texte.
		String aEcrire = "C'est au tour de : " + joueurActuel.getName();

		if(joueurActuel.getRole() == 1) aEcrire += " - le Pilote";
		else if(joueurActuel.getRole() == 2) aEcrire += " - l'Ingénieur";
		else if(joueurActuel.getRole() == 3) aEcrire += " - l'Explorateur";
		else if(joueurActuel.getRole() == 4) aEcrire += " - le Navigateur";
		else if(joueurActuel.getRole() == 5) aEcrire += " - le Plongeur";
		else if(joueurActuel.getRole() == 6) aEcrire += " - le Messager";

		Font fontCase = new Font("Arial", Font.BOLD, tailleCase/5);
		int tailleTexte = g.getFontMetrics().stringWidth(aEcrire);

		// On dessine le texte.
		g.setColor(Color.black);
		g.setFont(fontCase);
		g.drawString(aEcrire, this.getWidth() / 2 - tailleTexte / 2, tailleCase / 3);

		// On dessine le pion du joueur à coté de son nom (this.getWidth() / 100 pour séparer le pion du texte).
		g.setColor(pions[posJoueur].color);
		g.fillOval(this.getWidth() / 2 + tailleTexte / 2 + this.getWidth() / 100, tailleCase / 9, tailleCase / 3, tailleCase / 3);
		g.setColor(Color.black);
		g.drawOval(this.getWidth() / 2 + tailleTexte / 2 + this.getWidth() / 100, tailleCase / 9, tailleCase / 3, tailleCase / 3);


		// On change le texte à écrire et on recalcul sa taille.
		aEcrire = "  Clefs :";
		tailleTexte = g.getFontMetrics().stringWidth(aEcrire);

		// On écrit le texte.
		g.drawString(aEcrire, 0, this.getHeight() - tailleCase / 6);

		// On met un fond blanc si il y a moins de 4 clefs (parce que sinon, inutile d'en mettre un).
		g.setColor(Color.white);
		if(joueurActuel.getClef(0) < 4)
			g.fillRect(tailleTexte + this.getWidth() / 100, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		if(joueurActuel.getClef(1) < 4)
			g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		if(joueurActuel.getClef(2) < 4)
			g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		if(joueurActuel.getClef(3) < 4)
			g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase + tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);


		// On dessine 1 à 3 petits carrés dans le carré blanc, selon le nombre de clef.
		g.setColor(CAIR[0]);

		// Si le joueur à plus de 3 clefs on fait un grand carré au lieu de 4 petits carrés.
		if(joueurActuel.getClef(0) >= 4)
			g.fillRect(tailleTexte + this.getWidth() / 100, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		// Un else if pour éviter les 4 autres test inutile si le joueur n'a pas de clef.
		else if(joueurActuel.getClef(0) != 0){

			// Si le joueur a plus d'une clef.
			if (joueurActuel.getClef(0) >= 1)
				g.fillRect(tailleTexte + this.getWidth() / 100, this.getHeight() - tailleCase / 2, tailleCase / 4, tailleCase / 4);

			// Plus de deux.
			if (joueurActuel.getClef(0) >= 2)
				g.fillRect(tailleTexte + this.getWidth() / 100, this.getHeight() - tailleCase / 2 + tailleCase / 4, tailleCase / 4, tailleCase / 4);


			if (joueurActuel.getClef(0) >= 3)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase / 4, this.getHeight() - tailleCase / 2, tailleCase / 4, tailleCase / 4);
		}


		g.setColor(CEAU[0]);
		if(joueurActuel.getClef(1) >= 4)
			g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		else if(joueurActuel.getClef(1) != 0){
			if (joueurActuel.getClef(1) >= 1)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 4, tailleCase / 4);
			if (joueurActuel.getClef(1) >= 2)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase / 2, this.getHeight() - tailleCase / 2 + tailleCase / 4, tailleCase / 4, tailleCase / 4);
			if (joueurActuel.getClef(1) >= 3)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase / 2 + tailleCase / 4, this.getHeight() - tailleCase / 2, tailleCase / 4, tailleCase / 4);
		}

		g.setColor(CTERRE[0]);
		if(joueurActuel.getClef(2) >= 4)
			g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		else if(joueurActuel.getClef(2) != 0){
			if (joueurActuel.getClef(2) >= 1)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase, this.getHeight() - tailleCase / 2, tailleCase / 4, tailleCase / 4);
			if (joueurActuel.getClef(2) >= 2)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase, this.getHeight() - tailleCase / 2 + tailleCase / 4, tailleCase / 4, tailleCase / 4);
			if (joueurActuel.getClef(2) >= 3)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase + tailleCase / 4, this.getHeight() - tailleCase / 2, tailleCase / 4, tailleCase / 4);
		}


		g.setColor(CFEU[0]);
		if(joueurActuel.getClef(3) >= 4)
			g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase + tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		else if(joueurActuel.getClef(3) != 0){
			if (joueurActuel.getClef(3) >= 1)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase + tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 4, tailleCase / 4);
			if (joueurActuel.getClef(3) >= 2)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase + tailleCase / 2, this.getHeight() - tailleCase / 2 + tailleCase / 4, tailleCase / 4, tailleCase / 4);
			if (joueurActuel.getClef(3) >= 3)
				g.fillRect(tailleTexte + this.getWidth() / 100 + tailleCase + tailleCase / 2 + tailleCase / 4, this.getHeight() - tailleCase / 2, tailleCase / 4, tailleCase / 4);
		}

		// On entoure les cases de contours noirs.
		g.setColor(Color.black);
		g.drawRect(tailleTexte + this.getWidth() / 100, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		g.drawRect(tailleTexte + this.getWidth() / 100 + tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		g.drawRect(tailleTexte + this.getWidth() / 100 + tailleCase, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		g.drawRect(tailleTexte + this.getWidth() / 100 + tailleCase + tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);


		// On change le texte à écrire et on recalcul sa taille.
		aEcrire = ": Artefacts  ";
		tailleTexte = g.getFontMetrics().stringWidth(aEcrire);

		// On écrit le texte.
		g.drawString(aEcrire, this.getWidth() - tailleTexte, this.getHeight() - tailleCase / 6);

		// On dessine des cases à coté du texte et on met la couleur blanche si le joueur n'a pas la clé, la couleur de l'artefact sinon.
		g.setColor(joueurActuel.verifArtefact(0) ? CAIR[0] : Color.white);
		g.fillRect(this.getWidth() - tailleTexte - tailleCase/2 - this.getWidth() / 100 - tailleCase - tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		g.setColor(joueurActuel.verifArtefact(1) ? CEAU[0] : Color.white);
		g.fillRect(this.getWidth() - tailleTexte - tailleCase/2 - this.getWidth() / 100 - tailleCase, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		g.setColor(joueurActuel.verifArtefact(2) ? CTERRE[0] : Color.white);
		g.fillRect(this.getWidth() - tailleTexte - tailleCase/2 - this.getWidth() / 100 - tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		g.setColor(joueurActuel.verifArtefact(3) ? CFEU[0] : Color.white);
		g.fillRect(this.getWidth() - tailleTexte - tailleCase/2 - this.getWidth() / 100, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		// On entoure les cases de contours noirs.
		g.setColor(Color.black);
		g.drawRect(this.getWidth() - tailleTexte - tailleCase/2 - this.getWidth() / 100 - tailleCase - tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		g.drawRect(this.getWidth() - tailleTexte - tailleCase/2 - this.getWidth() / 100 - tailleCase, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		g.drawRect(this.getWidth() - tailleTexte - tailleCase/2 - this.getWidth() / 100 - tailleCase / 2, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);
		g.drawRect(this.getWidth() - tailleTexte - tailleCase/2 - this.getWidth() / 100, this.getHeight() - tailleCase / 2, tailleCase / 2, tailleCase / 2);

		return;
	}

	/*
	 * Méthode permettant de mettre à jour les données les tableaux cases et joueurs pour redessiner après.
	 */
	public void update(){
		// Variable dans laquelle on mettera les cases à étudier.
		Cellule casse;

		// On parcours les cases.
		for(int i = 0; i < grille.getTaillex(); i++){
			for(int j = 0; j < grille.getTailley(); j++) {
				// On pointe la case i, j.
				casse = grille.getCase(i, j);

				// Si cette case est l'héliport, on met la couleur de l'héliport selon son niveau.
				if(casse.isHeliport())
					cases[i][j].color = casse.getLevel() == 2 ? CHELI[0] : casse.getLevel() == 1 ? CHELI[1] : CHELI[2];

				// Sinon si c'est un artefact, on met la couleur de l'artefact selon son niveau.
				else if(casse.getArtefact() != 0) {
					if (casse.getArtefact() == 1)
						cases[i][j].color = casse.getLevel() == 2 ? CAIR[0] : casse.getLevel() == 1 ? CAIR[1] : CAIR[2];
					else if (casse.getArtefact() == 2)
						cases[i][j].color = casse.getLevel() == 2 ? CEAU[0] : casse.getLevel() == 1 ? CEAU[1] : CEAU[2];
					else if (casse.getArtefact() == 3)
						cases[i][j].color = casse.getLevel() == 2 ? CTERRE[0] : casse.getLevel() == 1 ? CTERRE[1] : CTERRE[2];
					else if (casse.getArtefact() == 4)
						cases[i][j].color = casse.getLevel() == 2 ? CFEU[0] : casse.getLevel() == 1 ? CFEU[1] : CFEU[2];
				}

				// Sinon c'est une case normal donc on applique les couleurs normales.
				else
					cases[i][j].color = casse.getLevel() == 2 ? CNORMAL[0] : casse.getLevel() == 1 ? CNORMAL[1] : CNORMAL[2];
			}
		}

		// On met à jour les positions des joueurs.
		for(int i = 0; i < pions.length; i++){
			pions[i].x = joueurs[i].getPosx();
			pions[i].y = joueurs[i].getPosy();
		}

		// On actualise l'affichage avec les valeurs mise à jour.
		repaint();
		return;
	}

	/*
	 * Méthode permettant de choisir si on veux afficher les instructions ou non.
	 */
	public void afficherAide(){
		// Interrupteur.
		afficheAide = afficheAide ? false : true;
		return;
	}
}