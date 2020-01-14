import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

import com.revature.projects.Project0.dao.BoughtDAO;
import com.revature.projects.Project0.dao.CarDAO;
import com.revature.projects.Project0.dao.UserDAO;

public class Test {
	
	String userFile = "users.dat";
	private static UserDAO uDAO = new UserDAO();
	private static CarDAO cDAO = new CarDAO();
	private static BoughtDAO bDAO = new BoughtDAO();

	@org.junit.Test
	public void userFileTest() {
		Object users = null;
		try (FileInputStream fis = new FileInputStream(userFile);
				ObjectInputStream ois = new ObjectInputStream(fis)){
					users = ois.readObject();
				}
		catch (Exception e) {
			e.printStackTrace();
		}
		assertNotNull(users);
	}
	
	@org.junit.Test
	public void testUserDAO() {
		Object file = null;
		file = uDAO.read();
		assertNotNull(file);
	}
	
	@org.junit.Test
	public void testCarDAO() {
		Object file = null;
		file = cDAO.read();
		assertNotNull(file);
	}
	
	@org.junit.Test
	public void testBoughtDAO() {
		Object file = null;
		file = bDAO.read();
		assertNotNull(file);
	}	

}
