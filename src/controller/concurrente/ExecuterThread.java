package controller.concurrente;

import java.util.concurrent.Executor;

public class ExecuterThread implements Executor{

	@Override
	public void execute(Runnable command) {
		command.run();
	}

}
