package codereview.chatsys;

import java.sql.SQLException;

import lombok.extern.log4j.Log4j;

import org.sql2o.Sql2o;
import org.sql2o.quirks.PostgresQuirks;

import com.google.inject.Singleton;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Log4j
@Singleton
public class DBCP implements IDBCP{

	private final Sql2o sql2o;
	
	public DBCP() {
		String configPath = getClass().getClassLoader().getResource("").getPath() + "hikari.properties";
		sql2o = new Sql2o(new HikariDataSource(new HikariConfig(configPath)), new PostgresQuirks());
	}

	@Override
	public Sql2o getSql2o() throws SQLException {
		return sql2o;
	}
}
