package DTOs;

import java.util.Objects;

public class ArtworkDTO {
	private String id;
	private String name;
	private String style;
	private String type;
	private int user_id;
	private int author_id;
	private long latitude;
	private long longitude;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
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

	@Override
	public int hashCode() {
		return Objects.hash(author_id, latitude, longitude, name, style, type, user_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtworkDTO other = (ArtworkDTO) obj;
		return author_id == other.author_id && latitude == other.latitude && longitude == other.longitude
				&& Objects.equals(name, other.name) && Objects.equals(style, other.style)
				&& Objects.equals(type, other.type) && user_id == other.user_id;
	}

}
