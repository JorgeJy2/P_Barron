package observer;

import java.util.HashSet;
import java.util.Set;

/**
 * Archivo: Observable.java contiene la definición de la clase Observable.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class Observable {
	// declaración de atributo
	private final Set<IObserver> IObservers = new HashSet<IObserver>();

	/**
	 * Método addIObservers
	 * 
	 * @param observador objeto de tipo IObserver
	 */
	public void addIObservers(IObserver observador) {
		this.IObservers.add(observador);

	}// cierre método addIObservers

	/**
	 * Método removeIObservers
	 * 
	 * @param observador objeto de tipo IObserver
	 */
	public void removeIObservers(IObserver observador) {
		this.IObservers.remove(observador);

	}// cierre método removeIObservers

	/**
	 * Método notifyIObservers
	 */
	public void notifyIObservers() {
		for (IObserver IObserver : IObservers) {
			IObserver.update();
		}
	}// cierre método notifyIObservers
}// cierre clase Observable
