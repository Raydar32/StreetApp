package DAOs;

import java.util.List;

import java.util.UUID;

import aggregates.ArtworkAggregate;


public interface ArtworkDaoInterface {
	public ArtworkAggregate save(ArtworkAggregate a) throws Exception;

	public ArtworkAggregate load(UUID id) throws Exception;

	public ArtworkAggregate delete(UUID id) throws Exception;

	public ArtworkAggregate update(ArtworkAggregate newArtwork, UUID id) throws Exception;

	public List<ArtworkAggregate> getEveryArtwork() throws Exception;
}
