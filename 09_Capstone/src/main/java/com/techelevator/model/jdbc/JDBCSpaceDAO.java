package com.techelevator.model.jdbc;

import com.techelevator.model.Space;
import com.techelevator.model.SpaceDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.math.BigDecimal;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCSpaceDAO implements SpaceDAO {

    private JdbcTemplate jdbcTemplate;

    public JDBCSpaceDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Space> getAllSpaces() {

        String sql = "SELECT id, venue_id, name, is_accessible, open_from, open_to, CAST (daily_rate AS decimal(32, 2)), max_occupancy FROM space";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);

        List<Space> spaces = new ArrayList<Space>();
        while(rows.next()) {
            Space space = mapRowToSpaces( rows );
            spaces.add( space );
        }
        return spaces;
    }

    @Override
    public List<Space> getSpacesByVenue(int venue_id) {

        String sql = "SELECT id, venue_id, name, is_accessible, open_from, open_to, CAST (daily_rate AS decimal(32, 2)), max_occupancy " +
                "FROM space " +
                "WHERE venue_id = ?";

        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, venue_id);

        List<Space> spaces = new ArrayList<Space>();
        while (rows.next()) {
            Space space = mapRowToSpaces( rows );
            spaces.add( space );
        }

        return spaces;
    }



    private Space mapRowToSpaces (SqlRowSet row) {
        Space space = new Space();

        space.setId(row.getInt("id"));
        space.setVenue_id(row.getInt("venue_id"));
        space.setName(row.getString("name"));
        space.setIs_accessible(row.getBoolean("is_accessible"));
        space.setOpen_to(row.getString("open_to"));
        space.setDaily_rate(row.getBigDecimal("daily_rate"));
        space.setMax_occupancy(row.getLong("max_occupancy"));

        if(row.getString("open_from") == null) {
            space.setOpen_from("NA");
        } else {
            space.setOpen_from(row.getString("open_from"));
        }

        if(row.getString("open_to") == null) {
            space.setOpen_to("NA");
        } else {
            space.setOpen_to(row.getString("open_to"));
        }

        return space;
    }

}