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

    public Object getMessageById(String path) {
        return null;
    }
}
