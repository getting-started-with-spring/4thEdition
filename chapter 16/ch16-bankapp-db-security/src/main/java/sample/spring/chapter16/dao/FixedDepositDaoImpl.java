package sample.spring.chapter16.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sample.spring.chapter16.domain.FixedDepositDetails;

@Repository
public class FixedDepositDaoImpl implements FixedDepositDao {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public List<FixedDepositDetails> getFixedDeposits(String user) {
		final String sql = "select * from fixed_deposit_details where user = :username";
		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"username", user);
		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new ResultSetExtractor<List<FixedDepositDetails>>() {
					List<FixedDepositDetails> fds = new ArrayList<FixedDepositDetails>();

					@Override
					public List<FixedDepositDetails> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						while (rs.next()) {
							FixedDepositDetails fd = new FixedDepositDetails();
							fd.setCustomerId(rs.getString("user"));
							fd.setId(rs.getLong("id"));
							fd.setDepositAmount(rs.getString("amount"));
							fd.setEmail(rs.getString("email"));
							fd.setTenure(rs.getString("tenure"));
							fds.add(fd);
						}
						return fds;
					}
				});
	}

	@Override
	public List<FixedDepositDetails> getAllFixedDeposits() {
		final String sql = "select * from fixed_deposit_details";
		return namedParameterJdbcTemplate.query(sql,
				new ResultSetExtractor<List<FixedDepositDetails>>() {
					List<FixedDepositDetails> fds = new ArrayList<FixedDepositDetails>();

					@Override
					public List<FixedDepositDetails> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						while (rs.next()) {
							FixedDepositDetails fd = new FixedDepositDetails();
							fd.setCustomerId(rs.getString("user"));
							fd.setId(rs.getLong("id"));
							fd.setDepositAmount(rs.getString("amount"));
							fd.setEmail(rs.getString("email"));
							fd.setTenure(rs.getString("tenure"));
							fds.add(fd);
						}
						return fds;
					}
				});
	}

	@Override
	public void saveFixedDeposit(FixedDepositDetails fixedDepositDetails) {
		final String sql = "insert into fixed_deposit_details(user, amount, tenure, email) "
				+ "values(:user, :amount, :tenure, :email)";
		Map<String, String> namedParams = new HashMap<String, String>();
		namedParams.put("user", fixedDepositDetails.getCustomerId());
		namedParams.put("amount", fixedDepositDetails.getDepositAmount());
		namedParams.put("tenure", fixedDepositDetails.getTenure());
		namedParams.put("email", fixedDepositDetails.getEmail());

		SqlParameterSource sqlParams = new MapSqlParameterSource(namedParams);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, sqlParams, keyHolder);
		
		fixedDepositDetails.setId(keyHolder.getKey().longValue());
	}

	public void closeFixedDeposit(int fixedDepositId) {
		final String sql = "delete from fixed_deposit_details where id = :fixedDepositId";

		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"fixedDepositId", fixedDepositId);

		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	public FixedDepositDetails getFixedDeposit(int fixedDepositId) {
		final String sql = "select * from fixed_deposit_details where id = :fixedDepositId";

		SqlParameterSource namedParameters = new MapSqlParameterSource(
				"fixedDepositId", fixedDepositId);

		return namedParameterJdbcTemplate.query(sql, namedParameters,
				new ResultSetExtractor<FixedDepositDetails>() {
					FixedDepositDetails fd = new FixedDepositDetails();

					@Override
					public FixedDepositDetails extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						while (rs.next()) {
							fd.setCustomerId(rs.getString("user"));
							fd.setId(rs.getLong("id"));
							fd.setDepositAmount(rs.getString("amount"));
							fd.setEmail(rs.getString("email"));
							fd.setTenure(rs.getString("tenure"));
						}
						return fd;
					}
				});
	}

	public void editFixedDeposit(FixedDepositDetails modifiedFixedDepositDetails) {

	}
}