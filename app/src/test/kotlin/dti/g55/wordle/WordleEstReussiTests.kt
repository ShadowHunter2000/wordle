package dti.g55.wordle

import kotlin.test.Test
import kotlin.test.*;

class WordleEstReussiTests {

    @Test
    fun `Étant donné un mot avec toutes les lettres devinées correctement, lorsqu'on utilise la méthode estRéussi() pour valider le test, on obtient vrai`() {
        val cobaye = Wordle("CORDE")

        cobaye.essayer("CORDE")
        assertEquals( true, cobaye.estRéussi() )
    }

    @Test
    fun `Étant donné un mot avec des lettres devinées correctement mais à la mauvaise position, lorsqu'on utilise la méthode estRéussi() pour valider le test, on obtient faux`() {
        val cobaye = Wordle("CORDE")

        cobaye.essayer("CODER")
        assertEquals( false, cobaye.estRéussi() )
    }

    @Test
    fun `Étant donné un mot avec aucune lettre devinée, lorsqu'on utilise la méthode estRéussi() pour valider le test, on obtient faux`() {
        val cobaye = Wordle("CORDE")

        cobaye.essayer("ABCFG")
        assertEquals( false, cobaye.estRéussi() )
    }

    @Test
    fun `Étant donné un mot avec toutes les lettres correctes mais dans le désordre, lorsqu'on utilise la méthode estRéussi() pour valider le test, on obtient faux`() {
        val cobaye = Wordle("CORDE")

        cobaye.essayer("RODCE")
        assertEquals( false, cobaye.estRéussi() )
    }
}