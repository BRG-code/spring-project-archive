package org.zerock.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {
	  @Test
	  public void testConnection() {
	    try {
	    	Connection con = DriverManager.getConnection("jdbc:log4jdbc:mysql://52.141.2.25:3306/imfsystem", "imfsystem","imfsystem1234");
	    	log.info(con);
	    } catch (Exception e) {
	    	fail(e.getMessage());
	    }
	  }
}
