package xmlPathTest;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import org.junit.jupiter.api.Test;

import java.util.List;

public class XmlPathTest {

    @Test
    public void xmlPathTest(){
        String xml = "<filmy>\n" +
                "\t<film gatunek=\"komedia\">\n" +
                "\t\t<id>1</id>\n" +
                "\t\t<nazwa>Forrest Gump</nazwa>\n" +
                "\t\t<ocena>8</ocena>\n" +
                "\t</film>\n" +
                "\t<film gatunek=\"komedia\">\n" +
                "\t\t<id>2</id>\n" +
                "\t\t<nazwa>American Pie</nazwa>\n" +
                "\t\t<ocena>7</ocena>\n" +
                "\t</film>\n" +
                "\t<film gatunek=\"dramat\">\n" +
                "\t\t<id>3</id>\n" +
                "\t\t<nazwa>Zielona mila</nazwa>\n" +
                "\t\t<ocena>9</ocena>\n" +
                "\t</film>\n" +
                "</filmy>";

        String nazwa = XmlPath.from(xml).get("filmy.film.nazwa[0]");
        //Lista nazw filmów
        List<String> nazwy = XmlPath.from(xml).getList("filmy.film.nazwa");
        //pobranie wartosci atrybutu gatunek dla pierwszego filmu
        String gatunek = XmlPath.from(xml).get("filmy.film[0].@gatunek");
        // pobranie wszystkich gałęzi zawierających informacje o filmach (wszystkie nody oznaczone jako film)
        List<Node> filmy = XmlPath.from(xml).get("filmy.film.findAll {element -> return element}");
        // wyciągnięcie z powyższej listy nazwy filmów
        String nazwaFilmu = filmy.get(2).get("nazwa").toString();
        // pobranie wszystkich gałęzi zawierających gatunek komediowy
        List<Node> komedie = XmlPath.from(xml).get("filmy.film.findAll {film -> film.@gatunek=='komedia'}");
        // znajdź film z oceną równą 9
        Node filmOcenaDziewiec = XmlPath.from(xml).get("filmy.film.find {film -> def ocena = film.ocena; ocena =='9'}");
        // // znajdź filmy z oceną wyższą od 7
        List<Node> wiekszeOdSiedem = XmlPath.from(xml).get("filmy.film.findAll {film -> def ocena = film.ocena.toFloat(); ocena > 7}");
        // nazwy wszystkich wprowadzonych filmów
        List<String> nazwyCollect = XmlPath.from(xml).get("filmy.film.collect {it.nazwa}");
    }
}
