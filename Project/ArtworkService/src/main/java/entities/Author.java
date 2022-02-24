package entities;

import java.util.Objects;

public class Author {

	private int id;
	private String authorName;
	private String groupName;

	private GroupType groupType;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public GroupType getGroupType() {
		return groupType;
	}

	public void setGroupType(GroupType groupType) {
		this.groupType = groupType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", authorName=" + authorName + ", groupType=" + groupType + "]";
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
		Author other = (Author) obj;
		return Objects.equals(authorName, other.authorName) && Objects.equals(groupName, other.groupName)
				&& groupType == other.groupType;
	}

}
