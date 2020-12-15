package state.score;

import io.Persistable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Score implements Persistable {

    private LocalDateTime timeStamp;
    private int score;

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public int getScore() {
        return score;
    }

    public String getFormattedTimeStamp() {
        return timeStamp.format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
    }

    public static Score createNew(int score) {
        Score newScore = new Score();
        newScore.score = score;
        newScore.timeStamp = LocalDateTime.now();
        return newScore;
    }

    @Override
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append("¤");
        stringBuilder.append(timeStamp.toString());
        stringBuilder.append("¤");
        stringBuilder.append(score);

        return stringBuilder.toString();
    }

    @Override
    public void applySerializedData(String serializedData) {
        String[] tokens = serializedData.split("¤");
        timeStamp = LocalDateTime.parse(tokens[1]);
        score = Integer.parseInt(tokens[2]);
    }
}
