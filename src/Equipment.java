public class Equipment extends Items {
    private String function;

    public Equipment(String name, int quantity, String function) {
        super(name, "Equipment", quantity);
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
