package codingInterview;

// This program prints the reverse of the string entered.
public class stringReverse {

	public String reverse(String S){
		String[] strarray=S.split("\\s");
		String reversestring = null;
		for(int i=(strarray.length)-1;i>=0;i--){
			if(i==(strarray.length)-1){
			 reversestring=strarray[i];
			}
			else{
				reversestring=reversestring.concat(" "+strarray[i]);
			}
		}
		return reversestring;
	}
	
	public static void main(String args[]){
		stringReverse sr=new stringReverse();
		String output=sr.reverse("HEllo this is a String which is given to Reverse and lets check it");
		System.out.println(output);
	}
}
