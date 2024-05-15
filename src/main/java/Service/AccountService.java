package Service;

import java.util.ArrayList;
import java.util.List;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Account;
import Model.Message;

public class AccountService {
    private AccountDAO accountDAO;
    private MessageDAO messageDAO;

    public AccountService() {
       accountDAO = new AccountDAO();
       messageDAO = new MessageDAO();
    }
  
    public AccountService(AccountDAO accountDAO){
           this.accountDAO = accountDAO;
   }

   public Account registerUser(Account account) {
         
         List<Account> acc = accountDAO.getAllUser();
      if(account.getUsername().length()>0 && account.getPassword().length()>=4) {
         for(int i=0;i<acc.size();i++){
            if(acc.get(i).getUsername()!=account.getUsername()){
               return  accountDAO.registerUser(account);
            }
            else
            return null;
         
         }
      }
      
         return null;
   }

   public Account loginUser(Account account){
    
       List<Account> accounts = accountDAO.getAllUser();
       for(int i=0;i<accounts.size();i++){
         if(accounts.get(i).getUsername().equals(account.getUsername()) && accounts.get(i).getPassword().equals(account.getPassword())){
             return accounts.get(i);
         }
   }
   return null;
}

public List<Message> getMessageByAccountId(int account_id){
      List<Message> messages = messageDAO.getAllMesasages();
      Account account = accountDAO.getAccountByAccountId(account_id);
      List<Message> filtered_message = new ArrayList<>();

      for(int i=0;i<messages.size();i++){
            if(account.getAccount_id()==messages.get(i).getPosted_by()){
               filtered_message.add(messageDAO.getMessageByMessageId(messages.get(i).getMessage_id()));
               return filtered_message;
            }
            else
            return null;
      }
   return null;
}  
}