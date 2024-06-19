//: enumerated/Competitor.java
// Switching one enum on another.
package thinking.enum_type;

public interface Competitor<T extends Competitor<T>> {
  Outcome compete(T competitor);
} ///:~
