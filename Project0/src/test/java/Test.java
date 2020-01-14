import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.revature.projects.Project0.dao.UserDAO;

public class Test {
	
	String userFile = "users.dat";
	private static UserDAO uDAO = new UserDAO();

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
		file = uDAO.readUsers();
		assertNotNull(file);
	}
	

}
