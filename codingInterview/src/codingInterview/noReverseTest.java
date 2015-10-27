package codingInterview;

import static org.junit.Assert.*;

import org.junit.Test;

public class noReverseTest {

	@Test
	public void test() {
		numberReverse testit=new numberReverse();
		int output=testit.printreverse(1456);
		assertEquals(6541,output);
	}

}
