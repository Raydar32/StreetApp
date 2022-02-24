package events;

import java.util.Comparator;

//Semplice metodo di comparazione tra eventi
public class EventComparator implements Comparator<Event> {
	@Override
	public int compare(Event o1, Event o2) {
		return o1.eventNumber - o2.eventNumber;
	}
}
