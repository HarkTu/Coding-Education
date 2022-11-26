package com.team2project.dao.user;

import com.team2project.dto.Account;
import com.team2project.dto.AccountRequest;
import com.team2project.dto.User;
import com.team2project.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {


    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public UserDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insertUser(UserRequest userRequest) {

        String sql =
                "INSERT INTO users   "
               +"       (username,   "
               +"        pass,       "
               +"        user_role,  "
               +"        email)      "
               +"     VALUES         "
               +"       (:username,  "
               +"        :pass,      "
               +"        :user_role, "
               +"        :email)     ";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("username", userRequest.getUsername())
                .addValue("pass", userRequest.getPass())
                .addValue("user_role", User.UserRole.USER.name())
                .addValue("email", userRequest.getEmail());

         namedParameterJdbcOperations.update(sql, parameterSource);
    }

    @Override
    public void setInfo(AccountRequest account, Long id) {
        String sql =
                " UPDATE accounts_info                   "
               +"  SET first_name = :first_name,         "
               +"      last_name = :last_name,           "
               +"      salary = :salary,                 "
               +"      family_status = :family_status,   "
               +"      family_members = :family_members, "
               +"      primary_currency = :currency,     "
               +"      credit_rating = :rating           "
               +" WHERE fk_user = :fk_user               ";


        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("first_name", account.getFirstName())
                .addValue("last_name", account.getLastName())
                .addValue("salary", account.getSalary())
                .addValue("family_status", account.getFamilyStatus().name())
                .addValue("family_members", account.getFamilyMembers())
                .addValue("currency", account.getPrimaryCurrency().name())
                .addValue("rating", "Unknown")
                .addValue("fk_user", id);

        namedParameterJdbcOperations.update(sql, parameterSource);
    }

    @Override
    public Account getAccountInfoByUserId(Long id) {

        SqlParameterSource parameterSource = new MapSqlParameterSource().addValue("fk_user", id);

        String sql =
                " SELECT account_id,       "
              + "        first_name,        "
              + "        last_name,         "
              + "        salary,           "
              + "        family_status,    "
              + "        family_members,   "
              + "        primary_currency, "
              + "        credit_rating,    "
              + "        fk_user           "
              + " FROM accounts_info       "
              + " WHERE fk_user= :fk_user  ";

        return namedParameterJdbcOperations.queryForObject(sql, parameterSource,
                (rs, rowNum) -> {
                    Account acc = new Account();
                    acc.setAccountId(rs.getLong("account_id"));
                    acc.setFirstName(rs.getString("first_name"));
                    acc.setLastName(rs.getString("last_name"));
                    acc.setSalary(rs.getBigDecimal("salary"));
                    acc.setFamilyStatus(rs.getObject("family_status", Account.FamilyStatus.class));
                    acc.setFamilyMembers(rs.getInt("family_members"));
                    acc.setPrimaryCurrency(rs.getObject("primary_currency", Account.PrimaryCurrency.class));
                    acc.setCreditRating(rs.getString("credit_rating"));

                    return acc;

                });
    }

    @Override
    public void updateClientCreditRating(String rating, Long userId) {
        String sql =
                " UPDATE accounts_info A         "
               +"  SET A.credit_rating = :rating "
               +" WHERE A.fk_user = :userId      ";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("rating", rating)
                .addValue("userId", userId);

        namedParameterJdbcOperations.update(sql,parameterSource);
    }

    @Override
    public void updateUsername(String newUsername, String oldUsername) {
        String sql =
                " UPDATE users U                  "
               +"  SET U.username = :newUsername  "
               +" WHERE U.username = :oldUsername ";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("newUsername", newUsername)
                .addValue("oldUsername", oldUsername);

        namedParameterJdbcOperations.update(sql,parameterSource);
    }

    @Override
    public void updatePassword(String newPassword, String username) {
        String sql =
                " UPDATE users U               "
               +"  SET U.pass = :newPassword   "
               +" WHERE U.username = :username ";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("newPassword", newPassword)
                .addValue("username", username);

        namedParameterJdbcOperations.update(sql,parameterSource);
    }

    @Override
    public void updateEmail(String newEmail, String username) {
        String sql =
                " UPDATE users U               "
               +"  SET U.email = :newEmail     "
               +" WHERE U.username = :username ";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("newEmail", newEmail)
                .addValue("username", username);

        namedParameterJdbcOperations.update(sql,parameterSource);
    }
    @Override
    public Optional<User> findUserByUsername(String username) {
        String sql =
                 " SELECT user_id,            "
                +"        username,           "
                +"        pass,               "
                +"        user_role,          "
                +"        email               "
                +" FROM users                 "
                +" WHERE username = :username ";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("username", username);

        try {
            return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(sql, parameterSource, (rs, rowNum) ->{
                User user = new User();
                user.setUserId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPass(rs.getString("pass"));
                user.setRole(rs.getObject("user_role", User.UserRole.class));
                user.setEmail(rs.getString("email"));

                return user;
            }));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        String sql =
                " SELECT user_id,      "
               +"        username,     "
               +"        pass,         "
               +"        user_role,    "
               +"        email         "
               +" FROM users           "
               +" WHERE email = :email ";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("email", email);

        try {
            return Optional.ofNullable(namedParameterJdbcOperations
                    .queryForObject(sql, parameterSource, (rs, rowNum) ->{

                User user = new User();
                user.setUserId(rs.getLong("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPass(rs.getString("pass"));
                user.setRole(rs.getObject("user_role", User.UserRole.class));
                user.setEmail(rs.getString("email"));

                return user;
            }));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }
}
