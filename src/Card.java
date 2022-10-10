public class Card {

    private String name;


    public Card(String name){
        this.name = name;
    }
    public String returnName(){
        return this.name;
    }
    public int returnValue(){


        switch (this.name) {
            case "jack", "queen", "king" -> {
                return 10;
            }
            case "ace" -> {
                return 11;
            }
            case "lowAce" -> {
                return 1;
            }
        }
        if (Integer.parseInt(this.name) <= 10) {
            return Integer.parseInt(this.name);
        }
        return 0;
    }

    @Override
    public String toString(){
        return ("Card is: " + returnValue() + " and it is a " + this.name);
    }

}
