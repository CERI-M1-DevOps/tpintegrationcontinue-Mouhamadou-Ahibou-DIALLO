package liste;

/*
* Classe ListeSimple
* ListeSimple est une liste simplement chaînée
*
* @author Firstname Lastname
* */
public class ListeSimple {
    private long size;
    Noeud tete;

    /*
    * Retourne la taille de la liste
    * */
    public long getSize() {
        return size;
    }

    /*
    * @param element
    * Ajoute un élément à la tête de la liste
    * */
    public void ajout(int element) {
        tete = new Noeud(element, tete);
        size++;
    }

    /*
    * @param element
    * @param nouvelleValeur
    * Modifie la valeur d'un élément de la liste si celui-ci est présent par nouvelleValeur
     **/
    public void modifiePremier(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null && courant.getElement() != element)
            courant = courant.getSuivant();
        if (courant != null)
            courant.setElement(nouvelleValeur);
    }

    /*
    * @param element
    * @param nouvelleValeur
    * Modifie la valeur de tous les éléments de la liste si celui-ci est présent par nouvelleValeur
     **/
    public void modifieTous(Object element, Object nouvelleValeur) {
        Noeud courant = tete;
        while (courant != null) {
            if (courant.getElement() == element)
                courant.setElement(nouvelleValeur);
            courant = courant.getSuivant();
        }
    }

    /*
    * Retourne la liste sous forme de chaîne de caractères
     **/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ListeSimple(");
        Noeud n = tete;
        while (n != null) {
            sb.append(n);
            n = n.getSuivant();
            if (n != null)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    /*
    * @param element
    * Supprime le premier élément de la liste si celui-ci est présent par element
     **/
    public void supprimePremier(Object element) {
        if (tete != null) {
            if (tete.getElement() == element) {
                tete = tete.getSuivant();
                size--;
                return;
            }
            Noeud precedent = tete;
            Noeud courant = tete.getSuivant();
            while (courant != null && courant.getElement() != element) {
                precedent = precedent.getSuivant();
                courant = courant.getSuivant();
            }
            if (courant != null) {
                precedent.setSuivant(courant.getSuivant());
                size--;
            }
        }
    }

    /*
    * @param element
    * Supprime tous les éléments de la liste si celui-ci est présent par element
     **/
    public void supprimeTous(int element) {
       tete = supprimeTousRecurs(element, tete);
    }

    /*
    * @param element
    * @param tete
    * Supprime des éléments de la liste en commençant par la tête tout en vérifiant si l'élément est présent
     **/
    public Noeud supprimeTousRecurs(Object element, Noeud tete) {
        if (tete != null) {
            Noeud suiteListe = supprimeTousRecurs(element, tete.getSuivant());
            if (tete.getElement() == element) {
                size--;
                return suiteListe;
            } else {
                tete.setSuivant(suiteListe);
                return tete;
            }
        } else return null;
    }

    /*
    * Retourne l'avant-dernier élément de la liste
     **/
    public Noeud getAvantDernier() {
        if (tete == null || tete.getSuivant() == null)
            return null;
        else {
            Noeud courant = tete;
            Noeud suivant = courant.getSuivant();
            while (suivant.getSuivant() != null) {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
            return courant;
        }
    }

    /*
    * Inverse la liste
     **/
    public void inverser() {
        Noeud precedent = null;
        Noeud courant = tete;
        while (courant != null) {
            Noeud next = courant.getSuivant();
            courant.setSuivant(precedent);
            precedent = courant;
            courant = next;
        }
        tete = precedent;
    }

    /*
    * @param r
    * Retourne le Noeud précédent le Noeud r
     **/
    public Noeud getPrecedent(Noeud r) {
    // la liste n'est pas vide puisqu'on transmet un Node de la liste et le Node existe obligatoirement
        Noeud precedent = tete;
        Noeud courant = precedent.getSuivant();
        while (courant != r) {
            precedent = courant;
            courant = courant.getSuivant();
        }
        return precedent;
    }

    /*
    * @param r1
    * @param r2
    * Echange les Noeuds r1 et r2
     **/
    public void echanger(Noeud r1, Noeud r2) {
        if (r1 == r2)
            return;
        Noeud precedentR1;
        Noeud precedentR2;
        if (r1 != tete && r2 != tete) {
            precedentR1 = getPrecedent(r1);
            precedentR2 = getPrecedent(r2);
            precedentR1.setSuivant(r2);
            precedentR2.setSuivant(r1);
        } else if (r1 == tete) {
            precedentR2 = getPrecedent(r2);
            precedentR2.setSuivant(tete);
            tete = r2;
        }
        else {
            precedentR1 = getPrecedent(r1);
            precedentR1.setSuivant(tete);
            tete = r1;
        }
        Noeud temp = r2.getSuivant();
        r2.setSuivant(r1.getSuivant());
        r1.setSuivant(temp);
    }


}