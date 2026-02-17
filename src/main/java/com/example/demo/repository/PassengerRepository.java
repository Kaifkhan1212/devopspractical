package com.example.demo.repository;

import com.example.demo.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PassengerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Passenger> rowMapper = (rs, rowNum) -> {
        Passenger p = new Passenger();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setAge(rs.getInt("age"));
        p.setGender(rs.getString("gender"));
        p.setSeatNumber(rs.getString("seat_number"));
        return p;
    };

    public List<Passenger> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM passengers ORDER BY id",
            rowMapper
        );
    }

    public Passenger findById(Long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM passengers WHERE id = ?",
            rowMapper,
            id
        );
    }

    public int save(Passenger passenger) {
        if (passenger.getId() == null) {
            return jdbcTemplate.update(
                "INSERT INTO passengers(name, age, gender, seat_number) VALUES(?, ?, ?, ?)",
                passenger.getName(),
                passenger.getAge(),
                passenger.getGender(),
                passenger.getSeatNumber()
            );
        } else {
            return jdbcTemplate.update(
                "UPDATE passengers SET name=?, age=?, gender=?, seat_number=? WHERE id=?",
                passenger.getName(),
                passenger.getAge(),
                passenger.getGender(),
                passenger.getSeatNumber(),
                passenger.getId()
            );
        }
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update(
            "DELETE FROM passengers WHERE id=?",
            id
        );
    }
}
