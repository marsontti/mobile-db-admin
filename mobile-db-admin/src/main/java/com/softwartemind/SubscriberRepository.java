package com.softwartemind;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SubscriberRepository(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Subscriber> getSubscribers(String sortBy) {
        String sql = "SELECT * FROM Subscriber ORDER BY " + sortBy;
        List<Subscriber> subscribers = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Subscriber.class));

        return subscribers;
    }

    public Subscriber getSubscriberById(int subscriberId) {
        String sql = "SELECT * FROM Subscriber WHERE SubscriberId = " + subscriberId;
        Subscriber subscriber = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Subscriber.class)).get(0);

        return subscriber;
    }

    public void addSubscriber(Subscriber subscriber) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Subscriber").usingColumns("Name", "Surname", "IMSI", "PhoneNumber", "Provider");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(subscriber);
        insertActor.execute(param);
    }

    public void deleteSubscriber(int subscriberId) {
        String sqlDeleteSubs = "DELETE FROM Subscriber WHERE SubscriberId = ?";
        jdbcTemplate.update(sqlDeleteSubs, subscriberId);
    }

    public void updateSubscriber(Subscriber subscriber) {
        String sql = "UPDATE Subscriber SET Name=:name, Surname=:surname, IMSI=:imsi, PhoneNumber=:phoneNumber, Provider=:provider WHERE SubscriberId=:subscriberId" ;
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(subscriber);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }
}
