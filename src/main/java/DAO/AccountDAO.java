package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {

            String sql = "INSERT INTO account(account_id,username,password) VALUES(?,?,?)" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        
            preparedStatement.setInt(1, account.getAccount_id());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();

            if(pkeyResultSet.next()){
                int generated_message_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_message_id, account.getUsername(),account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Account> getAllAcount() {
    
            Connection connection = ConnectionUtil.getConnection();
            List<Account> accounts = new ArrayList<>();
            try {
                //Write SQL logic here
                String sql = "SELECT * FROM account";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    Account account = new Account(rs.getInt("account_id"), rs.getString("username"),
                                                 rs.getString("password"));
                    accounts.add(account);
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return accounts;
        }

        public Account getAccountbyAccountId(int account_id){
            Connection connection = ConnectionUtil.getConnection();
            try {
                //Write SQL logic here
                String sql = "SELECT * FROM account WHERE account_id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
                //write preparedStatement's setInt method here.
                preparedStatement.setInt(1,account_id);
    
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    Account account = new Account(rs.getInt("account_id"),
                            rs.getString("  username"),
                            rs.getString("password"));
                       
                    return account;
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
            return null;
        }
    
}
