package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import next.model.Answer;
import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class AnswerDao {

	public void insert(Answer answer) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)";
		String answerSql = "UPDATE QUESTIONS SET countOfComment = countOfComment + 1 where questionId = ?";
		jdbcTemplate.update(sql, answer.getWriter(),
				answer.getContents(),
				new Timestamp(answer.getTimeFromCreateDate()),
				answer.getQuestionId());
		jdbcTemplate.update(answerSql, answer.getQuestionId());
	}
	
	public void remove(long questionId, String author) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "DELETE FROM ANSWERS WHERE questionId = ? and writer = ?";
		jdbcTemplate.update(sql, questionId, author);
		String delSql = "UPDATE QUESTIONS SET countOfComment = countOfComment - 1 where questionId = ?";
		jdbcTemplate.update(delSql, questionId);
	}

	public List<Answer> findAllByQuestionId(long questionId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "SELECT answerId, writer, contents, createdDate FROM ANSWERS WHERE questionId = ? "
				+ "order by answerId desc";
		
		RowMapper<Answer> rm = new RowMapper<Answer>() {
			@Override
			public Answer mapRow(ResultSet rs) throws SQLException {
				return new Answer(
						rs.getLong("answerId"),
						rs.getString("writer"), 
						rs.getString("contents"),
						rs.getTimestamp("createdDate"), 
						questionId);
			}
		};
		
		return jdbcTemplate.query(sql, rm, questionId);
	}
}
