import com.entities.ForecastEntity;
import com.service.controllers.MainController;
import com.service.model.ForecastModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


public class RestServiceTest {
    @Mock
    private ForecastModel forecastModel;

    @InjectMocks
    private MainController controller;

    private MockMvc mockMvc;
    private ForecastEntity entity1 = new ForecastEntity();
    private ForecastEntity entity2 = new ForecastEntity();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        entity1.setCity("Saratov");
        entity1.setCountry("Russia");
        entity1.setRegion("Saratov oblast");
        entity1.setDate(new Date(1482019200));
        entity1.setHigh((short) 50);
        entity1.setLow((short) 20);

        entity2.setCity("Moscow");
        entity2.setCountry("Russia");
        entity2.setRegion("Moscow region");
        entity2.setDate(new Date(1482019200));
        entity2.setHigh((short) 100);
        entity2.setLow((short) -20);
    }

    @Test
    public void getAllTest() throws Exception{
        List<ForecastEntity> forecastList = new ArrayList<>(Arrays.asList(entity1, entity2));

        when(forecastModel.getAll()).thenReturn(forecastList);

        mockMvc.perform(get("/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(xpath("forecastList/forecast[1]/city").string("Saratov"))
                .andExpect(xpath("forecastList/forecast[1]/country").string("Russia"))
                .andExpect(xpath("forecastList/forecast[1]/region").string("Saratov oblast"))
                .andExpect(xpath("forecastList/forecast[1]/date").string("18 Jan 1970"))
                .andExpect(xpath("forecastList/forecast[1]/high").string("50"))
                .andExpect(xpath("forecastList/forecast[1]/low").string("20"))

                .andExpect(xpath("forecastList/forecast[2]/city").string("Moscow"))
                .andExpect(xpath("forecastList/forecast[2]/country").string("Russia"))
                .andExpect(xpath("forecastList/forecast[2]/region").string("Moscow region"))
                .andExpect(xpath("forecastList/forecast[2]/date").string("18 Jan 1970"))
                .andExpect(xpath("forecastList/forecast[2]/high").string("100"))
                .andExpect(xpath("forecastList/forecast[2]/low").string("-20"));
    }

    @Test
    public void getForecastTest() throws Exception{
        when(forecastModel.getEntity("Saratov", "Russia", "Saratov oblast", "18.12.2016")).thenReturn(entity1);

        mockMvc.perform(get("/getForecast?city=Saratov&country=Russia&region=Saratov oblast&date=18.12.2016"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(xpath("Forecast/city").string("Saratov"))
                .andExpect(xpath("Forecast/country").string("Russia"))
                .andExpect(xpath("Forecast/region").string("Saratov oblast"))
                .andExpect(xpath("Forecast/date").string("18 Jan 1970"))
                .andExpect(xpath("Forecast/high").string("50"))
                .andExpect(xpath("Forecast/low").string("20"));
    }
}
