package personal.teslafoil.zubale.quote.integration;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import personal.teslafoil.zubale.quote.models.Vote;
import personal.teslafoil.zubale.quote.models.Vote.VoteValue;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IntegrationTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;
    
    ObjectMapper mapper;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testAddQuote() throws Exception {
        String quoteText = "testAddQuote";
        MockHttpServletRequestBuilder request = put("/api/quote/addquote/" + quoteText)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.TEXT_PLAIN);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        assertEquals(201, response.getStatus());
        JSONObject quoteObject = new JSONObject(response.getContentAsString());
        assertEquals(quoteText, quoteObject.getString("text"));
    }

    @Test
    public void testAddQuoteExist() throws Exception {
        String quoteText = "testAddQuoteExist";

        MockHttpServletRequestBuilder request = put("/api/quote/addquote/" + quoteText)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.TEXT_PLAIN);

        mockMvc.perform(request);
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        assertEquals(409, response.getStatus());
    }

    @Test
    public void testVoteQuote() throws Exception {
        String quoteText = "testVoteQuote";
        MockHttpServletRequestBuilder request1 = put("/api/quote/addquote/" + quoteText)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.TEXT_PLAIN);

        MockHttpServletResponse response1 = mockMvc.perform(request1).andReturn().getResponse();

        JSONObject quoteObject1 = new JSONObject(response1.getContentAsString());
        Vote vote1 = new Vote();
        vote1.setQuoteId(UUID.fromString(quoteObject1.getString("id")));
        vote1.setVoteValue(VoteValue.UP);

        MockHttpServletRequestBuilder request2 = post("/api/quote/vote/")
                .content(mapper.writeValueAsString(vote1))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MockHttpServletResponse response2 = mockMvc.perform(request2).andReturn().getResponse();

        assertEquals(200, response2.getStatus());
        JSONObject quoteObject2 = new JSONObject(response2.getContentAsString());
        assertEquals(quoteObject1.getString("id"), quoteObject2.getString("id"));
        assertEquals(1, quoteObject2.getInt("voteCount"));
        assertEquals(1, quoteObject2.getInt("voteScore"));
        
        Vote vote2 = new Vote();
        vote2.setQuoteId(UUID.fromString(quoteObject1.getString("id")));
        vote2.setVoteValue(VoteValue.DOWN);
        
        MockHttpServletRequestBuilder request3 = post("/api/quote/vote/")
                .content(mapper.writeValueAsString(vote2))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        
        MockHttpServletResponse response3 = mockMvc.perform(request3).andReturn().getResponse();

        assertEquals(200, response3.getStatus());
        JSONObject quoteObject3 = new JSONObject(response3.getContentAsString());
        assertEquals(quoteObject1.getString("id"), quoteObject3.getString("id"));
        assertEquals(2, quoteObject3.getInt("voteCount"));
        assertEquals(0, quoteObject3.getInt("voteScore"));
    }
    
    @Test
    public void testVoteQuoteNotExist() throws Exception {
        Vote vote1 = new Vote();
        vote1.setQuoteId(UUID.randomUUID());
        vote1.setVoteValue(VoteValue.UP);

        MockHttpServletRequestBuilder request = post("/api/quote/vote/")
                .content(mapper.writeValueAsString(vote1))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);

        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        assertEquals(404, response.getStatus());
    }
    
    @Test
    public void testRetrieveOrderedQuotes() throws Exception {
        String quoteText = "testRetrieveOrderedQuotes";
        MockHttpServletRequestBuilder request1 = put("/api/quote/addquote/" + quoteText)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.TEXT_PLAIN);

        MockHttpServletResponse response1 = mockMvc.perform(request1).andReturn().getResponse();

        JSONObject quoteObject = new JSONObject(response1.getContentAsString());
        
        MockHttpServletRequestBuilder request2 = get("/api/quote/quotelist/")
                .accept(MediaType.APPLICATION_JSON_UTF8);
        
        MockHttpServletResponse response2 = mockMvc.perform(request2).andReturn().getResponse();
        
        assertEquals(200, response2.getStatus());
        JSONArray quoteObjects = new JSONArray(response2.getContentAsString());
        assertEquals(1, quoteObjects.length());
        assertEquals(quoteObject.getString("id"), quoteObjects.getJSONObject(0).getString("id"));
    }
    
    @Test
    public void testRetrieveOrderedQuotesEmpty() throws Exception {
        MockHttpServletRequestBuilder request = get("/api/quote/quotelist/")
                .accept(MediaType.APPLICATION_JSON_UTF8);
        
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        
        assertEquals(200, response.getStatus());
        JSONArray quoteObjects = new JSONArray(response.getContentAsString());
        assertEquals(0, quoteObjects.length());
    }
    
    @Test
    public void testRetrieveOrderedQuotesMultiple() throws Exception {
        String quoteText1 = "testRetrieveOrderedQuotesMultiple1";
        MockHttpServletRequestBuilder request1 = put("/api/quote/addquote/" + quoteText1)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.TEXT_PLAIN);

        MockHttpServletResponse response1 = mockMvc.perform(request1).andReturn().getResponse();

        JSONObject quoteObject1 = new JSONObject(response1.getContentAsString());
        
        String quoteText2 = "testRetrieveOrderedQuotesMultiple2";
        MockHttpServletRequestBuilder request2 = put("/api/quote/addquote/" + quoteText2)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.TEXT_PLAIN);

        MockHttpServletResponse response2 = mockMvc.perform(request2).andReturn().getResponse();

        JSONObject quoteObject2 = new JSONObject(response2.getContentAsString());
        
        Vote vote = new Vote();
        vote.setQuoteId(UUID.fromString(quoteObject1.getString("id")));
        vote.setVoteValue(VoteValue.UP);
        
        MockHttpServletRequestBuilder request3 = post("/api/quote/vote/")
                .content(mapper.writeValueAsString(vote))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        
        mockMvc.perform(request3);
        
        MockHttpServletRequestBuilder request4 = get("/api/quote/quotelist/")
                .accept(MediaType.APPLICATION_JSON_UTF8);
        
        MockHttpServletResponse response4 = mockMvc.perform(request4).andReturn().getResponse();
        
        assertEquals(200, response4.getStatus());
        JSONArray quoteObjects = new JSONArray(response4.getContentAsString());
        assertEquals(2, quoteObjects.length());
        assertEquals(quoteObject1.getString("id"), quoteObjects.getJSONObject(0).getString("id"));
        assertEquals(quoteObject2.getString("id"), quoteObjects.getJSONObject(1).getString("id"));
    }
}
