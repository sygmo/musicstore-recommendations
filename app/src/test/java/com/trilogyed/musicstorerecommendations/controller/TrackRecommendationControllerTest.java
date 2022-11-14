package com.trilogyed.musicstorerecommendations.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.musicstorerecommendations.model.TrackRecommendation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackRecommendationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TrackRecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrackRecommendationController trackRecommendationController;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnNewTrackRecOnPost() throws Exception {
        TrackRecommendation inTrackRec = new TrackRecommendation();
        inTrackRec.setTrackId(3);
        inTrackRec.setUserId(1);
        inTrackRec.setLiked(true);

        TrackRecommendation outTrackRec = new TrackRecommendation();
        outTrackRec.setId(5);
        outTrackRec.setTrackId(3);
        outTrackRec.setUserId(1);
        outTrackRec.setLiked(true);

        doReturn(outTrackRec).when(trackRecommendationController).createTrackRec(inTrackRec);

        mockMvc.perform(post("/trackRec")
                        .content(mapper.writeValueAsString(inTrackRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(outTrackRec)));
    }

    @Test
    public void shouldReturnTrackRecById() throws Exception {
        TrackRecommendation outTrackRec = new TrackRecommendation();
        outTrackRec.setId(5);
        outTrackRec.setTrackId(3);
        outTrackRec.setUserId(1);
        outTrackRec.setLiked(true);

        doReturn(outTrackRec).when(trackRecommendationController).getTrackRec(5);

        mockMvc.perform(get("/trackRec/5")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(outTrackRec)));
    }

    @Test
    public void shouldReturn204OnGoodUpdate() throws Exception {
        TrackRecommendation inTrackRec = new TrackRecommendation();
        inTrackRec.setId(5);
        inTrackRec.setTrackId(3);
        inTrackRec.setUserId(1);
        inTrackRec.setLiked(false);

        doNothing().when(trackRecommendationController).updateTrackRec(inTrackRec);

        mockMvc.perform(put("/trackRec")
                        .content(mapper.writeValueAsString(inTrackRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn404OnBadUpdate() throws Exception {
        TrackRecommendation inTrackRec = new TrackRecommendation();
        inTrackRec.setId(6);
        inTrackRec.setTrackId(3);
        inTrackRec.setUserId(1);
        inTrackRec.setLiked(false);

        doThrow(new IllegalArgumentException("Track rec not found at given id.")).when(trackRecommendationController).updateTrackRec(inTrackRec);

        mockMvc.perform(put("/trackRec")
                        .content(mapper.writeValueAsString(inTrackRec))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteTrackRedAndReturnNoContent() throws Exception {
        doNothing().when(trackRecommendationController).deleteTrackRec(5);

        mockMvc.perform(delete("/trackRec/5")
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllTrackRecs() throws Exception {
        List<TrackRecommendation> trackRecommendationList = new ArrayList<>();

        TrackRecommendation outTrackRec = new TrackRecommendation();
        outTrackRec.setId(5);
        outTrackRec.setTrackId(3);
        outTrackRec.setUserId(1);
        outTrackRec.setLiked(true);

        trackRecommendationList.add(outTrackRec);

        TrackRecommendation outTrackRec1 = new TrackRecommendation();
        outTrackRec1.setId(6);
        outTrackRec1.setTrackId(7);
        outTrackRec1.setUserId(2);
        outTrackRec1.setLiked(false);

        trackRecommendationList.add(outTrackRec1);

        doReturn(trackRecommendationList).when(trackRecommendationController).getAllTrackRecs();

        mockMvc.perform(get("/trackRec")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(trackRecommendationList)));
    }
}