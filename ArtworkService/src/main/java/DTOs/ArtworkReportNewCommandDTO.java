package DTOs;

public class ArtworkReportNewCommandDTO {
	private String name;
	private String style;
	private String type;
	private long latitude;
	private long longitude;
	private int reportingUserID;
	private int artworkCreatorID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public int getReportingUserID() {
		return reportingUserID;
	}

	public void setReportingUserID(int reportingUserID) {
		this.reportingUserID = reportingUserID;
	}

	public int getArtworkCreatorID() {
		return artworkCreatorID;
	}

	public void setArtworkCreatorID(int artworkCreatorID) {
		this.artworkCreatorID = artworkCreatorID;
	}

	public ArtworkReportNewCommandDTO() {

	}

}
