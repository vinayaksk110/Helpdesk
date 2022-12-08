package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class CleanupBrowserDrivers {

	public static void main(String[] args) {
		
		File file = new File("C:\\Users\\Samanth\\Desktop\\NewTextDocument.bat");
	    try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
