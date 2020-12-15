package state.score;

import io.Persistable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreList implements Persistable {

    private List<Score> scoreList;

    public ScoreList() {
        scoreList = new ArrayList<>();
    }

    public void add(Score score){
        scoreList.add(score);
    }

    public  List<Score> getTopTen(){
        return scoreList.stream()
                .sorted(Comparator.comparing(Score::getScore).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Score score : scoreList){
            stringBuilder.append(score.serialize());
            stringBuilder.append(COLUMN_DELIMITER);
        }
        return stringBuilder.toString();
    }

    @Override
    public void applySerializedData(String serializedData) {
        String[] tokens = serializedData.split(COLUMN_DELIMITER);
        scoreList.clear();;

        for(int i = 1; i<tokens.length; i++){
            Score score = new Score();
            score.applySerializedData(tokens[i]);
            scoreList.add(score);
        }
    }
}
