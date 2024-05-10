package Service;
import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

public class MessageService {
     private MessageDAO messageDAO;
     private AccountDAO accountDAO;
 
     public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();

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
        List<Account> accounts = accountDAO.getAllAcount();
         for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccount_id()==message.getPosted_by()){
                if(messageDAO.getMessageByMessageId(message.getMessage_id())==null){
                    if(message.getMessage_text().length()>0 && message.getMessage_text().length()<255){
                        messageDAO.insertMessage(message);
                        return messageDAO.getMessageByMessageId(message.getMessage_id());
                 }
                return null;
            } 
            else
            return null;
            }
            else
            return null;
         }

    
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
