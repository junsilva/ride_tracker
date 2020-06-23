package com.pluralsight.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pluralsight.model.Ride;

public class RideRowMapper implements RowMapper<Ride> {
	@Override
	public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ride rideResult = new Ride();
		
		rideResult.setId(rs.getInt("id"));
		rideResult.setName(rs.getString("name"));
		rideResult.setDuration(rs.getInt("duration"));
		
		return rideResult;
	}
}
