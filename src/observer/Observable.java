package observer;

import java.util.HashSet;
import java.util.Set;

public class Observable {
	
	private final Set<IObserver> IObservers = new HashSet<IObserver>();
	
	public void addIObservers(IObserver observador){
		this.IObservers.add(observador);
		
	}
	
	public void removeIObservers(IObserver observador){
		this.IObservers.remove(observador);
		
	}
	
	public void notifyIObservers(){
		for (IObserver IObserver : IObservers) {
			IObserver.update();
		}
	}
}
