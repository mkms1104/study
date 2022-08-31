package com.example.temp.analysis;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import org.junit.jupiter.api.Test;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.phrase_extractor.KoreanPhraseExtractor;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;
import org.snu.ids.kkma.ma.MorphemeAnalyzer;
import org.snu.ids.kkma.ma.Sentence;
import scala.collection.Seq;

import java.util.List;

public class SimpleAnalyzer {
    final String str = "케즈 트리플 시즈널 솔리드 _아이보리_WF52981";

    @Test
    void 코모란_형태소분석기() {
        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

        KomoranResult analyzeResult = komoran.analyze(str);
        System.out.println(analyzeResult.getPlainText());

        analyzeResult.getTokenList().forEach(token -> {
            System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
        });
    }

    @Test
    void 꼬고마_형태소분석기() throws Exception {
        // init KeywordExtractor
        KeywordExtractor ke = new KeywordExtractor();

        // extract keywords
        KeywordList kl = ke.extractKeyword(str, true);

        // print result
        for (int i = 0; i < kl.size(); i++) {
            Keyword kwrd = kl.get(i);
            System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
        }


        // init MorphemeAnalyzer
        MorphemeAnalyzer ma = new MorphemeAnalyzer();

        // create logger, null then System.out is set as a default logger
        ma.createLogger(null);

        // analyze morpheme without any post processing
        List ret = ma.analyze(str);

        // refine spacing
        ret = ma.postProcess(ret);

        // leave the best analyzed result
        ret = ma.leaveJustBest(ret);

        // divide result to setences
        List stl = ma.divideToSentences(ret);

        // print the result
        for (int i = 0; i < stl.size(); i++) {
            Sentence st = (Sentence) stl.get(i);
            System.out.println("===>  " + st.getSentence());
            for (int j = 0; j < st.size(); j++) {
                System.out.println(st.get(j));
            }
        }
    }

    @Test
    void okt_형태소분석기() {
        // Normalize
        CharSequence normalized = OpenKoreanTextProcessorJava.normalize(str);
        System.out.println(normalized);
        // 한국어를 처리하는 예시입니다ㅋㅋ #한국어

        // Tokenize
        Seq<KoreanTokenizer.KoreanToken> tokens = OpenKoreanTextProcessorJava.tokenize(normalized);
        System.out.println(OpenKoreanTextProcessorJava.tokensToJavaStringList(tokens));
        // [한국어, 를, 처리, 하는, 예시, 입니, 다, ㅋㅋ, #한국어]
        System.out.println(OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokens));
        // [한국어(Noun: 0, 3), 를(Josa: 3, 1), 처리(Noun: 5, 2), 하는(Verb(하다): 7, 2), 예시(Noun: 10, 2),
        // 입니다(Adjective(이다): 12, 3), ㅋㅋㅋ(KoreanParticle: 15, 3), #한국어(Hashtag: 19, 4)]

        // Phrase extraction
        List<KoreanPhraseExtractor.KoreanPhrase> phrases = OpenKoreanTextProcessorJava.extractPhrases(tokens, true, true);
        System.out.println(phrases);
        // [한국어(Noun: 0, 3), 처리(Noun: 5, 2), 처리하는 예시(Noun: 5, 7), 예시(Noun: 10, 2), #한국어(Hashtag: 18, 4)]
    }
}
