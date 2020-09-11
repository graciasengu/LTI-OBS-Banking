package banking;

public class TransStore {

	private static String mcustomerID;
	private static int maccountno;
	private static int mrecipientaccountno;
	private static double mtransactionamt;
	private static String mlocation;
	private static String mttype;
	private static long mtransid;
	
	public static void set_customerID(String n){
		
		mcustomerID=n;
	}
	
	public static String get_customerID(){
		
		return mcustomerID;
	}
	
	public static void set_transid(long n){
		
		mtransid=n;
	}
	
	public static long get_transid(){
		
		return mtransid;
	}
	
	public static void set_accountno(int n){
			
		maccountno=n;
	}
	
	public static int get_accountno(){
		
		return maccountno;
	}
	
	public static void set_recipientaccountno(int n){
		
		mrecipientaccountno=n;
	}
	
	public static int get_recipientaccountno(){
		
		return mrecipientaccountno;
	}
		
	public static void set_transactionamt(double n){
		
		mtransactionamt=n;
	}
	
	public static double get_transactionamt(){
		
		return mtransactionamt;
	}
	
	public static void set_Location(String n){
		
		mlocation=n;
	}
	
	public static String get_Location(){
		
		return mlocation;
	}
	
	public static void set_transtype(String n){
		
		mttype=n;
	
	}
	
	public static String get_transtype(){
		
		return mttype;
	}
	
}
	
