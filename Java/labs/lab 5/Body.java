package lab5;
import java.util.*;

interface Body<T> extends Comparable<T>, Comparator<T>, Iterable<Object>, Iterator<Object> {
    public double area();
    public double volume();
}