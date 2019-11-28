package infos;

public class TestFileInfos {

	public static void main(String[] args) {
		String test = "ok.d";
		FileInfos testClass = new FileInfos();
		System.out.println(testClass.getExtensionUsingApacheCommonLib(test));
		System.out.println(testClass.getMimeUsingTika(test));
		//Command 
	}
}
