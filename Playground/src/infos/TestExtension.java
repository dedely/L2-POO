package infos;

public class TestExtension {

	public static void main(String[] args) {
		String test = "test.txt";
		Extension testClass = new Extension();
		System.out.println(testClass.getExtensionUsingApacheCommonLib(test));
		System.out.println(testClass.getMimeUsingTika(test));
	}
}
