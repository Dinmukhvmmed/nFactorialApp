package application.dto;

import application.model.Bigram;

public class BigramMapper {
    public static DtoBigram mapper(Bigram bigram) {
        DtoBigram dtoBigram = new DtoBigram();
        dtoBigram.setLetters(bigram.getLetters());
        dtoBigram.setFrequency(Integer.toString(bigram.getFrequency()));
        return dtoBigram;
    }
}
