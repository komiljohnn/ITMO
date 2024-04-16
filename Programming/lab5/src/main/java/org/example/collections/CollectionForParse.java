package org.example.collections;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;

@XmlRootElement(name = "Collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectionForParse {
    @XmlElement(name = "labWork")
    private LinkedList<LabWork> list = new LinkedList<>();

    public LinkedList<LabWork> getList() {
        return list;
    }

    public void setList(LinkedList<LabWork> list) {
        this.list = list;
    }

}