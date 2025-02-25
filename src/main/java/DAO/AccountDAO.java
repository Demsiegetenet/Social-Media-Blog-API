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

    public Account registerUser(Account account){
            Connection connection = ConnectionUtil.getConnection();
            try {
                String sql = "INSERT INTO account(username,password) VALUES(?,?)" ;
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, account.getUsername());
                preparedStatement.setString(2, account.getPassword());
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

    public List<Account> getAllUser() {
            Connection connection = ConnectionUtil.getConnection();
            List<Account> accounts = new ArrayList<>();
            try {
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
}
