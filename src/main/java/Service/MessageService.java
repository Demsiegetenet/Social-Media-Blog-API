package Service;
import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
     private MessageDAO messageDAO;

     public MessageService() {
        messageDAO = new MessageDAO();
     }

     public MessageService(MessageDAO messageDAO){
            this.messageDAO = messageDAO;
    }

     public List<Message> getAllMessages() {
        return messageDAO.getAllMesasages();
    }

    public Message getMessageById(int message_id){
        return messageDAO.getMessageByMessageId(message_id);
    }

    public Message addMessage(Message message) {

   if(message.getMessage_id()!=0 && message.getMessage_text().length()<255){
    messageDAO.insertMessage(message);
    
    return messageDAO.getMessageByMessageId(message.getMessage_id());
   }
   else
   return null;

    }
    public void deleteMessageById(int message_id){
        messageDAO.deleteMessage(message_id);
    }

    public Object deleteMessageById(String pathParam) {
        return null;
    }

    public Message updateFlight(int message_id, Message message){

        if(message!=null ){
            messageDAO.updateMessage(message_id, message);
            return messageDAO.getMessageByMessageId(message_id); 
    }

    else 
       return null ;  
    
}
}
