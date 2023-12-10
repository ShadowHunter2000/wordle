package dti.g55.wordle

import kotlin.test.Test
import kotlin.test.*;

class WordleConstructeurTests {

	@Test
	fun `Étant donné le mot cherché initialisé avec des minuscules, lorsqu'on récupère la propriété, on obtient le mot en majuscules`(){
		val cobaye = Wordle("Corde")

		val résultat_obtenu = cobaye.motCherché

		assertEquals( "CORDE", résultat_obtenu )
	}

	@Test
	fun `Étant donné un mot de 3 lettres, lorsqu'on initialise un Wordle, on obtient une exception`(){
		try{
			Wordle("xyz")
			fail()
		}
		catch(e : IllegalArgumentException){
			assertEquals( "Le mot cherché doit comporter exactement 5 lettres [A-Z]", e.message )
		}
	}

	@Test
	fun `Étant donné un mot de 6 lettres, lorsqu'on initialise un Wordle, on obtient une exception`(){
		try{
			Wordle("abcdef")
			fail()
		}
		catch(e : IllegalArgumentException){
			assertEquals( "Le mot cherché doit comporter exactement 5 lettres [A-Z]", e.message )
		}
	}

}
