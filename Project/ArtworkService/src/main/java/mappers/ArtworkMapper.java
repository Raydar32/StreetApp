package mappers;

import DTOs.ArtworkDTO;
import aggregates.ArtworkAggregate;

public class ArtworkMapper {
	/*
	 * DTO che restituisce soltanto i dati dell'artwork, il DTO con la
	 * storicizzazione degli eventi può essere aggiunto in seguito.
	 */
	public ArtworkDTO ArtworkEntityToDTO(ArtworkAggregate artwork) {
		ArtworkDTO dto = new ArtworkDTO();
		dto.setId(artwork.getId().toString());
		dto.setName(artwork.getName());
		dto.setStyle(artwork.getStyle().toString());
		dto.setType(artwork.getType().toString());
		dto.setLatitude(artwork.getLat());
		dto.setLongitude(artwork.getLongitude());
		dto.setAuthor_id(artwork.getArtworkCreator().getId());
		dto.setUser_id(artwork.getReportingUser().getId());

		return dto;
	}

}
