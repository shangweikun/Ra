package a.swk.spring.batch.demo2;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyItemReader implements ItemReader<String> {
    @Override
    public String read() throws UnexpectedInputException,
            ParseException,
            NonTransientResourceException {
        return "123";
    }
}
