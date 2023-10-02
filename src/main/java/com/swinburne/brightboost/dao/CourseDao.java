package com.swinburne.brightboost.dao;

import com.swinburne.brightboost.domain.Course;
import com.swinburne.brightboost.domain.User;
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
public class CourseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Course> findAll() {
        return jdbcTemplate.query("select * from course", new CourseRowMapper());
    }

    public Course findById(Long id) {
        String sql = "select * from course where id = ?";
        Course course = jdbcTemplate.queryForObject(sql, new CourseRowMapper(), id);
        return course;
    }
    public Integer save(Course course) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (course == null)
            return 0;
        String sql;
        if (course.getId() != null) {
            sql = "update course set course_name = ?, created_time=? where id=?";
            return jdbcTemplate.update(sql, new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, course.getCourseName());
                    ps.setString(2, formatter.format(new Date().getTime()));
                    ps.setLong(3, course.getId());
                }
            });
        } else {
            sql = "insert into course(course_name, created_time) "
                    + "values(?,?)";
            return jdbcTemplate.update(sql, course.getCourseName(), formatter.format(new Date().getTime()));
        }
    }

    public Integer deleteById(Long id) {
        String sql = "delete from course where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    class CourseRowMapper implements RowMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getLong("id"));
            course.setCourseName(rs.getString("course_name"));
            course.setCreatedTime(rs.getString("created_time"));

            return course;
        }
    }
}
