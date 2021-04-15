package kr.or.ddit.util.depository;

public class FileUtil {
	
	//contentDispostion ==> form-data; name="file"; filename="brown.png"
	public static String getFileName(String contentDisposition){
		
		/*
		 String contentDisposition = " form-data; name="file"; filename="brown.png" ";

		 FileUtil.getFileName(contentDispositon)을 테스트 할수 있는 테스트 코드 작성
		 ==> brown.png
 
		 */
		
		String[] attrs = contentDisposition.split("; ");
		
		for(String attr : attrs) {
			if(attr.startsWith("filename=")) {

				// filename="brown.png"
				attr = attr.replace("filename=", "");
				
				//"brown.png"
				return attr.substring(1, attr.length()-1);
			}
		}
		return "";
	}
	
	
	
	public static String getFileExtension(String fileName) {
		
		//brown.png
		//==> arr[0]="brown" , arr[1]="png"
//		return fileName.split("\\.")[1];
	
		//brown 이렇게만 파일명일때 / -1일때 라고 한 이유는, 자바코드를 만든사람이 indexOf로 뭔갈 찾았을때 없으면 -1을 반환하라고 약속한거 
		if(fileName.indexOf(".") == -1) {
			return "";
		}
		
		return "." + fileName.substring(fileName.lastIndexOf(".")+1);
		
	}
	
		
}
