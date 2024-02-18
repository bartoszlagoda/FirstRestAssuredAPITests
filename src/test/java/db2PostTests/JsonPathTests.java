package db2PostTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class JsonPathTests {

    @Test
    public void checkSpecificFieldJsonPath(){
        Response response = RestAssured.get("http://localhost:3000/posts/1");
        System.out.println(response.asString());

        //wyciągnięcie wartości secretString
        String secretString = response.path("secretString");
        // przejście do listy zawierajacej winning-numbers
        List<Integer> winningNumbers = response.path("winning-numbers");
        // pierwszy zwyciezca
        String firstWinnerName = response.path("winners.name[0]");
        String firstWinnerName1 = response.path("winners[0].name");
        // drugi zwyciezca
        String secondWinnerName = response.path("winners[1].name");
        // ostatni zwyciezca
        String lastWinnerName = response.path("winners[-1].name");
        // lista imion wszystkich zwyciezcow
        List<String> winnerNames = response.path("winners.name");
        // Mapa zawierajaca pierwszego zwyciezce w liście
        Map<String,?> winner = response.path("winners[0]");
        // Lista map dla wszystkich zwyciezcow
        List<Map<String,?>> winners = response.path("winners");

        // zwyciezca, ktory ma na imie Andrew
        Map<String,?> winnerInfo = response.path("winners.find {it.name=='Andrew'}");
        Integer winnerId = response.path("winners.find {it.name=='Andrew'}.winnerId");
        // największa wartość z tablicy z wygranymi numerami
        Integer maxNumber = response.path("winning-numbers.max()");
        Integer minNumber = response.path("winning-numbers.min()");
        // zwycięzca z największym id
        Map<String,?> winnerMaxId = response.path("winners.max {it.winnerId}");
        // suma pieniędzy, które są wygraną
        Integer moneySum = response.path("winners.collect{it.money}.sum()");
        // lista z id zwyciezcow o imieniu Andrew
        List<Integer> winnerIds = response.path("winners.findAll {it.name=='Andrew'}.winnerId");

    }
}
