package com.accenture.lkm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EurekaProducer.class)
public class EurekaProducerTest {
	@Test
	public void whenSpringContextIsBootstrapped_thenNoExceptions() {
	}
}
