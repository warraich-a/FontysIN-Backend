package service.model.dto;

public class ConversationDTO {

    //fields
    private int id;
    private int firstUserId;
    private int secondUserId;

    //constracture
    public ConversationDTO(int id, int firstUserId, int secondUserId) {
        this.id = id;
        this.firstUserId = firstUserId;
        this.secondUserId = secondUserId;
    }

    public ConversationDTO() {
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirstUserId() {
        return firstUserId;
    }

    public void setFirstUserId(int firstUserId) {
        this.firstUserId = firstUserId;
    }

    public int getSecondUserId() {
        return secondUserId;
    }

    public void setSecondUserId(int secondUserId) {
        this.secondUserId = secondUserId;
    }

    //to string

    @Override
    public String toString() {
        return "ConversationDTO{" +
                "id=" + id +
                ", firstUserId=" + firstUserId +
                ", secondUserId=" + secondUserId +
                '}';
    }

}
