package DTOs;

import java.util.Objects;

public class AuthorDTO {
	private int id;
	private String authorName;
	private String groupName;
	private String groupType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(authorName, groupName, groupType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorDTO other = (AuthorDTO) obj;
		return Objects.equals(authorName, other.authorName) && Objects.equals(groupName, other.groupName)
				&& Objects.equals(groupType, other.groupType);
	}

}
