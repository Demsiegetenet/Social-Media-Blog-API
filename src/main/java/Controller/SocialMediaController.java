package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account; 
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    private MessageService messageService;
    private AccountService accountService
;
    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
      
    }
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
    
        app.get("/messages", this::getAllMessagesHandler);
        app.patch("/messages/{message_id}", this::patchMessageHandler);
        app.get("/messages/{message_id}", this::getMessagesHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesFromUserHandler);

        app.post("/messages", this::postMessageHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/register", this::postRegisterHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);

        return app;
    }

    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    private void postMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage!=null){
            ctx.json(mapper.writeValueAsString(addedMessage));
        }else{
            ctx.status(400);
        }
    }

    private void postLoginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
   
        Account addedAcount = accountService.loginUser(account);
        if(addedAcount!=null){
          
            ctx.json(mapper.writeValueAsString(addedAcount));
             
        }
        else{
            ctx.status(401);
        }
    }

    private void postRegisterHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
   
        Account registeredAcount = accountService.registerUser(account);
        if(registeredAcount!=null){
          
            ctx.json(mapper.writeValueAsString(registeredAcount));
             
        }
        else{
            ctx.status(400);
        }
    }

    private void patchMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
      

        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message updatedMessage = messageService.updateMesage(message_id, message);
        System.out.println(updatedMessage);
        if(updatedMessage != null){
            ctx.json(mapper.writeValueAsString(updatedMessage));
        }else{
            ctx.status(400);
        }

    }

    private void getMessagesHandler(Context ctx) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessageById(message_id);      
        if(message != null){
            ctx.json(mapper.writeValueAsString(message));
        }else{
            ctx.json("");
        }
    }

    private void getAllMessagesFromUserHandler(Context ctx) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        Message message = messageService.getMessageById(account_id);      
        if(message != null){
            ctx.json(mapper.writeValueAsString(message));
        }else{
            ctx.json("");
        }
    }

    private void deleteMessageHandler(Context ctx) throws JsonMappingException, JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            int message_id = Integer.parseInt(ctx.pathParam("message_id"));
            Message deletedMessage = messageService.deleteMessageById(message_id);
            if(deletedMessage!=null){
                ctx.json(mapper.writeValueAsString(deletedMessage)); 
            }
            else{
                ctx.json("");
            }
    }
}