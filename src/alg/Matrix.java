package alg;

public class Matrix {
	//construtor vazio
	public Matrix() {
	}
	
	//soma duas matrizes (MÉTODO ESTÁTICO)
	public static int[][] sum(int[][] a, int[][] b) {
		if(a.length == b.length && a[0].length == b[0].length) {
			int[][] c = new int[a.length][a[0].length];
			
			for(int i = 0; i < c.length; i++) {
				for(int j = 0; j < c[0].length; j++) {
					c[i][j] = a[i][j] + b[i][j];
				}
			}
			
			return c;
		} else {
			return null;
		}		
	}
	
	//subtrai duas matrizes (MÉTODO ESTÁTICO)
	public static int[][] subtract(int[][] a, int[][] b) {
		if(a.length == b.length && a[0].length == b[0].length) {
			int[][] c = new int[a.length][a[0].length];
			
			for(int i = 0; i < c.length; i++) {
				for(int j = 0; j < c[0].length; j++) {
					c[i][j] = a[i][j] - b[i][j];
				}
			}
			
			return c;
		} else {
			return null;
		}		
	}
	
	//multiplica duas matrizes (MÉTODO ESTÁTICO)
	public static int[][] multiply(int[][] a, int[][] b) {
		if(a[0].length == b.length && a.length > 0 && b[0].length > 0) {
			int[][] c = new int[a.length][b[0].length];
			int aux;			
			
			for(int i = 0; i < c.length; i++) {
				for(int j = 0; j < c[0].length; j++) {
					//obter o elemento correspondente a soma das multiplicações
					//de cada elemento da linha da primeira matriz pela coluna
					//respectiva da segunda matriz
					aux = 0;
					
					for(int l=0; l < a[0].length; l++) {
						aux += a[i][l] * b[l][j];
					}
					
					c[i][j] = aux;
				}
			}
			
			return c;
		} else {
			return null;
		}	
	}
	
	//multiplica uma única matriz por escalar (MÉTODO ESTÁTICO)
	public static int[][] multplyByScalar(int[][] a, int scalar) {
		int[][] b = new int[a.length][a[0].length];
			
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				b[i][j] = a[i][j] * scalar;
			}
		}
			
		return b;	
	}
	
	
	public static String[][] toStr(int[][] m) {
		String[][] n = new String[m.length][m[0].length];
		
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[0].length; j++) {
				n[i][j] = String.valueOf(m[i][j]);
			}
		}
		
		return n;
	}	
		
	//converte a matriz romana para decimal
	public static int[][] toDecimal(String[][] m) {
		
		int[][] n = new int[m.length][m[0].length];
		
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[i].length; j++) {
				n[i][j] = elementToDec(m[i][j]);
			}
		}
		
		return n;
	}
	
	//convete um único elemento de romano para decimal
	public static int elementToDec(String text) {
		int n = 0;
		int rightNumber = 0;
		for (int i = text.length() - 1; i >= 0; i--) {
			int value = (int) romanCharToDouble(text.charAt(i));
			n += value * Math.signum(value + 0.5 - rightNumber);
			rightNumber = value;
		}
		return n;
	}
	
	//converte um único caractere romano para um número decimal
	private static double romanCharToDouble(char character) {
		return Math.floor(Math.pow(10, "IXCM".indexOf(character))) + 5 * Math.floor(Math.pow(10, "VLD".indexOf(character)));
	}
	
	
	
	//Código desenvolvido por: Professor Francisco Edmundo
	/*
	private int traduzirNumeralRomano(String texto) {
		int n = 0;
		int numeralDaDireita = 0;
		for (int i = texto.length() - 1; i >= 0; i--) {
			int valor = (int) traduzirNumeralRomano(texto.charAt(i));
			n += valor * Math.signum(valor + 0.5 - numeralDaDireita);
			numeralDaDireita = valor;
		}
		return n;
	}
	private double traduzirNumeralRomano(char caractere) {
		return Math.floor(Math.pow(10, "IXCM".indexOf(caractere))) + 5 * Math.floor(Math.pow(10, "VLD".indexOf(caractere)));
	}
	*/
}
