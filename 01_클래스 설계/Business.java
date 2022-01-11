class Market {
	String buyPrize;

	Market(String buyPrize){
		this.buyPrize = buyPrize;
		//this.buyPrize: 멤버, 참조, 객체, 변수 
        //buyPrize: 지역(파지), 참조, X, 변수
	}
	void thank(){
		System.out.println("감사합니다. 또 오세요.\n");
	}
	
}

class Clerk{   // 점원
	String name, prize1, prize2, prize3,prize4, prize5, prize6;
	int change, coffeePrice, cigaPrice, ricePrice, kimchiPrice, whiskyPrice, winePrice, selesVolume, seles;

	Clerk(){
		coffeePrice = 2900;
		cigaPrice = 4500;
		ricePrice = 2000;
		kimchiPrice = 1300;
		whiskyPrice = 300000;
		winePrice = 150000;
		
		prize1 = "커피";
		prize2 = "담배";
		prize3 = "김밥";
		prize4 = "김치";
		prize5 = "양주";
		prize6 = "와인";
	}
	Clerk(String name){
		this.name = name; 
	}
	void name(){
		System.out.println("점원의 이름은 " + name + " 입니다.\n");
	}
	void sellCoffee(){
		System.out.println("손님에게 " + prize1 + "을/를 판매했습니다.");
		System.out.println(prize1 + "의 가격은 " + coffeePrice + "원 입니다.");
		selesVolume++;
		seles += coffeePrice;
	}
	void sellCiga(){
		System.out.println("손님에게 " + prize2 + "을/를 판매했습니다.");
		System.out.println(prize2 + "의 가격은 " + cigaPrice + "원 입니다.");
		selesVolume++;
		seles += cigaPrice;
	}
	void sellRice(){
		System.out.println("손님에게 " + prize3 + "을/를 판매했습니다.");
		System.out.println(prize3 + "의 가격은 " + ricePrice + "원 입니다.");
		selesVolume++;
		seles += ricePrice;
	}
	void sellKimchi(){
		System.out.println("손님에게 " + prize4 + "을/를 판매했습니다.");
		System.out.println(prize4 + "의 가격은 " + kimchiPrice + "원 입니다.");
		selesVolume++;
		seles += kimchiPrice;
	}
	void sellWhisky(){
		System.out.println("손님에게 " + prize5 + "을/를 판매했습니다.");
		System.out.println(prize5 + "의 가격은 " + whiskyPrice + "원 입니다.");
		selesVolume++;
		seles += whiskyPrice;
	}
	void sellWine(){
		System.out.println("손님에게 " + prize6 + "을/를 판매했습니다.");
		System.out.println(prize6 + "의 가격은 " + winePrice + "원 입니다.");
		selesVolume++;
		seles += winePrice;
	}
	void todaySeles(){
		System.out.println("오늘의 총 판매 개수는 " + selesVolume + "개 입니다.");
		System.out.println("오늘의 총 판매액은 " + seles + "원 입니다.\n");
	}
}

class Customer {  // 손님
	String moneyC, name;
	Market bp1, bp2, bp3, bp4, bp5, bp6;

	Customer(String moneyC, String name){
		this.moneyC = moneyC;
		this.name = name;
	}
	void buyCoffee(){
		bp1 = new Market("커피"); //bp1: 멤버, 참조, 객체, 변수 
		System.out.println(name + "이/가 " + moneyC + "을/를 지불하고 " + bp1.buyPrize + "을/를 구입 하셨습니다.");
	}
	void buyCiga(){
		bp2 = new Market("담배");
		System.out.println(name + "이/가 " + moneyC + "을/를 지불하고 " + bp2.buyPrize + "을/를 구입 하셨습니다.");
	}
	void buyRice(){
		bp3 = new Market("김밥");
		System.out.println(name + "이/가 " + moneyC + "을/를 지불하고 " + bp3.buyPrize + "을/를 구입 하셨습니다.");
	}
	void buyKimchi(){
		bp4 = new Market("김치");
		System.out.println(name + "이/가 " + moneyC + "을/를 지불하고 " + bp4.buyPrize + "을/를 구입 하셨습니다.");
	}
	void buyWhisky(){
		bp5 = new Market("양주");
		System.out.println(name + "이/가 " + moneyC + "을/를 지불하고 " + bp5.buyPrize + "을/를 구입 하셨습니다.");
	}
	void buyWine(){
		bp6 = new Market("와인");
		System.out.println(name + "이/가 " + moneyC + "을/를 지불하고 " + bp6.buyPrize + "을/를 구입 하셨습니다.");
	}
}

class MarketOwner{
	int moneyO = 3000000;
	int sales;
	
	MarketOwner(int sales){
		this.sales = sales;
	}
	void deposit(){
		System.out.println("점주의 재산" + moneyO + "원");
		System.out.println("점주 재산 +" + sales + "원\n");
		moneyO += sales; //moneyO: 멤버, 기본, 객체, 변수
		System.out.println("점주의 총 재산 : " + moneyO + "원");
	}
}

class Business {              // 거래
	public static void main(String[] args){
		Clerk ckName = new Clerk("길동");
		ckName.name();        // 점원의 이름

		Customer cr1 = new Customer("현금","손님1");
		cr1.buyCoffee();      // 커피 구입
		Clerk ck = new Clerk();
		ck.sellCoffee();      // 커피 판매
		cr1.bp1.thank();      // 인사
		
		Customer cr2 = new Customer("현금", "손님2");
		cr2.buyCiga();        // 담배 구입
		ck.sellCiga();        // 담배 판매
		cr2.bp2.thank();      // 인사

		Customer cr3 = new Customer("체크카드", "손님3");
		cr3.buyRice();        // 밥 구입
		ck.sellRice();		  // 밥 판매
		cr3.bp3.thank();      // 인사

		Customer cr4 = new Customer("신용카드", "손님4");
		cr4.buyKimchi();      // 김치 구입
		ck.sellKimchi();      // 김치 판매
		cr4.bp4.thank();      // 인사

		Customer cr5 = new Customer("현금", "손님5");
		cr5.buyWhisky();      // 양주 구입
		ck.sellWhisky();      // 양주 판매
		cr5.buyWine();        // 와인 구입
		ck.sellWine();        // 와인 판매
		cr5.buyCiga();        // 담배 구입
		ck.sellCiga();        // 담배 판매
		cr5.bp5.thank();      // 인사

		ck.todaySeles();      // 오늘의 판매실적

		MarketOwner mo = new MarketOwner(465200);    // 점주의 재산에 오늘의 매상 추가
		mo.deposit();         // 점주의 재산
	}
}