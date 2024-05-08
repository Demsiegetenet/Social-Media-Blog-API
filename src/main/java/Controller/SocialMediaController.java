package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        app.get("example-endpoint", this::exampleHandler);
        app.get("/messages", this::getAllMessagesHandler);
        // app.get("/messages/{message_id}", this::getMessageHandler);

        app.post("/messages", this::postMessageHandler);
        app.post("/login", this::postLoginHandler);
        app.post("/register", this::postRegisterHandler);
        app.delete("/messages/{message_id}", ctx->{
            messageService.deleteMessageById(ctx.pathParam("message_id"));
        });

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    // private void getMessageHandler(Context ctx) {
    //   ctx.json(messageService.getMessageById(ctx.pathParam("message_id")));
    // }

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
   
        Account addedAcount = accountService.addAccount(account);
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
   
        Account addedAcount = accountService.addAccount(account);
        if(addedAcount!=null){
          
            ctx.json(mapper.writeValueAsString(addedAcount));
             
        }
        else{
            ctx.status(400);
        }
    }

    private void deleteMessageHandler(Context ctx) {
        ctx.json(messageService.deleteMessageById(ctx.pathParam("message_id")));
      }


}