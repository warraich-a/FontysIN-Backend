package service.model.dto;

public class UserDTO {
	private int id;
	private int profileId;
	private String firstName;
	private String lastName;
	private String image;

	public UserDTO(int id, int profileId, String firstName, String lastName, String image) {
		this.id = id;
		this.profileId = profileId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.image = image;
	}

	public UserDTO() {
	}

	public int getId() {
		return id;
	}

	public int getProfileId() {
		return profileId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getImage() {
		return image;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", profileId=" + profileId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", image='" + image + '\'' +
				'}';
	}
}
