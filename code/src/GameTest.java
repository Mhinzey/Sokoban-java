import java.io.BufferedReader;
import java.io.FileReader;

import junit.framework.TestCase;

import org.junit.Test;
/**
 *Classe qui génère les tests unités
 * @author Deryck Olivier, De Visch Justine
 *
 */

public class GameTest extends TestCase {
	String input,moves,output,expected;
	/**
	 * Méthode qui vérifie l'égalité entre deux fichiers
	 * @param expectedFileName	Un des fichiers à comparer.
	 * @param resultFileName	L'autre fichier à comparer.
	 * @return	True si les fichiers sont identiques, False dans le cas contraire.
	 */
	private static boolean equalFiles(String expectedFileName,
	        String resultFileName) {
	    boolean equal;
	    BufferedReader bExp;
	    BufferedReader bRes;
	    String expLine ;
	    String resLine ;

	    equal = false;
	    bExp = null ;
	    bRes = null ;

	    try {
	        bExp = new BufferedReader(new FileReader(expectedFileName));
	        bRes = new BufferedReader(new FileReader(resultFileName));

	        if ((bExp != null) && (bRes != null)) {
	            expLine = bExp.readLine() ;
	            resLine = bRes.readLine() ;

	            equal = ((expLine == null) && (resLine == null)) || ((expLine != null) && expLine.equals(resLine)) ;

	            while(equal && expLine != null)
	            {
	                expLine = bExp.readLine() ;
	                resLine = bRes.readLine() ; 
	                equal = expLine.equals(resLine) ;
	            }
	        }
	    } catch (Exception e) {

	    } finally {
	        try {
	            if (bExp != null) {
	                bExp.close();
	            }
	            if (bRes != null) {
	                bRes.close();
	            }
	        } catch (Exception e) {
	        }

	    }

	    return equal;

	}
	
	@Test //Caisse sur emplacement libre
	public void test1() {	
		input= "Level_1.xsb";
		moves= "test1.mov";
		output= "code/Jtest/tests/test1.xsb";
		expected="code/Jtest/expecteds/expected1.xsb";
	 	Game test=new Game(input, moves, output);	
	 	assertTrue(equalFiles(expected, output)) ;
	}
	@Test //Hero sur case libre
	public void test2() {	
		input= "Level_1.xsb";
		moves= "test2.mov";
		output= "code/Jtest/tests/test2.xsb";
		expected="code/Jtest/expecteds/expected2.xsb";
		Game test=new Game(input, moves, output);	
	 	assertTrue(equalFiles(expected, output)) ;

		}
	@Test//hero contre mur
	public void test3() {	
		input= "Level_1.xsb";
		moves= "test3.mov";
		output= "code/Jtest/tests/test3.xsb";
		expected="code/Jtest/expecteds/expected3.xsb";
		Game test=new Game(input, moves, output);	
	 	assertTrue(equalFiles(expected, output)) ;

		}
	@Test // caisse contre mur
	public void test4() {	
		input= "Level_1.xsb";
		moves= "test4.mov";
		output= "code/Jtest/tests/test4.xsb";
		expected="code/Jtest/expecteds/expected4.xsb";
		Game test=new Game(input, moves, output);	
	 	assertTrue(equalFiles(expected, output)) ;

		}
	@Test // caisse sur objectif
	public void test5() {	
		input= "Level_1.xsb";
		moves= "test5.mov";
		output= "code/Jtest/tests/test5.xsb";
		expected="code/Jtest/expecteds/expected5.xsb";
		Game test=new Game(input, moves, output);	
	 	assertTrue(equalFiles(expected, output)) ;

		}

}
