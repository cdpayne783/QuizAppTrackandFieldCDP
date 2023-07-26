package org.forestpark.quizapptrackandfieldcdp;

public class Question {
    private String qPrompt;
    private boolean correctAnswer;
    public int sound;

    public Question(String qPrompt, boolean correctAnswer, int sound) {
        this.qPrompt = qPrompt;
        this.correctAnswer = correctAnswer;
        this.sound = sound;
    }

    public String getqPrompt() {
        return qPrompt;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setqPrompt(String qPrompt) {
        this.qPrompt = qPrompt;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getSound() {
        return sound;
    }
    public void setSound(int sound) {
        this.sound = sound;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qPrompt='" + qPrompt + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
