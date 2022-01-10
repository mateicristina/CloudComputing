package com.cloud.bgmeetup.services.repository;

import com.cloud.bgmeetup.services.dto.EventDto;
import com.cloud.bgmeetup.services.dto.EventParticipantDto;
import com.cloud.bgmeetup.services.model.Event;
import com.cloud.bgmeetup.services.model.EventParticipant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<EventDto> get(String id) {
        String sql = "SELECT * FROM event WHERE id = ?";
        RowMapper<EventDto> mapper = getEventRowMapper();
        return jdbcTemplate.query(sql, mapper, id).stream().findFirst();
    }

    public List<EventDto> getAll() {
        String sql = "SELECT * FROM event";
        RowMapper<EventDto> mapper = getEventRowMapper();
        return jdbcTemplate.query(sql, mapper);
    }

    public void save(Event event) {
        String sql = "INSERT INTO event VALUES(?, ?, ?, ?, ?)";

        var eventId = UUID.randomUUID();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, eventId.toString());
            preparedStatement.setObject(2, event.getHostId().toString());
            preparedStatement.setString(3, event.getTitle());
            preparedStatement.setString(4, event.getLocation());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(event.getDate()));
            return preparedStatement;
        });

        var host = new EventParticipant();
        host.setEventId(eventId);
        host.setParticipantId(event.getHostId());
        addEventParticipant(host);
    }

    public void delete(String id) {
        String sql = "DELETE FROM event WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void addEventParticipant(EventParticipant eventParticipant){
        String sql = "INSERT INTO event_participant VALUES(?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, eventParticipant.getEventId().toString());
            preparedStatement.setObject(2, eventParticipant.getParticipantId().toString());
            return preparedStatement;
        });
    }

    public List<EventParticipantDto> getEventParticipants(String eventId){
        String sql = "SELECT * FROM event_participant WHERE eventId = ?";
        RowMapper<EventParticipantDto> mapper = getEventParticipantRowMapper();
        return jdbcTemplate.query(sql, mapper, eventId);

    }

    private RowMapper<EventDto> getEventRowMapper() {
        return (resultSet, i) -> new EventDto(
                UUID.fromString(resultSet.getString("id")),
                UUID.fromString(resultSet.getString("hostId")),
                "",
                resultSet.getString("title"),
                resultSet.getString("location"),
                resultSet.getString("date")
        );
    }

    private RowMapper<EventParticipantDto> getEventParticipantRowMapper() {
        return (resultSet, i) -> new EventParticipantDto(
                UUID.fromString(resultSet.getString("eventId")),
                UUID.fromString(resultSet.getString("participantId")),
                ""
        );
    }
}
