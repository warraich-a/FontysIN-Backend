package service.model.dto;

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
}
