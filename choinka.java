public class Choinka {
	public static void main (String[] args){
		int x;
		int n;
		x = 0;
		n = 10;
		while ( x <= 10 ){

			for(int j = 0; j < n; j++){
					System.out.print(" ");
				}

			for(int i = 0; i < x; i++){
				System.out.print("*");
			}

			for(int i = 1; i < x; i++){
				System.out.print("*");
			}

			System.out.print("\n");
			x++;
			if(n > 0 ){
				n--;
			}
		}
	}
}

