/**
 * 
 */
package test;

import org.junit.Test;

import main.classes.FauteuilRoulant;
import main.classes.Magasin;

/**
 * @author visiteur-info16
 *
 */

public class TestMagasin {

	@Test
	public void FauteuilleRoullant() {
		Magasin magasin= new Magasin();
		FauteuilRoulant f= new FauteuilRoulant("11111", "faut", "bmw", 1000, 2, 1, 10);
		magasin.ajouterArticle(f);


		//Assert.assertEquals(30, magasin.supprimerArticle());
	}

}
