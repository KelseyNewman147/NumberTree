package com.kelsey.NumberTree;

import com.kelsey.NumberTree.entities.Factory;
import com.kelsey.NumberTree.services.ChildNodeRepository;
import com.kelsey.NumberTree.services.FactoryRepository;
import com.kelsey.NumberTree.services.RootNodeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberTreeApplicationTests {

	@Autowired
	RootNodeRepository rootNodeRepo;

	@Autowired
	FactoryRepository factories;

	@Autowired
	ChildNodeRepository childNodes;

	@Autowired
	WebApplicationContext wap;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
	public void addFactory() throws Exception{
		Factory factory = new Factory();
		factory.setName("Hunter");
		factory.setRangleLow(12);
		factory.setRangeHigh(42);
	}

}
