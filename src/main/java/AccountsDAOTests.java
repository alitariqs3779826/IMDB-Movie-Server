import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app.dao.AccountDAO;
import app.model.Account;

class AccountsDAOTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		
	}

	
	@Test
	void tesofusername() {
	
		assertEquals(null,AccountDAO.getDetailUsername(null));
	}
	
	@Test
	void testofpendingaccount2() {
	
		assertEquals(0,AccountDAO.getPendingAccounts());
	}
	
	@Test
	void test() {
	
		
	}
	

}
