package uk.linyi;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import uk.linyi.domain.User;

import javax.sql.DataSource;
import java.util.List;

public class SpringJdbcDemoApplication {

    private static JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        System.out.println("========== 演示开始 ==========");
        init();
        String sql1 = "SELECT * FROM t_user WHERE name = ?";
        User t_user = jdbcTemplate.queryForObject(sql1, new BeanPropertyRowMapper<>(User.class), "张三");
        System.out.println("3. 查询单个用户: " + t_user);


        String sql2 = "SELECT * FROM t_user";
        List<User> t_userList = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<>(User.class));
        System.out.println("4. 查询用户列表: " + t_userList);

        // 5. 删除数据
        jdbcTemplate.update("DELETE FROM t_user WHERE name = ?", "李四");

        // 6. 统计数量
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM t_user", Integer.class);
        System.out.println("5. 剩余用户数量: " + count);

        System.out.println("========== 演示结束 ==========");
    }

    private static void init() {
        jdbcTemplate = new JdbcTemplate(createDriverManagerDataSource());
        jdbcTemplate.execute("CREATE TABLE t_user (id IDENTITY PRIMARY KEY, name VARCHAR(20), age INT)");
        jdbcTemplate.update("INSERT INTO t_user (name, age) VALUES (?, ?)", "张三", 25);
        jdbcTemplate.update("INSERT INTO t_user (name, age) VALUES (?, ?)", "李四", 30);
    }

    private static DataSource createDriverManagerDataSource() {
        // 使用 DriverManagerDataSource，每次 getConnection() 都创建新连接
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
