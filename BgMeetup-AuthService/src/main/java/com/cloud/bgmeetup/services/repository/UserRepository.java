package com.cloud.bgmeetup.services.repository;


import com.cloud.bgmeetup.services.dto.UserDto;
import com.cloud.bgmeetup.services.dto.UserLoginDto;
import com.cloud.bgmeetup.services.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void register(User user) {
        String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getLocation());
            preparedStatement.setObject(6, user.getPasswordHash());
            preparedStatement.setObject(7, user.getPasswordSalt());
            return preparedStatement;
        });
    }

    public Optional<UserDto> login(UserLoginDto userLogin) {
        String sql = "SELECT * FROM user WHERE email = ?";
        RowMapper<UserDto> mapper = getUserRowMapper();
        return jdbcTemplate.query(sql, mapper, userLogin.getEmail()).stream().findFirst();
    }

    private RowMapper<UserDto> getUserRowMapper() {
        return (resultSet, i) -> new UserDto(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("email"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("location"),
                resultSet.getBytes("passwordSalt"),
                resultSet.getBytes("passwordHash")
        );
    }
}