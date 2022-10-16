//SANDRA CIUDAD MORENO.

package ProyectoPracticas;
import java.util.Scanner;

public class main {
	static Scanner TECLADO = new Scanner(System.in);
	static int[][] apuestas, aciertos, numeroaciertos;
	static int[] boletoaleatorio;
	static int filas, columnas, reintegro, complementarioaleatorio, reintegroaleatorio;

	public static void main(String[] args) {

		System.out.println("PROGRAMA PARA OBTENER BOLETOS DE LOTER�A");
		CrearBoleto();

		apuestas = MatrizBidimensional(filas, columnas);
		numeroaciertos = new int[filas][1];

		System.out.println("Elige el numero de reintegro (del 0 al 9)");
		reintegro = IntroducirNumeroEntre(0, 9);

		// Generar boleto, numero complementario y reintegro aleatorios

		boletoaleatorio = MatrizAleatoriaEntre(6, 1, 49);

		do {
			complementarioaleatorio = NumeroAleatorio(1, 49);
		} while (LecturaArrayRepetido(complementarioaleatorio, boletoaleatorio) == true); //Para que no se repita el valor

		reintegroaleatorio = NumeroAleatorio(0, 9);

		// Guardar los aciertos en una matriz
		aciertos = ArrayAciertos(apuestas, boletoaleatorio);

		// Resultados
		ResultadoSorteo();
		ApuestasyAciertos();
		ComprobarReintegro();

	}

	// Metodos

	static int NumeroPositivo() {
		int numeropositivo = TECLADO.nextInt();
		while (numeropositivo <= 0) {
			System.out.println("El numero debe ser positivo");
			numeropositivo = TECLADO.nextInt();
		}
		return numeropositivo;
	}

	static int IntroducirNumeroEntre(int intervalo1, int intervalo2) {
		int numeropositivo = TECLADO.nextInt();
		while (numeropositivo < intervalo1 || numeropositivo > intervalo2) {
			System.out.println("El numero esta fuera del intervalo");
			numeropositivo = TECLADO.nextInt();
		}
		return numeropositivo;
	}

	static boolean LecturaArrayRepetido(int numero, int[] array, int posicionvector) {
		boolean repetido = false;
		if (posicionvector >= 0 && posicionvector <= array.length) {
			for (int i = 0; i < posicionvector + 1; i++) {
				if (numero == array[i]) {
					repetido = true;
				}
			}
		} else {
			System.out.println("El valor de la h no es valida");
		}
		return repetido;
	}

	static boolean LecturaArrayRepetido(int numero, int[] array) {
	
		return LecturaArrayRepetido(numero, array, array.length-1); //El metodo anterior, pero la posicion ser� el tama�o del vector
	}

	static int NumeroAleatorio(int intervalo1, int intervalo2) {
		int numeroaleatorio = (int) (Math.random() * intervalo2) + intervalo1;
		return numeroaleatorio;
	}

	static void MostrarMatriz(int[] array, int mostrarhasta) {
		for (int i = 0; i < mostrarhasta; i++) {
			System.out.print(" " + array[i]);
		}
	}

	static void MostrarMatriz(int[] array) {

		MostrarMatriz(array, array.length); //Usamos el anterior

	}

	// Fin de pratica 7

	// Practica 8

	static int[] IntroducirMatrizEntre(int[] array, int intervalo1, int intervalo2) {

		for (int i = 0; i < array.length; i++) {
			System.out.println("Introducir valor " + (i + 1));
			array[i] = IntroducirNumeroEntre(intervalo1, intervalo2);

			if (i > 0) {
				while (LecturaArrayRepetido(array[i], array, i - 1) == true) {
					System.out.println("Hay un numero repetido, rep�talo");
					array[i] = IntroducirNumeroEntre(intervalo1, intervalo2);
				}
			}
		}
		return array;
	}

	static int[] MatrizAleatoriaEntre(int N, int intervalo1, int intervalo2) {

		int[] ArrayRandom = new int[N];

		for (int i = 0; i < ArrayRandom.length; i++) {

			ArrayRandom[i] = (int) (Math.random() * intervalo2) + intervalo1;

			if (i > 0) {
				while (LecturaArrayRepetido(ArrayRandom[i], ArrayRandom, i - 1) == true) {
					ArrayRandom[i] = (int) (Math.random() * intervalo2) + intervalo1;
				}
			}
		}
		return ArrayRandom;
	}

	static int[][] MatrizBidimensional(int N, int M) { //El usuario introduce las apuestas
		int[][] ArrayBidimensional = new int[N][M];
		for (int i = 0; i < ArrayBidimensional.length; i++) {
			System.out.println("Introduce la apuesta " + (i + 1));
			IntroducirMatrizEntre(ArrayBidimensional[i], 1, 49);
		}
		return ArrayBidimensional;
	}

	static int[][] ArrayAciertos(int[][] matriz, int[] vector) {
		int counter = 0;
		int aciertos = 0;
		int[][] ArrayAciertos = new int[matriz.length][matriz[0].length + 3];

		for (int i = 0; i < matriz.length; i++) {

			for (int y = 0; y < matriz[0].length; y++) {
				
				//Comprobar aciertos apuestas
					if (LecturaArrayRepetido(matriz[i][y], vector)==true) {

						ArrayAciertos[i][counter] = matriz[i][y];
						counter = counter + 1;
						aciertos = aciertos + 1;
					}

				//Comprobar acierto complementario
				if (matriz[i][y] == complementarioaleatorio) {

					ArrayAciertos[i][counter] = matriz[i][y];
					counter = counter + 1;
					aciertos = aciertos + 1;
				}

			}
			numeroaciertos[i][0] = aciertos;
			aciertos = 0;
			counter = 0;
		}
		return ArrayAciertos;
	}

	// Sesion 9

	static void CrearBoleto() {
		System.out.println("\n¿Cuantas apuestas quiere realizar?");
		filas = NumeroPositivo();
		System.out.println("A continuacion se seleccionara el tipo de todas las apuestas 1=Simple2=Multiple");
		columnas = IntroducirNumeroEntre(1, 2);
		switch (columnas) {
		case 1:
			columnas = 6;
			break;
		case 2:
			columnas = 7;
			break;

		}

	}

	static void ResultadoSorteo() {
		System.out.print("\nEl resultado de la loteria es ");
		MostrarMatriz(boletoaleatorio);
		System.out.print(" + N.Complementario + " + complementarioaleatorio + " + N.Reintegro " + reintegroaleatorio);
	}

	static void ApuestasyAciertos() { //Informacion de las apuestas y los aciertos del usuario

		System.out.println("\nTus apuestas son: ");
		for (int i = 0; i < apuestas.length; i++) {
			System.out.print("\nApuesta " + (i + 1) + ":");
			System.out.print(" ");
			MostrarMatriz(apuestas[i]);
			System.out.print(" | Aciertos: ");
			MostrarMatriz(aciertos[i], numeroaciertos[i][0]);
			System.out.print(" (" + numeroaciertos[i][0] + " en total)");
		
		}

	}
	
	static void ComprobarReintegro() {
		if (reintegro == reintegroaleatorio) {
			System.out.println("\nHa acertado el numero de reintegro (" + reintegro + ")" );
		}
		else {
			System.out.println("\nNo ha acertado el numero de reintegro (el tuyo era el " + reintegro + ")");
		}
		
	}
	
}
