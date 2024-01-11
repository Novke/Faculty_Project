package fon.mas.novica.Faculty.Project.validation;

public abstract class AbstractRules<T> {

    protected void abort(String text){
        throw new IllegalArgumentException(text);
    }

    abstract public void all(T t);
}
