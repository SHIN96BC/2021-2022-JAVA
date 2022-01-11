class E4 {
	void m1(){
		for(int i=1; i<5; i++){
			for(int j=1; j<=i; j++){
				System.out.print("* ");
			}
			System.out.println();
		}
		System.out.println();
	}
	void m2(){
		for(int i=1; i<5; i++){
			for(int j=3; j>=i; j--){
				System.out.print("  ");
			}
			for(int k=1; k<=i; k++){
				System.out.print("* ");
			}
			System.out.println();
		}
		System.out.println();
	}
	void m3(){
		for(int i=1; i<5; i++){
			for(int j=4; j>=i; j--){
				System.out.print("* ");
			}
			System.out.println();
		}
		System.out.println();
	}
	void m4(){
		for(int i=1; i<5; i++){
			for(int j=1; j<i; j++){
				System.out.print("  ");
			}
			for(int k=4; k>=i; k--){
				System.out.print("* ");
			}	
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) 
	{
		E4 e = new E4();
		e.m1();
		e.m2();
		e.m3();
		e.m4();
	}
}
