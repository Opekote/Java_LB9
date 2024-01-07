package org.university;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class WorkWithAPITest {
//    @Mock
//    private CloseableHttpClient mockHttpClient;

//    @Mock
//    private CloseableHttpResponse mockResponseEntity;

//    @Mock
//    private HttpEntity mockHttpEntity;
//
//
//    @Mock
//    private EntityUtils entityUtils;

    @InjectMocks
    private WorkWithAPI testAPI;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testAPI = new WorkWithAPI("43a0cef4dd3b4c818a5328154582ef5d", "http://api.recrm.ru/json");

    }

//    @Test
//    public void testGetData() throws Exception {
//        String endpointPath = "/example";
//        String queryParams = "param1=value1&param2=value2";
//
//        CloseableHttpResponse realResponse = mock(CloseableHttpResponse.class);
//
//        when(realResponse.getEntity()).thenReturn(mockHttpEntity);
//        when(EntityUtils.toString(mockHttpEntity)).thenReturn("Mocked response");
//
//        when(mockHttpClient.execute(any())).thenReturn(realResponse);
//
//        // Actual method call
//        String result = testAPI.getData(endpointPath, queryParams);
//
//        verify(mockHttpClient).execute(any());
//
//        verify(entityUtils).toString(mockHttpEntity);
//
//        assertEquals("Mocked response", result);
//    }



    @Test
    public void testBuildUrl() {
        testAPI = new WorkWithAPI("43a0cef4dd3b4c818a5328154582ef5d","http://api.recrm.ru/json");
        String path = "/example";
        String queryOptions = "param1=value1&param2=value2";

        String resultUrl = testAPI.buildUrl(path, queryOptions);

        // Define the expected URL based on the provided path and query options
        String expectedUrl = "http://api.recrm.ru/json/example?key=43a0cef4dd3b4c818a5328154582ef5d&param1=value1&param2=value2";

        // Assert that the generated URL matches the expected URL
        assertEquals(expectedUrl, resultUrl);
    }


}
