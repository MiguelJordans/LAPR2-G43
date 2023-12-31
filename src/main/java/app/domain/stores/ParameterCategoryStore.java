package app.domain.stores;

import app.domain.model.ParameterCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents an List of all the Category of Parameters in the system
 */
public class ParameterCategoryStore implements Serializable {
    private final List<ParameterCategory> array;
    private ParameterCategory pc;

    /**
     * Constructor of the class it creates an empty list to be filled with objects of ParameterCategory
     */
    public ParameterCategoryStore() {
        this.array = new ArrayList<>();
    }

    /**
     * This method creates a new ParameterCategory object by calling his constructor.
     *
     * @param code unique code needed to identify the Parameter Category
     * @param name short name that characterize the Parameter Category
     * @return Parameter Category object created
     */
    public ParameterCategory createParameterCategory(String code, String name) {

        this.pc = new ParameterCategory(code, name);
        return this.pc;
    }

    /**
     * this method checks if the ParameterCategory object received is not null, if don't already exists.in the ArrayList and if the ParameterCategory code don't already exist
     *
     * @param pc ParameterCategory object
     * @return boolean value that is true if the object is not null and don't already exists in the ArrayList
     */

    public boolean validateParameterCategory(ParameterCategory pc) {
        return pc != null && !contains(pc) && uniqueCode(pc);
    }

    /**
     * This method searches in the Array List if already exists a Parameter Category object with the same code
     *
     * @param pc Parameter Category object in which we want to check the code
     * @return true if the code don't already exists, false if not
     */

    public boolean uniqueCode(ParameterCategory pc) {
        boolean find = true;
        for (ParameterCategory pc1 : array) {
            if (pc.getCode().equals(pc1.getCode())) {
                find = false;
            }
        }
        return find;
    }

    /**
     * this method checks if the ParameterCategory object received already exits in the ArrayList
     *
     * @param pc ParameterCategory object
     * @return boolean value that is true if the object already exists in the ArrayList
     */

    public boolean contains(ParameterCategory pc) {
        return this.array.contains(pc);
    }

    /**
     * this method is used to save the ParameterCategory object in the arrayList already created, before adding the object the method validates it
     *
     * @return a boolean value that indicates the success of the operation
     */
    public boolean saveParameterCategory() {
        if (validateParameterCategory(this.pc)) {
            add(this.pc);
            return true;
        } else {
            return false;
        }
    }

    /**
     * this method adds the ParameterCategory object to the arrayList
     *
     * @param pc ParameterCategory object
     * @return a boolean value that indicates the success of the operation
     */

    public boolean add(ParameterCategory pc) {
        return array.add(pc);
    }

    /**
     * This method search for an Parameter Category object by the index of that object in the ArrayList
     *
     * @param index index of the array list where we want to get the object
     * @return the Parameter Category object that was in the index of the array list
     */

    public ParameterCategory get(int index) {
        return array.get(index);
    }

    /**
     * This method search for an Parameter Category object by the code of that object in the ArrayList
     *
     * @param code code that characterize the Parameter Category object
     * @return if the object is found it returns the object, if not it returns null
     */

    public ParameterCategory getByCode(String code) {
        for (ParameterCategory parameterCategory : array) {
            if (parameterCategory.getCode().equals(code)) {
                return parameterCategory;
            }
        }
        return null;
    }

    /**
     * @return the list of Parameters Category
     */
    public List<ParameterCategory> getList() {
        return this.array;
    }

    /**
     * Go through all the objects in the ArrayList and appends the String of the method toString to a new String creating a new line for object
     *
     * @return String with all the objects in the ArrayList
     */
    public String toString() {
        StringBuilder listString = new StringBuilder();

        for (ParameterCategory s : array) {
            listString.append(s.toString()).append("\n");
        }
        return String.valueOf(listString);
    }

    /**
     * @return Parameter Category object
     */
    public ParameterCategory getPc() {
        return pc;
    }

    /**
     * Checks if the ArrayList of Parameter Categories are empty
     *
     * @return a boolean value that represents if the ArrayList is empty
     */
    public boolean isEmpty() {
        return this.array.isEmpty();
    }

    public ParameterCategory getParameterCategoryExist(String parametercategory) {

        for (ParameterCategory parameterCategory : this.array) {
            if (parameterCategory.getName().equals(parametercategory)) {

                return parameterCategory;
            }
        }
        return null;
    }
}