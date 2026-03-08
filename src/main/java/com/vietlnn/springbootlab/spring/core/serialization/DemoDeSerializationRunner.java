package com.vietlnn.springbootlab.spring.core.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DemoDeSerializationRunner {

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    String fileName = "fileSerialization.txt";
    DemoDeSerialization demoDeSerialization =
        new DemoDeSerialization("DeSerialization", 10, 20, 30, 40);

    // serialize object into a file -> convert object to ByteStream -> write into the file
    FileOutputStream file = new FileOutputStream(fileName);
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);

    System.out.println("Object before serializing: " + demoDeSerialization);
    objectOutputStream.writeObject(demoDeSerialization);

    objectOutputStream.close();
    file.close();
    System.out.println("Object has been serialized");

    // de-serialize file data back to object
    FileInputStream fileInputStream = new FileInputStream(fileName);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

    DemoDeSerialization deserializedObject = (DemoDeSerialization) objectInputStream.readObject();
    objectInputStream.close();
    fileInputStream.close();

    System.out.println("Object has been de-serialized!");
    System.out.println("Object after de-serializing: " + deserializedObject);
  }
}
