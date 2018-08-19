package com.jpd.FileTransfer;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.jpd.FileTransfer.service.EmailService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledTasks {
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
    private FTPUploader ftpUploader;
	
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    String server = "www.logicons.com";
    int port = 21;
    String user = "d2z";
    String pass = "D2z@Freipost";
   
    @Scheduled(cron = "0/1 0 * * * *")
    public void scheduleTaskWithFixedRate() {
      logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
      InputStream is = null;
      OutputStream os = null;
      List<String> fileList = new ArrayList<String>();
      String desFileCopytDir = "/home/jpduser/Country_Transfered/";
      //String desFileCopytDir = "/Users/rahameda/Desktop/Mani/rafik2/";
      File file = new File("/home/jpduser/Country/");
      //File file = new File("/Users/rahameda/Desktop/Mani/rafik1/");
      File[] files = file.listFiles();
      for(File f: files){
      	System.out.println("File Name"+f.getName());
      	fileList.add(f.getName());
        System.out.println(f.getPath());
        String hostDir = "/manifests/FDM/";
	        if(f.getName() != null) {
	        	 try {
	                	ftpUploader.FTPUploaderVal(server, user, pass);
	                	ftpUploader.uploadFile(f.getPath(), f.getName(), hostDir);
	                	ftpUploader.disconnect();
	                   try {
	                       is = new FileInputStream(f.getPath());
	                       String destDirc= desFileCopytDir + f.getName();
	                       os = new FileOutputStream(destDirc);
	                       byte[] buffer = new byte[1024];
	                       int length;
	                       while ((length = is.read(buffer)) > 0) {
	                           os.write(buffer, 0, length);
	                       }
	                   } finally {
	                   		os.close();
	                        is.close();
	                   }
	                   
	                   File SrcFile = new File(f.getPath());
	                   
		           		if(SrcFile.delete()){
		           			System.out.println(SrcFile.getName() + " is deleted!");
		           		}else{
		           			System.out.println("Delete operation is failed.");
		           		}
	                   
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	        }else {
	        	logger.info("No Files Found under Country folder");
	        }
      	}
      
      	if(files.length > 0) {
      		 emailService.sendSimpleMessage(fileList);
      	}
    }
    
    public void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        try {
			System.out.println(ftpClient.printWorkingDirectory());
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
    
    public void printFileDetails(FTPFile[] files) {}
 
    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
}
