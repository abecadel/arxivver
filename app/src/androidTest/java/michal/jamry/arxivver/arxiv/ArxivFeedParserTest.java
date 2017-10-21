package michal.jamry.arxivver.arxiv;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ArxivFeedParserTest {

    ArxivFeedParser arxivFeedParser = new ArxivFeedParser();

    @Test
    public void parseProperExample1() throws Exception {
        //given
        InputStream stream = new ByteArrayInputStream(FEED2.getBytes(StandardCharsets.UTF_8.name()));

        //when
        ArxivFeed arxivFeed = arxivFeedParser.parse(stream);

        //then
        assertEquals("http://arxiv.org/api/query?search_query=all:electron&id_list=&start=0&max_results=1", arxivFeed.getLink());
        assertEquals("ArXiv Query: search_query=all:electron&id_list=&start=0&max_results=1", arxivFeed.getTitle());
        assertEquals("http://arxiv.org/api/cHxbiOdZaP56ODnBPIenZhzg5f8", arxivFeed.getId());
//        assertEquals("2007-10-08T00:00:00-04:00", arxivFeed.getUpdated().toString()) //TODO
        assertEquals(1000, arxivFeed.getTotalResults());
        assertEquals(0, arxivFeed.getStartIndex());
        assertEquals(1, arxivFeed.getItemsPerPage());

        List<ArxivFeedEntry> arxivFeedEntries = arxivFeed.getEntries();
        assertNotNull(arxivFeedEntries);
        assertEquals(1, arxivFeedEntries.size());

        ArxivFeedEntry arxivFeedEntry = arxivFeedEntries.get(0);
        assertEquals("http://arxiv.org/abs/hep-ex/0307015", arxivFeedEntry.getId());
//        assertEquals(123, arxivFeedEntry.getPublished().getTime()); //TODO

        List<Date> updatedList = arxivFeedEntry.getUpdatedList();
        assertNotNull(updatedList);
        assertEquals(1, updatedList.size());
//        assertEquals(123, updatedList.get(0).getTime()); //TODO

        assertEquals("Multi-Electron Production at High Transverse Momenta in ep Collisions at\n" +
                "  HERA", arxivFeedEntry.getTitle());
        assertEquals("  Multi-electron production is studied at high electron transverse momentum in\n" +
                "positron- and electron-proton collisions using the H1 detector at HERA. The\n" +
                "data correspond to an integrated luminosity of 115 pb-1. Di-electron and\n" +
                "tri-electron event yields are measured. Cross sections are derived in a\n" +
                "restricted phase space region dominated by photon-photon collisions. In general\n" +
                "good agreement is found with the Standard Model predictions. However, for\n" +
                "electron pair invariant masses above 100 GeV, three di-electron events and\n" +
                "three tri-electron events are observed, compared to Standard Model expectations\n" +
                "of 0.30 \\pm 0.04 and 0.23 \\pm 0.04, respectively.\n", arxivFeedEntry.getSummary());


        assertNotNull(arxivFeedEntry.getAuthorList());
        assertEquals(1, arxivFeedEntry.getAuthorList().size());
        assertEquals("H1 Collaboration", arxivFeedEntry.getAuthorList().get(0));

        assertEquals("23 pages, 8 figures and 4 tables", arxivFeedEntry.getComment());
        assertEquals("Eur.Phys.J. C31 (2003) 17-29", arxivFeedEntry.getJournalRef());
        //TODO: links
        assertEquals("hep-ex", arxivFeedEntry.getPrimaryCategory());

        assertNotNull(arxivFeedEntry.getCategories());
        assertEquals(1, arxivFeedEntry.getCategories().size());
        assertEquals("hep-ex", arxivFeedEntry.getCategories().get(0));

    }

    @Test
    public void parseProperExample2() throws Exception {
        //given
        InputStream stream = new ByteArrayInputStream(FEED.getBytes(StandardCharsets.UTF_8.name()));

        //when
        arxivFeedParser.parse(stream);
    }


    public static final String FEED = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<feed xmlns=\"http://www.w3.org/2005/Atom\">\n" +
            "  <link href=\"http://arxiv.org/api/query?search_query%3DLSTM%26id_list%3D%26start%3D0%26max_results%3D10\" rel=\"self\" type=\"application/atom+xml\"/>\n" +
            "  <title type=\"html\">ArXiv Query: search_query=LSTM&amp;id_list=&amp;start=0&amp;max_results=10</title>\n" +
            "  <id>http://arxiv.org/api/wJaXQQxX0EEV1mQFEDZaXp7qJ5g</id>\n" +
            "  <updated>2017-10-21T00:00:00-04:00</updated>\n" +
            "  <opensearch:totalResults xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\">773</opensearch:totalResults>\n" +
            "  <opensearch:startIndex xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\">0</opensearch:startIndex>\n" +
            "  <opensearch:itemsPerPage xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\">10</opensearch:itemsPerPage>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1508.01991v1</id>\n" +
            "    <updated>2015-08-09T06:32:47Z</updated>\n" +
            "    <published>2015-08-09T06:32:47Z</published>\n" +
            "    <title>Bidirectional LSTM-CRF Models for Sequence Tagging</title>\n" +
            "    <summary>  In this paper, we propose a variety of Long Short-Term Memory (LSTM) based\n" +
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
            "observations.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Zhiheng Huang</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Wei Xu</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Kai Yu</name>\n" +
            "    </author>\n" +
            "    <link href=\"http://arxiv.org/abs/1508.01991v1\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1508.01991v1\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1709.06436v1</id>\n" +
            "    <updated>2017-09-19T14:04:15Z</updated>\n" +
            "    <published>2017-09-19T14:04:15Z</published>\n" +
            "    <title>Language Modeling with Highway LSTM</title>\n" +
            "    <summary>  Language models (LMs) based on Long Short Term Memory (LSTM) have shown good\n" +
            "gains in many automatic speech recognition tasks. In this paper, we extend an\n" +
            "LSTM by adding highway networks inside an LSTM and use the resulting Highway\n" +
            "LSTM (HW-LSTM) model for language modeling. The added highway networks increase\n" +
            "the depth in the time dimension. Since a typical LSTM has two internal states,\n" +
            "a memory cell and a hidden state, we compare various types of HW-LSTM by adding\n" +
            "highway networks onto the memory cell and/or the hidden state. Experimental\n" +
            "results on English broadcast news and conversational telephone speech\n" +
            "recognition show that the proposed HW-LSTM LM improves speech recognition\n" +
            "accuracy on top of a strong LSTM LM baseline. We report 5.1% and 9.9% on the\n" +
            "Switchboard and CallHome subsets of the Hub5 2000 evaluation, which reaches the\n" +
            "best performance numbers reported on these tasks to date.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Gakuto Kurata</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Bhuvana Ramabhadran</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>George Saon</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Abhinav Sethy</name>\n" +
            "    </author>\n" +
            "    <arxiv:comment xmlns:arxiv=\"http://arxiv.org/schemas/atom\">to appear in 2017 IEEE Automatic Speech Recognition and Understanding\n" +
            "  Workshop (ASRU 2017)</arxiv:comment>\n" +
            "    <link href=\"http://arxiv.org/abs/1709.06436v1\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1709.06436v1\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1701.03360v3</id>\n" +
            "    <updated>2017-06-05T18:51:08Z</updated>\n" +
            "    <published>2017-01-10T20:03:37Z</published>\n" +
            "    <title>Residual LSTM: Design of a Deep Recurrent Architecture for Distant\n" +
            "  Speech Recognition</title>\n" +
            "    <summary>  In this paper, a novel architecture for a deep recurrent neural network,\n" +
            "residual LSTM is introduced. A plain LSTM has an internal memory cell that can\n" +
            "learn long term dependencies of sequential data. It also provides a temporal\n" +
            "shortcut path to avoid vanishing or exploding gradients in the temporal domain.\n" +
            "The residual LSTM provides an additional spatial shortcut path from lower\n" +
            "layers for efficient training of deep networks with multiple LSTM layers.\n" +
            "Compared with the previous work, highway LSTM, residual LSTM separates a\n" +
            "spatial shortcut path with temporal one by using output layers, which can help\n" +
            "to avoid a conflict between spatial and temporal-domain gradient flows.\n" +
            "Furthermore, residual LSTM reuses the output projection matrix and the output\n" +
            "gate of LSTM to control the spatial information flow instead of additional gate\n" +
            "networks, which effectively reduces more than 10% of network parameters. An\n" +
            "experiment for distant speech recognition on the AMI SDM corpus shows that\n" +
            "10-layer plain and highway LSTM networks presented 13.7% and 6.2% increase in\n" +
            "WER over 3-layer aselines, respectively. On the contrary, 10-layer residual\n" +
            "LSTM networks provided the lowest WER 41.0%, which corresponds to 3.3% and 2.8%\n" +
            "WER reduction over plain and highway LSTM networks, respectively.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Jaeyoung Kim</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Mostafa El-Khamy</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Jungwon Lee</name>\n" +
            "    </author>\n" +
            "    <link href=\"http://arxiv.org/abs/1701.03360v3\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1701.03360v3\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.LG\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.LG\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.AI\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.SD\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1703.03055v1</id>\n" +
            "    <updated>2017-03-08T22:09:38Z</updated>\n" +
            "    <published>2017-03-08T22:09:38Z</published>\n" +
            "    <title>Interpretable Structure-Evolving LSTM</title>\n" +
            "    <summary>  This paper develops a general framework for learning interpretable data\n" +
            "representation via Long Short-Term Memory (LSTM) recurrent neural networks over\n" +
            "hierarchal graph structures. Instead of learning LSTM models over the pre-fixed\n" +
            "structures, we propose to further learn the intermediate interpretable\n" +
            "multi-level graph structures in a progressive and stochastic way from data\n" +
            "during the LSTM network optimization. We thus call this model the\n" +
            "structure-evolving LSTM. In particular, starting with an initial element-level\n" +
            "graph representation where each node is a small data element, the\n" +
            "structure-evolving LSTM gradually evolves the multi-level graph representations\n" +
            "by stochastically merging the graph nodes with high compatibilities along the\n" +
            "stacked LSTM layers. In each LSTM layer, we estimate the compatibility of two\n" +
            "connected nodes from their corresponding LSTM gate outputs, which is used to\n" +
            "generate a merging probability. The candidate graph structures are accordingly\n" +
            "generated where the nodes are grouped into cliques with their merging\n" +
            "probabilities. We then produce the new graph structure with a\n" +
            "Metropolis-Hasting algorithm, which alleviates the risk of getting stuck in\n" +
            "local optimums by stochastic sampling with an acceptance probability. Once a\n" +
            "graph structure is accepted, a higher-level graph is then constructed by taking\n" +
            "the partitioned cliques as its nodes. During the evolving process,\n" +
            "representation becomes more abstracted in higher-levels where redundant\n" +
            "information is filtered out, allowing more efficient propagation of long-range\n" +
            "data dependencies. We evaluate the effectiveness of structure-evolving LSTM in\n" +
            "the application of semantic object parsing and demonstrate its advantage over\n" +
            "state-of-the-art LSTM models on standard benchmarks.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Xiaodan Liang</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Liang Lin</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Xiaohui Shen</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Jiashi Feng</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Shuicheng Yan</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Eric P. Xing</name>\n" +
            "    </author>\n" +
            "    <arxiv:comment xmlns:arxiv=\"http://arxiv.org/schemas/atom\">To appear in CVPR 2017 as a spotlight paper</arxiv:comment>\n" +
            "    <link href=\"http://arxiv.org/abs/1703.03055v1\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1703.03055v1\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.CV\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.CV\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.AI\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.LG\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1611.05104v2</id>\n" +
            "    <updated>2016-12-17T06:47:05Z</updated>\n" +
            "    <published>2016-11-16T00:53:01Z</published>\n" +
            "    <title>A Way out of the Odyssey: Analyzing and Combining Recent Insights for\n" +
            "  LSTMs</title>\n" +
            "    <summary>  LSTMs have become a basic building block for many deep NLP models. In recent\n" +
            "years, many improvements and variations have been proposed for deep sequence\n" +
            "models in general, and LSTMs in particular. We propose and analyze a series of\n" +
            "augmentations and modifications to LSTM networks resulting in improved\n" +
            "performance for text classification datasets. We observe compounding\n" +
            "improvements on traditional LSTMs using Monte Carlo test-time model averaging,\n" +
            "average pooling, and residual connections, along with four other suggested\n" +
            "modifications. Our analysis provides a simple, reliable, and high quality\n" +
            "baseline model.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Shayne Longpre</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Sabeek Pradhan</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Caiming Xiong</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Richard Socher</name>\n" +
            "    </author>\n" +
            "    <link href=\"http://arxiv.org/abs/1611.05104v2\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1611.05104v2\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.AI\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1703.10722v2</id>\n" +
            "    <updated>2017-05-04T17:17:55Z</updated>\n" +
            "    <published>2017-03-31T00:50:37Z</published>\n" +
            "    <title>Factorization tricks for LSTM networks</title>\n" +
            "    <summary>  We present two simple ways of reducing the number of parameters and\n" +
            "accelerating the training of large Long Short-Term Memory (LSTM) networks: the\n" +
            "first one is \"matrix factorization by design\" of LSTM matrix into the product\n" +
            "of two smaller matrices, and the second one is partitioning of LSTM matrix, its\n" +
            "inputs and states into the independent groups. Both approaches allow us to\n" +
            "train large LSTM networks significantly faster to the state-of the art\n" +
            "perplexity. On the One Billion Word Benchmark we improve single model\n" +
            "perplexity down to 23.36.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Oleksii Kuchaiev</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Boris Ginsburg</name>\n" +
            "    </author>\n" +
            "    <arxiv:comment xmlns:arxiv=\"http://arxiv.org/schemas/atom\">accepted to ICLR 2017 Workshop</arxiv:comment>\n" +
            "    <link href=\"http://arxiv.org/abs/1703.10722v2\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1703.10722v2\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.NE\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"stat.ML\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1704.00405v2</id>\n" +
            "    <updated>2017-04-20T01:55:26Z</updated>\n" +
            "    <published>2017-04-03T02:10:19Z</published>\n" +
            "    <title>Syntax Aware LSTM Model for Chinese Semantic Role Labeling</title>\n" +
            "    <summary>  As for semantic role labeling (SRL) task, when it comes to utilizing parsing\n" +
            "information, both traditional methods and recent recurrent neural network (RNN)\n" +
            "based methods use the feature engineering way. In this paper, we propose Syntax\n" +
            "Aware Long Short Time Memory(SA-LSTM). The structure of SA-LSTM modifies\n" +
            "according to dependency parsing information in order to model parsing\n" +
            "information directly in an architecture engineering way instead of feature\n" +
            "engineering way. We experimentally demonstrate that SA-LSTM gains more\n" +
            "improvement from the model architecture. Furthermore, SA-LSTM outperforms the\n" +
            "state-of-the-art on CPB 1.0 significantly according to Student t-test\n" +
            "($p&lt;0.05$).\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Feng Qian</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Lei Sha</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Baobao Chang</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Lu-chen Liu</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Ming Zhang</name>\n" +
            "    </author>\n" +
            "    <link href=\"http://arxiv.org/abs/1704.00405v2\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1704.00405v2\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1709.08432v1</id>\n" +
            "    <updated>2017-09-25T11:31:50Z</updated>\n" +
            "    <published>2017-09-25T11:31:50Z</published>\n" +
            "    <title>House Price Prediction Using LSTM</title>\n" +
            "    <summary>  In this paper, we use the house price data ranging from January 2004 to\n" +
            "October 2016 to predict the average house price of November and December in\n" +
            "2016 for each district in Beijing, Shanghai, Guangzhou and Shenzhen. We apply\n" +
            "Autoregressive Integrated Moving Average model to generate the baseline while\n" +
            "LSTM networks to build prediction model. These algorithms are compared in terms\n" +
            "of Mean Squared Error. The result shows that the LSTM model has excellent\n" +
            "properties with respect to predict time series. Also, stateful LSTM networks\n" +
            "and stack LSTM networks are employed to further study the improvement of\n" +
            "accuracy of the house prediction model.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Xiaochen Chen</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Lai Wei</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Jiaxin Xu</name>\n" +
            "    </author>\n" +
            "    <link href=\"http://arxiv.org/abs/1709.08432v1\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1709.08432v1\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.LG\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.LG\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"stat.ML\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1604.06635v1</id>\n" +
            "    <updated>2016-04-22T12:51:11Z</updated>\n" +
            "    <published>2016-04-22T12:51:11Z</published>\n" +
            "    <title>Bridging LSTM Architecture and the Neural Dynamics during Reading</title>\n" +
            "    <summary>  Recently, the long short-term memory neural network (LSTM) has attracted wide\n" +
            "interest due to its success in many tasks. LSTM architecture consists of a\n" +
            "memory cell and three gates, which looks similar to the neuronal networks in\n" +
            "the brain. However, there still lacks the evidence of the cognitive\n" +
            "plausibility of LSTM architecture as well as its working mechanism. In this\n" +
            "paper, we study the cognitive plausibility of LSTM by aligning its internal\n" +
            "architecture with the brain activity observed via fMRI when the subjects read a\n" +
            "story. Experiment results show that the artificial memory vector in LSTM can\n" +
            "accurately predict the observed sequential brain activities, indicating the\n" +
            "correlation between LSTM architecture and the cognitive process of story\n" +
            "reading.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Peng Qian</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Xipeng Qiu</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Xuanjing Huang</name>\n" +
            "    </author>\n" +
            "    <arxiv:comment xmlns:arxiv=\"http://arxiv.org/schemas/atom\">25th International Joint Conference on Artificial Intelligence\n" +
            "  IJCAI-16</arxiv:comment>\n" +
            "    <link href=\"http://arxiv.org/abs/1604.06635v1\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1604.06635v1\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.CL\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.AI\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.LG\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.NE\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "  <entry>\n" +
            "    <id>http://arxiv.org/abs/1709.04057v1</id>\n" +
            "    <updated>2017-09-12T20:52:22Z</updated>\n" +
            "    <published>2017-09-12T20:52:22Z</published>\n" +
            "    <title>Parallelizing Linear Recurrent Neural Nets Over Sequence Length</title>\n" +
            "    <summary>  Recurrent neural networks (RNNs) are widely used to model sequential data but\n" +
            "their non-linear dependencies between sequence elements prevent parallelizing\n" +
            "training over sequence length. We show the training of RNNs with only linear\n" +
            "sequential dependencies can be parallelized over the sequence length using the\n" +
            "parallel scan algorithm, leading to rapid training on long sequences with small\n" +
            "minibatch size. We abstract prior linear sequence models into a new framework\n" +
            "of linear surrogate RNNs and develop a linear surrogate long short-term memory\n" +
            "(LS-LSTM) powered by a parallel linear recurrence CUDA kernel we implemented.\n" +
            "We evaluate the LS-LSTM on a long sequence noisy autoregressive task and find\n" +
            "the LS-LSTM achieves slightly superior train and test performance to a similar\n" +
            "sized LSTM in 4x less training time. We analyze latency and throughput of the\n" +
            "LS-LSTM and find the LS-LSTM reaches up to 175x the throughput of the LSTM in\n" +
            "the small minibatch long sequence regime.\n" +
            "</summary>\n" +
            "    <author>\n" +
            "      <name>Eric Martin</name>\n" +
            "    </author>\n" +
            "    <author>\n" +
            "      <name>Chris Cundy</name>\n" +
            "    </author>\n" +
            "    <arxiv:comment xmlns:arxiv=\"http://arxiv.org/schemas/atom\">8 Pages, 2 Figures, 1 Table</arxiv:comment>\n" +
            "    <link href=\"http://arxiv.org/abs/1709.04057v1\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link title=\"pdf\" href=\"http://arxiv.org/pdf/1709.04057v1\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"cs.NE\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.NE\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.AI\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"cs.LG\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "</feed>";

    private static final String FEED2 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<feed xmlns=\"http://www.w3.org/2005/Atom\" xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\" xmlns:arxiv=\"http://arxiv.org/schemas/atom\">\n" +
            "  <link xmlns=\"http://www.w3.org/2005/Atom\" href=\"http://arxiv.org/api/query?search_query=all:electron&amp;id_list=&amp;start=0&amp;max_results=1\" rel=\"self\" type=\"application/atom+xml\"/>\n" +
            "  <title xmlns=\"http://www.w3.org/2005/Atom\">ArXiv Query: search_query=all:electron&amp;id_list=&amp;start=0&amp;max_results=1</title>\n" +
            "  <id xmlns=\"http://www.w3.org/2005/Atom\">http://arxiv.org/api/cHxbiOdZaP56ODnBPIenZhzg5f8</id>\n" +
            "  <updated xmlns=\"http://www.w3.org/2005/Atom\">2007-10-08T00:00:00-04:00</updated>\n" +
            "  <opensearch:totalResults xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\">1000</opensearch:totalResults>\n" +
            "  <opensearch:startIndex xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\">0</opensearch:startIndex>\n" +
            "  <opensearch:itemsPerPage xmlns:opensearch=\"http://a9.com/-/spec/opensearch/1.1/\">1</opensearch:itemsPerPage>\n" +
            "  <entry xmlns=\"http://www.w3.org/2005/Atom\" xmlns:arxiv=\"http://arxiv.org/schemas/atom\">\n" +
            "    <id xmlns=\"http://www.w3.org/2005/Atom\">http://arxiv.org/abs/hep-ex/0307015</id>\n" +
            "    <published xmlns=\"http://www.w3.org/2005/Atom\">2003-07-07T13:46:39-04:00</published>\n" +
            "    <updated xmlns=\"http://www.w3.org/2005/Atom\">2003-07-07T13:46:39-04:00</updated>\n" +
            "    <title xmlns=\"http://www.w3.org/2005/Atom\">Multi-Electron Production at High Transverse Momenta in ep Collisions at\n" +
            "  HERA</title>\n" +
            "    <summary xmlns=\"http://www.w3.org/2005/Atom\">  Multi-electron production is studied at high electron transverse momentum in\n" +
            "positron- and electron-proton collisions using the H1 detector at HERA. The\n" +
            "data correspond to an integrated luminosity of 115 pb-1. Di-electron and\n" +
            "tri-electron event yields are measured. Cross sections are derived in a\n" +
            "restricted phase space region dominated by photon-photon collisions. In general\n" +
            "good agreement is found with the Standard Model predictions. However, for\n" +
            "electron pair invariant masses above 100 GeV, three di-electron events and\n" +
            "three tri-electron events are observed, compared to Standard Model expectations\n" +
            "of 0.30 \\pm 0.04 and 0.23 \\pm 0.04, respectively.\n" +
            "</summary>\n" +
            "    <author xmlns=\"http://www.w3.org/2005/Atom\">\n" +
            "      <name xmlns=\"http://www.w3.org/2005/Atom\">H1 Collaboration</name>\n" +
            "    </author>\n" +
            "    <arxiv:comment xmlns:arxiv=\"http://arxiv.org/schemas/atom\">23 pages, 8 figures and 4 tables</arxiv:comment>\n" +
            "    <arxiv:journal_ref xmlns:arxiv=\"http://arxiv.org/schemas/atom\">Eur.Phys.J. C31 (2003) 17-29</arxiv:journal_ref>\n" +
            "    <link xmlns=\"http://www.w3.org/2005/Atom\" href=\"http://arxiv.org/abs/hep-ex/0307015v1\" rel=\"alternate\" type=\"text/html\"/>\n" +
            "    <link xmlns=\"http://www.w3.org/2005/Atom\" title=\"pdf\" href=\"http://arxiv.org/pdf/hep-ex/0307015v1\" rel=\"related\" type=\"application/pdf\"/>\n" +
            "    <arxiv:primary_category xmlns:arxiv=\"http://arxiv.org/schemas/atom\" term=\"hep-ex\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "    <category term=\"hep-ex\" scheme=\"http://arxiv.org/schemas/atom\"/>\n" +
            "  </entry>\n" +
            "</feed>";

}