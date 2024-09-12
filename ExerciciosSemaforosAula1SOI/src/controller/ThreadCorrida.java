package controller;

import java.util.concurrent.Semaphore;

public class ThreadCorrida extends Thread {

	private int id = (int) threadId();
	private int distanciaPercorrida;
	private int velocidadeBonus;
	private Semaphore semaforo;
	private boolean cavaleiroPossuiTocha;
	private static int temTocha = 0;
	private static int temPedra = 0;
	private static int portaEscolhida = 0;

	public ThreadCorrida(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		while (distanciaPercorrida <= 2000) {
			caminhadaCavaleiro();
			if (distanciaPercorrida >= 500 && distanciaPercorrida <= 505 && temTocha == 0) {
				try {
					temTocha++;
					semaforo.acquire();
					pegarTocha();
				} catch (Exception e) {
					temTocha = 0;
					System.err.println(e.getMessage());
				} finally {
					cavaleiroPossuiTocha = true;
					semaforo.release();
				}
			}
			if (distanciaPercorrida >= 1500 && distanciaPercorrida <= 1505 && cavaleiroPossuiTocha == false
					&& temPedra == 0) {
				try {
					temPedra++;
					semaforo.acquire();
					pegarPedra();
				} catch (Exception e) {
					temPedra = 0;
					System.err.println(e.getMessage());
				} finally {
					semaforo.release();
				}
			}
			System.out.println("#" + id + " andou" + distanciaPercorrida);
		}
		System.out.println("#" + id + "chegou em " + distanciaPercorrida + " metros");

	}

	private void caminhadaCavaleiro() {
		int passo = (int) ((Math.random() * 3) + 2);
		try {
			sleep(15);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		distanciaPercorrida += (passo + velocidadeBonus);
	}

	private void pegarTocha() {
		velocidadeBonus += 2;
		System.out.println("O cavaleiro " + id + " pegou a tocha e recebeu " + "+2 de velocidade extra!");
	}

	private void pegarPedra() {
		velocidadeBonus += 2;
		System.out.println("O cavaleiro " + id + " pegou a pedra e recebeu " + "+2 de velocidade extra!");
	}

	private void escolherPorta() {
		int i = (int) (Math.random() * 3) + 1;
		while(i == portaEscolhida) {
			i = (int) (Math.random() * 3) + 1;
		}
	}
}