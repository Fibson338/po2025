public class CodingBat{

    public int diff21(int n) {
        if(n <= 21){
            return(21-n);
        }
        else
            return (n-21)*2;

    }

    public boolean nearHundred(int n) {
        return (Math.abs(100 - n) <= 10 || Math.abs(200 - n) <= 10);
    }

    public String helloName(String name) {
        return "Hello " + name + "!";
    }

    public boolean lucky13(int[] nums) {
        for ( int i=0; i < nums.length; i++){
            if (nums[i] == 1 || nums[i] == 3 ){
                return false;
            }
        }
        return true;
    }
}

