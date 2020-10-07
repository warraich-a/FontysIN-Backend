package service.model;

public class Contact {
	private int id;
	private static int idSeeder = 0;
	private int userId;
	private int friendId;
	private boolean isAccepted;

	public Contact() {
		this.id = idSeeder;
		idSeeder++;
	}

	public Contact(int userId, int friendId) {
		this.id = idSeeder;
		idSeeder++;
		this.userId = userId;
		this.friendId = friendId;
		this.isAccepted = false;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public boolean getIsAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean accepted) {
		isAccepted = accepted;
	}
}
