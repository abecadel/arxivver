package michal.jamry.arxivver.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ModelUtilsTest {

    @Test
    public void shortString() {
        String ret = ModelUtils.elipsis("aaa");

        assertEquals("aaa", ret);
    }

    @Test
    public void longString() {
        String ret = ModelUtils.elipsis(longString);

        assertEquals(ellipsedLongString, ret);
    }

    @Test
    public void nullString() {
        String ret = ModelUtils.elipsis(null);

        assertNull(ret);
    }

    String longString = "In this paper, we propose a variety of Long Short-Term Memory (LSTM) based\n" +
            "models for sequence tagging. These models include LSTM networks, bidirectional\n" +
            "LSTM (BI-LSTM) networks, LSTM with a Conditional Random Field (CRF) layer\n" +
            "(LSTM-CRF) and bidirectional LSTM with a CRF layer (BI-LSTM-CRF). Our work is\n" +
            "the first to apply a bidirectional LSTM CRF (denoted as BI-LSTM-CRF) model to\n" +
            "NLP benchmark sequence tagging data sets. We show that the BI-LSTM-CRF model\n" +
            "can efficiently use both past and future input features thanks to a\n" +
            "bidirectional LSTM component. It can also use sentence level tag information\n" +
            "thanks to a CRF layer. The BI-LSTM-CRF model can produce state of the art (or\n" +
            "close to) accuracy on POS, chunking and NER data sets. In addition, it is\n" +
            "robust and has less dependence on word embedding as compared to previous\n" +
            "observations.";

    String ellipsedLongString = "In this paper, we propose a variety of Long Short-Term Memory (LSTM) based\n" +
            "models for sequence tagging. These models include LSTM networks, bidirectional\n" +
            "LSTM (BI-LSTM) networks, LSTM with a Conditional Random Field (CRF) layer\n" +
            "(LSTM-CRF) and bidirectional LSTM with a CRF layer (BI-LSTM-...";
}