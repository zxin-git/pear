package util.test;

public class Test {
public static void main(String[] args) {
	System.out.println(GetStepNum(4));
}


public static int GetStepNum(int n)  {  
      if(n==1) return 1;  
      if(n==2) return 2;  
      return GetStepNum(n-1)+GetStepNum(n-2); 
} 

}
