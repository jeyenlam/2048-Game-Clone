public class Tile {
    int val;

    //default constructor
    public Tile() {
        val = 2;
    }

    //constructor
    public Tile(int val){
        this.val = val;
    }

    //setters & getters
    public void setTile(int val){
        this.val = val;
    }

    public int getTile(){
        return val;
    }

    //recursive
    public static boolean power2(double value){
        //base case
        if (value ==2){
            return true;
        }
        else if (value > 2){
            return power2(value/2);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public String toString(){
        return String.valueOf(val);
    }



    public static void main(String[] args){
        System.out.println(power2(5));
    }

}
