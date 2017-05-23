package org.ack.admin.web.template;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ZTreeNodeTest {

	@Test
	public void testJson() throws JsonProcessingException {
		ZTreeNode node = new ZTreeNode();
		node.setId(1);
		node.setIsParent(true);
		node.setName("用户管理");
		node.setPId(0);
		node.setValue("user:*");
		
		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(node);
		System.out.println(s);
		
	}
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
