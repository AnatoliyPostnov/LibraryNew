package com.postnov.library.messageTest;

import com.postnov.library.LibraryApplication;
import com.postnov.library.jms.Consumer;
import com.postnov.library.jms.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryApplication.class)
public class messageTest {

    @Autowired
    Producer producer;

    @Autowired
    Consumer consumer;

    @Test
    public void sendMessageTest() throws Exception {
        producer.send();
        consumer.takeMessage();
    }
}
