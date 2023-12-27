package Services;
import java.io.*;
import java.util.*;

import Models.Account;
import Models.AgentAccount;
import Models.PersonalAccount;
 
public class DataManage{
	public String  path ="datas.txt";
	public File myFile;
	public DataManage(){
		this.fData();
	}
	public void fData(){
		  myFile = new File(path);
		if(myFile.exists()){

		}else{
            
			try { 
				if (myFile.createNewFile()) {
							System.out.println("File created ");
						} else {
							System.out.println("File already exists.");
						}
					} catch (IOException e) { 
							System.out.println("error.");
					}
		}
	}
	public boolean accountExists(String phone){
		boolean isGot= false;
	 
      try (Scanner myReader = new Scanner(myFile)) {
		while (myReader.hasNextLine()) {
		    String data = myReader.nextLine();
			if(data.indexOf(phone) != -1){
			String number = data.split("\t")[1];
		    // System.out.println(number);
				if(number.equals(phone)){
					return true;
				}else{
					continue;
				} 
				
			}
		  }
		  myReader.close();
	}
     catch (FileNotFoundException e) { 
    	System.out.println("error");

    }


		return isGot;
	}
	public void addAccount(String name, String phoneNo,  String pin,  String type ,double balance){
		try{

           FileWriter fw=new FileWriter(myFile, true);    
           fw.write(name + "\t"  + phoneNo + "\t" + pin + "\t" + type + "\t" + balance +"\n");    
           fw.close(); 

          }catch(Exception e){
          	System.out.println(e);
          }
     }

     public Account getAccountByPhone(String phone){
		try { 
      Scanner myReader = new Scanner(myFile);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
		if(data.indexOf(phone) != -1){
			
        System.out.println(data);
		String name = data.split("\t")[0]; 

		String number = data.split("\t")[1]; 
		
		String pin = data.split("\t")[2];
		
		String accType = data.split("\t")[3]; 
		
		String accBalance = data.split("\t")[4]; 
			 
			if(number.equals(phone)){ 
				if(accType.equals("personal")){
					PersonalAccount account = new PersonalAccount();
					account.setName(name);
					account.setPhoneNumber(number);
					account.setPin(pin);
					account.setBalance(Double.parseDouble(accBalance));
					return account;
				}
				if(accType.equals("agent")){
					AgentAccount account = new AgentAccount();
					account.setName(name);
					account.setPhoneNumber(number);
					account.setPin(pin);
					account.setBalance(Double.parseDouble(accBalance));
					return account;
				}

			}else{
				continue;
			} 
			
		}
      }
      myReader.close();
    } catch (FileNotFoundException e) { 
    	System.out.println("error");

    }


        return new Account();
     }



}