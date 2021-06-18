package com.techelevator.model.jdbc;

import com.techelevator.model.Venue;
import com.techelevator.model.VenueDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCVenueDAO implements VenueDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCVenueDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Venue> getAllVenues() {

        String sql = "SELECT id, name, city_id, description FROM venue";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        List<Venue> venues = new ArrayList<Venue>();
        while(rows.next()) {
            Venue venue = mapRowToVenue( rows );
            venues.add( venue );
        }
        return venues;
    }


    private Venue mapRowToVenue (SqlRowSet row) {
        Venue venue = new Venue();

        venue.setId(row.getLong("id"));
        venue.setName(row.getString("name"));
        venue.setCity_id(row.getLong("city_id"));
        venue.setDescription(row.getString("description"));

        return venue;
    }

}
