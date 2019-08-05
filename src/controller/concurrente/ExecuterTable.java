package controller.concurrente;

public class ExecuterTable  implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Saludo");
		for(int i = 0; i<200; i++) {
			System.out.println("aa" +i);
		}
	}

}
