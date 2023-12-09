package org.example;

import java.io.*;

public class FileManager {

    private static FileManager instance;
    private String filePath;

    private FileManager() {
        // Private constructor to prevent instantiation
    }

    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setUserData(UserData userData){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filePath))) {
            oos.writeObject(userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public UserData fileGetData() {
        UserData userData ;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.filePath))) {
            userData = (UserData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            userData =new UserData();
            userData.addUserIn(new IndividualUser("TANISHQ", 400));
            userData.addUserIn(new IndividualUser("SAMEER", 500));
            userData.addUserIn(new IndividualUser("VIRAT", 200));
            return userData;
        }
        return userData;
    }

    public void folderCheck(){

        String projectDirectory = System.getProperty("user.dir");



        String absoluteDataFolderPath = projectDirectory + File.separator + this.filePath;

        System.out.println(absoluteDataFolderPath);

        File folder = new File(absoluteDataFolderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
