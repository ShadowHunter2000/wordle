package dti.g55.wordle

import java.lang.StringBuilder

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
	var motCherché : String = motCherché.uppercase()
		private set ( value ) {
			field = value
		}

	init {
		val motCherchéEnMajuscules = motCherché.uppercase()
		if ( motCherchéEnMajuscules.length != LONGUEUR_MOT || !motCherchéEnMajuscules.all { it in 'A'..'Z' } ) {
			throw IllegalArgumentException( "Le mot cherché doit comporter exactement 5 lettres [A-Z]" )
		}
		this.motCherché = motCherchéEnMajuscules
	}

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
		val resultat = StringBuilder()
		for ( i in 'A'..'Z' ) {
			val index = i - 'A'
			val etat = lettres[index]
			val etatLettre = when ( etat ) {
				ÉTAT_INCONNUE -> '*'
				ÉTAT_ABSENTE -> '_'
				ÉTAT_CORRECTE -> i
				ÉTAT_PRÉSENTE -> i.lowercaseChar()
				else -> throw IllegalStateException( "L'état de la lettre est illégal" )
			}
			resultat.append( etatLettre )
		}
		return resultat.toString()
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
		if ( essai.length != LONGUEUR_MOT || !essai.all { it in 'A'..'Z' || it in 'a'..'z' } )
			throw IllegalArgumentException( "L'essai doit comporter exactement 5 lettres [A-Z]" )
		val message = StringBuilder()

		for ( i in essai.indices ) {
			val lettre = essai[i].uppercaseChar()
			if ( lettre == motCherché[i] ) {
				if ( lettres[lettre - 'A'] == ÉTAT_CORRECTE ) {
					message.append( lettre )
				} else {
					message.append( lettre )
					lettres[lettre - 'A'] = ÉTAT_CORRECTE
				}
			} else if ( motCherché.contains( lettre ) && lettres[lettre - 'A'] != ÉTAT_CORRECTE ) {
				if ( lettres[lettre - 'A'] != ÉTAT_CORRECTE ) {
					message.append( lettre.lowercaseChar() )
					lettres[lettre - 'A'] = ÉTAT_PRÉSENTE
				} else {
					message.append( '_' )
				}
			} else {
				if ( lettres[lettre - 'A'] != ÉTAT_CORRECTE ) {
					message.append( '_' )
					lettres[lettre - 'A'] = ÉTAT_ABSENTE
				} else {
					message.append( lettre )
				}
			}
		}
		return message.toString()
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
