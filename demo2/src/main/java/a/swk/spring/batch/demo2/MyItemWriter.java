package a.swk.spring.batch.demo2;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

public class MyItemWriter implements ItemWriter<String> {
    @Override
    public void write(Chunk<? extends String> chunk) throws Exception {

        for (String item : chunk.getItems()) {
            System.out.println("Hello World, " + item);
        }

    }
}
