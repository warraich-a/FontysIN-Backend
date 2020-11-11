package service.model;

public class Contact {
	private int id;
	private static int idSeeder = 0;
	private User user;
	private User friend;
	private boolean isAccepted;

	public Contact() {
		this.id = idSeeder;
		idSeeder++;
	}

	public Contact(User user, User friend) {
		this.id = idSeeder;
		idSeeder++;
		this.user = user;
		this.friend = friend;
		this.isAccepted = false;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public boolean getIsAccepted() {
		return isAccepted;
	}

	public void setIsAccepted(boolean accepted) {
		isAccepted = accepted;
	}

	public static void decreaseIdSeeder() {
		idSeeder--;
	}
}
