package wiki.cwm.tiny.blog.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("wiki.cwm.tiny.blog.api.dao.mysql.mapper")
public class TinyBlogApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyBlogApiApplication.class, args);
	}

}
