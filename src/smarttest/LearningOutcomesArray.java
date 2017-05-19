/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LearningOutcomesArray extends ArrayList<LearningOutcome> implements java.io.Serializable {

    ArrayList<LearningOutcome> loArray;

    public LearningOutcomesArray() {
        loArray = new ArrayList<>();
    }

    public void ReadLearningOutcomes(String filePath) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            System.out.println(filePath);
            //Document doc = dBuilder.parse(getClass().getResourceAsStream(filePath));
            Document doc = dBuilder.parse(filePath);
            

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("category");

            int length = nList.getLength();

            for (int j = 0; j < length; j++) {
                Node node = nList.item(j);
                //LearningOutcome lo = new LearningOutcome();

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;

                    String categoryName = eElement.getAttribute("name");

                    //reading learning outcomes
                    int loLength = eElement.getElementsByTagName("lo").getLength();
                    for (int i = 0; i < loLength; i++) {
                        LearningOutcome temp = new LearningOutcome();
                        temp.category = categoryName;
                        temp.name = eElement.getElementsByTagName("lo").item(i).getTextContent();
                        this.loArray.add(temp);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Excepyion caught: " + ex);
        }

    }

    public LearningOutcome getLearningOutcome(String name) {
        for (LearningOutcome lo : loArray) {
            if (lo.name.equalsIgnoreCase(name)) {
                return lo;
            }
        }
        return new LearningOutcome();
    }

    @Override
    public String toString() {
        String str = "";
        for (LearningOutcome lo : this.loArray) {
            str = str + "Category: " + lo.category + " Name: " + lo.name + "\n";
        }

        return str;
    }
}
