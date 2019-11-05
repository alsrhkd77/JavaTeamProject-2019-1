package dataSet;

public class IngredientBuilder implements Builder {
    private String name;
    private String keep;
    private String parentCartegory;
    private String subCartegory;

    public IngredientBuilder(){
        name = " ";
        keep = " ";
        parentCartegory = " ";
        subCartegory = " ";
    }

    public void name(String name){
        this.name = name;
    }

    public void keep(String keep){
        this.keep = keep;
    }

    public void parentCartegory(String parentCartegory){
        this.parentCartegory = parentCartegory;
    }

    public void subCartegory(String subCartegory){
        this.subCartegory = subCartegory;
    }

    public Ingredient build() {
        return new Ingredient(name, keep, parentCartegory, subCartegory);
    }
}
