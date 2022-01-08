package com.cloud.bgmeetup.services.repository;

import com.cloud.bgmeetup.services.dto.UserDto;
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

    public Optional<UserDto> get(String id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        RowMapper<UserDto> mapper = getUserRowMapper();
        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }


    public User update(User user) {
        String sql = "UPDATE user SET email = ?, firstName = ?, lastName = ?, location = ? WHERE id = ?";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getLocation());
            preparedStatement.setObject(5, user.getId().toString());
            return preparedStatement;
        });
        return user;
    }

    private RowMapper<UserDto> getUserRowMapper() {
        return (resultSet, i) -> new UserDto(
                UUID.fromString(resultSet.getString("id")),
                resultSet.getString("email"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getString("location")
        );
    }
}
