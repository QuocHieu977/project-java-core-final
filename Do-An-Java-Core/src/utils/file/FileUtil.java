package utils.file;

import java.io.*;

public class FileUtil {
    public static Object readDataFile(String fileName) {
        try( ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
           return objectInputStream.readObject();
        } catch (IOException e) {
           e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeDataFile(Object obj, String fileName) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//    public static Object readDataFile(String filename, Map<String, List<Order>> orders)) {
//        try( ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
//            return objectInputStream.readObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void writeDataFile(Object obj, String fileName) {
//        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
//            objectOutputStream.writeObject(obj);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
