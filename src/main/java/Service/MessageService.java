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

    public Message getMessageByAccountId(int account_id){
        return messageDAO.getMessageByMessageId(account_id);
    }

    public Message addMessage(Message message) {
        List<Account> accounts = accountDAO.getAllUser();
         for(int i=0;i<accounts.size();i++){
            if(accounts.get(i).getAccount_id()==message.getPosted_by()){
                if(messageDAO.getMessageByMessageId(message.getMessage_id())==null){
                    if(message.getMessage_text().length()>0 && message.getMessage_text().length()<255){
                       return messageDAO.insertMessage(message);
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

    public Message deleteMessageById(int message_id){
        List<Message> messages = messageDAO.getAllMesasages();
        for(int i=0;i<messages.size();i++){
            if(messages.get(i).getMessage_id()==message_id){
                messageDAO.deleteMessageById(message_id);
                return messages.get(i);
            }
            else
            return null;
        }
       return null;
    }


    public Message updateMesage(int message_id, Message message){
            List<Message> messages = messageDAO.getAllMesasages();
            for(int i=0;i<messages.size();i++){
                if(message_id==messages.get(i).getMessage_id()){
                if(message.getMessage_text().length()>0 && message.getMessage_text().length()<=255){
                    messageDAO.updateMessage(message_id, message);
                    return messageDAO.getMessageByMessageId(message_id);
                }
                else
                return null;
                }
                else
                return null;
            }
            return null ;  
    
}
}
