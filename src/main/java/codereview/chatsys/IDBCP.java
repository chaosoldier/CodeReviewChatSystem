package codereview.chatsys;

import java.sql.SQLException;

import org.sql2o.Sql2o;

import com.google.inject.ImplementedBy;
import com.zaxxer.hikari.HikariDataSource;

@ImplementedBy(DBCP.class)
public interface IDBCP {

	public Sql2o getSql2o() throws SQLException;
}
