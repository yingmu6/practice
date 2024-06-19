//: annotations/database/Uniqueness.java
// Sample of nested annotations
package thinking.annotation.database;

public @interface Uniqueness {
  Constraints constraints()
    default @Constraints(unique=true);
} ///:~
