package dti.g55.wordle

/**
 * Un mot mystère de type Wordle
 *
 * @param motCherché doit comporter exactement 5 lettres dans l'interval A-Z
 *
 * @throws IllegalArgumentException si le mot cherché ne comporte pas exactement 5 caractères
 */
class Wordle( motCherché : String ) {

	// Longueur du mot cherché et des essais
	val LONGUEUR_MOT = 5

	// Les états possibles pour une lettre

	// La lettre n'a pas encore été essayée
	val ÉTAT_INCONNUE = 0
	// La lettre n'est pas dans le mot
	val ÉTAT_ABSENTE = 1
	// La lettre dans le mot, sa position est connue
	val ÉTAT_CORRECTE = 2
	// La lettre dans le mot, sa position est inconnue
	val ÉTAT_PRÉSENTE = 3

	// Le mot cherché
	var motCherché : String = ""
		private set

	companion object {
		var validateur = { lettres : Array<Int> -> lettres.count{ it == 2 } == 5 }
	}

	// L'état de l'ensemble des lettres possibles
	private val lettres = Array<Int>( 26 ){ 0 }
	
	/**
	 * Retourne l'état des 26 lettres, représentées chacune par un caractère
	 *
	 * L'astérisque (*) représente une lettre dont l'état n'est pas encore connue
	 * Un souligné (_) représente une lettre absente du mot
	 * Une minuscule représente une lettre présente dans le mot dont la position est inconnue
	 * Une majuscule représente une lettre présente dans le mot dont la position est connue
	 *
	 * @return (String) L'état des 26 lettres sous forme de chaîne
	 *
	 * @throws IllegalStateException si une lettre est dans un état illégal
	 */
	fun obtenirLettres(): String {
		return ""
	}

	/**
	 * Permet d'effectuer un essai
	 *
	 * @param essai (String) Le nouveau mot essayé
	 *
	 * @return (String) Le résultat de l'essai sous forme d'une chaîne de 5 caractères
	 *         selon les mêmes critères que pour obtenirLettres
	 *
	 * @throws IllegalArgumentException si le mot essayé ne comporte pas exactement 5 caractères
	 */
	fun essayer(essai: String): String {
		return ""
	}

	/**
	 * Retourne Vrai si le mot a été trouvé
	 *
	 * @return (Boolean) Vrai si et seulement si toutes les lettres du mot cherché ont été trouvées
	 */
	fun estRéussi() : Boolean {
		return validateur( lettres )
	}

}
