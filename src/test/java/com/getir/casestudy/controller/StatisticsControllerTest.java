package com.getir.casestudy.controller;

import com.getir.casestudy.service.StatisticService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private StatisticsController statistisController;

    @Mock
    private StatisticService statisticService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(statistisController).build();
    }

    @Test
    public void testGetCustomerStatistics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/statistics/{id}", 1)
                .accept(MediaType.APPLICATION_JSON));

    }

}
