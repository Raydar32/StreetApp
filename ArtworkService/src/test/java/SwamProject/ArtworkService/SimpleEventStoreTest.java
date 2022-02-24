package SwamProject.ArtworkService;
import org.junit.Assert;
import org.junit.Test;
import aggregates.ArtworkAggregate;
import entities.ArtStyle;
import entities.ArtType;
import entities.Author;
import entities.User;

import eventstore.SimpleEventStore;

public class SimpleEventStoreTest {

	ArtworkAggregate testArtwork = getTestingArtwork();
	SimpleEventStore eventStore = SimpleEventStore.getInstance();

	@Test
	public void testSaveAndLoad() throws Exception {
		// si salva l'aggregato
		eventStore.save(testArtwork.getId(), testArtwork.getEvents());
		ArtworkAggregate retrieved = new ArtworkAggregate(testArtwork.getId());
		retrieved.rebuildAggregateStatus(eventStore.load(testArtwork.getId()));
		Assert.assertEquals(testArtwork, retrieved);
	}

	@Test
	public void testUpdate() throws Exception {
		eventStore.save(testArtwork.getId(), testArtwork.getEvents());
		testArtwork.changeName("Update test");
		testArtwork.changeArtStyle(ArtStyle.COMIC);
		testArtwork.changeArtType(ArtType.MURALES);
		testArtwork.changeAuthor(new Author());
		eventStore.update(testArtwork, testArtwork.getId());
		ArtworkAggregate loadedAggregate = new ArtworkAggregate(testArtwork.getId());
		loadedAggregate.rebuildAggregateStatus(eventStore.load(testArtwork.getId()));
		Assert.assertEquals(loadedAggregate, testArtwork);
	}

	@Test(expected = NullPointerException.class)
	public void testDelete() throws Exception {
		eventStore.save(testArtwork.getId(), testArtwork.getEvents());
		eventStore.delete(testArtwork.getId());
		eventStore.load(testArtwork.getId());
	}

	private ArtworkAggregate getTestingArtwork() {
		User reportingUser = new User();
		Author author = new Author();
		ArtworkAggregate artwork = new ArtworkAggregate(reportingUser, 10, 10, "ArtworkTest");
		artwork.changeArtStyle(ArtStyle.COMIC);
		artwork.changeArtType(ArtType.MURALES);
		artwork.changeAuthor(author);
		return artwork;
	}

}
