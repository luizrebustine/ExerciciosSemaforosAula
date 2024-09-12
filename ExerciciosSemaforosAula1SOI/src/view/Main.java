package view;

import controller.ThreadCorrida;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		int permissoesCavaleiro = 1;
		Semaphore semaforo = new Semaphore(permissoesCavaleiro);
		for (int i = 0; i < 5; i++) {
			Thread t = new ThreadCorrida(semaforo);
			t.start();
		}
	}

}
