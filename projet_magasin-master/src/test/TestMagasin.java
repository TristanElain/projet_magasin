/**
 * 
 */
package test;

import main.FauteuilRoulant;
import main.Magasin;
import org.junit.Assert;
import org.junit.Test;

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
