package view;

import controller.ThreadCorrida;
import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) {
		int permissoesCavaleiro = 1;
		int permissoesPorta = 4;
		Semaphore semaforo = new Semaphore(permissoesCavaleiro);
		Semaphore semaforoPorta = new Semaphore(permissoesPorta);
		for (int i = 0; i < 5; i++) {
			Thread t = new ThreadCorrida(semaforo, semaforoPorta);
			t.start();
		}
	}

}
