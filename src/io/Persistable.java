package io;

public interface Persistable {
    String DELIMITER = ":";
    String SECTION_DELIMITER = System.lineSeparator() + "###" +
            System.lineSeparator();

    String LIST_DEIMITER = ", ";
    String COLUMN_DELIMITER = System.lineSeparator();

    String serialize();
    void applySerializedData(String serializedData);
}
