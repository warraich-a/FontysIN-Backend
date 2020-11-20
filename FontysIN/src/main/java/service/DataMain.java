package service;

import service.controller.MessageController;
import service.model.User;
import service.model.UserType;

import java.net.URI;
import java.time.LocalDate;


public class DataMain {

    private static final URI BASE_URI = URI.create("http://localhost:9090/booky/");

    public static void main(String[] args) {

        MessageController messageController = new MessageController();

        messageController.DeleteConversation(1,1);
    }

}
