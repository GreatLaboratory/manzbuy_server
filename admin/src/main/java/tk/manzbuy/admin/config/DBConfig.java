package tk.manzbuy.admin.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;


@Configuration
//이 페이지 자체가 설정이라는 걸 알려주기 위한 어노테이션
@EnableTransactionManagement
// transaction과 관련된 설정을 자동으로 해준다?
public class DBConfig implements TransactionManagementConfigurer {
	
	private String DriverClassName = "jdbc:mysql://manzbuy.cydn8qcpi5ax.ap-northeast-2.rds.amazonaws.com:3306/test?serverTimezone=Asia/Seoul&useSSL=false";
	
	private String username = "user";
	
	private String password = "cartopia95";
	
	@Bean
	// 아래의 bean은 메소드쓰는 방식처럼 작성한다.
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DriverClassName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	// 이 아래부터는 트랜젝션을 하게끔하는 코드이다. 사실상 위의 bean등록만 해도 상관없다. - 트랜젝션설정 안 할 경우
	
	
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// 사용자간의 트랜젝션 처리를 위한 PlatformTransactionManager메소드 
		// 이 메소드에선 트랜젝션을 처리할  PlatformTransactionManager객체를 반환해야함.
		return transactionManager();
	}
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}