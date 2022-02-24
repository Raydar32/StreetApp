package mappers;

import DTOs.EventDTO;
import events.Event;

public class EventMapper {

	public EventDTO eventToDTO(Event e) {
		EventDTO eDto = new EventDTO();
		eDto.eventName = e.getClass().toString();
		eDto.version = e.eventNumber;
		return eDto;
	}
}
