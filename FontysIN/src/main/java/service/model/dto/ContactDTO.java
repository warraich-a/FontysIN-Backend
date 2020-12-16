package service.model.dto;

import java.util.Objects;

public class ContactDTO {
	// ContactDTO

	private int id;
	private UserDTO user;
	private UserDTO friend;
	private boolean isAccepted;

	public ContactDTO(int id, UserDTO user, UserDTO friend, boolean isAccepted) {
		this.id = id;
		this.user = user;
		this.friend = friend;
		this.isAccepted = isAccepted;
	}

	public ContactDTO() {
	}

	public int getId() {
		return id;
	}

	public UserDTO getUser() {
		return user;
	}

	public UserDTO getFriend() {
		return friend;
	}

	public boolean getIsAccepted() {
		return isAccepted;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public void setFriend(UserDTO friend) {
		this.friend = friend;
	}

	public void setAccepted(boolean accepted) {
		isAccepted = accepted;
	}

	@Override
	public String toString() {
		return "ContactDTO{" +
				"id=" + id +
				", user=" + user +
				", friend=" + friend +
				", isAccepted=" + isAccepted +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ContactDTO that = (ContactDTO) o;
		return id == that.id &&
				isAccepted == that.isAccepted &&
				Objects.equals(user, that.user) &&
				Objects.equals(friend, that.friend);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, friend, isAccepted);
	}
}
