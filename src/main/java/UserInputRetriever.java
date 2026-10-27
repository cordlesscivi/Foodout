// Create UserInputRetriever interface here
public interface UserInputRetriever<T> {
    T produceOutput(int selection) throws IllegalArgumentException;

}