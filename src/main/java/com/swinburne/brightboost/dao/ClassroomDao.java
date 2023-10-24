package com.swinburne.brightboost.dao;


import com.swinburne.brightboost.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ClassroomDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Classroom> findClassroomByClassId(Long classId) {
		String sql = "select c.message_id as messageId, c.teacher_course_id as teacherCourseId, c.student_id as studentId, c.question_id as questionId, c.teacher_id as teacherId, c.message_text as messageText," +
				"c.message_type as messageType, c.created_time as createdTime, tc.start_time as startTime, tc.end_time as endTime, t.id as teacherId, t.first_name as teacherFirstName, t.last_name as teacherLastName, " +
				"s.first_name as studentFirstName, s.last_name as studentLastName " +
				"from classroom c left join teacher_course tc on c.teacher_course_id = tc.id " +
				"left join student s on c.student_id = s.id " +
				"left join teacher t on c.teacher_id = t.id " +
				"where tc.id = ? order by c.created_time desc";
		List<Classroom> cList = jdbcTemplate.query(sql, new ClassroomRowMapper(), classId);
		return cList;
	}

	public Integer save(Classroom c) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (c == null)
			return 0;
		String sql;
		if (c.getMessageId() != null) {
			sql = "update student_course SET teacher_course_id=?, student_id=?,"
					+ "teacher_id=?, message_text=?, message_type=?, question_id=?, created_time=? " + "WHERE id = ?";
			return jdbcTemplate.update(sql, new PreparedStatementSetter() {
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setLong(1, c.getTeacherCourseId());
					ps.setLong(2, c.getStudentId());
					ps.setLong(3, c.getTeacherId());
					ps.setString(4,c.getMessageText());
					ps.setString(5,c.getMessageType());
					ps.setLong(6, c.getQuestionId());
					ps.setString(7, formatter.format(new Date().getTime()));
					ps.setLong(8, c.getMessageId());
				}
			});
		} else {
			if(c.getQuestionId()==null){
				sql = "insert into classroom(teacher_course_id , student_id , teacher_id, message_text, message_type, question_id, created_time) "
						+ "values(?,?,?,?,?,LAST_INSERT_ID(),?)";
				return jdbcTemplate.update(sql, c.getTeacherCourseId(), c.getStudentId(), c.getTeacherId(),c.getMessageText(),
						c.getMessageType(), formatter.format(new Date().getTime()));
			}else{
				sql = "insert into classroom(teacher_course_id, student_id, teacher_id, message_text, message_type, question_id, created_time) "
						+ "values(?,?,?,?,?,?,?)";
				return jdbcTemplate.update(sql, c.getTeacherCourseId(), c.getStudentId(), c.getTeacherId(),c.getMessageText(),
						c.getMessageType(),c.getQuestionId(), formatter.format(new Date().getTime()));
			}

		}
	}

	class ClassroomRowMapper implements RowMapper<Classroom> {
		@Override
		public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classroom c = new Classroom();
			c.setMessageId(rs.getLong("messageId"));
			c.setTeacherCourseId(rs.getLong("teacherCourseId"));
			c.setQuestionId(rs.getLong("questionId"));
			c.setStudentId(rs.getLong("studentId"));
			c.setTeacherId(rs.getLong("teacherId"));
			c.setMessageText(rs.getString("messageText"));
			c.setMessageType(rs.getString("messageType"));
			c.setCreatedTime(rs.getString("createdTime"));
			TeacherCourse teacherCourse = new TeacherCourse();
			teacherCourse.setStartTime(rs.getString("startTime"));
			teacherCourse.setEndTime(rs.getString("endTime"));
			c.setTeacherCourse(teacherCourse);
			Teacher teacher = new Teacher();
			teacher.setId(rs.getLong("teacherId"));
			teacher.setFirstName(rs.getString("teacherFirstName"));
			teacher.setLastName(rs.getString("teacherLastName"));
			c.setTeacher(teacher);
			Student student = new Student();
			student.setId(rs.getLong("studentId"));
			student.setFirstName(rs.getString("studentFirstName"));
			student.setLastName(rs.getString("studentLastName"));
			c.setStudent(student);

			return c;
		}
	}
}
