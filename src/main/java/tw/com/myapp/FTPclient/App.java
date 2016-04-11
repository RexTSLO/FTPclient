package tw.com.myapp.FTPclient;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.io.FileUtils;

public class App 
{
	private static void showServerReply(FTPClient client) {
        String[] replies = client.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
	
    public static void main( String[] args )
    {
        FTPClient client = new FTPClient();
        
        //String filename = "file.json";
        try{
        	client.connect("xxx");
        	client.enterLocalPassiveMode();
        	showServerReply(client);
        	int replyCode = client.getReplyCode();
        	if (!FTPReply.isPositiveCompletion(replyCode)) {
        		System.out.println("Operation failed. Server reply code: " + replyCode);
        		return;
        	}
        	
        	boolean success = client.login("xxx","xxx");
        	showServerReply(client);
        	if (!success) {
        		System.out.println("Could not login to the server");
        		return;
        	}
        	
        	//upload file
        	/*FileInputStream fis = new FileInputStream("file.json");
        	System.out.println("Start uploading first file");
        	boolean done = client.storeFile("/Rex/file.json", fis);
        	fis.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }*/
            
        	
        	
            //upload file 2
            /*fis = new FileInputStream("file2.json");
            System.out.println("Start uploading second file");
            OutputStream outputStream = client.storeFileStream("/Rex/tom.json");
            byte[] bytesIn = new byte[4096];
            int read = 0;
 
            while ((read = fis.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            fis.close();
            outputStream.close();
 
            boolean completed = client.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }*/
        	
        	
        	
        	//download file
        	/*OutputStream fos = new FileOutputStream("data");
        	System.out.println("Start downloading file");
        	boolean completed = client.retrieveFile("/Rex/tom.json", fos);
        	if (completed) {
                System.out.println("The file is downloaded successfully.");
            }*/
        	
        	
        	
        	//delete file
        	/*boolean deleted = client.deleteFile(filename);
            if (deleted) {
                System.out.println("File deleted...");
            }*/
        	
        	
        	
            //create directory
            /*String dirToCreate = "/Rex";
            success = client.makeDirectory(dirToCreate);
            showServerReply(client);
            if (success) {
                System.out.println("Successfully created directory: " + dirToCreate);
            } else {
                System.out.println("Failed to create directory. See server's reply.");
            }*/
        	
        	
        	
        	// get list file
        	client.changeWorkingDirectory("/Rex");
        	FTPFile[] ftpFiles = client.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                // Check if FTPFile is a regular file
                if (ftpFile.getType() == FTPFile.FILE_TYPE) {
                    System.out.println("FTPFile: " + ftpFile.getName() + "; " + FileUtils.byteCountToDisplaySize(ftpFile.getSize()));
                }
            }
            
        	client.logout();
        } catch(IOException e){
        	e.printStackTrace();
        } finally{
        	try{
        		client.disconnect();
        	} catch(IOException e){
        		e.printStackTrace();
        	}
        }
    }
}
