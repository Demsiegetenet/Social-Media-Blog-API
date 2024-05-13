package Service;

import java.util.List;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
       accountDAO = new AccountDAO();
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
}