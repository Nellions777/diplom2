package student.diplom.web.models;

/**
 * Created by Евгений on 16.03.2016.
 */

// объекты этого класса будем посылать на клиент
public class SendParameter extends Parameter {

    // индексы элементов определённого интервала
    private int beginIndex;
    private int endIndex;

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}
