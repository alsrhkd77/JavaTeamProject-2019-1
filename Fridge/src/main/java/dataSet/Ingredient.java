package dataSet;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String name;
    private String keep;
    private String parentCategory;
    private String subCategory;

    public Ingredient(){
        name = "";
        keep = "";
        parentCategory = "";
        subCategory = "";
    }

    public Ingredient(String name, String keep, String parentCategory, String subCategory){
        this.name = name;
        this.keep = keep;
        this.parentCategory = parentCategory;
        this.subCategory = subCategory;
    }

    public Ingredient getIngredient(){
        return this;
    }

    public String getKeep(){
        return keep;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return new String(name +"  " + keep + " / " + parentCategory + " / " + subCategory);
    }
}
