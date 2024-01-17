package a.swk.spring.batch.demo2;

import org.springframework.batch.item.ItemProcessor;

public class MyItemProcessor implements ItemProcessor<String,String> {
    @Override
    public String process(String item) throws Exception {
        return "Hello World, " + item;
    }
}
