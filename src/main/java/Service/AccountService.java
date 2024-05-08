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

   public Account addAccount(Account account) {
      List<Account> acc = accountDAO.getAllAcount();
      boolean duplicate = true;
      for(int i=0;i<acc.size();i++){
         if(acc.get(i).getUsername()!=account.getUsername())
         duplicate = false;
      }


      if(duplicate==false && (account.getUsername()!=null &&  account.getPassword().length()>4)){
          accountDAO.insertAccount(account);
          return accountDAO.getAccountbyAccountId(account.getAccount_id());
      }
      else
      return null;
   }
}
