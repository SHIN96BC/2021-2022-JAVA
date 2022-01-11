class Cameraman {
	String tool;
	String gender;
	String shape;
	int career;

	Cameraman(){
		tool = "카메라"; //멤버, 참조, 객체, 변수
		gender = "남성";
	}

	Cameraman(String shape, int career) { //career: 파지, 기본, X, 변수 
		this.shape = shape;
		this.career = career;
		//this.career: 멤버, 기본, 객체, 변수 
	}


	void shooting() { //shoot 
		System.out.println("촬영한다.");
	}

	void work() {
		System.out.println("잘한다.");
	}
}



class Actor{
	String gender;
	String shape;
	String voice;
	int pay;
	Actor() {
		gender = "여성";
		pay = 10;
		shape = "예쁜";
		voice = "청량한";
	}
	Actor(String gender, String shape, int pay){
		this.gender = gender;
		this.shape = shape;
		this.pay = pay;
	}
	void action() { //act()
		System.out.println("연기를 한다.");
	}
	void get() {
		System.out.println("받는다.");
	}
	void work() {
		System.out.println("아주 잘한다.");
	}	
}


class Mk {
	String type;
	Mk(){
		type = "마이크";
	}
	Mk(String type){
		this.type = type;
	}
	void work(){
		System.out.println("로 소리를 녹음한다.");
	}
}




class Soundman {
	String gender;
	Mk mk1,mk2;

	Soundman() {
		gender = "여성";
	}

	Soundman(String gender) {
		this.gender = gender;
	}

	void recording () { //record()
		mk1 = new Mk("붐 마이크"); //mk1: 멤버, 참조, 객체, 변수  
		mk2 = new Mk("와이어레스 마이크");
		System.out.println(mk2.type + "를 사용해서 녹음하고 있다."); //type:멤버, 참조, 객체, 변수ㅜ
	}

	void mk_price() { //showPrice()
		System.out.println(mk1.type + "는 400만원이다.");
		System.out.println(mk2.type + "는 1000만원이다.");
	}


}

class Director {
	String shape1;
	String shape2;
	String special;
	
	Director() {
		shape1 = "배우보다 잘생기신";
		shape2 = "동안";
		special = "java코딩";
	}

	void coaching() { //coach()
		System.out.println("지도력 또한 최고이시다.");
	}

	void coding(){ //listen()
		System.out.println("가장 잘하신다는 소문이 있다.");
	}
}


class DramaMaking {
	public static void main(String[] args) { //args: 지역(파지), 참조, X, 변수
		Actor ac1 = new Actor(); //ac1: 지역(선초), 참조, X, 변수 
		System.out.print("성별이 " + ac1.gender + "이고, " + ac1.shape + " 배우가 "); //gender:멤버, 참조, 객체, 변수
		ac1.action();
		System.out.print("그 " + ac1.shape + " 배우는 페이로 " + ac1.pay + "억원을 ");
		ac1.get();
		
		Cameraman cm1 = new Cameraman();
		System.out.print("성별이 " + cm1.gender + "인 카메라맨이 " + cm1.tool + "를 사용해서 " + ac1.shape + " 배우의 연기를 ");
		cm1.shooting();//cm1: 지역(선초), 참조, X, 변수

		Soundman sm = new Soundman("여성");
		System.out.print("성별이 " + sm.gender + "인 음향담당자가 ");
		sm.recording();
		sm.mk_price();
		
		Cameraman cm2 = new Cameraman("난폭", 20);
		System.out.print("책임자를 맡고있는 카메라맨은 성격이 " + cm2.shape + "하지만, 경력이 " + cm2.career + "년 이어서 일을 참 ");
		cm2.work();
		
		Actor ac2 = new Actor("남성", "평범", 5);
		System.out.print("성별이 " + ac2.gender + "이고, " + ac2.shape + "한 배우는 페이로 " + ac2.pay + "억원을 ");
		ac2.get();
		System.out.print("이 배우는 얼굴은 " + ac2.shape + "하지만, 연기는 ");
		ac2.work();

		Director dr = new Director();
		System.out.print("마지막으로 " + dr.shape1 + " 김형수감독님께서는 " + dr.shape2 + "이시고, 유머러스하시면서, ");
		dr.coaching();
		System.out.print("들리는 소문으로, 김형수감독님께서는 "+ dr.special + "도 한국에서");
		dr.coding();
	}
}