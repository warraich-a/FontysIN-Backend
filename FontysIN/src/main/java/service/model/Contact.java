package service.model;

import java.util.Objects;

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

	public Contact(int id, User user, User friend) {
		this.id = id;
		this.user = user;
		this.friend = friend;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Contact contact = (Contact) o;
		return id == contact.id &&
				isAccepted == contact.isAccepted &&
				Objects.equals(user, contact.user) &&
				Objects.equals(friend, contact.friend);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, friend, isAccepted);
	}
}
