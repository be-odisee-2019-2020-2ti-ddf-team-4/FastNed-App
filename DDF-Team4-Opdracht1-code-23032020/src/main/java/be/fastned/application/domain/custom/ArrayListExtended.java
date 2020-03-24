package be.fastned.application.domain.custom;
import java.util.ArrayList;

/**
 * Extends upon the ArrayList class by adding a removed item to the corresponding archive ArrayList.
 * @param <T1> Type of the item being removed from an ArrayList.
 * @param <T2> Type of the Class containing this ArrayList.
 */
public class ArrayListExtended<T1, T2> extends ArrayList<Object> {
    /**
     * TODO: Replace inheritance by interface implementation
     * TODO: Try extending the override rather than wrapping it. (now there are 2 methods...)
     */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Technische-Variabelen //----------------// */
    private Object m_ToBeRemoved;
    private Object m_ListOwner;
    private boolean m_IsStatic;
    public String Caller;

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /**
     * Default Constructor voor deze klasse. */
    public ArrayListExtended(){
        super();
        Caller = KDebug.getCallerClassName();
    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Technische-Functies //----------------// */
    public boolean removeWrapped(Object toBeRemoved, Object listOwner, boolean isStatic){
        // Caching List item and List owner.
        m_ToBeRemoved = toBeRemoved;
        m_ListOwner = listOwner;
        m_IsStatic = isStatic;

        // Chaining original overridden method to return.
        return remove(m_ToBeRemoved);
    }

    @Override public boolean remove(Object toBeRemoved) {
        // Execute the custom functionality this extension is meant to provide.
        ExecuteCustomFunctionality();

        // Resume extended-upon super execution. (Encapsulated by Wrapper)
        return super.remove(toBeRemoved);
    }

    private void ExecuteCustomFunctionality(){
        // Casting: Cast toBeRemoved (Object) to the T1 arraylist-item Type.
        T1 toBeRemovedCasted = (T1)m_ToBeRemoved;
        Class targetClass = toBeRemovedCasted.getClass();

        // Casting: Cast listOwner (Object) to the T2 arraylist-owner Type.
        T2 castedParentObj = (T2)m_ListOwner;
        Class parentClass = castedParentObj.getClass();

        // Interpolating parent and item class name into target archive-list.
        String prefix = (m_IsStatic) ? "s_" : "";
        String collectionName = String.format("%aArchived%b%c",prefix, parentClass.getSimpleName(),targetClass.getSimpleName());

        // Extract and update target archive-ist containing archived items using Reflection with interpolation result.
        try {
            ArrayList<T1> targetList = (ArrayList<T1>)castedParentObj.getClass().getDeclaredField(collectionName).get(castedParentObj);
            targetList.add(toBeRemovedCasted);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// PROPERTY: Caller //----------------// */
    /**
     * Deze domein-attribuut getter vertegenwoordigt de Caller. */
    public String getCaller(){
        return this.Caller;
    }
}
