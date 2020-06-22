package com.pluralsight.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.pluralsight.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Ride> getRides() {
		List<Ride> rides = jdbcTemplate.query("select * from ride", new RowMapper<Ride>() {
			@Override
			public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ride rideResult = new Ride();
				
				rideResult.setId(rs.getInt("id"));
				rideResult.setName(rs.getString("name"));
				rideResult.setDuration(rs.getInt("duration"));
				
				return rideResult;
			}
		});
		
		
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {
		jdbcTemplate.update("insert into ride (name,duration) values (?,?)", ride.getName(), ride.getDuration());
		
		/*SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		
		List<String> columns = new ArrayList<String>();
		
		columns.add("name");
		columns.add("duration");
		
		insert.setTableName("ride");
		insert.setColumnNames(columns);
		
		Map<String, Object> data = new HashMap<String,Object>();
		data.put("name",ride.getName());
		data.put("duration",ride.getDuration());
		
		insert.setGeneratedKeyName("id");
		
		Number key = insert.executeAndReturnKey(data);
		
		System.out.println(key);
		*/
		return null;
	}
	
}
