package service.model.dto;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDTO userDTO = (UserDTO) o;
		return id == userDTO.id &&
				profileId == userDTO.profileId &&
				Objects.equals(firstName, userDTO.firstName) &&
				Objects.equals(lastName, userDTO.lastName) &&
				Objects.equals(image, userDTO.image);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, profileId, firstName, lastName, image);
	}
}
